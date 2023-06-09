package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obstacle {
    public BufferedImage image;
    public Rectangle solidArea;
    public int x, y, width, height;
    public GamePanel gp;
    public Obstacle(GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.width = gp.tileSize;
        this.height = gp.tileSize;

        // SolidArea merupakan collider box obstacle
        this.solidArea = new Rectangle();
        solidArea.x = x;
        solidArea.y = getSolidAreaY();
        solidArea.width = gp.tileSize;
        solidArea.height = (int) (gp.tileSize*0.2);

        getObstacleImage();
    }
    public void getObstacleImage(){
        // Me-load image
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Me-return posisi solidArea pada layar
    public int getSolidAreaX(){
        return x;
    }
    public int getSolidAreaY(){
        return (int) ( y + (0.8*gp.tileSize));
    }
}
