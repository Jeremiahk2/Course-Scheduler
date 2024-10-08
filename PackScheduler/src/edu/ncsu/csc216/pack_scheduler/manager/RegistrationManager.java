package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;
/**
 * Class for handling Registration information. Will accept a Registrar
 * user and will allow a logged-in Registrar to manage the course catalog
 * and the student directory.
 * @author Jeremiah Knizley
 * @author Spencer Grattan
 *
 */
public class RegistrationManager {

	/** an instance of a RegistrationManager */
	private static RegistrationManager instance;
	/** The current list of Courses in the catalog */
	private CourseCatalog courseCatalog;
	/** The current list of Students in the Directory */
	private StudentDirectory studentDirectory;
	/** The User that corresponds to the Registrar */
	private User registrar;
	/** The user that is currently using the manager */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** The name of the file where the login information is stored (STORED LOCALLY). */
	private static final String PROP_FILE = "registrar.properties";
	/** The faculty directory */
	private FacultyDirectory facultyDirectory; 

	/**
	 * Constructor for RegistrationManager, creates a new Registrar user using createRegistrar()
	 */
	private RegistrationManager() {
		createRegistrar();
		this.studentDirectory = new StudentDirectory();
		this.courseCatalog = new CourseCatalog();
		this.facultyDirectory = new FacultyDirectory();
	}

	/**
	 * adds the faculty to the given course
	 * @param course the course to be added to the faculty's schedule
	 * @param faculty the faculty that will be teaching the course
	 * @return true, if successfully added the course to the faculty's schedule, false if not.
	 * @throws IllegalArgumentException if the faculty could not be added to the course
	 */
	public boolean addFacultyToCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			faculty.getSchedule().addCourseToSchedule(course);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * removes the faculty from the given course
	 * @param course the course to be removed
	 * @param faculty the faculty with which the course will be removed from
	 * @return true if successfully removed, false if not
	 * @throws IllegalArgumentException if the course could not be removed from the faculty
	 */
	public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			faculty.getSchedule().removeCourseFromSchedule(course);
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * resets the given faculty member's schedule
	 * @param faculty the faculty member whose schedule will be reset
	 * @throws IllegalArgumentException if the faculty's schedule could not be reset
	 */
	public void resetFacultySchedule(Faculty faculty) {
		if (currentUser != null && currentUser.equals(registrar)) {
			faculty.getSchedule().resetSchedule();
		} else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * creates a new Registrar user using information from PROP_FILE
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Hashes the password of the current user
	 * @param 	pw the password of the user
	 * @return 	the password of the user in hashed form
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Returns the instance of this registration manager
	 * @return instance the instance of the current RegistrationManager object
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the current courseCatalog
	 * @return courseCatalog the current courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * returns the current StudentDirectory
	 * @return studentDirectory the Student Directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Returns the faculty directory 
	 * @return facultyDirectory the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Logs in a student or Registrar to the Registration Manager
	 * stores currentUser as s if the user is a student, "registrar" if the login information
	 * matches that of the registrar
	 * 
	 * @param 		id the ID of the user to be logged in
	 * @param 		password the password of the user to be logged in (NOT hashed)
	 * @return 		true if the user was successfully logged in, false if not
	 */
	public boolean login(String id, String password) {
	
		//if someone is logged in return false
		if (this.currentUser != null) {
			return false;
		}
		
		//hash the given password
		String localHashPW = hashPW(password);
		
		//if the id and password match the registrar, log in
		if (registrar.getId().equals(id)) {

			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
		}
		else {
			//check if there is a student that matches the given id
			Student s = studentDirectory.getStudentById(id);
			Faculty f = facultyDirectory.getFacultyById(id);
			if (s == null && f == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			}
			
			//if there is a student, check if their password is correct
			if (s != null && s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}	
			
			if (f != null && f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
		}

		//if none of the information is correct, return false
		//throw new IllegalArgumentException("User doesn't exist.");
		return false;
	}

	/** logs out the current user */
	public void logout() {
		currentUser = null;
	}

	/**
	 * returns the current user
	 * 
	 * @return User the current user as a User object
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * clears the data stored in the courseCatalog and studentDirectory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Class for creating Registrar users that are responsible for 
	 * managing the courseCatalog and studentDirectory
	 * 
	 * @author Jeremiah Knizley
	 *
	 */
	private static class Registrar extends User {
		
		/**
		 * Constructor for Registrars, parameters follow the same constraints as any User
		 * 
		 * @param firstName 	the first name of the Registrar user
		 * @param lastName 		the last name of the Registrar user
		 * @param id 			the ID of the Registrar user
		 * @param email 		the email of the Registrar user
		 * @param hashPW 		the hashed password of the Registrar user
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (currentUser == null) {
	    	throw new IllegalArgumentException("Illegal Action");
	    }
		if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (currentUser == null) {
	    	throw new IllegalArgumentException("Illegal Action");
	    }
		if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null) {
			throw new IllegalArgumentException("Illegal Action");
		}
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
}