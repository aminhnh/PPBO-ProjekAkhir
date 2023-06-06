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


        this.solidArea = new Rectangle();
        solidArea.x = x;
        solidArea.y = getSolidAreaY();
        solidArea.width = gp.tileSize;
        solidArea.height = (int) (gp.tileSize*0.4);

        getImage();
        System.out.println("Obstacle Spawned");
    }
    public void getImage(){
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public int getSolidAreaX(){
        return x;
    }
    public int getSolidAreaY(){
        return (int) ( y + (0.6*gp.tileSize));
    }
}
