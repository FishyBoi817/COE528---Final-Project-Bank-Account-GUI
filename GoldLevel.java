/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class GoldLevel implements LevelTypes {
    
    private final String level = "Gold";
    
    @Override
    public void setAccountLevel(Customer customer, double balance) {
        if (balance >= 10000 && balance < 20000) {
            customer.setLevel(level);
        }
    }
    
    @Override
    public void getAccountLevel(Customer customer) {
        customer.getLevel();
    }
}
