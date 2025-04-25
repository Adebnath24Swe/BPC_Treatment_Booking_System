/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Clinic.Appointment;
import Clinic.Physiotherapist;
import java.util.List;

/**
 *
 * @author Apu_PC
 */
public class DemoDataOfReportGenerator {
    





    
    // Method to generate the report based on the available data
    public static void generateReport(List<Appointment> allAppointments, List<Physiotherapist> allPhysiotherapists) {
        System.out.println("Generating Report...");
        
        // Example logic: print out all appointment details
        for (Appointment appointment : allAppointments) {
            appointment.displayAppointmentDetails();  // Display details of each appointment
        }

        // If you have physiotherapists to include in the report, do it here
        for (Physiotherapist physiotherapist : allPhysiotherapists) {
            System.out.println("Physiotherapist: " + physiotherapist.getName());
            // You can add more details about physiotherapists here if needed
        }
    }
}