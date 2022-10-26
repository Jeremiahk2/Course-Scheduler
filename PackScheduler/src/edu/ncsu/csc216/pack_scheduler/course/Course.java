/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Course object class that contains the name, title, section, credit hours, instructor's id
 * meeting days, start time, and end time as private fields with their getters and setters.
 * Child of Activity class.
 * @author Geigh Neill
 * @author Spencer Grattan
 */
public class Course extends Activity implements Comparable<Course> {
	
	/** Minimum length of Course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum length of Course name */
	private static final int MAX_NAME_LENGTH = 8;
	/** Length of section */
	private static final int SECTION_LENGTH = 3;
	/** Maximum number of credits */
	private static final int MAX_CREDITS = 5;
	/** Minimum number of credits */ 
	private static final int MIN_CREDITS = 1;
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a course object with values for all fields
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructors unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for
	 * courses that are arranged
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. Parameter string cannot be null or empty. If the name has a space or number
	 * or is above or below the max and min letter counts then an IAE will be thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if course name is invalid
	 */
	private void setName(String name) {
		
		if (name == null) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		CourseNameValidator validator = new CourseNameValidator();
		try {
			if (validator.isValid(name)) {
				this.name = name;
			}
			else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}

	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. Will throw an IAE if the section String is null, not equal to 3 in length, or
	 * has a digit in it.
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is invalid.
	 */
	public void setSection(String section) {
		
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		
		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. Will throw an IAE if the credits are below 1 credit or above 5
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are invalid
	 */
	public void setCredits(int credits) {
		
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.credits = credits;
	}

	/**
	 * Returns the Course instructors Id
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course instructors Id. Throws an IAE if instructorId is null or empty.
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is invalid
	 */
	public void setInstructorId(String instructorId) {
		
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		this.instructorId = instructorId;
	}
	
	/**
	 * Overrides Activity to check for Course specific meeting day requirements such as only week days. Will throw an IAE if 
	 * the course is arranged and has times other than 0, if there are more than one of each day in meeting string, and if any
	 * character other than M, T, W, H, F, or A are present in meeting string. Overridden because of different functionality
	 * compared to its sibling Event. Doesn't allow days on the weekend like Event.
	 * @param meetingDays The days the course meets
	 * @param startTime The time the course starts
	 * @param endTime The time the course ends
	 * @throws IllegalArgumentException if meeting days and times are invalid.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if ("A".equals(meetingDays)) {
			if (!(startTime == 0 && endTime == 0)) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		}
		else {
			int mCount = 0;
			int tCount = 0;
			int wCount = 0;
			int hCount = 0;
			int fCount = 0;
			
			for (int i = 0; i < meetingDays.length(); i++) {
				if (meetingDays.charAt(i) == 'M') {
					mCount++;
				}
				else if (meetingDays.charAt(i) == 'T') {
					tCount++;
				}
				else if (meetingDays.charAt(i) == 'W') {
					wCount++;
				}
				else if (meetingDays.charAt(i) == 'H') {
					hCount++;
				}
				else if (meetingDays.charAt(i) == 'F') {
					fCount++;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			if (mCount > 1 || tCount > 1 || wCount > 1 || hCount > 1 || fCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * The short display array is used to populate the rows of the course catalog and student 
	 * schedule. Override because it's an Activity class method.
	 * @return String array containing a shortened display array
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] returnArray = {getName(), getSection(), getTitle(), getMeetingString()};
		return returnArray;
 	}

	/**
	 * The full display array is used to display the final schedule. Override because it's an
	 * Activity class method.
	 * @return String array containing a long display array
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] returnArray = {getName(), getSection(), getTitle(), Integer.toString(getCredits()),
				getInstructorId(), getMeetingString(), ""};
		return returnArray;
	}
	
	/**
	 * Checks whether a course object is a duplicate
	 * @param activity The Activity being checked for duplication
	 * @return Whether the course object was a duplicate
	 */
	public boolean isDuplicate(Activity activity) {
		boolean isDuplicate = false;
		if (activity instanceof Course) {
			Course other = (Course) activity;
			if (other.getName().equals(name)) {
				isDuplicate = true;
			}
		}
		
		return isDuplicate;
	}

	/**
	 * Generates hash code for class objects. Overridden because of inheritance hierarchy.
	 * @return Result of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Returns boolean value on whether two objects are equals. Overridden because of inheritance hierarchy.
	 * @param obj Object being compared with the method caller
	 * @return boolean value whether the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields. Overridden to make specific for Course objects.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	@Override
	public int compareTo(Course c) {
		if (c == null) {
			throw new NullPointerException("Object is null.");
		}
		if (getClass() != c.getClass()) {
			throw new ClassCastException("Object is not student.");
		}
		
		SortedList<String> list = new SortedList<String>();
		
		if (this.equals(c) ) {
			return 0;
		}
		else if (!getName().equals(c.getName())) {
			list.add(getName());
			list.add(c.getName());
			if (list.get(0).equals(getName())) {
				return -1;
			}
			else if (list.get(1).equals(getName())) {
				return 1;
			}
		}
		else if (!getSection().equals(c.getSection())) {
			list.add(getSection());
			list.add(c.getSection());
			if (list.get(0).equals(getSection())) {
				return -1;
			}
			else if (list.get(1).equals(getSection())) {
				return 1;
			}
		}
		else {
			return 0;
		}
		return 0;
	}

}
