package ca.utoronto.utm.jugpuzzle;

import java.util.Observable;
import java.util.Observer;

/**
 * Model a Jug and its contained liquid. The Jug has a non-negative amount of
 * liquid, and a capacity, the maximum amount of liquid the Jug can hold. At all
 * times 0<=amount<=capacity.
 * 
 * @author arnold
 *
 */
public class Jug extends Observable {

	private int capacity = 0; // Always 0<=capacity
	private int amount = 0; // Always 0<=amount<=capacity

	/**
	 * Construct a Jug with the specified integers 0<=amount<=capacity.
	 * If invalid capacity or amount are presented, a Jug with 
	 * amount=capacity=0 is created.
	 * 
	 * @param capacity
	 * 			  a non negative integer
	 * @param amount
	 *            an integer such that 0<=amount<=capacity
	 */
	public Jug(int capacity, int amount) {
		if (0 <= amount && amount <= capacity) {
			setCapacity(capacity);
			add(amount);
		}
	}

	/**
	 * Create an empty Jug of the specified capacity. See Jug(amount, capacity)
	 * for details.
	 * @param capacity
	 */
	public Jug(int capacity) {
		this(capacity, 0);
	}

	/**
	 * Set the capacity of this to c if possible, otherwise this
	 * is unchanged.
	 * 
	 * @param c
	 *            the target capacity, with 0<=c
	 */
	private void setCapacity(int c) {
		if (c < 0)
			return;
		if (c < amount)
			return;
		capacity = c;
		setChanged();
		notifyObservers("set capacity");
	}

	/**
	 * Fill this from other Jug, either emptying other or filling this.
	 * Spilling this into this leaves this unchanged.
	 * @param other
	 *            a different Jug whose contents will spill into this
	 */
	public void spillInto(Jug other) {
		if (this == other)
			return;
		else
			remove(other.add(amount));
	}

	/**
	 * Add up to addAmount into this Jug, or until this is full.
	 * 
	 * @param addAmount
	 *            non-negative integer amount to be added to this
	 * @return the amount actually added to this
	 */
	public int add(int addAmount) {
		if (addAmount < 0)
			return 0;
		int freeSpace = capacity - amount;
		if (addAmount > freeSpace){
			addAmount = freeSpace;
		}
		amount = amount + addAmount;
		setChanged();
		notifyObservers("added liquid");
		return addAmount;
	}

	/**
	 * @return the amount currently in this
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the capacity of this
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Remove up to removeAmount from this Jug, or until this is empty
	 * 
	 * @param removeAmount
	 *            the non-negative integer amount to be removed from this
	 * @return the amount actually removed from this
	 */
	public int remove(int removeAmount) {
		if (removeAmount < 0)
			return 0;
		if (removeAmount > amount){
			removeAmount = amount;
		}
		amount = amount - removeAmount;
		setChanged();
		notifyObservers("removed liquid");
		return removeAmount;
	}

	/**
	 * @return a String representation of this
	 */
	public String toString() {
		return "(" + amount + "/" + capacity + ")";
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
