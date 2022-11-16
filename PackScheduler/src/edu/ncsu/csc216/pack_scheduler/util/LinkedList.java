package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

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
	
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.previous = front;
		size = 0;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
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
			//TODO: implement
		}
		
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
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

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}

