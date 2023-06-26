package it.polimi.dinosaursisland;

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

public class GameTest {
    private static DeSerialize deserialize;
    private static Map map;
    private static Game gameTest;
    private static Player playerTest;
    private static Player playerTest1;
    private static Player playerTest2;
    private static GameController gameController;
    private static Type type;
    
    public GameTest(){}
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        deserialize = new DeSerialize();
        map = (Map)deserialize.DeSerializeIt("mappaTest.txt");
        gameTest = new Game(map);
        type = new Type();
        playerTest = new Player("ciao","password");
        playerTest1 = new Player("nome","pass");
        playerTest2 = new Player("giocatore","default");
        gameController = new GameController(gameTest);
    }
    @AfterClass
    public static void tearDownClass() throws Exception {}
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}
    @Ignore
    @Test
    public void tokenPlayerTest(){    
}
    @Test
    public void newRegisterTest(){
        int sizeExpected = 3;
        assertTrue(gameTest.newRegister(playerTest.returnId(), "password"));
        assertTrue(gameTest.newRegister(playerTest1.returnId(), "pass"));
        assertTrue(gameTest.newRegister(playerTest2.returnId(), "default"));
        assertEquals(sizeExpected,gameTest.returnPlayerList().size());
                
    }
    @Test
    public void newLoginTest(){
        gameTest.newRegister(playerTest.returnId(), "password");
        assertTrue(gameTest.newLogin(playerTest.returnId(),"password"));
        assertFalse(gameTest.newLogin(playerTest.returnId(),"passwo"));
        assertFalse(gameTest.newLogin("giocatore","password")); 
    }
    @Test
    public void isPlayerPresTest(){
        gameTest.newRegister(playerTest.returnId(),"password");
        Player playerExpected = playerTest;
        assertEquals(playerExpected.returnId(),gameTest.isPlayerPres(playerTest.returnId()).returnId());
        assertNotSame("giocatore1",gameTest.isPlayerPres(playerTest.returnId()).returnId());
    }
    @Test
    public void setCurrentPlayerTest(){
        Player playerExpected = playerTest;
        gameTest.setCurrentPlayer(playerTest);
        assertEquals(playerExpected, gameTest.returnCurrentPlayer());
    }
    @Test
    public void isTypeNameAvailableTest(){
        gameTest.newRegister(playerTest.returnId(), "password");
        gameTest.newRegister(playerTest1.returnId(), "pass");
        gameTest.createType(playerTest, "carnivori", 1);
        gameTest.createType(playerTest1, "erbivori", 2);
        
        assertTrue(gameTest.isTypeNameAvailable("carni"));
        assertFalse(gameTest.isTypeNameAvailable("carnivori"));
        assertFalse(gameTest.isTypeNameAvailable("erbivori"));
    }            
    @Test
    public void createTypeTest(){
        assertTrue(gameTest.createType(playerTest,"carnivori",1));
        assertFalse(gameTest.createType(playerTest1,"team7",3));
    }        
    @Ignore
    @Test
    public void playerConnectedTest(){}
}