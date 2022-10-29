package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Tests the ArrayList constructor
	 */
	@Test
	void testArrayList() {
		ArrayList<String> l1 = assertDoesNotThrow(
				() -> new ArrayList<String>());
		assertEquals(0, l1.size());
	}
	
	/**
	 * Tests the ArrayList.get()
	 */
	@Test
	void testGet() {
		ArrayList<String> l1 = new ArrayList<String>();
		assertDoesNotThrow(() -> l1.add(0, "Banana"));
		assertDoesNotThrow(() -> l1.add(1, "Apple"));
		assertDoesNotThrow(() -> l1.add(2, "Cherry"));
		assertDoesNotThrow(() -> l1.add(3, "Strawberry"));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.get(-2));
		assertThrows(IndexOutOfBoundsException.class, () -> l1.get(420));
	}
	
	/**
	 * Tests the ArrayList.add
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
	
	

}
