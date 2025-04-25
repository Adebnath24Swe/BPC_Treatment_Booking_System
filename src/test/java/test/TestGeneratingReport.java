import Clinic.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class TestGeneratingReport {

    private List<Appointment> allAppointments;
    private List<Physiotherapist> allPhysiotherapists;

    @BeforeEach
    public void setup() {
        // Setup mock data for testing
        allAppointments = new ArrayList<>();
        allPhysiotherapists = new ArrayList<>();
        
        // Add mock physiotherapists
        Physiotherapist physiotherapist = new Physiotherapist("Dr. Smith", "123 Physio St", "123-456-7890");
        allPhysiotherapists.add(physiotherapist);

        // Create a mock patient
        Patient patient = new Patient("John Doe", "Kolkata", "+235436547");
        Treatment mockTreatment = new Treatment("Massage");
        AreaOfExpertise demoExpertise = new AreaOfExpertise("Osteopathy");
        // Create a mock scheduled slot (using String for date)
        ScheduledSlot scheduledSlot = new ScheduledSlot(
            "1", 
            demoExpertise, 
            mockTreatment, 
            DayOfWeek.MONDAY, 
            LocalDate.now(),  // Convert LocalDate to String
            LocalTime.of(9, 0), 
            LocalTime.of(10, 0), 
            physiotherapist
        );

        // Create an appointment with the patient and scheduled slot
        Appointment appointment = new Appointment(patient, scheduledSlot);
        allAppointments.add(appointment);
    }

    @Test
    public void testGenerateReport() {
        // Call the generateReport method
        test.DemoDataOfReportGenerator.generateReport(allAppointments, allPhysiotherapists);
    }
}
