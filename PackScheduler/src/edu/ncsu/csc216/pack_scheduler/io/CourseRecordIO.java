package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Writes into a file the Course records and reads Course records from files.
 * @author Geigh Neill
 *
 */
public class CourseRecordIO {
	
	 /**
     * Writes the given list of Courses to a text file in the proper format.
     * @param fileName file to write schedule of Courses to
     * @param courses list of Courses to write
     * @throws IOException if cannot write to file
     */
	public static void writeCourseRecords(String fileName, ArrayList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

	/**
     * Reads course records from a file and generates a list of valid Courses. Any invalid
     * Courses are ignored. If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Reads course info from string and returns desired Course object or throws
	 * IllegalArgumentException if string is not formatted correctly
	 * @param nextLine the next line in the Course records file to be read
	 * @return Returns a Course object with course info from the string
	 */
	private static Course readCourse(String nextLine) {
		Scanner in = new Scanner(nextLine);
		in.useDelimiter(",");
		
		try {
			String name = in.next();
			String title = in.next();
			String section = in.next();
			int credits = in.nextInt();
			String instructorId = in.next();
			String meetingDays = in.next();
			
			if ("A".equals(meetingDays)) {
				if (in.hasNext()) {
					in.close();
					throw new IllegalArgumentException("Invalid course.");
				}
				else {
					in.close();
					return new Course(name, title, section, credits, instructorId, meetingDays);
				}
			}
			else {
				int startTime = in.nextInt();
				int endTime = in.nextInt();
				
				if (in.hasNext()) {
					in.close();
					throw new IllegalArgumentException("Invalid course.");
				}
				else {
					in.close();
					return new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				}
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException(e);
		}
	}

}