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
	/** The last nam eof a student */
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
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setMaxCredits(maxCredits);
		setHashPw(hashPW);
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
		setHashPw(hashPW);
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
			throw new IllegalArgumentException("Invalid email.");
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
			throw new IllegalArgumentException("Invalid email.");
		}
		if (atIndex - periodIndex < 0) {
			throw new IllegalArgumentException("Invalid email.");
		}
		
		this.email = email;
	}

	/**
	 * @return the hashPw
	 */
	public String getHashPw() {
		return hashPw;
	}

	/**
	 * @param hashPw the hashPw to set
	 */
	public void setHashPw(String hashPw) {
		
		if (hashPw == null || "".equals(hashPw)) {
			throw new IllegalArgumentException("Invalid password.");
		}
		
		this.hashPw = hashPw;
	}

	/**
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.maxCredits = maxCredits;
	}

	/**
	 * Sets the student's first name
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name.");
		}
		
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name.");
		}
		
		this.lastName = lastName;
	}

	/**
	 * Sets the student's id number
	 * @param id the id to set
	 */
	private void setId(String id) {
		
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id.");
		}
		
		this.id = id;
	}
	
	

}
