/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * This class is responsible for maintaining a catalog of courses.
 * @author Jeremiah Knizley
 * @author Spencer Grattan
 *
 */
public class CourseCatalog {
	/** the list of courses in the catalog */
	private SortedList<Course> catalog;
	
	/** Constructs the catalog as an empty SortedList of courses */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/** creates a new course catalog as an empty SortedList of courses */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/** 
	 * Loads courses into the catalog from a CSV file
	 * @param fileName the name of the file where the CSV list of courses are
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * adds a course to the course catalog
	 * @param name the name of the course
	 * @param title the title of the course
	 * @param section the section of the course
	 * @param credits the number of credits the course gives
	 * @param instructorId the ID of the instructor teaching the course
	 * @param enrollmentCap the maximum number of students that can be enrolled in the course
	 * @param meetingDays the days the course meets for class (if applicable)
	 * @param startTime start time of class meetings in military time
	 * @param endTime the end time of class meetings in military time
	 * @return boolean true if successfully added, false if not.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) throws IllegalArgumentException {
		Course course = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for (int i = 0; i < catalog.size(); i++) {
			Course catalogCourse = catalog.get(i);
			String courseName = catalogCourse.getName();
			String courseSection = catalogCourse.getSection();
			if (courseName.equals(name) && courseSection.equals(section)) {
				return false;
			}
		}
		catalog.add(course);
		return true;
	}
	/**
	 * Removes a course from the catalog
	 * @param name the name of the course to be removed
	 * @param section the section of the course to be removed
	 * @return boolean true if successfully removed, false if there was nothing to remove
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		int index = -1;
		for (int i = 0; i < catalog.size(); i++) {
			Course catalogCourse = catalog.get(i);
			String courseName = catalogCourse.getName();
			String courseSection = catalogCourse.getSection();
			if (courseName.equals(name) && courseSection.equals(section)) {
				index = i;
			}
		}
		if (index != -1) {
			catalog.remove(index);
			return true;
		}
		return false;
	}
	/**
	 * Returns the specified course from the catalog
	 * @param name the name of the course to be returned
	 * @param section the section of the course to be returned
	 * @return the course requested, or null if the course is not in the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		int index = -1;
		for (int i = 0; i < catalog.size(); i++) {
			Course catalogCourse = catalog.get(i);
			String courseName = catalogCourse.getName();
			String courseSection = catalogCourse.getSection();
			if (courseName.equals(name) && courseSection.equals(section)) {
				index = i;
			}
		}
		if (index != -1) {
			return catalog.get(index);
		}
		return null;
	}
	/**
	 * Returns a 2D array containing information for all courses in the catalog.
	 * Information in the array is (for each course): name, section, title, meeting info.
	 * @return a 2d array containing information for all courses in the catalog
	 */
	public String[][] getCourseCatalog() {
		if (catalog.isEmpty()) {
			return new String[0][0];
		}
		String[][] courseCatalog = new String[catalog.size()][4]; 
		for (int i = 0; i < catalog.size(); i++) {
			Course currentCourse = catalog.get(i);
			courseCatalog[i] = currentCourse.getShortDisplayArray();
		}
		
		return courseCatalog;
	}
	/**
	 * Writes the full catalog of courses in toString format to a text file with the given file name
	 * @param fileName the name of the file to write to
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}
	
}
