package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class is a linkedList that uses only recursive methods to accomplish all non-basic functions
 * This list is able to add at any index, remove at any index, return its size, check if it's empty,
 * check if it contains a given element, set an index to a given element, and remove a given element if it is in the list
 * @author Allie Norton
 * @author Austin Bressler
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
	 * @throws NullPointerException if e is null
	 * @throws IllegalArgumentException if e is already in the list
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
			size++;
			return true;
		}
		return front.add(e);
	}

	/**
	 * This method adds a new element at a given index
	 * @param idx the index to be added at
	 * @param e the element being added to the list
	 * @throws NullPointerException if e is null
	 * @throws IllegalArgumentException if e is already in the list
	 * @throws IndexOutOfBoundsException if given index is not within the list
	 */
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
			size++;
		}
		else {
			front.add(idx, e);
		}
		
		
	}

	/**
	 * This method gets an element at the given index
	 * @param idx the index of the desired element
	 * @return E the desired element
	 * @throws IndexOutOfBoundsException if the given index is outside the list.
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}

	/**
	 * This method removes a given element from the list if it can
	 * @param e the element being removed from the list
	 * @return true if the element was successfully removed
	 */
	public boolean remove(E e) {
		if (e == null) {
			throw new NullPointerException();
		} else if (isEmpty()) {
			return false;
		} else if (front.data.equals(e)) {
			front = front.next;
			return true;
		} else {
			return front.remove(e);
		}
	}

	/**
	 * This method removes an element at a specific spot in the list
	 * @param idx the index of the element being removed
	 * @return E the element that was removed 
	 * @throws IndexOutOfBoundsException if index is not within the list
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		} else if (idx == 0) {
			E data = front.data;
			front = front.next;
			size--;
			return data;
		}
		return front.remove(idx);
	}

	/**
	 * This method changed the element at a given index and returns the element
	 * formally at that index
	 * @param idx the index of the element to be changed
	 * @param e the new element to insert
	 * @return E the previous element located at the index
	 * @throws NullPointerException if e is null
	 * @throws IllegalArgumentException if e is already in the list
	 * @throws IndexOutOfBoundsException if given index is not within the list
	 */
	public E set(int idx, E e) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		} else if (e == null) {
			throw new NullPointerException();
		} if (contains(e)) {
			throw new IllegalArgumentException();
		}
		return front.set(idx, e);
	}

	/**
	 * Determines if the desired element is in the list 
	 * @param e the desired element
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E e) {

		return !(front == null) && front.contains(e);
	}

	/**
	 * This inner class represents a location in the list and it stores the data at that location and
	 * contains a reference for the next node in the list.
	 * @author Allie Norton
	 * @author albressl
	 * @author Jeremiah Knizley
	 */
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
				size++;
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
				size++;
				return true;
			}
			else {
				return next.add(e);
			}
		}

		/**
		 * This method checks to see if this list node is the list node with
		 * the desired data, otherwise it checks the next node.
		 * @param idx how far away the correct index is
		 * @return E the data that was asked for
		 */
		public E get(int idx) {
			if(idx == 0) {
				return this.data;
			}
			return next.get(idx - 1);
		}

		/**
		 * This method removes the next list node if it is the list node that is supposed to be
		 * removed
		 * @param idx how far away the list node to remove is
		 * @return E the data contained in the removed list node
		 */
		public E remove(int idx) {
			if(idx == 1) {
				E element = this.next.data;
				this.next = this.next.next;
				size--;
				return element;
			}
			return this.next.remove(idx - 1);
		}

		/**
		 * This method removes the next list node if it has the element that is supposed to be removed
		 * @param e the element being removed
		 * @return true if the element was successfully removed
		 */
		public boolean remove(E e) {
			if (this.next == null) {
				return false;
			} else if(next.data.equals(e)) {
				this.next = this.next.next;
				return true;
			}
				
			return this.next.remove(e);
		}

		/**
		 * This method sets the current node's data to e
		 * if this is the correct list node
		 * @param idx distance from the list node to change
		 * @param e the new element being added to the list
		 * @return E the element that used to be at the desired node
		 */
		public E set(int idx, E e) {
			if(idx == 0) {
				E element = this.data;
				this.data = e;
				return element;
			}
			return this.next.set(idx - 1, e);
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

		/**
		 * This constructs a new list node with the given data and next set to the given node
		 * @param e the given data to store
		 * @param node the next node in the list, null if at end of list
		 */
		public ListNode(E e, ListNode node) {
			this.data = e;
			this.next = node;
		}
	}

}
