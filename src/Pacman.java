import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Pacman extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public int score;

    String requestDirection;

    public Pacman(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition(){
        x = 240;
        y = 48;
        speed = 2;
        size = 48;
        direction = "right";
        requestDirection = "right";
        score = 0;
    }

    public void getPlayerImage(){
        try {
            pacman_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pacman.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
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
                gamePanel.collisionManager.collisionWithGhost(x, y, gamePanel.ghost1.x, gamePanel.ghost1.y)){
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
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        g2.drawImage(pacman_img, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
