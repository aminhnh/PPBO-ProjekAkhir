package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// CLASS UNTUK MENGATUR INPUT (jika keyboard ditekan)

public class KeyHandler implements KeyListener {
//    Attribute
    private boolean upPressed1, downPressed1, leftPressed1, rightPressed1;
    private boolean upPressed2, downPressed2, leftPressed2, rightPressed2;
    private GamePanel gp;

//    Constructor
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

//    Getter Setter

    public boolean isUpPressed1() {
        return upPressed1;
    }

    public void setUpPressed1(boolean upPressed1) {
        this.upPressed1 = upPressed1;
    }

    public boolean isDownPressed1() {
        return downPressed1;
    }

    public void setDownPressed1(boolean downPressed1) {
        this.downPressed1 = downPressed1;
    }

    public boolean isLeftPressed1() {
        return leftPressed1;
    }

    public void setLeftPressed1(boolean leftPressed1) {
        this.leftPressed1 = leftPressed1;
    }

    public boolean isRightPressed1() {
        return rightPressed1;
    }

    public void setRightPressed1(boolean rightPressed1) {
        this.rightPressed1 = rightPressed1;
    }

    public boolean isUpPressed2() {
        return upPressed2;
    }

    public void setUpPressed2(boolean upPressed2) {
        this.upPressed2 = upPressed2;
    }

    public boolean isDownPressed2() {
        return downPressed2;
    }

    public void setDownPressed2(boolean downPressed2) {
        this.downPressed2 = downPressed2;
    }

    public boolean isLeftPressed2() {
        return leftPressed2;
    }

    public void setLeftPressed2(boolean leftPressed2) {
        this.leftPressed2 = leftPressed2;
    }

    public boolean isRightPressed2() {
        return rightPressed2;
    }

