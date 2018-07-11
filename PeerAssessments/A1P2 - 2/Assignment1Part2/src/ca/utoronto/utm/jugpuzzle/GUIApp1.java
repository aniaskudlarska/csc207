package ca.utoronto.utm.jugpuzzle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUIApp1 {
	static JugPuzzle jugPuzzle;
	static int[] result;
	static JFrame frame;
	
	public static void main(String[] args)  {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		// Create and hook up the Model, View and the controller
		// View
		GUIView guiView1 = new GUIView();
		JLabel won = new JLabel("Still Not won.");
		JLabel fromJug = new JLabel("From Jug:  ");
		JLabel toJug = new JLabel("To Jug #: ");
		// Model
		jugPuzzle = new JugPuzzle();
		// Hook the model to the view.
		jugPuzzle.addObserver(guiView1);
				
		// Create the GUI controller to control the Model
		JPanel layerOne = new JPanel();
		layerOne.setLayout(new GridLayout(2,4,5,5))
		;
		frame = new JFrame("JugPuzzle"); // Frame with title
		// What happens when we close the JFrame...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout components in a grid, 2 rows, 1 column
		frame.getContentPane().setLayout(new GridLayout(1,3));
		
		
		 // Creating references to JButtons, no buttons exist yet!!
		JButton bfrom0, bfrom1, bfrom2;
		JButton bto0, bto1, bto2;
		JButton bNew;
		

		// Create the buttons
        bNew = new JButton("New");
		bfrom0 =new JButton("0");
		bfrom1 =new JButton("1");
		bfrom2 =new JButton("2");
		
		bto0 =new JButton("0");
		bto1 =new JButton("1");
		bto2 =new JButton("2");
		
		// adding buttons to layers
		layerOne.add(fromJug);
		layerOne.add(bfrom0);
		layerOne.add(bfrom1);
		layerOne.add(bfrom2);
		layerOne.add(toJug);
		layerOne.add(bto0);
		layerOne.add(bto1);
		layerOne.add(bto2);
		
		// to be passed into button action listeners
		result = new int[] {0,0};
		JButton[] arrayButtons = new JButton[] {bfrom0, bfrom1, bfrom2, bto0, bto1, bto2, bNew};

		// add them to the contentPane
		frame.getContentPane().add(layerOne);
		frame.getContentPane().add(guiView1);
		frame.getContentPane().add(won);
		frame.getContentPane().add(bNew);

		// Create button press event handlers
		ButtonActionListener mb = new ButtonActionListener(arrayButtons,result, jugPuzzle, won);
		// Tell the buttons who they should call when they are pressed.
		// That is, hook up the controller to the Model.
		bfrom0.addActionListener(mb);
		bfrom1.addActionListener(mb);
		bfrom2.addActionListener(mb);
		
		bto0.addActionListener(mb);
		bto1.addActionListener(mb);
		bto2.addActionListener(mb);
		
		bNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	restartGame();
            	
            	
            }
        });      
		
		// tell the frame to pack in all the components
		// this is done according to the layout manager
		frame.pack();

		// lets see the frame
		frame.setVisible(true);
	}
	
	static void restartGame() {
		  frame.dispose();
		  createAndShowGUI();
		  frame.revalidate();
	}
	

	
	
}