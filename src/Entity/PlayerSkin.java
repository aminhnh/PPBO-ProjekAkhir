package Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerSkin {
//    Attribute
    private BufferedImage run1, run2, run3, run4, run5, run6;
    private BufferedImage down1, down2, down3, down4, down5, down6;
    private BufferedImage up1, up2;
    private BufferedImage dead1, dead2, dead3;
    private ArrayList<BufferedImage> run, down, up, dead;
    private static String[] skinColor = {"blue", "green", "red", "yellow"};
    private String color;

//    Constructor
    public PlayerSkin(String color){
        this.color = color.toLowerCase();
        getPlayerImage();
    }

//    Getter Setter


    public BufferedImage getRun1() {
        return run1;
    }

    public void setRun1(BufferedImage run1) {
        this.run1 = run1;
    }

    public BufferedImage getRun2() {
        return run2;
    }

    public void setRun2(BufferedImage run2) {
        this.run2 = run2;
    }

    public BufferedImage getRun3() {
        return run3;
    }

    public void setRun3(BufferedImage run3) {
        this.run3 = run3;
    }

    public BufferedImage getRun4() {
        return run4;
    }

    public void setRun4(BufferedImage run4) {
        this.run4 = run4;
    }

    public BufferedImage getRun5() {
        return run5;
    }

    public void setRun5(BufferedImage run5) {
        this.run5 = run5;
    }

    public BufferedImage getRun6() {
        return run6;
    }

    public void setRun6(BufferedImage run6) {
        this.run6 = run6;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getDown3() {
        return down3;
    }

    public void setDown3(BufferedImage down3) {
        this.down3 = down3;
    }

    public BufferedImage getDown4() {
        return down4;
    }

    public void setDown4(BufferedImage down4) {
        this.down4 = down4;
    }

    public BufferedImage getDown5() {
        return down5;
    }

    public void setDown5(BufferedImage down5) {
        this.down5 = down5;
    }

    public BufferedImage getDown6() {
        return down6;
    }

    public void setDown6(BufferedImage down6) {
        this.down6 = down6;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDead1() {
        return dead1;
    }

    public void setDead1(BufferedImage dead1) {
        this.dead1 = dead1;
    }

    public BufferedImage getDead2() {
        return dead2;
    }

    public void setDead2(BufferedImage dead2) {
        this.dead2 = dead2;
    }

    public BufferedImage getDead3() {
        return dead3;
    }

    public void setDead3(BufferedImage dead3) {
        this.dead3 = dead3;
    }

    public ArrayList<BufferedImage> getRun() {
        return run;
    }

    public void setRun(ArrayList<BufferedImage> run) {
        this.run = run;
    }

    public ArrayList<BufferedImage> getDown() {
        return down;
    }

    public void setDown(ArrayList<BufferedImage> down) {
        this.down = down;
    }

    public ArrayList<BufferedImage> getUp() {
        return up;
    }

    public void setUp(ArrayList<BufferedImage> up) {
        this.up = up;
    }

    public ArrayList<BufferedImage> getDead() {
        return dead;
    }

    public void setDead(ArrayList<BufferedImage> dead) {
        this.dead = dead;
    }

    public static String[] getSkinColor() {
        return skinColor;
    }

    public static void setSkinColor(String[] skinColor) {
        PlayerSkin.skinColor = skinColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //    Method
    public void getPlayerImage(){
        try {
            run1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_05.png"));
            run2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_06.png"));
            run3 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_07.png"));
            run4 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_08.png"));
            run5 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_09.png"));
            run6 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_10.png"));
            run = new ArrayList<>(Arrays.asList(run1, run2, run3, run4, run5, run6));

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_06.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_07.png"));
            up = new ArrayList<>(Arrays.asList(up1, up1, up1, up1, up1, up1));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_18.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_19.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_20.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_21.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_22.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_23.png"));
            down = new ArrayList<>(Arrays.asList(down1, down2, down3, down4, down5, down6));

            dead1 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_14.png"));
            dead2 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_15.png"));
            dead3 = ImageIO.read(getClass().getResourceAsStream("/player/dino_"+color+"_16.png"));
            dead = new ArrayList<>(Arrays.asList(dead1, dead1, dead2, dead2, dead3, dead3));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}