package tile;

import Entity.Player;
import main.GamePanel;
import java.awt.*;
import java.util.ArrayList;

// CLASS YANG MENGATUR OBSTACLE

public class ObstacleManager {
    GamePanel gp;
    ArrayList<Obstacle> obstacles;
    Player player1, player2;
    int x, y, updateCounter, spawnCounter, speed;

    public ObstacleManager(GamePanel gp, Player player1, Player player2){
        this.gp = gp;
        x = gp.tileSize*4;
        y = gp.tileSize*3;
        obstacles = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        spawnCounter = 0;
        updateCounter = 0;
        speed = 4;
    }
    public void update(){
        updateCounter++;
        // Check collision pada semua obstacles
        for (Obstacle obs : obstacles) {
            checkCollision(player1, obs);
        }
        // Tambah obstacle setiap satu detik
        if(updateCounter%gp.FPS == 1){
            spawnCounter++;
            spawnObstacle();
        }
    }
    // TODO: move obstacles
    // move every obstacle to the left by the speed
    // change speed
    public void spawnObstacle(){
        obstacles.add(new Obstacle(gp, gp.tileSize*(4 + (2*spawnCounter)), gp.tileSize*3));
    }

    public void checkCollision(Player player, Obstacle obs){
        int px1 = player.getSolidAreaX() ;
        int px2 = player.getSolidAreaX() + player.solidArea.width;
        int ox1 = obs.x;
        int ox2 = obs.x + obs.solidArea.width;

        int py1 = player.getSolidAreaY();
        int py2 = player.getSolidAreaY() + player.solidArea.height;
        int oy1 = obs.y;
        int oy2 = obs.y + obs.solidArea.height;

        //System.out.println("PX1: "+px1+ " PX2: "+px2+" OX1: "+ox1+" OX2 "+ox2);
        // Intersection antara player dan obstacle
        if (px1 < ox2 && px2 > ox1 && py1 < oy2 && py2 > oy1){
            System.out.println("GAME OVER");

        }
    }

    public void draw(Graphics2D g2){

        // Loop untuk menampilkan setiap obstacle di ArrayList obstacles
        for (Obstacle obs: obstacles) {
            //g2.fillRect(obs.solidArea.x, obs.getSolidAreaY(), obs.width, obs.solidArea.height);
            g2.drawImage(obs.image, obs.x, obs.y, gp.tileSize, gp.tileSize, null);
        }

        //g2.fillRect(obstacles[0].x, obstacles[0].y, obstacles[0].width, obstacles[0].height);
        //g2.drawImage(obstacles[0].image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
