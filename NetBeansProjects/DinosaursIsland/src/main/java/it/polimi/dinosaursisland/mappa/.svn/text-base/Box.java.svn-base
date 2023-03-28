package it.polimi.dinosaursisland.mappa;

import java.awt.Point;
import java.io.Serializable;

/**
 * Classe che rappresenta la singola casella.
 */
public class Box extends Point implements Serializable {

    /* Attributi */
    protected boolean walkable;

    /* Costruttori */
    public Box() {
    }

    public Box(int x, int y) {
        super.x = x;
        super.y = y;
    }

    public Box(Box box) {
        super.setLocation(box);
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public int returnX() {
        return (int) super.x;
    }

    public int returnY() {
        return (int) super.y;
    }   
}
