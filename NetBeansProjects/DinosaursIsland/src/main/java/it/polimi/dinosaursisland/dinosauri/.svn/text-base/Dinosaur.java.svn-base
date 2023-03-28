package it.polimi.dinosaursisland.dinosauri;

import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.partita.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 * La classe che rappresenta il Dinosauro
 */
public class Dinosaur implements Serializable {

    /*Attributi*/
    protected String name;
    private int x, y;
    private int energy = 750    ;
    private int size = 1;
    private int maxEnergy = 1000;
    private int age = 0;
    private int lifeSpan = 24 + (int) (Math.random() * 12);
    protected int strength = 750;
    private int visual = 2;
    protected int n;
    private ArrayList<Box> visualBox = new ArrayList(0);
    private boolean movementAction = true;
    private boolean otherAction = true;
    private Player player;
    private int numColumn = 0;
    private int numRow = 0;


    /*Costruttori*/
    public Dinosaur(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Dinosaur(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public Dinosaur(String name) {
        this.name = name;
    }

    public Dinosaur(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*Metodi principali*/
    /**
     * Ritorna l'energia del dinosauro.
     *
     * @param   nessuno.
     * @return  energy.
     */
    public int returnEnergy() {




        return energy;
    }
    
    public int returnLifeSpan() {
        return lifeSpan;
    }
    /**
     * Ritorna la dimensione del dinosauro.
     *
     * @param   nessuno.
     * @return  size.
     */
    public int returnSize() {
        return size;
    }

    public int returntAge() {
        return age;
    }
    /**
     * Ritorna la forza del dinosauro.
     *
     * @param   nessuno.
     * @return  strenght.
     */
    public int returnStrenght() {
        if (this instanceof Carnivorous) {
            return 2 * size * energy;
        }

        return size * energy;

    }

    public String returnName() {
        return name;
    }

    public Box returnPos() {
        return new Box(x, y);
    }
    /**
     * Ritorna la visuale del dinosauro.
     *
     * @param   nessuno.
     * @return  visualBox.
     */
    public ArrayList<Box> returnVisual() {

        return visualBox;
    }
    
    public int returnVisualDim() {
        return visual;
    }
    /**
     * Ritorna il num di Righe della matrice per poi visualizzare la visualeLocale del dinosauro.
     *
     * @param   nessuno.
     * @return  numRow.
     */
    public int returnNumRow() {
        return numRow;
    }
    
    /**
     * Ritorna il num di Colonne della matrice per poi visualizzare la visualeLocale del dinosauro.
     *
     * @param   nessuno.
     * @return  numColumn.
     */
    public int returnNumColumn() {
        return numColumn;
    }

    public boolean isInVisual(Box box) {
        if (visualBox.contains(box)) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void emptyVisual() {
        visualBox.clear();
        numColumn = 0;
        numRow = 0;
    }

    /**Setta in visualBox le caselle visibili dal dinosauro*/
    public void setVisual(Map map) {

        emptyVisual();


        int startX = x - visual;
        int endX = x + visual;
        int startY = y - visual;
        int endY = y + visual;
        int flag = 0;

        if (startX <= 0) {
            startX = 0;
        }
        if (visual > (39 - x)) {
            endX = 39;
        }
        if (startY <= 0) {
            startY = 0;
        }
        if (visual > (39 - y)) {
            endY = 39;
        }

        for (int i = startX; i <= endX; i++) {
            numRow++;
            for (int j = startY; j <= endY; j++) {
                visualBox.add(map.returnBox(i, j));
                if (flag == 0) {
                    numColumn++;
                }
            }
            flag = 1;
        }

    }

    public void setPosition(Box box) {
        x = box.returnX();
        y = box.returnY();
    }

    /**Incrementa l'energia del dinosauro se questa non e' gia' al massimo livello. Ritorna l'energia avanzata se si super il massimo, 0 altrimenti 
     *
     * @return remainedEnergy or 0
     */
    public int incrementEnergy(int energy) {
        if ((this.energy + energy) > maxEnergy) {
            int remainedEnergy = energy - (maxEnergy - this.energy);
            this.energy = maxEnergy;
            return remainedEnergy;

        }

        this.energy = this.energy + energy;
        return 0;
    }

    /** crescere il dinosauro di livello e ritorna la dimensione se il dinosauro e' cresciuto, 0 se muore.*/
    public int growth() {
        if (size < 5) {
            if (energy >= maxEnergy / 2) {
                energy = energy - (maxEnergy / 2);
                size++;
                maxEnergy = maxEnergy + 1000;
                if (size == 2 || size == 3) {
                    visual = 3;
                }
                if (size == 4 || size == 5) {
                    visual = 4;
                }
                return size;
            }
            return 0;
        }
        return size;
    }

    /**Ritorna true se è possibile deporre un uovo e decremente l'energia,false altrimenti**/
    public boolean layEgg() {
        if (energy >= 1500) {
            energy = energy - 1500;
            return true;
        }
        return false;
    }

    /**Ritorna il riferimento del dinosauro che perde il combattimento*/
    public Dinosaur fight(Dinosaur dino) {
        if (dino.equals(this)) {
            return null;
        }
        if (strength >= dino.returnStrenght()) {
            if (this instanceof Carnivorous) {
                energy = energy + (int) 0.75 * dino.returnEnergy();
            }
            return dino;

        }
        if (dino instanceof Carnivorous) {
            dino.incrementEnergy((int) 0.75 * energy);
            return this;
        }
        return this;
    }

    /**Controlla se il dinosauro si puo muovere nel box passato come parametro, 
     * altrimenti ritorna null 
     * oppure il riferimento al dinosauro se non aveva l'energia sufficiente**/
    public Object movement(Map map, Box box) {

        int startX = x - n;
        int endX = x + n;
        int startY = y - n;
        int endY = y + n;

        if (box.returnX() < 0 || box.returnX() > 39 || box.returnY() < 0 || box.returnY() > 39) {
            return null;
        } else {

            if (box.returnX() == x && box.returnY() == y) {
                return null;
            } else {

                if (box instanceof GroundBox) {

                    if (this instanceof Carnivorous || (this instanceof Erbivorous && ((GroundBox) box).isDinosaurPres() == null)) {

                        if (startX <= 0) {
                            startX = 0;
                        }
                        if (n > (39 - x)) {
                            endX = 39;
                        }
                        if (startY <= 0) {
                            startY = 0;
                        }
                        if (n > (39 - y)) {
                            endY = 39;
                        }

                        if (box.returnX() >= startX && box.returnX() <= endX
                                && box.returnY() >= startY && box.returnY() <= endY) {

                            int distance = Math.max(Math.abs(this.x - box.returnX()), Math.abs(this.y - box.returnY()));
                            int energyRequired = (int) (10 * Math.pow(2, distance));

                            if (energy <= energyRequired) {

                                return this;

                            } else {

                                for (int i = -1; i <= 1; i++) {

                                    for (int j = -1; j <= 1; j++) {
                                        Box temp1 = map.returnBox(x, y);
                                        if (i == 0 && j == 0) {
                                        } else {
                                            Box temp = map.returnBox(temp1.returnX() + i, temp1.returnY() + j);

                                            if (temp instanceof GroundBox) {
                                                if (temp.returnX() == box.returnX() && temp.returnY() == box.returnY()) {

                                                    energy = energy - energyRequired;
                                                    setVisual(map);
                                                    return map.returnBox(x, y);
                                                }


                                                for (int h = -1; h <= 1; h++) {
                                                    for (int k = -1; k <= 1; k++) {
                                                        if (h == 0 && k == 0) {
                                                        } else {
                                                            Box temp2 = map.returnBox(temp.returnX() + h, temp.returnY() + k);
                                                            if (temp2 instanceof GroundBox) {
                                                                if (temp2.returnX() == box.returnX() && temp2.returnY() == box.returnY()) {

                                                                    energy = energy - energyRequired;
                                                                    x = temp2.returnX();
                                                                    y = temp2.returnY();
                                                                    setVisual(map);
                                                                    return map.returnBox(x, y);
                                                                } else {
                                                                    if (this instanceof Carnivorous) {

                                                                        for (int p = -1; p <= 1; p++) {
                                                                            for (int q = -1; q <= 1; q++) {
                                                                                if (p == 0 && q == 0) {
                                                                                } else {
                                                                                    Box temp3 = map.returnBox(temp2.returnX() + p, temp2.returnY() + q);
                                                                                    if (temp3 instanceof GroundBox) {
                                                                                        if (temp3.returnX() == box.returnX() && temp3.returnY() == box.returnY()) {

                                                                                            energy = energy - energyRequired;
                                                                                            x = temp3.returnX();
                                                                                            y = temp3.returnY();
                                                                                            return map.returnBox(x, y);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }

                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }

                        }
                    } else {
                        return null;
                    }

                }

            }
        }
        return null;
    }

    public void setRandomPosition(Map map) {
        x = (int) (Math.random() * 39);
        y = (int) (Math.random() * 39);
        while (map.returnBox(x, y) instanceof WaterBox && ((GroundBox) map.returnBox(x, y)).isDinosaurPres() != null) {
            x = (int) (Math.random() * 39);
            y = (int) (Math.random() * 39);
        }
        this.x = x;
        this.y = y;
    }

    /**trova una casella disponibile il piu vicino possibile al dinosauro*/
    public Box returnAdjacentPosition(Map map) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (map.returnBox(x + i, y + j) instanceof GroundBox && ((GroundBox) map.returnBox(x + i, y + j)).isDinosaurPres() == null) {
                    return map.returnBox(x + i, y + j);
                }
            }
        }
        return null;
    }

    public boolean returnMovementAction() {
        return movementAction;
    }

    public boolean returnOtherAction() {
        return otherAction;
    }

    public void doneMovementAction() {
        movementAction = false;
    }

    public void doneOtherAction() {
        otherAction = false;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player returnPlayer() {
        return player;
    }
    
    /**Resetta le possibili azioni del dinosauro**/
    public void setAction() {
        movementAction = true;
        otherAction = true;
    }

    /**Aumenta l' età del dinosauro**/
    public void decrementLife() {
        age++;
    }

    /**Setta l'energia del dinosauro, metodo utile per il Test**/
    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
