package ca.utoronto.utm.jugpuzzle;
import javax.swing.*;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
public class JugPuzzleView extends JFrame implements Observer 
{
	private final int NUMBER_OF_JUGS = 3;
	
	private ActionListener jugListener,resetListener;
	private int bestscore;
	private boolean guiInitialized;
	
	private JButton[] buttons;
	private JLabel[] labels;
	private JTextArea gameLog;
	
	
	public JugPuzzleView(ActionListener jugListener, ActionListener resetListener)
	{
		super("JugPuzzle");
		JLabel[] labels = new JLabel[5];
		JButton[] buttons = new JButton[3];
		for(int i=0; i < labels.length; i++){
			labels[i] = new JLabel();
			if(i<3) buttons[i] = new JButton("Jug " + i);
		}
		
		this.labels = labels;
		this.buttons = buttons;
		bestscore = -1;
		guiInitialized = false;

		this.jugListener = jugListener;
		this.resetListener = resetListener;
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Updates the GUI when changes are made to the JugPuzzle model
	 * @param o The JugPuzzle model being observed.
	 * @param arg An argument passed from the notfyObservers() method of o
	 */
	public void update(Observable o, Object arg){
		// Update the Jug Strings.
		JugPuzzle jp = (JugPuzzle)o;
		String[] jugStrs = getJugStrings(jp);
		
		for(int i = 0; i < NUMBER_OF_JUGS; i++){
			labels[i].setText(jugStrs[i]);
		}
		
		labels[3].setText("Moves : " + jp.getMoves()); 
		// Check if the game is won and take the appropriate action if so. 
		if(jp.getIsPuzzleSolved()){
			disableAllButtons();
			if(jp.getMoves() < bestscore || bestscore == -1){
				bestscore = jp.getMoves();
				labels[4].setText("Best Score: " + bestscore);
				appendToLog("Congratulations you win!\n Press \"New Game\" to play again\n");
			}
			
		}
		else if(bestscore == -1) labels[4].setText("Best Score: None");
		
		//Initialize GUI if this is the first time updating
		if(!guiInitialized){
			initializeGUI();
			guiInitialized = true;
			setVisible(true);
		}
	}
	
	/**
	 * Adds and positions all the components of the GUI
	 */
	public void initializeGUI(){
		GridBagConstraints c = new GridBagConstraints();
		Container cp = this.getContentPane();
		cp.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		
		// Add Jug labels and buttons
		for(int i = 0; i < NUMBER_OF_JUGS; i++){
			c.gridx = i;
			c.gridy = 0;
			labels[i].setHorizontalAlignment(SwingConstants.HORIZONTAL);
			cp.add(labels[i],c);
			c.gridy = 1;
			cp.add(buttons[i],c); buttons[i].addActionListener(jugListener); buttons[i].setActionCommand(Integer.toString(i)); 
		}
		
		// Add moves label
		c.gridy = 2;
		c.gridx = 0;
		cp.add(labels[3],c);
		
		// Add best score label
		c.gridx = 2;
		cp.add(labels[4],c);
		
		// Add game log text-box
		c.gridy = 3;
		c.gridx = 0;
		c.gridwidth = 3;
		c.ipady = 40;
		gameLog = new JTextArea();
		gameLog.setEditable(false);
		cp.add(new JScrollPane(gameLog),c);
		
		// Add new game button
		JButton b;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridy = 4;
		c.gridx = 0;
		b = new JButton("New Game"); cp.add(b,c); b.addActionListener(resetListener);
		
		// Add exit button
		c.gridx = 2;
		b = new JButton("Exit"); cp.add(b,c);
		b.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();	
			}
		}); 
		this.pack();
	}
	
	/**
	 * Enables all Buttons used for selecting Jugs.
	 */
	public void enableAllButtons(){
		for(JButton button : buttons)
			button.setEnabled(true);
	}
	
	/**
	 * Disables all button used for selecting Jugs.
	 */
	public void disableAllButtons(){
		for(JButton button : buttons)
			button.setEnabled(false);
	}
	
	/** 
	 * Disables a specific Jug button 
	 * @param i
	 */
	public void disableButton(int i){
		buttons[i].setEnabled(false);
	}
	
	/**
	 * Appends s to the JTextArea for the game log.
	 * @param s A string containing the message to be appended.
	 */
	public void appendToLog(String s){
		gameLog.append(s);
	}
	
	/**
	 * Blanks the JTextArea for the game log.
	 */
	public void clearLog(){
		gameLog.setText("");
	}
	
	/**
	 * Generates an array containing the string representations of the Jugs in the JugPuzzle jp.
	 * @param jp The JugPuzzle being looked at.
	 * @return An array containing the string representation of the jugs in jp.
	 */
	public static String[] getJugStrings(JugPuzzle jp){
		Jug[] jugs = jp.getJugs();
		String[] s = {jugs[0].toString(),jugs[1].toString(),jugs[2].toString()};
		return s;
	}
}
