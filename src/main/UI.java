package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16B;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_16B = new Font("Arial", Font.BOLD, 16);
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        // Menampilkan FPS
        g2.setFont(arial_16B);
        g2.setColor(Color.white);
        g2.drawString("FPS : "+gp.actualFPS, gp.screenWidth-80, 30);

        if (gp.gameState == gp.playState){
            // TODO : add playStateStuff (lifes)
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - textLength/2;
    }
}
