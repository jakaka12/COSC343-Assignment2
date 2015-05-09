/**
 * COSC343 Assignment 2
 * Monster.java
 * Purpose: Create monster objects with a location
 * 
 * @author: Jazlyn Akaka
 * @version: 9/5/15
 */

import java.util.*;

public class Monster{
    private Location loc; 
    /**
     * CONSTRUCTOR: Creates a Monster
     */
    public Monster(int row, int col){
	loc = new Location(row,col);
  
    }

    public Location getLoc(){
	return loc;
    }

    public void setLoc(Location l){
	loc = l;
    }

    public String toString(){
	String result = "Monster " + loc;
	return result;
    }
    
    public static void main (String[] args){
	Monster frank = new Monster(10,2);
	System.out.println(frank.loc);

    }


}
