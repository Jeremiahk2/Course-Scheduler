package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayQueue class
 * @author Allie Norton
 *
 */
class ArrayQueueTest {
	
	/** the max capapcity for testing */
	private static final int CAPACITY = 5;

	/**
	 * Tests the constructor for ArrayQueue
	 */
	@Test
	void testArrayQueue() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		assertEquals(0, a1.size());
	}

	/**
	 * Tests ArrayQueue.enqueue method
	 */
	@Test
	void testEnqueue() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		
		a1.enqueue("first");
		assertEquals(1, a1.size());
		a1.enqueue("second");
		assertEquals(2, a1.size());
		a1.enqueue("third");
		assertEquals(3, a1.size());
		a1.enqueue("fourth");
		assertEquals(4, a1.size());
		a1.enqueue("fifth");
		assertEquals(5, a1.size());
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> a1.enqueue("sixth"));
		assertEquals("The list has reached capacity.", e.getMessage());
	}

	/**
	 * Tests ArrayQueue.dequeue method
	 */
	@Test
	void testDequeue() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		
		Exception e = assertThrows(NoSuchElementException.class, () -> a1.dequeue());
		assertEquals("The queue is empty", e.getMessage());
		
		a1.enqueue("first");
		a1.enqueue("second");
		a1.enqueue("third");
		assertEquals(3, a1.size());
		
		a1.dequeue();
		assertEquals(2, a1.size());
		a1.dequeue();
		assertEquals(1, a1.size());
		a1.dequeue();
		assertEquals(0, a1.size());
	}

	/**
	 * Tests ArrayQueue.isEmpty method
	 */
	@Test
	void testIsEmpty() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		
		assertTrue(a1.isEmpty());
		a1.enqueue("first");
		a1.enqueue("second");
		a1.enqueue("third");
		assertFalse(a1.isEmpty());
		a1.dequeue();
		a1.dequeue();
		a1.dequeue();
		assertTrue(a1.isEmpty());

	}

	/**
	 * Tests ArrayQueue.setCapacity method
	 */
	@Test
	void testSetCapacity() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		
		a1.enqueue("first");
		a1.enqueue("second");
		assertEquals(2, a1.size());
		a1.setCapacity(3);
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> a1.setCapacity(-1));
		assertEquals("Invalid capacity.", e.getMessage());
		e = assertThrows(IllegalArgumentException.class, () -> a1.setCapacity(1));
		assertEquals("Invalid capacity.", e.getMessage());
		
	}
	
	/**
	 * Tests ArrayQueue.setCapacity method
	 */
	@Test
	void testContains() {
		ArrayQueue<String> a1 = new ArrayQueue<String>(CAPACITY);
		
		a1.enqueue("first");
		a1.enqueue("second");
		assertFalse(a1.contains("third"));
		assertTrue(a1.contains("first"));
		assertTrue(a1.contains("second"));
	}

}
