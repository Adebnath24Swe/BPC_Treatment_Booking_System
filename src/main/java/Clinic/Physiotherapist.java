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
public class Physiotherapist extends Members {

    private List<AreaOfExpertise> expertiseList = new ArrayList<>();
    
    
    
    public Physiotherapist(String name, String address, String telephone) {
        super(name, address, telephone);
        setId(generateId());
    }
    

    @Override
    public String generateId() {
        return "M" + String.format("%03d", counterOfId++);
    }
    
    protected String setId(String id){
     return this.getId();
    }
}
