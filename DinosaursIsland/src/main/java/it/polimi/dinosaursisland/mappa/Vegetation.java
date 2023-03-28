package it.polimi.dinosaursisland.mappa;

import java.io.Serializable;

public class Vegetation implements Serializable{
    private int maxEnergy = 150 + (int) (Math.random() * 200);
    private int energy = maxEnergy;
    private int growConstant = (maxEnergy*20/100);
    public int ReturnEnergy(){
        return energy;
    }
    public void GrowEnergy(){
        if (energy < maxEnergy){
            if ((energy + growConstant) > maxEnergy){
                energy = maxEnergy;
            }
            energy = energy + growConstant;
        }
    }
    public void setEnergy(int energy){
        this.energy = energy;
    }
}
