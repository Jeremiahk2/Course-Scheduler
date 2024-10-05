package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class LinkedQueueTest {
	/** the max capapcity for testing */
	private static final int CAPACITY = 5;

	/**
	 * Tests the constructor for ArrayQueue
	 */
	@Test
	void testLinkedQueue() {
		LinkedQueue<String> a1 = new LinkedQueue<String>(CAPACITY);
		assertEquals(0, a1.size());
	}

	/**
	 * Tests ArrayQueue.enqueue method
	 */
	@Test
	void testEnqueue() {
		LinkedQueue<String> a1 = new LinkedQueue<String>(CAPACITY);
		
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
		
		assertThrows(IllegalArgumentException.class, () -> a1.enqueue("sixth"));
	}

	/**
	 * Tests ArrayQueue.dequeue method
	 */
	@Test
	void testDequeue() {
		LinkedQueue<String> a1 = new LinkedQueue<String>(CAPACITY);
		
		assertThrows(NoSuchElementException.class, () -> a1.dequeue());
		
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
		LinkedQueue<String> a1 = new LinkedQueue<String>(CAPACITY);
		
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
		LinkedQueue<String> a1 = new LinkedQueue<String>(CAPACITY);
		
		a1.enqueue("first");
		a1.enqueue("second");
		assertEquals(2, a1.size());
		a1.setCapacity(3);
		
		assertThrows(IllegalArgumentException.class, () -> a1.setCapacity(-1));
		assertThrows(IllegalArgumentException.class, () -> a1.setCapacity(1));
	}

}
