package Clinic;

import java.time.LocalDateTime;

public class TimeSlot {

    private static int slotCounter = 1;

    private int slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String treatment;
    private String expertiseArea;
    private Physiotherapist doctor;
    private boolean booked;
    private Appointment appointment;

   public TimeSlot(LocalDateTime start, LocalDateTime end, String treatment, String expertiseArea, Physiotherapist doctor) {
        this.slotId = slotCounter++;
        this.startTime = start;
        this.endTime = end;
        this.treatment = treatment;
        this.expertiseArea = expertiseArea;
        this.doctor = doctor;
        this.booked = false;
        this.appointment = null;
    }

    public int getSlotId() {
        return slotId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getExpertiseArea() {
        return expertiseArea;
    }

    public Physiotherapist getDoctor() {
        return doctor;
    }

    public boolean isBooked() {
        return booked;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void book(Appointment appointment) {
        this.booked = true;
        this.appointment = appointment;
    }

    public void cancel() {
        this.booked = false;
        this.appointment = null;
    }

    public void displaySlot() {
        String status = booked ? "🔒 Booked by 👤 " + appointment.getPatient().getName()
                               : "🔓 Available";

        System.out.printf("[slot-%03d] 📅 %-10s | 🕒 %s–%s | 🧴 %-35s | 🧠 %-15s | 👨‍⚕️ %-12s | %s\n",
                slotId,
                startTime.toLocalDate(),
                startTime.toLocalTime(),
                endTime.toLocalTime(),
                treatment,
                expertiseArea,
                doctor.getName(),
                status
        );
    }
}
