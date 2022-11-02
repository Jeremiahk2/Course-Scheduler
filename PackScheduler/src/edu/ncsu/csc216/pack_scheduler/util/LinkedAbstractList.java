/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * @author Spencer Grattan
 * @param <E> the generic parameter for LinkedAbstractList
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The front of the list */
	private ListNode front;
	/** the size of the list */
	private int size;
	/** the capacity of the list */
	private int capacity;
	
	/**
	 * Constructor for LinkedAbstractList
	 * @param capacity  the capacity of the newly created list
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		this.capacity = capacity;
	}
	
	/**
	 * 
	 */
	@Override
	public E get(int index) {
		
		return null;
	}

	/**
	 * 
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * @param idx
	 * @param element
	 */
	@Override
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		//TODO: implement duplicate check
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		ListNode duplicate = front;
		for (int i = 0; i < size; i++) {
			if (duplicate.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			duplicate = duplicate.next;
		}
		if (idx == 0) {
			ListNode newFront = new ListNode(element, front);
			front = newFront;
		}
		else {
			ListNode beforeValue = front;
			for (int i = 0; i < idx - 1; i++) {
				beforeValue = beforeValue.next;
			}
			beforeValue.next = new ListNode(element, beforeValue.next.next);
		}
		
	}
	
	/**
	 * 
	 */
	@Override
	public E remove(int idx) {
		
		return null;
	}
	
	/**
	 * 
	 * @param idx
	 * @param element
	 */
	@Override
	public E set(int idx, E element) {
		
		return null;
	}
	
	/**
	 * 
	 * @param capacity
	 */
	public void setCapacity(int capacity) {
		
	}
	
	private class ListNode {
		
		/** The data being stored in the node. The type passed into 
		 * LinkedAbstractList is the same type as the data */
		private E data;
		/** The next node in the chain of nodes that make up the LinkedAbstractList */
		private ListNode next;
		
		/**
		 * Constructor for ListNode when adding a node to the end of the 
		 * LinkedAbstractList
		 * @param data the object being stored in the node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
		}
		
		/**
		 * Constructor for LintNode when specifying a specific node to be 
		 * the next node in the LinkedAbstractList
		 * @param data the object being stored in the node
		 * @param next the next node in the LinkedAbstractList
		 */
		public ListNode(E data, ListNode next) {
			this.data = data; 
			this.next = next;
		}
		
	}
	

}
