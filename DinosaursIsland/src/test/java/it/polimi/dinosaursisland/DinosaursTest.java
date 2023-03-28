package it.polimi.dinosaursisland;

import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.mappa.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import it.polimi.dinosaursisland.dinosauri.*;
import it.polimi.dinosaursisland.mappa.*;

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

/////////////////////////////////////////////////////////////////////////////////
@RunWith (Parameterized.class)
public class DinosaursTest {
	private int x = 5;
    private int y = 5;      
    private int xExpected;
    private int yExpected;
    private Carnivorous dinoTest;
    private Map mapTest;
    
    public DinosaursTest(){}

	@Before
	public void setUp() throws Exception {
		dinoTest = new Carnivorous(x,y,"dinouno");
		mapTest = new Map();
	}
	@After
	public void tearDown(){
		dinoTest = null;
		mapTest = null;
	}   
	@Test
	public void movementTest(){
		mapTest.CreateMap();
		mapTest.PrintMap();
	    assertNotNull(dinoTest.Movement(mapTest, mapTest.ReturnBox(xExpected, yExpected)));
		assertEquals(xExpected,dinoTest.getX());
		assertEquals(yExpected,dinoTest.getY());
	}
	@Ignore
	@Test
	public void incrementEnergyTest(){
		int energyExpected = 1000;
		int energyRemainedExpected = 500;
		assertEquals(energyRemainedExpected,dinoTest.IncrementEnergy(750));
		    assertEquals(energyExpected, dinoTest.ReturnEnergy());
		}
	@Test
	public void LayEggTest(){
		dinoTest.IncrementEnergy(500);
		dinoTest.Growth();
		dinoTest.Growth();
		dinoTest.IncrementEnergy(2500);
		System.out.println(dinoTest.ReturnEnergy());
		System.out.println(dinoTest.ReturnSize());
		int energyExpect = dinoTest.ReturnEnergy();
		assertTrue(dinoTest.LayEgg());
	}
	@Test
	public void growthTest(){
		int sizeExpected = 3;
		dinoTest.Growth();
		dinoTest.IncrementEnergy(1000);
		assertEquals(sizeExpected, dinoTest.Growth());
	}
}