package ca.utoronto.utm.jugpuzzle;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

public class JugViewMove extends JLabel implements Observer{
	@Override
	public void update(Observable o, Object arg) {
		JugPuzzle jugPuzzle = (JugPuzzle)o;
		this.setText("Number of Moves: " + jugPuzzle.getMoves());	
		
	}

}
