package it.polimi.dinosaursisland;

import it.polimi.dinosaursisland.mappa.*;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MapTest {
    Map mapTest = new Map();
    Box temp = new Box();
        
    private int vegetationExpected = 512;
    private int carrionExpected = 20;
    private int vegetationCont = 0;
    private int carrionCont = 0;
        
    private GroundBox temp1 = new GroundBox(temp);
    
    @BeforeClass
    public static void setUpClass(){
        Map mapTest = new Map();
        Box temp = new Box();
       
    }
    @AfterClass
    public static void tearDownClass(){
        mapTest = null;
        temp = null;
    }
    @Before
    public void setUp(){
        mapTest = new Map();
        Box temp = new Box();
    }
    @After
    public void tearDown(){
        mapTest = null;
        temp = null;
    }
    @Ignore
    @Test
    public void CounterTest(){
        int contWaterExpected = 320;
        int contGroundExpected = 1280;
        int contWater = 0;
        int contGround = 0;
        mapTest.CreateMap();
        for (int i = 0; i < 40; i++){
            for (int j = 0; j < 40; j++){
                if (mapTest.ReturnBox(i, j) instanceof WaterBox){
                    contWater++;
                }
                else{
                    contGround++;                   
                }
            }
        }
        assertEquals(contWaterExpected, contWater);
        assertEquals(contGroundExpected, contGround);
    }
    @Test
    public void GroundboxTest(){
        mapTest.CreateMap();
        for(int i = 1;i<39;i++){
            for(int j=1;j<39;j++){
                temp = mapTest.ReturnBox(i, j);
                if(temp instanceof GroundBox){
                        if(((GroundBox) temp).isVegetationPres()!= null){
                            vegetationCont++;
                            vegetationRest--;
                        }
                        else{
                            if(((GroundBox)temp).isCarrionPres()!= null){
                                carrionCont++;
                            } 
                        }
                }
            }
        }
        assertEquals(vegetationExpected, vegetationCont);
        assertEquals(carrionExpected, carrionCont);
        assertEquals(vegetationRestExpected, vegetationRest);
    }
    @Ignore
    @Test
    public void OceanTest(){
        mapTest.CreateMap();
        mapTest.SetOcean();
        for(int i= 0; i<40;i++){
            for(int j=0;j<40;j++){
                if(i==0 || j==0 || i==39 || j==39){
                    assertTrue(mapTest.ReturnBox(i, j) instanceof WaterBox);
                    assertFalse(mapTest.returnAvailable().contains(mapTest.ReturnBox(i, j)));   
                }
            }
        }
    }
}