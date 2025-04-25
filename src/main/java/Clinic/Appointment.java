package Clinic;

 import java.time.LocalDateTime;
 import java.time.format.DateTimeFormatter;
 import java.util.Locale; // Import for formatting day and month

 public class Appointment {
  private static int appointmentCounter = 1;
  private int appointmentId;
  private Patient patient;
  private ScheduledSlot scheduledSlot;
  private LocalDateTime bookingDateTime;
  private String status; // Added status field

  public Appointment(Patient patient, ScheduledSlot scheduledSlot) {
   this.appointmentId = appointmentCounter++;
   this.patient = patient;
   this.scheduledSlot = scheduledSlot;
   this.bookingDateTime = LocalDateTime.now();
   this.status = "Booked"; // Default status
  }

  public int getAppointmentId() {
   return appointmentId;
  }

  public Patient getPatient() {
   return patient;
  }

  public ScheduledSlot getScheduledSlot() {
   return scheduledSlot;
  }

  public LocalDateTime getBookingDateTime() {
   return bookingDateTime;
  }

  public String getStatus() {
   return status;
  }

  public void setStatus(String status) {
   this.status = status;
  }

  public String getFormattedDateTime() {
  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.ENGLISH);
  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
  return scheduledSlot.getDate().format(dateFormatter) + ", " +
         scheduledSlot.getStartTime().format(timeFormatter) + "-" +
         scheduledSlot.getEndTime().format(timeFormatter);
 }

  public void displayAppointmentDetails() {
   DateTimeFormatter bookingDateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd-MMM-yyyy HH:mm");
   System.out.println("Appointment ID: " + appointmentId);
   System.out.println("Patient: " + patient.getName() + " (ID: " + patient.getId() + ")");
   System.out.println("Physiotherapist: " + scheduledSlot.getAssignedPhysiotherapist().getName() + " (ID: " + scheduledSlot.getAssignedPhysiotherapist().getId() + ")");
   System.out.println("Treatment: " + scheduledSlot.getTreatment().getName());
   System.out.println("Date and Time: " + getFormattedDateTime()); // Using the new format
   System.out.println("Booking Status: " + status); // Displaying the status
   System.out.println("Booked on: " + bookingDateTime.format(bookingDateTimeFormatter));
   System.out.println("--------------------");
  }
 }