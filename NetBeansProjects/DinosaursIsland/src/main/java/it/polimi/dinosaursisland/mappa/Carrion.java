package it.polimi.dinosaursisland.mappa;

import java.io.Serializable;
import java.util.Random;

/**
 * Classe che rappresenta la carogna.
 */
public class Carrion implements Serializable {

    /*Attributi*/
    private int maxEnergy = 350 + (int) (Math.random() * 300);
    private int energy = maxEnergy;
    private int x;
    private int y;
    private int reduceConstant = (maxEnergy * 20 / 100);
    
    /*Costruttori*/
    public Carrion() {
    }

    public Carrion(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /*Metodi*/
    
    /**Riduce l'energia della carogna**/
    public boolean reduceEnergy() {
        energy = energy - reduceConstant;
        if (energy <= 0) {
            return false;
        }
        return true;
    }
    /**Ritorna l'energia della carogna**/
    public int returnEnergy() {
        return energy;
    }
    /**Setta l'energia della carogna**/
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
