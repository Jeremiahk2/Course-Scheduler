/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This method acts as the file IO for faculty records and allows for the reading and storing
 * of faculty with files.
 * 
 * @author albressl
 */
public class FacultyRecordIO {

	/**
	 * This method reads a given file name and attempts to create a list of faculty from the data
	 * contained in the file
	 * @param fileName name of the file to extract faculty records from
	 * @return LinkedList a list of faculty
	 * @throws FileNotFoundException if the file name cannot be found as a file.
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> newFaculty = new LinkedList<Faculty>();
		while (fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				newFaculty.add(faculty);
			}
			catch (IllegalArgumentException e){
				//line is invalid, just skip it
			}
		}
		fileReader.close();
		return newFaculty;
		
	}

	/**
	 * This method processes individual file lines for the IO method attempting
	 * to make valid faculty
	 * @param nextLine the current line being read
	 * @return Faculty the faculty that was created by the line
	 * @throws IllegalArgumentException if the line is invalid
	 */
	private static Faculty processFaculty(String nextLine) {
		Scanner lineScanner = new Scanner(nextLine);
		try {
			lineScanner.useDelimiter(",");
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String facultyId = lineScanner.next();
			String email = lineScanner.next();
			String hashedPw = lineScanner.next();
			int maxCourses = lineScanner.nextInt();
			lineScanner.close();
			return new Faculty(firstName, lastName, facultyId, email, hashedPw, maxCourses);
		} catch (NoSuchElementException InputMismatchException) {
			lineScanner.close();
			throw new IllegalArgumentException("Error reading files.");
		}
	}
	
	/**
	 * This method writes the faculty records to a file so they can be stored for future use
	 * @param fileName name of the file being stored to
	 * @param facultyDirectory the list of faculty being saved to a file
	 * @throws IOException if file cannot be written to
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
    	    fileWriter.println(facultyDirectory.get(i).toString());
    	}

    	fileWriter.close();
		
	}
}
