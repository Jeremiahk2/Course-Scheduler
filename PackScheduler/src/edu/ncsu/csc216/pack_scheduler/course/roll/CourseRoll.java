/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * CourseRoll is a class that encapsulates information about students in a class.
 * More specifically it maintains a list of students in a class, and keeps track of adding/removing students from that roll.
 * @author Jeremiah Knizley
 *
 */
public class CourseRoll {
	
	/** the list of students in the course's roll */
	private LinkedAbstractList<Student> roll;
	/** the roll's enrollment capacity */
	private int enrollmentCap;
	/** the smallest class size */
	private static final int MIN_ENROLLMENT = 10;
	/** the largest class size */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * Constructor for CourseRoll, creates the list of students on the roll, sets the capacity using the parameter
	 * @param enrollmentCapacity the capacity of students that can be added to the roll
	 */
	public CourseRoll(int enrollmentCapacity) {
		roll = new LinkedAbstractList<Student>(enrollmentCapacity);
		setEnrollmentCap(enrollmentCapacity);
		
	}
	
	/**
	 * sets the enrollment capacity.
	 * @param enrollmentCapacity the capacity of students that can be added to the CourseRoll
	 * @throws IllegalArgumentException if enrollmentCapacity is less than MIN_ENROLLMENT or greater than MAX_ENROLLMENT
	 */
	public void setEnrollmentCap(int enrollmentCapacity) {
		if (enrollmentCapacity < MIN_ENROLLMENT || enrollmentCapacity > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		enrollmentCap = enrollmentCapacity;
		
	}

	/**
	 * returns the number of open seats.
	 * @return int the number of open seats (enrollmentCap minus the current size of the roll).
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * returns the enrollmentCap
	 * @return int the enrollmentCap
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
}
