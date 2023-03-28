package figure;

import junit.framework.TestCase;

public class CerchioTest extends TestCase {
    public CerchioTest(String testName) {
        super(testName);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    /**
     * Test of getRaggio method, of class Cerchio.
     */
    public void testGetRaggio() {
        System.out.println("getRaggio");
        Cerchio instance = null;
        double expResult = 0.0;
        double result = instance.getRaggio();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
    /**
     * Test of setRaggio method, of class Cerchio.
     */
    public void testSetRaggio() {
        System.out.println("setRaggio");
        double raggio = 0.0;
        Cerchio instance = null;
        instance.setRaggio(raggio);
        fail("The test case is a prototype.");
    }
    /**
     * Test of getCirconferenza method, of class Cerchio.
     */
    public void testGetCirconferenza() {
        System.out.println("getCirconferenza");
        Cerchio instance = null;
        double expResult = 0.0;
        double result = instance.getCirconferenza();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
    /**
     * Test of getArea method, of class Cerchio.
     */
    public void testGetArea() {
        System.out.println("getArea");
        Cerchio instance = null;
        double expResult = 0.0;
        double result = instance.getArea();
        assertEquals(expResult, result, 0.0);
        fail("The test case is a prototype.");
    }
    /**
     * Test of printDescrizione method, of class Cerchio.
     */
    public void testPrintDescrizione() {
        System.out.println("printDescrizione");
        Cerchio instance = null;
        instance.printDescrizione();
        fail("The test case is a prototype.");
    }
}