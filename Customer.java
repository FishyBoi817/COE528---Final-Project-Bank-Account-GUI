/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;


/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class Customer extends User{
    
    // Overview: A Customer can do the following:
    // 
    //          - getBalance() : returns current balance 
    //          - getLevel() : returns current level 
    //          - setLevel() : sets customer's level
    //          - makeOnlinePurchase() : calls Banking.java to makeOnlinePurchase with transaction fee depending on level
    //          - depositMoney() : calls Banking.java to deposit amount to balance
    //          - withdrawMoney() : calls Banking.java to withdraw amount from balance
    //          - login() : customer logins to CustomerController.java
    //          - logout() : customers logouts from CustomerController.java
    //
    // The abstraction function is:
    // 
    // AF(c) = Customer has 5 attributes: String role, String username, String password, double balance, String level.
    //         Customer is an extenstion of User interface. 
    //
    // The rep invariant is:
    //
    // RI(c) = false if 
    //         - Role = null OR Role != "Customer"
    //         - username = null
    //         - password = null
    //         - balance < 100
    //         - level = null
    //
    //the rep
    private double balance;
    private String level;
    private final Banking bank = new Banking();
    
    /**
     *
     * @param role
     * @param username
     * @param password
     * @param balance
     * @param level
     */
    public Customer (String role, String username, String password, double balance, String level) {
        super(role, username, password);
        this.balance = balance;
        this.level = level;
        
    }
    
    /**
     *
     * @return
     */
    public double getBalance(){
        // EFFECTS: returns balance instance
        return balance; 
    }
    
    /**
     *
     * @return
     */
    public String getLevel(){
        // EFFECTS: returns level instance
        return level;
    }
    
    /**
     *
     * @param levels
     */
    public void setLevel (String levels){
        // Requires: expect levels to be "Silver", "Gold" or "Platinum"
        // MODIFIES: this
        // EFFECTS: sets "level" instance with updated "levels"
        level = levels;
    }
    
    /**
     *
     * @param amount
     */
    public void makeOnlinePurchase(double amount){
        // Requires: amount > 0 AND amount + <= balance
        // MODIFIES: balance instance
        // EFFECTS: calls Banking.java to makeOnlinePurchaseFromBank and updates balance instance

        if (bank.makeOnlinePurchaseFromBank(amount, this.getBalance(), this.getLevel() ) == 0) 
            System.err.println("You cannot make purchase less than $50 or greater than your balance or your balance cannot be under $100.00");
        else 
        balance = bank.makeOnlinePurchaseFromBank(amount, this.getBalance(), this.getLevel());   
    }
    
    /**
     *
     * @param amount
     */
    public void depositMoney(double amount){
        // Requires: amount > 0
        // MODIFIES: balance instance
        // EFFECTS: calls Banking.java to depositMoneyTobank and updates balance instance
        
        if (bank.depositMoneyToBank(amount, this.getBalance()) == 0) 
            System.err.println("You cannot deposit amount less than 0");
        else
        balance = bank.depositMoneyToBank(amount, this.getBalance());
    }
    
    /**
     *
     * @param amount
     */
    public void withdrawMoney(double amount){
        // Requires: amount > 0 AND amount <= balance
        // MODIFIES: balance instance
        // EFFECTS: calls Banking.java to withdrawMoneyFromBank and updates balance
        
        if (bank.withdrawMoneyFromBank(amount, this.getBalance()) == 0) 
            System.err.println("You cannot withdraw more than your balance or negative or have account balance under $100.00");
            
        else
        balance = bank.withdrawMoneyFromBank(amount, this.getBalance()); 
    }
    
    /**
     *
     */
    public void login(){
        // EFFECTS: prints in console that customer logged in
        System.out.println("Customer has logged in!");
    }
    
    /**
     *
     */
    public void logout(){
        // EFFECTS: prints in console that customer logged out
        System.out.println("Customer has logged out!"); 
    }
    
    /**
     *
     * @return
     */
    public boolean repOK(){
        // EFFECTS: Returns true if the rep invariant holds for this
        //          object; otherwise returns false
        
        System.out.println("The Customer.java repOK was called: ");
        //return false when role is null OR role is not "Customer"
        if (this.getRole() == null || !(this.getRole().equals("Customer")))
            return false;
        
        //return false when username is null
        else if (this.getUsername() == null)
            return false;
        
        //return false when password is null
        else if (this.getPassword() == null)
            return false;
        
        //return false when balance is less than $100
        else if (this.getBalance() < 100)
            return false;
       
        //return false when level is null
        else if (this.getLevel() == null)
            return false;
        
        //if rep invariant holds, return true
        else return true;
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        // EFFECTS: Returns a string that contains customer attributes: 
        //          "role , username, password, balance, current level"
        
        return "Role: " + getRole() + ", Username: " + getUsername() + ", Password: " 
                 + getPassword() +  ", Account Level: " + ", Balance: $" + getBalance();
    }
}
