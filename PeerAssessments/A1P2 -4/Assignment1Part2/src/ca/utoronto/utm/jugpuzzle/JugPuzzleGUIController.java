package ca.utoronto.utm.jugpuzzle;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable; 

public class JugPuzzleGUIController extends Observable {
	public static int clicker = 1;
	public static int f = -1;
	static JButton j1 = null, j2 = null, j3 = null;
	

	public static void createAndShowGUI() {
		
		// Initializes all variables
		int jbutton = 0;
		int jbutton2 = 0;
		
		// Creates all the views required
		JugView0 jug0 = new JugView0();
		JugView1 jug1 = new JugView1();
		JugView2 jug2 = new JugView2();
		JugViewMove move = new JugViewMove();
		JugViewGameWon winner = new JugViewGameWon();
		
		//Makes the GUI into a GridBagLayout
		GridBagConstraints gbc = new GridBagConstraints();
		JFrame f = new JFrame("Jug GUI");
		f.getContentPane().setLayout(new GridBagLayout());
		gbc.insets = new Insets(15, 10, 10, 15);
		
		// Makes all the buttons and the amounts of liquid in each jug
		j1 = new JButton("Jug0");
		gbc.gridx = 0;
		gbc.gridy = 1;
		f.getContentPane().add(j1, gbc);
		
		JugPuzzleActionListener.jugPuzzle.addObserver(jug0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		f.getContentPane().add(jug0,gbc);
		
		
		j2 = new JButton("Jug1");
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		f.getContentPane().add(j2, gbc);
		
		JugPuzzleActionListener.jugPuzzle.addObserver(jug1);
		gbc.gridx = 1;
		gbc.gridy = 0;
		f.getContentPane().add(jug1, gbc);
		
		
		j3 = new JButton("Jug2");
		gbc.gridx = 2;
		gbc.gridy = 1;
		f.getContentPane().add(j3,gbc);
		
		JugPuzzleActionListener.jugPuzzle.addObserver(jug2);
		gbc.gridx = 2;
		gbc.gridy = 0;
		f.getContentPane().add(jug2, gbc);
		
		
		
		// Implements the 3 buttons into the action listener
		JugPuzzleActionListener mb1 = new JugPuzzleActionListener(jbutton, jbutton2);
		JugPuzzleActionListener mb2 = new JugPuzzleActionListener(jbutton, jbutton2);
		JugPuzzleActionListener mb3 = new JugPuzzleActionListener(jbutton, jbutton2);
	
		j1.addActionListener(mb1);	
		j2.addActionListener(mb2);	
		j3.addActionListener(mb3);
	
		// Sets number of moves and adds the field into the GUI
		JugPuzzleActionListener.jugPuzzle.addObserver(move);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.CENTER;
		f.add(move, gbc);
		
		// Creates the text box with the instructions and congratulatory message if the user has won
		JugPuzzleActionListener.jugPuzzle.addObserver(winner);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		f.getContentPane().add(winner,gbc);
		
		// Makes the restart and quit button
		JButton restart, quit;

		restart = new JButton("Restart");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		f.getContentPane().add(restart,gbc);
		
		quit = new JButton("Quit");
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		f.getContentPane().add(quit,gbc);
		
		// Implements restart and quit into the action listener
		JugPuzzleActionListener res = new JugPuzzleActionListener(restart);
		JugPuzzleActionListener q = new JugPuzzleActionListener(quit);

		restart.addActionListener(res);	
		quit.addActionListener(q);	
		
		
		 
		
		f.setTitle("Jug Puzzle");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.pack();
		
		
	}
	

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}