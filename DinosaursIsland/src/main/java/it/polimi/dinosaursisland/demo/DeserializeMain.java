package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.mappa.Map;
import it.polimi.dinosaursisland.partita.Game;
import it.polimi.dinosaursisland.serializazion.DeSerialize;

public class DeserializeMain {
    
    public static void main(String[] args){
        Game game = new Game ();
        Map map = null;
        DeSerialize deserialize = new DeSerialize();
        game = deserialize.DeSerializeIt("it.polimi.dinosaursisland.partita.Game@7f12f614");
        map = (Map)deserialize.DeSerializeIt("mappaTest.txt");
        map.PrintMap();
        
    }
}