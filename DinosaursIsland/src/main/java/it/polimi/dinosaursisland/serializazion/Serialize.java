package it.polimi.dinosaursisland.serializazion;

import java.io.Serializable;    
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Serialize implements Serializable{
        public String SerializeIt(Object object){
        try{
            FileOutputStream out = new FileOutputStream("mappaTest.txt");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(object);
            oos.close();
            return "mappaTest.txt";
            
            }catch(FileNotFoundException f){
            f.printStackTrace();         
            }catch(IOException e){
            e.printStackTrace();
            }
        return null;
    } 
}
