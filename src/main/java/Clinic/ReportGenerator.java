package Clinic;

 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.stream.Collectors;

 public class ReportGenerator {

  public static void generateMonthlyReport(List<Physiotherapist> physiotherapists, List<Appointment> appointments) {
   System.out.println("\n==================== Monthly Treatment Report ====================");

   for (Physiotherapist physio : physiotherapists) {
    System.out.println("\nPhysiotherapist: " + physio.getName() + " (ID: " + physio.getId() + ")");
    System.out.println("------------------------------------------------------------");

    List<Appointment> physioAppointments = appointments.stream()
        .filter(app -> app.getScheduledSlot().getAssignedPhysiotherapist().equals(physio)
                       /* Add month filtering if needed for a true monthly report */)
        .collect(Collectors.toList());

    if (physioAppointments.isEmpty()) {
     System.out.println("No appointments for this physiotherapist this month.");
    } else {
     System.out.println(String.format("%-25s %-20s %-30s %-10s", "Treatment Name", "Patient Name", "Time", "Status"));
     System.out.println(String.format("%-25s %-20s %-30s %-10s", "--------------", "------------", "----", "------"));
     for (Appointment appointment : physioAppointments) {
      System.out.printf("%-25s %-20s %-30s %-10s%n",
                        appointment.getScheduledSlot().getTreatment().getName(),
                        appointment.getPatient().getName(),
                        appointment.getFormattedDateTime(),
                        appointment.getStatus());
     }
    }
   }

   System.out.println("\n==================== Physiotherapist Ranking (by Attended Appointments) ====================");
   Map<Physiotherapist, Long> attendedCounts = new HashMap<>();

   for (Physiotherapist physio : physiotherapists) {
    long attendedCount = appointments.stream()
        .filter(app -> app.getScheduledSlot().getAssignedPhysiotherapist().equals(physio) &&
                       app.getStatus().equalsIgnoreCase("Attended")
                       /* Add month filtering if needed */)
        .count();
    attendedCounts.put(physio, attendedCount);
   }

   attendedCounts.entrySet().stream()
       .sorted(Map.Entry.<Physiotherapist, Long>comparingByValue().reversed())
       .forEach(entry -> System.out.println(entry.getKey().getName() + ": " + entry.getValue() + " attended appointments"));

   System.out.println("==========================================================================");
  }
 }