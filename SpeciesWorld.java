/**
 * COSC343 Assignment 2
 * SpeciesWorld.java
 * Purpose: Creates the world in which Creatures try to 
 * survive by eating strawberries and avoiding mushrooms and monsters.
 * 
 * @author: Jazlyn Akaka
 * @version: 13/5/15
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class SpeciesWorld{
    public int[][] strawb_array;
    public int[][] mushroom_array;
    public int[][] creature_array;
    public int[][] monster_array;
    public int[][] map;
    public Creature[] creatures;
    public Chromosome[] babyChroms;
    public Monster[] monsters;
    public int energyLoss;
    public int energyGain;
    public int dimx, dimy, numCreatures, numMonsters, numStrawb, numMush, energy;
    private Location strawbLoc, mushLoc, creLoc, monLoc; //to be used later by selectAction() and doAction()
    public int numAlive;
    public static int numEatS, numEatM, numMoveS, numMoveMu, numMoveC, numMoveMo;
    public static int toS, awayMo;

    /**
     * CONSTRUCTOR: Creates a world that is occupied by Creatures, Monsters, strawberries and mushrooms.
     * 
     * 
     */
    public SpeciesWorld(){
	toS = 0;
	awayMo = 0;
	numEatS = 0;
	numEatM = 0;
	numMoveS = 0;
	numMoveMu = 0;
	numMoveC = 0;
	numMoveMo = 0;
	dimx = 30;
	dimy = 30;
        numCreatures = 50;
	numMonsters = 15;
	numStrawb = 500;
	numMush = 100; 
	energy = 10; //starting energy_level of creatures
	energyLoss = 1; //energy lost by moving
	energyGain = 10; //energy gained by eating a strawberry
	numAlive = numCreatures;
	Random rand = new Random();
	creatures = new Creature[numCreatures];
	babyChroms = new Chromosome[numCreatures];
	monsters = new Monster[numMonsters];
	strawb_array = new int[dimx][dimy];
	mushroom_array = new int[dimx][dimy];
	creature_array = new int[dimx][dimy];
	monster_array = new int[dimx][dimy];
    
	//this 2d array keeps track of all creatures, monsters, stawberries, 
	//and mushrooms so that different types don't get placed in the same location
	// 1: creatures --> purple
	// 2: monsters --> black
	// 3: strawberries --> pink/red
	// 4: mushrooms --> green
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
	// for (int i = 0; i<dimx; i++){
	//     for (int j = 0; j<dimy; j++){
	// 	System.out.print(map[j][i]);
	//     }
	//     System.out.println("");
	// }
    }

    /**
     * CONSTRUCTOR: Creates a world that is occupied by Creatures, Monsters, strawberries and mushrooms.
     * this constructor is used after the first generation has gone through
     * @param an array of Chromosomes of size equal to numCreatures so that the world is populated
     * with creatures based on the first generation
     */
    public SpeciesWorld(Chromosome[] nextGen){
		toS = 0;
	awayMo = 0;
	numEatS = 0;
	numEatM = 0;
	numMoveS = 0;
	numMoveMu = 0;
	numMoveC = 0;
	numMoveMo = 0;
	dimx = 30;
	dimy = 30;
        numCreatures = 50;
	numMonsters = 15;
	numStrawb = 500;
	numMush = 100; 
	energy = 10; //starting energy_level of creatures
	energyLoss = 1; //energy lost by moving
	energyGain = 10; //energy gained by eating a strawberry
	numAlive = numCreatures;
	
	Random rand = new Random();
	creatures = new Creature[numCreatures];
	babyChroms = nextGen;
	monsters = new Monster[numMonsters];
	strawb_array = new int[dimx][dimy];
	mushroom_array = new int[dimx][dimy];
	creature_array = new int[dimx][dimy];
	monster_array = new int[dimx][dimy];
    
	//this 2d array keeps track of all creatures, monsters, stawberries, 
	//and mushrooms so that different types don't get placed in the same location
	// 1: creatures --> purple
	// 2: monsters --> black
	// 3: strawberries --> pink/red
	// 4: mushrooms --> green
	int[][] map = new int[dimx][dimy];
	
	int randx = rand.nextInt(dimx);
	int randy = rand.nextInt(dimy);
    
	//create each creature in a cell that isn't occupied by any others
	for (int c = 0; c<creatures.length; c++){
	    while (map[randx][randy]>0){
		randx = rand.nextInt(dimx);
		randy = rand.nextInt(dimy);
	    }
	    creatures[c] = new Creature(energy,randx,randy,babyChroms[0]);
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
	// 	System.out.print(map[j][i]);
	//     }
	//     System.out.println("");
	// }
	
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
	energyLoss = 1; //energy lost by moving
	energyGain = 2; //energy gained by eating a strawberry
	numAlive = numCreatures;
	
	Random rand = new Random();
	creatures = new Creature[numCreatures];
	babyChroms = new Chromosome[numCreatures];
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
	// 	System.out.print(map[j][i]);
	//     }
	//     System.out.println("");
	// }
	
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
	Creature c = new Creature(0,0,0);
	return c;
    }

    /** A method that gets the value in the map
     *  This is for use of initializing the GUI
     *  @param the x and y coordinates
     *  @return returns a value [0,4] that represents the object at that location
     */
    public int getContents(int x, int y){
	return map[x][y];
    }

    /**
     * Method returns the average fitness across the population of creatures
     * @return a double that is the average fitness (avg energy level)
     */
    public double getAvgFitness(){
	double sum = 0;
	double popSize = 0;
	for (int i = 0; i<numCreatures; i++){
	    double fitness = creatures[i].getEnergyLevel();
	    if (fitness>0){
		sum+=fitness;
		popSize++;
	    }
	}
	
	return sum/popSize;
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
	//	System.out.println("nearest strawb:" + result);
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
	//	System.out.println("nearest mush:" + result);
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
	//	System.out.println("nearest creature:" + result);
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
	//	System.out.println("nearest monster:" + result);
	return result;
    }
    
    /******************** CREATURE ACTION METHODS **********************/

    /** Note: 11/5/15 Directions of these action methods are relative. north might not necessarily 
     *  be in the direction above the given object when looking at the gui...
     */

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
	Location south = new Location(creatureX+1,creatureY);
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
	creature_array[c.getLoc().getX()][c.getLoc().getY()]++;
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

	if (locX>-1&&locY>-1){
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
	
	int creatureX = c.getLoc().getX();
	int creatureY = c.getLoc().getY();
	int locX = l.getX();
	int locY = l.getY();
	if (locX>-1&&locY>-1){
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
	//	System.out.println("eat before: " + c.getEnergyLevel());
	int cX = c.getLoc().getX();
	int cY = c.getLoc().getY();
	if (strawb_present(c.getLoc())){
	    c.gainEnergy(energyGain);
	    strawb_array[cX][cY]--;
	    numEatS++;
	}
	if (mushroom_present(c.getLoc())){
	    mushroom_array[cX][cY]--;
	    creature_array[cX][cY]--;
	    c.kill();
	    numAlive--;
	    numEatM++;
	}
	//	if (c.isAlive()) System.out.println(c.chromosome + "eat " + c.getEnergyLevel());
	//		System.out.println("eat after: "  + c.getEnergyLevel());
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
	boolean hasStrawb, hasMush;
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
		//		System.out.println("eat Strawb " + weights[0]);
		actionWeights[0] = weights[0];
		actionMap.put(weights[0],0);
	    }
	}
	if (hasMush){
	    if (c.chromosome.getEatMush()){
		//		System.out.println("eat mush " + weights[1]);
		actionWeights[1] = weights[1];
		actionMap.put(weights[1],1);
	    }
	}
	if (strawbLoc.getX()>-1&&strawbLoc.getY()>-1){
	    if (c.chromosome.getStrawbMove()>0){
		//		System.out.println("move strawb " + weights[2]);
		actionWeights[2] = weights[2];
		actionMap.put(weights[2],2);
	    }
	}
	if (mushLoc.getX()>-1&&mushLoc.getY()>-1){
	    if (c.chromosome.getMushMove()>0){
		//		System.out.println("move mush " + weights[3]);
		actionWeights[3] = weights[3];
		actionMap.put(weights[3],3);
	    }
	}
	if(monLoc.getX()>-1&&monLoc.getY()>-1){
	    if (c.chromosome.getMonsMove()>0){
		//		System.out.println("move mon:" + weights[4]);
		actionWeights[4] = weights[4];
		actionMap.put(weights[4],4);
	    }
	}
	if(creLoc.getX()>-1&&creLoc.getY()>-1){
	    if (c.chromosome.getCreMove()>0){
		//		System.out.println("move cre " + weights[5]);
		actionWeights[5] = weights[5];
		actionMap.put(weights[5],5);
	    }
	}
	if (actionMap.size()>0){
	    //sort actionWeights and find the largest weight and then get the corresponding value
	    Arrays.sort(actionWeights);
	    //	    System.out.println("action to perform " + actionMap.get(actionWeights[5]));
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
	    //	    System.out.println("eat");
	    eat(c);
	    break;
	case 2:
	    numMoveS++;
	    //	    System.out.println("move on strawberry");
	    switch(c.chromosome.getStrawbMove()){
	    case 1:
		//		System.out.println("move to strawb");
		toS++;
		moveCreatureTo(c,strawbLoc);
		break;
	    case 2:
		//		System.out.println("move away from strawb");
		moveCreatureAway(c,strawbLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;    
	    }
	    break;
	case 3:
	    numMoveMu++;
	    //	    System.out.println("move on mushroom");
	    switch(c.chromosome.getMushMove()){
	    case 1:
		//		System.out.println("move to mush");
		moveCreatureTo(c,mushLoc);
		break;
	    case 2:
		awayMo++;
		//		System.out.println("move away from mush");
		moveCreatureAway(c,mushLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;   
	    }
	    break;
	case 4:
	    numMoveC++;
	    //	    System.out.println("move on monster");
	    switch(c.chromosome.getMonsMove()){
	    case 1:
		//		System.out.println("move to monster");
		moveCreatureTo(c,monLoc);
		break;
	    case 2:
		//		System.out.println("move away from monster");
		moveCreatureAway(c,monLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;   
	    }
	    break;
	case 5:
	    numMoveMo++;
	    //	    System.out.println("move on creature");
	    switch(c.chromosome.getCreMove()){
	    case 1:
		//		System.out.println("move to creature");
		moveCreatureTo(c,creLoc);
		break;
	    case 2:
		//		System.out.println("move away from creatures");
		moveCreatureAway(c,creLoc);
		break;
	    case 3: default:
		moveCreature(c);
		break;  
	    }
	    break;
	case 6:
	    //	    System.out.println("default action");
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
        int currentX = c.getLoc().getX();
	int currentY = c.getLoc().getY();

	if (monster_array[currentX][currentY]>0){
	    creature_array[currentX][currentY]--;
	    c.kill();
	    numAlive--;
	}
	
    }
    


    /******************** MONSTER ACTION METHODS **********************/

    /** A method that moves a creature North, South, East, or West.
     *  Creature will move in a random direction.
     *  Will update monster_array, monster's location
     *  @param A monster to move
     */
    public void moveMonster(Monster m){
	int monsterX = m.getLoc().getX();
	int monsterY = m.getLoc().getY();
	
	Location north = new Location(monsterX-1,monsterY);
	Location south = new Location(monsterX+1,monsterY);
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
	monster_array[m.getLoc().getX()][m.getLoc().getY()]++;
    }

    
    /** A method that moves a monster North, South, East, or West.
     *  Creature will move in the direction of the provided location, 
     *  but not necessarily to that location if it is diagonally aligned.
     *  Updates monster_array, monster's location.
     *
     *  If the location is diagonal to the monster's location, 
     *  the monster will either move North or South. 
     *
     *  @param A monster to move, a location to head towards
     */
    public void moveMonster(Monster m, Location l){
	//	System.out.println("moveMonster");
	//only move the monster if its current location is
	//different from the one provided
	
	
	if (!m.getLoc().equals(l)){
	    int monsterX = m.getLoc().getX();
	    int monsterY = m.getLoc().getY();
	    int locX = l.getX();
	    int locY = l.getY();
	    monster_array[monsterX][monsterY]--;
	    //if the location has either X or Y in common with the monster's
	    //current location, then it will move to the specified location
	    if (locX>-1&&locY>-1){
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
	    }
	    //update monster_array    
	    monster_array[m.getLoc().getX()][m.getLoc().getY()]++;
	}
    }

    /************* EVOLUTION METHODS ******************/

    /** A method that goes through all the creatures and makes them perform
     *  an action. Also iterates through the monsters and moves them every
     *  so often.
     *
     *  @param The number of timeSteps to go through for one population and 
     *  the interval at which monsters should move. The greater the number
     *  the less the monsters will move.
     *
     *  @return The average fitness of the population
     *
     */
    public double oneGen(int timeSteps, int f){
	int t = timeSteps; //one generation lives for 20 time steps
	int monsterTime = f; //monsters move when t is divisible by f
	for (int i = 0; i<t; i++){
	    //	    System.out.println("i:" + i);
	    for (int j = 0; j<creatures.length; j++){
		//		System.out.println("j:" + j);
		Creature c = creatures[j];
		if (c.isAlive()){
		    doAction(c);
		}
		    
	    }
	
	    if (i%monsterTime==0){
		for (int k = 0; k<monsters.length; k++){
		    //		    System.out.println("k:" + k);
		    Monster m = monsters[k];
		    moveMonster(m,nearest_creature(m.getLoc()));
		    int mX = m.getLoc().getX();
		    int mY = m.getLoc().getY();
		    while (creature_array[mX][mY]>0){
			Creature cre = whichCreature(m.getLoc());
		        cre.kill();
			creature_array[mX][mY]--;
			numAlive--;
		    }
		}
	    }
	}

	//calculate average fitness
	double fitnessSum = 0;
	double popSize = numCreatures;
	for (int n = 0; n<creatures.length; n++){
	    int e = creatures[n].getEnergyLevel();
	    fitnessSum += e;
	    //	    System.out.print(e + ", ");
	}
	//	System.out.println("");
	double avg = fitnessSum/popSize;
	return avg;
    }

    /** Method calculates fitness of creatures and returns linkedlist of the fittest
     *  ones. Hard coded to take the fittest third of the population, and
     *  will not return any creatures that are dead. **ELITISM
     *
     *  @return a linkedlist of creatures that are the fittest in the population
     */
    //hard coding to take the fittest fourth of the population
    public LinkedList<Creature> getFittest(){
	LinkedList<Creature> result = new LinkedList<Creature>();

	//fraction of fittest population that will make up the fittest creatures in the population
	double d = 10;
	
	double k = numAlive/d;

	Hashtable<Double,Integer> fitMap = new Hashtable<Double,Integer>();
	//make a new creature array to sort
	//stores energy_levels of creatures
        double[] sorted = new double[creatures.length];
	for (int i = 0; i<sorted.length; i++){
	    double energyLevel = creatures[i].getEnergyLevel();
	    //don't want to add duplicates, so add a double if it's already in the table
	    //this may alter the result slightly since the second creature will have a
	    //better chance of being added to the result list
	    if (energyLevel>0){
		//only add to fitMap if not dead
		while(fitMap.containsKey(energyLevel)){
		    energyLevel+=0.001;
		}
		sorted[i] = energyLevel;
		fitMap.put(energyLevel, i);
	    }
	}
	Arrays.sort(sorted);
	for (int j = sorted.length-1; j>0; j--){
	    if (result.size()<k){
		double fitness = sorted[j];
		if (fitness>0.9){
		    int index = fitMap.get(fitness);
		    //		System.out.println("index: " + index);
		    //		System.out.println(creatures[index]);
		    result.add(creatures[index]);
		    fitMap.remove(fitness);
		}
	    }
	}

	return result;
	
    }

    /** Method selects two parents using something closer to the
     *  roulette wheel selection method, and creates a new chromosome
     *  for a future creature. ** Elitism ** Uses getFittest helper method to get parents.
     *  the same creature parents could have multiple children.
     *  If there are no fit Creatures, then makes a new random Chromosome
     *  
     *  @param determine how many of the fittest creatures to get
     *  the larger the denom, the fewer parents
     */
    public Chromosome makeBabyChrom(){
	Chromosome result = new Chromosome();
	Random rand = new Random();
	LinkedList<Creature> fittest = getFittest();
	int fitSize = fittest.size();
	//	System.out.println(fitSize);
	if (fitSize>1){
	    int p1 = rand.nextInt(fitSize);
	    int r = rand.nextInt(fitSize);
	    while (r==p1) r = rand.nextInt(fitSize);
	    int p2 = r;
	    result = new Chromosome(fittest.get(p1),fittest.get(p2));
	    //	    System.out.println("P1:" + fittest.get(p1) + " P2:" + fittest.get(p2));
	}else{
	    //	    System.out.println("random gen");
	}
	return result;
	
    }

    /** This method creates n new Chromosomes to be used in the next generation
     *  where n is the number of Creatures in the starting population.
     *  Calls makeBabyChrom() to make the new chromosomes
     */
    public Chromosome[] nextGenChroms(){
	Chromosome[] babies = new Chromosome[numCreatures];
	for (int i = 0; i<babies.length; i++){
	    babies[i] = makeBabyChrom();
	    //	    System.out.println(babies[i]);
	}
	return babies;
    }

    /******************** METHODS FOR USE WITH THE GUI *******************/

    /** A method that goes through all the creatures and makes them perform
     *  an action.
     */
    public void creatureStep(){
	for (int j = 0; j<creatures.length; j++){
	    Creature c = creatures[j];
	    if (c.isAlive()){
		doAction(c);
	    }    
	}
    }

    /** A method that goes through all the monsters and makes
     *  them move.
     */
    public void monsterStep(){
	for (int k = 0; k<monsters.length; k++){
	    Monster m = monsters[k];
	    moveMonster(m,nearest_creature(m.getLoc()));
	    int mX = m.getLoc().getX();
	    int mY = m.getLoc().getY();
	    while (creature_array[mX][mY]>0){
		Creature cre = whichCreature(m.getLoc());
		cre.kill();
		creature_array[mX][mY]--;
		numAlive--;
	    }
	}
    }


   

    
    /******************** MAIN METHOD **************************/
    
    public static void main (String[] args) throws FileNotFoundException{
	//SpeciesWorld world = new SpeciesWorld(10,10,10,10,25,25,10);
	PrintWriter writer = new PrintWriter(new File("fitnessResults.txt"));
	SpeciesWorld world = new SpeciesWorld();

	for (int i = 0; i<10; i++){
	    for ( int s = 0; s<60; s++){
		world.creatureStep();
		if (s%2==0) world.monsterStep();
	    }
	    System.out.println("Generation " + i);
	    System.out.println("eatS:" + numEatS + " eatM:" + numEatM + " moveS:" + numMoveS + " moveMu:" + numMoveMu + " moveC:" + numMoveC + " moveMo:" + numMoveMo );
	    System.out.println("toS:" + toS + " awayMo:" + awayMo);
	    // for (int j = 0; j<world.numCreatures; j++){
	    // 	System.out.println(world.creatures[j].getEnergyLevel());
	    // }
	    System.out.println("Average Fitness:" + world.getAvgFitness() + "\n");
	    world = new SpeciesWorld(world.nextGenChroms());
	}


	
	// int numGenerations = 50;
	// int timeSteps = 25;
	// int monsterMovement = 5;//They'll move every 5 timesteps
	// writer.println(world.dimx + " " +  world.dimy + " " +  world.numCreatures + " " +  world.numMonsters + " " +  world.numStrawb + " " + world.numMush + " " +  world.energy);
	// for (int i = 0; i<numGenerations; i++){
	//     //	    System.out.println("Generation " + i);
	//     double avgFit = world.oneGen(timeSteps,monsterMovement);
	//     if (avgFit==0) break;
	//     writer.println(avgFit);
	//     world = new SpeciesWorld(world.nextGenChroms());
	// }
	// writer.close();
	// for (int i = 0; i<3; i++){ // over 100 generations
	//     int population = 0;
	//     if (world.getAvgFitness()>0){
	// 	for (int s = 0; s<60; s++){ //15 steps per generation
	// 	    world.creatureStep();
	// 	    if (s%5==0) world.monsterStep();
	// 	}
	// 	int gen = i+1;
	// 	//	if (i%9==0){
	// 	    System.out.println("Generation " + gen + " Average Fitness: " + world.getAvgFitness());
	// 	    for(int j = 0; j<world.numCreatures; j++){
	// 		Creature c = world.creatures[j];
	// 		if (c.isAlive()){
	// 		    population++;
	// 		}
	// 	    }
	// 	    System.out.println("Population size:" + population);
	// 	    Systme.out.
	// 		//	}
	// 	world = new SpeciesWorld(world.nextGenChroms());
	//     }else{
	// 	System.out.println("extinct");
	// 	break;
	//     }
	// }
	
	
    }
}

