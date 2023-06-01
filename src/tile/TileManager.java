package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNumber;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[5];
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map.txt");
    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sky.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream io = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(io));

            // Memindahkan data angka dari txt ke mapTileNumber (array 2 dimensi)
            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine(); //read a line of text
                while (col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
            }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNumber[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;;
            }
        }
    }
}
