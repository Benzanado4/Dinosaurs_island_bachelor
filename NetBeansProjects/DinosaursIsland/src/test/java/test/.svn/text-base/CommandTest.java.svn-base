
package test;

import java.util.ArrayList;
import it.polimi.dinosaursisland.networking.Command;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit4Test della classe Command
 */
public class CommandTest {
    
    private  ArrayList <String> parameters ;
    private  Command commandTest;
    
    public CommandTest() {}
    /*
    @BeforeClass
    public static void setUpClass() throws Exception {
        parameters.add("name");
        parameters.add("password");
        commandTest = new Command("@creaUtente","T0123456789",parameters);
    
    }

    
    @AfterClass
    public static void tearDownClass() throws Exception {
        commandTest = null;
    }
    */
    @Before
    public void setUp() {
       parameters = new ArrayList<String>(0); 
       parameters.add("name");
       parameters.add("password");
       commandTest = new Command("@creaUtente","T0123456789",parameters);
    }
    
    @After
    public void tearDown() {
        parameters = null;
        commandTest = null;
    }
     
    
    @Test
    public void InitTest(){
    
        String  commandExpected = "@creaUtente";
        String tokenExpected = "T0123456789";
        int sizeExpected = 2;
        assertEquals(commandExpected,commandTest.returnCommand());
        assertEquals(tokenExpected,commandTest.returnToken());
        assertEquals(sizeExpected,commandTest.returnParameters().size());
        
    }
    
    @Test
    public void test(){
        String paramExpected = "password";
        assertEquals(paramExpected,commandTest.returnParameter(1));
    }

}
