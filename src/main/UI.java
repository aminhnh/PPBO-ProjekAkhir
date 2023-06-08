package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_16;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_16 = new Font("Arial", Font.BOLD, 16);
    }
    public void draw(Graphics2D g2){

        g2.setFont(arial_16);
        g2.setColor(Color.white);
        g2.drawString("FPS : "+gp.actualFPS, gp.screenWidth-80, 30);
    }
}
