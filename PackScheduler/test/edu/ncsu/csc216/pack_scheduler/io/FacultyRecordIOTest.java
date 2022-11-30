/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This is the test method for faculty record IO which allows for the saving and loading of faculty record files
 * @author albressl
 */
class FacultyRecordIOTest {
	
	/** a valid faculty */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** a valid faculty */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** a valid faculty */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** a valid faculty */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** a valid faculty */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** a valid faculty */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** a valid faculty */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** a valid faculty */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** An array containing all valid faculty used for testing purposes */
	private String [] validFaculty = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5,
	        validFaculty6, validFaculty7};
	
	/** A string containing the hashed password for a faculty */
	private String hashPW;
	/**The Algorithm used for hashing passwords */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Sets up hashPW and files for testing
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Test method for readFacultyRecords
	 */
	@Test
	void testReadFacultyRecords() {
		String validTestFile = "test-files/faculty_records.txt";
		String invalidTestFile = "test-files/invalid_faculty_records.txt";
		try {
			LinkedList<Faculty> records = new LinkedList<Faculty>();
			records = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, records.size());
			for (int i = 0; i < records.size(); i++) {
				assertEquals(validFaculty[i], records.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
		
		try {
			LinkedList<Faculty> invalidRecords = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertTrue(invalidRecords.isEmpty());
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + invalidTestFile);
		}
		
		assertThrows(FileNotFoundException.class, () -> FacultyRecordIO.readFacultyRecords("test-files/nonExistantFile"));
		
		
	}
	
	//Test method for writeFacultyRecords for writing to a valid file
	@Test
	void testWriteFacultyRecordsValid() {
		LinkedList<Faculty> records = new LinkedList<Faculty>();
		records.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		records.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		records.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", records);
			checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		} catch (IOException e) {
			fail("Cannot write to faculty records file");
		}
	}
	/**
	 * Test method for writeFacultyRecords for writing to an invalid file
	 */
	@Test
	void testWriteFacultyRecordsInvalid() {
		LinkedList<Faculty> records = new LinkedList<Faculty>();
		records.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 3));
		//Try writing to a file it doesn't have access to
		Exception exception = assertThrows(IOException.class, 
				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", records));
		assertEquals("/home/sesmith5/actual_faculty_records.txt (No such file or directory)", exception.getMessage());
		
		
		//try writing to valid location.
		
	}

	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
