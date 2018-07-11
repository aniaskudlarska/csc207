package ca.utoronto.utm.jugpuzzle;

import java.util.Observable;
import java.util.Observer;

/**
 * A Jug Puzzle consists of three Jugs (numbered 0,1 and 2) with capacities 8,5
 * and 3 respectively. Initially, jug 0 is full, the other two are empty. The
 * player of the game spills liquid between the jugs (move) until both jugs 0
 * and 1 contain 4 units of liquid each. When a player makes a move, one Jug spills into
 * another. The JugPuzzle knows how many moves have taken place since the start
 * of the game. A spill ends as soon as one jug is empty or one jug is filled.
 * 
 * @author csc207student
 */

public class JugPuzzle extends Observable{
	private Jug[] jugs;
	private int moves;

	/**
	 * Create a new JugPuzzle with three jugs, capacities 8,5,3
	 * and initial amounts 8,0,0. The goal is to achieve amounts
	 * 4,4,0. Initially the number of moves is 0.
	 */
	public JugPuzzle() {
		this.jugs = new Jug[3];
		this.jugs[0] = new Jug(8, 8);
		this.jugs[1] = new Jug(5);
		this.jugs[2] = new Jug(3);
		this.moves = 0;
	}

	/**
	 * 
	 * @return the number of moves since the start of the game
	 */
	public int getMoves() {
		return moves;
		
	}

	/**
	 * 
	 * @return whether this is solved, that is 4 units in jugs 0 and 1 each.
	 */
	public boolean getIsPuzzleSolved() {
		
		return jugs[0].getAmount() == 4 && jugs[1].getAmount() == 4;
		
	}
	

	/**
	 * Make a single move of the JugPuzzle, that is spill Jug 'from' into Jug 'to'.
	 * This counts as a single move.
	 * 
	 * @param from an integer identifying a jug
	 * @param to an integer identifying a jug
	 */
	public void move(int from, int to) {
		if(0<=from && from<jugs.length && 0<=to && to<jugs.length){
			jugs[from].spillInto(jugs[to]);
			moves++;
			this.setChanged();
			this.notifyObservers();
			
		}
	}

	/**
	 * @return a string representation of this
	 */
	public String toString() {
		return moves + " " + " 0:" + jugs[0] + " 1:" + jugs[1] + " 2:" + jugs[2];
	}
	
	/**
	 * moves all the liquid from jug1 and 2 into jug0 and resets moves made to 0 
	 * so that the user can restart the game from scratch
	 */
	public void restart() {
		this.jugs[1].spillInto(this.jugs[0]);
		this.jugs[2].spillInto(this.jugs[0]);
		this.moves = 0;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * @return int variable with the amount of liquid currently in jug0
	 */
	public int getAmnt0() {
		return jugs[0].getAmount();
	}
	
	/**
	 * @return int variable with the amount of liquid currently in jug1
	 */
	public int getAmnt1() {
		return jugs[1].getAmount();
	}
	
	/**
	 * @return int variable with the amount of liquid currently in jug2
	 */
	public int getAmnt2() {
		return jugs[2].getAmount();
	}
	
	/**
	 * @return int variable with the maximum amount of liquid possible in jug0
	 */
	public int getCap0() {
		return jugs[0].getCapacity();
	}
	
	/**
	 * @return int variable with the maximum amount of liquid possible in jug1
	 */
	public int getCap1() {
		return jugs[1].getCapacity();
		
	}
	
	/**
	 * @return int variable with the maximum amount of liquid possible in jug2
	 */
	public int getCap2() {
		return jugs[2].getCapacity();
	}
	
	/**
	 * If the user has won the game, it prints a congratulatory message, otherwise it prints the instructions
	 * @return a string variable that says congratulations and amount of moves you won the game in, 
	 * 		   or a string that gives the instructions
	 */
	public String getWin() {
		if (jugs[0].getAmount() == 4 && jugs[1].getAmount() == 4) {
			return "<html><p>Congratulations, you won in "+ moves + " moves! If you would like to "
					+ "play again, please press Restart, otherwise press Quit</p></html>";
		}
		else {
			return "<html><p>Get 4 units in the 1st Jug & "
					+ " 4 units in the 2nd Jug<p><html>";
		}
	}
	
	/**
	 * Creates an observer for the JugPuzzle class. This observer can see all the changes that occur within
	 * the class.
	 */
	@Override
	public synchronized void addObserver(Observer i) {
		super.addObserver(i);
		this.setChanged();
		this.notifyObservers();
	}
}