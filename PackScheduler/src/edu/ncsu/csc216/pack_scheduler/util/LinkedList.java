package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * TODO: javadoc 
 * @author Allie Norton
 *
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
	 * returns the LinkedListIterator for this LinkedList
	 * @param index The index at which the iterator will start at
	 * @return the LinkedListIterator for this LinkedList
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}

	/**
	 * TODO: javadoc
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
		 * 
		 * @param index
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			ListNode current = front;
			int currentIndex = 0;
			for (int i = 0; i < index; i++) {
				current = current.next;
				currentIndex++;
			}
			previous = current.previous;
			next = current;
			this.previousIndex = currentIndex - 1;
			this.nextIndex = currentIndex;;
			lastRetrieved = null;
		}
		
		/**
		 * Returns true if the next element's data is not null.
		 * @return boolean, true if the next element's data is not null.
		 */
		@Override
		public boolean hasNext() {
			return next != null && next.data != null;
		}
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
		@Override
		public E previous() {
			if (next.data == null) {
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
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}
	}

	/**
	 * Creates a new ListNode
	 * 
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
		 * @param next the nex node in the list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			previous = prev;
			this.next = next;
		}
	}

}