    public void setRightPressed2(boolean rightPressed2) {
        this.rightPressed2 = rightPressed2;
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
        if (gp.getGameState() == gp.getTitleState() && gp.getUi().getTitleScreenState() == gp.getUi().getTitleScreenMenu()) {
            // Saat scrolling opsi menu
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.playSFXCursorMove(2);
                gp.getUi().setMenuNum(gp.getUi().getMenuNum()-1);
                if (gp.getUi().getMenuNum() < 0) {
                    gp.getUi().setMenuNum(3);
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.getUi().setMenuNum(gp.getUi().getMenuNum()+1);
                gp.playSFXCursorMove(2);
                if (gp.getUi().getMenuNum() > 3) {
                    gp.getUi().setMenuNum(0);
                }
            }
            // Saat memilih menu (tekan enter)
            if (code == KeyEvent.VK_ENTER) {
                if (gp.getUi().getMenuNum() == 0) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenCharacter());
                } else if (gp.getUi().getMenuNum() == 1) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenCredits());
                } else if (gp.getUi().getMenuNum() == 2) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenSettings());
                } else if (gp.getUi().getMenuNum() == 3) {
                    gp.playSFXCursorMove(2);
                    gp.exitGame();
                }
            }
            // Set Player skin
        }else if (gp.getGameState() == gp.getTitleState() && gp.getUi().getTitleScreenState() == gp.getUi().getTitleScreenCharacter()) {
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                gp.playSFXCursorMove(2);
                if (gp.getUi().getPlayer1Skin() != gp.getUi().getPlayer2Skin()){
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());
                    gp.getPlayer1().setSkin(gp.getUi().getPlayerSkins()[gp.getUi().getPlayer1Skin()]);
                    gp.getPlayer2().setSkin(gp.getUi().getPlayerSkins()[gp.getUi().getPlayer2Skin()]);
                    gp.setGameState(gp.getPlayState());
                    gp.playMusic();
                }
            } else if (code == KeyEvent.VK_ESCAPE) {
                    gp.setGameState(gp.getTitleState());
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());

            } else if(code == KeyEvent.VK_W || code == KeyEvent.VK_A){
                    gp.getUi().setPlayer1Skin(gp.getUi().getPlayer1Skin()-1);
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_D){
                    gp.getUi().setPlayer1Skin(gp.getUi().getPlayer1Skin()-1);
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_UP || code == KeyEvent.VK_LEFT) {
                    gp.getUi().setPlayer2Skin(gp.getUi().getPlayer2Skin()+1);
                    gp.playSFXCursorMove(2);
            } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_RIGHT) {
                    gp.getUi().setPlayer2Skin(gp.getUi().getPlayer2Skin()-1);
                    gp.playSFXCursorMove(2);

            }
            // Credits
        } else if (gp.getGameState() == gp.getTitleState() && gp.getUi().getTitleScreenState() == gp.getUi().getTitleScreenCredits()){
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                gp.playSFXCursorMove(2);
                gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());
            }

        }

        // PLAY STATE
        else if (gp.getGameState() == gp.getPlayState()){
            if (code == KeyEvent.VK_UP){
                upPressed1 = true;
            } else if (code == KeyEvent.VK_W) {
                upPressed2 = true;
            } else if (code == KeyEvent.VK_DOWN){
                downPressed1 = true;
            } else if (code == KeyEvent.VK_S) {
                downPressed2 = true;
            } else if (code == KeyEvent.VK_LEFT){
                leftPressed1 = true;
            } else if (code == KeyEvent.VK_A) {
                leftPressed2 = true;
            } else if (code == KeyEvent.VK_RIGHT){
                rightPressed1 = true;
            } else if (code == KeyEvent.VK_D) {
                rightPressed2 = true;
            } else if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                gp.setGameState(gp.getPauseState());
                gp.getMusic().pause();
            }
        }

        // PAUSED STATE
        else if (gp.getGameState() == gp.getPauseState() || gp.getUi().getTitleScreenState() == gp.getUi().getTitleScreenSettings()){
            if (gp.getGameState() == gp.getPauseState()){
                if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE){
                    gp.getMusic().resume();
                    gp.setGameState(gp.getPlayState());
                }
            }
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
                gp.playSFXCursorMove(2);
                gp.getUi().setMenuNum(gp.getUi().getMenuNum()-1);
                if (gp.getUi().getMenuNum() < 0){
                    gp.getUi().setMenuNum(1);
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
                gp.playSFXCursorMove(2);
                gp.getUi().setMenuNum(gp.getUi().getMenuNum()+1);
                if (gp.getUi().getMenuNum() > 1){
                    gp.getUi().setMenuNum(0);
                }
            }
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_SPACE){
                if(gp.getUi().getMenuNum() == 0){
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());
                } else if (gp.getUi().getMenuNum() == 1){
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());
                } else if (gp.getUi().getMenuNum() == 2) {
                    gp.playSFXCursorMove(2);
                    gp.getUi().setTitleScreenState(gp.getUi().getTitleScreenMenu());
                }
            }

            if (code == KeyEvent.VK_LEFT){
                if (gp.getUi().getMenuNum() == 0 && gp.getMusic().getVolumeScale() > 0) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale()-1);
                    gp.getMusic().checkVolume();
                }
                if (gp.getUi().getMenuNum() == 1 && gp.getSfx().getVolumeScale() > 0) {
                    gp.getSfx().setVolumeScale(gp.getSfx().getVolumeScale()-1);
                    gp.getSfxMoveCursor().setVolumeScale(gp.getSfxMoveCursor().getVolumeScale()-1);
                    gp.getSfx().checkVolume();
                }
            }
            if (code == KeyEvent.VK_RIGHT){
                if (gp.getUi().getMenuNum() == 0 && gp.getMusic().getVolumeScale() < 5) {
                    gp.getMusic().setVolumeScale(gp.getMusic().getVolumeScale()+1);
                    gp.getMusic().checkVolume();
                }
                if (gp.getUi().getMenuNum() == 1 && gp.getSfx().getVolumeScale() < 5) {
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
            upPressed1 = false;
        }
        if (code == KeyEvent.VK_DOWN){
            downPressed1 = false;
        }
        if (code == KeyEvent.VK_LEFT){
            leftPressed1 = false;
        }
        if (code == KeyEvent.VK_RIGHT){
            rightPressed1 = false;
        }
        if (code == KeyEvent.VK_W){
            upPressed2 = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed2 = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed2 = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed2 = false;
        }
    }
}
