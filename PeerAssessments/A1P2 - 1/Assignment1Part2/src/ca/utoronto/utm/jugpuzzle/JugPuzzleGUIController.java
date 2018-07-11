package ca.utoronto.utm.jugpuzzle;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This is the JugPuzzleGUIController, this class along with the ActionListeners are the controllers
 * for this MVC implementation of the JugPuzzle game. This class is responsible for essentially 
 * bringing all the components of the game together. It connects the Controller to Models and Models
 * to the Views. We initialize the bulk of the instances of each class that is needed to properly
 * run this GUI implementation of the JugPuzzle in this class.
 * 
 * @author csc207student
 *
 */
public class JugPuzzleGUIController {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/**
	 * Creates the JFrame, Buttons, Views, Models, Controller and ActionListeners here.
	 * We also add the views to the screen. We add observers to their respective 
	 * Models and we add action listeners to the buttons and finally we make sure the
	 * JFrame has a proper size and is visible.
	 */
	public static void createAndShowGUI() {
		
		// Models
		JugPuzzle jugPuzzle = new JugPuzzle();
		Jug jug0, jug1, jug2;
		jug0 = jugPuzzle.getJug(0);
		jug1 = jugPuzzle.getJug(1);
		jug2 = jugPuzzle.getJug(2);
		
		// Buttons and buttons array to allow to pass the buttons as a parameter of an object
		// with ease
		JButton jug0Button = new JButton("Jug 0");
		JButton jug1Button = new JButton("Jug 1");
		JButton jug2Button = new JButton("Jug 2");
		JButton quitButton = new JButton("Quit");
		JButton restartButton = new JButton("Restart");
		JButton[] buttons = {jug0Button, jug1Button, jug2Button, quitButton, restartButton};
		
		// Views and LabelViews, these are the views that actually put things on the screen
		Jug0GUIView jug0View = new Jug0GUIView(jug0);
		Jug1GUIView jug1View = new Jug1GUIView(jug1);
		Jug2GUIView jug2View = new Jug2GUIView(jug2);
		JugPuzzleGUIView jugPuzzleView = new JugPuzzleGUIView(jugPuzzle, buttons);
		GameWonLabelView gameWonView = new GameWonLabelView();
		HighScoreLabelView highScoreView = new HighScoreLabelView();
		
		// Controller
		// ActionListeners for moving, reseting the game and quiting the game respectively
		JugPuzzleMoveActionListener jugPuzzleMoveAL = new JugPuzzleMoveActionListener(jugPuzzle, jug0Button, jug1Button, jug2Button);
		JugPuzzleResetActionListener jugPuzzleResetAL = new JugPuzzleResetActionListener(jugPuzzle, jug0Button, jug1Button, jug2Button);
		JugPuzzleQuitActionListener jugPuzzleQuitAL = new JugPuzzleQuitActionListener();
		
		// Frame to allow to actually display things to the screen
		JFrame frame = new JFrame("Jug Puzzle Game"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Adding the views as observers to the Jug Models
		jug0.addObserver(jug0View);
		jug1.addObserver(jug1View);
		jug2.addObserver(jug2View);
		// Adding the views as observers to the JugPuzzle Model
		jugPuzzle.addObserver(jugPuzzleView);
		jugPuzzle.addObserver(gameWonView);
		jugPuzzle.addObserver(highScoreView);
        
		// Adding the ActionListeners to the buttons that they are made for respectively
		jug0Button.addActionListener(jugPuzzleMoveAL);
		jug1Button.addActionListener(jugPuzzleMoveAL);
		jug2Button.addActionListener(jugPuzzleMoveAL);
		restartButton.addActionListener(jugPuzzleMoveAL);
		restartButton.addActionListener(jugPuzzleResetAL);
		quitButton.addActionListener(jugPuzzleQuitAL);
		
		// Adding the views to the frame
        frame.getContentPane().add(gameWonView);
        frame.getContentPane().add(highScoreView);
        frame.getContentPane().add(jug0View);
        frame.getContentPane().add(jug1View);
		frame.getContentPane().add(jug2View);
        frame.getContentPane().add(jugPuzzleView);
	
		frame.setSize(360, 400);
		frame.setVisible(true);
	}
}
