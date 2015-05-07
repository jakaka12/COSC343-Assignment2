/**
 * COSC343 Assignment 2
 * Location.java
 * Purpose: 
 * 
 * @author: Jazlyn Akaka
 * @version: 29/4/15
 */

import java.util.*;

public class Location{
	private int row, col;

	/**
   	 * CONSTRUCTOR: Creates a Location
   	 */
	public Location(int rowIndex, int colIndex){
		row = rowIndex;
		col = colIndex; 
		
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public void setRow(int newRow){
		row = newRow;
	}

	public void setCol(int newCol){
		col = newCol;
	}

	public String toString(){
		return "[location = (" + row + "," + col + ")]";
	}
}