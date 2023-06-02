package main;
import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int originalTileSize = 16;  // 16x16 24x24
    public final int scale = 3; // 48
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 12;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    // FPS
    int FPS =  60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // tile/barang diluar layar akan di load
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // fokus menerima input
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // Dengan memulai thread, run() di class ini (this) akan dijalankan
    }
    @Override
    public void run() {
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
//                System.out.println("FPS : "+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // konversi g jd Graphics2D karena ada lebih bynk function
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose(); // stlh drawing selesai, hapus Graphics2D ini (good for memory)
    }
}
