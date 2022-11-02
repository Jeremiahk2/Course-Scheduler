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
	 * Gets the object of type E stored at the given index in the linked abstract list
	 * @param index the index of the object to get in the list
	 */
	@Override
	public E get(int index) {
		//checks for the index being out of bounds
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		//traverses the linked list and gets to the node at the given index
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		//returns the object stored at the given index
		return current.data;
	}

	/**
	 * Gets the current size of the linked abstract list- how many nodes are in the list. 
	 */
	@Override
	public int size() {
		return this.size;
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
			size++;
		}
		else {
			ListNode beforeValue = front;
			for (int i = 0; i < idx - 1; i++) {
				beforeValue = beforeValue.next;
			}
			if (idx != size) {
				ListNode elementNode = new ListNode(element, beforeValue.next.next);
				beforeValue.next = elementNode;
				size++;
			}
			else {
				ListNode elementNode = new ListNode(element);
				beforeValue.next = elementNode;
				size++;
			}
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
	 * Sets the object at a given index to a new given element
	 * @param idx the index where the element is replaced
	 * @param element the object replacing the old element
	 * @return the overridden element
	 */
	@Override
	public E set(int idx, E element) {
		//checks for null element 
		if (element == null) {
			throw new IllegalArgumentException();
		}
		
		//checks for the index being out of bounds
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		//checks for a duplicate element in the list
		ListNode duplicate = front;
		for (int i = 0; i < size; i++) {
			if (duplicate.data.equals(element)) {
				throw new IllegalArgumentException();
			}
			duplicate = duplicate.next;
		}
		
		//traverses the linked list and gets to the node before the given index
		ListNode current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		
		E replacedElement = current.next.data;
		
		//create a new node with the passed data and set connect the previous node to the new one
		ListNode newNode = new ListNode(element, current.next.next);
		current.next = newNode;
		
		//return the overridden element
		return replacedElement;
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
