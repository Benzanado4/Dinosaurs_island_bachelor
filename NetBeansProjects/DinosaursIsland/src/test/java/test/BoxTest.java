
package test;

import it.polimi.dinosaursisland.mappa.Box;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit4 test di Box
 */
public class BoxTest {
    
    public BoxTest() {
    }
    
    private static Box box;
    @BeforeClass
    public static void setUpClass() throws Exception {
         box = new Box(12,13);
    
    }
    @AfterClass
    public static void tearDownClass() throws Exception {
        box = null;
    }
    
    @Test
    public void returnTest(){
        int xExpected = 12;
        int yExpected = 13;
        assertEquals(xExpected,box.returnX());
        assertEquals(yExpected,box.returnY());
    }        
    
    @Test
    public void setWalkableTest(){
        assertFalse(box.isWalkable());
        box.setWalkable(true);
        assertTrue(box.isWalkable());
    }        
}
