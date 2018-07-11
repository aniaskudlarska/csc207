package ca.utoronto.utm.jugpuzzle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JugPuzzleActionListener implements ActionListener {
	static JugPuzzle jugPuzzle = new JugPuzzle();
	private int t, f;
	private ArrayList<JButton> buttons;
	public static JLabel youWon;
	private JButton button;
	
	/**
	 * Creates a constructor for the ActionListener 
	 * to move liquid from jug f to jug t in the GUI game
	 * @param f int variable that represents the jug from which liquid is taken out
	 * @param t int variable that represents the jug into which liquid is poured into
	 */
	JugPuzzleActionListener(int f, int t) {
		this.f=f;
		this.t=t;
	}
	
	JugPuzzleActionListener(JLabel win) {
		this.youWon = win;
	}
	
	
	/**
	 * Creates a variable button in the class
	 * @param button JButton that is used in restart and quit
	 */
	public JugPuzzleActionListener(JButton button) {
		this.button = button;
	}
	
	/**
	 * Determines which button was pressed by the user and acts accordingly.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String stringVersion = e.getActionCommand(); // This sets the action command into a String which is easier to manipulate
		
		
		if (stringVersion == "Jug0" || stringVersion == "Jug1" || stringVersion == "Jug2") { 
		
			/**
			 * If a user clicked one of the jugs, the if statement checks if the click was the second one. If it was the 
			 * second click, it sets this.t to the value of the jug which the user clicked, and moves the liquid from the jug
			 * that was clicked last.
			 */
			if (JugPuzzleGUIController.clicker%2 == 0) { 
				this.t= Character.getNumericValue(stringVersion.charAt(3));
				jugPuzzle.move(JugPuzzleGUIController.f, this.t);
				JugPuzzleGUIController.f = 0;
				this.t = 0;
				JugPuzzleGUIController.clicker++; // The jug the user clicked is being spilled into and the counter is updated to indicate as such.	
			}
			
			/**
			 * If the user clicked on the jug and it was the first click, it sets the JugPuzzleGUIController.f variable
			 * equal to the value of the jug clicked.
			 */
			else {
				JugPuzzleGUIController.f =  Character.getNumericValue(stringVersion.charAt(3));
				JugPuzzleGUIController.clicker++;
			}
			
		}
		/**
		 * If the user presses the "Restart" button, all the liquid is spilled back into 
		 * the first jug and moves are reset so as to start fresh. It also enables all the 
		 * jug buttons so the user can make moves again	
		 */
		else if (stringVersion == "Restart") {
			jugPuzzle.restart();
			JugPuzzleGUIController.j1.setEnabled(true);
			JugPuzzleGUIController.j2.setEnabled(true);
			JugPuzzleGUIController.j3.setEnabled(true);
		}
		/**
		 * If the user presses the "Quit" button", the program terminates and closes the GUI
		 */
		else if (stringVersion == "Quit") {
	        System.exit(0);
		}
		
		/**
		 * Checks if the puzzle has been completed, in which case 
		 * it prints a congratulatory message, prompting the user to either restart or quit.
		 * Then, it disables all the jug buttons so the user cannot make a move after the game is over.
		 * */
		if (jugPuzzle.getIsPuzzleSolved() == true) {
			JugPuzzleGUIController.j1.setEnabled(false);
			JugPuzzleGUIController.j2.setEnabled(false);
			JugPuzzleGUIController.j3.setEnabled(false);
		}
		
	}

}
