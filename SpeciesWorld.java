/**
 * COSC343 Assignment 2
 * SpeciesWorld.java
 * Purpose: Creates the world in which Creatures try to survive by eating strawberries and avoiding mushrooms and monsters.
 * 
 * @author: Jazlyn Akaka
 * @version: 9/5/15
 */

import java.util.*;
import java.lang.*;

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
    public void senseCurrent(Location l){
	
    }

    /** A method that checks for the presence of strawberries in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if strawberry is in the cell, false if not
     */
    public boolean strawb_present(Location l){
	return map[l.getX()][l.getY()]==3;
    }

    /** A method that checks for the presence of mushrooms in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if mushroom is in the cell, false if not
     */
    public boolean mushroom_present(Location l){
	return map[l.getX()][l.getY()]==4;
    }

    /** A method that checks for the presence of creature in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if creature is in the cell, false if not
     */
    public boolean creature_present(Location l){
	return map[l.getX()][l.getY()]==1;
    }

    /** A method that checks for the presence of monsters in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if monster is in the cell, false if not
     */
    public boolean monster_present(Location l){
	return map[l.getX()][l.getY()]==2;
    }

    /** A method that returns the location of the nearest strawberry.
     * @param The location that is center of the neighbourhood
     * @return A Location of where the nearest strawberry is. If there are no strawberries,
     * it returns the original location. If there are multiple nearest strawberries, it 
     * randomly returns one of their locations.
     */
    public Location nearest_strawb(Location l){
	Random rand = new Random();
	Location current, result;
	List<Location> locList = new ArrayList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		current = new Location(x,y);
		if (strawb_present(current)){
		    locList.add(current);
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.get(0);
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest mushroom.
     * @param The location that is center of the neighbourhood
     * @return A Location of where the nearest mushroom is. If there are no mushrooms,
     * it returns the original location. If there are multiple nearest mushrooms, it 
     * randomly returns one of their locations.
     */
    public Location nearest_mushroom(Location l){
	Random rand = new Random();
	Location current, result;
	List<Location> locList = new ArrayList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		current = new Location(x,y);
		if (mushroom_present(current)){
		    locList.add(current);
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.get(0);
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest creature.
     * @param The location that is center of the neighbourhood
     * @return A Location of where the nearest creature is. If there are no creature,
     * it returns the original location. If there are multiple nearest creature, it 
     * randomly returns one of their locations.
     */
    public Location nearest_creature(Location l){
	Random rand = new Random();
	Location current, result;
	List<Location> locList = new ArrayList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		current = new Location(x,y);
		if (creature_present(current)){
		    locList.add(current);
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.get(0);
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest monster.
     * @param The location that is center of the neighbourhood
     * @return A Location of where the nearest monster is. If there are no monster,
     * it returns the original location. If there are multiple nearest monster, it 
     * randomly returns one of their locations.
     */
    public Location nearest_monster(Location l){
	Random rand = new Random();
	Location current, result;
	List<Location> locList = new ArrayList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		current = new Location(x,y);
		if (monster_present(current)){
		    locList.add(current);
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.get(0);
	    }
	}else{
	    return l;
	}
    }


    
    /** A method that checks the creature's neighbourhood
     *  and determines which direction the creature should move.
     *  The neighbourhood includes the location of the creature and 
     *  the surrounding cells.
     */
    public void senseHood(Location l){
	
    }

    
    
    
    public static void main (String[] args){
	SpeciesWorld world = new SpeciesWorld(10,10,10,10,25,25,3);
	
    }
  
  
}
