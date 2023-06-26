package it.polimi.dinosaursisland.mappa;

import  java.awt.Point;
import java.io.Serializable;

public class Box extends Point implements Serializable{
	/* Attributes */
	protected boolean walkable;
	
	/* Constructors */
	public Box(){}; 
	public Box(int x, int y){
		super.x = x;
		super.y = y;
	}
	public Box(Box box){
		super.setLocation(box);
	}

	/* Methods */
	public boolean isWalkable(){
		return walkable;
	}
	public void setWalkable(boolean walkable){
		this.walkable = walkable;
	}
	public int GetX(){
	   return (int) super.x;
	}
	public int GetY(){
	   return (int) super.y;
	}
}
