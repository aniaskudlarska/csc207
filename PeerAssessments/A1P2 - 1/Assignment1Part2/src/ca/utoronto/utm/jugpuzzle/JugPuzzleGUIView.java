package ca.utoronto.utm.jugpuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is responsible for displaying the view for the third Jug.
 * We implement observer as this class observes the Jug class Model. Here
 * we also display the capacity of the Jug and the amount of liquid inside
 * the Jug. We also actually draw the amount of liquid inside the Jug with the
 * use of graphics. In this class we also display image for this Jug. 
 * 
 * @author csc207student
 *
 */
public class JugPuzzleGUIView extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JugPuzzle jugPuzzle;
	private JButton jug0, jug1, jug2, quit, restart;

	/**
	 * JugPuzzleGUIView controller, here we set the background
	 * color to black and set the bounds of the Panel. We also
	 * set the class instance of JugPuzzle to the one passed in the
	 * parameter. The Jug buttons, restart and quit buttons we 
	 * also set those buttons to the ones passed in the array respectively.
	 * 
	 * @param jp
	 *            JugPuzzle, the JugPuzzle used to play the game
	 * @param buttons
	 *            Buttons[], an array of buttons used for the game
	 */
	JugPuzzleGUIView(JugPuzzle jp, JButton[] buttons) {
    	setBackground(Color.BLACK);
    	setBounds(0, 0, 360, 150);
		jugPuzzle = jp;
		jug0 = buttons[0];
		jug1 = buttons[1];
		jug2 = buttons[2];
		quit = buttons[3];
		restart = buttons[4];
	}

	/**
	 * No return, this function is responsible for actually displaying things
	 * to the JPanel and ultimately the screen. We use the inherited method paintComponent
	 * from the parent class JPanel. We set the bounds for the JPanel and display the
	 * number of moves and the current highscore of the game, if there is one. In this 
	 * function we also set the bounds of the buttons respectively and add them to the JPanel
	 * which ultimately displays them to the screen.
	 * 
	 * @param g
	 *            Graphics variable
	 */
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
    	setBounds(0, 0, 360, 150);
        g.setColor(Color.WHITE);
        g.drawString("Moves: " + jugPuzzle.getMoves(), 5, 20);
        if (jugPuzzle.getHighScore() == -1) {
        	g.drawString("Highscore: none", 5, 50);
        } else {
        	g.drawString("Highscore: " + jugPuzzle.getHighScore(), 5, 50);
        }
        jug0.setBounds(27, 80, 70, 20);
        jug1.setBounds(142, 80, 70, 20);
        jug2.setBounds(250, 80, 70, 20);
		quit.setBounds(135, 10, 80, 20);
		restart.setBounds(245, 10, 80, 20);
        add(jug0); 
        add(jug1); 
        add(jug2);
        add(quit);
        add(restart);
    }
	
	/**
	 * No return, this function is responsible for updating this class, the view,
	 * since it is an observer of the JugPuzzle Model class. Once an update is made
	 * we simple set the JugPuzzle instance this class uses to the one passed as the 
	 * observable object parameter and then repaint the Panel. 
	 * 
	 * @param observable
	 *            Observable, an observable object i.e the Jug model
	 * @param arg
	 *            Object, an Object
	 */
	@Override
	public void update(Observable observable, Object arg1) {
        jugPuzzle = (JugPuzzle)observable;
        repaint();
    }

}
