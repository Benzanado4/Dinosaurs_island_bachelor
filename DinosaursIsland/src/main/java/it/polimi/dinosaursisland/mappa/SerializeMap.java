package it.polimi.dinosaursisland.mappa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

public class SerializeMap {
    public void Serialize(Object object){
        File mapFile = new File("mappa.txt");
        ObjectOutputStream oos = null;
        try{
            FileOutputStream fos = new FileOutputStream(mapFile);
            oos = new ObjectOutputStream(fos);
        } 
        catch (FileNotFoundException event){
            event.printStackTrace();
        }
        catch(IOException ioEvent){
            ioEvent.printStackTrace();
        }
        try{
            oos.writeObject(object);
        } 
        catch(IOException ioEvent1){
            ioEvent1.printStackTrace();
        }
        try{
            oos.close();
        }
        catch(IOException ioEvent2){
            ioEvent2.printStackTrace();
        }
    }
}
