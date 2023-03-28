package it.polimi.dinosaursisland.exceptions;

public class EatException extends Exception{
    private int newEnergy;
    public EatException(int en){
        newEnergy=en;
    }
    public int returnSize(){
        return newEnergy;
    }
}
