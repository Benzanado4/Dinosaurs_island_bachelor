package it.polimi.dinosaursisland.mappa;

import java.util.ArrayList;
import it.polimi.dinosaursisland.dinosauri.*;

/**
 * Classe che rappresenta la casella di terra.
 */
public class GroundBox extends Box {

    private Dinosaur dinosaurPres = null;
    private Vegetation vegetationPres = null;
    private Carrion carrionPres = null;
    private ArrayList<Box> adjacentBoxes;

    /* Costruttori */
    public GroundBox(int x, int y) {
        super.setLocation(x, y);
        super.walkable = true;
    }

    /** Crea un Box su cui e' presente una Carogna **/
    public GroundBox(int x, int y, Carrion carrion) {
        super.setLocation(x, y);
        super.walkable = true;
        carrionPres = carrion;
    }

    /** Crea un Box con le coordinate di un altro**/
    public GroundBox(Box box) {
        super.setLocation(box.returnX(), box.getY());


    }

    public GroundBox(GroundBox box) {
        super.setLocation(box.returnX(), box.returnY());
        vegetationPres = box.isVegetationPres();

    }

    /* Metodi per settare il box*/
    public Dinosaur setDinosaurPres(Dinosaur dino) {
        if (dinosaurPres != null) {
            return dinosaurPres;
        }
        dinosaurPres = dino;
        return dinosaurPres;

    }

    public void moveDinosaurAway() {
        dinosaurPres = null;
    }

    public Carrion setCarrionPres(Carrion carrion) {
        carrionPres = carrion;
        return carrion;
    }

    public Vegetation setVegetationPres(Vegetation vegetation) {
        vegetationPres = vegetation;
        return vegetation;
    }

    /* Metodi per leggere lo stato del box */
    
    /** Ritorna il Dinosauro presente sulla casella di terra oppure null se non è presente**/
    public Dinosaur isDinosaurPres() {
        return dinosaurPres;
    }
    /** Ritorna la vegetazione presente sulla casella di terra oppure null se non è presente**/
    public Vegetation isVegetationPres() {
        return vegetationPres;
    }
    /** Ritorna la carogna presente sulla casella di terra oppure null se non è presente**/
    public Carrion isCarrionPres() {
        return carrionPres;
    }
}
