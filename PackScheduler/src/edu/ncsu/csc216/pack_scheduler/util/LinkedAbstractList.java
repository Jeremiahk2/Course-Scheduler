/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * A custom LinkedList. Implements typical LinkedList methods, though changes functionality to suite PackScheduler's needs.
 * Also contains a capacity, which determines the maximum amount of elements in the list.
 * @author Spencer Grattan
 * @author Jeremiah Knizley
 * @param <E> the generic parameter for LinkedAbstractList
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The front of the list */
	private ListNode front;
	/** the node at the back of the list */
	private ListNode back;
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
		this.back = null;
		setCapacity(capacity);
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
		try {
			return current.data;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * Gets the current size of the linked abstract list- how many nodes are in the list. 
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Adds the element to the index. The element that was at the index (if there was one) is pushed up to the next index.
	 * @param idx the index where the element will be added in the list
	 * @param element The element to be added to the list
	 * @throws NullPointerException if element is null
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than size
	 * @throws IllegalArgumentException if the list is at capacity or if the element is a duplicate of one already in the list
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
		if (idx == size() || idx == 0) {
			ListNode newBack = new ListNode(element, null);
			//ListNode oldBack = back;
			if (size () == 1 && idx == size()) {
				front.next = newBack;
			}
			if (back != null && idx == size()) {
				back.next = newBack;
				back = back.next;
			}
			else if (back == null) {
				back = newBack;
			}
			if (front != null && idx != 0) {
				size++;
			}
			else if (idx == 0) { /*(idx == 0) { */
				ListNode newFront = new ListNode(element, front);
				//ListNode oldFront = front;
				//newFront.next = oldFront;
				front = newFront;
				size++;
			}
			if (size() == 1) {
				front = back;
			}
		}
		else {
			ListNode beforeValue = front;
			for (int i = 0; i < idx - 1; i++) {
				beforeValue = beforeValue.next;
			}
			if (idx != size) {
				ListNode elementNode = new ListNode(element, beforeValue.next);
				beforeValue.next = elementNode;
				size++;
			}
			else {
				ListNode elementNode = new ListNode(element, null);
				beforeValue.next = elementNode;
				size++;
			}
		}
		
	}
	
	/**
	 * Removes the element at the specified index from the list, returning it to the client
	 * @param idx the index of the element to remove
	 * @throws IndexOutOfBoundsException if idx is less than 0 or greater than or equals to size
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E data = null;
		if (idx == 0) {
			data = front.data;
			front = front.next;
		}
		else {
			ListNode beforeValue = front;
			for (int i = 0; i < idx - 1; i++) {
				beforeValue = beforeValue.next;
			}
			if (idx == size() - 1) {
				back = beforeValue;
			}
			data = beforeValue.next.data;
			beforeValue.next = beforeValue.next.next;
		}
		size--;
		return data;
	}
	
	/**
	 * Sets the object at a given index to a new given element
	 * @param idx the index where the element is replaced
	 * @param element the object replacing the old element
	 * @return the overridden element
	 * @throws NullPointerException if element is null
	 * @throws IndexOutOfBoundsException if index is less than 0 or index is greater than/equal to size
	 * @throws IllegalArgumentException if element is a duplicate of one already in the list.
	 */
	@Override
	public E set(int idx, E element) {
		//checks for null element 
		if (element == null) {
			throw new NullPointerException();
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
		
		E replacedElement;
		if (idx == 0) {
			replacedElement = current.data;
		}
		else { 
			replacedElement = current.next.data;
		}
		
		//create a new node with the passed data and set connect the previous node to the new one
		ListNode newNode;
		if (idx == 0) {
			newNode = new ListNode(element, current.next);
			front = newNode;
		}
		else {
			newNode = new ListNode(element, current.next.next);
			current.next = newNode;
		}
		
		//return the overridden element
		return replacedElement;
	}
	
	/**
	 * sets the capacity of the list
	 * @param capacity the capacity of the list, that size cannot be over
	 * @throws IllegalArgumentException if capacity is less than 0 or if capacity is less than size
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Standard implementation of ListNode, designed for Generics.
	 * @author Jeremiah Knizley
	 *
	 */
	private class ListNode {
		
		/** The data being stored in the node. The type passed into 
		 * LinkedAbstractList is the same type as the data */
		private E data;
		/** The next node in the chain of nodes that make up the LinkedAbstractList */
		private ListNode next;
		
//		/**
//		 * Constructor for ListNode when adding a node to the end of the 
//		 * LinkedAbstractList
//		 * @param data the object being stored in the node
//		 */
//		public ListNode(E data) {
//			this.data = data;
//			this.next = null;
//		}
		
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
