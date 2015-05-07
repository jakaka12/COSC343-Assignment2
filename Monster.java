/**
 * COSC343 Assignment 2
 * Monster.java
 * Purpose: 
 * 
 * @author: Jazlyn Akaka
 * @version: 29/4/15
 */

import java.util.*;

public class Monster{
	private int energy_level;
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

	public static void main (String[] args){
		Monster frank = new Monster(10,2,2);
		System.out.println(frank.loc);

	}


}