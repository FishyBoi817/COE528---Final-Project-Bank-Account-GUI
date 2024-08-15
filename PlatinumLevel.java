/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
// PlatinumLevel class
public class PlatinumLevel implements LevelTypes {
    
    private final String level = "Platinum";
    
    @Override
    public void setAccountLevel(Customer customer, double balance) {
        if (balance >= 20000) {
            customer.setLevel(level);
        }
    }
    
    @Override
    public void getAccountLevel(Customer customer) {
        customer.getLevel();
    }
}