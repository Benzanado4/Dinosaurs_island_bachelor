package it.polimi.dinosaursisland.serializazion;

import java.io.Serializable;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ClassNotFoundException;


public class DeSerialize implements Serializable{
    public Object DeSerializeIt(String nome){
        try{
            FileInputStream in = new FileInputStream(nome);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object object = (Object) ois.readObject();
            ois.close();
            return object;
        }catch(FileNotFoundException f){
            f.printStackTrace();         
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException c){
            c.printStackTrace();
        }
        return null;
    }   
}