/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests CourseRoll
 * @author Jeremiah Knizley
 * @author Spencer Grattan
 *
 */
class CourseRollTest {

	/**
	 * Test method for CourseRoll constructor. Also tests getEnrollmentCap, getOpenSeats and setEnrollmentCapacity in the process
	 */
	@Test
	void testCourseRoll() {
		Course testCourse = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sheckman", 100, "MW");
		assertDoesNotThrow(() -> new CourseRoll(testCourse, 100));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(testCourse, 9));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(testCourse, 251));
		
		CourseRoll roll = new CourseRoll(testCourse, 100);
		
		assertEquals(100, roll.getEnrollmentCap());
		assertEquals(100, roll.getOpenSeats());
		
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(null, 10));
	}
	
	/**
	 * Tests the enroll method
	 */
	@Test
	void testEnroll() {
		Course testCourse = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sheckman", 100, "MW");
		CourseRoll roll = new CourseRoll(testCourse, 10);
		Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "pw");
		Student s2 = new Student("David", "Williams", "dwilly", "dwilly@ncsu.edu", "pw");
		Student s3 = new Student("Tyriq", "Brown", "tybrown", "tybrown@ncsu.edu", "pw");
		Student s4 = new Student("Spencer", "Grattan", "dsgratta", "dsgratta@ncsu.edu", "pw");
		Student s5 = new Student("Student 5", "LN", "stu5", "stu5@ncsu.edu", "pw");
		Student s6 = new Student("Student 6", "LN", "stu6", "stu6@ncsu.edu", "pw");
		Student s7 = new Student("Student 7", "LN", "stu7", "stu7@ncsu.edu", "pw");
		Student s8 = new Student("Student 8", "LN", "stu8", "stu8@ncsu.edu", "pw");
		Student s9 = new Student("Student 9", "LN", "stu9", "stu9@ncsu.edu", "pw");
		Student s10 = new Student("Student 10", "LN", "stu10", "stu10@ncsu.edu", "pw");
		Student s11 = new Student("Don't add me", "ahhh", "fdjslk", "kayleepoppop@yahoo.com", "pw");
		
		//test for null student
		assertThrows(IllegalArgumentException.class, 
				() -> roll.enroll(null));
		
		//test valid cases
		assertDoesNotThrow(() -> roll.enroll(s1));
		assertDoesNotThrow(() -> roll.enroll(s2));
		assertDoesNotThrow(() -> roll.enroll(s3));
		assertDoesNotThrow(() -> roll.enroll(s4));
		assertDoesNotThrow(() -> roll.enroll(s5));
		assertDoesNotThrow(() -> roll.enroll(s6));
		assertDoesNotThrow(() -> roll.enroll(s7));
		assertDoesNotThrow(() -> roll.enroll(s8));
		assertDoesNotThrow(() -> roll.enroll(s9));
		assertEquals(1, roll.getOpenSeats());
		
		//test for already enrolled student
		assertThrows(IllegalArgumentException.class, 
				() -> roll.enroll(s2));
		
		//add another student to fill the class
		assertDoesNotThrow(() -> roll.enroll(s10));
		assertEquals(0, roll.getOpenSeats());
		
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
	}
	
	/**
	 * Tests the drop method
	 */
	@Test
	void testDrop() {
		Course testCourse = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sheckman", 100, "MW");
		CourseRoll roll = new CourseRoll(testCourse, 10);
		Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "pw");
		Student s2 = new Student("David", "Williams", "dwilly", "dwilly@ncsu.edu", "pw");
		Student s3 = new Student("Tyriq", "Brown", "tybrown", "tybrown@ncsu.edu", "pw");
		
		//test for null student 
		assertThrows(IllegalArgumentException.class,
				() -> roll.drop(null));
		
		//populate roll
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		assertEquals(7, roll.getOpenSeats());
		
		//test valid remove student
		assertDoesNotThrow(() -> roll.drop(s3));
		assertEquals(8, roll.getOpenSeats());
	}
	
	/**
	 * Tests the canEnroll method
	 */
	@Test
	void testCanEnroll() {
		Course testCourse = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sheckman", 100, "MW");
		CourseRoll roll = new CourseRoll(testCourse, 10);
		Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "pw");
		Student s2 = new Student("David", "Williams", "dwilly", "dwilly@ncsu.edu", "pw");
		Student s3 = new Student("Tyriq", "Brown", "tybrown", "tybrown@ncsu.edu", "pw");
		Student s4 = new Student("Spencer", "Grattan", "dsgratta", "dsgratta@ncsu.edu", "pw");
		Student s5 = new Student("Student 5", "LN", "stu5", "stu5@ncsu.edu", "pw");
		Student s6 = new Student("Student 6", "LN", "stu6", "stu6@ncsu.edu", "pw");
		Student s7 = new Student("Student 7", "LN", "stu7", "stu7@ncsu.edu", "pw");
		Student s8 = new Student("Student 8", "LN", "stu8", "stu8@ncsu.edu", "pw");
		Student s9 = new Student("Student 9", "LN", "stu9", "stu9@ncsu.edu", "pw");
		Student s10 = new Student("Student 10", "LN", "stu10", "stu10@ncsu.edu", "pw");
		Student s11 = new Student("Student 11", "LN", "stu11", "stu11@ncsu.com", "pw");
		Student s12 = new Student("Student 12", "LN", "stu12", "stu12@ncsu.com", "pw");
		Student s13 = new Student("Student 13", "LN", "stu13", "stu13@ncsu.com", "pw");
		Student s14 = new Student("Student 14", "LN", "stu14", "stu14@ncsu.com", "pw");
		Student s15 = new Student("Student 15", "LN", "stu15", "stu15@ncsu.com", "pw");
		Student s16 = new Student("Student 16", "LN", "stu16", "stu16@ncsu.com", "pw");
		Student s17 = new Student("Student 17", "LN", "stu17", "stu17@ncsu.com", "pw");
		Student s18 = new Student("Student 18", "LN", "stu18", "stu18@ncsu.com", "pw");
		Student s19 = new Student("Student 19", "LN", "stu19", "stu19@ncsu.com", "pw");
		Student s20 = new Student("Student 20", "LN", "stu20", "stu20@ncsu.com", "pw");
		Student s21 = new Student("Student 21", "LN", "stu21", "stu21@ncsu.com", "pw");
		
		//test case returns true
		assertTrue(roll.canEnroll(s1));
		
		//test for already enrolled student 
		roll.enroll(s1);
		assertEquals(9, roll.getOpenSeats());
		assertFalse(roll.canEnroll(s1));
		
		//test for no more room in class
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		assertTrue(roll.canEnroll(s11));
		
		roll.enroll(s11);
		assertEquals(1, roll.getNumberOnWaitlist());
		roll.enroll(s12);
