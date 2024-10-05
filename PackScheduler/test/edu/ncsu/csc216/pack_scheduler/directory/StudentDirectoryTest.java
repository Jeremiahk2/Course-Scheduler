package edu.ncsu.csc216.pack_scheduler.directory;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * @author Geigh Neill
 * @author Spencer Grattan
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Invalid course records file */
	private final String nonExistentTestFile = "";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test first name */
	private static final String FIRST_NAME_1 = "Larry";
	/** Test last name */
	private static final String LAST_NAME_1 = "Burt";
	/** Test id */
	private static final String ID_1 = "lburt";
	/** Test email */
	private static final String EMAIL_1 = "lburt@ncsu.edu";
	/** Test password */
	private static final String PASSWORD_1 = "wp";
	/** Wrong test password */
	private static final String WRONG_PW = "PW";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	/** Test low max credits */
	private static final int MAX_CREDITS_LOW = 2;
	/** Test high max credits */
	private static final int MAX_CREDITS_HIGH = 19;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		//Test throws IAE statement
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd.loadStudentsFromFile(nonExistentTestFile));
		assertEquals("Unable to read file ", exception.getMessage(), "Incorrect exception thrown with invalid file " + nonExistentTestFile);
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		//Test invalid Student because of duplicate ID
		assertFalse(sd.addStudent(FIRST_NAME_1, LAST_NAME_1, ID, EMAIL_1, PASSWORD_1, PASSWORD_1, MAX_CREDITS_LOW));
	}
	
	/**
	 * Tests StudentDirectory.addStudent() for Students with high/low max credits.
	 */
	@Test
	public void testAddStudentHighLowCredits() {
		StudentDirectory sd = new StudentDirectory();
		
		//Tests if students with high/low credits are added with separate constructor
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS_LOW);
		sd.addStudent(FIRST_NAME_1, LAST_NAME_1, ID_1, EMAIL_1, PASSWORD_1, PASSWORD_1, MAX_CREDITS_HIGH);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(2, studentDirectory.length);
	}
	
	/**
	 * Tests StudentDirect.addStudent() Exception messages.
	 */
	@Test
	public void testAddStudentExceptions() {
		StudentDirectory sd = new StudentDirectory();
		
		Exception nullPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", nullPwException.getMessage(), "Incorrect exception thrown with null password");
		Exception nullPwException1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS));
		assertEquals("Invalid password", nullPwException1.getMessage(), "Incorrect exception thrown with null password");
		Exception emptyPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS));
		assertEquals("Invalid password", emptyPwException.getMessage(), "Incorrect exception thrown with empty password");
		Exception emptyPwException1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS));
		assertEquals("Invalid password", emptyPwException1.getMessage(), "Incorrect exception thrown with empty password");
		Exception diffPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, WRONG_PW, MAX_CREDITS));
		assertEquals("Passwords do not match", diffPwException.getMessage(), "Incorrect exception thrown with different passwords");
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(10, studentDirectory.length);
		//System.out.println(studentDirectory[9][0]);
		assertEquals("Althea", studentDirectory[5][0]);
		assertEquals("Hicks", studentDirectory[5][1]);
		assertEquals("ahicks", studentDirectory[5][2]);
		assertTrue(sd.removeStudent("efrost"));
		studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Zahir", studentDirectory[5][0]);
		assertEquals("King", studentDirectory[5][1]);
		assertEquals("zking", studentDirectory[5][2]);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		Exception saveException = assertThrows(IllegalArgumentException.class,
				() -> sd.saveStudentDirectory(nonExistentTestFile));
		assertEquals("Unable to write to file " + nonExistentTestFile, saveException.getMessage(), 
				"Incorrect exception thrown for invalid save file");
	}
	
	/**
	 * Tests getStudentById()
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();
		
		Student student1 = new Student("Tim", "Thalamander", "ttthal", "tinytim@yahoo.com", "pw", 13);
		//Add some students
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		sd.addStudent("Soap", "Sussy", "smsussy", "bigsoap@ncsu.edu", "pw", "pw", 14);
		sd.addStudent("Tim", "Thalamander", "ttthal", "tinytim@yahoo.com", "pw", "pw", 13);
		
		//tests for a valid case 
		assertEquals(student1.getFirstName(), sd.getStudentById("ttthal").getFirstName());
		assertEquals(student1.getLastName(), sd.getStudentById("ttthal").getLastName());
		assertEquals(student1.getId(), sd.getStudentById("ttthal").getId());
		assertEquals(student1.getEmail(), sd.getStudentById("ttthal").getEmail());
		assertEquals(student1.getMaxCredits(), sd.getStudentById("ttthal").getMaxCredits());
		
		
		//tests for invalid case
		assertEquals(null, sd.getStudentById("finger"));
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	

}
