package Clinic;

import java.util.ArrayList;
import java.util.List;

public class AreaOfExpertise {

    private String name;
    private List<Treatment> treatments;

    public AreaOfExpertise(String name) {
        this.name = name;
        this.treatments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void addTreatment(Treatment t) {
        treatments.add(t);
    }

    public boolean hasTreatment(String treatmentName) {
        for (Treatment t : treatments) {
            if (t.getName().equalsIgnoreCase(treatmentName)) {
                return true;
            }
        }
        return false;
    }
}
