package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// ABSTRACT CLASS UNTUK ENTITY (player dan makhluk lain jika perlu)

public abstract class Entity {
    public int x, y, speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int jumpCounter = 0;
    public Rectangle solidArea;
}
