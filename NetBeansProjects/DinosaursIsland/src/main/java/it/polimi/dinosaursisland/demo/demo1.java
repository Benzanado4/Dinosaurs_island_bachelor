
package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.dinosauri.Type;
import it.polimi.dinosaursisland.mappa.Map;
import it.polimi.dinosaursisland.partita.Game;
import it.polimi.dinosaursisland.partita.GameController;
import it.polimi.dinosaursisland.partita.Player;
import it.polimi.dinosaursisland.serializazion.DeSerialize;

public class demo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DeSerialize deserialize = new DeSerialize();
        Map map = (Map)deserialize.DeSerializeIt("mappaTest.txt");
        Game gameTest = new Game(map);
        Player playerTest = new Player("bella","password");
        Player playerTest1 = new Player("bella","passw");
        Player playerTest2 = new Player("bella2","passw");
        GameController gameController = new GameController(gameTest);
        Type type = new Type();
    
        gameTest.newRegister(playerTest.returnId(), "password");
        gameTest.newRegister(playerTest1.returnId(), "passw");
        gameTest.newRegister(playerTest2.returnId(), "passw");
        
        System.out.println(gameTest.returnPlayerList().get(0).returnId());  
        System.out.println(gameTest.returnPlayerList().get(1).returnId()); 
        
        
        Player temp = gameTest.isPlayerPres(playerTest.returnId()) ;
        
        if(temp.equals(playerTest)){
            System.out.println("d");    
    }
    }
}
