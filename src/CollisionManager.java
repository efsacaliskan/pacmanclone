
import java.util.Objects;

public class CollisionManager {
    GamePanel gamePanel;
    public CollisionManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public int whatIsThere(int x, int y){
        int col = x / gamePanel.tileSize;
        int row = y / gamePanel.tileSize;

        return gamePanel.tileManager.mapTileNumber[col][row];
    }
    public boolean isOkToMove(int x, int y, String direction){

        if (Objects.equals(direction, "right")) {
            if((x+gamePanel.tileSize) % gamePanel.tileSize == 0 &&
                    (whatIsThere(x+gamePanel.tileSize+1, y) == 1 ||
                            whatIsThere(x+gamePanel.tileSize+1, y+gamePanel.tileSize-1) == 1 ||
                    whatIsThere(x+gamePanel.tileSize+1, y) == 4 ||
                    whatIsThere(x+gamePanel.tileSize+1, y+gamePanel.tileSize-1) == 4)){
                return false;
            }
        }

        if (Objects.equals(direction, "left")) {
            if(x % gamePanel.tileSize == 0 &&
                    (whatIsThere(x-1, y) == 1 ||
                    whatIsThere(x-1, y+gamePanel.tileSize-1) == 1 ||
                    whatIsThere(x-1, y) == 4 ||
                    whatIsThere(x-1, y+gamePanel.tileSize-1) == 4)){
                return false;
            }
        }

        if (Objects.equals(direction, "up")) {
            if(y % gamePanel.tileSize == 0 &&
                    (whatIsThere(x, y-1) == 1 ||
                            whatIsThere(x+gamePanel.tileSize-1, y-1) == 1 ||
                            whatIsThere(x, y-1) == 4 ||
                            whatIsThere(x+gamePanel.tileSize-1, y-1) == 4)){
                return false;
            }
        }

        if (Objects.equals(direction, "down")) {
            if((y + gamePanel.tileSize) % gamePanel.tileSize == 0 &&
                    (whatIsThere(x, y+gamePanel.tileSize+1) == 1 ||
                            whatIsThere(x+gamePanel.tileSize-1, y+gamePanel.tileSize+1) == 1 ||
                            whatIsThere(x, y+gamePanel.tileSize+1) == 4 ||
                            whatIsThere(x+gamePanel.tileSize-1, y+gamePanel.tileSize+1) == 4)){
                return false;
            }
        }
        return true;
    }

    public boolean collisionWithGhost(int player_x, int player_y, int ghost_x, int ghost_y){

        String[] rel_pos = {"", ""};

        if(player_x > ghost_x){
            rel_pos[0] = "right";
        }else if(player_x < ghost_x){
            rel_pos[0] = "left";
        }else{
            rel_pos[0] = "equal";
        }

        if(player_y > ghost_y){
            rel_pos[1] = "bottom";
        }else if(player_y < ghost_y){
            rel_pos[1] = "above";
        }else{
            rel_pos[1] = "equal";
        }


        if(rel_pos[0].equals("right") && rel_pos[1].equals("bottom")) {
            return player_x < ghost_x + gamePanel.tileSize && player_y < ghost_y + gamePanel.tileSize;
        }else if(rel_pos[0].equals("right") && rel_pos[1].equals("above")){
            return player_x < ghost_x + gamePanel.tileSize && player_y + gamePanel.tileSize > ghost_y;
        }else if(rel_pos[0].equals("left") && rel_pos[1].equals("bottom")){
            return player_x + gamePanel.tileSize > ghost_x && player_y < ghost_y + gamePanel.tileSize;
        }else if(rel_pos[0].equals("left") && rel_pos[1].equals("above")){
            return player_x + gamePanel.tileSize > ghost_x && player_y +gamePanel.tileSize > ghost_y;
        }else if(rel_pos[0].equals("right")){
            return player_x < ghost_x + gamePanel.tileSize;
        }else if(rel_pos[0].equals("left")){
            return player_x + gamePanel.tileSize > ghost_x;
        }else if(rel_pos[1].equals("bottom")){
            return player_y < ghost_y + gamePanel.tileSize;
        }else if(rel_pos[1].equals("above")){
            return player_y+gamePanel.tileSize > ghost_y;
        }

        return false;
    }

    public boolean canCollectedCoin(int x, int y, String direction){
        if (Objects.equals(direction, "right")) {
            if((x+gamePanel.tileSize) % gamePanel.tileSize == 36 &&
                    (whatIsThere(x+gamePanel.tileSize, y) == 2 &&
                            whatIsThere(x+gamePanel.tileSize, y+gamePanel.tileSize-1) == 2)){
                System.out.println("hey");
                return true;
            }
            if((x+gamePanel.tileSize) % gamePanel.tileSize == 36 &&
                    (whatIsThere(x+gamePanel.tileSize, y) == 3 &&
                            whatIsThere(x+gamePanel.tileSize, y+gamePanel.tileSize-1) == 3)){
                System.out.println("hey");
                return true;
            }
        }
        if (Objects.equals(direction, "left")) {
            if(x % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y) == 2 &&
                            whatIsThere(x, y+gamePanel.tileSize-1) == 2)){
                return true;
            }
            if(x % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y) == 3 &&
                            whatIsThere(x, y+gamePanel.tileSize-1) == 3)){
                return true;
            }
        }
        if (Objects.equals(direction, "up")) {
            if(y % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y-1) == 2 &&
                            whatIsThere(x+gamePanel.tileSize-1, y-1) == 2)){
                return true;
            }
            if(y % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y-1) == 3 &&
                            whatIsThere(x+gamePanel.tileSize-1, y-1) == 3)){
                return true;
            }
        }
        if (Objects.equals(direction, "down")) {
            if((y + gamePanel.tileSize) % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y+gamePanel.tileSize+1) == 2 &&
                            whatIsThere(x+gamePanel.tileSize-1, y+gamePanel.tileSize+1) == 2)){
                return true;
            }
            if((y + gamePanel.tileSize) % gamePanel.tileSize == 24 &&
                    (whatIsThere(x, y+gamePanel.tileSize+1) == 3 &&
                            whatIsThere(x+gamePanel.tileSize-1, y+gamePanel.tileSize+1) == 3)){
                return true;
            }
        }
        return false;
    }
}
