package it.polimi.dinosaursisland;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

public class MovementTest {
    public MovementTest(){}
    
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
        try{    
            gameControllerTest.MoveDinosaur(gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0), game.returnMap().ReturnBox(7, 5));    
        }
        catch(MovedException me){
    }
    @AfterClass
    public static void tearDownClass() throws Exception {}
    @Before
    public void setUp(){}
    @After
    public void tearDown(){}
    @Test
    public void moveTest()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        int xExpected1=7;
        int yExpected1=5;
        int size = 2;
        assertEquals(xExpected1,gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).getX());
        assertEquals(yExpected1,gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).getY());
        assertEquals(size,game.returnPlayerList().size());

        try{   
            gameControllerTest.MoveDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0), game.returnMap().ReturnBox(7, 5));
        }
        catch(TypeDeathException tde){
        }
        assertEquals(10,gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).getX());
        assertEquals(5,gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).getY());   
    }
}