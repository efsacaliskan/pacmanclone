
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
                            whatIsThere(x+gamePanel.tileSize+1, y+gamePanel.tileSize-1) == 1)){
                return false;
            }
        }

        if (Objects.equals(direction, "left")) {
            if(x % gamePanel.tileSize == 0 &&
                    (whatIsThere(x-1, y) == 1 ||
                            whatIsThere(x-1, y+gamePanel.tileSize-1) == 1)){
                return false;
            }
        }

        if (Objects.equals(direction, "up")) {
            if(y % gamePanel.tileSize == 0 &&
                    (whatIsThere(x, y-1) == 1 ||
                            whatIsThere(x+gamePanel.tileSize-1, y-1) == 1)){
                return false;
            }
        }

        if (Objects.equals(direction, "down")) {
            if((y + gamePanel.tileSize) % gamePanel.tileSize == 0 &&
                    (whatIsThere(x, y+gamePanel.tileSize+1) == 1 ||
                            whatIsThere(x+gamePanel.tileSize-1, y+gamePanel.tileSize+1) == 1)){
                return false;
            }
        }
        return true;
    }
}
