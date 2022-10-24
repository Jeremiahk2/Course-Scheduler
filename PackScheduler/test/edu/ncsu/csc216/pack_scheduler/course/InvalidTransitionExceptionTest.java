package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the InvalidTransitionException class
 * @author Spencer Grattan
 *
 */
class InvalidTransitionExceptionTest {

	/**
	 * Tests constructor with default message
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ite = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ite.getMessage());
	}
	
	/**
	 * Tests constructor with custom message
	 */
	@Test
	void testInvalidTransitionExceptionMessage() {
		InvalidTransitionException ite = new InvalidTransitionException("ahh");
		assertEquals("ahh", ite.getMessage());
	}

}