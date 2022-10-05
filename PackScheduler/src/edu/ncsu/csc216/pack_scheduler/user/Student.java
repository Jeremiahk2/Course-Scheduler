package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class for creating Student objects, which hold information about the student
 * @author Geigh Neill
 * @author Jeremiah Knizley
 * @author Sahil Kanchan
 */
public class Student extends User implements Comparable<Student> {
	
	/** The student's maximum credits */
	private int maxCredits;
	/** The maximum credits a student can have */
	public static final int MAX_CREDITS = 18;
	/** The minimum credits a student can have */
	public static final int MIN_CREDITS = 3;

	/**
	 * Constructs Student object and sets it's instance fields
	 * @param firstName The student's first name
	 * @param lastName The student's last name
	 * @param id THe student's id number
	 * @param email The student's email
	 * @param hashPW  The student's hashed password
	 * @param maxCredits The maximum credits the student can have
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, 
			int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		
	}
	
	/**
	 * Constructs Student object with max credits at 18
	 * @param firstName The student's first name
	 * @param lastName The student's last name
	 * @param id THe student's id number
	 * @param email The student's email
	 * @param hashPW  The student's hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(MAX_CREDITS);
	}
	
	/**
	 * Returns the max credits the student can have
	 * @return The max credits of the student
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits the student can have
	 * @param maxCredits the max credits of the student
	 * @throws IllegalArgumentException if maxCredits is less than the minimum credits or greater than the maximum credits
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		
		this.maxCredits = maxCredits;
	}

	
	/**
	 * Formats a string for the Student class and its fields
	 * @return Returns string of Student class and fields
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString = String.format("%s,%s,%s,%s,%s,%d", this.getFirstName(), this.getLastName(), this.getId(), this.getEmail(), this.getPassword(),
				maxCredits);
		
		return returnString;
	}

	/**
	 * Compares parameter Student object s to caller and returns -1, 0, or 1 depending on whether
	 * the caller is less than s, equal to s, or greater than s, respectively. Order is determined 
	 * first by alphabetical order of the Student's last names, then by first name, and lastly by
	 * their unity id's. Alphabetical ordering is implemented through use of the imported SortedList
	 * java library.
	 * @param s Student object being compared to the caller.
	 * @return Integer value determining the ordering of the two objects.
	 * @throws NullPointerException if the parameter object s is null.
	 * @throws ClassCastException if the parameter is not a student object.
	 */
	@Override
	public int compareTo(Student s) {
		if (s == null) {
			throw new NullPointerException("Object is null.");
		}
		if (getClass() != s.getClass()) {
			throw new ClassCastException("Object is not student.");
		}
		
		SortedList<String> list = new SortedList<String>();
		
		if (this.equals(s) ) {
			return 0;
		}
		else if (!getLastName().equals(s.getLastName())) {
			list.add(getLastName());
			list.add(s.getLastName());
			if (list.get(0).equals(getLastName())) {
				return -1;
			}
			else if (list.get(1).equals(getLastName())){
				return 1;
			}
		}
		else if (!getFirstName().equals(s.getFirstName())) {
			list.add(getFirstName());
			list.add(s.getFirstName());
			if (list.get(0).equals(getFirstName())) {
				return -1;
			}
			else if (list.get(1).equals(getFirstName())){
				return 1;
			}
		}
		else if (!getId().equals(s.getId())) {
			list.add(getId());
			list.add(s.getId());
			if (list.get(0).equals(getId())) {
				return -1;
			}
			else if (list.get(1).equals(getId())) {
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * default override for hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(maxCredits);
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
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}

}
