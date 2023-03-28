package test;

import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.mappa.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.serializazion.DeSerialize;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import org.junit.Test;
import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.Collection;
import org.junit.Ignore;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *  Junit4 Test per testare i metodi del Dinosauro.
 */



public class DinosaursTest {
        
        private static DeSerialize deserialize;
        private static Carnivorous dinoTestCarne;
        private static Erbivorous dinoTestErba;
        private static Map mapTest;
        
        public DinosaursTest(){}
        
        @BeforeClass
        public static void setUpClass() throws Exception {
            deserialize = new DeSerialize();
            dinoTestCarne = new Carnivorous(8,13,"dinouno");
            dinoTestErba = new Erbivorous(13,14,"dinodue");
            mapTest = (Map)deserialize.deSerializeIt("mappaDino");
            System.out.println("@BeforeClass:starting Class");
            mapTest.printMap();
        }
        
        @AfterClass
        public static void tearDownClass(){
            deserialize = null;
            dinoTestCarne = null;
            mapTest = null;
            System.out.println("@AfterClass:ending Class");
       }   
        
        @Test
        public void energyTest(){
            int openingEnergy = 750;
            int openingSize = 1;
            assertEquals(openingSize,dinoTestCarne.returnSize());
            assertEquals(openingSize,dinoTestErba.returnSize());
            assertEquals(openingEnergy,dinoTestCarne.returnEnergy());
            assertEquals(openingEnergy,dinoTestErba.returnEnergy());
        }
        
        @Test
        public void strenghtTest(){
            int openingCarniStrength = 1500;
            int openingErbiStrength = 750;
            assertEquals(openingCarniStrength,dinoTestCarne.returnStrenght());
            assertEquals(openingErbiStrength,dinoTestErba.returnStrenght());
        }

        
        @Test
        public void movementTest(){
            mapTest.printMap();
            int xExpected = 8;
            int yExpected = 12;
            assertEquals(mapTest.returnBox(xExpected, yExpected),dinoTestCarne.movement(mapTest, mapTest.returnBox(8,12)));
            
            assertEquals(xExpected,dinoTestCarne.getX());
            assertEquals(yExpected,dinoTestCarne.getY());
            assertNotNull(dinoTestCarne.movement(mapTest, mapTest.returnBox(10,12))); 
            assertNotNull(dinoTestCarne.movement(mapTest, mapTest.returnBox(13,15)));
            assertNull(dinoTestCarne.movement(mapTest, mapTest.returnBox(13,15)));
            assertNull(dinoTestCarne.movement(mapTest, mapTest.returnBox(11,15)));
            assertNull(dinoTestCarne.movement(mapTest, mapTest.returnBox(27,35)));
            
            System.out.println(dinoTestCarne.getX());
            System.out.println(dinoTestCarne.getY());
            System.out.println("@movementTest: Ok!!");
           }
        
        
        @Test 
        public void movementTest2(){
            dinoTestErba.setEnergy(20);
            Dinosaur dinosaurExpected = dinoTestErba;
            assertEquals(dinoTestErba,dinoTestErba.movement(mapTest, mapTest.returnBox(15,16)));
            
            System.out.println(dinoTestErba.returnEnergy());
            System.out.println("@movementTest2: Ok DinoDead!!");
        }
        
        
	@Test
        public void incrementEnergyTest(){
                dinoTestCarne.setEnergy(750);
		int energyExpected = 1000;
		int energyRemainedExpected = 500;
		assertEquals(energyRemainedExpected,dinoTestCarne.incrementEnergy(750));
                assertEquals(energyExpected, dinoTestCarne.returnEnergy());
                System.out.println("@incrementEnergyTest: Ok!!");
	}
	
        @Test
        public void LayEggTest(){
            dinoTestCarne.incrementEnergy(500);
		dinoTestCarne.growth();
		dinoTestCarne.growth();
		dinoTestCarne.incrementEnergy(2500);
		int energyExpect = dinoTestCarne.returnEnergy();
		assertTrue(dinoTestCarne.layEgg());
                System.out.println("@layEggTest: Ok!!");
	}
       
        @Test
	public void growthTest(){
		int sizeExpected = 3;
                
		dinoTestCarne.growth();
		dinoTestCarne.incrementEnergy(1000);
                System.out.println(dinoTestCarne.returnEnergy());
		assertEquals(sizeExpected, dinoTestCarne.growth());
                assertEquals(0,dinoTestCarne.growth());
                System.out.println("@growthTest: Ok!!");
	}
        
	@Test
        public void fightTest(){
            Erbivorous dinoTest2 = new Erbivorous(2,3,"dinodue");
            dinoTestCarne.setEnergy(2000);
            dinoTest2.setEnergy(3000);
            dinoTestCarne.growth();
            dinoTest2.growth();
            Dinosaur dinoExpected1 = dinoTestCarne;
            Dinosaur dinoExpected2 = dinoTest2;
            assertNull(dinoTestCarne.fight(dinoTestCarne));
            assertEquals(dinoExpected1,dinoTestCarne.fight(dinoTest2));
            dinoTestCarne.setEnergy(3000);
            assertEquals(dinoExpected2,dinoTest2.fight(dinoTestCarne));
            System.out.println("@fightTest: Ok!!");
        }
}

