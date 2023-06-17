package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// CLASS UNTUK MENGATUR INPUT (jika keyboard ditekan)

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        // Menu
        if (gp.gameState == gp.titleState && gp.ui.titleScreenState == gp.ui.titleScreenMenu) {
            // Saat scrolling opsi menu
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.playSFXCursorMove(2);
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0) {
                    gp.ui.menuNum = 3;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.menuNum++;
                gp.playSFXCursorMove(2);
                if (gp.ui.menuNum > 3) {
                    gp.ui.menuNum = 0;
                }
            }
            // Saat memilih menu (tekan enter)
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.menuNum == 0) {
                    gp.playSFXCursorMove(2);
                    gp.ui.titleScreenState = gp.ui.titleScreenCharacter;
                } else if (gp.ui.menuNum == 1) {
                    gp.playSFXCursorMove(2);
                    gp.ui.titleScreenState = gp.ui.titleScreenCredits;
                } else if (gp.ui.menuNum == 2) {
                    gp.playSFXCursorMove(2);
                    gp.ui.titleScreenState = gp.ui.titleScreenSettings;
                } else if (gp.ui.menuNum == 3) {
                    gp.playSFXCursorMove(2);
                    gp.exitGame();
                }
            }
        }else if (gp.gameState == gp.titleState && gp.ui.titleScreenState == gp.ui.titleScreenCharacter) {
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                gp.ui.titleScreenState = gp.ui.titleScreenMenu;
                gp.gameState = gp.playState;
                gp.playMusic();
            }
            else if(code == KeyEvent.VK_W || code == KeyEvent.VK_A){
                    gp.ui.player1Skin--;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_D){
                    gp.ui.player1Skin--;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_LEFT) {
                    gp.ui.player2Skin++;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_RIGHT) {
                    gp.ui.player2Skin--;
                    gp.playSFXCursorMove(2);

            }
            // Credits
        } else if (gp.gameState == gp.titleState && gp.ui.titleScreenState == gp.ui.titleScreenCredits){
            if (code == KeyEvent.VK_ENTER){
                gp.playSFXCursorMove(2);
                gp.ui.titleScreenState = gp.ui.titleScreenMenu;
            }

        }

        // PLAY STATE
        else if (gp.gameState == gp.playState){
            if (code == KeyEvent.VK_UP){
                upPressed = true;
            }
            if (code == KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if (code == KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                gp.gameState = gp.pauseState;
                gp.music.pause();
            }
        }

        // PAUSED STATE
        else if (gp.gameState == gp.pauseState || gp.ui.titleScreenState == gp.ui.titleScreenSettings){
            if (gp.gameState == gp.pauseState){
                if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE){
                    gp.music.resume();
                    gp.gameState = gp.playState;
                }
            }
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
                gp.playSFXCursorMove(2);
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0){
                    gp.ui.menuNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
                gp.playSFXCursorMove(2);
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 1){
                    gp.ui.menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.menuNum == 0){
                    gp.ui.titleScreenState = gp.ui.titleScreenMenu;
                } else if (gp.ui.menuNum == 1){
                    gp.ui.titleScreenState = gp.ui.titleScreenMenu;
                } else if (gp.ui.menuNum == 2) {
                    gp.ui.titleScreenState = gp.ui.titleScreenMenu;
                }
            }

            if (code == KeyEvent.VK_LEFT){
                if (gp.ui.menuNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                }
                if (gp.ui.menuNum == 1 && gp.sfx.volumeScale > 0) {
                    gp.sfx.volumeScale--;
                    gp.sfxMoveCursor.volumeScale--;
                    gp.sfx.checkVolume();
                }
            }
            if (code == KeyEvent.VK_RIGHT){
                if (gp.ui.menuNum == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                }
                if (gp.ui.menuNum == 1 && gp.sfx.volumeScale < 5) {
                    gp.sfx.volumeScale++;
                    gp.sfxMoveCursor.volumeScale++;
                    gp.sfx.checkVolume();
                }
            }
        }
        // GAMEOVER
        else if (gp.gameState == gp.gameOverState){
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                gp.resetGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
