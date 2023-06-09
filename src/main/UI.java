package main;

import Entity.Player;
import Entity.PlayerSkin;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_16B;
    Font pixellari, minecraft, upheavtt;
    public int menuNum = 0;
    PlayerSkin[] playerSkins;

    // Title Screen Sub-state
    public int titleScreenState = 0;
    public final int titleScreenMenu = 0;
    public final int titleScreenCredits = 1;
    public final int titleScreenCharacter = 2;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_16B = new Font("Arial", Font.BOLD, 16);
        setupFonts();
        playerSkins = new PlayerSkin[4];
        setUpPlayerSkins();
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;


        g2.setFont(upheavtt);
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState){
            drawFPS();
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    public void drawTitleScreen() {
        if (titleScreenState == titleScreenMenu) {
            // Background Color
            g2.setColor(new Color(80, 187, 255));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Title text settings
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
            String text = "DINO PARTY";
            int x = getXforCenteredText(text);
            int y = (int)(gp.tileSize * 2.5);

            // Shadow
            g2.setColor(Color.black);
            g2.drawString(text, x + 2, y + 2);

            // Draw title text
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Player images
            int playerSize = gp.tileSize*2;
            y += gp.tileSize;
            x = (gp.screenWidth - (playerSize*4))/2;
            g2.drawImage(playerSkins[0].up1, x, y, playerSize,playerSize, null);
            x += gp.tileSize*2;
            g2.drawImage(playerSkins[1].up1, x, y, playerSize,playerSize, null);
            x += gp.tileSize*4;
            g2.drawImage(playerSkins[2].up1, x, y, -playerSize,playerSize, null);
            x += gp.tileSize*2;
            g2.drawImage(playerSkins[3].up1, x, y, -playerSize,playerSize, null);

            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

            text = "PLAY";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (menuNum == 0) {
                g2.drawString(">", x - 30, y);
            }

            text = "CREDITS";
            x = getXforCenteredText(text);
            y += gp.tileSize / 2;
            g2.drawString(text, x, y);
            if (menuNum == 1) {
                g2.drawString(">", x - 30, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize / 2;
            g2.drawString(text, x, y);
            if (menuNum == 2) {
                g2.drawString(">", x - 30, y);
            }

        }
        else if (titleScreenState == titleScreenCredits){
            // TODO: Create credits screen
        }
        else if (titleScreenState == titleScreenCharacter){
            // TODO: Create character picker screen
        }
    }
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String text = "PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/4;

        g2.drawString(text, x, y);
        // TODO: Add options?
    }
    public void drawFPS(){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            g2.setColor(Color.white);
            g2.drawString("FPS : "+gp.actualFPS, gp.screenWidth-120, 30);
    }
    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - textLength/2;
    }
    public void setupFonts(){
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Pixellari.ttf");
            pixellari = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Minecraft.ttf");
            minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Upheavtt.ttf");
            upheavtt = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setUpPlayerSkins(){
        playerSkins[0] = new PlayerSkin("blue");
        playerSkins[1] = new PlayerSkin("red");
        playerSkins[2] = new PlayerSkin("yellow");
        playerSkins[3] = new PlayerSkin("green");
    }
}
