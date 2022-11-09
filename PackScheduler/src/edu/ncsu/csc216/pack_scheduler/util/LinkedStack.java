/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This object represents a stack of elements.
 * Uses Linked list implementation.
 * @param <E> the object type being stored in the stack
 * @author Owner
 */
public class LinkedStack<E> implements Stack<E> {
	
	/**
	 * Field that holds the list used by the stack
	 */
	private LinkedAbstractList<E> stack;

	/**
	 * This method constructs a new LinkedStack with a given capacity
	 * @param capacity desired initial capacity for the stack
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * adds an element to the top of the stack
	 * @param element the element to be added to the stack
	 * @throws IllegalArgumentException if there is no room to add the element in the stack
	 */
	@Override
	public void push(E element) {
		stack.add(element);
	}

	/**
	 * removes the top element from the stack and returns it to the client
	 * @return E the element at the top of the stack that has been removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if(stack.size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/**
	 * checks to see if the stack is empty
	 * @return boolean false if it is not empty, true if is empty.
	 */
	@Override
	public boolean isEmpty() {
		return stack.size() == 0;
	}

	/**
	 * returns the size of the stack
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * sets the capacity for the stack.
	 * @param capacity the desired capacity of the stack.
	 * @throws IllegalArgumnetExcepton if the new capacity is less than the number of elements in the stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
		
	}

}
