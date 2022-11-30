/**
 * 
 */
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

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * This class tests the faculty directory class for functionality and correct exception throwing
 * @author albressl
 */
class FacultyDirectoryTest {

	/** Valid Faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid Faculty records file */
	private final String nonExistentTestFile = "";
	/** Test first name */
	private static final String FIRST_NAME = "Fac";
	/** Test last name */
	private static final String LAST_NAME = "Ulty";
	/** Test id */
	private static final String ID = "fulty";
	/** Test email */
	private static final String EMAIL = "fulty@ncsu.edu";
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
	private static final int MAX_COURSES = Faculty.MAX_COURSES;
	/** Test low max credits */
	private static final int MAX_COURSES_LOW = 0;
	/** Test high max credits */
	private static final int MAX_COURSES_HIGH = 6;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are Faculty in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		//Test throws IAE statement
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd.loadFacultyFromFile(nonExistentTestFile));
		assertEquals("Unable to read file ", exception.getMessage(), "Incorrect exception thrown with invalid file " + nonExistentTestFile);
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid Faculty because of duplicate ID
		assertFalse(sd.addFaculty(FIRST_NAME_1, LAST_NAME_1, ID, EMAIL_1, PASSWORD_1, PASSWORD_1, MAX_COURSES_LOW));
	}
	
	/**
	 * Tests FacultyDirectory.addFaculty() for Faculty with high/low max courses.
	 */
	@Test
	public void testAddFacultyHighLowCredits() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Tests if Faculty with high/low credits are added with separate constructor
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES_LOW);
		sd.addFaculty(FIRST_NAME_1, LAST_NAME_1, ID_1, EMAIL_1, PASSWORD_1, PASSWORD_1, MAX_COURSES_HIGH);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(2, facultyDirectory.length);
	}
	
	/**
	 * Tests FacultyDirect.addFaculty() Exception messages.
	 */
	@Test
	public void testAddFacultyExceptions() {
		FacultyDirectory sd = new FacultyDirectory();
		
		Exception nullPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", nullPwException.getMessage(), "Incorrect exception thrown with null password");
		Exception nullPwException1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES));
		assertEquals("Invalid password", nullPwException1.getMessage(), "Incorrect exception thrown with null password");
		Exception emptyPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES));
		assertEquals("Invalid password", emptyPwException.getMessage(), "Incorrect exception thrown with empty password");
		Exception emptyPwException1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES));
		assertEquals("Invalid password", emptyPwException1.getMessage(), "Incorrect exception thrown with empty password");
		Exception diffPwException = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, WRONG_PW, MAX_COURSES));
		assertEquals("Passwords do not match", diffPwException.getMessage(), "Incorrect exception thrown with different passwords");
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add Faculty and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(8, facultyDirectory.length);
		assertEquals("Elton", facultyDirectory[5][0]);
		assertEquals("Briggs", facultyDirectory[5][1]);
		assertEquals("ebriggs", facultyDirectory[5][2]);
		assertTrue(sd.removeFaculty("awitt"));
		facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Norman", facultyDirectory[5][0]);
		assertEquals("Brady", facultyDirectory[5][1]);
		assertEquals("nbrady", facultyDirectory[5][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Add a Faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_Faculty_records.txt");
		checkFiles("test-files/expected_Faculty_records.txt", "test-files/actual_Faculty_records.txt");
		Exception saveException = assertThrows(IllegalArgumentException.class,
				() -> sd.saveFacultyDirectory(nonExistentTestFile));
		assertEquals("Unable to write to file " + nonExistentTestFile, saveException.getMessage(), 
				"Incorrect exception thrown for invalid save file");
	}
	
	/**
	 * Tests getFacultyById()
	 */
	@Test
	public void testGetFacultyById() {
		FacultyDirectory sd = new FacultyDirectory();
		
		Faculty faculty1 = new Faculty("Tim", "Thalamander", "ttthal", "tinytim@yahoo.com", "pw", 3);
		//Add some Faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Tim", "Thalamander", "ttthal", "tinytim@yahoo.com", "pw", "pw", 3);
		
		//tests for a valid case 
		assertEquals(faculty1.getFirstName(), sd.getFacultyById("ttthal").getFirstName());
		assertEquals(faculty1.getLastName(), sd.getFacultyById("ttthal").getLastName());
		assertEquals(faculty1.getId(), sd.getFacultyById("ttthal").getId());
		assertEquals(faculty1.getEmail(), sd.getFacultyById("ttthal").getEmail());
		assertEquals(faculty1.getMaxCourses(), sd.getFacultyById("ttthal").getMaxCourses());
		
		
		//tests for invalid case
		assertEquals(null, sd.getFacultyById("finger"));
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
