/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests CourseRoll
 * @author Jeremiah Knizley
 *
 */
class CourseRollTest {

	/**
	 * Test method for CourseRoll constructor. Also tests getEnrollmentCap, getOpenSeats and setEnrollmentCapacity in the process
	 */
	@Test
	void testCourseRoll() {
		assertDoesNotThrow(() -> new CourseRoll(100));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(9));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251));
		
		CourseRoll roll = new CourseRoll(100);
		
		assertEquals(100, roll.getEnrollmentCap());
		assertEquals(100, roll.getOpenSeats());
	}

}
