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
    public final static int scale = 2;
//    public boolean onFloor = true;
    public boolean onAir = false;
    public boolean isJumping = false;
    public double velocity, floor;
    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.velocity = 0;
        setDefaultValues();
        getPlayerImage();
        floor = (gp.tileSize+6)*2;
    }
    public void setDefaultValues(){
        x = gp.tileSize*2;
        y = (gp.tileSize+6)*2;
        speed = 4;
        direction = "run";
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
            up = new ArrayList<>(Arrays.asList(up1, up1, up1, up1, up1, up1));

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
        System.out.println("Y = "+y+"  v = "+velocity);
        if(keyHandler.upPressed){
            direction = "up";
        } else if (keyHandler.downPressed){
            direction = "down";
        } else {
            direction = "run";
        }
        spriteCounter++;
        if (spriteCounter > 5){
            spriteNum++;
            if (spriteNum > 5){
                spriteNum=0;
            }
            spriteCounter = 0;
            jumpCounter++;
        }

        if (keyHandler.upPressed && onFloor() && !isJumping){
            // saat tekan naik
            velocity = 15;
            isJumping = true;
        }
        if (velocity > 0){
            // dino lompat
            move(speed);
            velocity--;
            onAir = true;
        }
        if (velocity == 0 && onAir){
            // saat di puncak lompat
            velocity = -15;
        }
        if (velocity < 0 ){
            // dino turun
            move(-speed);
            velocity++;
            onAir = false;
        }
        if (velocity == 0 && onFloor()){
            isJumping = false;
        }
    }
    public void move(double v){
        y -= v;
    }
    public boolean onFloor(){
        return y <= floor;
    }
    public void draw(Graphics2D g2){

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
//        g2.setColor(Color.white);
//        g2.fillRect(gp.tileSize*3, gp.tileSize*4, gp.tileSize, gp.tileSize); //Floor reference
        // Fill ukuran sprite player
//        g2.fillRect(x, y, gp.tileSize*Player.scale, gp.tileSize*Player.scale);

        // Draw Dino on screen
        g2.drawImage(image, (int) x, (int) y, gp.tileSize*Player.scale, gp.tileSize*Player.scale, null);
    }
}
