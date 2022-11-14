package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LinkedAbstractListTest {

	/**
	 * Tests the LinkedAbstractList constructor
	 */
	@Test
	void testLinkedAbstractList() {
		LinkedAbstractList<String> l1 = assertDoesNotThrow(
				() -> new LinkedAbstractList<String>(10));
		assertEquals(0, l1.size());
		assertThrows(IllegalArgumentException.class, () -> new LinkedAbstractList<String>(-1));
	}

	/**
	 * Tests the LinkedAbstractList.get method
	 */
	@Test
	void testGet() {
		LinkedAbstractList<String> l1 = new LinkedAbstractList<String>(10);
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
	 * Tests the LinkedAbstractList.add method
	 */
	@Test
	void testAdd() {
		LinkedAbstractList<String> l1 = new LinkedAbstractList<String>(10);
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));

		l1.add(2, "Pineapple");
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
		
		LinkedAbstractList<String> l2 = new LinkedAbstractList<String>(10);
		
		l2.add(0, "apple");
		l2.get(0);
		l2.add(0, "orange");
		assertEquals(2, l2.size());
		assertEquals("orange", l2.get(0));
		l2.get(1);

		//Unnecessary because size should not increase beyond it's base capacity of 10
//		assertDoesNotThrow(() -> l1.add(10, "studnet"));
//
//		assertEquals(11, l1.size());

		assertThrows(NullPointerException.class, () -> l1.add(null));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.add(-1, "yeet"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.add(420, "yeet"));

	}

	/**
	 * Tests the LinkedAbstractList.remove method
	 */
	@Test
	void testRemove() {
		LinkedAbstractList<String> l1 = new LinkedAbstractList<String>(10);
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
	 * Tests the LinkedAbstractList.set method
	 */
	@Test
	void testSet() {
		LinkedAbstractList<String> l1 = new LinkedAbstractList<String>(10);
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));

		assertDoesNotThrow(() -> assertEquals("Banana", l1.set(0, "Kiwi")));
		assertDoesNotThrow(() -> assertEquals("Apple", l1.set(1, "Pineapple")));
		assertEquals(4, l1.size());
		assertEquals("Pineapple", l1.get(1));

		assertThrows(NullPointerException.class, () -> l1.set(2, null));
		assertThrows(IllegalArgumentException.class, () -> l1.set(3, "Kiwi"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-1, "Chocolate"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.set(-4, "Orange"));
	}

}
