/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests Faculty class
 * @author Jeremiah Knizley
 *
 */
class FacultyTest {

	/** a valid first name for a Faculty*/
	private static final String FIRST_NAME = "firstname";
	
	/** a valid last name for a Faculty*/
	private static final String LAST_NAME = "lastname";
	
	/** a valid ID for a Faculty*/
	private static final String ID = "id";
	
	/** a valid email for a Faculty*/
	private static final String EMAIL = "firstlast@ncsu.edu";
	
	/** a valid password for a Faculty */
	private static final String PASSWORD = "Password123";
	
	/** the default max courses for a Faculty */
	private static final int MAX_COURSE = 3;
	
	/**
	 * Tests hashCode for Faculty.java
	 */
	@Test
	void testHashCode() {
		
		User c1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		User c2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		User c3 = new Faculty("testfirstname", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		User c4 = new Faculty(FIRST_NAME, "testlastname", ID, EMAIL, PASSWORD, MAX_COURSE);
		User c5 = new Faculty(FIRST_NAME, LAST_NAME, "ttest", EMAIL, PASSWORD, MAX_COURSE);
		User c6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "test@ncsu.edu", PASSWORD, MAX_COURSE);
		User c7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "Testpassword123", MAX_COURSE);
		
		assertEquals(c1.hashCode(), c2.hashCode());
		
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
	}

	/**
	 * Tests Faculty constructor when max courses are given
	 */
	@Test
	void testFacultyCourses() {
		
		Faculty c = assertDoesNotThrow(() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE), "Should not throw exception");
		
		assertAll("Faculty", () -> assertEquals(FIRST_NAME, c.getFirstName(), "incorrect first name"), 
							 () -> assertEquals(LAST_NAME, c.getLastName(), "incorrect last name"),
							 () -> assertEquals(ID, c.getId(), "incorrect id"),
							 () -> assertEquals(EMAIL, c.getEmail(), "incorrect email"),
							 () -> assertEquals(PASSWORD, c.getPassword(), "incorrect password"),
							 () -> assertEquals(MAX_COURSE, c.getMaxCourses(), "incorrect max courses"));
	}

	/**
	 * Tests setMaxCourses with invalid input
	 * @param courses an invalid courses number
	 */
	@ParameterizedTest
	@ValueSource(ints = {0, -1, 19, 20})
	void testSetMaxCoursesInvalid(int courses) {
		
		Faculty faculty = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> faculty.setMaxCourses(courses));
		assertEquals("Invalid max courses", exception.getMessage(), "Incorrect exception thrown with invalid courses - " + MAX_COURSE);
	}
	
	/**
	 * Tests setMaxCourses with valid input
	 * @param courses an invalid max courses value
	 */
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	public void testSetCoursesValid(int courses) {
		Faculty faculty = assertDoesNotThrow(
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, courses),
				"Should not throw exception");
		assertEquals(courses, faculty.getMaxCourses(), "Failed test with valid courses - " + courses);
	}
	/**
	 * tests equals() method
	 */
	@Test
	void testEqualsObject() {
		
		User c1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		User c2 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		
		User c3 = new Faculty("testfirstname", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSE);
		User c4 = new Faculty(FIRST_NAME, "testlastname", ID, EMAIL, PASSWORD, MAX_COURSE);
		User c5 = new Faculty(FIRST_NAME, LAST_NAME, "testid", EMAIL, PASSWORD, MAX_COURSE);
		User c6 = new Faculty(FIRST_NAME, LAST_NAME, ID, "test@ncsu.edu", PASSWORD, MAX_COURSE);
		User c7 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "password123", MAX_COURSE);

		
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));

	}

	/**
	 * Tests that toString returns the correct comma-separated value list.
	 */
	@Test
	public void testToString() {
		User c1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 2);
		String s1 = "firstname,lastname,id,firstlast@ncsu.edu,Password123,2";
		assertEquals(s1, c1.toString());
	}

}
