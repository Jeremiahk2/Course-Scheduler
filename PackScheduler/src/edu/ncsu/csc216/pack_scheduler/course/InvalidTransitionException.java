package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Class to handle invalid transitions in the finite state machine
 */
public class InvalidTransitionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with custom message
	 * @param message the message returned for the exception
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Constructor with default message
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");
	}
}
