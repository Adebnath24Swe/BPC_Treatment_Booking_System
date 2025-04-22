package Clinic;

import Manager.PatientManager;
import Manager.TimetableManager;
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

public class Test {

    // Class level list of Physiotherapists.  Make it static and initialize directly.
    private static List<Physiotherapist> allPhysiotherapists = new ArrayList<>();
    private static final Map<String, Physiotherapist> physiotherapistIdMap = new HashMap<>(); // Map for IDs
    private static List<AreaOfExpertise> allExpertiseAreas = initializeExpertiseAndTreatments();

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager pManager = new PatientManager();
        String patientFilePath = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/patient.txt";
        pManager.loadPatientFromFile(patientFilePath);
        
        
        
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
        List<ScheduledSlot> weeklySchedule = generateDemoSchedule();

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

    // Method to define expertise and treatments
    private static List<AreaOfExpertise> initializeExpertiseAndTreatments() {
        AreaOfExpertise physiotherapy = new AreaOfExpertise("Physiotherapy");
        Treatment neuralMobilisation = new Treatment("Neural Mobilisation");
        Treatment massagePhysio = new Treatment("Massage (Physiotherapy)");
        physiotherapy.addTreatment(neuralMobilisation);
        physiotherapy.addTreatment(massagePhysio);

        AreaOfExpertise osteopathy = new AreaOfExpertise("Osteopathy");
        Treatment mobilisationSpineJoints = new Treatment("Mobilisation of Spine/Joints");
        Treatment acupuncture = new Treatment("Acupuncture");
        osteopathy.addTreatment(mobilisationSpineJoints);
        osteopathy.addTreatment(acupuncture);

        AreaOfExpertise rehabilitation = new AreaOfExpertise("Rehabilitation");
        Treatment poolRehabilitation = new Treatment("Pool Rehabilitation");
        Treatment massageRehab = new Treatment("Massage (Rehabilitation)");
        rehabilitation.addTreatment(poolRehabilitation);
        rehabilitation.addTreatment(massageRehab);

        return Arrays.asList(physiotherapy, osteopathy, rehabilitation);
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

    private static List<ScheduledSlot> generateDemoSchedule() {
        List<ScheduledSlot> schedule = new ArrayList<>();
        LocalDate startOfWeek = LocalDate.of(2025, 5, 4); // Start date: Sunday, May 4, 2025

        // Get references to the physiotherapists and treatments
        Physiotherapist sarahThompson = physiotherapistIdMap.get("PHY001");
        Physiotherapist alexReed = physiotherapistIdMap.get("PHY002");
        Physiotherapist ninaPatel = physiotherapistIdMap.get("PHY003");
        Physiotherapist emmaLewis = physiotherapistIdMap.get("PHY005");
        Physiotherapist jamesWalker = physiotherapistIdMap.get("PHY004");

        AreaOfExpertise physiotherapy = null;
        AreaOfExpertise osteopathy = null;
        AreaOfExpertise rehabilitation = null;

        for (AreaOfExpertise area : allExpertiseAreas) {
            if (area.getName().equals("Physiotherapy")) {
                physiotherapy = area;
            } else if (area.getName().equals("Osteopathy")) {
                osteopathy = area;
            } else if (area.getName().equals("Rehabilitation")) {
                rehabilitation = area;
            }
        }

        Treatment neuralMobilisation = null;
        Treatment massagePhysio = null;
        Treatment acupuncture = null;
        Treatment mobilisationSpineJoints = null;
        Treatment poolRehabilitation = null;
        Treatment massageRehab = null;

        if (physiotherapy != null) {
            for (Treatment treatment : physiotherapy.getTreatments()) {
                if (treatment.getName().equals("Neural Mobilisation")) {
                    neuralMobilisation = treatment;
                } else if (treatment.getName().equals("Massage (Physiotherapy)")) {
                    massagePhysio = treatment;
                }
            }
        }
        if (osteopathy != null) {
            for (Treatment treatment : osteopathy.getTreatments()) {
                 if (treatment.getName().equals("Acupuncture")) {
                    acupuncture = treatment;
                }
                else if (treatment.getName().equals("Mobilisation of Spine/Joints")) {
                    mobilisationSpineJoints = treatment;
                }
            }
        }

        if (rehabilitation != null) {
            for (Treatment treatment : rehabilitation.getTreatments()) {
                if (treatment.getName().equals("Pool Rehabilitation")) {
                    poolRehabilitation = treatment;
                } else if (treatment.getName().equals("Massage (Rehabilitation)")) {
                    massageRehab = treatment;
                }
            }
        }

        // Create the scheduled slots directly
        schedule.add(new ScheduledSlot("sun-w1-001", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("09:00"), LocalTime.parse("10:00"), sarahThompson));
        schedule.add(new ScheduledSlot("sun-w1-002", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("10:00"), LocalTime.parse("11:00"), alexReed));
        schedule.add(new ScheduledSlot("sun-w1-003", osteopathy, acupuncture, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("11:00"), LocalTime.parse("12:00"), ninaPatel));
        schedule.add(new ScheduledSlot("sun-w1-004", rehabilitation, poolRehabilitation, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("12:00"), LocalTime.parse("13:00"), emmaLewis));
        schedule.add(new ScheduledSlot("sun-w1-005", physiotherapy, massagePhysio, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("13:00"), LocalTime.parse("14:00"), sarahThompson));
        schedule.add(new ScheduledSlot("sun-w1-006", osteopathy, mobilisationSpineJoints, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("14:00"), LocalTime.parse("15:00"), jamesWalker));
        schedule.add(new ScheduledSlot("sun-w1-007", rehabilitation, massageRehab, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("15:00"), LocalTime.parse("16:00"), emmaLewis));
        schedule.add(new ScheduledSlot("sun-w1-008", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, startOfWeek, LocalTime.parse("16:00"), LocalTime.parse("17:00"), alexReed));

        schedule.add(new ScheduledSlot("mon-w1-009", physiotherapy, massagePhysio, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
        schedule.add(new ScheduledSlot("mon-w1-010", osteopathy, mobilisationSpineJoints, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
        schedule.add(new ScheduledSlot("mon-w1-011", rehabilitation, poolRehabilitation, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
        schedule.add(new ScheduledSlot("mon-w1-012", physiotherapy, neuralMobilisation, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
        schedule.add(new ScheduledSlot("mon-w1-013", osteopathy, acupuncture, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
        schedule.add(new ScheduledSlot("mon-w1-014", rehabilitation, massageRehab, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
        schedule.add(new ScheduledSlot("mon-w1-015", physiotherapy, massagePhysio, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
        schedule.add(new ScheduledSlot("mon-w1-016", osteopathy, mobilisationSpineJoints, DayOfWeek.MONDAY, startOfWeek.plusDays(1), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));

        
        
        schedule.add(new ScheduledSlot("tue-w1-017", physiotherapy, massagePhysio, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
        schedule.add(new ScheduledSlot("tue-w1-018", osteopathy, mobilisationSpineJoints, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
        schedule.add(new ScheduledSlot("tue-w1-019", rehabilitation, poolRehabilitation, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
        schedule.add(new ScheduledSlot("tue-w1-020", physiotherapy, neuralMobilisation, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
        schedule.add(new ScheduledSlot("tue-w1-021", osteopathy, acupuncture, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
        schedule.add(new ScheduledSlot("tue-w1-022", rehabilitation, massageRehab, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
        schedule.add(new ScheduledSlot("tue-w1-023", physiotherapy, massagePhysio, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
        schedule.add(new ScheduledSlot("tue-w1-024", osteopathy, mobilisationSpineJoints, DayOfWeek.TUESDAY, startOfWeek.plusDays(2), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));
        
        
        schedule.add(new ScheduledSlot("wed-w1-025", physiotherapy, neuralMobilisation, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("09:00"), LocalTime.parse("10:00"), sarahThompson));
        schedule.add(new ScheduledSlot("wed-w1-026", physiotherapy, neuralMobilisation, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("10:00"), LocalTime.parse("11:00"), alexReed));
        schedule.add(new ScheduledSlot("wed-w1-027", osteopathy, acupuncture, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("11:00"), LocalTime.parse("12:00"), ninaPatel));
        schedule.add(new ScheduledSlot("wed-w1-028", rehabilitation, poolRehabilitation, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("12:00"), LocalTime.parse("13:00"), emmaLewis));
        schedule.add(new ScheduledSlot("wed-w1-029", physiotherapy, massagePhysio, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("13:00"), LocalTime.parse("14:00"), sarahThompson));
        schedule.add(new ScheduledSlot("wed-w1-030", osteopathy, mobilisationSpineJoints, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("14:00"), LocalTime.parse("15:00"), jamesWalker));
        schedule.add(new ScheduledSlot("wed-w1-031", rehabilitation, massageRehab, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("15:00"), LocalTime.parse("16:00"), emmaLewis));
        schedule.add(new ScheduledSlot("Wed-w1-032", physiotherapy, neuralMobilisation, DayOfWeek.WEDNESDAY, startOfWeek.plusDays(3), LocalTime.parse("16:00"), LocalTime.parse("17:00"), alexReed));
        
        
        schedule.add(new ScheduledSlot("thu-w1-033", physiotherapy, massagePhysio, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
        schedule.add(new ScheduledSlot("thu-w1-034", osteopathy, mobilisationSpineJoints, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
        schedule.add(new ScheduledSlot("thu-w1-035", rehabilitation, poolRehabilitation, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
        schedule.add(new ScheduledSlot("thu-w1-036", physiotherapy, neuralMobilisation, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
        schedule.add(new ScheduledSlot("thu-w1-037", osteopathy, acupuncture, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
        schedule.add(new ScheduledSlot("thu-w1-038", rehabilitation, massageRehab, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
        schedule.add(new ScheduledSlot("thu-w1-039", physiotherapy, massagePhysio, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
        schedule.add(new ScheduledSlot("thu-w1-040", osteopathy, mobilisationSpineJoints, DayOfWeek.THURSDAY, startOfWeek.plusDays(4), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));
        
        schedule.add(new ScheduledSlot("fri-w1-041", physiotherapy, neuralMobilisation, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("09:00"), LocalTime.parse("10:00"), sarahThompson));
        schedule.add(new ScheduledSlot("fri-w1-042", physiotherapy, neuralMobilisation, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("10:00"), LocalTime.parse("11:00"), alexReed));
        schedule.add(new ScheduledSlot("fri-w1-043", osteopathy, acupuncture, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("11:00"), LocalTime.parse("12:00"), ninaPatel));
        schedule.add(new ScheduledSlot("fri-w1-044", rehabilitation, poolRehabilitation, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("12:00"), LocalTime.parse("13:00"), emmaLewis));
        schedule.add(new ScheduledSlot("fri-w1-045", physiotherapy, massagePhysio, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("13:00"), LocalTime.parse("14:00"), sarahThompson));
        schedule.add(new ScheduledSlot("fri-w1-046", osteopathy, mobilisationSpineJoints, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("14:00"), LocalTime.parse("15:00"), jamesWalker));
        schedule.add(new ScheduledSlot("fri-w1-047", rehabilitation, massageRehab, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("15:00"), LocalTime.parse("16:00"), emmaLewis));
        schedule.add(new ScheduledSlot("fri-w1-048", physiotherapy, neuralMobilisation, DayOfWeek.FRIDAY, startOfWeek.plusDays(5), LocalTime.parse("16:00"), LocalTime.parse("17:00"), alexReed));

        schedule.add(new ScheduledSlot("sat-w1-048", physiotherapy, massagePhysio, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
        schedule.add(new ScheduledSlot("sat-w1-049", osteopathy, mobilisationSpineJoints, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
        schedule.add(new ScheduledSlot("sat-w1-050", rehabilitation, poolRehabilitation, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
        schedule.add(new ScheduledSlot("sat-w1-051", physiotherapy, neuralMobilisation, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
        schedule.add(new ScheduledSlot("sat-w1-052", osteopathy, acupuncture, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
        schedule.add(new ScheduledSlot("sat-w1-053", rehabilitation, massageRehab, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
        schedule.add(new ScheduledSlot("sat-w1-054", physiotherapy, massagePhysio, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
        schedule.add(new ScheduledSlot("sat-w1-055", osteopathy, mobilisationSpineJoints, DayOfWeek.SATURDAY, startOfWeek.plusDays(6), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));
        
        
        // Assign the slots to the physiotherapists
        for (ScheduledSlot slot : schedule) {
            if (slot.getAssignedPhysiotherapist() != null) {
                slot.getAssignedPhysiotherapist().addAssignedSlot(slot);
            }
        }
        return schedule;
    }

    private static void generatePhysiotherapistScheduleOutput(List<ScheduledSlot> weeklySchedule) {
        System.out.println("Week 1 – Physiotherapist Assignment Schedule (May 2025)\n");
        System.out.println("Physiotherapists & Expertise:");
        for (Physiotherapist phy : allPhysiotherapists) {
            String expertise = phy.getExpertiseList().stream()
                    .map(AreaOfExpertise::getName)
                    .collect(java.util.stream.Collectors.joining(", "));
            System.out.printf("ID(%s)- %-30s | %s%n", phy.getId(), phy.getName(), expertise);
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-15s| %-25s| %-30s| %-11s| %-12s| %-23s| %s",
                "ID", "Area of Expertise", "Treatment", "Day", "Date", "Time Slot", "Assigned Physiotherapist (ID)"));
        System.out.println("-------------|---------------------------|-----------------------------|------------|-------------|------------------------|-------------------------------");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
        LocalDate currentDate = null; // Keep track of the current date
        for (ScheduledSlot slot : weeklySchedule) {
            if (currentDate == null || !slot.getDate().equals(currentDate)) {
                if (currentDate != null) {
                    System.out.println(); // Add a blank line before the next day
                }
                currentDate = slot.getDate();
            }
            String startTimeStr = slot.getStartTime().format(timeFormatter);
            String endTimeStr = slot.getEndTime().format(timeFormatter);
            String physioName = slot.getAssignedPhysiotherapist() != null ? slot.getAssignedPhysiotherapist().getName() : "Unassigned";
            String physioId = slot.getAssignedPhysiotherapist() != null ? slot.getAssignedPhysiotherapist().getId() : "";

            // Debugging output to check the assigned physiotherapist.
            System.out.printf("%-15s| %-25s| %-30s| %-11s| %-12s| %-23s| %s (%s)%n",
                    slot.getId(),
                    slot.getAreaOfExpertise().getName(),
                    slot.getTreatment().getName(),
                    slot.getDayOfWeek(),
                    slot.getDate().format(dateFormatter),
                    startTimeStr + " – " + endTimeStr,
                    physioName,
                    physioId);
        }
    }

    //--------All sub menus goes here-------
    
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

    // New method for Physiotherapist Management Submenu
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
        List<ScheduledSlot> weeklySchedule = generateDemoSchedule();

        // Output the schedule for the first week
        generatePhysiotherapistScheduleOutput(weeklySchedule);
                
                }
                case "3" -> {
                    System.out.print("Enter Physiotherapist ID (e.g., Phy001): ");
                    String physioId = scanner.nextLine().trim();
                    displayPhysiotherapistDetails(physioId);
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


//-----Appointment menu----------
    //    public static void appointmentMenu(AppointmentManager am, List<Patient> patients, List<Pysiotherapist> physios, Scanner scanner) {
//    boolean back = false;
//    while (!back) {
//        System.out.println("\n--- 📅 Appointment Booking Menu ---");
//        System.out.println("1. Book by Physiotherapist's name");
//        System.out.println("2. Book by Expert areas");
//        
//        System.out.println("3. View All Appointments");
//        System.out.println("4. Back to Main Menu");
//        System.out.print("Choose an option: ");
//        String choice = scanner.nextLine();
//
//        switch (choice) {
//            case "1" -> am.bookByPhysiotherapistName(patients, physios, scanner);
//            case "2" -> System.out.println("Booking by expertise");
//            case "3" -> am.showAllAppointments();
//            case "4" -> back = true;
//            default -> System.out.println("Ops!! Invalid option. Try again.");
//        }
//    }
//}
    
    
    
    // Method to display all physiotherapists
//    private static void displayAllPhysiotherapists() {
//        System.out.println("\n--- All Physiotherapists ---");
//        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
//
//        for (Physiotherapist physio : allPhysiotherapists) {
//            physio.displayInfo();
//
//            List<ScheduledSlot> assignedSlots = physio.getAssignedSlots();
//            if (assignedSlots != null && !assignedSlots.isEmpty()) {
//                System.out.println("\n--- Assigned Time Slots for " + physio.getName() + " (ID: " + physio.getId() + ") ---");
//                for (ScheduledSlot slot : assignedSlots) {
//                    System.out.printf("Slot ID: %-10s | Date: %-12s | Time: %-15s | Treatment: %-30s | Area: %-20s%n",
//                            slot.getId(),
//                            slot.getDate().format(dateFormatter),
//                            slot.getStartTime().format(timeFormatter) + " - " + slot.getEndTime().format(timeFormatter),
//                            slot.getTreatment().getName(),
//                            slot.getAreaOfExpertise().getName());
//                }
//            } else {
//                System.out.println("\n" + physio.getName() + " (ID: " + physio.getId() + ") has no assigned time slots.");
//            }
//            System.out.println("---------------------------");
//        }
    //}

    
    //--------------new one to display
    private static void displayAllPhysiotherapists() {
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
    
    
    //-------------
    // Method to display a specific physiotherapist's details, including assigned slots
    private static void displayPhysiotherapistDetails(String physioId) {
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
}

