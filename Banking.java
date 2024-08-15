/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class Banking { 
    // Overview: The Banking.java class can do the following:
    // 
    //          - makeOnlienPurchaseFromBank() : takes in amount to purchase, customer's balance and level; calls the withdrawMoneyFromBank() method
    //          - depositMoneyToBank() : takes in amount to deposit and customer's balance; adds amount to balance
    //          - withdrawMoneyFromBank() : takes in amount to withdraw and customer's balance; deducts amount from balance
    //
    // The abstraction function is:
    // 
    // AF(c) = Banking object has 1 attribute: double balance
    //
    // The rep invariant is:
    //
    // RI(c) = false if 
    //       - balance < 100; cannot be negative real numbers and balance in bank must be minimum 100
    //
    // the rep
    private double balance;
    
    private double getBalance(){
        // EFFECTS: returns balance instance
        return balance;
    }
    
    private void setBalance(double b){
        // EFFECTS: set balance instance
        balance = b;
    }
    
    /**
     *
     * @param amount
     * @param balanceFromAccount
     * @param level
     * @return
     */
    public double makeOnlinePurchaseFromBank(double amount, double balanceFromAccount, String level){
        // Requires: amount > 0, balance >= 100 and String != null
        // MODIFIES: balance
        // EFFECTS: depending on level of customer, will add fee to purchase amount and return result of withdrawFromBank();   
        
        if (balanceFromAccount >= 100){
        setBalance(balanceFromAccount);
        }
        
        if (amount >= 50 && amount <= balance){

            switch (level){
                
                case "Silver" -> {
                    return this.withdrawMoneyFromBank(20 + amount, balance);
                }
                    
                case "Gold" -> {
                    return this.withdrawMoneyFromBank(10 + amount, balance);
                }
                 
                case "Platinum" -> {
                    return this.withdrawMoneyFromBank(amount, balance);
                }

                default -> {
                    System.err.println("Error occured");
                    return 0;
                }
                    
            }
        } 
        
        else return 0;
        
    }

    /**
     *
     * @param amount
     * @param balanceFromAccount
     * @return
     */
    public double depositMoneyToBank(double amount, double balanceFromAccount){
        // Requires: amount > 0 and balance >= 100
        // MODIFIES: balance
        // EFFECTS: ensures ammount is > 0, adds amount to customer's balance and returns updated balance
        
        if (balanceFromAccount >= 100){
        setBalance(balanceFromAccount);
        }
        
        if (amount > 0) return balance + amount;
        else return 0;
        
    }
    
    /**
     *
     * @param amount
     * @param balanceFromAccount
     * @return
     */
    public double withdrawMoneyFromBank(double amount, double balanceFromAccount) {
        // Requires: amount > 0 and balance >= 100
        // MODIFIES: balance
        // EFFECTS: ensures ammount is > 0, deducts amount to customer's balance and returns updated balance        
        setBalance(balanceFromAccount);
        
        if (amount > 0 && amount <= balance) {
            
            balance = balance - amount;
            
            if (balance < 100)
                return 0;
            
            return balance;
        }
        else return 0;
        
    }
    
    /**
     *
     * @return
     */
    public boolean repOK(){

        // EFFECTS: Returns true if the rep invariant holds for this
        //          object; otherwise returns false   
        
        // return false when balance in account is under $100
        if (balance < 100)
            return false;
        
        // return true when balance is equal to and greater than $100
        else return true;
        
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        // EFFECTS: Returns a string that contains Banking balance
        
        return "Banking balance: " + getBalance();
        
    }
}
