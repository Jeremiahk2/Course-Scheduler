/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Creates a custom array-based Queue for PackScheduler
 * @param <E> a generic type, classes implementing can use generics to implement.
 * @author Jeremiah Knizley
 *
 */
public class ArrayQueue<E> implements Queue<E> {

	/** the list containing the elements in the queue */
	private ArrayList<E> list;
	
	/** the capacity of the list */
	private int capacity;
	
	/**
	 * creates an ArrayQueue, initializing list and setting the private field capacity to the parameter
	 * @param capacity the capacity of the ArrayQueue
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<>();
		setCapacity(capacity);
	}
	
	/**
	 * Adds the element to the back of the Queue. 
	 * @param element the element to add to the queue
	 * @throws IllegalArgumentException if there is no more room in the list, meaning the list has reached capacity
	 */
	@Override
	public void enqueue(E element) {
		if (list.size() == capacity) {
			throw new IllegalArgumentException("The list has reached capacity.");
		}
		list.add(list.size(), element);
		
	}

	/**
	 * removes and returns the element at the front of the queue
	 * @return E the element removed from the queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("The queue is empty");
		}
		return list.remove(0);
	}

	/**
	 * returns true if the Queue is empty, false if not.
	 * @return boolean true if the Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * returns the current size of the queue
	 * @return the current size of the queue
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * sets the capacity of the list
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if the parameter is negative, or if it is less than the current size of the queue
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
		
	}

	/**
	 * This method checks if the queue currently contains a particular object
	 * @param element the element that is being checked to see if it is contained in the queue
	 * @return boolean true if queue contains the element
	 */
	public boolean contains(E element) {
		boolean foundE = false;
		int size = this.size(); // Size will change as things are added and removed
		for(int i = 0; i < size; i++) {
			E current = this.dequeue();
			if (element.equals(current) ) {
				foundE = true;
			}
			this.enqueue(current);
		}
		return foundE;
	}

	
}
