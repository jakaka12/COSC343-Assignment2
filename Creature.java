/**
 * COSC343 Assignment 2
 * Creature.java
 * Purpose: Create creature objects with an energy level and a location
 * 
 * @author: Jazlyn Akaka
 * @version: 10/5/15
 */

import java.util.*;

public class Creature{
    private int energy_level;
    private Location loc;
    public final Chromosome chromosome;
    
    /**
     * CONSTRUCTOR: Creates a Creature with location, starting energy level,
     * and a chromosome, which determines its actions
     * @param starting energy level and x and y coordinates for location
     */
    public Creature(int energy, int x, int y){
	energy_level = energy;
	loc = new Location(x,y);
	chromosome = new Chromosome();
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

    /** Method kills the creature by bringing its
     *  energy level down to 0.
     */
    public void kill(){
	energy_level = 0;
    }
    
    public void gainEnergy(int n){
	energy_level+=n;
    }

    public String toString(){
	String result = "energy_level:" + energy_level;
	result += "\t" + loc + "\t" + "alive:" + isAlive();
	result += "\n" + chromosome;
	return result;
    }
    
    public static void main (String[] args){
	Creature gru = new Creature(10,2,2);
	System.out.println(gru.loc);
	System.out.println(gru.chromosome);

    }


}
