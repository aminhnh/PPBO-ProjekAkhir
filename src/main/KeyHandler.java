package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// CLASS UNTUK MENGATUR INPUT (jika keyboard ditekan)

public class KeyHandler implements KeyListener {
//    Attribute
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gp;

//    Constructor
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

//    Getter Setter
    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    //    Method
    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        // Menu
        if (gp.getGameState() == gp.getTitleState() && gp.getUi().titleScreenState == gp.getUi().titleScreenMenu) {
            // Saat scrolling opsi menu
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.playSFXCursorMove(2);
                gp.getUi().menuNum--;
                if (gp.getUi().menuNum < 0) {
                    gp.getUi().menuNum = 3;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.getUi().menuNum++;
                gp.playSFXCursorMove(2);
                if (gp.getUi().menuNum > 3) {
                    gp.getUi().menuNum = 0;
                }
            }
            // Saat memilih menu (tekan enter)
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().menuNum == 0) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().titleScreenState = gp.getUi().titleScreenCharacter;
                } else if (gp.getUi().menuNum == 1) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().titleScreenState = gp.getUi().titleScreenCredits;
                } else if (gp.getUi().menuNum == 2) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().titleScreenState = gp.getUi().titleScreenSettings;
                } else if (gp.getUi().menuNum == 3) {
                    gp.playSFXCursorMove(2);
                    gp.exitGame();
                }
            }
        }else if (gp.getGameState() == gp.getTitleState() && gp.getUi().titleScreenState == gp.getUi().titleScreenCharacter) {
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                gp.getUi().titleScreenState = gp.getUi().titleScreenMenu;
                gp.setGameState(gp.getPlayState());
                gp.playMusic();
            }
            else if(code == KeyEvent.VK_W || code == KeyEvent.VK_A){
                    gp.getUi().player1Skin--;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_D){
                    gp.getUi().player1Skin--;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_LEFT) {
                    gp.getUi().player2Skin++;
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_RIGHT) {
                    gp.getUi().player2Skin--;
                    gp.playSFXCursorMove(2);

            }
            // Credits
        } else if (gp.getGameState() == gp.getTitleState() && gp.getUi().titleScreenState == gp.getUi().titleScreenCredits){
            if (code == KeyEvent.VK_ENTER){
                gp.playSFXCursorMove(2);
                gp.getUi().titleScreenState = gp.getUi().titleScreenMenu;
            }

        }

        // PLAY STATE
        else if (gp.getGameState() == gp.getPlayState()){
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
                gp.setGameState(gp.getPauseState());
                gp.getMusic().pause();
            }
        }

        // PAUSED STATE
        else if (gp.getGameState() == gp.getPauseState() || gp.getUi().titleScreenState == gp.getUi().titleScreenSettings){
            if (gp.getGameState() == gp.getPauseState()){
                if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE){
                    gp.getMusic().resume();
                    gp.setGameState(gp.getPlayState());
                }
            }
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
                gp.playSFXCursorMove(2);
                gp.getUi().menuNum--;
                if (gp.getUi().menuNum < 0){
                    gp.getUi().menuNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
                gp.playSFXCursorMove(2);
                gp.getUi().menuNum++;
                if (gp.getUi().menuNum > 1){
                    gp.getUi().menuNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER){
                if(gp.getUi().menuNum == 0){
                    gp.getUi().titleScreenState = gp.getUi().titleScreenMenu;
                } else if (gp.getUi().menuNum == 1){
                    gp.getUi().titleScreenState = gp.getUi().titleScreenMenu;
                } else if (gp.getUi().menuNum == 2) {
                    gp.getUi().titleScreenState = gp.getUi().titleScreenMenu;
                }
            }

            if (code == KeyEvent.VK_LEFT){
                if (gp.getUi().menuNum == 0 && gp.getMusic().getVolumeScale() > 0) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale()-1);
                    gp.getMusic().checkVolume();
                }
                if (gp.getUi().menuNum == 1 && gp.getSfx().getVolumeScale() > 0) {
                    gp.getSfx().setVolumeScale(gp.getSfx().getVolumeScale()-1);
                    gp.getSfxMoveCursor().setVolumeScale(gp.getSfxMoveCursor().getVolumeScale()-1);
                    gp.getSfx().checkVolume();
                }
            }
            if (code == KeyEvent.VK_RIGHT){
                if (gp.getUi().menuNum == 0 && gp.getMusic().getVolumeScale() < 5) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale()+1);
                    gp.getMusic().checkVolume();
                }
                if (gp.getUi().menuNum == 1 && gp.getSfx().getVolumeScale() < 5) {
                    gp.getSfx().setVolumeScale(gp.getSfx().getVolumeScale()+1);
                    gp.getSfxMoveCursor().setVolumeScale(gp.getSfxMoveCursor().getVolumeScale()+1);
                    gp.getSfx().checkVolume();
                }
            }
        }
        // GAMEOVER
        else if (gp.getGameState() == gp.getGameOverState()){
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
