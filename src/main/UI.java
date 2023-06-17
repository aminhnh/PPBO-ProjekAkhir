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
    Font pixellari, minecraft, upheavtt, fontMenu, fontCredits, fontTitle;
    public int menuNum = 0;
    PlayerSkin[] playerSkins;

    // Title Screen Sub-state
    public int titleScreenState = 0;
    public final int titleScreenMenu = 0;
    public final int titleScreenCredits = 1;
    public final int titleScreenSettings = 2;
    public final int titleScreenCharacter = 3;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_16B = new Font("Arial", Font.BOLD, 16);
        setupFonts();
        playerSkins = new PlayerSkin[4];
        setUpPlayerSkins();
        fontMenu = upheavtt.deriveFont(Font.PLAIN, 32F);
        fontCredits = pixellari.deriveFont(Font.PLAIN, 20F);
        fontTitle = upheavtt.deriveFont(Font.PLAIN, 60F);
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        if (gp.gameState == gp.titleState){
            if (titleScreenState == titleScreenMenu){
                drawMenuScreen();
            }
            else if (titleScreenState == titleScreenCredits){
                drawCreditsScreen();
            } else if (titleScreenState == titleScreenSettings) {
                drawSettingsScreen();
            } else if (titleScreenState == titleScreenCharacter) {
                drawCharacterScreen();
            }
        }
        else if (gp.gameState == gp.playState){
            drawFPS();
        }
        else if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        else if (gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    public void drawMenuScreen() {
        // Background Color
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title text settings
        g2.setFont(fontTitle);
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
        g2.setFont(fontMenu);

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

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gp.tileSize / 2;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - 30, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize / 2;
        g2.drawString(text,x, y);
        if (menuNum == 3) {
            g2.drawString(">", x - 30, y);
        }
    }
    public void drawCharacterScreen(){
        // TODO: make character picker
    }
    public void drawCreditsScreen(){
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(fontMenu);

        String credit = "Made By : ";
        int creditX = getXforCenteredText(credit);
        int creditY = (int) (gp.tileSize*1.5);
        g2.drawString(credit, creditX, creditY);

        g2.setFont(fontCredits);
        String line1 = "Aminah Nurul Huda";
        int line1X = getXforCenteredText(line1);
        int line1Y = creditY + gp.tileSize;
        g2.drawString(line1, line1X, line1Y);

        String line2 = "Amanda Farliana Setyasari";
        int line2X = getXforCenteredText(line2);
        int line2Y = line1Y + gp.tileSize/2;
        g2.drawString(line2, line2X, line2Y);

        String line3 = "Salwa Jasmine A'aliyah";
        int line3X = getXforCenteredText(line3);
        int line3Y = line2Y + gp.tileSize/2;
        g2.drawString(line3, line3X, line3Y);

        String line4 = "Risma Saputri";
        int line4X = getXforCenteredText(line4);
        int line4Y = line3Y + gp.tileSize/2;
        g2.drawString(line4, line4X, line4Y);

        g2.setFont(fontMenu);
        String text = "Dosen Pengampu:";
        int x = getXforCenteredText(text);
        int y = line4Y + gp.tileSize*2;
        g2.drawString(text, x, y);

        g2.setFont(fontCredits);
        text = "Margareta Hardiyanti, S.Kom., M.Eng.";
        y += gp.tileSize;
        x = getXforCenteredText(text);
        g2.drawString(text, x, y);

        // "BACK" option
        g2.setFont(fontMenu);
        text = "BACK";
        x = getXforCenteredText(text);
        y += gp.tileSize*1.5 ;

        g2.drawString(text, x, y);
        g2.drawString(">", x - 30, y);
    }
    public void drawSettingsScreen(){
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        drawSettings();
    }
    public void drawSettings(){
        g2.setColor(Color.white);
        int x = 170;
        int y = (int)(gp.tileSize);
        int rectX;
        int rectY = (int)(gp.tileSize);

        //MUSIC + BAR
        g2.setFont(fontMenu);
        String text = "MUSIC";
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - 30, y);
        }
        rectX = gp.tileSize + gp.tileSize * 5;
        rectY += gp.tileSize * 2.5;
        g2.drawRect(rectX, rectY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(rectX, rectY, volumeWidth, 24);

        //SOUND + BAR
        text = "SOUND";
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - 30, y);
        }
        rectY += gp.tileSize;
        g2.drawRect(rectX, rectY, 120, 24);
        volumeWidth = 24 * gp.sfx.volumeScale;
        g2.fillRect(rectX, rectY, volumeWidth, 24);
    }
    public void drawPauseScreen(){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(fontTitle);
        String text = "PAUSED";
        g2.setColor(Color.white);
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/4;
        g2.drawString(text, x, y);

        drawSettings();
    }
    public void drawGameOverScreen(){
        // TODO: draw game over
        g2.setColor(Color.white);

        g2.setFont(fontCredits);
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.tileSize;
        g2.drawString(text, x, y);

        g2.setFont(fontTitle);
        text = "{Player Color} Wins!";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);


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
