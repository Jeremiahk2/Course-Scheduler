/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException is a checked exception that is thrown by Conflict interface
 * if a conflict is present between Activities. 
 * @author Geigh Neill
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that has a parameter for custom exception message.
	 * @param message Exception message produced when thrown
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Parameterless constructor that passes default exception message.
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
