/**
 * COSC343 Assignment 2
 * SpeciesWorld.java
 * Purpose: Creates the world in which Creatures try to survive by eating strawberries and avoiding mushrooms and monsters.
 * 
 * @author: Jazlyn Akaka
 * @version: 7/5/15
 */

import java.util.*;

public class SpeciesWorld{
	public int[][] stawb_array;
	public int[][] mushroom_array;
	public int[][] creature_array;
	public int[][] monster_array;
	public Creature[] creatures;
	public Monster[] monsters;
	/**
   	 * CONSTRUCTOR: Creates SpeciesWorld
   	 */
	public SpeciesWorld(int dimx, int dimy, int cre, int mon, int straw, int mush, int ene){
		int numCreatures = cre;
		int numMonsters = mon;
		int numStawb = staw;
		int numMush = mush;
		int energy = ene;

		Random rand = new Random();
		creatures = new Creature[numCreatures];
		monsters = new Monster[numMonsters];
		strawb_array = new int[dimx][dimy];
		mushroom_array = new int[dimx][dimy]

		//create each creature and monster
		for (int i = 0; i<creatures.length; i++){
			creatures[i] = new Creature(energy, rand.nextInt(dimx), rand.nextInt(dimy));
			monsters[i] = new Monster(rand.nextInt(dimx),rand.nextInt(dimy));
		}

		//place the strawberries
		for (int j = 0; j<numStawb; j++){
			strawb_array[rand.nextInt(dimx)][rand.nextInt(dimy)]++;
		}

		//place the mushrooms
		for (int k = 0; k<numMush; k++){
			mushroom_array[rand.nextInt(dimx)][rand.nextInt(dimy)]++;
		}

		
	}

	



	public static void main (String[] args){


	}


}