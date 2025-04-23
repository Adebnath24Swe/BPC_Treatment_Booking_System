package Clinic;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Physiotherapist extends Members {

    private List<AreaOfExpertise> expertiseList = new ArrayList<>();
    private List<ScheduledSlot> assignedSlots = new ArrayList<>(); // To store assigned slots

    public Physiotherapist(String name, String address, String telephone) {
        super(name, address, telephone);
        //setId(generateId());
    }

    @Override
    public String generateId() {
        return "PHY" + String.format("%03d", counterOfId++); // Unique prefix for physiotherapists
    }

     public String setId(String id){
     return this.getId();
    }

    public void addExpertise(AreaOfExpertise expertise) {
        this.expertiseList.add(expertise);
    }

    public List<AreaOfExpertise> getExpertiseList() {
        return expertiseList;
    }

    public List<ScheduledSlot> getAssignedSlots() {
        return assignedSlots;
    }

    public void addAssignedSlot(ScheduledSlot slot) {
        this.assignedSlots.add(slot);
    }

    // ADD THIS METHOD:
    public boolean offersTreatment(String treatmentName) {
        for (AreaOfExpertise expertise : expertiseList) {
            if (expertise.hasTreatment(treatmentName)) {
                return true;
            }
        }
        return false;
    }
    ////-------------
//     @Override
//    public void displayInfo() {
//        super.displayInfo();
//        System.out.println("Expertise: " + expertiseList.stream()
//                .map(AreaOfExpertise::getName)
//                .collect(java.util.stream.Collectors.joining(", ")));
//    }
    
    
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Areas of Expertise:");
        for (AreaOfExpertise expertise : expertiseList) {
            System.out.println("- " + expertise.getName());
            for (Treatment treatment : expertise.getTreatments()) {
                System.out.println("  - " + treatment.getName());
            }
        }
//        if (!assignedSlots.isEmpty()) {
//            System.out.println("\n--- Assigned Slots ---");
//            for (ScheduledSlot slot : assignedSlots) {
//                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
//                DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
//                System.out.printf("%s on %s (%s - %s)%n",
//                                  slot.getTreatment().getName(),
//                                  slot.getDate().format(dateFormatter),
//                                  slot.getStartTime().format(timeFormatter),
//                                  slot.getEndTime().format(timeFormatter));
//            }
//        }
    }
}