/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
		assertDoesNotThrow(() -> assertTrue(validator.isValid("E115")));
//		assertFalse(validator.isValid("a28"));
	}
	
	/**
	 * test method for a state where only two letters are entered at the start
	 */
	@Test
	void testStateLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("rn493d")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ah155")));
//		assertFalse(validator.isValid("as3"));
	}
	
	/**
	 * test method for a state where three letters are entered at the start
	 */
	@Test
	void testStateLLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("sfd320")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("asf294e")));
	}
	
	/**
	 * test method for a state where four letters are entered at the start
	 */
	@Test
	void testStateLLLL()   {
		CourseNameValidator validator = new CourseNameValidator();
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ajfb582")));
		assertDoesNotThrow(() -> assertTrue(validator.isValid("ahfq295p")));
	}
	
	/**
	 * test method for things entered after the suffix.
	 */
	@Test
	void testInvalidSuffix()	{
		CourseNameValidator validator = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("ahfq2958"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("ahfq295!"));
	}
	
	/**
	 * test method for various invalid strings
	 */ 
	@Test
	void testInvalidStrings() {
		CourseNameValidator validator = new CourseNameValidator();
		Exception e2 = assertThrows(InvalidTransitionException.class, () -> validator.isValid("9hiya"));
		assertEquals("Course name must start with a letter.", e2.getMessage());
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("abcdp"));
		assertDoesNotThrow(() -> assertFalse(validator.isValid("E11")));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("ai3p"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("aosf0s"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("sjfm6f"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("b52l"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("s2052"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("f294p5"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("s203gg"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("le@"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("sjf!"));
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("p492df"));
	} 
	/**
	 * test method for a string that specifically STARTS with an invalid character.
	 */
	@Test
	void testInvalidStrings1() {
		CourseNameValidator validator = new CourseNameValidator();
		assertThrows(InvalidTransitionException.class, () -> validator.isValid("9hiya"));
	}
}
