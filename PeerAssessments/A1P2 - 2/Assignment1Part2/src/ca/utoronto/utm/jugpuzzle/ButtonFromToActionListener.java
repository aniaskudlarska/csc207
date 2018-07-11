package ca.utoronto.utm.jugpuzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ButtonFromToActionListener implements ActionListener {
	
	private int fromJug;
	private int toJug;
	protected JugPuzzle jugPuzzle;
	protected JLabel view;
	
	
	ButtonFromToActionListener(int fromJug, int toJug, JugPuzzle jugPuzzle, JLabel view) {
		this.fromJug = fromJug;
		this.toJug = toJug;
		this.jugPuzzle = jugPuzzle;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		this.jugPuzzle.move(fromJug, toJug);

		if (this.jugPuzzle.getIsPuzzleSolved() == true) {
			this.view.setText("hi");
		}
		
}
}
