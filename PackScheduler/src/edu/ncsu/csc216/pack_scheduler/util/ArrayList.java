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
		list = (E[]) new Object[INIT_SIZE];
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
		//Replace with size later
		if (size == idx) {
			growArray();
		}
		if (size == 0) {
			list[0] = value;
		}
		else {
			E replaced = list[idx];
			list[idx] = value;
			for (int i = idx + 1; i < size; i++) {
				list[i] = replaced;
				replaced = list[i];
			}
		}

	}
	
	@Override
	public E get(int index) {
		return list[index];
	}

	@Override
	public int size() {
		return list.length;
	}
	
	private void growArray() {
		@SuppressWarnings("unchecked")
		E[] list2 = (E[])new Object[size * 2];
		for (int i = 0; i < size; i++) {
			list2[i] = list[i];
		}
		list = list2;
	}
}
