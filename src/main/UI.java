package main;

import Entity.Player;
import Entity.PlayerSkin;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UI {
//    Attribute
    private GamePanel gp;
    private UIAssets uiAssets;
    private Graphics2D g2;
    private Font arial_16B;
    private Font pixellari, minecraft, upheavtt, fontMenu, fontCredits, fontTitle;
    private int menuNum = 0;
    private int player1Skin, player2Skin = 0;
    private PlayerSkin[] playerSkins;

    // Title Screen Sub-state
    private int titleScreenState = 0;
    private final int titleScreenMenu = 0;
    private final int titleScreenCredits = 1;
    private final int titleScreenSettings = 2;
    private final int titleScreenCharacter = 3;

//    Constructor
    public UI(GamePanel gp) {
        this.gp = gp;
        this.uiAssets = new UIAssets();
        uiAssets.getUIImage();
        arial_16B = new Font("Arial", Font.BOLD, 16);
        setupFonts();
        playerSkins = new PlayerSkin[4];
        setUpPlayerSkins();
        fontMenu = upheavtt.deriveFont(Font.PLAIN, 32F);
        fontCredits = pixellari.deriveFont(Font.PLAIN, 20F);
        fontTitle = upheavtt.deriveFont(Font.PLAIN, 60F);
    }

//    Getter Setter
    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public UIAssets getUiAssets() {
        return uiAssets;
    }

    public void setUiAssets(UIAssets uiAssets) {
        this.uiAssets = uiAssets;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public Font getArial_16B() {
        return arial_16B;
    }

    public void setArial_16B(Font arial_16B) {
        this.arial_16B = arial_16B;
    }

    public Font getPixellari() {
        return pixellari;
    }

    public void setPixellari(Font pixellari) {
        this.pixellari = pixellari;
    }

    public Font getMinecraft() {
        return minecraft;
    }

    public void setMinecraft(Font minecraft) {
        this.minecraft = minecraft;
    }

    public Font getUpheavtt() {
        return upheavtt;
    }

    public void setUpheavtt(Font upheavtt) {
        this.upheavtt = upheavtt;
    }

    public Font getFontMenu() {
        return fontMenu;
    }

    public void setFontMenu(Font fontMenu) {
        this.fontMenu = fontMenu;
    }

    public Font getFontCredits() {
        return fontCredits;
    }

    public void setFontCredits(Font fontCredits) {
        this.fontCredits = fontCredits;
    }

    public Font getFontTitle() {
        return fontTitle;
    }

    public void setFontTitle(Font fontTitle) {
        this.fontTitle = fontTitle;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(int menuNum) {
        this.menuNum = menuNum;
    }

    public int getPlayer1Skin() {
        return player1Skin;
    }

    public void setPlayer1Skin(int player1Skin) {
        this.player1Skin = player1Skin;
    }

    public int getPlayer2Skin() {
        return player2Skin;
    }

    public void setPlayer2Skin(int player2Skin) {
        this.player2Skin = player2Skin;
    }

    public PlayerSkin[] getPlayerSkins() {
        return playerSkins;
    }

    public void setPlayerSkins(PlayerSkin[] playerSkins) {
        this.playerSkins = playerSkins;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }

    public void setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
    }

    public int getTitleScreenMenu() {
        return titleScreenMenu;
    }

    public int getTitleScreenCredits() {
        return titleScreenCredits;
    }

    public int getTitleScreenSettings() {
        return titleScreenSettings;
    }

    public int getTitleScreenCharacter() {
        return titleScreenCharacter;
    }


//    Method
    public void draw(Graphics2D g2){
        this.g2 = g2;
        if (gp.getGameState() == gp.getTitleState()){
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
        else if (gp.getGameState() == gp.getPlayState()){
            //drawFPS();
            drawScore();
        }
        else if (gp.getGameState() == gp.getPauseState()){
            drawPauseScreen();
        }
        else if (gp.getGameState() == gp.getGameOverState()){
            drawGameOverScreen();
        }
    }
    public void drawMenuScreen() {
        // Background Color
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Title text settings
        g2.setFont(fontTitle);
        String text = "DINO PARTY";
        int x = getXforCenteredText(text);
        int y = (int)(gp.getTileSize() * 2.5);

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);

        // Draw title text
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Player images
        int playerSize = gp.getTileSize()*2;
        y += gp.getTileSize();
        x = (gp.getScreenWidth() - (playerSize*4))/2;
        g2.drawImage(playerSkins[0].getUp1(), x, y, playerSize,playerSize, null);
        x += gp.getTileSize()*2;
        g2.drawImage(playerSkins[1].getUp1(), x, y, playerSize,playerSize, null);
        x += gp.getTileSize()*4;
        g2.drawImage(playerSkins[2].getUp1(), x, y, -playerSize,playerSize, null);
        x += gp.getTileSize()*2;
        g2.drawImage(playerSkins[3].getUp1(), x, y, -playerSize,playerSize, null);

        // Menu
        g2.setFont(fontMenu);

        text = "PLAY";
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 3;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - 30, y);
        }

        text = "CREDITS";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - 30, y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawString(">", x - 30, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.getTileSize() / 2;
        g2.drawString(text,x, y);
        if (menuNum == 3) {
            g2.drawString(">", x - 30, y);
        }
    }
    public void drawCharacterScreen(){
        // Logic
        player1Skin = (player1Skin > 3) ? 0 : (player1Skin < 0) ? 3 : player1Skin;
        player2Skin = (player2Skin > 3) ? 0 : (player2Skin < 0) ? 3 : player2Skin;


        // Background Color
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Title
        g2.setFont(fontTitle);
        String text = "Choose Your";
        int x = getXforCenteredText(text);
        int y = (int) (gp.getTileSize()*1.5);

        g2.setColor(Color.black);
        g2.drawString(text, x + 2, y + 2);
        g2.drawString("Player", getXforCenteredText("Player")+2, y+gp.getTileSize()+2);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        g2.drawString("Player", getXforCenteredText("Player"), y+gp.getTileSize());

        // Player Skin
        int playerSize = gp.getTileSize()*4;
        x = (int)(gp.getScreenWidth()/3.5) - playerSize/2;
        y += gp.getTileSize()*1.5;
        g2.drawImage(playerSkins[player1Skin].getRun3(), x, y, playerSize,playerSize, null);

        x = (int)(gp.getScreenWidth()/3) + playerSize/2;
        g2.drawImage(playerSkins[player2Skin].getRun3(), x, y, playerSize,playerSize, null);

        // Options
        g2.setFont(fontMenu);
        text = PlayerSkin.getSkinColor()[player1Skin];
        x = getXforCenteredText(text);
        y += gp.getTileSize() * 5;
        g2.drawString(text, getQuarterX(text, false), y);
        text = ">             <";
        g2.drawString(text, getQuarterX(text, false), y);

        text = PlayerSkin.getSkinColor()[player2Skin];
        g2.drawString(text, getQuarterX(text, true), y);
        text = ">             <";
        g2.drawString(text, getQuarterX(text, true), y);

        if(player1Skin == player2Skin){
            drawSkinError();
        }
    }
    public void drawSkinError(){
        g2.setFont(fontCredits);
        String text = "Player skin cannot be the same!";
        g2.setColor(Color.white);
        int x = getXforCenteredText(text);
        int y = gp.getTileSize()*9;
        g2.drawString(text, x, y);
    }
    public int getQuarterX(String text, boolean right){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        if (right){
            return (gp.getScreenWidth()/4)*3 - textLength/2 - gp.getTileSize()/2;
        } else {
            return gp.getScreenWidth()/4 - textLength/2 + gp.getTileSize()/2;
        }
    }

    public void drawCreditsScreen(){
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setColor(Color.white);
        g2.setFont(fontMenu);

        String credit = "Made By : ";
        int creditX = getXforCenteredText(credit);
        int creditY = (int) (gp.getTileSize()*1.5);
        g2.drawString(credit, creditX, creditY);

        g2.setFont(fontCredits);
        String line1 = "Aminah Nurul Huda";
        int line1X = getXforCenteredText(line1);
        int line1Y = creditY + gp.getTileSize();
        g2.drawString(line1, line1X, line1Y);

        String line2 = "Amanda Farliana Setyasari";
        int line2X = getXforCenteredText(line2);
        int line2Y = line1Y + gp.getTileSize()/2;
        g2.drawString(line2, line2X, line2Y);

        String line3 = "Salwa Jasmine A'aliyah";
        int line3X = getXforCenteredText(line3);
        int line3Y = line2Y + gp.getTileSize()/2;
        g2.drawString(line3, line3X, line3Y);

        String line4 = "Risma Saputri";
        int line4X = getXforCenteredText(line4);
        int line4Y = line3Y + gp.getTileSize()/2;
        g2.drawString(line4, line4X, line4Y);

        g2.setFont(fontMenu);
        String text = "Dosen Pengampu:";
        int x = getXforCenteredText(text);
        int y = line4Y + gp.getTileSize()*2;
        g2.drawString(text, x, y);

        g2.setFont(fontCredits);
        text = "Margareta Hardiyanti, S.Kom., M.Eng.";
        y += gp.getTileSize();
        x = getXforCenteredText(text);
        g2.drawString(text, x, y);

        // "BACK" option
        g2.setFont(fontMenu);
        text = "BACK";
        x = getXforCenteredText(text);
        y += gp.getTileSize()*1.5 ;

        g2.drawString(text, x, y);
        g2.drawString(">", x - 30, y);
    }
    public void drawSettingsScreen(){
        g2.setColor(new Color(80, 187, 255));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        // Draw "SETTINGS" text
        g2.setFont(fontTitle);
        g2.setColor(Color.white);
        String text = "SETTINGS";
        int x = getXforCenteredText(text);
        int y = (int)(gp.getTileSize() * 2.5);
        g2.drawString(text, x, y);

        drawSettings();
    }
    public void drawSettings(){
        g2.setColor(Color.white);
        int x = 170;
        int y = (int)(gp.getTileSize());
        int rectX;
        int rectY = (int)(gp.getTileSize());

        //MUSIC + BAR
        g2.setFont(fontMenu);
        String text = "MUSIC";
        y += gp.getTileSize() * 3;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x - 30, y);
        }
        rectX = gp.getTileSize() + gp.getTileSize() * 5;
        rectY += gp.getTileSize() * 2.5;
        g2.drawRect(rectX, rectY, 120, 24);
        int volumeWidth = 24 * gp.getMusic().getVolumeScale();
        g2.fillRect(rectX, rectY, volumeWidth, 24);

        //SOUND + BAR
        text = "SOUND";
        y += gp.getTileSize();
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x - 30, y);
        }
        rectY += gp.getTileSize();
        g2.drawRect(rectX, rectY, 120, 24);
        volumeWidth = 24 * gp.getSfx().getVolumeScale();
        g2.fillRect(rectX, rectY, volumeWidth, 24);
    }
    public void drawPauseScreen(){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.getScreenWidth(), gp.getScreenHeight());

        g2.setFont(fontTitle);
        String text = "PAUSED";
        g2.setColor(Color.white);
        int x = getXforCenteredText(text);
        int y = gp.getScreenHeight()/4;
        g2.drawString(text, x, y);

        drawSettings();
    }
    public void drawGameOverScreen(){
        g2.setColor(Color.white);

        g2.setFont(fontCredits);
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.getTileSize();
        g2.drawString(text, x, y);

        g2.setFont(fontTitle);
        text = gp.getWinner() + " Wins!";
        x = getXforCenteredText(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        g2.setFont(fontMenu);
        text = "SCORE : "+gp.getScore();
        x = getXforCenteredText(text);
        y += gp.getTileSize();
        g2.drawString(text, x, y);

        if (gp.setHighScore(gp.getScore())){
            text = "NEW HIGHSCORE!!";
            x = getXforCenteredText(text);
            y += gp.getTileSize()*3;
            g2.drawString(text, x, y);
        }

        text = "Highscore: "+gp.getHighScore();
        x = getXforCenteredText(text);
        y += gp.getTileSize()*2.5;
        g2.drawString(text, x, y);
    }
    public void drawFPS(){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
            g2.setColor(Color.white);
            g2.drawString("FPS : "+gp.getActualFPS(), gp.getScreenWidth()-120, 30);
    }
    public void drawScore(){
        g2.setFont(fontMenu);
        g2.setColor(Color.white);
        g2.drawString(String.format("%05d", gp.getScore()), gp.getScreenWidth()- (int) (gp.getTileSize()*2.5), (int)(gp.getTileSize()*0.7));
    }

    public int getXforCenteredText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.getScreenWidth()/2 - textLength/2;
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
        playerSkins[1] = new PlayerSkin("green");
        playerSkins[2] = new PlayerSkin("red");
        playerSkins[3] = new PlayerSkin("yellow");
    }
}
