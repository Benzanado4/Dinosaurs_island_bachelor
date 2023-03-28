package it.polimi.dinosaursisland.dinosauri;

/**
 * La classe che rappresenta il dinosauro carnivoro.
 */

public class Carnivorous extends Dinosaur {
    
/*Costruttori*/
    public Carnivorous(int x, int y, String name) {
        super(x, y, name);
        strength = 1500;
        n = 3;
    }

    public Carnivorous(String name) {
        super(name);
        strength = 1500;
        n = 3;
    }

    public Carnivorous(int x, int y) {
        super(x, y);
        strength = 1500;
        n = 3;
    }
}