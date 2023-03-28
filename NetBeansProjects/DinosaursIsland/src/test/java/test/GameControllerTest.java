



package test;


import it.polimi.dinosaursisland.dinosauri.Erbivorous;

import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.exceptions.*;
import it.polimi.dinosaursisland.dinosauri.CarnivorousType;
import it.polimi.dinosaursisland.dinosauri.Dinosaur;

import it.polimi.dinosaursisland.partita.Player;
import it.polimi.dinosaursisland.dinosauri.Carnivorous;
import it.polimi.dinosaursisland.serializazion.DeSerialize;
import it.polimi.dinosaursisland.partita.GameController;
import it.polimi.dinosaursisland.partita.Game;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *  Junit4 Test per testare i metodi principali del GameController.
 */

public class GameControllerTest {

    public GameControllerTest() {}
    
        
        private static DeSerialize deserialize;
        private static Game game;
        private static GameController gameControllerTest;
        private static Map map;
        private static Carnivorous trex;
        private static Erbivorous triceratop;
        private static CarnivorousType type;
        
        
        
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        deserialize  = new DeSerialize();
        map = (Map)deserialize.deSerializeIt("mappaDino");
        game = new Game(map);
        gameControllerTest = new GameController(game);
        trex  = new Carnivorous(3,7,"bravo");
        triceratop = new Erbivorous(6,5,"triky");
        type = new CarnivorousType("CarneType", trex);
        map.printMap();
                
        game.newRegister("firstPlayer", "bella");
        game.newLogin("firstPlayer", "bella");
        game.newRegister("secondPlayer", "brutta");
        game.newLogin("secondPlayer", "brutta");
        game.newRegister("thirdPlayer", "carina");
        game.newLogin("thirdPlayer", "carina");
        game.newRegister("fourthPlayer", "orribile");
        game.newLogin("fourthPlayer", "orribile");
        game.newRegister("fifthPlayer", "brutta");
        game.newLogin("fifthPlayer", "brutta");
        game.newRegister("sixthPlayer", "brutta");
        game.newLogin("sixthPlayer", "brutta");
        
