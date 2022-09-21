package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.Test;

/**
 * Tests SortedList.
 * @author Geigh Neill
 * @author Jeremiah Knizley
 * @author Sahil Kanchan
 *
 */
public class SortedListTest {

	/**
	 * Tests SortedList size and order after changes. Tests list capacity grows accordingly.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		assertTrue(list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		
		String s = "";
		for (int i = 0; i < list.size(); i++) {
			s += list.get(i);
		}
		
		String equals = "abcdefghijk";
		System.out.println(s);
		assertEquals(11, list.size());
		assertEquals(s, equals);
	
	}

	/**
	 * Tests add()
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		//test adding to the front
		list.add("apple");
		list.add("coconut");
		//test adding to the middle
		list.add("cherry");
		//test adding to the end
		list.add("pineapple");
		//testing null and duplicate
		Exception e1 = assertThrows(NullPointerException.class, () -> list.add(null));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add("coconut"));
		assertEquals("Element already in list.", e2.getMessage());
		//test fist element is correct
		assertEquals(list.get(0), "apple");
		//test middle element is correct
		assertEquals(list.get(2), "cherry");
		//test last element is correct
		assertEquals(list.get(list.size() - 1), "pineapple");
	}
	
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		
		//TODO Add some elements to the list
		
		//TODO Test getting an element at an index < 0
		
		//TODO Test getting an element at size
		
	}
	
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		
		//TODO Add some elements to the list - at least 4
		
		//TODO Test removing an element at an index < 0
		
		//TODO Test removing an element at size
		
		//TODO Test removing a middle element
		
		//TODO Test removing the last element
		
		//TODO Test removing the first element
		
		//TODO Test removing the last element
	}
	
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		
		//TODO Add some elements
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		
		//TODO Test checking the index of null
		
	}
	
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		
		//TODO Clear the list
		
		//TODO Test that the list is empty
	}

	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		
		//TODO Add at least one element
		
		//TODO Check that the list is no longer empty
	}

	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		
		//TODO Add some elements
		
		//TODO Test some true and false cases
	}
	
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for equality and non-equality
	}
	
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		
		//TODO Test for the same and different hashCodes
	}

}
 