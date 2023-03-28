package it.polimi.dinosaursisland.mappa;

import java.util.ArrayList;
import it.polimi.dinosaursisland.dinosauri.*;

public class GroundBox extends Box {
    /* Attributes */
    private Dinosaur dinosaurPres = null;
    private Vegetation vegetationPres = null;
    private Carrion carrionPres = null;
    private ArrayList<Box> adjacentBoxes;

    /* Constructors */
    public GroundBox(int x, int y){
        super.setLocation(x, y);
        super.walkable = true;
    }
    
    /*Methods*/
    public GroundBox(int x, int y, Carrion carrion){
    // Creates a Box in which there is a carrion
        super.setLocation(x, y);
        super.walkable = true;
        carrionPres = carrion;
    }
    public GroundBox(Box box){
    // Creates a Box with the coordinates of another one
        super.setLocation(box.getX(), box.getY());
    }
    public GroundBox(GroundBox box){
        super.setLocation(box.getX(), box.getY());
        vegetationPres = box.isVegetationPres();
    }

    public Dinosaur setDinosaurPres(Dinosaur dino){
        if (dinosaurPres != null){
            return dinosaurPres;
        }
        dinosaurPres = dino;
        return dinosaurPres;

    }
    public void moveDinosaurAway(){
        dinosaurPres = null;
    }
    public Carrion setCarrionPres(Carrion carrion){
        carrionPres = carrion;
        return carrion;
    }
    public Vegetation setVegetationPres(Vegetation vegetation){
        vegetationPres = vegetation;
        return vegetation;
    }

    public Dinosaur isDinosaurPres(){
        return dinosaurPres;
    }
    public Vegetation isVegetationPres(){
        return vegetationPres;
    }
    public Carrion isCarrionPres(){
    //Returns null if there is not a carriot, otherwise returns the reference to the carrion present in the tile
        return carrionPres;
    }
}
