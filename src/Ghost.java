
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Ghost extends Entity {
    GamePanel gamePanel;
    String[] directions = {"right", "left", "down", "up"};
    String color;

    Boolean is_eatable = false;
    private Timer eatableTimer;
    public Ghost(GamePanel gamePanel, String color){
        this.gamePanel = gamePanel;
        this.color = color;
        setDefaultPosition(x, y);
        getGhostImage();
        is_eatable = false;
        eatableTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                is_eatable = false;
            }
        });
    }

    public void startEatableTimer() {
        is_eatable = true;
        eatableTimer.restart();
    }

    public void setDefaultPosition(int x, int y){
        this.x = 720;
        this.y = 336;
        speed = 2;
        Random random = new Random();
        int randomIndex = random.nextInt(directions.length);
        direction = directions[randomIndex];
    }

    public void getGhostImage() {
        try{
            if(!is_eatable && color.equals("blue")){
                if(direction.equals("right")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost_right.png")));
                }else if(direction.equals("left")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost_left.png")));
                }else if(direction.equals("down")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost_down.png")));
                }else if(direction.equals("up")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue_ghost_up.png")));
                }
            }else if(!is_eatable && color.equals("green")) {
                if(direction.equals("right")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost_right.png")));
                }else if(direction.equals("left")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost_left.png")));
                }else if(direction.equals("down")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost_down.png")));
                }else if(direction.equals("up")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/green_ghost_up.png")));
                }

            }else if(!is_eatable && color.equals("yellow")) {
                if(direction.equals("right")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow_ghost_right.png")));
                }else if(direction.equals("left")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow_ghost_left.png")));
                }else if(direction.equals("down")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow_ghost_down.png")));
                }else if(direction.equals("up")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow_ghost_up.png")));
                }
            }else if(!is_eatable && color.equals("brown")) {
                if(direction.equals("right")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/brown_ghost_right.png")));
                }else if(direction.equals("left")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/brown_ghost_left.png")));
                }else if(direction.equals("down")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/brown_ghost_down.png")));
                }else if(direction.equals("up")){
                    ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/brown_ghost_up.png")));
                }
            }else if(is_eatable){
                ghost_img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/freak_ghost.png")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public boolean atCross(){

        int count = 0;
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;

        if(gamePanel.collisionManager.isOkToMove(x, y, "left") && (y == 336)){
            return false;
        }else if(gamePanel.collisionManager.isOkToMove(x, y, "right") && (y == 336)){
            return false;
        }

        if (gamePanel.collisionManager.isOkToMove(x, y, "right")) {
            right = true;
            count++;
        }
        if (gamePanel.collisionManager.isOkToMove(x, y, "left")) {
            left = true;
            count++;
        }
        if (gamePanel.collisionManager.isOkToMove(x, y, "down")) {
            down = true;
            count++;
        }
        if (gamePanel.collisionManager.isOkToMove(x, y, "up")) {
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

    public void moveRandom(){

        getGhostImage();

        Random random = new Random();
        int randomIndex = random.nextInt(directions.length);
        String requestDirection = directions[randomIndex];

        if(!atCross()){
            if(!gamePanel.collisionManager.isOkToMove(x, y, direction)){
                randomIndex = random.nextInt(directions.length);
                direction = directions[randomIndex];
            }
            if (direction.equals("right") && gamePanel.collisionManager.isOkToMove(x, y, direction)) {
                x += speed;
            } else if (direction.equals("left") && gamePanel.collisionManager.isOkToMove(x, y, direction)) {
                x -= speed;
            }

            if (direction.equals("down") && gamePanel.collisionManager.isOkToMove(x, y, direction)) {
                y += speed;
            } else if (direction.equals("up") && gamePanel.collisionManager.isOkToMove(x, y, direction)) {
                y -= speed;
            }
        }else{
            if(!gamePanel.collisionManager.isOkToMove(x, y, requestDirection)){
                System.out.println(requestDirection);
                randomIndex = random.nextInt(directions.length);
                requestDirection = directions[randomIndex];
            }
            direction = requestDirection;
            if(direction.equals("right") && gamePanel.collisionManager.isOkToMove(x, y, direction)){
                x += speed;
            }else if(direction.equals("left") && gamePanel.collisionManager.isOkToMove(x, y, direction)){
                x -= speed;
            }else if(direction.equals("down") && gamePanel.collisionManager.isOkToMove(x, y, direction)){
                y += speed;
            }else if(direction.equals("up") && gamePanel.collisionManager.isOkToMove(x, y, direction)){
                y -= speed;
            }
        }
    }

    public void followPlayer(int player_x, int player_y){

        if (((y == player_y) || (x == player_x)) && x < player_x && gamePanel.collisionManager.isOkToMove(x, y, "right")) {
            x += speed;
            direction = "right";
        }else if (x > player_x && gamePanel.collisionManager.isOkToMove(x, y, "left")) {
            x -= speed;
            direction = "left";
        }

        if (((y == player_y) || (x == player_x)) && y < player_y && gamePanel.collisionManager.isOkToMove(x, y, "down")) {
            y += speed;
            direction = "down";
        }else if (y > player_y && gamePanel.collisionManager.isOkToMove(x, y, "up")) {
            y -= speed;
            direction = "up";
        }
    }

    public void update(int player_x, int player_y){

        moveRandom();

        /*double distance = Math.sqrt(Math.pow((x - player_x), 2) + Math.pow((y - player_y), 2));

        if(distance < 120){
            followPlayer(player_x, player_y);
        }else{
            moveRandom();
        }*/
    }
    public void draw(Graphics2D g2){
        g2.drawImage(ghost_img, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
