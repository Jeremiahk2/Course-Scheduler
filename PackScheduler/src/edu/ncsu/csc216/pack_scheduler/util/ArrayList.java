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
		boolean duplicate = false;
		for (int i = 0;  i < size; i++) {
			if (list[i] == value) {
				duplicate = true;
			}
		}
		if (duplicate) {
			throw new IllegalArgumentException("Duplicate value");
		}

		if (idx < 0 || idx > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		//Replace with size later
		if (this.list.length == idx) {
			growArray();
		}

		if (size == idx) {
			list[size] = value;
		}
		else {
			E replaced = list[idx];
			list[idx] = value;
			for (int i = idx + 1; i < size + 1; i++) {
				list[i] = replaced;
				replaced = list[i];
			}
		}
		size++;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	@Override
	public int size() {
		int count = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i] != null) {
				count += 1;
			}
		}
		return count;
	}

	private void growArray() {
		@SuppressWarnings("unchecked")
		E[] list2 = (E[])new Object[size * 2];
		for (int i = 0; i < size; i++) {
			list2[i] = list[i];
		}
		list = list2;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E rtnE = get(index);
		for (int i = index; i < size - 1; i++) {
			list[i] = get(index + 1);
		}
		list[size - 1] = null;
		size--;
		return rtnE;
	}

	@Override
	public E set(int index, E value) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (value == null) {
			throw new NullPointerException();
		}
		//check for duplicate element
		boolean duplicate = false;
		for (int i = 0;  i < size; i++) {
			if (list[i] == value) {
				duplicate = true;
			}
		}
		if (duplicate) {
			throw new IllegalArgumentException("Duplicate value");
		}
		E rtnValue = get(index);
		list[index] = value;


		return rtnValue;

	}
}




