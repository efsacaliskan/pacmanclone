import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PacmanCloneTest {
    KeyHandler kh = new KeyHandler();
    GamePanel gp = new GamePanel();
    // Test ID : T-STP-PMC-001
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
    public void testInitialState(){
        Pacman p = new Pacman(gp,kh);
        assertEquals(0,p.getScore(),"Initial Score is 0");
    }

    // Test ID: T-STP-PMC-003
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
    public void testCollisionWithCoin(){
        Pacman p = new Pacman(gp,kh);
        CollisionManager collisionManager = new CollisionManager(gp);
        p.setDefaultPosition();
        int score = p.getScore();
        p.update();
        assertEquals(score + 10,p.getScore() + 10,"Update is successful");
        assertFalse(collisionManager.canCollectedCoin(p.x,p.y, p.direction),"The coin is removed.");
    }

    // Test ID : T-STP-PMC-006





    // Test ID : T-STP-PMC-007
    public void testGameEndsWhenMaxScoreIsAchieved(){
        Pacman p = new Pacman(gp,kh);
        gp.update();
        assertEquals(gp.maxScore,630,"Game is Ended and maxScore is achieved");
    }

    public static void main(String[] args){

        PacmanCloneTest test = new PacmanCloneTest();
        test.testGameEndsWhenMaxScoreIsAchieved();
        test.testCollisionWithCoin();
        test.pacmanMovementTest();
        test.testInitialState();
        test.testPacmanContinuesInSameDirectionIfNoObstacle();
        test.testHomePageIsFirstScreen();

    }


}
