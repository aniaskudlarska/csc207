package ca.utoronto.utm.jugpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * This class is only responsible for handling the event that the user
 * clicks the restart button. 
 * 
 * @author csc207student
 *
 */
public class JugPuzzleResetActionListener implements ActionListener{

	private JugPuzzle jugPuzzle;
	private JButton jug0Button, jug1Button, jug2Button;

	/**
	 * Constructor for new JugPuzzleResetActionListener,
	 * initializes the jugPuzzle which is used for the class. As
	 * well as the JButtons used for moving liquid to/from the
	 * Jugs in the actual JugPuzzle controller.
	 * 
	 * @param jp	
	 *            JugPuzzle, JugPuzzle that's used in the controller
	 * @param jug0	
	 *            JButton, used for moving liquid to/from the first Jug
	 * @param jug1	
	 *            JButton, used for moving liquid to/from the second Jug
	 * @param jug2	
	 *            JButton, used for moving liquid to/from the third Jug
	 */
	JugPuzzleResetActionListener(JugPuzzle jp, JButton jug0, JButton jug1, JButton jug2) {
		jugPuzzle = jp;
		jug0Button = jug0;
		jug1Button = jug1;
		jug2Button = jug2;
	}

	/**
	 * No return, resets the JugPuzzle and makes the buttons visible again.
	 */
	private void startOver() { 
		jugPuzzle.resetPuzzle();
		jug0Button.setVisible(true);
		jug1Button.setVisible(true);
		jug2Button.setVisible(true);
	}

	/**
	 * No return, this function calls the startOver function to restart the game.
	 * 
	 * @param event	
	 *            ActionEvent, an event triggered by pressing a Button
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		startOver();
	}

}
