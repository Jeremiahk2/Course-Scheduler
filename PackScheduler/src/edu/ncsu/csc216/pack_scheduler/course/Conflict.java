/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict interface that is used to handle scheduling conflicts between activities
 * in WolfScheduler.
 * @author Geigh Neill
 *
 */
public interface Conflict {
	
	/**
	 * Checks whether there is a conflict between the method calling instance of Activity and the 
	 * parameter possibleConflictingActivity. A conflict occurs when there is at least one day with
	 * an overlapping time. A time is overlapping when the minute is the same as another time. If a
	 * conflict is present a ConflictException is thrown.
	 * @param possibleConflictingActivity Activity being checked for conflict with method caller
	 * @throws ConflictException When a conflict is present between Activities
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
