package main;
import Entity.Player;
import tile.ObstacleManager;
import tile.TileManager;
import javax.swing.*;
import java.awt.*;

// CLASS UNTUK MENGATUR GAME

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int originalTileSize = 16;  // 16x16px
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 16x3 = 48
    public final int maxScreenCol = 12; // Banyak kolom tile
    public final int maxScreenRow = 10; // Banyak baris tile
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // FPS
    int FPS =  60; // Berapa kali game di-update per detik

    // SYSTEM
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    Thread gameThread;

    // ENTITY & OBJECT
    Player player = new Player(this, keyHandler);
    ObstacleManager obstacleManager = new ObstacleManager(this, player, player);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Tile diluar layar akan di load
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // fokus menerima input
    }
    public void startGameThread(){
        playMusic(0);
        gameThread = new Thread(this);
        // Dengan memulai thread, run() di class ini (this) akan dijalankan
        gameThread.start();
    }
    @Override
    public void run() {
        // Method ini memanggil update() 60 kali per detik (sesuai dengan FPS)

        double drawInterval = 1000000000/FPS ; // 1 miliar nanosecond (1 detik)/FPS
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //Untuk menampilkan FPS
        int timer = 0;
        int drawCount = 0;

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

            // Menampilkan FPS - Jika 1 detik sudah berlalu, cek berapa kali update telah dilakukan
            if (timer >= 1000000000){
                //System.out.println("FPS : "+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        // Memanggil method update pada player dan obstacleManager 60 kali per detik
        player.update();
        obstacleManager.update();
    }

    // Method untuk menampilkan objek dll. pads screen
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // konversi g jd Graphics2D karena ada lebih bynk function

        // Tile
        tileManager.draw(g2);

        // Obstacle
        obstacleManager.draw(g2);

        // Player
        player.draw(g2);

        // Setelah drawing selesai, hapus Graphics2D ini (good for memory)
        g2.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSFX(int i){
        sound.setFile(i);
        sound.play();
    }

}
