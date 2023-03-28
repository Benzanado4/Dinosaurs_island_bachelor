package it.polimi.dinosaursisland.mappa;

import java.io.Serializable;
import java.util.Random;

public class Carrion implements Serializable{
    /* Attributes */
    private int maxEnergy = 350 + (int) (Math.random() * 300);
    private int energy = maxEnergy;
    private int x;
    private int y;
    private int reduceConstant = (maxEnergy*20/100);

    /* Constructors */
    public Carrion(){}
    public Carrion(int x, int y){
        this.x = x;
        this.y = y;
    }

    /* Methods */
    public boolean ReduceEnergy(){
        energy = energy - reduceConstant;
        if (energy <= 0){
            return false;
        }
        return true;
    }
    public int ReturnEnergy(){
        return energy;
    }
    public void setEnergy(int energy){
        this.energy = energy;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
