package main;
import Entity.Player;
import javax.swing.*;
import java.awt.*;
import tile.ObstacleManager;

// CLASS UNTUK MENGATUR GAME

public class GamePanel extends JPanel implements Runnable{
//    Attribute
    // Screen Settings
    private final int originalTileSize = 16;  // 16x16px
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 16x3 = 48
    private final int maxScreenCol = 12; // Banyak kolom tile
    private final int maxScreenRow = 10; // Banyak baris tile
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    // FPS
    private final int FPS =  60; // Berapa kali game di-update per detik
    private int actualFPS;

    // SYSTEM
    private KeyHandler keyHandler = new KeyHandler(this);
    private Sound music = new Sound();
    private Sound sfx = new Sound();
    private Sound sfxMoveCursor = new Sound();
    private UI ui = new UI(this);
    private Thread gameThread;

    // ENTITY & OBJECT
    private Player player;
    private ObstacleManager obstacleManager;
    private String winner;

    // GAME STATE
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int gameOverState = 3;
    private int score, highScore, counterScore = 0;

//    Constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Tile diluar layar akan di load
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // fokus menerima input
        player = new Player(this, keyHandler, "green");
        obstacleManager = new ObstacleManager(this, player, player);
    }

//    Getter Setter

    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getFPS() {
        return FPS;
    }

    public int getActualFPS() {
        return actualFPS;
    }

    public void setActualFPS(int actualFPS) {
        this.actualFPS = actualFPS;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public Sound getMusic() {
        return music;
    }

    public void setMusic(Sound music) {
        this.music = music;
    }

    public Sound getSfx() {
        return sfx;
    }

    public void setSfx(Sound sfx) {
        this.sfx = sfx;
    }

    public Sound getSfxMoveCursor() {
        return sfxMoveCursor;
    }

    public void setSfxMoveCursor(Sound sfxMoveCursor) {
        this.sfxMoveCursor = sfxMoveCursor;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ObstacleManager getObstacleManager() {
        return obstacleManager;
    }

    public void setObstacleManager(ObstacleManager obstacleManager) {
        this.obstacleManager = obstacleManager;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getTitleState() {
        return titleState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCounterScore() {
        return counterScore;
    }

    public void setCounterScore(int counterScore) {
        this.counterScore = counterScore;
    }

    //    Method
    public void setupGame(){
        gameState = titleState;
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        // Dengan memulai thread, run() di class ini (this) akan dijalankan
        gameThread.start();
    }
    @Override
    public void run() {
        // Method ini memanggil update() 60 kali per detik (sesuai dengan FPS)

        double drawInterval = 1000000000.0/FPS ; // 1 miliar nanosecond (1 detik)/FPS
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //Untuk menampilkan FPS
        int timer = 0;
        int drawCount = 0;
        counterScore = 0;

        while(gameThread != null){ // selama gameThread ada, jalankan kode di {}
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                update(); // Update info (cth: posisi karakter)
                repaint(); // Draw layar dengan info yg telah di update (dia memanggil method paintComponent() )
                delta--;
                drawCount++;
            }

            // Jika 1 detik sudah berlalu, simpan berapa kali update telah dilakukan
            // dan reset counter
            if (timer >= 1000000000){
                actualFPS = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        // Memanggil method update pada player dan obstacleManager 60 kali per detik
        if (gameState == playState){
            counterScore++;
            if (counterScore == 6){
                score++;
                counterScore = 0;
            }
            player.update();
            obstacleManager.update();
        }
        else if (gameState == gameOverState){
            // Do nothing
        }
        else if (gameState == pauseState){
            // Do nothing
        }
    }

    // Method untuk menampilkan objek dll. pads screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // konversi g jd Graphics2D karena ada lebih bynk function

        // Awal game (title screen)
        if (gameState == titleState){
            ui.draw(g2);
        }
        else {
            // Tile
            //tileManager.draw(g2);

            // Obstacle
            obstacleManager.draw(g2);

            // Player
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        // Setelah drawing selesai, hapus Graphics2D ini (good for memory)
        g2.dispose();
    }
    public void playMusic(){
        music.setFile(0);
        music.play();
        music.loop();
    }
    public void playSFX(int i){
        sfx.setFile(i);
        sfx.play();
    }

    public void playSFXCursorMove(int i) {
        sfxMoveCursor.setFile(i);
        sfxMoveCursor.play();
    }
    public void stopMusic(){
        music.stop();
    }

    public void exitGame(){
        System.exit(0);
    }
    public void resetGame(){
        score = 0;
        player.setDefaultValues();
        obstacleManager.resetObstacles();
        gameState = playState;
        playMusic();
    }
}
