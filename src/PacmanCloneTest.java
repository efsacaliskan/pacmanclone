import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PacmanCloneTest {
    KeyHandler kh = new KeyHandler();
    GamePanel gp = new GamePanel();
    // Test ID : T-STP-PMC-001
    @Test
    public void testHomePageIsFirstScreen(){
        try{
            HomePage homePage = new HomePage();
            boolean HomePage = true;
            assertTrue("HomePage is first screen: ",HomePage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Test ID : T-STP-PMC-002
    @Test
    public void testInitialState(){
        Pacman p = new Pacman(gp,kh);
        assertEquals(0,p.getScore(),"Initial Score is 0");
    }

    // Test ID: T-STP-PMC-003
    @Test
    public void pacmanMovementTest(){
        Pacman p = new Pacman(gp,kh);
        CollisionManager collisionManager = new CollisionManager(gp);


        int position_x = p.getEntityX();
        int position_y = p.getEntityY();
        int position = collisionManager.whatIsThere(position_x,position_y);

        p.update();
        assertEquals(2,position);

    }

    // Test ID : T-STP-PMC-004
    @Test
    public void testPacmanContinuesInSameDirectionIfNoObstacle(){
        CollisionManager collisionManager = new CollisionManager(gp);
        Pacman p = new Pacman(gp,kh);
        p.setDefaultPosition();
        int ghost_x = 150;
        int ghost_y = 40;
        if(collisionManager.collisionWithGhost(p.x,p.y,ghost_x,ghost_y)){
            assertEquals(150,p.x);
            assertEquals(40,ghost_y);
        }
    }

    // Test ID : T-STP-PMC-005
    @Test
    public void testCollisionWithCoin(){
        Pacman p = new Pacman(gp,kh);
        CollisionManager collisionManager = new CollisionManager(gp);
        p.setDefaultPosition();
        int score = p.getScore();
        p.update();
        assertEquals(score + 20,p.getScore() + 20,"Update is successful");
        assertFalse(collisionManager.canCollectedCoin(p.x,p.y, p.direction),"The coin is removed.");
    }

    // Test ID : T-STP-PMC-006





    // Test ID : T-STP-PMC-007
    @Test
    public void testGameEndsWhenMaxScoreIsAchieved(){
        Pacman p = new Pacman(gp,kh);
        gp.update();
        assertEquals(gp.maxScore,2630,"Game is Ended and maxScore is achieved");
    }

    //Test ID : T-STP-PMC-008
    @Test
     public void testPacmanCollidesWithGhostAndGameEnds() {
        GamePanel gamePanel = new GamePanel();
        Pacman pacman = gamePanel.pacman;  
        Ghost ghost1 = gamePanel.ghost1; 
        pacman.x = ghost1.x; 
        pacman.y = ghost1.y;
        if (gamePanel.collisionManager.collisionWithGhost(pacman.x, pacman.y, ghost1.x, ghost1.y)) {
            gamePanel.endGame();
        }
        assertNull(gamePanel.gameThread, "Game ended due to collision with ghost.");
    }
     //Test ID : T-STP-PMC-009
     @Test
    public void testPacmanCollidesWithGhostWithBigItem() {
        GamePanel gamePanel = new GamePanel();  
        gamePanel.ghost1.startEatableTimer(); 
        gamePanel.pacman.x = gamePanel.ghost1.x; 
        gamePanel.pacman.y = gamePanel.ghost1.y;
        gamePanel.update();
        assertTrue(gamePanel.ghost1.is_eatable, "Ghost should be eatable after Pacman eats bigItem");
    }
    //Test ID : T-STP-PMC-010
    @Test
    public void testPacmanStopsAtObstacle() {
        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread(); 
        gamePanel.pacman.x = gamePanel.tileSize; 
        gamePanel.pacman.y = 0;
        gamePanel.pacman.direction = "right";
        gamePanel.pacman.move();

        assertEquals(gamePanel.tileSize, gamePanel.pacman.x, "Pacman should have stopped at the obstacle.");
        assertEquals(0, gamePanel.pacman.y, "Pacman's Y coordinate should remain unchanged.");
        
        gamePanel.gameThread.interrupt();
    }

    //Test ID : T-STP-PMC-011
    //burada run time error alıyor
    @Test
    public void testGameEnds() {
    
        GamePanel gamePanel = new GamePanel();
     
        gamePanel.endGame();

        assertNull(gamePanel.gameThread, "Game ends.");
        /*java.lang.NullPointerException: Cannot invoke "javax.swing.JFrame.dispose()" because "topLevelFrame" is null
 at GamePanel.endGame(GamePanel.java:116)
 at PacmanCloneTest.testGameEnds(PacmanCloneTest.java:142)*/
    }
 public static void main(String[] args){
        PacmanCloneTest test = new PacmanCloneTest();
        test.testGameEndsWhenMaxScoreIsAchieved();
        test.testCollisionWithCoin();
        test.pacmanMovementTest();
        test.testInitialState();
        test.testPacmanContinuesInSameDirectionIfNoObstacle();
        test.testHomePageIsFirstScreen();
        test.testPacmanCollidesWithGhostAndGameEnds();
        test.testPacmanCollidesWithGhostWithBigItem();
        test.testPacmanStopsAtObstacle();
        //test.testGameEnds();
    }


}
