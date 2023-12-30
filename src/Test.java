import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

public class Test{
    // For sequential purposes, following class will be tested sequentially
    // HomePage -> LeaderboardPage -> TileManager -> KeyHandler -> Ghost -> Pacman -> GamePanel -> CollisionManager
    // Testing for Pacman clone will be done in this class

    GamePanel gp = new GamePanel();
    TileManager tm = new TileManager(gp);

    public Tile[] tileTest;
    int numberOfCoin = 0;
    public int[][] mapTileNumber;

    // 1) HomePage Class Testing
    // 2) LeaderboardPage Testing
    // 3) TileManager Testing
    public void loadMapTest(String myPathTest){
        try{
            InputStream ts = getClass().getResourceAsStream(myPathTest);
            assert ts != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(ts));

            int col = 0;
            int row = 0;

            while(col< gp.maxScreenCol && row < gp.maxScreenRow){
                String lineTest = br.readLine();
                while(col< gp.maxScreenCol){
                    String[] numbers = lineTest.split(" ");
                    int number = Integer.parseInt(numbers[col]);
                    if(number == 2){
                        numberOfCoin += 1;
                    }
                    mapTileNumber[col][row] = number;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImageTest(){
        try{
            tileTest[0] = new Tile();
            tileTest[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/freeway.png")));


            tileTest[1] = new Tile();
            tileTest[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/obstacle.png")));
            tileTest[1].collision = true;

            tileTest[2] = new Tile();
            tileTest[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/coin.png")));
            tileTest[2].collision = true;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawTest(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNumber[col][row];
            g2.drawImage(tileTest[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }





    //GamePanel Class Testing

    Thread testingGameThread;
    public void startGameThreadTest(){
        testingGameThread = new Thread(gp);
        testingGameThread.start();

    }




    KeyHandler kh = new KeyHandler();
    Pacman p = new Pacman(gp,kh);

    // Unit Testing approach will be applied by hand.


    public static void main(String[] args){

    }





}
