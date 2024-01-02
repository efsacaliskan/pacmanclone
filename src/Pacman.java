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
    private int speed;

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
        speed = 4;
        size = 48;
        direction = "right";
        requestDirection = "right";
        score = 0;
    }

    public void getPlayerImage(){
        try {
            if(Objects.equals(direction, "right")){
                pacman_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_right.png")));
            }else if(Objects.equals(direction, "left")){
                pacman_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_left.png")));
            }else if(Objects.equals(direction, "up")){
                pacman_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_up.png")));
            }else if(Objects.equals(direction, "down")){
                pacman_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman_down.png")));
            }
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

        // move from most left to most right or from most right to most left
        if(x < 0){
            x = 1488;
        }else if(x > 1488){
            x = 0;
        }

        // It changes the request direction if the requested place is available.
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

        // pacman moves according to its direction if next place is available.
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

        // if ghost is eatable then pacman can eat the ghost that pacman collided one
        if(gamePanel.ghost1.is_eatable && gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost1.x, gamePanel.ghost1.y)){
            gamePanel.ghost1.x = 720;
            gamePanel.ghost1.y = 336;
            gamePanel.ghost1.is_eatable = false;
            gamePanel.tileManager.startDoorTimer();
        }else if(gamePanel.ghost2.is_eatable && gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost2.x, gamePanel.ghost2.y)){
            gamePanel.ghost2.x = 720;
            gamePanel.ghost2.y = 336;
            gamePanel.ghost2.is_eatable = false;
            gamePanel.tileManager.startDoorTimer();
        }else if(gamePanel.ghost3.is_eatable && gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost3.x, gamePanel.ghost3.y)){
            gamePanel.ghost3.x = 720;
            gamePanel.ghost3.y = 336;
            gamePanel.ghost3.is_eatable = false;
            gamePanel.tileManager.startDoorTimer();
        }else if(gamePanel.ghost4.is_eatable && gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost4.x, gamePanel.ghost4.y)){
            gamePanel.ghost4.x = 720;
            gamePanel.ghost4.y = 336;
            gamePanel.ghost4.is_eatable = false;
            gamePanel.tileManager.startDoorTimer();
        }else if(gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost2.x, gamePanel.ghost2.y) ||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost1.x, gamePanel.ghost1.y)||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost3.x, gamePanel.ghost3.y)||
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost4.x, gamePanel.ghost4.y)){
            gamePanel.endGame();
        }

        // requested direction changes immediately after user presses a key
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

        // It calls move function
        move();

        // It calls this function to get updated appearance of pacman.
        getPlayerImage();

        // it checks coin collision with pacman
        if(gamePanel.collisionManager.canCollectedCoin(x, y, direction)){
            int col = x / gamePanel.tileSize;
            int row = y / gamePanel.tileSize;
            if(direction.equals("right")){
                col = (x+48) / gamePanel.tileSize;
            }else if(direction.equals("down")){
                row = (y+48) / gamePanel.tileSize;
            }
            if(gamePanel.tileManager.mapTileNumber[col][row] == 2){
                score += 10;
                gamePanel.tileManager.mapTileNumber[col][row] = 0;
                gamePanel.updateScoreDisplay(this.score);
            }
            // It checks for the big coin.
            if(gamePanel.tileManager.mapTileNumber[col][row] == 3){
                score += 100;
                gamePanel.tileManager.mapTileNumber[col][row] = 0;
                gamePanel.updateScoreDisplay(this.score);
                gamePanel.ghost1.startEatableTimer();
                gamePanel.ghost2.startEatableTimer();
                gamePanel.ghost3.startEatableTimer();
                gamePanel.ghost4.startEatableTimer();
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(pacman_img, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
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
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;

    }
}
