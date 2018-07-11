package ca.utoronto.utm.jugpuzzle;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 * This class is responsible for displaying the game won label once the game has been won.
 * 
 * @author csc207student
 *
 */
public class GameWonLabelView extends JLabel implements Observer{

	private static final long serialVersionUID = 1L;
	
	/**
	 * GameWonLabelView constructor, we set the bounds of the label
	 * and the color of the text to white.
	 */
	GameWonLabelView() {
		setBounds(35, 75, 360, 13);
		setForeground(Color.WHITE);
	}
	
	/**
	 * No return, this function is responsible for updating this class, the view,
	 * since it is an observer of the JugPuzzle Model class. Once an update is made
	 * we simple set the JugPuzzle instance this class uses to the one passed as the 
	 * observable object parameter and then set the text of the label to the game won message.
	 * We need to check if the game has been won since other methods inside the JugPuzzle 
	 * model update this observer.
	 * 
	 * @param observable
	 *            Observable, an observable object i.e the JugPuzzle model
	 * @param arg
	 *            Object, an Object
	 */
	@Override
	public void update(Observable observable, Object arg1) {
		JugPuzzle jugPuzzle = (JugPuzzle)observable;
		if (jugPuzzle.getIsPuzzleSolved()) {
			setText("Congratulations, you won! You did it in " + jugPuzzle.getMoves() + " moves!");
		} else {
			setText("");
		}
	}

}
