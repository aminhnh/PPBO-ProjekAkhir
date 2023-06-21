package Entity;

import main.GamePanel;
import main.KeyHandler;

public class Player2 extends Player{
    public Player2(GamePanel gp, KeyHandler keyHandler, String color) {
        super(gp, keyHandler, color);
        floor = (getGp().getTileSize()+2)*7;
    }

    @Override
    public void setDefaultValues(){
        setX(getGp().getTileSize()*2);
        setY((getGp().getTileSize()+2)*7);
        setSpeed(5);
        setDirection("run");
    }
    @Override
    public void update(){
        //System.out.println("Y = "+y+"  v = "+velocity);
        if(getKeyHandler().isUpPressed2() || isJumping){
            setDirection("up");
        } else if (getKeyHandler().isDownPressed2()){
            setDirection("down");
        } else if (getKeyHandler().isLeftPressed2()){
            setX(super.getX()-super.getSpeed());
        } else if (getKeyHandler().isRightPressed2()) {
            setX(getX()+getSpeed());
        } else{
            setDirection("run");
        }
        setSpriteCounter(getSpriteCounter()+1);
        if (getSpriteCounter() > 5){
            setSpriteNum(getSpriteNum()+1);
            if (getSpriteNum() > 5){
                setSpriteNum(0);
            }
            setSpriteCounter(0);
            setJumpCounter(getJumpCounter()+1);
        }

        // TODO: Make Jump better
        // Kode dibawah ini mengatur dino lompat
        if (getKeyHandler().isUpPressed2() && onFloor() && !isJumping){
            // saat tekan naik & player di lantai:
            getGp().playSFX(1);
            velocity = 20;
            isJumping = true;
        }
        if (velocity > 0){
            // dino lompat
            move(getSpeed());
            velocity--;
            onAir = true;
        }
        if (velocity == 0 && onAir){
            // saat di puncak lompat
            velocity = -20;
        }
        if (velocity < 0 ){
            // dino turun
            move(-getSpeed());
            velocity++;
            onAir = false;
        }
        if (velocity == 0 && onFloor()){
            isJumping = false;
        }

    }
}
