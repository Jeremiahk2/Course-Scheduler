/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * An array-based stack data structure, customly made for PackScheduler.
 * @author Jeremiah Knizley
 * @author Allie Norton
 * @author Austin Bressel
 * @param <E> the generic type of the array stack.
 */
public class ArrayStack<E> implements Stack<E> {
	/** the capacity of the array stack */
	private int capacity;
	/** the array containing the elements in the stack */
	private ArrayList<E> stack;
	
	/**
	 * constructor for ArrayStacks, sets private field capacity to parameter capacity, initializes stack
	 * @param capacity the capacity of the stack
	 */
	public ArrayStack(int capacity) {
		this.stack = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * adds an element to the top of the stack
	 * @param element the element to be added to the stack
	 * @throws IllegalArgumentException if there is no room to add the element in the stack
	 */
	@Override
	public void push(E element) {
		if (stack.size() == capacity) {
			throw new IllegalArgumentException("The stack is full.");
		}
		
		stack.add(element);
	}

	/**
	 * removes the top element from the stack and returns it to the client
	 * @return E the element at the top of the stack that has been removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
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
		if (capacity < 0 || capacity < stack.size()) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
		
	}

	
}
