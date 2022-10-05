package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

//TODO: Add abstraction
/**
 * Class for creating Users who operate pack scheduler
 * @author Spencer Grattan
 * @author Jerry Knizley
 */
public abstract class User {

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
	
	/**
	 * Constructor for User objects. Only needs first name, last name, id, email and password
	 * @param firstName		the person's first name
	 * @param lastName		the person's last name
	 * @param id			the person's ID
	 * @param email			the person's email address
	 * @param pw			the person's password
	 */
	public User(String firstName, String lastName, String id, String email, String pw) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(pw);
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
	protected void setId(String id) {
		
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		
		this.id = id;
	}

	/**
	 * Default override for hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, hashPw, id, lastName);
	}

	/**
	 * Default override for equals()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(hashPw, other.hashPw) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}

}