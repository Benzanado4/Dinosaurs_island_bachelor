package it.polimi.dinosaursisland.exceptions;

import it.polimi.dinosaursisland.dinosauri.Dinosaur;

public class EggDeposedException extends Exception{
    private Dinosaur egg;
    public EggDeposedException(Dinosaur egg){
        this.egg=egg;
    }
    public Dinosaur returnEgg(){
        return egg;
    }
}
