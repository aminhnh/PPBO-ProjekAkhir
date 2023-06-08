package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16B;
    Font pixellari, minecraft, upheavtt;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_16B = new Font("Arial", Font.BOLD, 16);

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Pixellari.ttf");
            pixellari = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
            minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Upheavtt.ttf");
            minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        // Menampilkan FPS
        g2.setFont(minecraft);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.setColor(Color.white);
        g2.drawString("FPS : "+gp.actualFPS, gp.screenWidth-120, 30);

        if (gp.gameState == gp.playState){
            // TODO : add playStateStuff (lifes)
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        g2.setFont(minecraft);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - textLength/2;
    }
}
