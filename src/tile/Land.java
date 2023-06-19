package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Land {
    public BufferedImage image;
    public int x, y;
    public Land(){
        getImage();
    }
    public void getImage(){
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
