package tile;

import Entity.Entity;
import Entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ObstacleManager {
    GamePanel gp;
    ArrayList<Obstacle> obstacles;
    Player player1, player2;
    int x, y, spawnCounter;

    public ObstacleManager(GamePanel gp, Player player1, Player player2){
        this.gp = gp;
        x = gp.tileSize*4;
        y = gp.tileSize*3;
        obstacles = new ArrayList<>();
        this.player1 = player1;
        this.player2 = player2;
        spawnCounter = 0;
    }
    public void update(){
        spawnCounter++;
        for (Obstacle obs : obstacles) {
            checkCollision(player1, obs);
        }
        if(spawnCounter%60 == 1){
            spawnObstacle();
        }
    }
    // TODO
    // Every few seconds, spawn a new obstacle
    // move every obstacle to the left by the speed
    // change speed
    public void spawnObstacle(){
        obstacles.add(new Obstacle(gp, gp.tileSize*4 + spawnCounter, gp.tileSize*3));
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

//        System.out.println("PX1: "+px1+ " PX2: "+px2+" OX1: "+ox1+" OX2 "+ox2);
        // Intersection antara player dan obstacle
        if (px1 < ox2 && px2 > ox1 && py1 < oy2 && py2 > oy1){
            System.out.println("GAME OVER");
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TODO buat loop untuk nampilin setiap elemen di list obstacles
        for (Obstacle obs: obstacles) {
            g2.fillRect(obs.solidArea.x, obs.getSolidAreaY(), obs.width, obs.solidArea.height);
            g2.drawImage(obs.image, obs.x, obs.y, gp.tileSize, gp.tileSize, null);
        }
//        g2.fillRect(obstacles[0].x, obstacles[0].y, obstacles[0].width, obstacles[0].height);
//        g2.drawImage(obstacles[0].image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
