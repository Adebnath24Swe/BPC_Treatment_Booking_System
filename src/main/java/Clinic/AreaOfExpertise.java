/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clinic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Apu_PC
 */
public class AreaOfExpertise {
    private String name;
    
    private List<Treatment> treatmentList;
    
    
    public AreaOfExpertise(String name){
    
        this.name = name;
        this.treatmentList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    
    public void addTreatment(Treatment treatment){
        
        treatmentList.add(treatment) ; 
    }
    
    public List<Treatment> getTreatments() {
        return treatmentList;
    }
}
