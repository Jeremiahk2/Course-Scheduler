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
	
	

}
