package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
//import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class for creating StudentRecordIO objects, which keep track of students.
 * @author Sahil Kanchan
 * @author Jeremiah Knizley
 */
public class StudentRecordIO {

	/**
	 * Reads through a CSV file that contains students and their information and stores them
	 * in an ArrayList. If the format for the line with the student's information is invalid, it is skipped
	 * and that student is not added to the list.
	 * @param fileName the name of the file that contains the student records
	 * @return newStudent contains a list of all valid students
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> newStudent = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				newStudent.add(student);
			}
			catch (IllegalArgumentException e){
				//line is invalid, just skip it
			}
		}
		fileReader.close();
		return newStudent;
		
	}
	
	/**
	 * Writes student records to a file where each line is a different student and displays their details as a comma separated list
	 * @param fileName the name of the file to write the student records to
	 * @param studentDirectory the list of current students
	 * @throws IOException if the file cannot be found 
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for (int i = 0; i < studentDirectory.size(); i++) {
    	    fileWriter.println(studentDirectory.get(i).toString());
    	}

    	fileWriter.close();
		
	}
	
	/**
	 * Helper method for readCourseRecords. Processes a line
	 * from a CSV file containing students and their information and turns them
	 * into Student objects using the details on the line.
	 * @param line the line to be parsed
	 * @return Student the student object that results from parsing the line.
	 * @throws IllegalArgumentException if there is an error reading the line (like invalid format of the line)
	 */
	private static Student processStudent(String line) {
		Scanner lineScanner = new Scanner(line);
		try {
			lineScanner.useDelimiter(",");
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String studentId = lineScanner.next();
			String email = lineScanner.next();
			String hashedPw = lineScanner.next();
			int credits = lineScanner.nextInt();
			lineScanner.close();
			return new Student(firstName, lastName, studentId, email, hashedPw, credits);
		} catch (NoSuchElementException InputMismatchException) {
			lineScanner.close();
			throw new IllegalArgumentException("Error reading files.");
		}
	}

}