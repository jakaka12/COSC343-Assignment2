/**
 * COSC343 Assignment 2
 * Creature.java
 * Purpose: 
 * 
 * @author: Jazlyn Akaka
 * @version: 29/4/15
 */

import java.util.*;

public class Creature{
	private int energy_level;
	private Location loc; 
	/**
   	 * CONSTRUCTOR: Creates a Creature 
   	 */
	public Creature(int energy, int row, int col){
		energy_level = energy;
		loc = new Location(row,col);
		
	}

	public int getEnergyLevel(){
		return energy_level;
	}

	public Location getLoc(){
		return loc;
	}

	public boolean isDead(){
		return (energy_level>0);
	}

	public void loseEnergy(int n){
		energy_level-=n;
	}

	public void moveTo(){
		
	}

	public void moveAway(){
		
	}



	public static void main (String[] args){
		Creature gru = new Creature(10,2,2);
		System.out.println(gru.loc);

	}


}