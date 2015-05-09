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
    private int[][] strawb_array;
    private int[][] mushroom_array;
    private int[][] creature_array;
    private int[][] monster_array;
    private int[][] map;
    private Creature[] creatures;
    private Monster[] monsters;
    /**
     * CONSTRUCTOR: Creates a world that is occupied by Creatures, Monsters, strawberries and mushrooms.
     * Creates 
     */
    public SpeciesWorld(int dimx, int dimy, int cre, int mon, int straw, int mush, int ene){
	int numCreatures = cre;
	int numMonsters = mon;
	int numStrawb = straw;
	int numMush = mush;
	int energy = ene;
    
	Random rand = new Random();
	creatures = new Creature[numCreatures];
	monsters = new Monster[numMonsters];
	strawb_array = new int[dimx][dimy];
	mushroom_array = new int[dimx][dimy];
    
	//this 2d array keeps track of all creatures, monsters, stawberries, 
	//and mushrooms so that different types don't get placed in the same location
	// 1: creatures
	// 2: monsters
	// 3: strawberries
	// 4: mushrooms
	int[][] map = new int[dimx][dimy];
    
	int randx = rand.nextInt(dimx);
	int randy = rand.nextInt(dimy);
    
	//create each creature in a cell that isn't occupied by any others
	for (int c = 0; c<creatures.length; c++){
	    while (map[randx][randy]>0){
		randx = rand.nextInt(dimx);
		randy = rand.nextInt(dimy);
	    }
	    creatures[c] = new Creature(ene,randx,randy);
	    creature_array[randx][randy]++;
	    map[randx][randy]=1;
	}
    
	//create each monster in a cell that isn't occupied by any others
	for (int m = 0; m<monsters.length; m++){
	    while (map[randx][randy]>0){
		randx = rand.nextInt(dimx);
		randy = rand.nextInt(dimy);
	    }
	    monsters[m] = new Monster(randx,randy);
	    monster_array[randx][randy]++;
	    map[randx][randy]=2;
	}

	//place the strawberries in empty cells or cells that have strawberries
	for (int s = 0; s<numStrawb; s++){
	    boolean sNotFound = true;
	    while (map[randx][randy]>0&&sNotFound){
		randx = rand.nextInt(dimx);
		randy = rand.nextInt(dimy);
		if (map[randx][randy]==3)
		    sNotFound = false;
	    }
	    strawb_array[randx][randy]++;
	    map[randx][randy]=3;
	}

	//******TESTING
	// print out values in strawb_array to confirm correct placement and incrementing of strawberries
	// for (int a = 0; a<dimx; a++){
	//     for (int b = 0; b<dimy; b++){
	// 	System.out.print(strawb_array[a][b]);
	//     }
	//     System.out.println("");
	// }
    
	//place the mushrooms in empty cells or cells that have mushrooms
	for (int k = 0; k<numMush; k++){
	    boolean mNotFound = true;
	    while (map[randx][randy]>0&&mNotFound){
		randx = rand.nextInt(dimx);
		randy = rand.nextInt(dimy);
		if (map[randx][randy]==4)
		    mNotFound = false;
	    }
	    mushroom_array[randx][randy]++;
	    map[randx][randy]=4;
	}

	//*******TESTING
	//print out values in the map to see positions of creatures, monsters, strawberries and mushrooms
	// for (int i = 0; i<dimx; i++){
	//     for (int j = 0; j<dimy; j++){
	// 	System.out.print(map[i][j]);
	//     }
	//     System.out.println("");
	// }
	
    }

    /** A method that checks the contents of the cell the creature is in.
     *  
     */
    public void senseCurrent(Creature c){
	
    }
    
    
    
    
    public static void main (String[] args){
	SpeciesWorld world = new SpeciesWorld(10,10,10,10,25,25,3);
	
    }
  
  
}
