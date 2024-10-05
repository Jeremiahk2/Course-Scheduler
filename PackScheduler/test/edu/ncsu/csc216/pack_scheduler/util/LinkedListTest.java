package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;

import org.junit.jupiter.api.Test;

/**
 * Tests the LinkedList class
 * @author Allie Norton
 *
 */
class LinkedListTest {

	/**
	 * Tests the LinkedList constructor
	 */
	@Test
	void testLinkedList() {
		LinkedList<String> l1 = assertDoesNotThrow(
				() -> new LinkedList<String>());
		assertEquals(0, l1.size());
	}

	/**
	 * Tests the LinkedList.get method
	 */
	@Test
	void testGet() {
		LinkedList<String> l1 = new LinkedList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.get(4));
		assertEquals("Banana", l1.get(0));
		assertEquals("Strawberry", l1.get(3));
	}

	/**
	 * Tests the LinkedList.add method
	 */
	@Test
	void testAdd() {
		LinkedList<String> l1 = new LinkedList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));

		assertDoesNotThrow(() -> l1.add(2, "Pineapple"));

		assertEquals("Pineapple", l1.get(2));
		assertEquals("Cherry", l1.get(3));

		assertEquals(5, l1.size());
		assertThrows(IllegalArgumentException.class, () -> l1.add("Pineapple"));

		assertEquals(5, l1.size());

		assertDoesNotThrow(() -> l1.add(5, "Kiwi"));
		assertDoesNotThrow(() -> l1.add(6, "Blueberry"));
		assertDoesNotThrow(() -> l1.add(7, "Watermelon"));
		assertDoesNotThrow(() -> l1.add(8, "Orange"));
		assertDoesNotThrow(() -> l1.add(9, "Chocolate"));

		assertEquals(10, l1.size());

		assertDoesNotThrow(() -> l1.add(10, "studnet"));

		assertEquals(11, l1.size());

		assertThrows(NullPointerException.class, () -> l1.add(null));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.add(-1, "yeet"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.add(420, "yeet"));

	}

	/**
	 * Tests the LinkedList.remove method
	 */
	@Test
	void testRemove() {
		LinkedList<String> l1 = new LinkedList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));
		assertDoesNotThrow(() -> l1.remove(1));
		assertEquals("Banana", l1.get(0));
		assertEquals("Cherry", l1.get(1));
		assertEquals("Strawberry", l1.get(2));
		assertEquals(l1.size(), 3);
		assertThrows(IndexOutOfBoundsException.class, () -> l1.remove(3));
		assertEquals("Cherry", l1.remove(1));
		assertEquals(l1.size(), 2);
		assertThrows(IndexOutOfBoundsException.class, () -> l1.remove(2));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.get(2));
		
		
	}
	
	/**
	 * Tests the LinkedListIterator's ability to move through the list
	 */
	@Test
	void testIteratorMovement() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		assertDoesNotThrow(() -> list.add(0, 0));
		assertDoesNotThrow(() -> list.add(1, 1));
		assertDoesNotThrow(() -> list.add(2, 2));
		assertDoesNotThrow(() -> list.add(3, 3));
		assertDoesNotThrow(() -> list.add(4, 4));
		ListIterator<Integer> iterator = list.listIterator();
		assertFalse(iterator.hasPrevious());
		assertEquals(0, iterator.next());
		assertEquals(1, iterator.next());
		assertEquals(2, iterator.next());
		assertEquals(3, iterator.next());
		assertEquals(4, iterator.next());
		assertFalse(iterator.hasNext());
		assertEquals(4, iterator.previous());
		assertEquals(3, iterator.previous());
		assertTrue(iterator.hasPrevious());
		assertTrue(iterator.hasNext());
	}
	
	/**
	 * Tests the LinkedList.set method
	 */
	@Test
	void testSet() {
		LinkedList<String> l1 = new LinkedList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		
		assertDoesNotThrow(() -> assertEquals("Banana", l1.set(0, "Pineapple")));
		assertEquals(1, l1.size());
		assertEquals("Pineapple", l1.get(0));
		
		assertDoesNotThrow(() -> assertEquals("Pineapple", l1.set(0, "Banana")));
		assertEquals(1, l1.size());
		assertEquals("Banana", l1.get(0));
		
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));

		assertDoesNotThrow(() -> assertEquals("Apple", l1.set(1, "Pineapple")));
		assertEquals(4, l1.size());
		assertEquals("Pineapple", l1.get(1));
		
		assertDoesNotThrow(() -> assertEquals("Strawberry", l1.set(3, "Apple")));
		assertEquals(4, l1.size());
		assertEquals("Apple", l1.get(3));
		
		assertDoesNotThrow(() -> assertEquals("Banana", l1.set(0, "Melon")));
		assertEquals(4, l1.size());
		assertEquals("Melon", l1.get(0));

		assertThrows(NullPointerException.class, () -> l1.set(2, null));
		assertThrows(IllegalArgumentException.class, () -> l1.set(3, "Apple"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-1, "Chocolate"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-4, "Orange"));

	}


}
