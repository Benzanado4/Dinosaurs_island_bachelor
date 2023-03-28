package it.polimi.dinosaursisland;

import org.omg.PortableInterceptor.Interceptor;
import org.junit.runners.Parameterized;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import org.junit.runner.RunWith;
import org.junit.rules.ExpectedException;

import it.polimi.dinosaursisland.partita.Turn;
import it.polimi.dinosaursisland.dinosauri.ErbivorousType;
import it.polimi.dinosaursisland.dinosauri.Erbivorous;
import org.junit.Ignore;
import it.polimi.dinosaursisland.exceptions.*;
import it.polimi.dinosaursisland.dinosauri.CarnivorousType;
import it.polimi.dinosaursisland.dinosauri.Dinosaur;
import it.polimi.dinosaursisland.partita.Score;
import it.polimi.dinosaursisland.partita.Player;
import it.polimi.dinosaursisland.dinosauri.Type;
import it.polimi.dinosaursisland.dinosauri.Carnivorous;
import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.serializazion.DeSerialize;
import it.polimi.dinosaursisland.partita.GameController;
import it.polimi.dinosaursisland.partita.Game;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(interceptor.class)
public class GameControllerTest {
    @Interceptor
    public ExpectedException thrown; //= new ExpectedException();

    public GameControllerTest(){}

    private static DeSerialize deserialize;
    private static Game game;
    private static GameController gameControllerTest;
    private static Map map;
    private static Carnivorous trex;
    private static Erbivorous triceratop;
    private static Player playerTest;
    private static Player playerTest1;
    private static Score score;
    private static CarnivorousType type;
    private static ErbivorousType type1;
    private static Turn turn;
    private static Turn turn1;
    private FightException fe;
        
