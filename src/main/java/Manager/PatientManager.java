
package Manager;

import Clinic.Patient;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class PatientManager {
    private ArrayList<Patient> patientList= new ArrayList<>();
    
    public void loadPatientFromFile(String file){
        
        try{
            
           BufferedReader reader = new BufferedReader(new FileReader(file));
           String line;
           
           while((line=reader.readLine())!= null){
             String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String address = parts[2].trim();
                    String phone = parts[3].trim();

                    Patient p = new Patient(name, address, phone);
                    p.setId(id);
                    patientList.add(p);
                }
           }
            reader.close();
            //System.out.println("Patient's List loaded successfully.");
            System.out.println("");
           
        }catch(IOException e){
            System.out.println("Error message: "+ e.getMessage());
        }
    }
    
    public void displayAllpatient(){
      if(patientList.isEmpty()){
          System.out.println("Oops!! No patient data found!!");
      }else{
          for(Patient pa: patientList){
             pa.displayInfo();
          }
      }
    }
    
    public ArrayList<Patient> getPatient(){
      return patientList;
    }
    
    
    // Add patient data to patient list and save to file.
    public void addNewPatient(Scanner scanner){
    
        
        Patient newPatient;
        
        System.out.println("======== Add new patient ========");
        
        //name read from user input
        System.out.println("Enter patient's name: ");
        String name = scanner.nextLine();
        
        //address read from user input
        System.out.println("Enter patient's address: ");
        String address = scanner.nextLine();
        
        //telephone no read from user input
        System.out.println("Enter patient's telephone no: ");
        String telephone = scanner.nextLine();
        
        //Creating new object of Patient class.
        
        newPatient = new Patient(name, address, telephone);
        
        
        //Add this new patient object to existing arrayList of patient
        //added patient's data to patient's list
        patientList.add(newPatient);
        //save patient data to file
        String path = "D:/Programming/Java/PojectUOH-1/BPC_Treatment_Booking_System/src/main/java/Data/patient.txt";
        savePatientTofile(path, newPatient);
        
        System.out.println("Patient's added successfully to patient's list.");
        System.out.println("--------");
        
        
        System.out.println("-----------------------------------------------");
        System.out.println("Newly added patient information: ");
        newPatient.displayInfo();
        System.out.println("----------------------------------");
        
        
    }

    
 // Patient data saving to txt file.   
 public void savePatientTofile(String filePath, Patient p) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
        writer.write(p.getId()+","+p.getName() + "," + p.getAddress() + "," + p.getTelephone_no());
        writer.newLine(); // adds a new line
        
        System.out.println("--------------------------------------");
        
        System.out.println("Patient data is saved successfully to patient.txt file. ");
        
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}
 
 
 //Remove patient by it's id:
 
 public void removePatientById(Scanner scanner) {
    System.out.print("Enter Patient ID to remove: ");
    String idToRemove = scanner.nextLine().trim();

    boolean removed = patientList.removeIf(p -> p.getId().equalsIgnoreCase(idToRemove));

    if (removed) {
        rewriteAllPatientsToFile("D:/Programming/Java/ProjectUOH/BPC_BookingSystem/src/main/java/clinic/patient.txt");
        System.out.println("Patient with ID " + idToRemove + " removed.");
    } else {
        System.out.println("No patient found with ID: " + idToRemove);
    }
}

 
 //All patient id rewrite
public void rewriteAllPatientsToFile(String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (Patient p : patientList) {
            writer.write(p.getId() + "," + p.getName() + "," + p.getAddress() + "," + p.getTelephone_no());
            writer.newLine();
        }
    } catch (IOException e) {
        System.out.println("Error updating patient file: " + e.getMessage());
    }
}


}
