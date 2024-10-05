/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Faculty directory object which stores all the faculty and manages them as a group
 * this allows for the control of the current list of faculty.
 * @author albressl
 */
public class FacultyDirectory {

	/** List of faculty in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * This method constructs a new faculty directory
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}

	/**
	 * This method initializes a new faculty directory for use by clearing the 
	 * current directory
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();
	}
	
	/**
	 * This method attempts to read faculty records from a given file and set the results as the 
	 * current directory
	 * @param fileName the name of the file being read
	 * @throws IllegalArgumentException if file cannot be read
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * This method creates a new faculty member and then attempts to add them to the list of faculty
	 * @param firstName First name of faculty member
	 * @param lastName Last name of faculty member
	 * @param id Id of faculty member
	 * @param email email of faculty member
	 * @param password password of faculty member
	 * @param repeatPassword password of faculty member repeated
	 * @param maxCourses the max number of courses the faculty member can teach
	 * @return boolean false if faculty member cannot be added
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password, String repeatPassword, int maxCourses) {
		String hashPW = "";
		String repeatHashPW = "";
		if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
			throw new IllegalArgumentException("Invalid password");
		}
		
		hashPW = hashString(password);
		repeatHashPW = hashString(repeatPassword);
		
		if (!hashPW.equals(repeatHashPW)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		//If an IllegalArgumentException is thrown, it's passed up from Faculty
		//to the GUI
		Faculty faculty = null;
		faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
		
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(faculty.getId())) {
				return false;
			}
		}
		return facultyDirectory.add(faculty);
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * This method removes a faculty member from the directory based on matching IDs
	 * @param facultyId the ID of the faculty member being removed
	 * @return boolean true if successfully removed
	 */
	public boolean removeFaculty(String facultyId) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			if (s.getId().equals(facultyId)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method creates a 2d string array for use by the GUI
	 * that represents the faculty directory
	 * @return String[][] the faculty directory as a 2d string array
	 */
	public String[][] getFacultyDirectory() {
		String [][] directory = new String[facultyDirectory.size()][3];
		for (int i = 0; i < facultyDirectory.size(); i++) {
			User s = facultyDirectory.get(i);
			directory[i][0] = s.getFirstName();
			directory[i][1] = s.getLastName();
			directory[i][2] = s.getId();
		}
		return directory;
	}
	
	/**
	 * This saves the current faculty directory to a file for future use
	 * @param fileName the file being saved to
	 * @throws IllegalArgumentException if unable to save to file
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}
	
	/**
	 * This method returns a specific faculty member and searched for them using their ID
	 * returns null if the faculty member cannot be found
	 * @param id the id of the desired faculty member
	 * @return the faculty member that was found
	 */
	public Faculty getFacultyById(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			if (facultyDirectory.get(i).getId().equals(id)) {
				return facultyDirectory.get(i);
			}
		}
		
		return null;
	}
} 
