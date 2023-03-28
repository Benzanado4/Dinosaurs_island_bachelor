
package test;

import it.polimi.dinosaursisland.dinosauri.Dinosaur;
import it.polimi.dinosaursisland.dinosauri.Carnivorous;
import it.polimi.dinosaursisland.dinosauri.CarnivorousType;
import it.polimi.dinosaursisland.dinosauri.Erbivorous;
import it.polimi.dinosaursisland.dinosauri.ErbivorousType;
import it.polimi.dinosaursisland.partita.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit4 test per i metodi di Player
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    private static Player player;
    private static Player player2;
    private static Player player3;
    private static ErbivorousType type;
    private static CarnivorousType type3;
    private static Erbivorous first;
    private static Carnivorous second;
    private static Carnivorous seconddue;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        first = new Erbivorous("first");
        second = new Carnivorous("second");
        seconddue = new Carnivorous("second");
        type = new ErbivorousType("erbivori",first);
        player = new Player("username","password");
        player2 = new Player("username2","password2",type);
        player3 = new Player("username3","password3",type);
    
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        first = null;
        second = null;
        seconddue = null;
        type = null;
        player = null;
        player2 = null;    
        player3 = null;    
    }
    
    
    @Test
    public void returnIdTest(){
        String idExpected = "username";
        assertEquals(idExpected,player.returnId());
        String idExpected2 = "username2";
        assertEquals(idExpected2,player2.returnId());
   
    }
    
    @Test
    public void returnIdTest3(){
        String idExpected3 = "username3";
        assertEquals(idExpected3,player3.returnId());
   
    }
    @Test
    public void passCorrectTest(){
        String passExpected = "password";
        String passExpected2 = "password2";
        String passExpected3 = "password3";
        assertTrue(player.passwordCorrect(passExpected));
        assertTrue(player2.passwordCorrect(passExpected2));
        assertTrue(player3.passwordCorrect(passExpected3));
        assertFalse(player.passwordCorrect("pass"));
        assertFalse(player3.passwordCorrect("password"));
    }
    
    @Test
    public void returnTypeTest(){
        assertEquals(type,player2.returnType());
    }
    
    @Test
    public void typeDeadTest(){
        
        player2.typeDead();
        assertNull(player2.returnType());
        assertNull(player.returnType());
    }
    
    @Test
    public void setScoreTest(){
        int scoreExpected = 2;
        player3.setScore();
        assertEquals(scoreExpected,player3.returnScore().returnScore());
       
    }
    @Test
    public void setScoreTest2(){
        player3.returnType().returnDinosaur(0).growth();
        player3.returnType().newDinosaur2(seconddue, player3);
        
        int scoreExpected = 5;
        player3.setScore();
        assertEquals(scoreExpected,player3.returnScore().returnScore());
        
    }
    @Test
    public void setEggTest(){
        Dinosaur test = second;
        player3.setEgg(second);
        assertEquals(test,player3.returnEgg());
        assertNull(player.returnEgg());
        
    }        
    
    
}

