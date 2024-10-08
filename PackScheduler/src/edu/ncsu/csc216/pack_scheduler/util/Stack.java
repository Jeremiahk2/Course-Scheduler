/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Interface for implementing a generic Stack in different lists
 * @param <E> generic type for all parameters
 * @author Jeremiah Knizley
 *
 */
public interface Stack<E> {
	
	/**
	 * adds an element to the top of the stack
	 * @param element the elemnt to be added to the stack
	 * @throws IllegalArgumentException if there is no room to add the elemnent in the stack
	 */
	void push(E element);
	
	/**
	 * removes the top element from the stack and returns it to the client
	 * @return E the element at the top of the stack that has been removed
	 * @throws EmptyStackException if the stack is empty
	 */
	E pop();
	
	/**
	 * checks to see if the stack is empty
	 * @return boolean false if it is not empty, true if not.
	 */
	boolean isEmpty();
	
	/**
	 * returns the size of the stack
	 * @return the size of the stack
	 */
	int size();
	
	/**
	 * sets the capacity for the stack.
	 * @param capacity the desired capacity of the stack.
	 * @throws IllegalArgumentException if the new capacity is less than the number of elements in the stack.
	 */
	void setCapacity(int capacity);
	
}
