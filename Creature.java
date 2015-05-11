/**
 * COSC343 Assignment 2
 * Creature.java
 * Purpose: Create creature objects with an energy level and a location
 * 
 * @author: Jazlyn Akaka
 * @version: 11/5/15
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

    /**
     * CONSTRUCTOR: Creates a Creature with location, starting energy level,
     * and a chromosome, which determines its actions
     * @param starting energy level and x and y coordinates for location,
     * and chromosome from parent creature
     */
    public Creature(int energy, int x, int y, Chromosome c){
	energy_level = energy;
	loc = new Location(x,y);
	chromosome = c;
    }

    /** GETTERS AND SETTERS **/

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

    /** Method used when creature moves around the world
     */
    public void loseEnergy(int n){
	energy_level-=n;
    }

    /** Method kills the creature by bringing its
     *  energy level down to 0.
     */
    public void kill(){
	energy_level = 0;
    }
    
    /** Method used when a creature eats a strawberry
     */
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
	/**TESTING**/
	Creature gru = new Creature(10,2,2);
	System.out.println(gru.loc);
	System.out.println(gru.chromosome);
    }
}
