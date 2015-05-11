/**
 * COSC343 Assignment 2
 * WorldGUI.java
 * Purpose: Graphical representation for SpeciesWorld
 * 
 * WorldGUI.java uses some adapted code from Wellesley College's 2006 cs111 BuggleWorld.java
 * 
 * @author: Jazlyn Akaka
 * @version: 11/5/15
 */

import java.awt.*;
import java.awt.event.*; // ***
import java.applet.*;  
import java.util.*;
import javax.swing.*;  // ****

public class WorldGUI extends JApplet{// implements ActionListener{
    public int x = 10; //dimx
    public int y = 10; //dimy
    private static WorldGrid grid = new WorldGrid();
    private final static Color backgroundColor = Color.gray;
    private JPanel worldPanel;
    public static SpeciesWorld currentWorld;

    public static void runAsApplication(final String name){
	Runnable runner = new Runnable() {
		public void run(){
		    JFrame.setDefaultLookAndFeelDecorated(true); //enable window decorations
		    JFrame frame = new JFrame(name);
		    frame.setSize(534,534); //Default frame size
		    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		    
		    frame.add(grid, BorderLayout.CENTER);
		    frame.setVisible(true);
		    frame.setVisible(true);
		}
	    };

	javax.swing.SwingUtilities.invokeLater(runner);
    }


    public void init(){
	//	System.out.println("GUI init()");
	currentWorld = grid.world;
	createGUI(); //Create SpeciesWorld GUI. This allocates space for the grid, but does not draw it yet
	grid.paint();
    }

    public void createGUI(){
	Container c = getContentPane();
	c.setLayout(new BorderLayout());
	c.add("Center",grid);
	
	
    }

    public void setDims (int xdim, int ydim){
	this.x = xdim;
	this.y = ydim;
    }
    
    public static void main (String[] args){
	runAsApplication("SpeciesWorld");
    }
}

/*****************************************************************************/
/*
 * Purpose: A rectangular area of the SpeciesWorld applet that 
 * displays the state of the world
 *
 * WorldGrid is an adapted version of Wellesley College's 2006 cs111 BuggleGrid
 */ 
class WorldGrid extends Canvas{ //implements MouseListener {
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
    private Graphics gfx;

    public Color getBackColor(){
	return backgroundColor;
    }

    // public void init(){
    // 	this.addMouseListener(this);
    // }

    public WorldGrid(){
        init();
    }


    public void makeGrid(){
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
	//	System.out.println("paintGrid");
	makeGrid();
	gfx = this.getGraphics();

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

    public void paintMonster(Location l){
    	//use monsterColor
	//	System.out.println("paintMonster:" + l);
	gfx.setPaintMode();
    	gfx.setColor(monsterColor);
	Rectangle rect = cellRectangle(convertCoord(l));
	//	gfx.fillRect(rect.y,rect.x,rect.width,rect.height);
		gfx.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void paintStrawb(Location l){
    	//use strawbColor
	//	System.out.println("paintStrawb:" + l);
	gfx.setPaintMode();
	gfx.setColor(strawbColor);
	Rectangle rect = cellRectangle(convertCoord(l));
	gfx.fillRect(rect.x, rect.y,rect.width,rect.height);
    }

    public void paintMush(Location l){
    	//use mushColor
	//	System.out.println("paintMush:" + l);
	gfx.setPaintMode();
	gfx.setColor(mushColor);
	Rectangle rect = cellRectangle(convertCoord(l));
	gfx.fillRect(rect.x, rect.y,rect.width,rect.height);
    }
    
    public void paint(){
	this.paint(gfx);
    }

    public void paint(Graphics g){
	paintGrid();
    }

    public void init(){
	world = new SpeciesWorld();
	//    this.start();
	
    }

    /*********** METHODS THAT SETS UP THE SIMULATION *********/
    /** This is the method that starts the mayhem
     *
     */
    public void start(){
	while (true){
	    








	}
    }
    
    public static void main (String[] args){

    }
    
}

