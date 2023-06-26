package it.polimi.dinosaursisland.exceptions;

import it.polimi.dinosaursisland.dinosauri.Dinosaur;

public class DinoDeathException extends Exception{
    private Dinosaur dino;
    public DinoDeathException(Dinosaur dino){
        this.dino=dino;
    }   
    public Dinosaur returnDeadDino(){
        return dino;
    }
}
