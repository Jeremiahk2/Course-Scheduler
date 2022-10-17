package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Test the RegistrationManager class
 * @author Spencer Grattan
 *
 */
public class RegistrationManagerTest {
	
	/** Instance of registrationManager to test */
	private RegistrationManager manager;
	/** The name of the file where the login information is stored (STORED LOCALLY). */
	private static final String PROP_FILE = "registrar.properties";
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests the getCourseCatalog method
	 */
	@Test
	public void testGetCourseCatalog() {
		assertTrue(manager.getCourseCatalog() instanceof CourseCatalog);
	}

	/**
	 * Tests the getStudentDirectory method
	 */
	@Test
	public void testGetStudentDirectory() {
		assertTrue(manager.getStudentDirectory() instanceof StudentDirectory);
	}

	/**
	 * tests the login method
	 */
	@Test
	public void testLogin() {
		//test for case where a student logs in 
		//populate manager's Student Directory with a student
		assertDoesNotThrow(
				() -> manager.getStudentDirectory().addStudent("Spencer", "Grattan", "goomba", "dsgratta@ncsu.edu", "12345", "12345", 16));

		assertTrue(manager.login("goomba", "12345"));
		assertEquals("goomba", manager.getCurrentUser().getId());
		assertEquals("Spencer", manager.getCurrentUser().getFirstName());
		assertEquals("Grattan", manager.getCurrentUser().getLastName());
		assertEquals("dsgratta@ncsu.edu", manager.getCurrentUser().getEmail());
		

		//test for a case where the registrar logs in 
		Properties prop = new Properties();
		InputStream input = assertDoesNotThrow(
				() -> new FileInputStream(PROP_FILE));
		assertDoesNotThrow(
				() -> prop.load(input));
		String pw = assertDoesNotThrow(
				() -> prop.getProperty("pw"));
		String id = assertDoesNotThrow(
				() -> prop.getProperty("id"));
		
		
		assertTrue(manager.login(id, pw));
		
		//test for a case where the student id is invalid
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> manager.login("bad_id", "bad_password"));
		assertEquals("User doesn't exist.", e1.getMessage());
		
		//test for a case where the student id is valid but the password is invalid
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> manager.login("goomba", "bad_password"));
		assertEquals("User doesn't exist.", e2.getMessage());
	}

	/**
	 * tests the logout method
	 * login method & getCurrentUser method must be working in order to test logout method
	 */
	@Test
	public void testLogout() {
		//first logs in a student 
		assertDoesNotThrow(
				() -> manager.getStudentDirectory().addStudent("Spencer", "Grattan", "goomba", "dsgratta@ncsu.edu", "12345", "12345", 16));

		assertTrue(manager.login("goomba", "12345"));
		
		//then tests they are logged out 
		assertDoesNotThrow(
				() -> manager.logout());
		assertEquals(null, manager.getCurrentUser());
	}

	/**
	 * Tests the getCurrentUser method
	 */
	@Test
	public void testGetCurrentUser() {
		//tests when no one is logged in
		assertEquals(null, manager.getCurrentUser());
	}

}