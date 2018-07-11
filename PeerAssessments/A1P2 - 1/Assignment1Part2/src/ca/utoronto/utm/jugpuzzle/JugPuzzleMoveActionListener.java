package ca.utoronto.utm.jugpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * This class is responsible for handling the event that the user wants to
 * move liquid to and from Jugs. This class is under the assumption that
 * once a button is pressed it is the Jug the user wants to move liquid 
 * from and once another button is pressed, that is the Jug the user wants 
 * to move liquid to. We also check if the game is over, once it is we make
 * the buttons disappear and set the new highscore (if it beat the previous one).
 * we make the buttons 
 * 
 * @author csc207student
 *
 */
public class JugPuzzleMoveActionListener implements ActionListener{

	private JugPuzzle jugPuzzle;
	private int fromJug, toJug;
	private JButton jug0Button, jug1Button, jug2Button;
	
	/**
	 * Constructor for new JugPuzzleMoveActionListener,
	 * initializes jugPuzzle for the class and the from and
	 * to variables which store the Jug from which the user wants
	 * to move liquid and the Jug to which the user wants to move liquid.
	 * As well as the JButtons used for moving liquid to/from the
	 * Jugs in the actual JugPuzzle controller.
	 * 
	 * @param jp	
	 *            JugPuzzle, the JugPuzzle used for the game
	 * @param jug0 
	 *            JButton, used for moving liquid to/from the first Jug
	 * @param jug1	
	 *            JButton, used for moving liquid to/from the second Jug
	 * @param jug2	
	 *            JButton, used for moving liquid to/from the third Jug
	 */
	JugPuzzleMoveActionListener(JugPuzzle jp, JButton jug0, JButton jug1, JButton jug2) {
		jug0Button = jug0;
		jug1Button = jug1;
		jug2Button = jug2;
		jugPuzzle = jp;
		fromJug = -1;
		toJug = -1;
	}
	
	/**
	 * Moves liquid from one Jug to another and also updates
	 * the View. Once a move is made we reset the Jug numbers
	 * the user wants to move liquid to/from. Once a move is
	 * made we also check if the JugPuzzle is solved, if it is
	 * we set the new highscore and remove the buttons from the
	 * screen,
	 * 
	 * @param from	
	 *            integer, the Jug to move liquid from
	 * @param to 
	 *            integer, the Jug to move liquid to
	 */
	private void moveLiquid(int from, int to) {
		jugPuzzle.move(from, to);
		if (jugPuzzle.getIsPuzzleSolved()) {
			jugPuzzle.setHighScore(jugPuzzle.getMoves());
			jug0Button.setVisible(false);
			jug1Button.setVisible(false);
			jug2Button.setVisible(false);
		}
		fromJug = -1;
		toJug = -1;
	}
	
	/**
	 * No return, this function handles what happens when buttons are pressed. 
	 * Once a button is pressed it is assumed that the button pressed represents the
	 * Jug to move liquid from, then next time a button is pressed it is assumed that
	 * button was for the Jug to move liquid to. If the retart button is pressed at any
	 * moment we need to reset the Jug index for the Jug(s) the user wants to move liquid
	 * from/to.
	 * 
	 * @param event	
	 *            ActionEvent, an event triggered by pressing a Button
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Restart") {
			fromJug = -1;
			toJug = -1;
			return;
		}
		if (fromJug == -1) {
			fromJug = Integer.parseInt(event.getActionCommand().substring(4));
		} else {
			toJug = Integer.parseInt(event.getActionCommand().substring(4));
			moveLiquid(fromJug, toJug);
		}
	}

}
