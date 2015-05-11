/**
 * COSC343 Assignment 2
 * WorldGrid.java
 *
 * Purpose: A rectangular area of the SpeciesWorld applet that 
 * displays the state of the world
 *
 * Note: WorldGrid is an adapted version of Wellesley College's 2006 cs111 BuggleGrid
 * 
 * @author Jazlyn Akaka
 * @version 11/5/15
 */ 

import java.awt.*;
import java.awt.event.*; // ***
import java.applet.*;  
import java.util.*;
import javax.swing.*;  // ****


public class WorldGrid extends Canvas{
    public SpeciesWorld world;
    private int cellWidth;
    private int cellHeight;
    private Rectangle gridRect;
    private final static Color backgroundColor = Color.lightGray;
    private final static Color gridLineColor = Color.darkGray;
    private final static Color creatureColor = new Color(149,68,255);
    private final static Color monsterColor = Color.black;
    private final static Color mushColor = new Color(111,166,52);
    private final static Color strawbColor = new Color(255,167,196);
    public JButton[] buttons = new JButton[5];
    private String[] buttonNames = new String[] {"Reset","Start","Stop","Step","Fitness"};
    protected Graphics gfx;

    /** CONSTRUCTOR
     */
    public WorldGrid(SpeciesWorld sw){
        world = sw;
	//make buttons
	for (int i = 0; i<buttons.length; i++){
	    buttons[i] = new JButton(buttonNames[i]);
	}


    }


    public void addButtons(){
	

    }


    public JButton[] getButtons(){
	return buttons;

    }

    
    public void makeGrid(){
	//	if (gfx!=null){
	    //	gfx = this.getGraphics();
	    //	System.out.println("makeGrid");
	    Dimension d = getSize();
	    //	System.out.println(d);
	    cellWidth = d.width/world.dimy;
	    cellHeight = d.height/world.dimx;
	    int gridWidth = world.dimy * cellWidth + 1;// +1 for last grid line
	    int gridHeight = world.dimx * cellHeight + 1;
	    int gridX = (d.width - gridWidth)/2;
	    int gridY = (d.height - gridHeight)/2;
	    gridRect = new Rectangle(gridX,gridY,gridWidth,gridHeight);
	    //	}
    }

    public Location cellOrigin (Location p) {
	//Returns the graphics coordinate of the upper left corner of the cell at coord p.
	//Cell coordinates range from (1,1) to (dimy,dimx), from lower left to upper right.
	return new Location (gridRect.x + (p.x - 1)*cellWidth, gridRect.y + (world.dimx - p.y) * cellHeight);
    }

    public Rectangle cellRectangle (Location p){
	//Cell coordinates range from (1,1) to (dimy,dimx), from lower left to upper right.
	Location origin = cellOrigin(p);
	//Account for width of grid line in rectangle dimensions.
	//(Don't include grid lines in rectangle.)
	return new Rectangle(origin.x + 1, origin.y + 1, cellWidth - 1, cellHeight -1);
    }

    public void paintGrid(){
	//	if (gfx!=null){
	    //	    System.out.println(gfx);
	    //	System.out.println("paintGrid");
	makeGrid();

	gfx = this.getGraphics();
	System.out.println(gfx);
	if (gfx!=null){
	    //paint rectangle on which the centered grid will be displayed
	    Dimension canvasSize = this.getSize();
	    gfx.setColor(gridLineColor);
	    gfx.fillRect(0,0,canvasSize.width,canvasSize.height);

	    //assume that gfx already defined by makeGrid().
	    //makeGrid() should be called before paintGrid()
	    gfx.setColor(gridLineColor);
	    gfx.fillRect(0,0,canvasSize.width, canvasSize.height);

	    //Now display grid itself.
	    gfx.setColor(backgroundColor);
	    gfx.fillRect(gridRect.x, gridRect.y, gridRect.width, gridRect.height);
	    int left = gridRect.x;
	    int right = left + gridRect.width -1;
	    int top = gridRect.y;
	    int bottom = top + gridRect.height -1;
	    gfx.setColor(gridLineColor);
	    //Paint horizontal grid lines
	    for (int j = 0; j<=world.dimx; j++){
		//	    System.out.println("draw horiz");
		gfx.drawLine(left, gridRect.y + j * cellHeight, right, gridRect.y + j * cellHeight);
	    }
	    //Paint vertical gride lines
	    for (int i = 0; i<=world.dimy; i++){
	    
		gfx.drawLine(gridRect.x + i * cellWidth, top, gridRect.x + i * cellWidth, bottom);
	    }

	    //paint creatures, monsters, mushrooms, and strawberries onto grid
	    for (int k = 0; k<world.dimx; k++){
		for (int m = 0; m<world.dimy; m++){
		    Location currentLoc = new Location(k,m);
		    //System.out.print(currentLoc);
		    if (world.mushroom_present(currentLoc)) paintMush(currentLoc);
		    if (world.strawb_present(currentLoc)) paintStrawb(currentLoc);
		    if (world.creature_present(currentLoc)) paintCreature(currentLoc);
		    if (world.monster_present(currentLoc)) paintMonster(currentLoc);
		    //		resetPaint(currentLoc);
		}
	    }
	    	}
    }


