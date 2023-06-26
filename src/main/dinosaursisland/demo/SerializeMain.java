package it.polimi.dinosaursisland.demo;

import it.polimi.dinosaursisland.mappa.SerializeMap;
import it.polimi.dinosaursisland.partita.Game;
import it.polimi.dinosaursisland.serializazion.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import it.polimi.dinosaursisland.mappa.*;


public class SerializeMain {
    public static void main(String[] args) throws FileNotFoundException, IOException, NotSerializableException {
        Map map = new Map();
        map.CreateMap();
        Serialize serialize = new Serialize();
        serialize.SerializeIt(map);
    }
}
