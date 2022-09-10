package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * 
 * @author Sahil Kanchan
 *
 */
public class StudentRecordIO {

	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		ArrayList<Student> newStudent = new ArrayList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				newStudent.add(student);
			}
			catch (IllegalArgumentException e){
				
			}
		}
		fileReader.close();
		return newStudent;
		
	}
	
	/**
	 * 
	 * @param fileName
	 * @param studentDirectory
	 * @throws IOException
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for (int i = 0; i < studentDirectory.size(); i++) {
    	    fileWriter.println(studentDirectory.get(i).toString());
    	}

    	fileWriter.close();
		
	}
	
//	private static Student processStudent(String line) {
//		
//	}

}
