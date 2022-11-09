/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class is the test class for the ArrayStack object that makes sure it behaves
 * as a stack with a set capacity is expected to
 * @author albressl
 */
class ArrayStackTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#ArrayStack(int)}.
	 */
	@Test
	void testArrayStack() {
		assertThrows(IllegalArgumentException.class, () -> new ArrayStack<Integer>(-20));
		assertDoesNotThrow(() -> new ArrayStack<Integer>(5));
		assertDoesNotThrow(() -> new ArrayStack<Integer>(0));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#push(java.lang.Object)}.
	 */
	@Test
	void testPush() {
		ArrayStack<String> testStack = new ArrayStack<String>(2);
		assertDoesNotThrow(() -> testStack.push("Hi"));
		assertDoesNotThrow(() -> testStack.push("Hello"));
		assertThrows(IllegalArgumentException.class, () -> testStack.push("Hola"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#pop()}.
	 */
	@Test
	void testPop() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#size()}.
	 */
	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.ArrayStack#setCapacity(int)}.
	 */
	@Test
	void testSetCapacity() {
		fail("Not yet implemented");
	}

}
