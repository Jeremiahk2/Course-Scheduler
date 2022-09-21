/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


/**
 * @author Sahil Kanchan
 *
 */
class StudentTest {

	
	/** */
	private static final String FIRST_NAME = "firstname";
	
	/** */
	private static final String LAST_NAME = "lastname";
	
	/** */
	private static final String ID = "id";
	
	/** */
	private static final String EMAIL = "firstlast@ncsu.edu";
	
	/** */
	private static final String PASSWORD = "Password123";
	
	/** */
	private static final int MAX_CRED = 18;

	/** */
	private static final int STU_MAX_CRED = 16;
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#hashCode()}.
	 */
	@Test
	void testHashCode() {
		
		Student c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c3 = new Student("testfirstname", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c4 = new Student(FIRST_NAME, "testlastname", ID, EMAIL, PASSWORD, MAX_CRED);
		Student c5 = new Student(FIRST_NAME, LAST_NAME, "ttest", EMAIL, PASSWORD, MAX_CRED);
		Student c6 = new Student(FIRST_NAME, LAST_NAME, ID, "firstlast@ncsu.edu", PASSWORD, MAX_CRED);
		Student c7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Testpassword123", MAX_CRED);
		Student c8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		
		assertEquals(c1.hashCode(), c2.hashCode());
		
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
		assertNotEquals(c1.hashCode(), c5.hashCode());
		assertNotEquals(c1.hashCode(), c6.hashCode());
		assertNotEquals(c1.hashCode(), c7.hashCode());
		assertNotEquals(c1.hashCode(), c8.hashCode());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#Student(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testStudentCredits() {
		
		Student c = assertDoesNotThrow(() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED), "Should not throw exception");
		
		assertAll("Student", () -> assertEquals(FIRST_NAME, c.getFirstName(), "incorrect first name"), 
							 () -> assertEquals(LAST_NAME, c.getLastName(), "incorrect last name"),
							 () -> assertEquals(ID, c.getId(), "incorrect id"),
							 () -> assertEquals(EMAIL, c.getEmail(), "incorrect email"),
							 () -> assertEquals(PASSWORD, c.getPassword(), "incorrect password"),
							 () -> assertEquals(MAX_CRED, c.getMaxCredits(), "incorrect max credits"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#Student(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
    void testStudentNoCredits() {
		
		Student c = assertDoesNotThrow(() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD), "Should not throw exception");
		
		assertAll("Student", () -> assertEquals(FIRST_NAME, c.getFirstName(), "incorrect first name"), 
							 () -> assertEquals(LAST_NAME, c.getLastName(), "incorrect last name"),
							 () -> assertEquals(ID, c.getId(), "incorrect id"),
							 () -> assertEquals(EMAIL, c.getEmail(), "incorrect email"),
							 () -> assertEquals(PASSWORD, c.getPassword(), "incorrect password"),
							 () -> assertEquals(STU_MAX_CRED, c.getMaxCredits(), "incorrect max credits"));
	}
	
	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"test@gmail..com", "test@yahoocom", "testtestoutlookcom"})
	public void testSetEmailInvalid(String mail) {
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> student.setEmail(mail));
		assertEquals("Invalid email", exception.getMessage(), "Incorrect exception thrown with invalid mail - " + mail);
	}
	
	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"test@gmail.com", "test2@ncsu.edu", "test3@hotmail.com"})
	public void testSetEmailValid(String mail) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, mail, PASSWORD, MAX_CRED),
				"Should not throw exception");
		assertEquals(mail, student.getEmail(), "Failed test with valid email - " + mail);
	}

	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	void testSetPasswordInvalid(String pass) {
		
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> student.setPassword(pass));
		assertEquals("Invalid password", exception.getMessage(), "Incorrect exception thrown with invalid password - " + pass);
	}
	
	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"password123", "PASS123", "Password!@321"})
	public void testSetPasswordValid(String pass) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, pass, MAX_CRED),
				"Should not throw exception");
		assertEquals(pass, student.getPassword(), "Failed test with valid password - " + pass);
	}

	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(ints = {0, 2, 1, 19, 20})
	void testSetMaxCreditsInvalid(int cred) {
		
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> student.setMaxCredits(cred));
		assertEquals("Invalid credits", exception.getMessage(), "Incorrect exception thrown with invalid credits - " + cred);
	}
	
	/**
	 * Tests setSection with invalid input.
	 * @param invalid invalid input for the test
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(ints = {4, 6, 10, 16, 17})
	public void testSetCreditsValid(int cred) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, cred),
				"Should not throw exception");
		assertEquals(cred, student.getMaxCredits(), "Failed test with valid credits - " + cred);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#setFirstName(java.lang.String)}.
	 */
	@Test
	void testSetFirstNameInvalid(String firstName) {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Student(firstName, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED));
		
		assertEquals("Invalid first name", e1.getMessage(), "Incorrect exception thrown with invalid first name - " + firstName);
	}
	
	/**
	 * 
	 * @param 
	 */
	@ParameterizedTest
	@ValueSource(strings = {"Max", "John", "Jhonson", "Paul", "Bart"})
	public void testSetFirstNameValid(String firstName) {

		Student student = assertDoesNotThrow(
				() -> new Student(firstName, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED),
				"Should not throw exception");
		assertEquals(firstName, student.getFirstName(), "Failed test with valid student first name - " + firstName);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#setLastName(java.lang.String)}.
	 */
	@Test
	void testSetLastNameInvalid(String lastName) {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Student(FIRST_NAME, lastName, ID, EMAIL, PASSWORD, MAX_CRED));
		
		assertEquals("Invalid last name", e1.getMessage(), "Incorrect exception thrown with invalid last name - " + lastName);
	}
	
	/**
	 * 
	 * @param lastName
	 */
	@ParameterizedTest
	@ValueSource(strings = {"Byers", "Mayson", "Davis", "Jones", "Garcia"})
	public void testSetLastNameValid(String lastName) {

		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, lastName, ID, EMAIL, PASSWORD, MAX_CRED),
				"Should not throw exception");
		assertEquals(lastName, student.getLastName(), "Failed test with valid student last name - " + lastName);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.Student#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		
		Student c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		
		Student c3 = new Student("testfirstname", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c4 = new Student(FIRST_NAME, "testlastname", ID, EMAIL, PASSWORD, MAX_CRED);
		Student c5 = new Student(FIRST_NAME, LAST_NAME, "testid", EMAIL, PASSWORD, MAX_CRED);
		Student c6 = new Student(FIRST_NAME, LAST_NAME, ID, "test@ncsu.edu", PASSWORD, MAX_CRED);
		Student c7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "password123", MAX_CRED);
		Student c8 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 12);
		
		assertTrue(c1.equals(c2));
		assertTrue(c2.equals(c1));
		
		assertFalse(c1.equals(c3));
		assertFalse(c1.equals(c4));
		assertFalse(c1.equals(c5));
		assertFalse(c1.equals(c6));
		assertFalse(c1.equals(c7));
		assertFalse(c1.equals(c8));
	}

	/**
	 * Tests that toString returns the correct comma-separated value.
	 */
	@Test
	public void testToString() {
		Student c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		String s1 = "firstname,lastname, testid, test@ncsu.edu, Password123, 18";
		assertEquals(s1, c1.toString());

		Student c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String s2 = "firstname,lastname, testid, test@ncsu.edu, Password123";
		assertEquals(s2, c2.toString());
	}

}
