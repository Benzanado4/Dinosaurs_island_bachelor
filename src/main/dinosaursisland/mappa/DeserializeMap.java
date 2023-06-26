package it.polimi.dinosaursisland.mappa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeMap {
    public static void main(String[] args){
        ObjectInputStream input = null;
        Map map = null;
        
        try{
            input = new ObjectInputStream(new FileInputStream("mappafine.txt"));
        }
        catch(FileNotFoundException event){
            event.printStackTrace();
        }
        catch(IOException ioEvent){
            ioEvent.printStackTrace();
        }
        try{
            map = (Map)input.readObject();
        }
        catch(IOException ioEvent1){
            ioEvent1.printStackTrace();
        }
        catch (ClassNotFoundException event1){
            event1.printStackTrace();
        }
        map.PrintMap();
    }
}
