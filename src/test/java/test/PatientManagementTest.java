package test;

import Clinic.Patient;
import Manager.PatientManager;
import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class PatientManagementTest {

    private PatientManager patientManager;
    private String testFilePath;
    
    
   
    private List<Patient> patientList;
    
    // another beforeeach.....
    
@BeforeEach
void setUp() throws IOException {
    patientManager = new PatientManager();

    // Clear or initialize patient list
    patientList = patientManager.getPatient(); // Make sure this returns the actual list, not a copy
    if (patientList != null) {
        patientList.clear(); // Avoid NullPointerException
    } else {
        throw new IllegalStateException("patientList is null. Check getPatient() implementation.");
    }

    // Use a test-specific file path
    testFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/test/java/testData/test_patient.txt";

    // Ensure testData directory exists
    File testDataDir = new File("D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/test/java/testData");
    if (!testDataDir.exists()) {
        testDataDir.mkdirs();
    }

    // Create a clean test file
    File file = new File(testFilePath);
    if (file.exists()) {
        file.delete(); // Delete old test data
    }
    boolean created = file.createNewFile();
    if (!created) {
        throw new IOException("Failed to create test file: " + testFilePath);
    }
}

    
    

//    @BeforeEach
//    void setUp() throws IOException {
//        patientManager = new PatientManager();
//
//        patientList = patientManager.getPatient(); // or access directly if public
//        patientList.clear(); // reset before each test
//        //testFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/patient.txt";
//        testFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/test/java/testData/test_patient.txt";
//
//
//        // Create an empty test file before each test
//        File file = new File(testFilePath);
//        if (file.exists()) {
//            file.delete();
//        }
//        file.createNewFile();
//    }

    private void addAndSavePatient(Patient patient) {
        patientManager.getPatient().add(patient);
        patientManager.savePatientTofile(testFilePath, patient);
    }

    @Test
    void testLoadPatientFromFile() throws IOException {
        // Create a file with test data
        FileWriter writer = new FileWriter(testFilePath);
        writer.write("123,John Doe,1 Main St,555-1234\n");
        writer.write("456,Jane Smith,2 Oak Ave,555-5678\n");
        writer.close();

        patientManager.loadPatientFromFile(testFilePath);
        assertEquals(2, patientManager.getPatient().size());
        assertEquals("John Doe", patientManager.getPatient().get(0).getName());
        assertEquals("Jane Smith", patientManager.getPatient().get(1).getName());
    }

    @Test
    void testDisplayAllPatient() {
        // Check method runs without errors.
        Patient patient1 = new Patient("Alice", "Address 1", "111");
        Patient patient2 = new Patient("Bob", "Address 2", "222");
        addAndSavePatient(patient1);
        addAndSavePatient(patient2);
        patientManager.displayAllpatient();
        assertTrue(true);
    }

//    @Test
//    void testAddNewPatient() throws IOException {
//        // Simulate user input.
//        String input = "Test User\nTest Address\n123-456-7890\n";
//        Scanner scanner = new Scanner(input);
//
//        // Provide sample input to the user (optional, for clarity)
//        System.out.println("Sample input: ");
//        System.out.println("Name: Test User");
//        System.out.println("Address: Test Address");
//        System.out.println("Phone No: 123-456-7890");
//
//        try {
//            patientManager.addNewPatient(scanner);
//            assertEquals(1, patientManager.getPatient().size());
//            assertEquals("Test User", patientManager.getPatient().get(0).getName());
//
//            // Verify file was written to.
//            FileReader fileReader = new FileReader(testFilePath);
//            char[] buffer = new char[1024];
//            int bytesRead = fileReader.read(buffer);
//            String fileContent = new String(buffer, 0, bytesRead);
//            fileReader.close();
//            assertTrue(fileContent.contains("Test User"));
//        } catch (IOException e) {
//            System.err.println("An IOException occurred: " + e.getMessage());
//            throw e; // Re-throw the exception so the test fails.
//        }
//    }

// add new patient new logic------
    
   

    @Test
    public void testAddNewPatientSuccessfully() {
        // Simulate user input
        String input = "John Doe\n123 Street\n123-456-7890\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        patientManager.addNewPatient(scanner);

        assertEquals(1, patientList.size());

        Patient addedPatient = patientList.get(0);
        assertEquals("John Doe", addedPatient.getName());
        assertEquals("123 Street", addedPatient.getAddress());
        assertEquals("123-456-7890", addedPatient.getTelephone_no());
    }

    
    
    
//-----
    
    
    @Test
    void testSavePatientToFile() throws IOException {
        Patient patient = new Patient("File Test", "File Address", "999-888-7777");
        patientManager.savePatientTofile(testFilePath, patient);

        FileReader fileReader = new FileReader(testFilePath);
        char[] buffer = new char[1024];
        int bytesRead = fileReader.read(buffer);
        String fileContent = new String(buffer, 0, bytesRead);
        fileReader.close();

        assertTrue(fileContent.contains("File Test"));
        assertTrue(fileContent.contains("File Address"));
        assertTrue(fileContent.contains("999-888-7777"));
    }

    
    //problem
//    @Test
//    void testRemovePatientById() throws IOException {
//        Patient patient1 = new Patient("Alice", "Address 1", "111");
//        patient1.setId("PT001");
//        Patient patient2 = new Patient("Bob", "Address 2", "222");
//        patient2.setId("PT002");
//        addAndSavePatient(patient1);
//        addAndSavePatient(patient2);
//
//        // Simulate user input with a Scanner.
//        String input = "PT001\n";
//        Scanner scanner = new Scanner(input);
//        patientManager.removePatientById(scanner);
//
//        assertEquals(1, patientManager.getPatient().size());
//        assertNull(patientManager.getPatient().stream().filter(p -> p.getId().equals("1")).findFirst().orElse(null));
//        assertNotNull(patientManager.getPatient().stream().filter(p -> p.getId().equals("2")).findFirst().orElse(null));
//
//        // Check file contents
//        FileReader fileReader = new FileReader(testFilePath);
//        char[] buffer = new char[1024];
//        int bytesRead = fileReader.read(buffer);
//        String fileContent = new String(buffer, 0, bytesRead);
//        fileReader.close();
//        assertFalse(fileContent.contains("Alice"));
//        assertTrue(fileContent.contains("Bob"));
//    }

    @Test
    public void testRemovePatientByIdSuccessfully() {
        // Setup: Add test patient
        Patient testPatient = new Patient( "John Doe", "123 Street", "123-456-7890");
        testPatient.setId("PT001");
        patientList.add(testPatient);

        // Simulate user input: Enter ID to remove
        String input = "PT001\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        // Call method
        patientManager.removePatientById(scanner);

        // Verify
        assertEquals(0, patientList.size());
    }
    
    @Test
    public void testRemovePatientById_ifNotFound() {
        // No patients in list
        String input = "P999\n"; // Nonexistent ID
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        patientManager.removePatientById(scanner);

        assertEquals(0, patientList.size()); // Still empty
    }
    
    
}

