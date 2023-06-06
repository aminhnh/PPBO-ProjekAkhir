package tile;

import Entity.Entity;
import Entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ObstacleManager {
    GamePanel gp;
    Obstacle[] obstacles;
    Player player1, player2;
    int x, y;

    public ObstacleManager(GamePanel gp, Player player1, Player player2){
        this.gp = gp;
        x = gp.tileSize*4;
        y = gp.tileSize*3;
        obstacles = new Obstacle[1];
        getObstacleImage();
        this.player1 = player1;
        this.player2 = player2;
    }
    public void spawnObstacle(){}
    public void update(){
        for (Obstacle obs : obstacles) {
            checkCollision(player1, obs);
        }
    }
    public void checkCollision(Player player, Obstacle obs){
        int px1 = player.getSolidAreaX() ;
        int px2 = player.getSolidAreaX() + player.solidArea.width;
        int ox1 = obs.x;
        int ox2 = obs.x + obs.width;

        int py1 = player.getSolidAreaY();
        int py2 = player.getSolidAreaY() + player.solidArea.height;
        int oy1 = obs.y;
        int oy2 = obs.y + player.solidArea.height;

//        System.out.println("PX1: "+px1+ " PX2: "+px2+" OX1: "+ox1+" OX2 "+ox2);
        if (px1 < ox2 && px2 > ox1 && py1 < oy2 && py2 > oy1){
            System.out.println("GAME OVER");
//            System.out.println(px1 < ox2);
//            System.out.println("Game Over  "+"PX1: "+px1+ " PX2: "+px2+" OX1: "+ox1+" OX2 "+ox2);
        }
    }
    public void getObstacleImage(){
        try {
            obstacles[0] = new Obstacle();
            obstacles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike.png"));
            obstacles[0].solidArea = new Rectangle();
            obstacles[0].x = gp.tileSize*4;
            obstacles[0].y = (int) (gp.tileSize*3 + (0.6*gp.tileSize));
            obstacles[0].width = gp.tileSize;
            obstacles[0].height = (int) (gp.tileSize*0.4);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.fillRect(obstacles[0].x, obstacles[0].y, obstacles[0].width, obstacles[0].height);
        g2.drawImage(obstacles[0].image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
