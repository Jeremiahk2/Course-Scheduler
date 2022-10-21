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
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a859c")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a345")));
//		assertFalse(validator.isValid("a28"));
	}
	
	@Test
	void testStateLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("rn493d")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ah155")));
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
		assertFalse(validator.isValid("l@"));
		assertFalse(validator.isValid("9"));
		assertFalse(validator.isValid("abcdp"));
		assertFalse(validator.isValid("a4c"));
		assertFalse(validator.isValid("ai3p"));
		assertFalse(validator.isValid("aosf0s"));
		assertFalse(validator.isValid("sjfm6f"));
		assertFalse(validator.isValid("b52l"));
		assertFalse(validator.isValid("s2052"));
		assertFalse(validator.isValid("f294p5"));
		assertFalse(validator.isValid("s203gg"));
	}
}
