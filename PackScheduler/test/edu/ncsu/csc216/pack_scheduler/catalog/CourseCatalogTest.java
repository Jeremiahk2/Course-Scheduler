package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests CourseCatalog implementation into PackScheduler
 * @author Jeremiah Knizley
 *
 */
public class CourseCatalogTest {
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";
	
	/** Course name */
	private static final String NAME = "CSC 216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	
	/** Event title */
	private static final String EVENT_TITLE = "Exercise";
	/** Event meeting days */
	private static final String EVENT_MEETING_DAYS = "MTWHF";
	/** Event start time */
	private static final int EVENT_START_TIME = 800;
	/** Event end time */
	private static final int EVENT_END_TIME = 900;
	/** Event details */
	private static final String EVENT_DETAILS = "Cardio Time!";
	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	/**
	 * Test CourseCatalog.saveCourseCatalog
	 */
	@Test
	public void testSaveCourseCatalog() {
		//Test that empty catalog saves successfully
		CourseCatalog cc = new CourseCatalog();
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		//Add courses and test that exports correctly
		cc.addCourseToCatalog("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		cc.addCourseToCatalog("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		cc.addCourseToCatalog("CSC 216", "Software Development Fundamentals", "601", 3, "jctetter", "A", 0, 0);
		assertEquals(3, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
	}
	
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			String test = "-1";
			while (actScanner.hasNextLine()) {
				test = expScanner.nextLine();
				assertEquals(test, actScanner.nextLine());
				
			}
			if (expScanner.hasNextLine()) {
				fail();
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	/**
	 * Tests CourseCatalog.addCourseToCatalog, newCourseCatalog, and removeCourseFromCatalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		catalog.addCourseToCatalog("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		catalog.addCourseToCatalog("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		catalog.addCourseToCatalog("CSC 216", "Software Development Fundamentals", "601", 3, "jctetter", "A", 0, 0);
		assertEquals(3, catalog.getCourseCatalog().length);
		
		assertTrue(catalog.removeCourseFromCatalog("CSC 116", "003"));
		assertEquals(2, catalog.getCourseCatalog().length);
		assertFalse(catalog.removeCourseFromCatalog("CSC 116", "003"));
		assertEquals(2, catalog.getCourseCatalog().length);
		
		catalog.newCourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests loadCoursesFromFile for CourseCatalog
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog catalog = new CourseCatalog();
		assertEquals(0, catalog.getCourseCatalog().length);
		catalog.loadCoursesFromFile("test-files/expected_course_records.txt");
		assertEquals(3, catalog.getCourseCatalog().length);
		Course course1 = new Course("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		Course course2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Course course3 = new Course("CSC 216", "Software Development Fundamentals", "601", 3, "jctetter", "A", 0, 0);
		assertTrue(course1.equals(catalog.getCourseFromCatalog("CSC 116", "003")));
		assertTrue(course2.equals(catalog.getCourseFromCatalog("CSC 216", "001")));
		assertTrue(course3.equals(catalog.getCourseFromCatalog("CSC 216", "601")));
		
		
	}
}
