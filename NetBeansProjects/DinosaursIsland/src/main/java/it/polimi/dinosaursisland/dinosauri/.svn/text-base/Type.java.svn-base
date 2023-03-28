package it.polimi.dinosaursisland.dinosauri;

import java.util.ArrayList;
import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.partita.Player;
import java.io.Serializable;

/**
 * La classe della specie.
 */
public class Type implements Serializable {

    protected int numDinosaur;
    protected ArrayList<Dinosaur> dinos = new ArrayList<Dinosaur>(0);
    protected String name;
    protected ArrayList<Box> fullVisual = new ArrayList(0);
    protected ArrayList<Box> exploredVisual = new ArrayList(0);
    private int actionCounter = 2;
    private int gameTime = 120;
    private boolean exist = true;

    /**Ritorna il dinosauro all'indice passato come argomento*/
    public Dinosaur returnDinosaur(int i) {
        return dinos.get(i);
    }
    
    /**Ritoran l'Arraylist dei dinosauri della specie*/
    public ArrayList<Dinosaur> returnDinosaurs() {
        return dinos;
    }
    public int returnNumDinosaur(){
        return numDinosaur;
    }
    /**Ritorna il nome della specie*/
    public String returnName() {
        return name;
    }

    /**Setta il nome della specie*/
    public void setName(String name) {
        this.name = name;
    }

    /**Trova all'interno della specie il dinosauro con il nome passato come argomento*/
    public Dinosaur findDinosaur(String name) {
        for (int i = 0; i < dinos.size(); i++) {
            if (dinos.get(i).returnName().equals(name)) {
                return dinos.get(i);
            }
        }
        return null;
    }

    /**Aggiorna la vista globale della specie e setta quella esplorata*/
    public void setFullVisual() {
        fullVisual.clear();
        for (int i = 0; i < dinos.size(); i++) {
            fullVisual.addAll(dinos.get(i).returnVisual());
        }
        setExploredVisual();    
    }

    /**Aggiorna la porzione di mappa esplorata dalla specie*/
    public void setExploredVisual() {
        for (int i = 0; i < fullVisual.size(); i++) {
            if (!exploredVisual.contains(fullVisual.get(i))) {
                exploredVisual.add(fullVisual.get(i));
            }
        }
    }

    /**Controlla se il box passato come paramentro è nella visuale della specie*/
    public boolean isInFullVisual(Box box) {
        if (fullVisual.contains(box)) {
            return true;
        }
        return false;
    }

    /**Controlla se il box passato come paramentro è nella mappa esplorata della specie*/
    public boolean isInExploredVisual(Box box) {
        if (exploredVisual.contains(box)) {
            return true;
        }
        return false;
    }

    /**Ritorna un arraylist con tutte le caselle nella visuale della specie*/
    public ArrayList<Box> returnFullVisual() {
        return fullVisual;
    }
    public ArrayList<Box> returnExploredVisual(){
        return exploredVisual;
    }
    
    /**Aggiunge un nuovo dinosauro alla specie se non sono gia 5*/
    public Dinosaur newDinosaur(String name, Player player) {
        if (dinos.size() < 5) {
            if (this instanceof CarnivorousType) {
                Carnivorous temp = new Carnivorous(name);
                temp.setPlayer(player);
                dinos.add(temp);
                numDinosaur++;
                actionCounter = actionCounter + 2;
                return temp;
            }
            if (this instanceof ErbivorousType) {
                Erbivorous temp = new Erbivorous(name);
                temp.setPlayer(player);
                dinos.add(temp);
                numDinosaur++;
                actionCounter = actionCounter + 2;
                return temp;
            }
        }
        return null;
    }
    public Dinosaur newDinosaur2(Dinosaur dino, Player player) {
        if (dinos.size() < 5) {
            if (this instanceof CarnivorousType) {
                dino.setPlayer(player);
                dinos.add(dino);
                numDinosaur++;
                actionCounter = actionCounter + 2;
                return dino;
            }
            if (this instanceof ErbivorousType) {
                dino.setPlayer(player);
                dinos.add(dino);
                numDinosaur++;
                actionCounter = actionCounter + 2;
                return dino;
            }
        }
        return null;
    }

    /**Rimuove il dinosauro dalla specie e ritorna false se la specie non ha piu dinosauri*/
    public boolean dinosaursDead(Dinosaur dino) {
        dinos.remove(dino);
        numDinosaur--;
        actionCounter = actionCounter - 2;
        setFullVisual();
        if (!dinos.isEmpty()) {
            return true;
        }
        return false;

    }

    /**Decrementa il numero di mosse disponibili per la specie*/
    public void doneAction() {
        actionCounter--;
    }

    public int returnAvailableAction() {
        return actionCounter;
    }

    public void decrementTime() {
        gameTime--;
    }

    public int leftTime() {
        return gameTime;
    }

    public void setAction() {
        actionCounter = 0;
        for (int i = 0; i < dinos.size(); i++) {
            actionCounter = actionCounter + 2;
            dinos.get(i).setAction();
        }
    }

    public void extinguish() {
        exist = false;
    }

    public boolean exist() {
        return exist;
    }
    
}
