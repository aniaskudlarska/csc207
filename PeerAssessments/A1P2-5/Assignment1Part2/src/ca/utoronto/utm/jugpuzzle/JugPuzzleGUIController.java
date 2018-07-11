package ca.utoronto.utm.jugpuzzle;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JugPuzzleGUIController 
{

	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() 
	{
		JFrame frame = new JFrame("Jug Puzzle"); // JFrame title

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes the jug puzzle frame

		frame.getContentPane().setLayout(new CardLayout()); // Sets a flow layout

		// Create 3 buttons for the jugs
		JButton jug0 = new JButton("8");
		JButton jug1 = new JButton("5");
		JButton jug2 = new JButton("3");

		// Adds jug buttons to the content pane
		frame.getContentPane().add(jug0);
		frame.getContentPane().add(jug1);
		frame.getContentPane().add(jug2);

		// Create button press event handlers
		//JugPuzzleActionListener mb1 = new JugPuzzleActionListener(jt);
		//JugPuzzleActionListener mb2 = new JugPuzzleActionListener(t);
		

		// Tell the buttons who they should call when they are pressed
		//b1.addActionListener(mb1);
		//b2.addActionListener(mb2);

		// tell the frame to pack in all the components
		// this is done according to the layout manager
		frame.pack();

		// lets see the frame
		frame.setVisible(true);
		
	}
	
}
