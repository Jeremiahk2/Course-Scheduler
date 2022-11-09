/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Activity class. Specifically tests implementation of the 
 * checkConflict method from Conflict interface.
 * @author Geigh Neill
 *
 */
class ActivityTest {

	/**
	 * Tests Activity.checkConflict()
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests Activity.checkConflict() for conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1445, 1600);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	   
	    Exception e5 = assertThrows(ConflictException.class, () -> a2.checkConflict(a5));
	    assertEquals("Schedule conflict.", e5.getMessage());
	    
	    Exception e6 = assertThrows(ConflictException.class, () -> a5.checkConflict(a2));
	    assertEquals("Schedule conflict.", e6.getMessage());
	}
	
	/**
	 * Tests Activity.checkConflict() for no conflict cases
	 */
	@Test
	public void testCheckConflictNoConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1500, 1615);
		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
		Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	    assertDoesNotThrow(() -> a5.checkConflict(a6));
	    assertDoesNotThrow(() -> a6.checkConflict(a5));
	}
}
