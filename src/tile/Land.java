package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Land {
//    Attribute
    private BufferedImage image;
    private int x, y;

//    Constructor
    public Land(){
        setImage();
    }

//    Getter Setter
    public void setImage(){
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public BufferedImage getImage() {
        return image;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

}
