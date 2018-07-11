package ca.utoronto.utm.jugpuzzle;

import java.awt.event.*;

public class JugPuzzleGUIController {
	
	private JugPuzzle jp;
	private JugPuzzleView jpview;
	private int selected;
	
	public JugPuzzleGUIController(){

		jp = new JugPuzzle();
		
		//Initialize GUI with listeners for jug selection and resetting the game.
		jpview = new JugPuzzleView(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int jug = Integer.parseInt(e.getActionCommand());
					if(selected == -1){
						selected = jug;
						jpview.disableButton(jug);
					}
					else{
						jpview.appendToLog(" " + (jp.getMoves() + 1) + ": Spilled from Jug" + selected + " to Jug" + jug + "\n");
						jp.move(selected,jug);
						resetSelection();
						
					}
				}
			},
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					jp.resetGame();
					jpview.clearLog();
					resetSelection();
				}
			});
		
		jp.addObserver(jpview);
		jp.notifyObservers();
		resetSelection();
	}
	/**
	 * Resets the selection variable in JugPuzzleGUIController and re-enables the button corresponding to the selection
	 * in JugPuzzleView.
	 */
	public void resetSelection(){
		selected = -1;
		if(!jp.getIsPuzzleSolved())
			jpview.enableAllButtons();
	}

	public static void main(String [] args){
		JugPuzzleGUIController jpgc = new JugPuzzleGUIController();
	}

}