/**
 * COSC343 Assignment 2
 * SimulationPanel.java
 * Purpose: 
 *
 * Adapted from CS230 Final Project
 * 
 * @author: Jazlyn Akaka
 * @version: 12/5/15
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class SimulationPanel extends JPanel{
    private SpeciesWorld world;
    private GridPanel grid;

    /**
     * CONSTRUCTOR: Creates the full panel with grid and heading and button
     */
    public SimulationPanel(){
	world = new SpeciesWorld();
	setBackground(Color.lightGray);
	grid = new GridPanel(world);
	add(grid);
	
    }

  

}
