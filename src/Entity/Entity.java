package Entity;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Entity {
    public double x, y, speed;
    public BufferedImage run1, run2, run3, run4, run5, run6;
    public BufferedImage down1, down2, down3, down4, down5, down6;
    public BufferedImage up1, up2;
    public ArrayList<BufferedImage> run, down, up;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int jumpCounter = 0;
}
