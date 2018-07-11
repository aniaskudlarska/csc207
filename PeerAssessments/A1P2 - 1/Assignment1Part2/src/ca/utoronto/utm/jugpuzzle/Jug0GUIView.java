package ca.utoronto.utm.jugpuzzle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class is responsible for displaying the view for the first Jug.
 * We implement observer as this class observes the Jug class Model. Here
 * we also display the capacity of the Jug and the amount of liquid inside
 * the Jug. We also actually draw the amount of liquid inside the Jug with the
 * use of graphics. In this class we also display image for this Jug. 
 * 
 * @author csc207student
 *
 */
public class Jug0GUIView extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	private Jug jug;
	private BufferedImage jugImage;
	
	/**
	 * Constructor for the Jug0GUIView class, here we set the background
	 * color and set the bounds for the panel. We also set the classes Jug
	 * instance to the Jug passed as the parameter for the class, i.e the first
	 * Jug in the JugPuzzle.
	 * 
	 * @param j		
	 *            Jug, the first Jug in the JugPuzzle
	 */
	Jug0GUIView(Jug j) {
		setBackground(Color.BLACK);
		setBounds(0, 100, 140, 300);
		jug = j;
	}
	
	/**
	 * No return, this function is responsible for actually displaying things
	 * to the JPanel and ultimately the screen. We use the inherited method paintComponent
	 * from the parent class JPanel. We set the bounds for the JPanel and
	 * we also display the image for the Jug. In this function we also display the
	 * liquid level using Graphics and a switch statement, we finally display the
	 * amount filled and capacity of the Jug.
	 * 
	 * @param g	
	 *            Graphics, graphics 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBounds(0, 100, 140, 300);
        try {                
        	jugImage = ImageIO.read(new File("res/jug0.png"));
        	g.drawImage(jugImage, 15, 60, this);
        } catch (IOException ioe) {
           	 System.out.println(ioe);
        }
        g.setColor(Color.BLUE);
        switch (jug.getAmount()) {
        	case 0: break;
        	case 1: g.fillOval(52, 74, 23, 5); break;
        	case 2: g.fillOval(45, 72, 36, 7); break; 
        	case 3: g.fillOval(43, 70, 40, 9); break; 
        	case 4: g.fillOval(40, 67, 45, 12); break; 
        	case 5: g.fillOval(36, 67, 52, 12); break;
        	case 6: g.fillOval(34, 64, 56, 14); break;
        	case 7: g.fillOval(34, 64, 56, 15); break;
        	case 8: g.fillOval(34, 60, 56, 18); break;
        	default: break;
        }
        g.drawString("Filled: " + jug.getAmount(), 40, 200);
        g.drawString("Capacity: " + jug.getAmount(), 30, 220);
    }

	/**
	 * No return, this function is responsible for updating this class, the view,
	 * since it is an observer of the Jug Model class. Once an update is made
	 * we simple set the Jug instance this class uses to the one passed as the 
	 * observable object parameter and then repaint the Panel.
	 * 
	 * @param observable
	 *            Observable, an observable object i.e the Jug model
	 * @param arg
	 *            Object, an Object
	 */
	@Override
	public void update(Observable observable, Object arg) {
		jug = (Jug)observable;
		repaint();
	}

}
