
package Clinic;


public class Patient extends Members {
    
   public Patient(String name, String address, String telephone) {
        super(name, address, telephone);
        
    }
    // Patient id comes here from member class.
    @Override
    public String generateId() {
        return "PT" + String.format("%03d", counterOfId++);
    }
     
    public String setId(String id){
     return this.getId();
    }
     
   
    
}
