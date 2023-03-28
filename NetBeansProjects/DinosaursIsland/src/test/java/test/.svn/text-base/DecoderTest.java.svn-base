
package test;

import org.junit.Ignore;
import it.polimi.dinosaursisland.networking.Command;
import it.polimi.dinosaursisland.networking.Decoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Junit4 della classe Decoder
 */
public class DecoderTest {
    
    public DecoderTest() {
    }
    
    private static Decoder decoder;
    private static Command command;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        decoder = new Decoder();
        command = new Command("@comandoErrato");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        decoder = null;
        command = null;
    }
    
   
    @Test
    public void decoderTest(){
        Command commandExpected = command;
        Command commandExpected1 = new Command("@ok");
        assertEquals(commandExpected.returnCommand(),decoder.decodeCommand("ok").returnCommand());
        assertNull(decoder.decodeCommand(null));
        assertEquals(commandExpected1.returnCommand(),decoder.decodeCommand("@ok").returnCommand());
    }
    
    @Test
    public void DecoderTest2(){
       
        Command command1Expected = new Command("@loginUtente");
        Command command2Expected = new Command("@creaUtente");
        
        Command command1 = decoder.decodeCommand("@creaUtente,io,tu");
        Command command2 = decoder.decodeCommand("@loginUtente,io,tu");
        
        assertEquals(command1Expected.returnCommand(),decoder.decodeCommand("@loginUtente,b,b").returnCommand());
        System.out.println(command2.returnToken());
    }
    
    @Ignore
    @Test
    public void DecoderTest3(){
        Command command1Expected = new Command("@creaRazza");
    //    assertEquals(command1Expected.returnToken(),command1Expected.returnToken()));
        
        
    }
    @Ignore
    @Test
    public void DecoderTest4(){
        Command command1Expected = new Command("@creaUtente");
        assertEquals(command1Expected.returnCommand(),decoder.decodeCommand("@creaUtente,b,b").returnCommand());
        
    }
    @Test
    public void DecoderTest5(){
        Command command1Expected = new Command("@creaUtente");
        assertEquals(command1Expected.returnCommand(),decoder.decodeCommand("@creaUtente,b,b").returnCommand());
        
    }
}
