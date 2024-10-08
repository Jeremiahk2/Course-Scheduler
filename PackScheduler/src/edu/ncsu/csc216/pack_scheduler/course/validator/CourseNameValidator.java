package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Ensures that a course name is valid
 * @author Jeremiah Knizley
 *
 */
public class CourseNameValidator {


	/** boolean stating if the ending state is valid or not. */
	private boolean validEndState;
	/** the letter count of the course string */
	private int letterCount;
	/** the digit count of the course string */
	private int digitCount;
	/** the current state of the course name */
	private State currentState;
	/** The state of the course name in its initial state */
	private final InitialState initialState = new InitialState();
	/** The state of the course name in its suffix state */
	private final SuffixState suffixState = new SuffixState();
	/** The state of the course name in its letter state */
	private final LetterState letterState = new LetterState();
	/** The state of the course name in its number state */
	private final NumberState numberState = new NumberState();
	/**
	 * Constructor for CourseNameValidator, initializes currentState to initialState.
	 */
	public CourseNameValidator() {
		currentState = initialState;
	}

	/**
	 * determines whether or not a course name is valid.
	 * @param name the name of the course
	 * @return boolean, true if valid, false if not.
	 * @throws InvalidTransitionException if an invalid character appears in the course name
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		char[] characters = new char[name.length()];
		characters = name.toCharArray();
		currentState = initialState;
		digitCount = 0;
		letterCount = 0;
		for (int i = 0; i < characters.length; i++) {
			if (currentState == initialState) {
				if (Character.isDigit(characters[i])) {
					currentState.onDigit(); 
				}
				else if (Character.isLetter(characters[i])) {
					currentState.onLetter(); 
				}
				else {
					currentState.onOther(); 
				}
			}
			else if (currentState == suffixState) {
				if (Character.isDigit(characters[i])) {
					currentState.onDigit(); 
				}
				else if (Character.isLetter(characters[i])) {
					currentState.onLetter(); 
				}
				else {
					currentState.onOther(); 
				}
			}
			else if (currentState == letterState) {
				if (Character.isDigit(characters[i])) {
					currentState.onDigit(); 
				}
				else if (Character.isLetter(characters[i])) {
					currentState.onLetter(); 
				}
				else {
					currentState.onOther(); 
				}
			}
			else if (currentState == numberState) {
				if (Character.isDigit(characters[i])) {
					currentState.onDigit(); 
				}
				else if (Character.isLetter(characters[i])) {
					currentState.onLetter(); 
				}
				else {
					currentState.onOther(); 
				}
			}
		}

		if (validEndState) {
			currentState = initialState;
			digitCount = 0;
			letterCount = 0;
			return true;
		}
		currentState = initialState;
		digitCount = 0;
		letterCount = 0;
		return false;
	}

	/**
	 * An abstract class that is used for different states a string's characters can be in. All classes behave the same for non-alphanumeric characters, so onOther is concrete
	 * All classes require onDigit and onLetter, though implementation may vary.
	 * @author Jeremiah Knizley
	 *
	 */
	public abstract class State {
		/**
		 * an abstract method for what should be done for letters in the course string for the current state
		 * @throws InvalidTransitionException  if a letter is not valid for the current state.
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * an abstract method for what should be done for digits in the course string for the current state
		 * @throws InvalidTransitionException if the state does not accept digits
		 */
		public abstract  void onDigit() throws InvalidTransitionException;

		/**
		 * throws an exception.
		 * @throws InvalidTransitionException because the course cannot contain characters other than numbers or letters.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}

	}

	/**
	 * Class for if the current state is a letter.
	 * @author Jeremiah Knizley
	 *
	 */
	private class LetterState extends State {

		/**
		 * If there are already four letters present, an exception is thrown.
		 * If not, letter count is increased by 1 and current state remains in letterState
		 * @throws InvalidTransitionException if the letter count equals 4 at the time of execution.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount == 4) {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
			else {
				letterCount++;
				currentState = letterState;
			}

		}
		/**
		 * sets current state to NumberState
		 * increases digit count.
		 */
		@Override
		public void onDigit() {
			currentState = numberState;
			digitCount++;

		}


	}
	/**
	 * Class for if the current state is a suffix.
	 * @author Jeremiah Knizley
	 *
	 */
	private class SuffixState extends State {

		/**
		 * Throws an exception
		 * @throws InvalidTransitionException because a suffix cannot have anything after it.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");

		}

		/**
		 * Throws an exception
		 * @throws InvalidTransitionException because a suffix cannot have anything after it.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}

	}
	/**
	 * Class for if the current state is the initial state.
	 * @author Jeremiah Knizley
	 *
	 */
	private class InitialState extends State {

		/**
		 * Sets the current state to a LetterState, increases letter count by 1.
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = letterState;
		}

		/**
		 * throws an invalidTransitionException
		 * @throws InvalidTransitionException because digits cannot be the first character in the course string
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");

		}

	}
	/**
	 * Class for if the current state is a digit.
	 * @author Jeremiah Knizley
	 *
	 */
	private class NumberState extends State {

		/**
		 * If digit count is three, current state is set to the suffix state and validEndState is set to true.
		 * If digit count isn't three, it's invalid because it can't transfer to the suffix state
		 * @throws InvalidTransitionException if digitCount doesn't equal 3
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != 3) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			else {
				currentState = suffixState;
				validEndState = true;
			}

		}

		/**
		 * If digit count is less than 3, digit count is increased by 1, and current state remains in the number state. 
		 * Additionally, if digit count is exactly 2, then validEndState is set to true.
		 * @throws InvalidTransitionException  if this digit would be the fourth digit, which is invalid.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount <= 2) {
				digitCount++;
				currentState = numberState;
				if (digitCount == 3) {
					validEndState = true;
				}
			}
			else if (digitCount == 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}

		}

	}

}
