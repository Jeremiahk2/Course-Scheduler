/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Class for a Schedule object. 
 * @author Jeremiah Knizley
 * @author Spencer Grattan
 */
public class Schedule {
	
	/** ArrayList to contain all of the courses in the schedule */
	private ArrayList<Course> schedule;
	/** The title of the schedule */
	private String title;
	
	/**
	 * Constructor for Schedule
	 */
	public Schedule() {
		this.setTitle("My Schedule");
		this.schedule = new ArrayList<Course>();
	}
	
	/**
	 * Adds a course to the end of the schedule
	 * @param course the course being added to the schedule
	 * @return true if the course was able to be added 
	 * @throws IllegalArgumentException if there is a duplicate course
	 * or if there is a course conflict with an existing course
	 * @throws NullPointerException if the course added is null
	 */
	public boolean addCourseToSchedule(Course course) {
		
	}
	
	/**
	 * Removes a course from the schedule 
	 * @param course the course to be removed
	 * @return true if the course was successfully removed
	 * false if there was not a course to remove
	 */
	public boolean removeCourseFromSchedule(Course course) {
		
	}
	
	/**
	 * Creates a new schedule ArrayList and resets the title to default
	 */
	public void resetSchedule() {
		this.setTitle("My Schedule");
		this.schedule = new ArrayList<Course>();
	}
	
	/**
	 * Gets a 2d array of course information where each row is a course
	 * and each column is name, section, title, meetingString
	 * @return all course information
	 */
	public String[][] getScheduledCourses() {
		
	}
	
	/**
	 * Sets the title of the schedule
	 * @param title the title to be set
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Gets the title of the schedule
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}
}
