package Clinic;

import Manager.PatientManager;
import Manager.TimetableManager;
import DemoData.DemoSchedule;
import static DemoData.DemoSchedule.initializeExpertiseAndTreatments;
import Manager.AppointmentManager;
import Manager.PhysiotherapistManager;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.Comparator;

public class ClinicSystemTest {

    // Class level list of Physiotherapists.  Make it static and initialize directly.
    private static List<Physiotherapist> allPhysiotherapists = new ArrayList<>();
    private static final Map<String, Physiotherapist> physiotherapistIdMap = new HashMap<>(); // Map for IDs
    private static List<AreaOfExpertise> allExpertiseAreas = initializeExpertiseAndTreatments();
    
    private static List<Appointment> allAppointments = new ArrayList<>();
    
    
    //----------Static block goes here -----------------
    
    // this static block will execute first to load all data.
    static {
        Physiotherapist therapist1 = new Physiotherapist("Sarah Thompson", "Address 1", "111");
        therapist1.setId("PHY001");
        physiotherapistIdMap.put("PHY001", therapist1);

        Physiotherapist therapist2 = new Physiotherapist("Alex Reed", "Address 2", "222");
        therapist2.setId("PHY002");
        physiotherapistIdMap.put("PHY002", therapist2);

        Physiotherapist therapist3 = new Physiotherapist("Nina Patel", "Address 3", "333");
        therapist3.setId("PHY003");
        physiotherapistIdMap.put("PHY003", therapist3);

        Physiotherapist therapist4 = new Physiotherapist("James Walker", "Address 4", "444");
        therapist4.setId("PHY004");
        physiotherapistIdMap.put("PHY004", therapist4);

        Physiotherapist therapist5 = new Physiotherapist("Emma Lewis", "Address 5", "555");
        therapist5.setId("PHY005");
        physiotherapistIdMap.put("PHY005", therapist5);

        allPhysiotherapists.addAll(physiotherapistIdMap.values()); // Add them to the list as well.
    }
//-------------Main execution point of program--------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager pManager = new PatientManager();
        String patientFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/patient.txt";
        pManager.loadPatientFromFile(patientFilePath);
        
        
        // force jvm to print icon in console.
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Define Areas of Expertise and Treatments
        //List<AreaOfExpertise> allExpertiseAreas = initializeExpertiseAndTreatments();  // Moved to class level

        // Associate expertise and treatments with physiotherapists
        associateExpertiseWithPhysiotherapists();

        // Generate demo schedule
        List<ScheduledSlot> weeklySchedule = DemoSchedule.generateDemoSchedule();

        // Output the schedule for the first week
        //generatePhysiotherapistScheduleOutput(weeklySchedule);

