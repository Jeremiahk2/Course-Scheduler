/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import edu.ncsu.csc217.collections.list.SortedList;

import java.io.FileNotFoundException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * This class is responsible for maintaining a catalog of courses.
 * @author Jeremiah Knizley
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
			catalog = CourseRecordIO.readCourseRecords(fileName); //TODO: refactor CourseRecordIO to only use SortedLists
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
}
