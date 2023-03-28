package it.polimi.dinosaursisland.mappa;

/**
 * Classe che rappresenta l'acqua.
 */

public class WaterBox extends Box {

    /* Costruttori */
    public WaterBox(int x, int y) {
        super.setLocation(x, y);
        super.walkable = false;
    }

    public WaterBox(Box box) {
        if (box != null) {
            super.setLocation(box);
        }

    }
}
