/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Creates Faculty objects, a type of User.
 * Adds private field maxCourses and MAX_COURSES and MIN_COURSES constants for 
 * Courses implementation
 * @author Jeremiah Knizley
 *
 */
public class Faculty extends User {

	/** The faculty's maximum courses */
	private int maxCourses;
	/** The maximum courses a faculty can have */
	public static final int MAX_COURSES = 3;
	/** The minimum courses a faculty can have */
	public static final int MIN_COURSES = 1;
	
	/**
	 * Constructs Faculty object and sets it's instance fields
	 * @param firstName The faculty's first name
	 * @param lastName The faculty's last name
	 * @param id The faculty's id number
	 * @param email The faculty's email
	 * @param hashPW  The faculty's hashed password
	 * @param maxCourses The maximum courses the faculty member can have
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, 
			int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
	}
	
	/**
	 * Returns the max courses the faculty can have
	 * @return The max courses of the faculty
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the max courses the faculty can have
	 * @param maxCourses the max courses of the faculty
	 * @throws IllegalArgumentException if maxCourses is less than the MIN_COURSES or greater than the MAX_COURSES
	 */
	public void setMaxCourses(int maxCourses) {
		
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		
		this.maxCourses = maxCourses;
	}

	
	/**
	 * Formats a string for the Faculty class and its fields in the format firstName,lastName,ID,Email,Password,maxCourses
	 * @return Returns string of Faculty class and fields
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString = String.format("%s,%s,%s,%s,%s,%d", this.getFirstName(), this.getLastName(), this.getId(), this.getEmail(), this.getPassword(),
				maxCourses);
		
		return returnString;
	}
	
	/**
	 * default override for hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCourses);
		return result;
	}
	
	/**
	 * default override for equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}
}
