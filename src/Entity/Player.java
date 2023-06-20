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
//    Attribute
    private GamePanel gp;
    private KeyHandler keyHandler;
    private PlayerSkin skin;
    private final static int scale = 2;
    private boolean onAir = false;
    private boolean isJumping = false;
    private double velocity, floor;

//    Constructor
    public Player(GamePanel gp, KeyHandler keyHandler, String color) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.velocity = 0;
        floor = (gp.tileSize+6)*2;
        setDefaultValues();

        // Set skin player
        skin = new PlayerSkin("green");

        // SolidArea itu collision box untuk player (area yg dideteksi jika menabrak collision box obstacle)
        setSolidArea(new Rectangle());
        getSolidArea().width = 7*gp.scale;
        getSolidArea().height = 19*gp.scale;
        getSolidArea().x = getX() - (gp.tileSize*Player.scale/2) - (getSolidArea().width/2);
        getSolidArea().y = 8*gp.scale;
    }

//    Getter Setter
    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public PlayerSkin getSkin() {
        return skin;
    }

    public void setSkin(PlayerSkin skin) {
        this.skin = skin;
    }

    public boolean isOnAir() {
        return onAir;
    }

    public void setOnAir(boolean onAir) {
        this.onAir = onAir;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getFloor() {
        return floor;
    }

    public void setFloor(double floor) {
        this.floor = floor;
    }

    //    Method
    public void setDefaultValues(){
        setX(gp.tileSize*2);
        setY((gp.tileSize+6)*2);
        setSpeed(5);
        setDirection("run");
    }

    // Me-return lokasi solidArea pada layar
    public int getSolidAreaX(){ return (int) (getSolidArea().x + getX()); }
    public int getSolidAreaY(){ return (int) (getSolidArea().y + getY()); }

    public void update(){
        //System.out.println("Y = "+y+"  v = "+velocity);
        if(keyHandler.upPressed || isJumping){
            setDirection("up");
        } else if (keyHandler.downPressed){
            setDirection("down");
        } else if (keyHandler.leftPressed){
            setX(super.getX()-super.getSpeed());
        } else if (keyHandler.rightPressed) {
            setX(getX()+getSpeed());
        } else{
            setDirection("run");
        }
        setSpriteCounter(getSpriteCounter()+1);
        if (getSpriteCounter() > 5){
            setSpriteNum(getSpriteNum()+1);
            if (getSpriteNum() > 5){
                setSpriteNum(0);
            }
            setSpriteCounter(0);
            setJumpCounter(getJumpCounter()+1);
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
            move(getSpeed());
            velocity--;
            onAir = true;
        }
        if (velocity == 0 && onAir){
            // saat di puncak lompat
            velocity = -20;
        }
        if (velocity < 0 ){
            // dino turun
            move(-getSpeed());
            velocity++;
            onAir = false;
        }
        if (velocity == 0 && onFloor()){
            isJumping = false;
        }

    }
    public void playDamageAnimation(){
        setDirection("dead");
    }
    public void move(double v){
        setY((int) (getY()-v));
    }
    public boolean onFloor(){
        return getY() <= floor;
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (getDirection()){
            case "up":
                image = skin.getUp().get(getSpriteNum());
                break;
            case "down":
                image = skin.getDown().get(getSpriteNum());
                break;
            case "run":
                image = skin.getRun().get(getSpriteNum());
                break;
            case "dead":
                image = skin.getDead().get(gp.actualFPS%5);
                break;
        }
        // Fill ukuran sprite player
        //g2.fillRect((int)x, (int)y, gp.tileSize*Player.scale, gp.tileSize*Player.scale);

        // Draw Dino on screen
        g2.drawImage(image, (int) getX(), (int) getY(), gp.tileSize*Player.scale, gp.tileSize*Player.scale, null);
        // Draw solidArea
        //g2.fillRect(getSolidAreaX(), getSolidAreaY(), solidArea.width, solidArea.height);
    }
}
