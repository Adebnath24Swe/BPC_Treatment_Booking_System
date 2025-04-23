
package Manager;


import java.util.*;

public class TimetableManager {
    private static final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final Map<String, String> treatmentToExpertise = new HashMap<>();
    
    static {
        treatmentToExpertise.put("Massage", "Physiotherapy");
        treatmentToExpertise.put("Neural mobilisation", "Physiotherapy");
        treatmentToExpertise.put("Mobilisation of the spine and joints", "Osteopathy");
        treatmentToExpertise.put("Acupuncture", "Osteopathy");
        treatmentToExpertise.put("Pool rehabilitation", "Rehabilitation");
    }

    private List<TimeSlot> timetable = new ArrayList<>();
    
    // Set the starting date for the timetable (e.g., May 1st, 2025 is a Thursday)
    private Calendar startDate = new GregorianCalendar(2025, Calendar.MAY, 1); // May 1st, 2025 (Thursday)

    // Method to initialize the timetable based on the fixed treatment schedule
    public void generateTimetable() {
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        int slotCounter = 1;
        
        // Loop for each week
        for (int week = 1; week <= 4; week++) {
            // Loop for each day of the week
            for (String day : weekDays) {
                // Calculate the date for this slot
                String date = calculateDateForSlot(startDate, week, day);

                // Assign treatment slots based on the day and the fixed schedule
                if (day.equals("Sunday") || day.equals("Tuesday") || day.equals("Thursday")) {
                    // Massage - Slots 1, 2, 3
                    for (int i = 1; i <= 3; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Massage"));
                    }
                    // Acupuncture - Slots 4, 5
                    for (int i = 1; i <= 2; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Acupuncture"));
                    }
                    // Massage - Slots 6, 7, 8, 9
                    for (int i = 1; i <= 4; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Massage"));
                    }
                } else if (day.equals("Saturday") || day.equals("Monday") || day.equals("Wednesday")) {
                    // Mobilisation of the spine and joints - Slots 1, 2, 3
                    for (int i = 1; i <= 3; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Mobilisation of the spine and joints"));
                    }
                    // Acupuncture - Slots 4, 5
                    for (int i = 1; i <= 2; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Acupuncture"));
                    }
                    // Mobilisation of the spine and joints - Slots 6, 7, 8, 9
                    for (int i = 1; i <= 4; i++) {
                        timetable.add(createSlot(week, day, slotCounter++, date, "Mobilisation of the spine and joints"));
                    }
                } else if (day.equals("Friday")) {
                    // Pool rehabilitation, Massage, and Neural mobilisation for Friday
                    timetable.add(createSlot(week, day, slotCounter++, date, "Pool rehabilitation"));
                    timetable.add(createSlot(week, day, slotCounter++, date, "Massage"));
                    timetable.add(createSlot(week, day, slotCounter++, date, "Neural mobilisation"));
                    timetable.add(createSlot(week, day, slotCounter++, date, "Massage"));
                    timetable.add(createSlot(week, day, slotCounter++, date, "Neural mobilisation"));
                    timetable.add(createSlot(week, day, slotCounter++, date, "Massage"));
                }
            }
        }
    }

    // Helper method to create a time slot for a given week, day, and treatment
    private TimeSlot createSlot(int week, String day, int slotID, String date, String treatment) {
        String slotIDStr = "W" + week + "S" + slotID;
        String time = getTimeForSlot(slotID);
        String areaOfExpertise = treatmentToExpertise.get(treatment);
        return new TimeSlot(slotIDStr, day, date, time, treatment, areaOfExpertise);
    }

    // Method to return time for the slot
    private String getTimeForSlot(int slotID) {
        int hour = 9 + (slotID - 1) / 2;
        int minute = (slotID % 2 == 1) ? 0 : 30;
        return String.format("%02d:%02d–%02d:%02d", hour, minute, hour, minute + 30);
    }

    // Calculate the date for the given day in the current week
    private String calculateDateForSlot(Calendar startDate, int week, String day) {
        // Move the calendar to the correct day in the week (taking into account the first day is May 1st)
        Calendar tempDate = (Calendar) startDate.clone();
        int dayOfWeek = getDayOfWeekNumber(day);
        int diff = dayOfWeek - tempDate.get(Calendar.DAY_OF_WEEK);

        // Move to the first day of the required week
        tempDate.add(Calendar.DATE, diff + (week - 1) * 7);
        
        // Format the date (e.g., May 1st, May 2nd)
        return String.format("%1$tB %1$td, %1$tY", tempDate);
    }

    // Helper method to map day name to Calendar's day of week
    private int getDayOfWeekNumber(String day) {
        switch (day) {
            case "Sunday": return Calendar.SUNDAY;
            case "Monday": return Calendar.MONDAY;
            case "Tuesday": return Calendar.TUESDAY;
            case "Wednesday": return Calendar.WEDNESDAY;
            case "Thursday": return Calendar.THURSDAY;
            case "Friday": return Calendar.FRIDAY;
            case "Saturday": return Calendar.SATURDAY;
            default: return Calendar.SUNDAY;
        }
    }

    // Method to display the timetable
    public void displayTimetable() {
        for (TimeSlot slot : timetable) {
            System.out.println(slot);
        }
    }

    // Method to save timetable to a file (example: timetable.txt)
    public void saveTimetableToFile() {
        try (java.io.FileWriter writer = new java.io.FileWriter("D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/timetable2.txt")) {
            for (TimeSlot slot : timetable) {
                writer.write(slot.toString() + "\n");
            }
            System.out.println("Timetable saved to timetable.txt.");
        } catch (java.io.IOException e) {
            System.err.println("Error saving timetable: " + e.getMessage());
        }
    }
}

