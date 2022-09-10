package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Student class
 * @author Geigh Neill
 * @author Jeremiah Knizley
 * @author Sahil Kanchan
 */
public class Student {
	
	/** the first name of a student */
	private String firstName;
	/** The last name of a student */
	private String lastName;
	/** The student's ID number as a string */
	private String id;
	/** The student's email address */
	private String email;
	/** The student's password */
	private String hashPw;
	/** the student's maximum credits */
	private int maxCredits;
	/** The max credits a student can have */
	public static final int MAX_CREDITS = 18;
	/** The min credits a student can have */
	public static final int MIN_CREDITS = 3;

	/**
	 * Student constructor
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param id student's id number
	 * @param email student's email
	 * @param hashPW  The student's password
	 * @param maxCredits maximum credits student can have
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
	 * Student constructor
	 * @param firstName student's first name
	 * @param lastName student's last name
	 * @param id student's id number
	 * @param email student's email
	 * @param hashPW The student's password
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
	 * Returns first name of student
	 * @return student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns last name of student
	 * @return student's last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns id of student
	 * @return student's id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * @param email the email to set
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
	 * @return the hashPw
	 */
	public String getPassword() {
		return hashPw;
	}

	/**
	 * Sets the student's password
	 * @param hashPw the hashPw to set
	 */
	public void setPassword(String hashPw) {
		
		if (hashPw == null || "".equals(hashPw)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		this.hashPw = hashPw;
	}

	/**
	 * Returns the max credits a student has
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits a student can have
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		
		this.maxCredits = maxCredits;
	}

	/**
	 * Sets the student's first name
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		
		this.lastName = lastName;
	}

	/**
	 * Sets the student's id number
	 * @param id the id to set
	 */
	private void setId(String id) {
		
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		this.id = id;
	}

	/**
	 * Generates hashCode for Student Object
	 * @return Returns hash code generated by class fields
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
	 * @param obj The object being compared to this one
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

}
