package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * @author Allie Norton
 * @author Austin Bressel
 * @author Jeremiah
 * @param <E> the generic type of the recursive linked list
 *
 */
public class LinkedListRecursive<E> {
	/** the size of the list */
	private int size;
	/** the front of the list */
	private ListNode front;

	/**
	 * Constructor for LinkedListRecursive class. Initializes front to null and size
	 * to zero
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Determines if the list is empty
	 * 
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list
	 * 
	 * @return size the size of the list
	 */
	public int size() {
		return size;
	}

	public boolean add(E e) {
		return false;
	}

	public void add(int idx, E e) {

	}

	public E get(int idx) {
		return null;
	}

	public boolean remove(E e) {
		return false;
	}

	public E remove(int idx) {
		return null;
	}

	public E set(int idx, E e) {
		return null;
	}

	/**
	 * Determines if the desired element is in the list 
	 * @param e the desired element
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E e) {

		if (isEmpty()) {
			return false;
		}

		return front.contains(e);
	}

	private class ListNode {

		public E data;

		public ListNode next;

		public void add(int idx, E e) {

		}

		public E get(int idx) {
			return null;
		}

		public E remove(int idx) {
			return null;
		}

		public boolean remove(E e) {
			return false;
		}

		public E set(int idx, E e) {
			return null;
		}

		/**
		 * Determines if the current node has the desired elements
		 * 
		 * @param e the desired element
		 * @return true if the node contains the element, false if the node is at the
		 *         end of the list, and calls the method again if neither of the
		 *         previous mentioned are found
		 */
		public boolean contains(E e) {
			// check data in current node
			if (this.data.equals(e)) {
				return true;
			}

			// check if at end of the list
			if (this.next == null) {
				return false;
			}

			return next.contains(e);
		}

		public ListNode(E e, ListNode node) {

		}
	}

}
