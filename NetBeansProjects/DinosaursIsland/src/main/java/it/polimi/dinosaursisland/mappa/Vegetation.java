package it.polimi.dinosaursisland.mappa;

/**
 * Classe che rappresenta la vegetazione.
 */

import java.io.Serializable;

public class Vegetation implements Serializable {

    private int maxEnergy = 150 + (int) (Math.random() * 200);
    private int energy = maxEnergy;
    private int growConstant = (maxEnergy * 20 / 100);

    public int returnEnergy() {
        return energy;
    }

    public void growEnergy() {
        if (energy < maxEnergy) {
            if ((energy + growConstant) > maxEnergy) {
                energy = maxEnergy;
            }
            energy = energy + growConstant;
        }
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
