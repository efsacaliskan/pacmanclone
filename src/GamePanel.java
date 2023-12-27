import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Pacman pacman = new Pacman(this, keyHandler);
    public TileManager tileManager = new TileManager(this);
    public CollisionManager collisionManager = new CollisionManager(this);

    public Ghost ghost1 = new Ghost(this, 48, 48, "blue");
    public Ghost ghost2 = new Ghost(this, 672, 480, "green");
    int maxScore = tileManager.numberOfCoin * 10;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyHandler);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                // System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        pacman.update();
        ghost1.update(pacman.x, pacman.y);
        ghost2.update(pacman.x, pacman.y);
        if(pacman.score == maxScore){
            System.out.println("WIN");
            endGame();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        ghost1.draw(g2);
        ghost2.draw(g2);
        pacman.draw(g2);
        g2.dispose();
    }

    public void endGame() {
        System.out.println("Game Over");
        gameThread = null;
        try {
            new HomePage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JFrame topLevelFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topLevelFrame.dispose();

    }
}
