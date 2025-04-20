
package Clinic;

public class Members {
    public static int counterOfId = 1;  
    private String id;
    private String name;
    private String address;
    private String telephone_no;
    
    public Members( String name, String address, String telephone){
        
        this.id = this.generateId();
        this.name = name;
        this.address = address;
        this.telephone_no = telephone;
    
    }
    
    public String generateId() {
        return "M" + String.format("%03d", counterOfId++);
    }
    
    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getTelephone_no() {
        return telephone_no;
    }

    public String getName() {
        return name;
    }
    //public void setName(String name) { this.name = name; }
    //public void setId(String id) { this.id = id; }
    
    
    public void displayInfo(){
        System.out.println("----------------------------");
        System.out.println("All information of : "+ this.getId());
        System.out.println("");
        System.out.println("ID: "+ this.getId());
        System.out.println("Name: "+ this.getName());
        System.out.println("Address: "+ this.getAddress());
        System.out.println("Telephone no.: "+ this.getTelephone_no());
        
    }
}
