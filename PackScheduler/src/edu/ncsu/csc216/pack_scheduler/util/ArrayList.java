/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * @author Spencer Grattan
 * @param <E> the object type of the ArrayList
 *
 */
public class ArrayList<E> extends AbstractList<E>{

	/**  */
	private static final int INIT_SIZE = 10;
	/**  */
	private E[] list;
	/**  */
	private int size;
	
	/**
	 * Constructor for an ArrayList
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		list = (E[]) new Object[10];
	}
	
	/**
	 * Adds a given value of type E to the ArrayList at the given index
	 * @param idx the index of the item to add
	 * @param value the item being added
	 */
	@Override
	public void add(int idx, E value) {
		if (value == null) {
			throw new NullPointerException();
		}
		//check for duplicate element
		
		
		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		
		
	}
	
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
