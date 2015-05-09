/**
 * COSC343 Assignment 2
 * Location.java
 * Purpose: 
 * 
 * @author: Jazlyn Akaka
 * @version: 9/5/15
 */

import java.util.*;

public class Location{
    private int x, y;

    /**
     * CONSTRUCTOR: Creates a Location
     */
    public Location(int xIndex, int yIndex){
	x = xIndex;
	y = yIndex; 
		
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public void setX(int newX){
	x = newX;
    }

    public void setCol(int newY){
	y = newY;
    }

    public String toString(){
	return "[location = (" + x + "," + y + ")]";
    }
}
