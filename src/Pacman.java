import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class Pacman extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public int score;

    public String playerName;
    String requestDirection;

    BufferedImage pacman_img;

    public Pacman(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition(){
        x = 224;
        y = 48;
        speed = 2;
        size = 48;
        direction = "right";
        requestDirection = "right";
        score = 0;
    }

    public void getPlayerImage(){
        try {
            /*if(Objects.equals(direction, "right")){
                pacman_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_right.png")));
            }else if(Objects.equals(direction, "left")){
                pacman_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_left.png")));
            }else if(Objects.equals(direction, "up")){
                pacman_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_up.png")));
            }else if(Objects.equals(direction, "down")){
                pacman_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_down.png")));
            }*/
            pacman_right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_right.png")));
            pacman_left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_left.png")));
            pacman_up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_up.png")));
            pacman_down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_down.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore(){
        return score;
    }

    public void move(){

        switch (requestDirection){
            case "up":
                if(gamePanel.collisionManager.isOkToMove(x, y, requestDirection)){
                    direction = "up";
                }
                break;
            case "down":
                if(gamePanel.collisionManager.isOkToMove(x, y, requestDirection)){
                    direction = "down";
                }
                break;
            case "right":
                if(gamePanel.collisionManager.isOkToMove(x, y, requestDirection)){
                    direction = "right";
                }
                break;
            case "left":
                if(gamePanel.collisionManager.isOkToMove(x, y, requestDirection)){
                    direction = "left";
                }
                break;
            default:
                break;
        }

        switch(direction){
            case "up":
                if(gamePanel.collisionManager.isOkToMove(x, y, direction)){
                    y -= speed;
                }
                break;
            case "down":
                if(gamePanel.collisionManager.isOkToMove(x, y, direction)){
                    y += speed;
                }
                break;
            case "right":
                if(gamePanel.collisionManager.isOkToMove(x, y, direction)){
                    x += speed;
                }
                break;
            case "left":
                if(gamePanel.collisionManager.isOkToMove(x, y, direction)){
                    x -= speed;
                }
                break;
            default:
                break;
        }
    }
    public void update(){

        if(gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost2.x, gamePanel.ghost2.y) ||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost1.x, gamePanel.ghost1.y)||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost3.x, gamePanel.ghost3.y)||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost4.x, gamePanel.ghost4.y)){
            gamePanel.endGame();
        }

        if(keyHandler.upPressed){
            requestDirection = "up";
        }
        else if(keyHandler.downPressed){
            requestDirection = "down";
        }
        else if(keyHandler.rightPressed){
            requestDirection = "right";
        }
        else if(keyHandler.leftPressed){
            requestDirection = "left";
        }

        move();
        getPlayerImage();

        if(gamePanel.collisionManager.canCollectedCoin(x, y, direction)){
            score += 10;
            int col = x / gamePanel.tileSize;
            int row = y / gamePanel.tileSize;
            if(direction.equals("right")){
                col = (x+48) / gamePanel.tileSize;
            }else if(direction.equals("down")){
                row = (y+48) / gamePanel.tileSize;
            }
            if(gamePanel.tileManager.mapTileNumber[col][row] == 2){
                gamePanel.tileManager.mapTileNumber[col][row] = 0;
                System.out.println("score: " + score);
                gamePanel.updateScoreDisplay(this.score);
            }
        }
    }

    public void draw(Graphics2D g2){
        if(direction.equals("right")){
            g2.drawImage(pacman_right, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(direction.equals("left")){
            g2.drawImage(pacman_left, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(direction.equals("up")){
            g2.drawImage(pacman_up, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if(direction.equals("down")){
            g2.drawImage(pacman_down, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
        }

    }

    public int getScore(){
        return  score;
    }

    public int getEntityX(){
        return x;
    }

    public int getEntityY(){
        return y;
    }
}
