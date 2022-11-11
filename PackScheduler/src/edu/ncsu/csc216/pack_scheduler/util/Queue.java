/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for creating custom Queues for PackScheduler
 * @param <E> a generic type, classes implementing can use generics to implement.
 * @author Jeremiah Knizley
 *
 */
public interface Queue<E> {
	
	/**
	 * Adds the element to the back of the Queue. 
	 * @param element the element to add to the queue
	 * @throws IllegalArgumentException if there is no more room in the list, meaning the list has reached capacity
	 */
	void enqueue(E element);
	
	/**
	 * removes and returns the element at the front of the queue
	 * @return E the element removed from the queue
	 * @throws NoSuchElementException if the Queue is empty
	 */
	E dequeue();
	
	/**
	 * returns true if the Queue is empty, false if not.
	 * @return boolean true if the Queue is empty, false if not
	 */
	boolean isEmpty();
	
	/**
	 * returns the current size of the queue
	 * @return the current size of the queue
	 */
	int size();
	
	/**
	 * sets the capacity of the list
	 * @param capacity the capacity of the list
	 * @throws IllegalArgumentException if the parameter is negative, or if it is less than the current size of the queue
	 */
	void setCapacity(int capacity);
}