    //convert from upper left as (0,0) to (0,10)
    //hard coded for 10x10 grid
    public Location convertCoord(Location l){
	int xcoord = l.getX()+1;
	int ycoord = l.getY();
	switch(ycoord){
	case 0:
	    ycoord = 10;
	    break;
	case 1:
	    ycoord = 9;
	    break;
	case 2:
	    ycoord = 8;
	    break;
	case 3:
	    ycoord = 7;
	    break;
	case 4:
	    ycoord = 6;
	    break;
	    //no case 5 because it doesn't change
	case 6:
	    ycoord = 4;
	    break;
	case 7:
	    ycoord = 3;
	    break;
	case 8:
	    ycoord = 2;
	    break;
	case 9:
	    ycoord = 1;
	    break;
	default:
	    break;
	}
	
        return new Location(xcoord,ycoord);
    }
    
    public void paintCreature(Location l){
	if (gfx!=null){
	    //use creatureColor
	    //	System.out.println("paintCreature:" + l);
	    gfx.setPaintMode();
	    gfx.setColor(creatureColor);
	    Location converted = convertCoord(l);
	    //	System.out.println("converted:" + converted);
	    //	Location swapXY = new Location(converted.getY(),converted.getX());
	    Rectangle rect = cellRectangle(converted);
	    gfx.fillRect(rect.x, rect.y, rect.width, rect.height);
	    //	gfx.fillRect(rect.y,rect.x,rect.width,rect.height);
	}
    }

    public void paintMonster(Location l){
	if (gfx!=null){
	    //use monsterColor
	    //	System.out.println("paintMonster:" + l);
	    gfx.setPaintMode();
	    gfx.setColor(monsterColor);
	    Rectangle rect = cellRectangle(convertCoord(l));
	    //	gfx.fillRect(rect.y,rect.x,rect.width,rect.height);
	    gfx.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
    }

    public void paintStrawb(Location l){
	if (gfx!=null){
	    //use strawbColor
	    //	System.out.println("paintStrawb:" + l);
	    gfx.setPaintMode();
	    gfx.setColor(strawbColor);
	    Rectangle rect = cellRectangle(convertCoord(l));
	    gfx.fillRect(rect.x, rect.y,rect.width,rect.height);
	}
    }

    public void paintMush(Location l){
    	//use mushColor
	//	System.out.println("paintMush:" + l);
	gfx.setPaintMode();
	gfx.setColor(mushColor);
	Rectangle rect = cellRectangle(convertCoord(l));
	gfx.fillRect(rect.x, rect.y,rect.width,rect.height);
    }

    public void paintBackground(Location l){
	//use backgroundColor
	gfx.setPaintMode();
	gfx.setColor(backgroundColor);
	Rectangle rect = cellRectangle(convertCoord(l));
	gfx.fillRect(rect.x,rect.y,rect.width,rect.height);
    }
    
    
    public void paint(){
	this.paint(gfx);
    }

    public void paint(Graphics g){
	paintGrid();
    }

    // public void init(){
    // 	world = new SpeciesWorld();
	
    // }

    public void resetPaint(Location l){
	if (gfx!=null){
	    //If the creature died by completing the action
	    //repaint the cell accordingly. If any of the objects share a cell,
	    //Creatures will be painted over strawberries and mushrooms, and
	    //Monsters will be painted over all other objects.
	    paintBackground(l);
	    if (world.strawb_present(l)) paintStrawb(l);
	    if (world.mushroom_present(l)) paintMush(l);
	    if (world.creature_present(l)) paintCreature(l);
	    if (world.monster_present(l)) paintMonster(l);
	}
    }

    /*********** METHODS THAT SETS UP THE SIMULATION *********/
    /** This is the method that puts creatures to work
     *
     */
    public void goCreatures(){
	Creature c = world.creatures[0];
	Location originalLocation = c.getLoc();
	world.doAction(c);
	Location newLocation = c.getLoc();
	System.out.println("original:"+originalLocation + " " + convertCoord(originalLocation));
	System.out.println("new:"+newLocation + " " + convertCoord(newLocation));
	// for (int i = 0; i<world.creatures.length;i++){
	//     Creature c = world.creatures[i];
	//     
	//     System.out.println("original:"+originalLocation + " " + convertCoord(originalLocation));
	//     if (c.isAlive()){
	// 	world.doAction(c);
	// 	
	// 	System.out.println("new:"+newLocation + " " + convertCoord(newLocation));
	//     }
	// }
	
        // for (int i = 0; i<world.creatures.length; i++){
	//     Creature c = world.creatures[i];
	//     Location oldLoc = c.getLoc();
	//     System.out.println(c);
	//     System.out.println(oldLoc);
	//     //only run if the current creature is alive
	//     if (c.isAlive()){
	// 	world.doAction(c);
	// 	Location newLoc = c.getLoc();
	// 	System.out.println(newLoc);
	// 	//check to see if the creature is in the same cell as a monster
	// 	if (world.monster_present(newLoc)) c.kill();

	// 	//update cell colors if the creature has died or moved
	// 	if (!c.isAlive()){
	// 	    System.out.println("Dead");
	// 	    // resetPaint(oldLoc);
	// 	}else if (!oldLoc.equals(newLoc)){
	// 	    System.out.println("position is different");
	// 	    // resetPaint(oldLoc);
	// 	    //		    resetPaint(newLoc);
	// 	}
		
	//     }
	    	   
	// }
    }
    
    public static void main (String[] args){
	
    }
    
}

