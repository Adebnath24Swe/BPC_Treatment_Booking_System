package test;


import Clinic.AreaOfExpertise;
import Clinic.Patient;
import Clinic.Physiotherapist;
import Clinic.Treatment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAppointment {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static List<Appointment> allAppointments; // Assuming this is in the same class as viewAllAppointments

    // Inner class representing the Appointment for testing purposes
    public static class Appointment {
        private Patient patient;
        private String appointmentTime;

        public Appointment(Patient patient, String appointmentTime) {
            this.patient = patient;
            this.appointmentTime = appointmentTime;
        }

        public Patient getPatient() {
            return patient;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void displayAppointmentDetails() {
            System.out.println("Patient: " + patient.getName() + ", Time: " + appointmentTime);
        }
    }

    // Inner class representing the Members (for Patient to extend) - minimal for this test
    public static class Members {
        private String name;
        private String address;
        private String telephone;
        private String id;
        protected static int counterOfId = 1;

        public Members(String name, String address, String telephone) {
            this.name = name;
            this.address = address;
            this.telephone = telephone;
            this.id = generateId();
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String generateId() {
            return "MB" + String.format("%03d", counterOfId++);
        }
    }

    // Assuming viewAllAppointments is a static method in this TestAppointment class
    private static void viewAllAppointments() {
        System.out.println("\n--- All Booked Appointments ---");
        if (allAppointments.isEmpty()) {
            System.out.println("No appointments have been booked yet.");
        } else {
            for (Appointment appointment : allAppointments) {
                appointment.displayAppointmentDetails();
            }
        }
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        allAppointments = new ArrayList<>(); // Reset the list before each test
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        allAppointments.clear(); // Clean up after each test
    }

    @Test
    void testViewAllAppointments_noAppointments() {
        // Arrange: allAppointments is empty

        // Act: Call the method
        viewAllAppointments();

        // Assert: Check the output, normalizing line endings and trimming whitespace
        String expectedOutput = "\n--- All Booked Appointments ---\nNo appointments have been booked yet.\n";
        String actualOutput = outputStreamCaptor.toString();

        assertEquals(
            expectedOutput.replace("\r\n", "\n").trim(),
            actualOutput.replace("\r\n", "\n").trim()
        );
    }


    @Test
    void testViewAllAppointments() {
        // Arrange: Create Patients and Appointments, and add them to the list
        Patient patient1 = new Patient("Bob Johnson", "456 Oak Ave", "0987654321");
        Appointment appointment1 = new Appointment(patient1, "2025-05-02 11:00");
        Patient patient2 = new Patient("Charlie Brown", "789 Pine Ln", "0112233445");
        Appointment appointment2 = new Appointment(patient2, "2025-05-03 12:00");
        allAppointments.add(appointment1);
        allAppointments.add(appointment2);

        // Act: Call the method
        viewAllAppointments();

        // Assert: Check the output, normalizing line endings and trimming whitespace
        String expectedOutput = "\n--- All Booked Appointments ---\nPatient: Bob Johnson, Time: 2025-05-02 11:00\nPatient: Charlie Brown, Time: 2025-05-03 12:00\n";
        String actualOutput = outputStreamCaptor.toString();

        assertEquals(
            expectedOutput.replace("\r\n", "\n").trim(),
            actualOutput.replace("\r\n", "\n").trim()
        );
    }
    
    
    
}
        
