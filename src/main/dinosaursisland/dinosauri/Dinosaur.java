package it.polimi.dinosaursisland.dinosauri;

import it.polimi.dinosaursisland.mappa.*;
import it.polimi.dinosaursisland.partita.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
/**
 * Dinosaur Class
 */
public class Dinosaur implements Serializable{

    /*Attributes*/
    protected String name;
    private int x, y;
    private int energy = 1800;
    private int size = 1;
    private int maxEnergy = 1000;
    private int age;
    private int lifeSpan = 24 + (int) (Math.random() * 12);
    protected int strength = 750;
    private int visual = 2;
    protected int n;
    private ArrayList<Box> VisualBox = new ArrayList(0);
    private boolean movementAction = true;
    private boolean otherAction = true;
    private Player player;
    private int numColumn = 0;
    private int numRow = 0;
        
    /*Constructors*/
    public Dinosaur(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public Dinosaur(String name, Player player){
        this.name = name;
        this.player = player;
    }
    public Dinosaur(String name){
        this.name = name;
    }
    public Dinosaur(int x, int y){
        this.x = x;
        this.y = y;
    }

    /*Methods for attributes*/
    /**
     * Return the energy of the Dinosaur.
     *
     * @param   nothing.
     * @return  energy.
     */
    
    public int ReturnEnergy(){
        return energy;
    }
    public int ReturnLifeSpan(){
        return lifeSpan;
    }
    public int ReturnSize(){
        return size;
    }
    public int ReturntAge(){
        return age;
    }
    public int ReturnStrenght(){
        if (this instanceof Carnivorous){
            return 2 * size * energy;
        }

        return size * energy;

    }
    public String ReturnName(){
        return name;
    }
    public Box ReturnPos(){
        return new Box(x, y);
    }
    public ArrayList<Box> returnVisual(){
        
        return VisualBox;
    }
    public boolean isInVisual(Box box){
        if (VisualBox.contains(box)){
            return true;
        }
        return false;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
        }

    public void emptyVisual(){
        VisualBox.removeAll(VisualBox);
    }
    
    public void SetVisual(Map map){
        //Set in visualBox the tiles that the Dinosaur can see
        emptyVisual();
        
        int startX = x - visual;
        int endX = x + visual;
        int startY = y - visual;
        int endY = y + visual;
        int flag = 0;
        
        if (startX <= 0){
            startX = 0;
        }
        if (visual > (39 - x)){
            endX = 39;
        }
        if (startY <= 0){
            startY = 0;
        }
        if (visual > (39 - y)){
            endY = 39;
        }
        for (int i = startX; i <= endX; i++){
            numRow++;
            for (int j = startY; j <= endY; j++){
                VisualBox.add(map.ReturnBox(i, j));
                if(flag==0){
                    numColumn++;
                }
            }
            flag = 1;
        }
    }

    public int returnVisualDim(){
        return visual;
    }
    
    public void setPosition(Box box){
        x = box.GetX();
        y = box.GetY();
    }

    public int IncrementEnergy(int energy){
        /*Increments the energy of the dinosaur if it is not at the max level already
        retunr the energy rest if the max value is exceeded, 0 otherwise */
        if ((this.energy + energy) > maxEnergy){
            int remainedEnergy = energy - (maxEnergy - this.energy);
            this.energy = maxEnergy;
            return remainedEnergy;
        }
        this.energy = this.energy + energy;
        return 0;
    }

    public int Growth(){
        //Enhances the level of the dino and return the dimension if the dino is grown, 0 otherwise
        if (size < 5){
            if (energy >= maxEnergy / 2){
                energy = energy - (maxEnergy / 2);
                size++;
                maxEnergy = maxEnergy + 1000;
                if (size == 2 || size == 3){
                    visual = 3;
                }
                if (size == 4 || size == 5){
                    visual = 4;
                }
                return size;
            }
            return 0;
        }
        return size;
    }

    public boolean LayEgg(){
        //Lays an egg
        if (energy >= 1500){
            energy = energy - 1500;
            return true;
        }
        return false;
    }

    public Dinosaur Fight(Dinosaur dino){
        //Returns the reference to the dinosaur that lost the match
        if (strength >= dino.ReturnStrenght()){
            if (this instanceof Carnivorous){
                energy = energy + (int) 0.75 * dino.ReturnEnergy();
            }
            return dino;

        }
        if (dino instanceof Carnivorous){
            dino.IncrementEnergy((int) 0.75 * energy);
            return this;
        }
        return this;
    }

    public Object Movement(Map map, Box box){
        int startX = x - n;
        int endX = x + n;
        int startY = y - n;
        int endY = y + n;

        if (box.GetX() < 0 || box.GetX() > 39 || box.GetY() < 0 || box.GetY() > 39){
            return null;
        } else {

            if (box.GetX() == x && box.GetY() == y){
                return null;
            } else {

                if (box instanceof GroundBox){

                    if (this instanceof Carnivorous || (this instanceof Erbivorous && ((GroundBox) box).isDinosaurPres() == null)){

                        if (startX <= 0){
                            startX = 0;
                        }
                        if (n > (39 - x)){
                            endX = 39;
                        }
                        if (startY <= 0){
                            startY = 0;
                        }
                        if (n > (39 - y)){
                            endY = 39;
                        }

                        if (box.GetX() >= startX && box.GetX() <= endX
                                && box.GetY() >= startY && box.GetY() <= endY){

                            int distance = Math.max(Math.abs(this.x - box.GetX()), Math.abs(this.y - box.GetY()));
                            int energyRequired = (int) (10 * Math.pow(2, distance));

                            if (energy <= energyRequired){

                                return this;

                            } else {

                                for (int i = -1; i <= 1; i++){

                                    for (int j = -1; j <= 1; j++){
                                        Box temp1 = map.ReturnBox(x, y);
                                        if (i == 0 && j == 0){
                                        } else {
                                            Box temp = map.ReturnBox(temp1.GetX() + i, temp1.GetY() + j);

                                            if (temp instanceof GroundBox){
                                                if (temp.GetX() == box.GetX() && temp.GetY() == box.GetY()){

                                                    energy = energy - energyRequired;
                                                    x = temp.GetX();
                                                    y = temp.GetY();
                                                    System.out.println(x);
                                                    System.out.println(y);
                                                    SetVisual(map);
                                                    return map.ReturnBox(x, y);
                                                }


                                                for (int h = -1; h <= 1; h++){
                                                    for (int k = -1; k <= 1; k++){
                                                        if (h == 0 && k == 0){
                                                        } else {
                                                            Box temp2 = map.ReturnBox(temp.GetX() + h, temp.GetY() + k);
                                                            if (temp2 instanceof GroundBox){
                                                                if (temp2.GetX() == box.GetX() && temp2.GetY() == box.GetY()){

                                                                    energy = energy - energyRequired;
                                                                    x = temp2.GetX();
                                                                    y = temp2.GetY();
                                                                    System.out.println(x);
                                                                    System.out.println(y);
                                                                    SetVisual(map);
                                                                    return map.ReturnBox(x, y);
                                                                } else {
                                                                    if (this instanceof Carnivorous){

                                                                        for (int p = -1; p <= 1; p++){
                                                                            for (int q = -1; q <= 1; q++){
                                                                                if (p == 0 && q == 0){
                                                                                } else {
                                                                                    Box temp3 = map.ReturnBox(temp2.GetX() + p, temp2.GetY() + q);
                                                                                    if (temp3 instanceof GroundBox){
                                                                                        if (temp3.GetX() == box.GetX() && temp3.GetY() == box.GetY()){

                                                                                            energy = energy - energyRequired;
                                                                                            x = temp3.GetX();
                                                                                            y = temp3.GetY();
                                                                                            System.out.println(x);
                                                                                            System.out.println(y);
                                                                                            return map.ReturnBox(x, y);
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

    public void setRandomPosition(Map map){
        int x = (int) (Math.random() * 39);
        int y = (int) (Math.random() * 39);
        while (map.ReturnBox(x, y) instanceof WaterBox && ((GroundBox) map.ReturnBox(x, y)).isDinosaurPres() != null){
            x = (int) (Math.random() * 39);
            y = (int) (Math.random() * 39);
        }
        this.x = x;
        this.y = y;
    }

    public Box returnAdjacentPosition(Map map){
        //find an available TILE CASELLA the most near as possible
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (map.ReturnBox(x + i, y + j) instanceof GroundBox && ((GroundBox) map.ReturnBox(x + i, y + j)).isDinosaurPres() == null){
                    return map.ReturnBox(x + i, y + j);
                }
            }
        }
        return null;
    }

    public boolean returnMovementAction(){
        return movementAction;
    }

    public boolean returnOtherAction(){
        return otherAction;
    }

    public void doneMovementAction(){
        movementAction = false;
    }

    public void doneOtherAction(){
        otherAction = false;
    }
    

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player returnPlayer(){
        return player;
    }

    public void setAction(){
        movementAction = true;
        otherAction = true;
    }
    public int returnNumRow(){
        return numRow;
    }
    public int returnNumColumn(){
        return numColumn;
    }
    
    public void decrementLife(){
        lifeSpan--;
    }
}