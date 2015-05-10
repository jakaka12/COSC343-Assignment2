/**
 * COSC343 Assignment 2
 * SpeciesWorld.java
 * Purpose: Creates the world in which Creatures try to 
 * survive by eating strawberries and avoiding mushrooms and monsters.
 * 
 * @author: Jazlyn Akaka
 * @version: 10/5/15
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
    private int energyGain;
    private int dimx, dimy, numCreatures, numMonsters, numStrawb, numMush, energy;
    private Location strawbLoc, mushLoc, creLoc, monLoc;

    /**
     * CONSTRUCTOR: Creates a world that is occupied by Creatures, Monsters, strawberries and mushrooms.
     * 
     * 
     */
    public SpeciesWorld(){
	dimx = 10;
	dimy = 10;
        numCreatures = 12;
	numMonsters = 12;
	numStrawb = 25;
	numMush = 25; 
	energy = 30; //starting energy_level of creatures
	energyLoss = 2; //energy lost by moving
	energyGain = 4; //energy gained by eating a strawberry
    
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
	    creatures[c] = new Creature(energy,randx,randy);
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
    
    /**
     * CONSTRUCTOR: Creates a world that is occupied by Creatures, Monsters, strawberries and mushrooms.
     * @param dimensions x and y for the grid, number of creatures, number of monsters, number of strawberries, 
     * number of mushrooms, and starting energy level for the creatures.
     */
    public SpeciesWorld(int dimensionX, int dimensionY, int cre, int mon, int straw, int mush, int ene){
	dimx = dimensionX;
	dimy = dimensionY;
	numCreatures = cre;
	numMonsters = mon;
	numStrawb = straw;
	numMush = mush;
	energy = ene;   //starting energy_level of creatures
	energyLoss = 2; //energy lost by moving
	energyGain = 4; //energy gained by eating a strawberry
    
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
	    creatures[c] = new Creature(energy,randx,randy);
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

    /** A method that returns the creature in the given location
     *  If there isn't a creature in the cell, it will return a creature
     *  with an energy level of zero, and position at (0,0);
     *  @param a location with a creature
     *  @return the creature in the location
     */
    public Creature whichCreature(Location l){
	for (int i = 0; i<creatures.length; i++){
	    if (l.equals(creatures[i].getLoc())){
		return creatures[i];
	    }
	}
	Creature dead = new Creature(0,0,0);
	return dead;
    }

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
	result = new Location(-1,-1);
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
		result = locList.get(rand.nextInt(locList.size()));
	    }else{
		result = locList.getFirst();
	    }
	}
	System.out.println("nearest strawb:" + result);
	return result;
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
	result = new Location(-1,-1);
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
		result = locList.get(rand.nextInt(locList.size()));
	    }else{
		result = locList.getFirst();
	    }
	}
	System.out.println("nearest mush:" + result);
	return result;
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
	result = new Location(-1,-1);
	LinkedList<Location> locList = new LinkedList<Location>();
	for (int x = l.getX()-1; x<l.getX()+2; x++){
	    for (int y = l.getY()-1; y<l.getY()+2;y++){
		//only check the new cell of (x,y) if it lies within the grid
		if (x>=0&&x<10&&y>=0&&y<10){
		    current = new Location(x,y);
		    if (!l.equals(current)){
			if (creature_present(current)){
			    locList.add(current);
			}
		    }
		}		
	    }
	}
	if (locList.size()>0){
	    if (locList.size()>1){
		result = locList.get(rand.nextInt(locList.size()));
	    }else{
		result = locList.getFirst();
	    }
	}
	System.out.println("nearest creature:" + result);
	return result;
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
	result = new Location(-1,-1);
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
	        result = locList.get(rand.nextInt(locList.size()));
	    }else{
		result = locList.getFirst();
	    }
	}
	System.out.println("nearest monster:" + result);
	return result;
    }
    
    /******************** CREATURE ACTION METHODS **********************/

    /** A method that moves creature North one cell and decrements energy
     *  @param A creature to move
     */
    public void moveNorth(Creature c){
	int creatureX = c.getLoc().getX();
	//only move north if not at north border
	if (creatureX-1>=0){
	    int creatureY = c.getLoc().getY();
	
	    Location north = new Location(creatureX-1,creatureY);
	    creature_array[creatureX][creatureY]--;
	    c.setLoc(north);
	    creature_array[creatureX-1][creatureY]++;
	    c.loseEnergy(energyLoss);
	}
    }

    /** A method that moves creature South one cell and decrements energy
     *  @param A creature to move
     */
    public void moveSouth(Creature c){
	int creatureX = c.getLoc().getX();
	//only move south if not at south border
	if (creatureX+1<10){
	    int creatureY = c.getLoc().getY();
	    Location south = new Location(creatureX+1,creatureY);
	    creature_array[creatureX][creatureY]--;
	    c.setLoc(south);
	    creature_array[creatureX+1][creatureY]++;
	    c.loseEnergy(energyLoss);
	}
    }
    
    /** A method that moves creature East one cell and decrements energy
     *  @param A creature to move
     */
    public void moveEast(Creature c){
	int creatureY = c.getLoc().getY();
	//only move east if not at east border
	if (creatureY+1<10){
	    int creatureX = c.getLoc().getX();
	    Location east = new Location(creatureX,creatureY+1);
	    creature_array[creatureX][creatureY]--;
	    c.setLoc(east);
	    creature_array[creatureX][creatureY+1]++;
	    c.loseEnergy(energyLoss);
	}
    }

    /** A method that moves creature West one cell and decrements energy
     *  @param A creature to move
     */
    public void moveWest(Creature c){
	int creatureY = c.getLoc().getY();
	//only move west if not at west border
	if (creatureY-1>=0){
	    int creatureX = c.getLoc().getX();
	    Location west = new Location(creatureX,creatureY-1);
	    creature_array[creatureX][creatureY]--;
	    c.setLoc(west);
	    creature_array[creatureX][creatureY-1]++;
	    c.loseEnergy(energyLoss);
	}
    }
    
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
	int creatureX = c.getLoc().getX();
	int creatureY = c.getLoc().getY();
	int locX = l.getX();
	int locY = l.getY();
	    
	if (creatureX!=locX&&creatureY!=locY){
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

    /**********I THINK SOMETHING IS WRONG WITH THIS METHOD.. NEED TO FIX***********/
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
	
	    int creatureX = c.getLoc().getX();
	    int creatureY = c.getLoc().getY();
	    int locX = l.getX();
	    int locY = l.getY();

	    if (creatureX!=locX&&creatureY!=locY){
	    
	    Location north = new Location(creatureX-1,creatureY);
	    Location south = new Location(creatureY+1,creatureY);
	    Location east = new Location(creatureX,creatureY+1);
	    Location west = new Location(creatureX,creatureY-1);
	    
	    creature_array[creatureX][creatureY]--;
	    //if the location has either X or Y in common with the creature's
	    //current location, then it will move directly away from the specified location


	    //can probably simplify this, but for now, it accounts for the different cases...
	    //not the most specific implementation when the creature is stuck in a corner or on an edge...
	    if (creatureX==locX){
		//inline along X'
		if (locY>creatureY){
		    //location to the east of creature
		    if (west.getY()>=0){
			c.setLoc(west);
		    }else if(south.getX()<10){
			c.setLoc(south);
		    }else if(north.getX()>=0){
			c.setLoc(north);
		    }
		}else{
		    //location to the west of creature
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
		    //location to south of creature
		    if (north.getX()>=0){
			c.setLoc(north);
		    }else if(east.getY()<10){
			c.setLoc(east);
		    }else if(west.getY()>=0){
			c.setLoc(west);
		    }
		}else{
		    //location to north of creature
		    if (south.getX()>=0){
			c.setLoc(south);
		    }else if (east.getY()<10){
			c.setLoc(east);
		    }else if (west.getY()>=0){
			c.setLoc(west);
		    }
		}
	    }else{
		//location is diagonal to creature
		if (locX == creatureX-1){
		    //location is north of creature
		    if (south.getX()<10){
			c.setLoc(south);
		    }else if(east.getY()<10){
			c.setLoc(east);
		    }else if(west.getY()>=0){
			c.setLoc(west);
		    }
		}else{
		    //location is south of creature
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
	}else{
	    //randomly move the creature if it is in the same location as what it wants
	    //to move away from. No matter which direction it goes, it will be moving away
	    moveCreature(c);
	}
    }


    /** A method that makes a creature eat one unit of food
     *  which could either be a mushroom or a strawberry.
     *  If it is a mushroom, the creature's energy will be reduced
     *  to zero, effectively killing the creature. If it is a strawberry
     *  the creature's energy_level will increase by a fixed amount determined
     *  in the constructor. 
     *  @param The creature that will be eating
     */
    public void eat(Creature c){
	int cX = c.getLoc().getX();
	int cY = c.getLoc().getY();
	if (strawb_present(c.getLoc())){
	    c.gainEnergy(energyGain);
	    strawb_array[cX][cY]--;
	}
	if (mushroom_present(c.getLoc())){
	    mushroom_array[cX][cY]--;
	    creature_array[cX][cY]--;
	    c.kill();
	}
    }


    /** A method that helps the creature determine which action to do
     *  based on its sense of the neighbourhood and its chromosome characteristics
     *  Returns the action to do
     *  @param A creature that needs help deciding what to do
     *  @return An integer that represents a specific movement
     *          0: eat strawberry
     *          1: eat mushroom
     *          2: move based on strawberry
     *          3: move based on mushroom
     *          4: move based on creature
     *          5: move based on monster
     *          6: default action
     */
    public int selectAction(Creature c){
	LinkedList<Integer> action = new LinkedList<Integer>();
	boolean hasStrawb, hasMush;
	int actionCount = 0;
	//	Location strawbLoc, mushLoc, creLoc, monLoc;
	double[] weights = c.chromosome.getWeights();
	double[] actionWeights = new double[6];
	Hashtable<Double,Integer> actionMap = new Hashtable<Double,Integer>();
	
	hasStrawb = strawb_present(c.getLoc());
	hasMush = mushroom_present(c.getLoc());
	strawbLoc = nearest_strawb(c.getLoc());
	mushLoc = nearest_mushroom(c.getLoc());
	creLoc = nearest_creature(c.getLoc());
	monLoc = nearest_monster(c.getLoc());
	//Add actions to list if the corresponding action from the chromosome is not ignore
	if (hasStrawb){
	    if (c.chromosome.getEatStrawb()){
		System.out.println("eat Strawb " + weights[0]);
		action.add(0);
		actionWeights[0] = weights[0];
		actionMap.put(weights[0],0);
	    }
	}
	if (hasMush){
	    if (c.chromosome.getEatMush()){
		System.out.println("eat mush " + weights[1]);
		action.add(1);
		actionWeights[1] = weights[1];
		actionMap.put(weights[1],1);
	    }
	}
	if (strawbLoc.getX()>-1&&strawbLoc.getY()>-1){
	    if (c.chromosome.getStrawbMove()>0){
		System.out.println("move strawb " + weights[2]);
		action.add(2);
		actionWeights[2] = weights[2];
		actionMap.put(weights[2],2);
	    }
	}
	if (mushLoc.getX()>-1&&mushLoc.getY()>-1){
	    if (c.chromosome.getMushMove()>0){
		System.out.println("move mush " + weights[3]);
		action.add(3);
		actionWeights[3] = weights[3];
		actionMap.put(weights[3],3);
	    }
	}
	if(monLoc.getX()>-1&&monLoc.getY()>-1){
	    // System.out.println("chromosome value: " + c.chromosome.getMonsMove());
	    if (c.chromosome.getMonsMove()>0){
		System.out.println("move mon:" + weights[4]);
		action.add(4);
		actionWeights[4] = weights[4];
		actionMap.put(weights[4],4);
	    }
	}
	if(creLoc.getX()>-1&&creLoc.getY()>-1){
	    //	    System.out.println("creatureLoc:" + creLoc + "chrome:" + c.chromosome.getCreMove());
	    if (c.chromosome.getCreMove()>0){
		System.out.println("move cre " + weights[5]);
		action.add(5);
		actionWeights[5] = weights[5];
		actionMap.put(weights[5],5);
	    }
	}
	if (action.size()>0){
	    //sort actionWeights and find the largest weight and then get the corresponding value
	    Arrays.sort(actionWeights);
	    System.out.println("action to perform " + actionMap.get(actionWeights[5]));
	    return actionMap.get(actionWeights[5]);
	}else{
	    return 6;
	}
    }

    /** A method that makes the creature do the action specified in the selectAction() method.
     *  Based on the value returned from selectAction(), this method will call the appropriate
     *  helper methods to make the creature do that action. Possible actions are eating (strawberries
     *  or mushrooms), and moving away/towards a specified monster/strawberry/mushroom/creature, or 
     *  moving at random.
     *  @param The creature that needs to do an action and the action, represented as an integer, that
     *  the creature should perform.
     *          0: eat strawberry
     *          1: eat mushroom
     *          2: move based on strawberry
     *          3: move based on mushroom
     *          4: move based on creature
     *          5: move based on monster
     *          6: default action
     */
    public void doAction(Creature c){		
	int action = selectAction(c);
	switch(action){
	case 0:
	case 1:
	    System.out.println("eat");
	    eat(c);
	    break;
	case 2:
	    System.out.println("move on strawberry");
	    switch(c.chromosome.getStrawbMove()){
	    case 1:
		System.out.println("move to strawb");
		moveCreatureTo(c,strawbLoc);
		break;
	    case 2:
		System.out.println("move away from strawb");
		moveCreatureAway(c,strawbLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;    
	    }
	    break;
	case 3:
	    System.out.println("move on mushroom");
	    switch(c.chromosome.getMushMove()){
	    case 1:
		System.out.println("move to mush");
		moveCreatureTo(c,mushLoc);
		break;
	    case 2:
		System.out.println("move away from mush");
		moveCreatureAway(c,mushLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;   
	    }
	    break;
	case 4:
	    System.out.println("move on monster");
	    switch(c.chromosome.getMonsMove()){
	    case 1:
		System.out.println("move to monster");
		moveCreatureTo(c,monLoc);
		break;
	    case 2:
		System.out.println("move away from monster");
		moveCreatureAway(c,monLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;   
	    }
	    break;
	case 5:
	    System.out.println("move on creature");
	    switch(c.chromosome.getCreMove()){
	    case 1:
		System.out.println("move to creature");
		moveCreatureTo(c,creLoc);
		break;
	    case 2:
		System.out.println("move away from creatures");
		moveCreatureAway(c,creLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;  
	    }
	    break;
	case 6:
	    System.out.println("default action");
	    switch(c.chromosome.getAction()){
	    case 1:
		moveNorth(c);
		break;
	    case 2:
		moveSouth(c);
		break;
	    case 3:
		moveEast(c);
		break;
	    case 4:
		moveWest(c);
		break;
	    default:
		moveCreature(c);
		break;
	    }
	    break;
	default:
	    moveCreature(c);
	    break;
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
	//SpeciesWorld world = new SpeciesWorld(10,10,10,10,25,25,10);
	SpeciesWorld world = new SpeciesWorld();
	System.out.println(world.creatures[0]);
	//	System.out.println(world.creatures[0].chromosome);
	//	System.out.println(world.selectAction(world.creatures[0]));
	world.doAction(world.creatures[0]);
	System.out.println(world.creatures[0]);
      	world.doAction(world.creatures[0]);
	System.out.println(world.creatures[0]);









	/*******PREVIOUS TESTING*************/
	
	// System.out.println("start: " + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.whichCreature(world.monsters[0].getLoc()).kill();
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getY()]--;
	// }
	// System.out.println("1st: " + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.whichCreature(world.monsters[0].getLoc()).kill();
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getY()]--;
	// }

	// System.out.println("2nd: " + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.whichCreature(world.monsters[0].getLoc()).kill();
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getY()]--;
	// }

	// System.out.println("3rd: " + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.whichCreature(world.monsters[0].getLoc()).kill();
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getY()]--;
	// }
	
	// System.out.println("creature at " + world.creatures[3].getLoc());
	// world.moveCreatureAway(world.creatures[3],world.nearest_monster(world.creatures[3].getLoc()));
	// System.out.println(world.creatures[3]);

	// world.moveCreatureAway(world.creatures[3],world.nearest_monster(world.creatures[3].getLoc()));
	// System.out.println(world.creatures[3]);
	
	// System.out.println(world.monsters[0]);
	// Location l = world.nearest_creature(world.monsters[0].getLoc());
	// if (l.equals(world.monsters[0].getLoc())){
	//     world.moveMonster(world.monsters[0]);
	// }else{
	//     world.moveMonster(world.monsters[0],l);
	// }
	// System.out.println(world.monsters[0]);
	// System.out.println("start:" + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.whichCreature(creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getX()]).kill();
	// }
	// System.out.println("first move:" + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getX()].kill();
	// }
	// System.out.println("second move:" + world.monsters[0]);
	// world.moveMonster(world.monsters[0],world.nearest_creature(world.monsters[0].getLoc()));
	// if (world.creature_present(world.monsters[0].getLoc())){
	//     world.creature_array[world.monsters[0].getLoc().getX()][world.monsters[0].getLoc().getX()].kill();
	// }
	// System.out.println("third move:" + world.monsters[0]);
    }
  
  
}
