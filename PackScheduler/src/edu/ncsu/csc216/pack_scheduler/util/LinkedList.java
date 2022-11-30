package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class functions as a list, it can add, set, remove and do all normal functions a list can provide
 * It stores data and returns the data as requested. This particular list also includes iterator functionality
 * which allows for the use of a for each loop along with other iterator functions that can be used to move through
 * and get information from the list. While using an iterator DO NOT edit the list outside of the iterator's functions
 * otherwise the iterator will break.
 * @author Allie Norton
 * @author Austin Bressler
 * @param <E> the generic type of the linked list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	
	/** The node at the front of the list */
	private ListNode front; 
	/** The node at the back of the list */
	private ListNode back; 
	/** The size of the list */
	private int size;
	
	/**
	 * constructor for LinkedLists, assigns front and back to ListNodes with element of null
	 * Sets front's next reference to back and back's previous reference to front. Sets size to 0
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.previous = front;
		size = 0;
		
	}

	/**
	 * returns the size of the list
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * This method adds an element to the list at a given index
	 * @param index the index being added to
	 * @param element the element being added to the list
	 * @throws IllegalArgumentException if element is already in the list
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater
	 *  than or equals to the size of the list
	 */
	@Override
	public void add(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		LinkedListIterator adder = new LinkedListIterator(index);
		adder.add(element);
	}

	
	/**
	 * This method sets a particular element at a given index to a different value
	 * then returns the original data that was replaced.
	 * @param index the index being set
	 * @param element the element being placed in the index
	 * @return E the element that used to be at an index
	 * @throws IllegalArgumentException if the element is already in the list
	 * @throws IndexOutOfBoundsException if the given index is not within the list
	 */
	@Override
	public E set(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		LinkedListIterator setter = new LinkedListIterator(index);
		E e = setter.next();
		setter.set(element);
		return e;
	}

	/**
	 * returns the LinkedListIterator for this LinkedList
	 * @param index The index at which the iterator will start at
	 * @return the LinkedListIterator for this LinkedList
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * This is the iterator for this linked list class which
	 * is able to mover through the list and return values in the list while
	 * also editing the list if needed. Do not edit the list directly
	 * while iterating, only edit the list using the iterator while iterating. 
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/** The list node returned by previous() */
		public ListNode previous;
		/** The list node returned by next() */
		public ListNode next; 
		/** The index returned by previousIndex() */
		public int previousIndex; 
		/** The index returned by nextIndex() */
		public int nextIndex; 
		/** The list node returned by the last call of previous() or next() */
		private ListNode lastRetrieved;
		
		/**
		 * This method constructs a list iterator for use that starts at an
		 * index within the list.
		 * @param index the starting index
		 * @throws IndexOutOfBoundsException if the given index is not within the list
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			if(index < size / 2) {
				ListNode current = front;
				int currentIndex = 0;
				for (int i = 0; i < index; i++) {
					current = current.next;
					currentIndex++;
				}
				previous = current;
				next = current.next;
				this.previousIndex = currentIndex - 1;
				this.nextIndex = currentIndex;
				lastRetrieved = null;
			} else {
				ListNode current = back;
				int currentIndex = size - 1;
				for (int i = size - 1; i >= index; i--) {
					current = current.previous;
					currentIndex--;
				}
				next = current;
				previous = current.previous;
				this.nextIndex = currentIndex + 1;
				this.previousIndex = currentIndex;
				lastRetrieved = null;
			}
		}
		
		
		
		/**
		 * Returns true if the next element's data is not null.
		 * @return boolean, true if the next element's data is not null.
		 */
		@Override
		public boolean hasNext() {
			return next != null && next.data != null;
		}
		
		/**
		 * Returns the next element in the list
		 * @return E the next element in the list
		 * @throws NoSuchElementException if the iterator attempts to get
		 * an element past the end of the list.
		 */
		@Override
		public E next() {
			if (next.data == null) {
				throw new NoSuchElementException();
			}
			E data = next.data;
			previousIndex++;
			nextIndex++;
			previous = next;
			next = next.next;
			lastRetrieved = previous;
			return data;
			
		}
		
		/**
		 * Returns true if the previous element's data is not null.
		 * @return boolean, true if the previous element's data is not null.
		 */
		@Override
		public boolean hasPrevious() {
			return previous != null && previous.data != null;
		}
		
		/**
		 * This method returns the previous element in a list 
		 * @return E the previous element in the list
		 * @throws NoSuchElementException if the iterator
		 * attempts to get an element before the start of the list
		 */
		@Override
		public E previous() {
			if (previous.data == null) {
				throw new NoSuchElementException();
			}
			E data = previous.data;
			previousIndex--;
			nextIndex--;
			next = previous;
			previous = previous.previous;
			lastRetrieved = next;
			return data;
		}
		
		/**
		 * returns the index of the next element
		 * @return the index of the next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		/**
		 * returns the index of the previous element
		 * @return the index of the previous element
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}
		
		/**
		 * This method removes the previously retrieved element from the list
		 * @throws IllegalStateException if the last action performed with the
		 * iterator was not a retrieval.
		 */
		@Override
		public void remove() {
			if(this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			this.lastRetrieved.previous.next = this.lastRetrieved.next;
			this.lastRetrieved.next.previous = this.lastRetrieved.previous;
			this.previous = this.lastRetrieved.previous;
			this.next = this.lastRetrieved.next;
			this.previousIndex = this.previousIndex - 1;
			this.nextIndex = this.nextIndex - 1;
			size--;
			this.lastRetrieved = null;
		}
		
		/**
		 * This method sets the last element to a new element
		 * This method does not work if the last action performed
		 * by the iterator was not a retrieval. 
		 * @throws IllegalStateException if last action was not a retrieval
		 * @throws NullPointerException if the given element to replace is null
		 */
		@Override
		public void set(E e) {
			if(this.lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if(e == null) {
				throw new NullPointerException();
			}
			this.lastRetrieved.data = e;
			
		}
		
		/**
		 * This method adds a new list element at the index the iterator would return next
		 * @param e the element to be added to the list
		 * @throws NullPointerException if e is null
		 */
		@Override
		public void add(E e) {
			if(e == null) {
				throw new NullPointerException();
			}
			ListNode node = new ListNode(e, this.previous, this.next);
			previous.next = node;
			next.previous = node;
			this.next = node;
			size++;
			this.lastRetrieved = null;
			
		}
	}

	/**
	 * This class represents a node in the list that can hold data and lead to other
	 * list nodes.
	 * @author Allie Norton
	 *
	 */
	private class ListNode {

		/** The data in the node */
		public E data;
		/** The next node in the list */
		public ListNode next;
		/** The previous node in the list */
		public ListNode previous;

		/**
		 * Constructor for the ListNode class, assigns the data in the node
		 * 
		 * @param data the data in the node
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * Contructor for the ListNode class, assigns all parameters
		 * 
		 * @param data the data in the node
		 * @param prev the previous node in the list
		 * @param next the next node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			previous = prev;
			this.next = next;
		}
	}

}

