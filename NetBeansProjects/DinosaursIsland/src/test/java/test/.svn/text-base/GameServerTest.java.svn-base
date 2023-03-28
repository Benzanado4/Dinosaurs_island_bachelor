
package test;

import it.polimi.dinosaursisland.networking.Command;
import it.polimi.dinosaursisland.networking.Decoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameServerTest {
    
    
    public GameServerTest() {}
    
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
    
    
}
