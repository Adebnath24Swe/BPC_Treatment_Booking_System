package Clinic;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

// New class to handle schedule generation
class ScheduleGenerator {

    private static List<Physiotherapist> allPhysiotherapists;
    private static List<AreaOfExpertise> allExpertiseAreas;
    private static final Map<String, Physiotherapist> physiotherapistIdMap = new HashMap<>();

    // Constructor to initialize the physiotherapists and expertise areas
    public ScheduleGenerator(List<Physiotherapist> physiotherapists, List<AreaOfExpertise> expertiseAreas) {
        ScheduleGenerator.allPhysiotherapists = physiotherapists;
        ScheduleGenerator.allExpertiseAreas = expertiseAreas;
        for (Physiotherapist physio : physiotherapists) {
            physiotherapistIdMap.put(physio.getId(), physio);
        }
    }

    // Method to generate the schedule for a given week and start date
    public List<ScheduledSlot> generateWeeklySchedule(int weekNumber, LocalDate startDate) {
        List<ScheduledSlot> schedule = new ArrayList<>();
        LocalDate weekStartDate = startDate;

        // Get references to the physiotherapists
        Physiotherapist sarahThompson = physiotherapistIdMap.get("PHY001");
        Physiotherapist alexReed = physiotherapistIdMap.get("PHY002");
        Physiotherapist ninaPatel = physiotherapistIdMap.get("PHY003");
        Physiotherapist emmaLewis = physiotherapistIdMap.get("PHY004");
        Physiotherapist jamesWalker = physiotherapistIdMap.get("PHY005");

        AreaOfExpertise physiotherapy = null;
        AreaOfExpertise osteopathy = null;
        AreaOfExpertise rehabilitation = null;

        for (AreaOfExpertise area : allExpertiseAreas) {
            if (area.getName().equals("Physiotherapy")) {
                physiotherapy = area;
            } else if (area.getName().equals("Osteopathy")) {
                osteopathy = area;
            } else if (area.getName().equals("Rehabilitation")) {
                rehabilitation = area;
            }
        }

        Treatment neuralMobilisation = null;
        Treatment massagePhysio = null;
        Treatment acupuncture = null;
        Treatment mobilisationSpineJoints = null;
        Treatment poolRehabilitation = null;
        Treatment massageRehab = null;

        if (physiotherapy != null) {
            for (Treatment treatment : physiotherapy.getTreatments()) {
                if (treatment.getName().equals("Neural Mobilisation")) {
                    neuralMobilisation = treatment;
                } else if (treatment.getName().equals("Massage (Physiotherapy)")) {
                    massagePhysio = treatment;
                }
            }
        }
        if (osteopathy != null) {
            for (Treatment treatment : osteopathy.getTreatments()) {
                if (treatment.getName().equals("Acupuncture")) {
                    acupuncture = treatment;
                } else if (treatment.getName().equals("Mobilisation of Spine/Joints")) {
                    mobilisationSpineJoints = treatment;
                }
            }
        }

        if (rehabilitation != null) {
            for (Treatment treatment : rehabilitation.getTreatments()) {
                if (treatment.getName().equals("Pool Rehabilitation")) {
                    poolRehabilitation = treatment;
                } else if (treatment.getName().equals("Massage (Rehabilitation)")) {
                    massageRehab = treatment;
                }
            }
        }

        // Use a switch statement to handle the different weekly schedules
        switch (weekNumber) {
            case 1:
            case 3:
                // Week 1 and 3 schedule
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-001", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("09:00"), LocalTime.parse("10:00"), sarahThompson));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-002", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("10:00"), LocalTime.parse("11:00"), alexReed));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-003", osteopathy, acupuncture, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("11:00"), LocalTime.parse("12:00"), ninaPatel));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-004", rehabilitation, poolRehabilitation, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("12:00"), LocalTime.parse("13:00"), emmaLewis));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-005", physiotherapy, massagePhysio, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("13:00"), LocalTime.parse("14:00"), sarahThompson));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-006", osteopathy, mobilisationSpineJoints, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("14:00"), LocalTime.parse("15:00"), jamesWalker));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-007", rehabilitation, massageRehab, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("15:00"), LocalTime.parse("16:00"), emmaLewis));
                schedule.add(new ScheduledSlot("sun-w" + weekNumber + "-008", physiotherapy, neuralMobilisation, DayOfWeek.SUNDAY, weekStartDate, LocalTime.parse("16:00"), LocalTime.parse("17:00"), alexReed));

                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-009", physiotherapy, massagePhysio, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-010", osteopathy, mobilisationSpineJoints, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-011", rehabilitation, poolRehabilitation, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-012", physiotherapy, neuralMobilisation, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-013", osteopathy, acupuncture, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-014", rehabilitation, massageRehab, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-015", physiotherapy, massagePhysio, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
                schedule.add(new ScheduledSlot("mon-w" + weekNumber + "-016", osteopathy, mobilisationSpineJoints, DayOfWeek.MONDAY, weekStartDate.plusDays(1), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));
                break;
            case 2:
            case 4:
                // Week 2 and 4 schedule (different from week 1 and 3)
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-001", physiotherapy, neuralMobilisation, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("10:00"), sarahThompson));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-002", physiotherapy, massagePhysio, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("10:00"), LocalTime.parse("11:00"), alexReed));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-003", osteopathy, acupuncture, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("11:00"), LocalTime.parse("12:00"), ninaPatel));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-004", rehabilitation, poolRehabilitation, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("12:00"), LocalTime.parse("13:00"), emmaLewis));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-005", physiotherapy, neuralMobilisation, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("13:00"), LocalTime.parse("14:00"), sarahThompson));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-006", osteopathy, mobilisationSpineJoints, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("14:00"), LocalTime.parse("15:00"), jamesWalker));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-007", rehabilitation, massageRehab, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("15:00"), LocalTime.parse("16:00"), emmaLewis));
                schedule.add(new ScheduledSlot("tue-w" + weekNumber + "-008", physiotherapy, massagePhysio, DayOfWeek.TUESDAY, weekStartDate.plusDays(2), LocalTime.parse("16:00"), LocalTime.parse("17:00"), alexReed));

                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-009", physiotherapy, neuralMobilisation, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("09:00"), LocalTime.parse("10:00"), alexReed));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-010", osteopathy, mobilisationSpineJoints, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("10:00"), LocalTime.parse("11:00"), ninaPatel));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-011", rehabilitation, poolRehabilitation, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("11:00"), LocalTime.parse("12:00"), jamesWalker));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-012", physiotherapy, massagePhysio, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("12:00"), LocalTime.parse("13:00"), sarahThompson));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-013", osteopathy, acupuncture, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("13:00"), LocalTime.parse("14:00"), ninaPatel));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-014", rehabilitation, massageRehab, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("14:00"), LocalTime.parse("15:00"), emmaLewis));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-015", physiotherapy, neuralMobilisation, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("15:00"), LocalTime.parse("16:00"), alexReed));
                schedule.add(new ScheduledSlot("wed-w" + weekNumber + "-016", osteopathy, mobilisationSpineJoints, DayOfWeek.WEDNESDAY, weekStartDate.plusDays(3), LocalTime.parse("16:00"), LocalTime.parse("17:00"), jamesWalker));
                break;
            default:
                break;
        }

        // Assign the slots to the physiotherapists
        for (ScheduledSlot slot : schedule) {
            if (slot.getAssignedPhysiotherapist() != null) {
                slot.getAssignedPhysiotherapist().addAssignedSlot(slot);
            }
        }
        return schedule;
    }
}