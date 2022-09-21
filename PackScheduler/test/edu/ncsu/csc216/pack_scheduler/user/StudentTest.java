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
 * Tests the Student class and all it's methods
 * @author Sahil Kanchan 
 * @author Jeremiah Knizley
 */
class StudentTest {

	
	/** a valid first name for a student*/
	private static final String FIRST_NAME = "firstname";
	
	/** a valid last name for a student*/
	private static final String LAST_NAME = "lastname";
	
	/** a valid ID for a student*/
	private static final String ID = "id";
	
	/** a valid email for a student*/
	private static final String EMAIL = "firstlast@ncsu.edu";
	
	/** a valid password for a student */
	private static final String PASSWORD = "Password123";
	
	/** the default max credits for a student */
	private static final int MAX_CRED = 18;

	/** a manually inputed student's max credits*/
	private static final int STU_MAX_CRED = 16;
	
	/**
	 * Tests hashCode for student.java
	 */
	@Test
	void testHashCode() {
		
		Student c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c3 = new Student("testfirstname", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);
		Student c4 = new Student(FIRST_NAME, "testlastname", ID, EMAIL, PASSWORD, MAX_CRED);
		Student c5 = new Student(FIRST_NAME, LAST_NAME, "ttest", EMAIL, PASSWORD, MAX_CRED);
		Student c6 = new Student(FIRST_NAME, LAST_NAME, ID, "test@ncsu.edu", PASSWORD, MAX_CRED);
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
	 * Tests Student constructor when max credits are given
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
	 * tests the Student constructor when max credits are not entered
	 */
	@Test
    void testStudentNoCredits() {
		
		Student c = assertDoesNotThrow(() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD), "Should not throw exception");
		
		assertAll("Student", () -> assertEquals(FIRST_NAME, c.getFirstName(), "incorrect first name"), 
							 () -> assertEquals(LAST_NAME, c.getLastName(), "incorrect last name"),
							 () -> assertEquals(ID, c.getId(), "incorrect id"),
							 () -> assertEquals(EMAIL, c.getEmail(), "incorrect email"),
							 () -> assertEquals(PASSWORD, c.getPassword(), "incorrect password"),
							 () -> assertEquals(MAX_CRED, c.getMaxCredits(), "incorrect max credits"));
	}
	
	/**
	 * Tests setEmail with invalid input
	 * @param mail an invalid email
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"test.gmail@com", "test@yahoocom", "testtestoutlookcom"})
	public void testSetEmailInvalid(String mail) {
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> student.setEmail(mail));
		assertEquals("Invalid email", exception.getMessage(), "Incorrect exception thrown with invalid mail - " + mail);
	}
	
	/**
	 * Tests setEmail with valid input.
	 * @param mail an email that is valid
	 */
	@ParameterizedTest
	@ValueSource(strings = {"test@gmail.com", "test2@ncsu.edu", "test3@hotmail.com"})
	public void testSetEmailValid(String mail) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, mail, PASSWORD, MAX_CRED),
				"Should not throw exception");
		assertEquals(mail, student.getEmail(), "Failed test with valid email - " + mail);
	}

	/**
	 * Tests Tests setPassword with invalid input
	 * @param pass an invalid password
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
	 * Tests setPassword with valid input
	 * @param pass a valid password
	 */
	@ParameterizedTest
	@ValueSource(strings = {"password123", "PASS123", "Password!@321"})
	public void testSetPasswordValid(String pass) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, pass, MAX_CRED),
				"Should not throw exception");
		assertEquals(pass, student.getPassword(), "Failed test with valid password - " + pass);
	}

	/**
	 * Tests setMaxCredits with invalid input
	 * @param cred an invalid credit number
	 */
	@ParameterizedTest
	@ValueSource(ints = {0, 2, 1, 19, 20})
	void testSetMaxCreditsInvalid(int cred) {
		
		Student student = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED);

		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> student.setMaxCredits(cred));
		assertEquals("Invalid max credits", exception.getMessage(), "Incorrect exception thrown with invalid credits - " + MAX_CRED);
	}
	
	/**
	 * Tests setMaxCredits with invalid input
	 * @param cred an invalid max credit value
	 */
	@ParameterizedTest
	@ValueSource(ints = {4, 6, 10, 16, 17})
	public void testSetCreditsValid(int cred) {
		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, cred),
				"Should not throw exception");
		assertEquals(cred, student.getMaxCredits(), "Failed test with valid credits - " + cred);
	}

	/**
	 * tests setFirstName with invalid input
	 */
	@Test
	void testSetFirstNameInvalid() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Student("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED));
		
		assertEquals("Invalid first name", e1.getMessage(), "Incorrect exception thrown with invalid first name - " + FIRST_NAME);
	}
	
	/**
	 * Tests setFirstName with valid input
	 * @param firstName a valid first name
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
	 * Tests setLastName with invalid input
	 */
	@Test
	void testSetLastNameInvalid() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Student(FIRST_NAME, "", ID, EMAIL, PASSWORD, MAX_CRED));
		
		assertEquals("Invalid last name", e1.getMessage(), "Incorrect exception thrown with invalid last name - " + LAST_NAME);
	}
	
	/**
	 * tests setLastName with valid input
	 * @param lastName a valid last name
	 */
	@ParameterizedTest
	@ValueSource(strings = {"Byers", "Mayson", "Davis", "Jones", "Garcia"})
	public void testSetLastNameValid(String lastName) {

		Student student = assertDoesNotThrow(
				() -> new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CRED),
				"Should not throw exception");
		assertEquals(LAST_NAME, student.getLastName(), "Failed test with valid student last name - " + LAST_NAME);
	}
	
	/**
	 * Tests setId with invalid input
	 */
	@Test
	public void testSetIdInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, MAX_CRED));
		assertEquals("Invalid id", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_CRED));
		assertEquals("Invalid id", e2.getMessage());
	}
	/**
	 * tests equals() method
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
	 * Tests that toString returns the correct comma-separated value list.
	 */
	@Test
	public void testToString() {
		Student c1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, STU_MAX_CRED);
		String s1 = "firstname,lastname,id,firstlast@ncsu.edu,Password123,16";
		assertEquals(s1, c1.toString());

		Student c2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String s2 = "firstname,lastname,id,firstlast@ncsu.edu,Password123,18";
		assertEquals(s2, c2.toString());
	}

}