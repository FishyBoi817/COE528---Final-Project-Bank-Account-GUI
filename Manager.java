/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class Manager extends User{
    
    // Overview: A Manager can do the following:
    // 
    //          - addCustomer() : create Customer object and appends to CustomerFile (ArrayList)
    //          - deleteCustomer() : deletes Customer object from CustomerFile (ArrayList)
    //          - login() : customer logins to ManagerController.java
    //          - logout() : customers logouts from ManagerController.java
    //
    // The abstraction function is:
    // 
    // AF(c) = Manager has 3 attributes: String role, String username, String password
    //         Manager is an extenstion of User interface. 
    //
    // The rep invariant is:
    //
    // RI(c) = false if 
    //         - Role = null OR Role != "Manager"
    //         - username = null
    //         - password = null
    //
    //the rep
    private CustomerFile customerFile;
    
    /**
     *
     * @param role
     * @param username
     * @param password
     */
    public Manager(String role, String username, String password) {
        super(role, username, password);
    }
    
    /**
     *
     * @param role
     * @param username
     * @param password
     * @param amount
     * @param level
     */
    public void addCustomer(String role, String username, String password, double amount, String level){
        // Requires: role != null && role == "Customer", username != null, password != null, amount >= 100, level != null
        // MODIFIES: CustomerFile object and Customer object
        // EFFECTS: creates new Customer object and appends to customerFile object (adds to ArrayList)
        
        Customer customer = new Customer (role, username, password, amount, level);
        customerFile = new CustomerFile();
        customerFile.addCustomerToFile(customer);
        
    }
    
    /**
     *
     * @param username
     */
    public void deleteCustomer(String username){
        // Requires: username != null
        // MODIFIES: CustomerFile object
        // EFFECTS: calls CustomerFile.java to deleteCustomerFromFile       
        customerFile.deleteCustomerFromFile(username);
        
    }
    
    /**
     *
     */
    public void login(){
        // EFFECTS: pirnts in console that manager has logged in
        System.out.println("Manager has logged in!");
    }
    
    /**
     *
     */
    public void logout(){

        // EFFECTS: prints in console that manager has logged out
        System.out.println("Manager has logged out!"); 
    }
    
    public boolean repOK(){
        // EFFECTS: Returns true if the rep invariant holds for this
        //          object; otherwise returns false
        
        System.out.print("The Manager.java repOK was called: ");
        //return false when role is null OR role is not "Manager"
        if (this.getRole() == null && !(this.getRole().equals("Manager")))
            return false;
        
        //return false if username is null
        else if (this.getUsername() == null)
            return false;
        
        //return false if password is null
        else if (this.getPassword() == null)
            return false;
        
         //if rep invariant holds, return true
        else return true; 
    }
    
    public String toString(){
        // EFFECTS: Returns a string that contains customer attributes: 
        //          "role , username, password"
        
        return "Role: " + getRole() + ", Username: " + getUsername() + ", Password: " 
                 + getPassword();
    }

    
}
