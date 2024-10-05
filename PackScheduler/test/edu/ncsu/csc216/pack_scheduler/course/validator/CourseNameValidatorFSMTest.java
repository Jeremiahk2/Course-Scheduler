/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class for CourseNameValidatorFSM
 * @author Jeremiah Knizley
 *
 */
class CourseNameValidatorFSMTest {

	/**
	 * test method for a state where only one letter is entered at the start
	 */
	@Test 
	void testStateL() {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a345")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("a859c")));
//		assertFalse(validator.isValid("a28"));
	}
	
	@Test
	void testStateLL()   {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ah155")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("rn493d")));
//		assertFalse(validator.isValid("as3"));
	}
	@Test
	void testStateLLL()   {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("sfd320")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("asf294e")));
	}
	@Test
	void testStateLLLL()   {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ajfb582")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ahfq295p")));
	}
	
	@Test
	void testInvalidStrings() {
		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
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
	
//	void testStateLD() throws InvalidTransitionException {
//		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
//		assertTrue(validator.isValid("a345"));
//		assertTrue(validator.isValid("j3294"));
//	}
//	
//	void testStateLLD() throws InvalidTransitionException {
//		CourseNameValidatorFSM validator = new CourseNameValidatorFSM();
//		assertTrue(validator.isValid("sk345))
//	}
}
