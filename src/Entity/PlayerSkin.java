package Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerSkin {
    public BufferedImage run1, run2, run3, run4, run5, run6;
    public BufferedImage down1, down2, down3, down4, down5, down6;
    public BufferedImage up1, up2;
    public ArrayList<BufferedImage> run, down, up;
    String color;
    public PlayerSkin(String color){
        System.out.println("masuk player skin cons");
        this.color = color.toLowerCase();
        getPlayerImage();
    }
    public void getPlayerImage(){
        System.out.println("masuk get player image");
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

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}