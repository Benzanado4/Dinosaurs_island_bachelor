package it.polimi.dinosaursisland.exceptions;

public class GrowthException extends Exception{    
    private int newSize;
    public GrowthException(int size){
        newSize=size;
    }
    public int returnEnergy(){
        return newSize;
    }
}
