
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public KeyHandler() {

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_SPACE) {
            if(gamePanel.isPaused){
                gamePanel.isPaused = false;
            }else{
                gamePanel.isPaused = true;
            }
        }else if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        else if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        else if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}

