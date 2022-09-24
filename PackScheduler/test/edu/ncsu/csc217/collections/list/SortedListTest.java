package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
	
	/**
	 * Tests the sortedList.get() method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
		//setup
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		list.add("oranges");
		//Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		//Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		
	}
	
	/**
	 * Tests the SortedList.remove() method 
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		//setup
		list.add("pear");
		list.add("pretzel"); 
		list.add("banana");
		list.add("strawberry"); 
		list.add("apple"); 
		list.add("cherry");
		list.add("orange"); 
		//Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		//Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		//Test removing a middle element
		assertTrue(list.contains("orange"));
		assertDoesNotThrow(() -> list.remove(3));
		assertFalse(list.contains("orange"));
		//Test removing the last element
		assertTrue(list.contains("strawberry"));
		assertDoesNotThrow(() -> list.remove(list.size() - 1));
		assertFalse(list.contains("strawberry"));
		//Test removing the first element
		assertTrue(list.contains("apple"));
		assertDoesNotThrow(() -> list.remove(0));
		assertFalse(list.contains("apple"));
		//Test removing the last element
		assertTrue(list.contains("pretzel"));
		assertDoesNotThrow(() -> list.remove(list.size() - 1));
		assertFalse(list.contains("pretzel"));
		assertEquals("banana", list.get(0));
		assertEquals("cherry", list.get(1));
		assertEquals("pear", list.get(2));
		
		
	}
	/**
	 * tests the SortedList.indexOf() method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
		//setup
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		list.add("orange"); 
		list.add("pear");
		list.add("pretzel"); 
		list.add("strawberry"); 
		list.add("pear");
		//Test various calls to indexOf for elements in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("cherry"));
		assertEquals(3, list.indexOf("orange"));
		assertEquals(6, list.indexOf("strawberry"));
		assertEquals(5, list.indexOf("pretzel"));
		assertEquals(4, list.indexOf("pear"));
		//Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));
		
	}
	/**
	 * tests SortedList.clear() method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//Add some elements
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		list.add("orange"); 
		list.add("pear");
		list.add("pretzel"); 
		list.add("strawberry"); 
		list.add("pear");
		//Clear the list
		list.clear();
		//Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * tests SortedList.IsEmpty();
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//Test that the list starts empty
		assertTrue(list.isEmpty());
		//setup
		list.add("apple");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}
	
	/**
	 * tests SortedList.contains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test the empty list case
		assertFalse(list.contains("apple"));
		//setup
		list.add("apple");
		list.add("banana");
		list.add("cherry");
		list.add("orange"); 
		list.add("pear");
		list.add("pretzel"); 
		list.add("strawberry"); 
		list.add("pear");
		//Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("cherry"));
		assertFalse(list.contains("CHERRY"));
		assertFalse(list.contains("woah"));
		assertTrue(list.contains("pear"));
	}
	
	/**
	 * tests SortedList.equals() method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//Make two lists the same and one list different
		list1.add("apple");
		list1.add("banana");
		list1.add("pear");
		list1.add("pretzel"); 
		list1.add("strawberry"); 
		list1.add("pear");
		//create identical list
		list2.add("apple");
		list2.add("banana");
		list2.add("pear");
		list2.add("pretzel"); 
		list2.add("strawberry"); 
		list2.add("pear");
		//create different list
		list3.add("apple");
		list3.add("banana");
		list3.add("cherry");
		list3.add("orange"); 
		list3.add("pear");
		list3.add("pretzel"); 
		list3.add("strawberry"); 
		list3.add("pear");
		//Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
		assertTrue(list1.equals(list1));
		assertTrue(list2.equals(list2));
		assertTrue(list3.equals(list3));
	}
	
	/**
	 * tests SortedList.hashCode()
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Make list
		list1.add("apple");
		list1.add("banana");
		list1.add("pear");
		list1.add("pretzel"); 
		list1.add("strawberry"); 
		list1.add("pear");
		//create identical list
		list2.add("apple");
		list2.add("banana");
		list2.add("pear");
		list2.add("pretzel"); 
		list2.add("strawberry"); 
		list2.add("pear");
		//create different list
		list3.add("apple");
		list3.add("banana");
		list3.add("cherry");
		list3.add("orange"); 
		list3.add("pear");
		list3.add("pretzel"); 
		list3.add("strawberry"); 
		list3.add("pear");
		//Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 