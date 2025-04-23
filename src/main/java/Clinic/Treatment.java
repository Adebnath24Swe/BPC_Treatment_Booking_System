package Clinic;

import java.util.ArrayList;
import java.util.List;

public class Treatment {

    private String name;
    private List<TimeSlot> timeSlots;

    public Treatment(String name) {
        this.name = name;
        this.timeSlots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void addTimeSlot(TimeSlot slot) {
        timeSlots.add(slot);
    }

    public void displayAvailableSlots() {
        for (TimeSlot slot : timeSlots) {
            if (!slot.isBooked()) {
                slot.displaySlot();
            }
        }
    }

    public TimeSlot getSlotById(int id) {
        for (TimeSlot slot : timeSlots) {
            if (slot.getSlotId() == id) {
                return slot;
            }
        }
        return null;
    }
}
