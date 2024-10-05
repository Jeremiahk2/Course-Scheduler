/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		assertDoesNotThrow(() -> new CourseRoll(100));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(9));
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(251));
		
		CourseRoll roll = new CourseRoll(100);
		
		assertEquals(100, roll.getEnrollmentCap());
		assertEquals(100, roll.getOpenSeats());
	}
	
	/**
	 * Tests the enroll method
	 */
	@Test
	void testEnroll() {
		CourseRoll roll = new CourseRoll(10);
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
		
		//test for no more room in the class
		assertThrows(IllegalArgumentException.class, 
				() -> roll.enroll(s11));
	}
	
	/**
	 * Tests the drop method
	 */
	@Test
	void testDrop() {
		CourseRoll roll = new CourseRoll(10);
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
		CourseRoll roll = new CourseRoll(10);
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
		
		//test case returns true
		assertTrue(roll.canEnroll(s1));
		
		//test for already enrolled student 
		roll.enroll(s1);
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
		assertFalse(roll.canEnroll(s11));
	}

}
