package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Student class
 * @author Geigh Neill
 * @author Jeremiah Knizley
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
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
		this.maxCredits = maxCredits;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param id the id to set
	 */
	private void setId(String id) {
		this.id = id;
	}
	
	

}
