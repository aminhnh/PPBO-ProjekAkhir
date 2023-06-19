package tile;

import Entity.Player;
import main.GamePanel;
import main.Sound;

import java.awt.*;
import java.util.ArrayList;

// CLASS YANG MENGATUR OBSTACLE

public class ObstacleManager {
    GamePanel gp;
    ArrayList<Obstacle> obstacles;
    ArrayList<Land> listLand;
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
        speed = 10;

        listLand = new ArrayList<>();
        setLand();
    }
    public void setLand(){
        for(int i = 0; i < 15; i++){
            Land land = new Land();
            land.x = (int) i * gp.tileSize;
            listLand.add(land);
        }
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
        moveLand();
        moveObstacles();
        deleteOffscreenObstacles();
    }
    // TODO: move other tiles
    // TODO: change speed to be faster as time passes
    // TODO: randomize obstacles distance

    public void spawnObstacle(){
        obstacles.add(new Obstacle(gp, gp.screenWidth, gp.tileSize*3));
    }
    public void moveObstacles(){
        for (Obstacle obs: obstacles) {
            obs.x -= speed;
        }
    }
    public void moveLand(){
        System.out.println(listLand.size());
        for(Land land : listLand){
            land.x -= speed;
        }
        Land firstLand = listLand.get(0);
        if(firstLand.x + gp.tileSize <  - (gp.tileSize*2)){
            firstLand.x = listLand.get(listLand.size()-1).x + gp.tileSize;
            listLand.add(firstLand);
            listLand.remove(0);
        }
    }
    public void deleteOffscreenObstacles(){
        obstacles.removeIf(obs -> obs.x < -(gp.tileSize+10));
    }
    public void checkCollision(Player player, Obstacle obs){
        int px1 = player.getSolidAreaX() ;
        int px2 = player.getSolidAreaX() + player.solidArea.width;
        int ox1 = obs.getSolidAreaX();
        int ox2 = obs.getSolidAreaX() + obs.solidArea.width;

        int py1 = player.getSolidAreaY();
        int py2 = player.getSolidAreaY() + player.solidArea.height;
        int oy1 = obs.getSolidAreaY();
        int oy2 = obs.getSolidAreaY() + obs.solidArea.height;

        // Intersection antara player dan obstacle
        if (px1 < ox2 && px2 > ox1 && py1 < oy2 && py2 > oy1){
            gp.stopMusic();
            gp.playSFX(3);
            player.playDamageAnimation();
            gp.gameState = gp.gameOverState;

            // DEBUG
            //System.out.println("GAME OVER");
            //System.out.println("PX1: "+px1+ " PX2: "+px2+" OX1: "+ox1+" OX2 "+ox2);
        }
    }
    public void draw(Graphics2D g2){
        // Set background
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Loop untuk menampilkan setiap obstacle di ArrayList obstacles
        for (Obstacle obs: obstacles) {
            //g2.setColor(Color.white);
            //g2.fillRect(obs.getSolidAreaX(), obs.getSolidAreaY(), obs.solidArea.width, obs.solidArea.height);
            g2.drawImage(obs.image, obs.x, obs.y, gp.tileSize, gp.tileSize, null);
        }
        for (Land land : listLand){
            g2.drawImage(land.image, land.x, gp.tileSize*4, gp.tileSize, gp.tileSize, null);
            g2.drawImage(land.image, land.x, gp.tileSize*9, gp.tileSize, gp.tileSize, null);
        }
    }
    public void resetObstacles(){
        obstacles.clear();
        x = gp.tileSize*4;
        y = gp.tileSize*3;
        speed = 10;
    }

}
