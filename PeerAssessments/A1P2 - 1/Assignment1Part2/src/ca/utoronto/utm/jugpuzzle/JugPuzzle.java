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

public class JugPuzzle extends Observable {
	private Jug[] jugs;
	private int moves;
	private int highScore;

	/**
	 * Create a new JugPuzzle with three jugs, capacities 8,5,3
	 * and initial amounts 8,0,0. The goal is to achieve amounts
	 * 4,4,0. Initially the number of moves is 0. Sets the initial
	 * highscore to -1.
	 */
	public JugPuzzle() {
		jugs = new Jug[3];
		jugs[0] = new Jug(8, 8);
		jugs[1] = new Jug(5);
		jugs[2] = new Jug(3);
		moves = 0;
		highScore = -1;
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
	 * This function returns the specified Jug as the desired index
	 * passed as the parameter. The function returns null if the index
	 * does not exist, i.e is greater/less than the number of Jugs in
	 * the JugPuzzle.
	 * 
	 * @param jugIndex
	 *            the index of the Jug
	 * @return the Jug at the specified index or null
	 */
	public Jug getJug(int jugIndex) {
		if (0 <= jugIndex && jugIndex < jugs.length) {
			return jugs[jugIndex];
		}
		return null;
	}
	
	/**
	 * Make a single move of the JugPuzzle, that is spill Jug 'from' into Jug 'to'.
	 * This counts as a single move. If a move is valid then the observers
	 * are notified that this class has changed.
	 * 
	 * @param from 
	 *            an integer identifying a jug
	 * @param to 
	 *            an integer identifying a jug
	 */
	public void move(int from, int to) {
		if(0<=from && from<jugs.length && 0<=to && to<jugs.length){
			jugs[from].spillInto(jugs[to]);
			moves++;
			setChanged();
			notifyObservers("moved liquid");
		}
	}
	
	/**
	 * Returns liquid level of Jug at index jugIndex. If the 
	 * jugIndex is invalid i.e. not in between 0 and 2 then this 
	 * function returns 0 as the liquid level.
	 * 
	 * @param jugIndex 
	 *            an integer index of the Jug
	 * @return liquid level of the desired Jug given the index
	 */
	public int getLiquidLevel(int jugIndex) {
		if (0 <= jugIndex && jugIndex <= 2) {
			return jugs[jugIndex].getAmount();
		}
		return 0;
	}
	
	/**
	 * Returns capacity of Jug at index jugIndex. If the 
	 * jugIndex is invalid i.e. not in between 0 and 2 then this 
	 * function returns 0 as the capacity.
	 * 
	 * @param jugIndex 
	 *            an integer index of the Jug
	 * @return capacity of the desired Jug given the index
	 */
	public int getCapacityLevel(int jugIndex) {
		if (0 <= jugIndex && jugIndex <= 2) {
			return jugs[jugIndex].getCapacity();
		}
		return 0;
	}
	
	/**
	 * 
	 * @return integer, the high score the this JugPuzzle
	 */
	public int getHighScore() {
		return highScore;
	}
	
	/**
	 * Sets the high score to the newest high score. Initially
	 * since the high score is -1, any score that is set at the 
	 * beginning will the high score. Notifies the observers
	 * that this classs  has changed.
	 * 
	 * @param score
	 *            the new highscore of the game, iff it is greater than the previous score
	 */
	public void setHighScore(int score) {
		if (getHighScore() == -1 || getHighScore() > score) {
			highScore = score;
			setChanged();
			notifyObservers("highscore changed");
		}
	}
	
	/**
	 * Resets JugPuzzle to original state. First Jug is filled 
	 * second and third Jugs are empty. Jugs have the same
	 * capacity as they did in the beginning and the number of
	 * moves is set back to 0. Notifies the observers of a change.
	 */
	public void resetPuzzle() {
		jugs[0].add(jugs[0].getCapacity()-jugs[0].getAmount());
		jugs[1].remove(jugs[1].getAmount());
		jugs[2].remove(jugs[2].getAmount());
		moves = 0;
		setChanged();
		notifyObservers("reset");
	}

	/**
	 * @return a string representation of this
	 */
	public String toString() {
		return moves + " " + " 0:" + jugs[0] + " 1:" + jugs[1] + " 2:" + jugs[2];
	}
	
	/**
	 * No return, adds an observer that can observe this class
	 * 
	 * @param observer
	 *            Observer to add as an observer of this class
	 */
	@Override
	public synchronized void addObserver(Observer observer) {
		super.addObserver(observer);
		setChanged();
		notifyObservers("observed");
	}
}