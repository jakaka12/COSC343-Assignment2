/**
 * COSC343 Assignment 2
 * Food.java
 * Purpose: Create food for the scene. If the boolean variable poison is true,
 *			then the food item is a mushroom, otherwise it is a strawberry. 
 * 
 * @author: Jazlyn Akaka
 * @version: 29/4/15
 */

import java.util.*;

public class Food{
	private boolean poison;
	private Location loc; 
	/**
   	 * CONSTRUCTOR: Creates a Food item (mushroom or stawberry)
   	 */
	public Food(boolean poisonous, int row, int col){
		poison = poisonous;
		loc = new Location(row,col);
		
	}

	/**
	* boolean method that determines type of food.
	* @return: boolean poison. If true, then the food item is a mushroom
	* 			if false, then the food item is a strawberry.
	*/
	public boolean isMushroom(){
		return poison;
	}

	public Location getLoc(){
		return loc;
	}

	public static void main (String[] args){
		Food dinner = new Food(true,3,5);
		System.out.println(dinner.loc);

	}


}