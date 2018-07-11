package ca.utoronto.utm.jugpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is only responsible for handling the event that the 
 * quit button is pressed. 
 * 
 * @author csc207student
 *
 */
public class JugPuzzleQuitActionListener implements ActionListener {
	
	/**
	 * Quits the game.
	 */
	public void quit() {
		System.exit(0);
	}
	
	/**
	 * No returns, this function calls the quit function once the quit button is pressed.
	 * 
	 * @param event	
	 *            ActionEvent, an event triggered by pressing a Button
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		quit();
	}

}
