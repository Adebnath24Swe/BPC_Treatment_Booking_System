
package Clinic;

import Manager.PatientManager;
import java.util.Scanner;

/**
 *
 * @author Apu_PC
 */
public class Test {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager pManager = new PatientManager();
        String patientFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/patient.txt";
        pManager.loadPatientFromFile(patientFilePath);
        
        
        boolean running = true;

        while (running) {
            // Main menu
            System.out.println("\n=== BPC Booking System ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Physiotherapist Management");
            System.out.println("3. Appointment Booking");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            
            
            switch (choice) {
                case "1" -> patientMenu(pManager, scanner);
                case "2" ->  System.out.println("will be implemented later.");
                case "3" -> System.out.println("will be implemented later.");   
                case "4" -> System.out.println("will be implemented later.");
                case "5" -> {
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
            
        

        
    }
    
    
    
    private static void patientMenu(PatientManager pManager, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n=== Patient Management Menu ===");
            System.out.println("1. Display All Patients");
            System.out.println("2. Add New Patient");
            System.out.println("3. Remove Patient by ID");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> pManager.displayAllpatient();
                case "2" -> pManager.addNewPatient(scanner);
                case "3" -> pManager.removePatientById(scanner);
                case "4" -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

}



