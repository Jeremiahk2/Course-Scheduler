/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * @author Spencer Grattan
 * @param E 
 *
 */
public class LinkedAbstractList<E> extends AbstractList{

	/**  */
	private ListNode front;
	/**  */
	private int size;
	/**  */
	private int capacity;
	
	/**
	 * Constructor for LinkedAbstractList
	 * @param capacity 
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
	public Object get(int index) {
		// TODO Auto-generated method stub
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
		
	}
	
	/**
	 * 
	 */
	@Override
	public E remove(int idx) {
		
	}
	
	/**
	 * 
	 * @param idx
	 * @param element
	 */
	@Override
	public void set(int idx, E element) {
		
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
