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
		
	}
	
	

}
