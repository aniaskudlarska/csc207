package ca.utoronto.utm.jugpuzzle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class GUIApp2 {
	static JugPuzzle jugPuzzle;
	
	public static void main(String[] args) {
		
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


		
		// Model
		jugPuzzle = new JugPuzzle();
		jugPuzzle.addObserver(guiView1);
		// Hook the model to the view.
				
		// Create the GUI controller to control the Model
		JFrame frame = new JFrame("JugPuzzle"); // Frame with title
		// What happens when we close the JFrame...
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Layout components in a grid, 2 rows, 1 column
		frame.getContentPane().setLayout(new GridLayout(2,1));
		
		

		JButton b00,b11,b22,b01,b10,b02,b20,b12,b21; // Two reference to JButton, no buttons exist yet!!

		// Create the buttons
		b00 =new JButton("0 to 0");
		b11 =new JButton("1 to 1");
		b22 =new JButton("2 to 2");
		b01 =new JButton("0 to 1");
		b10 =new JButton("1 to 0");
		b02 =new JButton("0 to 2");
		b20 =new JButton("2 to 0");
		b12 =new JButton("1 to 2");
		b21 = new JButton("2 to 1");

		// add them to the contentPane
		frame.getContentPane().add(b00);
		frame.getContentPane().add(b11);
		frame.getContentPane().add(b22);
		
		frame.getContentPane().add(b01);
		frame.getContentPane().add(b10);
		
		frame.getContentPane().add(b02);
		frame.getContentPane().add(b20);
		
		frame.getContentPane().add(b12);
		frame.getContentPane().add(b21);
		frame.getContentPane().add(guiView1);
		frame.getContentPane().add(won);

		// Create button press event handlers
		ButtonFromToActionListener mb00 = new ButtonFromToActionListener(0,0,jugPuzzle,won);
		ButtonFromToActionListener mb11 = new ButtonFromToActionListener(1,1,jugPuzzle,won);
		ButtonFromToActionListener mb22 = new ButtonFromToActionListener(2,2,jugPuzzle,won);
		
		ButtonFromToActionListener mb01 = new ButtonFromToActionListener(0,1,jugPuzzle,won);
		ButtonFromToActionListener mb10 = new ButtonFromToActionListener(1,0,jugPuzzle,won);
		
		ButtonFromToActionListener mb02 = new ButtonFromToActionListener(0,2,jugPuzzle,won);
		ButtonFromToActionListener mb20 = new ButtonFromToActionListener(2,0,jugPuzzle,won);
		
		ButtonFromToActionListener mb12 = new ButtonFromToActionListener(1,2,jugPuzzle,won);
		ButtonFromToActionListener mb21 = new ButtonFromToActionListener(2,1,jugPuzzle,won);

		// Tell the buttons who they should call when they are pressed.
		// That is, hook up the controller to the Model.
		b00.addActionListener(mb00);;
		b11.addActionListener(mb11);
		b22.addActionListener(mb22);
		
		b01.addActionListener(mb01);
		b10.addActionListener(mb10);
		
		b02.addActionListener(mb02);
		b20.addActionListener(mb20);
		
		b12.addActionListener(mb12);
		b21.addActionListener(mb21);
		
		// tell the frame to pack in all the components
		// this is done according to the layout manager
		frame.pack();

		// lets see the frame
		frame.setVisible(true);
	}
	
	
}