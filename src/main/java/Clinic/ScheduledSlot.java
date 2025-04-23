package Clinic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduledSlot {
    private String id;  // Added ID field
    private AreaOfExpertise areaOfExpertise;
    private Treatment treatment;
    private DayOfWeek dayOfWeek;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Physiotherapist assignedPhysiotherapist;
    public boolean isBooked;

    // New Constructor with ID
    public ScheduledSlot(String id, AreaOfExpertise areaOfExpertise, Treatment treatment, DayOfWeek dayOfWeek, LocalDate date, LocalTime startTime, LocalTime endTime, Physiotherapist assignedPhysiotherapist) {
        this.id = id;
        this.areaOfExpertise = areaOfExpertise;
        this.treatment = treatment;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.assignedPhysiotherapist = assignedPhysiotherapist;
//        this.isBooked = true;
    }

    public ScheduledSlot(AreaOfExpertise areaOfExpertise, Treatment treatment, DayOfWeek dayOfWeek, LocalDate date, LocalTime startTime, LocalTime endTime, Physiotherapist assignedPhysiotherapist) {
        this.areaOfExpertise = areaOfExpertise;
        this.treatment = treatment;
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.assignedPhysiotherapist = assignedPhysiotherapist;
    }

    public String getId() {
        return id;
    }

    public AreaOfExpertise getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Physiotherapist getAssignedPhysiotherapist() {
        return assignedPhysiotherapist;
    }

    public void setAssignedPhysiotherapist(Physiotherapist assignedPhysiotherapist) {
        this.assignedPhysiotherapist = assignedPhysiotherapist;
    }
}