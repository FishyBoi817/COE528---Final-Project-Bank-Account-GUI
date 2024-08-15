/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class CustomerFile {
    
    // Overview: CustomerFile are mutable, can dynamically change
    //           depending on addition and/ or removal of customer objects.
    //
    //         - addCustomerToFile (Customer customerProfile) : adds customer object to customerFile 
    //         - deleteCustomerToFile (Customer customerProfile) : deletes customer object from customerFile 
    //         - getCustomer (String customerUsername) : searches and return customer object from customerFile 
    //         - getCustomerRoleFromFile(String customerUsername) : get the Customer Role from customerFile 
    //         - getCustomerUsernameFromFile(String customerUsername) : get the Customer Username from customerFile 
    //         - getCustomerPasswordFromFile(String customerUsername) : get the Customer Password from customerFile 
    //         - retrieveTextFiles() : searches for existing customer text files and stores it into customerFile 
    //
    // The abstraction function is:
    // 
    // AF(c) = customerFile = new ArrayList<Customer>(): acts as a customerfiles (arrayList) that containts customer objects 
    //
    // The rep invariant is:
    //
    // RI(c) = false if 
    //         - contents of customerFile contains null; customers objects are null
    //         - customerFile size cannot be 0
    //         - element (customer objects) in customerFile have duplicates
    //         - customerFile is empty
    
    //the rep
    private final ArrayList <Customer> customerFile;  
    public static CustomerLevels customerLevels = new CustomerLevels();
    
    public CustomerFile() {
        // EFFECTS: Creates a new customerFile object
        customerFile = new ArrayList<>();
    }
    
    /**
     *
     * @param customerProfile
     */
    public void addCustomerToFile (Customer customerProfile){
        // Requires: customerProfile object not null
        // MODIFIES: this
        // EFFECTS: Appends the customerProfile to end of customerFile
        
        if (this.getCustomerUsernameFromFile(customerProfile.getUsername()) == false)
        customerFile.add(customerProfile);
    }
    
    /**
     *
     * @param username
     */
    public void deleteCustomerFromFile (String username){ 
        // Requires: username String not null and username of customer must exist in customerFile
        // MODIFIES: this
        // EFFECTS: Removes the customerProfile from customerFile
        //          Searches the customer objects by username within of customerFile
        Customer c = null;
 
        for (Customer p : customerFile){
            if (p.getUsername().equals(username) )
            c = p;
        }
        customerFile.remove(c);  
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Customer getCustomer (String username){
        // Requires: username String not null and username of customer must exist in customerFile
        // MODIFIES: this
        // EFFECTS: returns customer object from CustomerFile
        //          Searches the customer objects by username within of customerFile        
        Customer c = null;
        
        for (Customer p : customerFile){
           
            if (p.getUsername().equals(username) )
            c = p;

        }
        
        return c;
    }   
    
    /**
     *
     * @param role
     * @return
     */
    public Boolean getCustomerRoleFromFile(String role){
        // Requires: role String not null and role of customer must exist in custmerFile
        // MODIFIES: this
        // EFFECTS: returns true if customer Role is "Customer"
        //          Searches the customer objects by username within of customerFile
        if (customerFile.isEmpty() == true) {
            
            return false;
        }
        
        else{
        
            for (int i = 0; i < customerFile.size(); i++){

                String fileRole =  customerFile.get(i).getRole();

                if (role.equals(fileRole)) return true;

            }
        }
        
        return false; 
    }
    
    /**
     *
     * @param username
     * @return
     */
    public Boolean getCustomerUsernameFromFile(String username){
        // Requires: username String not null and username of customer must exist in customerFile
        // MODIFIES: this
        // EFFECTS: returns true if username exists in customer objects within customerFile
        //          Searches the customer objects by username within of customerFile 
        if (customerFile.isEmpty() == true) {
            
            return false;
        }
        
        else{
        
            for (int i = 0; i < customerFile.size(); i++){

                String fileUsername =  customerFile.get(i).getUsername();

                if (username.equals(fileUsername)) return true;

            }
        }
        
        return false; 
    }
    
    /**
     *
     * @param password
     * @return
     */
    public Boolean getCustomerPasswordFromFile(String password){
        // Requires: password String not null and password of customer must exist in custmerFile
        // MODIFIES: this
        // EFFECTS: returns true if username exists in customer objects within customerFile
        //          Searches the customer objects by password within of customerFile       
        if (customerFile.isEmpty() == true) {
            
            return false;
        }
        
        else{
        
            for (int i = 0; i < customerFile.size(); i++){

                String filePassword = customerFile.get(i).getPassword();

                if (filePassword.equals(password)) return true;

            }
        }
        
        return false;
    }
    
    /**
     *
     * @throws IOException
     */
    public void retrieveTextFiles() throws IOException {
        // MODIFIES: this
        // EFFECTS: searches existing text files and creates customer objects 
        //          then stores it into customerFiles ArrayList
        
    Path directoryPath = Paths.get("src", "CustomerFiles");

    if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
        System.err.println("Directory not found: " + directoryPath);
        return;
    }

    try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
        System.out.println("Customer Files Scanned: \n");
        for (Path filePath : directoryStream) {
            if (Files.isRegularFile(filePath)) {
                try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                    String username = reader.readLine();
                    String password = reader.readLine();
                    String balance = reader.readLine();
                    double convertedBalance = Double.parseDouble(balance);
                    
                    Customer customer = new Customer("Customer", username, password, convertedBalance, "");
                    customerLevels.setAccountLevel(customer, convertedBalance);
                    this.addCustomerToFile(customer);

                    // Process the read data
                    
                    System.out.println("Filename: " + username + ".txt" + "\nUsername: " + username + "\nPassword: " + password + "\nBalance: " + balance);
                }
            }
        }
        System.out.println(" ");
    } catch (IOException e) {
        System.err.println("An error occurred while reading files: " + e.getMessage());
    }
}
    
    /**
     * 
     * @return 
     */
    public boolean repOk(){
        // EFFECTS: Returns true if the rep invariant holds for this
        //          object; otherwise returns false
        
        // customerFile cannot be empty
        if (customerFile.isEmpty() == true)
            return false;
        
        //customerFile contains null OR item array cannot be null
        if (customerFile == null || customerFile.contains(null)) 
            return false;
        
        //customerFile size cannot be 0
        if (customerFile.size() == 0) 
            return false;
        
        //customer objects in customerFile cannot have same elements
        for (int i = 0; i < customerFile.size(); i++){ 
            
            for (int j = i + 1; j < customerFile.size(); j++){
                
                if (customerFile.get(i).equals(customerFile.get(j)))
                   return false;
                   
            }
              
        }
        
        //otherwise if all conditions met, return true
        return true;
    }
    
    @Override
    public String toString(){
        // EFFECTS: Returns a string that contains every customer: 
        //          "customerId, username, password, balance, current level"
        //          from customerFile arrayList

        int customerId = 1; 
        String line = "\nCustomerFile.java:\n";
        
        if (customerFile.isEmpty() == false){
            
            for (Customer contents : customerFile){
            line = line + "Customer " + customerId 
                        + ", username: " + contents.getUsername() 
                        + ", password: " + contents.getPassword() 
                        + ", balance:  " + contents.getBalance() 
                        + ", current level: " + contents.getLevel() + "\n";
            customerId ++;
            }
        }
        
        return line;
    }
}