//		roll.enroll(s13);
//		roll.enroll(s14);
//		roll.enroll(s15);
//		roll.enroll(s16);
//		roll.enroll(s17);
//		roll.enroll(s18);
//		roll.enroll(s19);
//		roll.enroll(s20);
//		assertEquals(10, roll.getNumberOnWaitlist());
		// try to add student 11th student to waitlist 
//		Exception e = assertThrows(IllegalArgumentException.class, () -> (roll.canEnroll(s21)));
//		assertEquals("Something went wrong", e.getMessage());
//		// try to add student to waitilist that is already on waitlist
//		assertFalse(roll.canEnroll(s11));
		
		
	}

	/**
	 * Tests the CourseRoll.setEnrollementCap method 
	 */
	@Test 
	void testSetEnrollmentCap() {
		Course testCourse = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sheckman", 100, "MW");
		CourseRoll roll = new CourseRoll(testCourse, 15);
		Student s1 = new Student("firstName", "lastName", "id", "email@ncsu.edu", "pw");
		Student s2 = new Student("David", "Williams", "dwilly", "dwilly@ncsu.edu", "pw");
		Student s3 = new Student("Tyriq", "Brown", "tybrown", "tybrown@ncsu.edu", "pw");
		Student s4 = new Student("Spencer", "Grattan", "dsgratta", "dsgratta@ncsu.edu", "pw");
		Student s5 = new Student("Student 5", "LN", "stu5", "stu5@ncsu.edu", "pw");
		Student s6 = new Student("Student 6", "LN", "stu6", "stu6@ncsu.edu", "pw");
		Student s7 = new Student("Student 7", "LN", "stu7", "stu7@ncsu.edu", "pw");
		Student s8 = new Student("Student 8", "LN", "stu8", "stu8@ncsu.edu", "pw");
		Student s9 = new Student("Student 9", "LN", "stu9", "stu9@ncsu.edu", "pw");
		Student s10 = new Student("Student 10", "LN", "stu10", "stu10@ncsu.edu", "pw");
		Student s11 = new Student("Student 11", "LN", "stu11", "stu11@ncsu.com", "pw");
		
		roll.enroll(s1);
		roll.enroll(s2);
		roll.enroll(s3);
		roll.enroll(s4);
		roll.enroll(s5);
		roll.enroll(s6);
		roll.enroll(s7);
		roll.enroll(s8);
		roll.enroll(s9);
		roll.enroll(s10);
		roll.enroll(s11);
		assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(10));
	}
	
}
