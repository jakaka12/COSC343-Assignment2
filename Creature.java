/**
 * COSC343 Assignment 2
 * Creature.java
 * Purpose: Create creature objects with an energy level and a location
 * 
 * @author: Jazlyn Akaka
 * @version: 9/5/15
 */

import java.util.*;

public class Creature{
    private int energy_level;
    private Location loc; 
    /**
     * CONSTRUCTOR: Creates a Creature 
     */
    public Creature(int energy, int x, int y){
	energy_level = energy;
	loc = new Location(x,y);
		
    }

    public int getEnergyLevel(){
	return energy_level;
    }

    public Location getLoc(){
	return loc;
    }

    public void setLoc(Location l){
	loc = l;
    }

    public boolean isAlive(){
	return (energy_level>0);
    }

    public void loseEnergy(int n){
	energy_level-=n;
    }

    public String toString(){
	String result = "energy_level:" + energy_level;
	result += "\t" + loc + "\t" + "alive:" + isAlive();
	return result;
    }
    
    public static void main (String[] args){
	Creature gru = new Creature(10,2,2);
	System.out.println(gru.loc);

    }


}
