package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * @author Allie Norton
 * @author Austin Bressel
 * @author Jeremiah Knizley
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

	/**
	 * adds data e to the end of the list
	 * @param e the data to be added to the list
	 * @return boolean true if successfully added.
	 */
	public boolean add(E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (size == 0) {
			front = new ListNode(e, null);
			return true;
		}
		return front.add(e);
	}

	public void add(int idx, E e) {
		if (e == null) {
			throw new NullPointerException();
		}
		if (contains(e)) {
			throw new IllegalArgumentException();
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (idx == 0) {
			front = new ListNode(e, front);
		}
		else {
			front.add(idx, e);
		}
		
		
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

		return !(front == null) && front.contains(e);
	}

	private class ListNode {

		/** the data stored in the ListNode */
		public E data;
		
		/** the next node after this ListNode */
		public ListNode next;

		/**
		 * public pair with LinkedListRecursive that adds the element if it is at the correct index.
		 * If not at the correct index, the method is called again with index - 1
		 * @param idx a number representing distance from the index, decremented each time add is run. At 1 the method is at the index.
		 * @param e the data to be added to the list.
		 */
		public void add(int idx, E e) {
			if (idx == 1) {
				this.next = new ListNode(e, this.next);
			}
			else {
				this.next.add(idx - 1, e);
			}
		}
		/**
		 * public pair to LinkedListRecursive.add(E e) 
		 * If this node is the last node, next is set to a new ListNode with e as it's data and null as next
		 * If this node is not the last node, the next node calls add for itself.
		 * @param e the data to be added to the list
		 * @return boolean true if successfully added.
		 */
		public boolean add(E e) {
			
			if (next == null) {
				next = new ListNode(e, null);
				return true;
			}
			else {
				return next.add(e);
			}
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
