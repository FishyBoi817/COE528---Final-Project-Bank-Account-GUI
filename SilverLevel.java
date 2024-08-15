/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
//SilverLevel Class
public class SilverLevel implements LevelTypes {
    
    private final String level = "Silver";
    
    @Override
    public void setAccountLevel(Customer customer, double balance) {
        if (balance >= 100 && balance < 10000) {
            customer.setLevel(level);
        }
    }
    
    @Override
    public void getAccountLevel(Customer customer) {
            customer.getLevel();
    }
}
