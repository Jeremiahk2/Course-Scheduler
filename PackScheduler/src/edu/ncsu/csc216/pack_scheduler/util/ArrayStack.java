/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * An array-based stack data structure, customly made for PackScheduler.
 * @author Jeremiah Knizley
 * @param <E> the generic type of the array stack.
 */
public class ArrayStack<E> implements Stack<E> {
	/** the capacity of the array stack */
	private int capacity;
	/** the array containing the elements in the stack */
	private E[] stack;
	
	/**
	 * constructor for ArrayStacks, sets private field capacity to parameter capacity, initializes stack
	 * @param capacity the capacity of the stack
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		setCapacity(capacity);
		stack = (E[]) new Object[capacity];
	}
	
	/**
	 * adds an element to the top of the stack
	 * @param element the elemnt to be added to the stack
	 * @throws IllegalArgumentException if there is no room to add the elemnent in the stack
	 */
	@Override
	public void push(E element) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * removes the top element from the stack and returns it to the client
	 * @return E the element at the top of the stack that has been removed
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * checks to see if the stack is empty
	 * @return boolean false if it is not empty, true if not.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * returns the size of the stack
	 * @return the size of the stack
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * sets the capacity for the stack.
	 * @param capacity the desired capacity of the stack.
	 * @throws IllegalArgumnetExcepton if the new capacity is less than the number of elements in the stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}

	
}
