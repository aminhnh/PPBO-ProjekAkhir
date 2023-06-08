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
        if (gp.gameState == gp.titleState){
            // Saat scrolling opsi menu
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0){
                    gp.ui.menuNum = 2;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 2){
                    gp.ui.menuNum = 0;
                }
            }
            // Saat memilih menu (tekan enter)
            if (code == KeyEvent.VK_ENTER){
                if(gp.ui.menuNum == 0){
                    gp.gameState = gp.playState;
                    gp.playMusic();
                } else if (gp.ui.menuNum == 1){
                    // TODO: add credits gamestate & ui
                } else if (gp.ui.menuNum == 2){
                    System.exit(0);
                }
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
            if (code == KeyEvent.VK_ESCAPE){
                System.out.println("pressed escape");
                gp.gameState = gp.pauseState;
                gp.music.pause();
            }
        }

        // PAUSED STATE
        else if (gp.gameState == gp.pauseState){
            if (code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
                gp.music.resume();
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
