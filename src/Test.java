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
import java.util.Random;

public class Test extends Entity{
    // For sequential purposes, following class will be tested sequentially
    // HomePage -> LeaderboardPage -> TileManager -> KeyHandler -> Ghost -> Pacman -> GamePanel -> CollisionManager
    // Testing for Pacman clone will be done in this class

    GamePanel gp = new GamePanel();
    TileManager tm = new TileManager(gp);

    public Tile[] tileTest;
    int numberOfCoin = 0;
    public int[][] mapTileNumber;
    String[] directions = {"right", "left", "down", "up"};
    String color;

    Ghost testGhost = new Ghost(gp,x,y,color);

    // 1) ---------------------------------------HomePage Class Testing---------------------------------------
    // 2) ---------------------------------------LeaderboardPage Testing---------------------------------------


    // 3) ---------------------------------------TileManager Testing---------------------------------------
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

    // 4) ---------------------------------------KeyHandler Testing---------------------------------------

    // 5) ---------------------------------------Ghost Class Testing---------------------------------------

    public void setDefaultPositionTest(int x,int y){
        this.x = x;
        this.y = y;
        speed = 1;
        Random random = new Random();
        int randomIndexNew = random.nextInt(directions.length);
        direction = directions[randomIndexNew];
    }

    public void getGhostImage(){
        try{
            if(color.equals("blue")){
                ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost.png")));

            }else if(color.equals("green")){
                ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost.png")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getTriggeredGhostImage(){
        try{
            if(color.equals("blue")){
                ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost.png")));
            }else if(color.equals("green")) {
                ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost.png")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean atCrossTest(){
        int count = 0;
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;

        if (gp.collisionManager.isOkToMove(x, y, "right")) {
            right = true;
            count++;
        }
        if (gp.collisionManager.isOkToMove(x, y, "left")) {
            left = true;
            count++;
        }
        if (gp.collisionManager.isOkToMove(x, y, "down")) {
            down = true;
            count++;
        }
        if (gp.collisionManager.isOkToMove(x, y, "up")) {
            up = true;
            count++;
        }

        if(count > 2){
            return true;
        }else if(count < 2){
            return false;
        }else{
            if(direction.equals("right")){
                return (down || up) && left;
            }else if(direction.equals("left")){
                return (down || up) && right;
            }else if(direction.equals("down")){
                return (right || left) && up;
            }else if(direction.equals("up")){
                return (right || left) && down;
            }
            return false;
        }
    }

    public void moveRandomTest(){

        getGhostImage();

        Random random = new Random();
        int randomIndex = random.nextInt(directions.length);
        String requestDirection = directions[randomIndex];

        if(!atCrossTest()){
            if(!gp.collisionManager.isOkToMove(x, y, direction)){
                randomIndex = random.nextInt(directions.length);
                direction = directions[randomIndex];
            }
            if (direction.equals("right") && gp.collisionManager.isOkToMove(x, y, direction)) {
                x += speed;
            } else if (direction.equals("left") && gp.collisionManager.isOkToMove(x, y, direction)) {
                x -= speed;
            }

            if (direction.equals("down") && gp.collisionManager.isOkToMove(x, y, direction)) {
                y += speed;
            } else if (direction.equals("up") && gp.collisionManager.isOkToMove(x, y, direction)) {
                y -= speed;
            }
        }else{
            if(!gp.collisionManager.isOkToMove(x, y, requestDirection)){
                System.out.println(requestDirection);
                randomIndex = random.nextInt(directions.length);
                requestDirection = directions[randomIndex];
            }
            direction = requestDirection;
            if(direction.equals("right") && gp.collisionManager.isOkToMove(x, y, direction)){
                x += speed;
            }else if(direction.equals("left") && gp.collisionManager.isOkToMove(x, y, direction)){
                x -= speed;
            }else if(direction.equals("down") && gp.collisionManager.isOkToMove(x, y, direction)){
                y += speed;
            }else if(direction.equals("up") && gp.collisionManager.isOkToMove(x, y, direction)){
                y -= speed;
            }
        }
    }

    public void followPlayerTest(int player_a, int player_b){

        getTriggeredGhostImage();

        if (x < player_a && gp.collisionManager.isOkToMove(x, y, "right")) {
            x += speed;
            direction = "right";
        }else if (x > player_a && gp.collisionManager.isOkToMove(x, y, "left")) {
            x -= speed;
            direction = "left";
        }

        if (y < player_b && gp.collisionManager.isOkToMove(x, y, "down")) {
            y += speed;
            direction = "down";
        }else if (y > player_b && gp.collisionManager.isOkToMove(x, y, "up")) {
            y -= speed;
            direction = "up";
        }
    }
    public void updateTest(int player_a, int player_b){

        double distance = Math.sqrt(Math.pow((x - player_a), 2) + Math.pow((y - player_b), 2));

        if(distance < 200){
            followPlayerTest(player_a, player_b);
        }else{
            moveRandomTest();
        }
    }
    public void draw(Graphics2D g2){
        g2.drawImage(ghost_img, x, y, gp.tileSize, gp.tileSize, null);
    }


    // 6) ---------------------------------------Pacman Class Testing---------------------------------------







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
