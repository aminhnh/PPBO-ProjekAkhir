package Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            run1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_05.png"));
            run2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_06.png"));
            run3 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_07.png"));
            run4 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_08.png"));
            run5 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_09.png"));
            run6 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_10.png"));
            run = new ArrayList<>(Arrays.asList(run1, run2, run3, run4, run5, run6));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_06.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_07.png"));
            up = new ArrayList<>(Arrays.asList(up1, up2, up1, up2, up1, up2));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_18.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_19.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_20.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_21.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_22.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/dino_blue_23.png"));
            down = new ArrayList<>(Arrays.asList(down1, down2, down3, down4, down5, down6));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyHandler.upPressed){
            direction = "up";
//            y -= speed;
        } else if (keyHandler.downPressed){
            direction = "down";
//            y += speed;
        } else {
            direction = "run";
        }
        spriteCounter++;
        if (spriteCounter > 5){ //animasinya 12 fps
            spriteNum++;
            if (spriteNum > 5){
                spriteNum=0;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction){
            case "up":
                image = up.get(spriteNum);
                break;
            case "down":
                image = down.get(spriteNum);
                break;
            case "run":
                image = run.get(spriteNum);
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize*2, 2*gp.tileSize, null);
    }
}