        game.createType(gameControllerTest.findPlayerByName("firstPlayer"), "bravi", 1);
        game.createType(gameControllerTest.findPlayerByName("secondPlayer"), "cattivi", 2);
        game.createType(gameControllerTest.findPlayerByName("thirdPlayer"), "trex", 1);
        game.createType(gameControllerTest.findPlayerByName("fourthPlayer"), "triceratop", 2);
        game.createType(gameControllerTest.findPlayerByName("fifthPlayer"), "tricer", 2);
        game.createType(gameControllerTest.findPlayerByName("sixthPlayer"), "ciao", 2);
        game.gameAccess(gameControllerTest.findPlayerByName("firstPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("secondPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("thirdPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("fourthPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("fifthPlayer"));
        game.gameAccess(gameControllerTest.findPlayerByName("sixthPlayer"));
        

        System.out.println("@BeforeClass:starting Class");
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
       
    @Test
    public void findTypeTest1()throws NullPointerException{
        assertNull(gameControllerTest.findType(trex));
        }
    
   
    @Test
    public void findTypeByNameTest1()throws NullPointerException{
        assertNull(gameControllerTest.findTypeByName(trex.returnName())); 
        } 
    
    @Test
    public void findDinoByNameTest1()throws NullPointerException{
      
        assertNull(gameControllerTest.findDinoByName(trex.returnName())); 
        } 
    
    @Test
    public void findPlayerTest1() throws NullPointerException{
        assertNull(gameControllerTest.findPlayer(type));
        } 
   
    @Test
    public void findPlayerByNameTest1()throws NullPointerException{
         assertNull(gameControllerTest.findPlayerByName(trex.returnName())); 
        }   
    
    @Test
    public void findTypeTest(){
        int sizeExpected = 6;
        assertEquals(sizeExpected,game.returnPlayerList().size());
        assertEquals(gameControllerTest.findPlayerByName("firstPlayer").returnType(),game.returnPlayerList().get(0).returnType());
        assertEquals(gameControllerTest.findPlayerByName("secondPlayer").returnType(),game.returnPlayerList().get(1).returnType());
        System.out.println("@findTypeTest: OK find!");
        }
    
    @Test
    public void findTypeByNameTest(){
        
        assertEquals(gameControllerTest.findPlayerByName("firstPlayer").returnType(),gameControllerTest.findTypeByName("bravi1"));
        assertEquals(gameControllerTest.findPlayerByName("secondPlayer").returnType(),gameControllerTest.findTypeByName("cattivi1"));
        System.out.println("@findTypeByName-Test: OK find!");
    }
    
    
    @Test
    public void findPlayerTest(){
        Player playerExpected = gameControllerTest.findPlayerByName("firstPlayer");
        assertEquals(playerExpected,gameControllerTest.findPlayer(gameControllerTest.findTypeByName("bravi1")));
        System.out.println("@findPlayer-Test: OK find!");
    }
    
    
    @Test
    public void findPlayerByNameTest(){
        Player playerExpected = gameControllerTest.findPlayerByName("firstPlayer");
        assertEquals(playerExpected,gameControllerTest.findPlayerByName("firstPlayer"));
        System.out.println("@findPlayerByName-Test: OK find!");
    }
    
    @Test
    public void findDinoByNameTest(){
        Dinosaur dinoExpected = gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0); 
        assertEquals(dinoExpected,gameControllerTest.findDinoByName("bravi1"));
        System.out.println("@findDinoByName-Test: OK find!");
    }
    
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.NotMovedException.class)
    public void moveTest1 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).setPosition(game.returnMap().returnBox(3,7));
        gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).setPosition(game.returnMap().returnBox(6,5));
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(4, 7));  
        System.out.println("@moveTest1-FirstTest: OK Can't move!");
        
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.MovedException.class)
    public void moveTest2 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(4,10));  
        System.out.println("@moveTest2-SecondTest:OK moved!");
    } 
    
    @Test
    public void controlActionTest(){
    assertFalse(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).returnMovementAction());    
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.YetMovedException.class)
    public void moveTest3 ()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(4,9));  
        System.out.println("@moveTest3-ThirtTest:OK alreadyMoved!");
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.EatException.class)
    public void moveTest4()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(6, 6));  
        System.out.println("@moveTest4-FourthTest:OK eat!");
    }
       
    //prova con 2 quando depone
    @Test(expected = it.polimi.dinosaursisland.exceptions.TypeDeathException.class)
    public void moveTest5()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).setEnergy(40);       
        gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0).setAction();       
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("secondPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(8,8));
        assertNull(gameControllerTest.findTypeByName("cattivi1"));
        assertNull(gameControllerTest.findPlayerByName("secondPlayer"));
        assertNull(gameControllerTest.findType(gameControllerTest.findDinoByName("cattivi1")));
        
        System.out.println("@moveTest5-FourthTest:OK typeDeath!");
        
      }
    
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.EatException.class)
    public void moveTest6()throws NotMovedException, YetMovedException, FightException, DinoDeathException, TypeDeathException, EatException, MovedException, MoveDeathException{
        gameControllerTest.findPlayerByName("thirdPlayer").returnType().returnDinosaur(0).setPosition(game.returnMap().returnBox(1,31));
        gameControllerTest.moveDinosaur(gameControllerTest.findPlayerByName("thirdPlayer").returnType().returnDinosaur(0), game.returnMap().returnBox(1,30));
        
        System.out.println("@moveTest6-FifthTest:OK eat!");
    }
    
    /*Test per la crescita del Dinosauro*/
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.GrowDinosaurException.class)
    public void growthDinosaurOkTest() throws GrowDinosaurException, DinoDeathException, TypeDeathException, ActionNotPermittedException{
        
        gameControllerTest.growDinosaur(gameControllerTest.findPlayerByName("fifthPlayer").returnType().returnDinosaur(0));
    }
 
    
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.EggDeposedException.class)
    public void layEggTest1() throws TypeDeathException, DinoDeathException, ActionNotPermittedException, EggDeposedException{
        
        gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0).setEnergy(1800);
        gameControllerTest.layEgg(gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0));
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.GrowDinosaurException.class)
    public void growthDinosaur6() throws GrowDinosaurException, DinoDeathException, TypeDeathException, ActionNotPermittedException{
        gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0).setEnergy(800);       
        gameControllerTest.growDinosaur(gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0));
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.ActionNotPermittedException.class)
    public void zz() throws DinoDeathException, TypeDeathException, ActionNotPermittedException, GrowDinosaurException{
        
        System.out.println(gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnAvailableAction());       
        gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0).setEnergy(1800);       
        gameControllerTest.growDinosaur(gameControllerTest.findPlayerByName("sixthPlayer").returnType().returnDinosaur(0));  
}
    
    @Test
    public void layEggTest2() throws TypeDeathException{
        assertFalse(gameControllerTest.hatchEgg());
        
    }
    
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.TypeDeathException.class)
     public void growthTest3() throws DinoDeathException, TypeDeathException, ActionNotPermittedException, GrowDinosaurException{
        gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).setEnergy(100);
        gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0).setAction();   
        gameControllerTest.growDinosaur(gameControllerTest.findPlayerByName("firstPlayer").returnType().returnDinosaur(0));
    }
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.EggDeposedException.class)
    public void layEggTest() throws  EggDeposedException, TypeDeathException, DinoDeathException, ActionNotPermittedException{
       gameControllerTest.findPlayerByName("thirdPlayer").returnType().returnDinosaur(0).setEnergy(1800);
       gameControllerTest.layEgg(gameControllerTest.findPlayerByName("thirdPlayer").returnType().returnDinosaur(0));   
    }
    
    
    @Test(expected = it.polimi.dinosaursisland.exceptions.TypeDeathException.class)
    public void layEggTest3() throws TypeDeathException, DinoDeathException, ActionNotPermittedException, EggDeposedException{
        gameControllerTest.layEgg(gameControllerTest.findPlayerByName("thirdPlayer").returnType().returnDinosaur(0));
        
    }
        
    @Test(expected = it.polimi.dinosaursisland.exceptions.TypeDeathException.class)
    public void killDinoTest() throws TypeDeathException{
        gameControllerTest.killDinosaur(gameControllerTest.findPlayerByName("fourthPlayer").returnType().returnDinosaur(0));
        
    }      
    
    
}
   
   
