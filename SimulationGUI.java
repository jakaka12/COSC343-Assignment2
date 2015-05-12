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
import java.awt.Dimension.*;

public class SimulationGUI{

    /**
     * Main method for Species World program
     *
     */ 
    public static void main(String[] args){
	//creates and shows a Frame
	JFrame frame = new JFrame("SpeciesWorld");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new SimulationPanel(),BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);


    }




}
