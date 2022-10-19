/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.InvalidTransitionException;

/**
 * Test class for CourseNameValidator
 * @author Jeremiah Knizley
 *
 */
class CourseNameValidatorTest {

	/**
	 * test method for a state where only one letter is entered at the start
	 */
	@Test 
	void testStateL() {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a345")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a859c")));
//		assertFalse(validator.isValid("a28"));
	}
	
	@Test
	void testStateLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ah155")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("rn493d")));
//		assertFalse(validator.isValid("as3"));
	}
	@Test
	void testStateLLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("sfd320")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("asf294e")));
	}
	@Test
	void testStateLLLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ajfb582")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ahfq295p")));
	}
	
	@Test
	void testInvalidStrings() {
		CourseNameValidator validator = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("l@"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("9"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("abcdp"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("a4c"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("ai3p"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("aosf0s"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("sjfm6f"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("b52l"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("s2052"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("f294p5"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("s203gg"));
	}
}
