import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Test{
    // Testing for Pacman clone will be done in this class

    //GamePanel Class Testing
    GamePanel gp = new GamePanel();
    Thread testingGameThread;
    public void startGameThreadTest(){
        testingGameThread = new Thread(gp);
        testingGameThread.start();

    }


    KeyHandler kh = new KeyHandler();
    Pacman p = new Pacman(gp,kh);

    // Unit Testing approach will be applied by hand.





}