        // The existing menu for patient management remains below
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
                case "2" -> physiotherapistMenu(scanner); // Call the new physiotherapist menu
                case "3" -> appointmentBookingMenu(scanner, pManager);
                case "4" -> generateReport();
                case "5" -> {
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    
    
    
    
    
    
    
    


    // Method to associate expertise with physiotherapists
    private static void associateExpertiseWithPhysiotherapists() {
        //List<AreaOfExpertise> allExpertiseAreas = initializeExpertiseAndTreatments(); // Get the previously created list //NO NEED TO CALL IT AGAIN

        physiotherapistIdMap.get("PHY001").addExpertise(allExpertiseAreas.get(0)); // Sarah Thompson - Physiotherapy
        physiotherapistIdMap.get("PHY001").addExpertise(allExpertiseAreas.get(2)); // Sarah Thompson - Rehabilitation

        physiotherapistIdMap.get("PHY002").addExpertise(allExpertiseAreas.get(0)); // Alex Reed - Physiotherapy

        physiotherapistIdMap.get("PHY003").addExpertise(allExpertiseAreas.get(1)); // Nina Patel - Osteopathy

        physiotherapistIdMap.get("PHY004").addExpertise(allExpertiseAreas.get(1)); // James Walker - Osteopathy
        physiotherapistIdMap.get("PHY004").addExpertise(allExpertiseAreas.get(2)); // James Walker - Rehabilitation

        physiotherapistIdMap.get("PHY005").addExpertise(allExpertiseAreas.get(2)); // Emma Lewis - Rehabilitation
    }

    

    

    //--------All sub menus goes here-------
    
  
    // -------------Patient Submenu--------
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

    // -------------Physiotherapist Submenu--------
    private static void physiotherapistMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Physiotherapist Management Menu ===");
            System.out.println("1. Display All Physiotherapists");
            System.out.println("2. Show timetable");
            System.out.println("3. Display Physiotherapist Details by ID");
            System.out.println("4. Generate Timetable for 4 week");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> displayAllPhysiotherapists();
                case "2" -> {
                associateExpertiseWithPhysiotherapists();

            
        // Generate demo schedule
        List<ScheduledSlot> weeklySchedule =  DemoSchedule.generateDemoSchedule();

        // Output the schedule for the first week
        DemoSchedule.generatePhysiotherapistScheduleOutput(weeklySchedule);
                
                }
                case "3" -> {
                    System.out.print("Enter Physiotherapist ID (e.g., Phy001): ");
                    String physioId = scanner.nextLine().trim();
                    PhysiotherapistManager.displayPhysiotherapistDetails(physioId);
                }
                case "4" -> {
                        TimetableManager timetableManager = new TimetableManager();

                        // Generate the timetable
                        timetableManager.generateTimetable();

                        // Display the timetable
                        timetableManager.displayTimetable();

                        // Save timetable to file
                        timetableManager.saveTimetableToFile();
                
                }
                case "5" -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


//-----Appointment Submenu----------
private static void appointmentBookingMenu(Scanner scanner, PatientManager pManager) {
  boolean back = false;
  while (!back) {
   System.out.println("\n=== Appointment Booking Menu (Admin/User) ===");
   System.out.println("1. Book Appointment by Physiotherapist");
   System.out.println("2. Book Appointment by Area of Expertise (Coming Soon)");
   System.out.println("3. View All Appointments");
   System.out.println("4. Delete Appointment by id");
   System.out.println("5. Update Booking Status");
   System.out.println("6. Back to Main Menu");
   System.out.print("Choose an option: ");
   String choice = scanner.nextLine().trim();

   switch (choice) {
    case "1" -> AppointmentManager.bookAppointmentByPhysiotherapistAdmin(scanner, pManager);
    case "2" -> AppointmentManager.bookAppointmentByExpertise(scanner, pManager);
    case "3" -> AppointmentManager.viewAllAppointments(); // Call the new method
    case "4" -> AppointmentManager.removeAppointment(scanner);
    case "5" -> AppointmentManager.updateBookingStatus(scanner);
    case "6" -> back = true;

    default -> System.out.println("Invalid option. Try again.");
   }
  }
 }
    
    
    
    //--------------new one to display
 
    
    
    //-------------
    // Method to display a specific physiotherapist's details, including assigned slots
 

 //display all therapist.

public static void displayAllPhysiotherapists() {
  System.out.println("\n--- All Physiotherapists ---");
  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
  DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

  // Sort the allPhysiotherapists list based on their IDs
  allPhysiotherapists.sort(Comparator.comparing(Physiotherapist::getId));

  for (Physiotherapist physio : allPhysiotherapists) {
   physio.displayInfo();

   List<ScheduledSlot> assignedSlots = physio.getAssignedSlots();
   if (assignedSlots != null && !assignedSlots.isEmpty()) {
    System.out.println("\n--- Assigned Time Slots for " + physio.getName() + " (ID: " + physio.getId() + ") ---");
    for (ScheduledSlot slot : assignedSlots) {
     System.out.printf("Slot ID: %-10s | Date: %-12s | Time: %-15s | Treatment: %-30s | Area: %-20s%n",
       slot.getId(),
       slot.getDate().format(dateFormatter),
       slot.getStartTime().format(timeFormatter) + " - " + slot.getEndTime().format(timeFormatter),
       slot.getTreatment().getName(),
       slot.getAreaOfExpertise().getName());
    }
   } else {
    System.out.println("\n" + physio.getName() + " (ID: " + physio.getId() + ") has no assigned time slots.");
   }
   System.out.println("---------------------------");
  }
 }



// display physiotherapist by id.

public static void displayPhysiotherapistDetails(String physioId) {
        //Physiotherapist foundPhysio = physiotherapistIdMap.get(physioId);
        Physiotherapist foundPhysio = physiotherapistIdMap.get(physioId.toUpperCase()); //changed to upper case.

        if (foundPhysio != null) {
            System.out.println("\n--- Physiotherapist Details ---");
            foundPhysio.displayInfo();

            List<ScheduledSlot> assignedSlots = foundPhysio.getAssignedSlots();
            if (assignedSlots != null && !assignedSlots.isEmpty()) {
                System.out.println("\n--- Assigned Time Slots for " + foundPhysio.getName() + " (ID: " + foundPhysio.getId() + ") ---");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

                for (ScheduledSlot slot : assignedSlots) {
                    System.out.printf("Slot ID: %-10s | Date: %-12s | Time: %-15s | Treatment: %-30s | Area: %-20s%n",
                            slot.getId(),
                            slot.getDate().format(dateFormatter),
                            slot.getStartTime().format(timeFormatter) + " - " + slot.getEndTime().format(timeFormatter),
                            slot.getTreatment().getName(),
                            slot.getAreaOfExpertise().getName());
                }
            } else {
                System.out.println("\n" + foundPhysio.getName() + " (ID: " + foundPhysio.getId() + ") has no assigned time slots.");
            }
        } else {
            System.out.println("Physiotherapist with ID " + physioId + " not found.");
        }
    }
    
 private static void generateReport() {
  ReportGenerator.generateMonthlyReport(allPhysiotherapists, allAppointments);
 }
 
 
}

