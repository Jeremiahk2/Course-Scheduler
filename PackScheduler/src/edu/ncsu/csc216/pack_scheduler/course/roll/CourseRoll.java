/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * CourseRoll is a class that encapsulates information about students in a class.
 * More specifically it maintains a list of students in a class, and keeps track of adding/removing students from that roll.
 * @author Jeremiah Knizley
 * @author Spencer Grattan
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
	/** the course this roll is associated with */
	private Course course;
	/** the size of the wait list */
	private static final int WAITLIST_SIZE = 10;
	/** The wait list that stores students waiting to join a class if spots open */
	private LinkedQueue<Student> waitList;
	
	/**
	 * Constructor for CourseRoll, creates the list of students on the roll, sets the capacity using the parameter
	 * @param enrollmentCapacity the capacity of students that can be added to the roll
	 * @param course the course associated with this roll
	 * @throws IllegalArgumentException if course is null
	 */
	public CourseRoll(Course course, int enrollmentCapacity) {
		roll = new LinkedAbstractList<Student>(enrollmentCapacity);
		setEnrollmentCap(enrollmentCapacity);
		setCourse(course);
		waitList = new LinkedQueue<Student>(WAITLIST_SIZE);
		
	}
	
	/**
	 * This method sets the course that this roll is associated with
	 * @param course the course to be associated with
	 * @throws IllegalArgumentException if course is null
	 */
	private void setCourse(Course course) {
		if(course == null) {
			throw new IllegalArgumentException("Course cannot be null");
		}
		this.course = course;
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
		if (enrollmentCapacity < roll.size()) {
			throw new IllegalArgumentException();
		}
		enrollmentCap = enrollmentCapacity;
		roll.setCapacity(enrollmentCapacity);
		
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
	
	/**
	 * Enrolls the passed student by adding it to the end of the roll
	 * @param s the student to enroll
	 * @throws IllegalArgumentException if the student is null, 
	 * there is no more room in the class, 
	 * the student is already enrolled, 
	 * adding the student to the LinkedAbstractList generates an exception 
	 * and wait list is full
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Student cannot be null");
		}
		if (!(canEnroll(s))) {
			throw new IllegalArgumentException("Student cannot be enrolled");
		}
		try {
			roll.add(s);
			Schedule sched = s.getSchedule();
			sched.addCourseToSchedule(course);
		} catch (Exception e) {
			try {
				waitList.enqueue(s);
			} catch (Exception e1) {
				throw new IllegalArgumentException("Something went wrong");
			}
		}
	}
	
	/**
	 * Drops the passed student from the course by removing it from 
	 * the roll
	 * @param s the student to drop from the course
	 * @throws IllegalArgumentException if the student is null
	 * if removing the student from the LinkedAbstractList generates 
	 * an exception 
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Student cannot be null");
		}
		if(roll.contains(s)) {
			try {
				roll.remove(s);
				if(waitList.size() != 0) {
					roll.add(waitList.size() - 1, waitList.dequeue()); //Adds a student who was waiting to join a course
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("Something went wrong");
			}
		} else {
			int size = waitList.size(); // Size will change as things are added and removed
			for(int i = 0; i < size; i++) {
				Student current = waitList.dequeue();
				if(!s.equals(current)) {
					waitList.enqueue(current); //Only adds back to queue if student isn't being removed
				}
			}
		}
	}
	
	/**
	 * Returns the number of students currently on the wait list for a course
	 * @return int the number of students on the wait list for a course
	 */
	public int getNumberOnWaitlist() {
		return waitList.size();
	}
	
	/**
	 * Helper method to check if a student can be added to the course roll
	 * @param s the student being enrolled
	 * @return true if the student can be added to the course roll
	 * false if there is no more room in the class or if the
	 * student is already enrolled
	 */
	public boolean canEnroll(Student s) {
		if (roll.contains(s)) {
			return false;
		} else {
			return !(roll.size() == this.enrollmentCap && (waitList.contains(s) || waitList.size() == WAITLIST_SIZE));
		}
	}
	
}
