/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test the Schedule Class 
 * @author Spencer Grattan
 * @author Jeremiah Knizley
 *
 */
class ScheduleTest {
	
	/**
	 * Tests the Schedule constructor
	 */
	@Test
	void testSchedule() {
		//tests for default constructor values
		Schedule s1 = assertDoesNotThrow(() -> new Schedule());
		assertEquals("My Schedule", s1.getTitle());
		String[][] emptyTestArray = new String[][] {};
		assertArrayEquals(emptyTestArray, s1.getScheduledCourses());
	}
	
	/**
	 * Tests the addCourseToSchedule method
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule s1 = new Schedule();
		Course c = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", "MWF", 1300, 1400);
		
		//tests for valid course addition
		assertDoesNotThrow(() -> s1.addCourseToSchedule(c));
		String[][] testArray = new String[][] { {"CSC123", "001", "Learning to type", c.getMeetingString()} };
 		assertArrayEquals(testArray, s1.getScheduledCourses());
		
		//tests for adding duplicate course
		Course c2 = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", "MWF", 1500, 1600);
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> s1.addCourseToSchedule(c2));
		assertEquals("You are already enrolled in CSC123", e1.getMessage());
		
		//tests for adding conflicting course
		Course c3 = new Course("CSC567", "Intro to Mainframe Hacking", "002", 3, "goobo", "MWF", 1300, 1400);
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> s1.addCourseToSchedule(c3));
		assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
		
		//tests for adding null
		assertThrows(NullPointerException.class, () -> s1.addCourseToSchedule(null));
	}
	
	/**
	 * Test the removeCourseFromSchedule method
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Schedule s1 = new Schedule();
		Course c = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", "MWF", 1300, 1400);
		
		//populate schedule
		assertDoesNotThrow(() -> s1.addCourseToSchedule(c));
		
		//test course not in schedule
		Course c3 = new Course("CSC567", "Intro to Mainframe Hacking", "002", 3, "goobo", "MWF", 1300, 1400);
		assertFalse(s1.removeCourseFromSchedule(c3));
		
		//test course in schedule
		assertTrue(s1.removeCourseFromSchedule(c));
		String[][] testArray = new String[][] {};
		assertArrayEquals(testArray, s1.getScheduledCourses());
	}
	
	/**
	 * Tests the resetSchedule method
	 */
	@Test
	void testResetSchedule() {
		Schedule s1 = new Schedule();
		Course c = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", "MWF", 1300, 1400);
		
		//populate schedule and change title
		s1.addCourseToSchedule(c);
		s1.setTitle("mmmmmmmmm");
		
		//test reset
		s1.resetSchedule();
		assertEquals("My Schedule", s1.getTitle());
		String[][] testArray = new String[][] {};
		assertArrayEquals(testArray, s1.getScheduledCourses());
	}
	
	/**
	 * Tests the getScheduledCorses method
	 */
	@Test
	void testGetScheduledCourses() {
		//test empty schedule
		Schedule s1 = new Schedule();
		String[][] testArray = new String[][] {};
		assertArrayEquals(testArray, s1.getScheduledCourses());
		
		//populate schedule
		Course c = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", "MWF", 1300, 1400);
		Course c1 = new Course ("CSC234", "Intro to houses", "001", 3, "scooby", "TH", 1000, 1100);
		s1.addCourseToSchedule(c);
		s1.addCourseToSchedule(c1);
		
		//test populated schedule
		String[][] testArray2 = new String[][] {{c.getName(), c.getSection(), c.getTitle(), c.getMeetingString()},
			{c1.getName(), c1.getSection(), c1.getTitle(), c1.getMeetingString()}};
		assertArrayEquals(testArray2, s1.getScheduledCourses());
	}
	
	/**
	 * Tests the setTitle method
	 */
	@Test
	void testSetTitle() {
		Schedule s1 = new Schedule();
		//tests valid case
		s1.setTitle("Wa wa");
		assertEquals("Wa wa", s1.getTitle());
		
		//tests null
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> s1.setTitle(null));
		assertEquals("Title cannot be null.", e1.getMessage());
	}
	
	

}
