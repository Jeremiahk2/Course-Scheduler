/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a custom ArrayList for use in PackScheduler.Schedule.
 * This list extends AbstractList and modifications are largely to include custom error checking 
 * or to change return types so that they can be used later in the program (like set and remove).
 * @author Spencer Grattan
 * @author Jeremiah Knizley
 * @param <E> the object type of the ArrayList, generic for versatility
 *
 */
public class ArrayList<E> extends AbstractList<E> {

	/** the initial size of the E array that holds the data in the list */
	private static final int INIT_SIZE = 10;
	/** the array that holds the data in the list, E is used as a generic type */
	private E[] list;
	/** the size of the array, cannot be greater than capacity. Only counts non-null elements */
	private int size;

	/**
	 * Constructor for an ArrayList, initializes size to 0, and creates a new list with INIT_SIZE as the size
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}

	/**
	 * Adds a given value of type E to the ArrayList at the given index (E is generic)
	 * The list is shifted up after the value is added. If a list contains "Apple", "Banana", then add(1, "Cherry")
	 * would add Cherry at index 1 and Banana would shift to index 2. Increases size by one
	 * @param idx the index of the item in the list to add
	 * @param value the item being added
	 * @throws NullPointerException if the value is null
	 * @throws IllegalArgumentException if the value is already in the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equals to size
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

	/**
	 * returns the value at the specific index in the list
	 * @return E the value at the index
	 * @throws IndexOufOfBoundsException if the index is less than 0 or greater than equals to size
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * returns the size of the list, only counts non-null elements
	 * @return size, the size of the array
	 */
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

	/**
	 * Increases the capacity of the array by a factor of two.
	 * used for when the array reaches capacity so that more elements can be added.
	 */
	private void growArray() {
		@SuppressWarnings("unchecked")
		E[] list2 = (E[])new Object[size * 2];
		for (int i = 0; i < size; i++) {
			list2[i] = list[i];
		}
		list = list2;
	}

	/**
	 * Removes the element at the index, returning the element removed
	 * All elements above the removed element are shifted down in the list
	 * @return E the element that was removed from the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to size
	 */
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

	/**
	 * Replaces the element at the index with value
	 * @return E the value that was replaced in the list.
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than equal to size
	 * @throws NullPointerException if value is null
	 * @throws IllegalArgumentException if value is already in the list
	 */
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




