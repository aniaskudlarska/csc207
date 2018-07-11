package ca.utoronto.utm.jugpuzzle;


import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ButtonActionListener implements ActionListener {
	
	JButton[] arrayButtons;
	int[] result;
	JugPuzzle jugPuzzle;
	JLabel view;
	
	ButtonActionListener(JButton[] arrayButtons, int[] result, JugPuzzle jugPuzzle, JLabel view) {
		this.arrayButtons = arrayButtons;
		this.result = result;
		this.jugPuzzle = jugPuzzle;
		this.view = view;

		arrayButtons[3].setEnabled(false);
		arrayButtons[4].setEnabled(false);
		arrayButtons[5].setEnabled(false);
		
	}
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.equals(this.arrayButtons[0]) ||
				source.equals(this.arrayButtons[1]) ||
				source.equals(this.arrayButtons[2])
				) {
			result[0] = Integer.parseInt(source.getText());
			arrayButtons[0].setEnabled(false);
			arrayButtons[1].setEnabled(false);
			arrayButtons[2].setEnabled(false);
			arrayButtons[3].setEnabled(true);
			arrayButtons[4].setEnabled(true);
			arrayButtons[5].setEnabled(true);
			
		}
		else {
			result[1] = Integer.parseInt(source.getText());

			arrayButtons[3].setEnabled(false);
			arrayButtons[4].setEnabled(false);
			arrayButtons[5].setEnabled(false);
			arrayButtons[0].setEnabled(true);
			arrayButtons[1].setEnabled(true);
			arrayButtons[2].setEnabled(true);
			jugPuzzle.move(result[0], result[1]);
			if (this.jugPuzzle.getIsPuzzleSolved() == true) {
				this.view.setText("You've Won!");

				arrayButtons[0].setEnabled(false);
				arrayButtons[1].setEnabled(false);
				arrayButtons[2].setEnabled(false);
			}
			
			
		}
		
				
		
		
}
	
	public void restartApplication()
	{
	 
	}
}
