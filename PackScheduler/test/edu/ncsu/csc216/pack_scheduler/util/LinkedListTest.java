package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

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
	void testArrayList() {
		ArrayList<String> l1 = assertDoesNotThrow(
				() -> new ArrayList<String>());
		assertEquals(0, l1.size());
	}

	/**
	 * Tests the LinkedList.get method
	 */
	@Test
	void testGet() {
		ArrayList<String> l1 = new ArrayList<String>();
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
		ArrayList<String> l1 = new ArrayList<String>();
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
		ArrayList<String> l1 = new ArrayList<String>();
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
	 * Tests the LinkedList.set method
	 */
	@Test
	void testSet() {
		ArrayList<String> l1 = new ArrayList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));

		assertDoesNotThrow(() -> assertEquals("Apple", l1.set(1, "Pineapple")));
		assertEquals(4, l1.size());
		assertEquals("Pineapple", l1.get(1));

		assertThrows(NullPointerException.class, () -> l1.set(2, null));
		assertThrows(IllegalArgumentException.class, () -> l1.set(3, "Banana"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-1, "Chocolate"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-4, "Orange"));

	}


}
