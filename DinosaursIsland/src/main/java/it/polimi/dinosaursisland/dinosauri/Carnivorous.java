package it.polimi.dinosaursisland.dinosauri;

import it.polimi.dinosaursisland.mappa.Carrion;

public class Carnivorous extends Dinosaur{
	public Carnivorous(int x, int y, String name){
		super(x,y,name);
                strength=1500;
                n=3;
    }
    public Carnivorous(String name){
		super(name);
                strength=1500;
                n=3;
    }
    public Carnivorous(int x, int y){
		super(x,y);
                strength=1500;
                n = 3;
    }
}