    @BeforeClass
    public static void setUpClass() throws Exception {
        deserialize  = new DeSerialize();
        map = (Map)deserialize.DeSerializeIt("mappaTest.txt");
        game = new Game (map);
        gameControllerTest = new GameController(game);
        trex  = new Carnivorous(7,3,"bravo");
        triceratop = new Erbivorous(7,5,"triky");
        type = new CarnivorousType("name", trex);
        type1 = new ErbivorousType("foglie",triceratop);
        playerTest = new Player("first","bella");
        playerTest1 = new Player("second","brutta");
        System.out.println("@BeforeClass:starting Class");
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    @Before
    public void setUp(){
        score = new Score(playerTest);   
    }
    @After
    public void tearDown(){
        map = null;
        game = null;
    }    
    @Test(expected = java.lang.NullPointerException.class)
    public void findTypeTest1()throws NullPointerException{
        gameControllerTest.findType(trex); 
    }
    @Test(expected = java.lang.NullPointerException.class)
    public void findTypeByNameTest1()throws NullPointerException{
        gameControllerTest.findTypeByName(trex.ReturnName()); 
    } 
    @Test(expected = java.lang.NullPointerException.class)
    public void findDinoByNameTest1()throws NullPointerException{
        gameControllerTest.findDinoByName(trex.ReturnName()); 
    } 
    @Test(expected = java.lang.NullPointerException.class)
    public void findPlayerTest1() throws NullPointerException{
        gameControllerTest.findPlayer(type);
        } 
    @Test(expected = java.lang.NullPointerException.class)
    public void findPlayerByNameTest1()throws NullPointerException{
        gameControllerTest.findPlayerByName(trex.ReturnName()); 
        } 
    @Ignore
    @Test
    public void findTypeTest(){
        int sizeExpected = 1;
        score.AssignScore();
        game.newRegister(playerTest.returnId(), "bella");
        gameControllerTest.findPlayerByName("first").setType(type); 
        game.setGameController(gameControllerTest);
        gameControllerTest.setDinosaur(trex);
        
        gameControllerTest.findPlayerByName("first").returnType().returnDinosaurs().get(0).setPlayer(playerTest);  
        game.newLogin(playerTest.returnId(), "bella");
        assertEquals(playerTest.returnType(),type);
        assertEquals(sizeExpected,game.returnPlayerList().size());
        assertEquals(playerTest.returnId(),game.returnPlayerList().get(0).returnId());
        assertEquals(sizeExpected,gameControllerTest.findPlayerByName("first").returnType().returnDinosaurs().size());
        assertEquals(type,gameControllerTest.findType(trex));
        System.out.println("@findTypeTest: OK find!");
        }
    @Ignore
    @Test
    public void findTypeByNameTest(){
        Type typeExpected = type; 
        assertEquals(typeExpected,gameControllerTest.findTypeByName(trex.ReturnName()));
        System.out.println("@findTypeByName-Test: OK find!");
    }
    @Ignore
    @Test
    public void findPlayerTest(){
        Player playerExpected = playerTest;
        assertEquals(playerExpected.returnId(),gameControllerTest.findPlayer(type).returnId());
        System.out.println("@findPlayer-Test: OK find!");
    }
    @Ignore
    @Test
    public void findPlayerByNameTest(){
        Player playerExpected = playerTest;
        assertEquals(playerExpected.returnId(),gameControllerTest.findPlayerByName("first").returnId());
        System.out.println("@findPlayerByName-Test: OK find!");
    }
    @Ignore
    @Test
    public void findDinoByNameTest(){
        Dinosaur dinoExpected = trex; 
        assertEquals(dinoExpected,gameControllerTest.findDinoByName(trex.ReturnName()));
        System.out.println("@findDinoByName-Test: OK find!");
    }
    @Ignore
    @Test(expected = it.polimi.dinosaursisland.exceptions.NotMovedException.class)
    public void moveTest1 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.setDinosaur(trex);
        gameControllerTest.MoveDinosaur(trex, game.returnMap().ReturnBox(8,3));
        System.out.println("@Movement-FirstTest: OK Can't move!");
    }
    @Ignore
    @Test(expected = it.polimi.dinosaursisland.exceptions.MovedException.class)
    public void moveTest2 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.setDinosaur(trex);
        gameControllerTest.MoveDinosaur(trex, game.returnMap().ReturnBox(5,3));
        System.out.println("@movement-SecondTest: OK moved!");
    } 
    @Ignore
    @Test(expected = it.polimi.dinosaursisland.exceptions.YetMovedException.class)
    public void moveTest3 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.setDinosaur(trex);
        gameControllerTest.MoveDinosaur(trex, game.returnMap().ReturnBox(6,3));
        System.out.println("@movement-ThirdTest: OK alreadyMoved!");
    }
    @Ignore
    @Test(expected = it.polimi.dinosaursisland.exceptions.EatException.class)
    public void moveTest4()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        game.newRegister(playerTest1.returnId(), "brutta");
        gameControllerTest.findPlayerByName("second").setType(type1); 
        gameControllerTest.setDinosaur(triceratop);
        gameControllerTest.MoveDinosaur(triceratop, game.returnMap().ReturnBox(7,6));
        System.out.println("@movement-FourthTest: OK eat!");
    }
    @Test //(expected = it.polimi.dinosaursisland.exceptions.FightException.class)
    public void moveTest5()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        int xExpected = 7;
        int xExpected1 = 7;
        int yExpected = 5;
        int yExpected1 = 5;
        int sizeExpected =2;

        game.newRegister("firstPlayer", "bella");
        game.newLogin("firstPlayer", "bella");
        game.newRegister("secondPlayer", "brutta");
        game.newLogin("secondPlayer", "brutta");
        game.createType(gameControllerTest.findPlayerByName("firstPlayer"), "bravi", 1);
        game.createType(gameControllerTest.findPlayerByName("secondPlayer"), "cattivi", 2);
        game.gameAccess(gameControllerTest.findPlayerByName("firstPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("secondPlayer"));
        gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).setPosition(game.returnMap().ReturnBox(7, 3));
        gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).setPosition(game.returnMap().ReturnBox(8, 5));
        
        assertEquals(sizeExpected,game.returnPlayerList().size());
        assertEquals(gameControllerTest.findTypeByName("bravi1"),gameControllerTest.findPlayerByName("firstPlayer").returnType());      
        assertEquals(gameControllerTest.findTypeByName("cattivi1"),gameControllerTest.findPlayerByName("secondPlayer").returnType());      
        assertEquals(gameControllerTest.findDinoByName("bravi1"),gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0));      
        assertEquals(gameControllerTest.findDinoByName("cattivi1"),gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0));      
        try{    
            gameControllerTest.MoveDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0), game.returnMap().ReturnBox(7, 5));
        }
        catch(MovedException mex){}
        
        assertEquals(xExpected,gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).getX());
        assertEquals(yExpected,gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).getY());
        gameControllerTest.MoveDinosaur(gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0), game.returnMap().ReturnBox(7, 5));
        assertEquals(xExpected1,gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).getX());
        assertEquals(yExpected1,gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).getY());
        assertEquals(gameControllerTest.findDinoByName("buoni1"),fe.returnDeadDino());
    }     
    @Ignore
    @Test
    public void test(){
        int sizeEx = 2;
        assertEquals(sizeEx,game.returnPlayerList().size());
    }
}
