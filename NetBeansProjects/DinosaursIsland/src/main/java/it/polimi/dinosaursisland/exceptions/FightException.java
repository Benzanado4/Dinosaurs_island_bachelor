
package it.polimi.dinosaursisland.exceptions;

import it.polimi.dinosaursisland.dinosauri.Dinosaur;


public class FightException extends Exception {
    private Dinosaur dino;
    
    public FightException(Dinosaur dino){
        this.dino=dino;
    }
            
    public Dinosaur returnDeadDino(){
        return dino;
    }
    
}
