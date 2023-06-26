package it.polimi.dinosaursisland.dinosauri;

import it.polimi.dinosaursisland.mappa.Vegetation;

public class Erbivorous extends Dinosaur{
	public Erbivorous(int x,int y, String name){
            super(x,y, name);
            n=2;           
    }
    public Erbivorous(String name){
            super(name);
            n=2;
    }
    public Erbivorous(int x,int y){
            super(x,y);
            n=2;
    }
}