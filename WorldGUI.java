/**
 * COSC343 Assignment 2
 * WorldGUI.java
 * Purpose: Graphical representation for SpeciesWorld
 * 
 * WorldGUI.java uses some adapted code from Wellesley College's 2006 cs111 BuggleWorld.java
 * and Wellesley College's spring '13 SimpleAnimationPlayer.java
 * 
 * @author: Jazlyn Akaka
 * @version: 11/5/15
 */

import java.awt.*;
import java.awt.event.*; // ***
import java.applet.*;  
import java.util.*;
import javax.swing.*;  // ****

public class WorldGUI extends JApplet{
    public int x = 10; //dimx
    public int y = 10; //dimy
    public static SpeciesWorld currentWorld = new SpeciesWorld();
    private static WorldGrid grid = new WorldGrid(currentWorld);
    private final static Color backgroundColor = Color.gray;
    private static JPanel controlPanel;
    protected static JButton[] buttons = grid.getButtons();
    
    public static void runAsApplication(final String name){
	Runnable runner = new Runnable() {
		public void run(){
		    ButtonListener listener = new ButtonListener();
		    JFrame.setDefaultLookAndFeelDecorated(true); //enable window decorations
		    JFrame frame = new JFrame(name);
		    controlPanel = new JPanel();
		    for (int i = 0; i<buttons.length; i++){
			controlPanel.add(buttons[i]);
			buttons[i].addActionListener(listener);
		    }
		    frame.setSize(534,534); //Default frame size
		    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		    frame.getContentPane().add(controlPanel,BorderLayout.NORTH);
		    frame.getContentPane().add(grid, BorderLayout.CENTER);
		    frame.pack();
		    frame.setVisible(true);
		    // try{
		    // 	Thread.sleep(2000);
		    // }catch(InterruptedException ex){
		    // 	Thread.currentThread().interrupt();
		    // }
		    //grid.goCreatures();
		}
	    };
	javax.swing.SwingUtilities.invokeLater(runner);
    }

    // public void JPanel makeControlPanel(){
    // 	JPanel p = new JPanel();
    // 	for (int i = 0; i<buttons.length; i++){
    // 	    p.add(buttons[i]);
    // 	}
    // 	return p;
    // }

    private static class ButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    if (event.getSource()==buttons[0]){
		currentWorld = new SpeciesWorld();
		grid = new WorldGrid(currentWorld);
		grid.paintGrid();
	    }

	}
    }


    
    public static void main (String[] args){
	runAsApplication("SpeciesWorld");

	
    }
}
