package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Abstract super class of Course and Event that holds the common instance fields between the two
 * title, meeting days, start time, and end time. Allows the scheduler system to hold both Course
 * and Event objects in the same ArrayList to provide greater functionality between the two
 * classes. Activity also abstracts both Course and Event methods getShortDisplayArray and
 * getLongDisplayArray. 
 * 
 * @author Geigh Neill
 *
 */
public abstract class Activity implements Conflict {

	/** Total hours in a day */
	private static final int UPPER_HOUR = 24;
	/** Total minutes in an hour */
	private static final int UPPER_MINUTE = 60;
	/** Used for military time conversion */
	private static final int MILITARY_CONVERSION = 100;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Activity's constructor provides a foundation for when the child classes event and course
	 * are constructed but is never constructed directly on its own. Common fields such as title
	 * meeting days, start time, and end time are set here for Event and Course classes.
	 * @param title The title of the Activity
	 * @param meetingDays The meeting days of the Activity
	 * @param startTime The start time of the Activity
	 * @param endTime The end time of the Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Activity's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
		
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days, start time, and end time
	 * @param meetingDays the Course meeting days to set
	 * @param startTime the Course start time to set
	 * @param endTime the Course end time to set
	 * @throws IllegalArgumentException if meeting days and times are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
			
		int startHour = startTime / MILITARY_CONVERSION;
		int startMin = startTime % MILITARY_CONVERSION;
		int endHour = endTime / MILITARY_CONVERSION;			
		int endMin = endTime % MILITARY_CONVERSION;
			
		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
			
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the meeting days as well as the meeting start and end in standard time
	 * @return Returns meeting info as string
	 */
	public String getMeetingString() {
		String meetingString;
		String newStartTime = getTimeString(getStartTime());
		String newEndTime = getTimeString(getEndTime());
		
		if ("A".equals(getMeetingDays())) {
			meetingString = "Arranged";
		}
		else {
			meetingString = getMeetingDays() + " " + newStartTime + "-" + newEndTime; 
		}
		
		return meetingString;
	}

	/**
	 * Returns military time integer converted to a standard time format string
	 * @param time the military time to be converted
	 * @return the standard time conversion
	 */
	private String getTimeString(int time) {
		int hour = time / MILITARY_CONVERSION;
		int min = time % MILITARY_CONVERSION;
		
		String s;
		
		if (hour == 12) {
			if (min < 10) {
				s = String.format("%d:0%dPM", hour, min);
			}
			else {
				s = String.format("%d:%dPM", hour, min);
			}
		}
		else if (hour > 12) {
			if (min < 10) {
				s = String.format("%d:0%dPM", hour - 12, min);
			}
			else {
				s = String.format("%d:%dPM", hour - 12, min);
			}
		}
		else {
			if (min < 10) {
				s = String.format("%d:0%dAM", hour, min);
			}
			else {
				s = String.format("%d:%dAM", hour, min);
			}
		}
		
		return s;
	}

	/**
	 * Returns the Activity's start time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	
	/**
	 * Creates a short, 4 element length array to display Event and Course field information
	 * @return String array containing a shortened display array of field info
	 */
	public abstract String[] getShortDisplayArray(); 
	
	/**
	 * Creates a long, 7 element length array to display Event and Course field information
	 * completely.
	 * @return String array containing a lengthened display array of field info
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Checks for duplicates of an Event or Course in WolfScheduler before adding an Activity.
	 * @param activity The activity being checked for duplicates in schedule
	 * @return Whether the activity has a duplicate or not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Checks whether there is a conflict when adding an Activity to schedule. Iterates through getMeetingDays() string comparing
	 * the characters in it to the getMeetingDays() of the parameter. If a matching character is found boolean dayMatch is set to 
	 * true. When dayMatch is true checkConflict will find which Activity, this or parameter, has the latest starting time and use
	 * that to determine whether there is or is not a conflict. If a conflict is present ConflictException is thrown. Overridden
	 * because comes from Conflict interface.
	 * @param possibleConflictingActivity Activity being checked for conflict
	 * @throws ConflictException When a conflict is present between Activities
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean dayMatch = false;
		for (char c : getMeetingDays().toCharArray()) {
			if (c == 'A') {
				break;
			}
			for (char c1 : possibleConflictingActivity.getMeetingDays().toCharArray()) {
				if (c == c1) {
					dayMatch = true;
				}
			}
			if (dayMatch) {
				break;
			}
		}
		
		int st = getStartTime();
		int et = getEndTime();
		int st1 = possibleConflictingActivity.getStartTime();
		int et1 = possibleConflictingActivity.getEndTime();
		boolean isConflict = false;
		
		if (dayMatch) {
			if (Integer.compare(st, st1) == 0) {
				isConflict = true;
			}
			else if (Integer.compare(st, st1) < 0) {
				if (st1 <= et) {
					isConflict = true;
				}
			}
			else {
				if (st <= et1) {
					isConflict = true;
				}
			}
		}
		
		if (isConflict) {
			throw new ConflictException();
		}
		
	}

	/**
	 * Generates hash code for class objects. Overridden because of super class status.
	 * @return Result of hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Returns boolean value on whether two objects are equals depending on class equivalence
	 * and fields. Overridden because of super class status.
	 * @param obj Object being compared with the method caller
	 * @return boolean value whether the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}