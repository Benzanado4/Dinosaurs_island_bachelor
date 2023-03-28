package it.polimi.dinosaursisland.exceptions;

import javax.print.attribute.Size2DSyntax;

public class GrowDinosaurException extends Exception{
    private int newSize;
    public GrowDinosaurException(int newSize){
        this.newSize=newSize;
    }
    public int returnNewSize(){
        return newSize;
    }
}
