package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class for creating Student objects, which hold information about the student
 * @author Geigh Neill
 * @author Jeremiah Knizley
 * @author Sahil Kanchan
 */
public class Student implements Comparable<Student> {
	
	/** The first name of a student */
	private String firstName;
	/** The last name of a student */
	private String lastName;
	/** The student's ID number as a string */
	private String id;
	/** The student's email address */
	private String email;
	/** The student's password */
	private String hashPw;
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setMaxCredits(maxCredits);
		setPassword(hashPW);
		setEmail(email);
		
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
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setMaxCredits(MAX_CREDITS);
		setPassword(hashPW);
		setEmail(email);
	}
	
	/**
	 * Returns first name of the student
	 * @return The student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns last name of the student
	 * @return The student's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns id of the student
	 * @return The student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the student's email
	 * @return The student's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email. An email is invalid if it is null or empty.
	 * It is also invalid if the email does not contain an @ or a period. 
	 * It is also invalid if the period comes before the @ in the string
	 * @param email The student's email address
	 * @throws IllegalArgumentException if the email is invalid
	 */
	public void setEmail(String email) {
		
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		boolean hasAt = false;
		boolean hasPeriod = false;
		int atIndex = 0;
		int periodIndex = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				hasAt = true;
				atIndex = i;
			}
			else if (email.charAt(i) == '.') {
				hasPeriod = true;
				periodIndex = i;
			}
		}
		
		if (!hasAt || !hasPeriod) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (atIndex - periodIndex > 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		
		this.email = email;
	}

	/**
	 * Returns the student's password
	 * @return The student's hashed password
	 */
	public String getPassword() {
		return hashPw;
	}

	/**
	 * Sets the student's password
	 * @param hashPw The student's hashed password
	 * @throws IllegalArgumentException if the password is null or empty
	 */
	public void setPassword(String hashPw) {
		
		if (hashPw == null || "".equals(hashPw)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		this.hashPw = hashPw;
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
	 * Sets the student's first name
	 * @param firstName Student's first name
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName Student's last name
	 * @throws IllegalArgumentException if the last name is null or empty
	 */
	public void setLastName(String lastName) {
		
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		
		this.lastName = lastName;
	}

	/**
	 * Sets the student's id number
	 * @param id Student's id
	 * @throws IllegalArgumentException if the id is null or empty
	 */
	private void setId(String id) {
		
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		this.id = id;
	}

	/**
	 * Generates hashCode for Student Object
	 * @return Returns hash code generated from class fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPw == null) ? 0 : hashPw.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares objects instance fields to determine if equal
	 * @param obj The object being compared to the method caller
	 * @return Returns boolean of whether objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPw == null) {
			if (other.hashPw != null)
				return false;
		} else if (!hashPw.equals(other.hashPw))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		return maxCredits == other.maxCredits;
	}

	/**
	 * Formats a string for the Student class and its fields
	 * @return Returns string of Student class and fields
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString = String.format("%s,%s,%s,%s,%s,%d", firstName, lastName, id, email, hashPw,
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
				return 1;
			}
			else if (list.get(1).equals(getFirstName())){
				return -1;
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

}
