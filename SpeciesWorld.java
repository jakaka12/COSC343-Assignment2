/**
 * COSC343 Assignment 2
 * SpeciesWorld.java
 * Purpose: Creates the world in which Creatures try to 
 * survive by eating strawberries and avoiding mushrooms and monsters.
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
    private int energyLoss;
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
	int energyLoss = 2;
    
	Random rand = new Random();
	creatures = new Creature[numCreatures];
	monsters = new Monster[numMonsters];
	strawb_array = new int[dimx][dimy];
	mushroom_array = new int[dimx][dimy];
	creature_array = new int[dimx][dimy];
	monster_array = new int[dimx][dimy];
    
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
	for (int i = 0; i<dimx; i++){
	    for (int j = 0; j<dimy; j++){
		System.out.print(map[i][j]);
	    }
	    System.out.println("");
	}
	
    }

    /************************ METHODS ******************************/

    //DON'T NEED THESE
    // /**THESE ARE FOR TESTING PURPOSES**/
    // public Creature getCreature(){
    // 	return creatures[0];

    // }
    // public Monster getMonster(){
    // 	return monsters[0];
    // }

    /******************** SENSORY METHODS **************************/

    /** A method that checks for the presence of strawberries in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if strawberry is in the cell, false if not
     */
    public boolean strawb_present(Location l){
	return strawb_array[l.getX()][l.getY()]>0;
    }

    /** A method that checks for the presence of mushrooms in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if mushroom is in the cell, false if not
     */
    public boolean mushroom_present(Location l){
	return mushroom_array[l.getX()][l.getY()]>0;
    }

    /** A method that checks for the presence of creature in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if creature is in the cell, false if not
     */
    public boolean creature_present(Location l){
	return creature_array[l.getX()][l.getY()]>0;
    }

    /** A method that checks for the presence of monsters in the given location
     *  @param The location that needs checking
     *  @return A boolean; true if monster is in the cell, false if not
     */
    public boolean monster_present(Location l){
	return monster_array[l.getX()][l.getY()]>0;
    }

    /** A method that returns the location of the nearest strawberry.
     *  @param The location that is center of the neighbourhood
     *  @return A Location of where the nearest strawberry is. If there are no strawberries,
     *  it returns the original location. If there are multiple nearest strawberries, it 
     *  randomly returns one of their locations.
     */
    public Location nearest_strawb(Location l){
	Random rand = new Random();
	Location current, result;
	LinkedList<Location> locList = new LinkedList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		//only check the new cell of (x,y) if it lies within the grid
		if (x>=0&&x<10&&y>=0&&y<10){
		    current = new Location(x,y);
		    if (strawb_present(current)){
			//System.out.println("strawberry here:" + current);
			locList.add(current);
			// }else{
			// 	System.out.println("no strawb");
		    }
		    // }else{
		    //     System.out.println(x + "," + y + " out of the grid");
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.getFirst();
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest mushroom.
     *  @param The location that is center of the neighbourhood
     *  @return A Location of where the nearest mushroom is. If there are no mushrooms,
     *  it returns the original location. If there are multiple nearest mushrooms, it 
     *  randomly returns one of their locations.
     */
    public Location nearest_mushroom(Location l){
	Random rand = new Random();
	Location current, result;
	LinkedList<Location> locList = new LinkedList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		//only check the new cell of (x,y) if it lies within the grid
		if (x>=0&&x<10&&y>=0&&y<10){
		    current = new Location(x,y);
		    if (mushroom_present(current)){
			locList.add(current);
		    }
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.getFirst();
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest creature.
     *  @param The location that is center of the neighbourhood
     *  @return A Location of where the nearest creature is. If there are no creature,
     *  it returns the original location. If there are multiple nearest creature, it 
     *  randomly returns one of their locations.
     */
    public Location nearest_creature(Location l){
	Random rand = new Random();
	Location current, result;
	LinkedList<Location> locList = new LinkedList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		//only check the new cell of (x,y) if it lies within the grid
		if (x>=0&&x<10&&y>=0&&y<10){
		    current = new Location(x,y);
		    if (creature_present(current)){
			locList.add(current);
		    }
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.getFirst();
	    }
	}else{
	    return l;
	}
    }

    /** A method that returns the location of the nearest monster.
     *  @param The location that is center of the neighbourhood
     *  @return A Location of where the nearest monster is. If there are no monster,
     *  it returns the original location. If there are multiple nearest monster, it 
     *  randomly returns one of their locations.
     */
    public Location nearest_monster(Location l){
	Random rand = new Random();
	Location current, result;
	LinkedList<Location> locList = new LinkedList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		//only check the new cell of (x,y) if it lies within the grid
		if (x>=0&&x<10&&y>=0&&y<10){
		    current = new Location(x,y);
		    if (monster_present(current)){
			locList.add(current);
		    }
		
		}
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		return locList.get(rand.nextInt(locList.size()));
	    }else{
		return locList.getFirst();
	    }
	}else{
	    return l;
	}
    }

    /******************** CREATURE ACTION METHODS **********************/

    /** A method that randomly moves a creature North, South, East, or West.
     *  Will update creature_array, creature's location, and decrement
     *  the creature's energy level.
     *  @param A creature to move
     */
    public void moveCreature(Creature c){
	int creatureX = c.getLoc().getX();
	int creatureY = c.getLoc().getY();
	
	Location north = new Location(creatureX-1,creatureY);
	Location south = new Location(creatureY+1,creatureY);
	Location east = new Location(creatureX,creatureY+1);
	Location west = new Location(creatureX,creatureY-1);
	
	LinkedList<Location> direction = new LinkedList<Location>();
	if (creatureX>0) direction.add(north);
	if (creatureX<9) direction.add(south);
	if (creatureY>0) direction.add(west);
	if (creatureY<9) direction.add(east);

	creature_array[creatureX][creatureY]--;

	//choose random direction to move from the possible moves available
	Random rand = new Random();
	c.setLoc(direction.get(rand.nextInt(direction.size())));

	//update creature_array and energy level
	creature_array[c.getLoc().getX()][c.getLoc().getX()]++;
	c.loseEnergy(energyLoss);	
    }


    /** A method that moves a creature towards the given location.
     *  Legal movements are North, South, East, or West.
     *  Updates creature_array, creature's location. Also decrements
     *  the creature's energy level. 
     *
     *  If the location is diagonal to the creature's location, 
     *  the creature will either move North or South. 
     *
     *  @param A creature to move, a location to head towards
     */
    public void moveCreatureTo(Creature c, Location l){

	//only move the creature if its current location is
	//different from the one provided
	
	if (!c.getLoc().equals(l)){
	    int creatureX = c.getLoc().getX();
	    int creatureY = c.getLoc().getY();
	    int locX = l.getX();
	    int locY = l.getY();
	    creature_array[creatureX][creatureY]--;
	    //if the location has either X or Y in common with the creature's
	    //current location, then it will move to the specified location
	    if (creatureX==locX||creatureY==locY){
		c.setLoc(l);
	    }else{
		if (locX == creatureX-1){
		    //Move North
		    Location north = new Location(creatureX-1, creatureY);
		    c.setLoc(north);
		}else{
		    //Move South
		    Location south = new Location(creatureX+1, creatureY);
		    c.setLoc(south);
		}
		
	    }
	    
	    //update creature_array    
	    creature_array[c.getLoc().getX()][c.getLoc().getY()]++;
	    
	    //update energy level
	    c.loseEnergy(energyLoss);
	}
    }

    
    /** A method that moves a creature away from the given location.
     *  Legal movements are North, South, East, or West.
     *  Updates creature_array, creature's location. Also decrements
     *  the creature's energy level. 
     *
     *  If the location is diagonal to the creature's location, 
     *  the creature will either move North or South. 
     *
     *  @param A creature to move, a location to head away from
     */
    public void moveCreatureAway(Creature c, Location l){

	//only move the creature if its current location is
	//different from the one provided
	
	if (!c.getLoc().equals(l)){
	    int creatureX = c.getLoc().getX();
	    int creatureY = c.getLoc().getY();
	    int locX = l.getX();
	    int locY = l.getY();

	    Location north = new Location(creatureX-1,creatureY);
	    Location south = new Location(creatureY+1,creatureY);
	    Location east = new Location(creatureX,creatureY+1);
	    Location west = new Location(creatureX,creatureY-1);
	    
	    creature_array[creatureX][creatureY]--;
	    //if the location has either X or Y in common with the creature's
	    //current location, then it will move directly away from the specified location


	    //can probably simplify this, but for now, it accounts for the different cases...
	    if (creatureX==locX){
		//inline along X's
		if (locY>creatureY){
		    if (west.getY()>=0){
			c.setLoc(west);
		    }else if(south.getX()<10){
			c.setLoc(south);
		    }else if(north.getX()>=0){
			c.setLoc(north);
		    }
		}else{
		    if (east.getY()<10){
			c.setLoc(east);
		    }else if(south.getX()<10){
			c.setLoc(south);
		    }else if(north.getX()>=0){
			c.setLoc(north);
		    }
		}
	    }else if(creatureY==locY){
		//inline along Y's
		if (locX>creatureX){
		    if (north.getX()>=0){
			c.setLoc(north);
		    }else if(east.getY()<10){
			c.setLoc(east);
		    }else if(west.getY()>=0){
			c.setLoc(west);
		    }
		}
	    }else{
		if (locX == creatureX-1){
		    //Move South if can
		    if (south.getX()<10){
			c.setLoc(south);
		    }else if(east.getY()<10){
			c.setLoc(east);
		    }else if(west.getY()>=0){
			c.setLoc(west);
		    }
		}else{
		    //Move North if can
		    if (north.getX()>=0){
			c.setLoc(north);
		    }else if(east.getY()<10){
			c.setLoc(east);
		    }else if(west.getY()>=0){
			c.setLoc(west);
		    }
		}
		
	    }
	    
	    //update creature_array    
	    creature_array[c.getLoc().getX()][c.getLoc().getY()]++;
	    
	    //update energy level
	    c.loseEnergy(energyLoss);
	}
    }


    /******************** MONSTER ACTION METHODS **********************/

    /** A method that moves a creature North, South, East, or West.
     *  Creature will move in a random direction.
     *  Will update creature_array, creature's location, and decrement
     *  the creature's energy level.
     *  @param A creature to move
     */
    public void moveMonster(Monster m){
	int monsterX = m.getLoc().getX();
	int monsterY = m.getLoc().getY();
	
	Location north = new Location(monsterX-1,monsterY);
	Location south = new Location(monsterY+1,monsterY);
	Location east = new Location(monsterX,monsterY+1);
	Location west = new Location(monsterX,monsterY-1);
	
	LinkedList<Location> direction = new LinkedList<Location>();
	if (monsterX>0) direction.add(north);
	if (monsterX<9) direction.add(south);
	if (monsterY>0) direction.add(west);
	if (monsterY<9) direction.add(east);

	monster_array[monsterX][monsterY]--;

	//choose random direction to move from the possible moves available
	Random rand = new Random();
	m.setLoc(direction.get(rand.nextInt(direction.size())));

	//update creature_array and energy level
	monster_array[m.getLoc().getX()][m.getLoc().getX()]++;
    }

    
    /** A method that moves a monster North, South, East, or West.
     *  Creature will move in the direction of the provided location, 
     *  but not necessarily to that location if it is diagonally aligned.
     *  Updates monster_array, monster's location.
     *
     *  If the location is diagonal to the monster's location, 
     *  the monster will either move North or South. 
     *
     *  @param A creature to move, a location to head towards
     */
    public void moveMonster(Monster m, Location l){

	//only move the creature if its current location is
	//different from the one provided
	
	if (!m.getLoc().equals(l)){
	    int monsterX = m.getLoc().getX();
	    int monsterY = m.getLoc().getY();
	    int locX = l.getX();
	    int locY = l.getY();
	    monster_array[monsterX][monsterY]--;
	    //if the location has either X or Y in common with the monster's
	    //current location, then it will move to the specified location
	    if (monsterX==locX||monsterY==locY){
		m.setLoc(l);
	    }else{
		if (locX == monsterX-1){
		    //Move North
		    Location north = new Location(monsterX-1, monsterY);
		    m.setLoc(north);
		}else{
		    //Move South
		    Location south = new Location(monsterX+1, monsterY);
		    m.setLoc(south);
		}
		
	    }
	    
	    //update monster_array    
	    monster_array[m.getLoc().getX()][m.getLoc().getY()]++;

	}
    }


    
    
    /******************** MAIN METHOD **************************/
    
    public static void main (String[] args){
	SpeciesWorld world = new SpeciesWorld(10,10,10,10,25,25,10);
	System.out.println("creature at " + world.creatures[3].getLoc());
	world.moveCreatureAway(world.creatures[3],world.nearest_monster(world.creatures[3].getLoc()));
	System.out.println(world.creatures[3]);

	world.moveCreatureAway(world.creatures[3],world.nearest_monster(world.creatures[3].getLoc()));
	System.out.println(world.creatures[3]);
	
	System.out.println(world.monsters[0]);
	Location l = world.nearest_creature(world.monsters[0].getLoc());
	if (l.equals(world.monsters[0].getLoc())){
	    world.moveMonster(world.monsters[0]);
	}else{
	    world.moveMonster(world.monsters[0],l);
	}
	System.out.println(world.monsters[0]);
	world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	System.out.println(world.monsters[0]);
    }
  
  
}
