package it.polimi.dinosaursisland.exceptions;

import it.polimi.dinosaursisland.dinosauri.*;

public class TypeDeathException extends Exception {    
    private Type type;
    public TypeDeathException(Type type){
        this.type=type;
    }
    public Type returnDeadType(){
        return type;
    }
}
