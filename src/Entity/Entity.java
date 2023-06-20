package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// ABSTRACT CLASS UNTUK ENTITY (player dan makhluk lain jika perlu)

public abstract class Entity {
//    Attribute
    private int x, y, speed;
    private String direction;
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int jumpCounter = 0;
    private Rectangle solidArea;

//    Getter Setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public int getJumpCounter() {
        return jumpCounter;
    }

    public void setJumpCounter(int jumpCounter) {
        this.jumpCounter = jumpCounter;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
