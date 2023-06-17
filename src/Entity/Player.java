package Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// CLASS PLAYER MENGATUR SEGALA TTNG PLAYER

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyHandler;
    PlayerSkin skin;
    public final static int scale = 2;
    public boolean onAir = false;
    public boolean isJumping = false;
    public double velocity, floor;
    public Player(GamePanel gp, KeyHandler keyHandler, String color) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.velocity = 0;
        floor = (gp.tileSize+6)*2;
        setDefaultValues();

        // Set skin player
        skin = new PlayerSkin("green");

        // SolidArea itu collision box untuk player (area yg dideteksi jika menabrak collision box obstacle)
        solidArea = new Rectangle();
        solidArea.width = 7*gp.scale;
        solidArea.height = 19*gp.scale;
        solidArea.x = x - (gp.tileSize*Player.scale/2) - (solidArea.width/2);
        solidArea.y = 8*gp.scale;
    }
    public void setDefaultValues(){
        x = gp.tileSize*2;
        y = (gp.tileSize+6)*2;
        speed = 5;
        direction = "run";
    }

    // Me-return lokasi solidArea pada layar
    public int getSolidAreaX(){ return (int) (solidArea.x + x); }
    public int getSolidAreaY(){ return (int) (solidArea.y + y); }

    public void update(){
        //System.out.println("Y = "+y+"  v = "+velocity);
        if(keyHandler.upPressed || isJumping){
            direction = "up";
        } else if (keyHandler.downPressed){
            direction = "down";
        } else if (keyHandler.leftPressed){
            x-=speed;
        } else if (keyHandler.rightPressed) {
            x+=speed;
        } else{
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

        // TODO: Make Jump better
        // Kode dibawah ini mengatur dino lompat
        if (keyHandler.upPressed && onFloor() && !isJumping){
            // saat tekan naik & player di lantai:
            gp.playSFX(1);
            velocity = 20;
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
            velocity = -20;
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
    public void playDamageAnimation(){
        direction = "dead";
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
                image = skin.up.get(spriteNum);
                break;
            case "down":
                image = skin.down.get(spriteNum);
                break;
            case "run":
                image = skin.run.get(spriteNum);
                break;
            case "dead":
                image = skin.dead.get(gp.actualFPS%5);
                break;
        }
        // Fill ukuran sprite player
        //g2.fillRect((int)x, (int)y, gp.tileSize*Player.scale, gp.tileSize*Player.scale);

        // Draw Dino on screen
        g2.drawImage(image, (int) x, (int) y, gp.tileSize*Player.scale, gp.tileSize*Player.scale, null);
        // Draw solidArea
        //g2.fillRect(getSolidAreaX(), getSolidAreaY(), solidArea.width, solidArea.height);
    }
}
