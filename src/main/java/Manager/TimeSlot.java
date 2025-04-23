
package Manager;

public class TimeSlot {
    private String slotID;
    private String day;
    private String date;  // Added date
    private String time;
    private String treatment;
    private String areaOfExpertise;
    private String doctor;
    private String patient;
    private String status;

    public TimeSlot(String slotID, String day, String date, String time, String treatment, String areaOfExpertise) {
        this.slotID = slotID;
        this.day = day;
        this.date = date;  // Set the date
        this.time = time;
        this.treatment = treatment;
        this.areaOfExpertise = areaOfExpertise;
        this.doctor = "None";
        this.patient = "None";
        this.status = "Available";
    }

    public String getSlotID() {
        return slotID;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;  // Return date
    }

    public String getTime() {
        return time;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] 📅 %s | 🗓️ %s | 🕒 %s | 🧴 %s | 🧠 %s | 👨‍⚕️ %s | 👤 %s | %s",
                slotID, day, date, time, treatment, areaOfExpertise, doctor, patient, status);
    }
}

