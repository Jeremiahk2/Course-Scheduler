package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Tests the Student object.
 * @author SarahHeckman
 * @author Spencer Grattan
 */
public class StudentTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}
	
	/**
	 * Tests the canAdd method
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, 6);
		Course c = new Course("CSC123", "Learning to type", "001", 3, "pbnutter", 10, "MWF", 1300, 1400);
		Course c1 = new Course ("CSC234", "Intro to houses", "001", 3, "scooby", 10, "MTH", 1300, 1500);
		Course c2 = new Course ("CSC334", "Intro to houses II", "001", 4, "scooby", 10, "TH", 700, 800);
		
		//Tests a valid case
		assertTrue(s1.canAdd(c));
		s1.getSchedule().addCourseToSchedule(c);
		
		//tests null 
		assertFalse(s1.canAdd(null));
		//tests course already in schedule
		assertFalse(s1.canAdd(c));
		//tests conflicting course
		assertFalse(s1.canAdd(c1));
		//tests exceeds max allowed credits
		assertFalse(s1.canAdd(c2));
	}

}
