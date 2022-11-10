/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * This class is the test class for the ArrayStack object that makes sure it behaves
 * as a stack with a set capacity is expected to
 * @author albressl
 */
class ArrayStackTest {

	/**
	 * Test method for the ArrayStack constructor to make sure it constructs the stack properly
	 */
	@Test
	void testArrayStack() {
		assertThrows(IllegalArgumentException.class, () -> new ArrayStack<Integer>(-20));
		assertDoesNotThrow(() -> new ArrayStack<Integer>(5));
		assertDoesNotThrow(() -> new ArrayStack<Integer>(0));
	}

	/**
	 * Test method for push method to make sure objects can be pushed onto the stack.
	 */
	@Test
	void testPush() {
		ArrayStack<String> testStack = new ArrayStack<String>(2);
		assertDoesNotThrow(() -> testStack.push("Hi"));
		assertDoesNotThrow(() -> testStack.push("Hello"));
		assertThrows(IllegalArgumentException.class, () -> testStack.push("Hola"));
	}

	/**
	 * Test method for pop method to make sure things can be returned form the stack correctly.
	 */
	@Test
	void testPop() {
		ArrayStack<Integer> testStack = new ArrayStack<Integer>(2);
		assertThrows(EmptyStackException.class, () -> testStack.pop());
		assertDoesNotThrow(() -> testStack.push(1));
		assertDoesNotThrow(() -> testStack.push(2));
		assertEquals(2, testStack.pop());
		assertDoesNotThrow(() -> testStack.push(3));
		assertEquals(3, testStack.pop());
		assertEquals(1, testStack.pop());
		assertThrows(EmptyStackException.class, () -> testStack.pop());
	}

	/**
	 * Test method for the is empty method to make sure it returns true when the stack is empty.
	 */
	@Test
	void testIsEmpty() {
		ArrayStack<Double> testStack = new ArrayStack<Double>(2);
		assertTrue(testStack.isEmpty());
		assertDoesNotThrow(() -> testStack.push(1.0));
		assertDoesNotThrow(() -> testStack.push(2.0));
		assertFalse(testStack.isEmpty());
		assertEquals(2.0, testStack.pop());
		assertEquals(1.0, testStack.pop());
		assertTrue(testStack.isEmpty());
	}

	/**
	 * Test method for the size method to make sure it returns size properly.
	 */
	@Test
	void testSize() {
		ArrayStack<Integer> testStack = new ArrayStack<Integer>(2);
		assertEquals(0, testStack.size());
		assertDoesNotThrow(() -> testStack.push(1));
		assertEquals(1, testStack.size());
		assertDoesNotThrow(() -> testStack.push(2));
		assertEquals(2, testStack.size());
		assertEquals(2, testStack.pop());
		assertEquals(1, testStack.size());
	}

	/**
	 * Test method for set capacity method to see if it can be changed
	 */
	@Test
	void testSetCapacity() {
		ArrayStack<Double> testStack = new ArrayStack<Double>(2);
		assertDoesNotThrow(() -> testStack.push(1.0));
		assertDoesNotThrow(() -> testStack.push(2.0));
		assertThrows(IllegalArgumentException.class, () -> testStack.push(3.0));
		assertDoesNotThrow(() -> testStack.setCapacity(3));
		assertDoesNotThrow(() -> testStack.push(3.0));
		assertThrows(IllegalArgumentException.class, () -> testStack.setCapacity(1));
		assertEquals(3.0, testStack.pop());
		assertEquals(2.0, testStack.pop());
		assertEquals(1.0, testStack.pop());
		assertDoesNotThrow(() -> testStack.setCapacity(0));
		assertThrows(IllegalArgumentException.class, () -> testStack.setCapacity(-1));
	}

}
