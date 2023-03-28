package test;


import it.polimi.dinosaursisland.serializazion.DeSerialize;
import it.polimi.dinosaursisland.mappa.Map;
import it.polimi.dinosaursisland.partita.*;
import it.polimi.dinosaursisland.dinosauri.*;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Junit4 Test della partita.
 */

public class GameTest {
    
    private static DeSerialize deserialize;
    private static Map map;
    private static Game gameTest;
    private static Player playerTest;
    private static Player playerTest1;
    private static Player playerTest2;
    private static GameController gameController;
    
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        deserialize = new DeSerialize();
        map = (Map)deserialize.deSerializeIt("mappaDino");
        gameTest = new Game(map);
        gameController = new GameController(gameTest);
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
        
    }
    
    @Test
    public void setNextTest(){
        assertNull(gameTest.returnCurrentPlayer());
    }
   
    @Test
    public void newRegisterTest(){
        int sizeExpected = 3;
        assertTrue(gameTest.newRegister("playerNumber0", "zero"));
        assertTrue(gameTest.newRegister("playerNumber1", "primo"));
        assertTrue(gameTest.newRegister("playerNumber2", "due"));
        assertEquals(sizeExpected,gameTest.returnPlayerList().size());
        assertFalse(gameTest.newRegister("playerNumber1", "pass1"));
        System.out.println("@newRegisterTest: Ok!");
    }
    
    @Test
    public void newLoginTest(){
        int activeExpected = 3;
        
        assertTrue(gameTest.newLogin("playerNumber0","zero"));
        assertTrue(gameTest.newLogin("playerNumber1","primo"));
        assertTrue(gameTest.newLogin("playerNumber2","due"));
        assertFalse(gameTest.newLogin("playerNumber2","pass2"));
        assertFalse(gameTest.newLogin("playerNumber2","ZERO"));
        System.out.println("@newLoginTest: Ok!");
    }
    
    @Test
    public void isPlayerPresTest(){
        assertEquals(3,gameTest.returnPlayerList().size());
        
        Player playerNumber2 = gameController.findPlayerByName("playerNumber2");
        
        Player playerExpected = gameController.findPlayerByName("playerNumer1");
        
        assertEquals(playerExpected,gameTest.isPlayerPres("playerNumer1"));
        assertNotSame(playerNumber2,gameTest.isPlayerPres("playerNumber1"));
        System.out.println("@isPlayerPres: Ok!");
    }
    
    @Test
    public void setCurrentPlayerTest(){
        
        Player playerExpected = gameController.findPlayerByName("playerNumber3");
        gameTest.setCurrentPlayer(gameController.findPlayerByName("playerNumber3"));
        assertEquals(playerExpected, gameTest.returnCurrentPlayer());
        System.out.println("@setCurrentPlayer: Ok!");
        }
    
            //Fino a otto giocatori
    @Test
    public void createTypeTest(){
        
        gameTest.newRegister("playerNumber3", "tre");
        gameTest.newRegister("playerNumberFour4", "quattro");
        gameTest.newRegister("playerNumberFive5", "cinque");
        gameTest.newRegister("playerNumberSix6", "sei");
        gameTest.newRegister("playerNumberSeven7", "sette");
        
        assertTrue(gameTest.newLogin("playerNumber3", "tre"));
        assertTrue(gameTest.newLogin("playerNumberFour4", "quattro"));
        assertTrue(gameTest.newLogin("playerNumberFive5", "cinque"));
        assertTrue(gameTest.newLogin("playerNumberSix6", "sei"));
        assertTrue(gameTest.newLogin("playerNumberSeven7", "sette"));
        
        
        gameTest.createType(gameController.findPlayerByName("playerNumber0"), "Trex", 1);
        gameTest.createType(gameController.findPlayerByName("playerNumber1"), "Veloc", 1);
        gameTest.createType(gameController.findPlayerByName("playerNumber2"), "Bronto", 2);
        gameTest.createType(gameController.findPlayerByName("playerNumber3"), "Stego", 2);
        gameTest.createType(gameController.findPlayerByName("playerNumberFour4"), "Mega", 1);
        gameTest.createType(gameController.findPlayerByName("playerNumberFive5"), "Trice", 2);
        gameTest.createType(gameController.findPlayerByName("playerNumberSix6"), "Ptero", 1);
        gameTest.createType(gameController.findPlayerByName("playerNumberSeven7"), "Brachio", 2);
        
        
        gameTest.gameAccess(gameController.findPlayerByName("playerNumber0"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumber1"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumber2"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumber3"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumberFive5"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumberSix6"));
        gameTest.gameAccess(gameController.findPlayerByName("playerNumberSeven7"));
        
        
        assertEquals(8,gameTest.returnPlayerList().size());
        assertEquals(7,gameTest.returnActiveList().size());
                
        System.out.println("@createTypeTest: Ok!");

        
    }
    @Test
    public void isTypeNameAvailableTest(){
        
        assertTrue(gameTest.isTypeNameAvailable("stego23"));
        assertFalse(gameTest.isTypeNameAvailable("Stego"));
        assertFalse(gameTest.isTypeNameAvailable("Brachio"));
        assertFalse(gameTest.isTypeNameAvailable("Ptero"));
        
        System.out.println("@isTypeNameAvailableTest: Ok!");
    }        
    
    
    @Test
    public void setCurrentPlayerTest2(){
        
        Player playerExpected = gameController.findPlayer(gameController.findTypeByName("Stego1"));
        gameTest.setCurrentPlayer(gameController.findPlayerByName("playerNumber3"));
        assertEquals(playerExpected, gameTest.returnCurrentPlayer());
        System.out.println("@setCurrentPlayer SecondTest: Ok!");
        }
    
    @Test
    public void setNextPlayerTest(){
        
        Player playerExpected = gameController.findPlayerByName("playerNumberFive5");
        gameTest.setNextPlayer();
        assertEquals(playerExpected,gameTest.returnCurrentPlayer());
    }
    @Test
    public void endGameTest(){
        int sizeOld = 7;
        assertEquals(sizeOld,gameTest.returnActiveList().size());
        gameTest.endGame();
        int sizeNew = 0; 
        assertEquals(sizeNew,gameTest.returnActiveList().size());
        
    }

}