package BankingApplication;

/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
public class CustomerLevels {
    private LevelTypes state;

    // Setter method for state

    /**
     *
     * @param state
     */
    public void setState(LevelTypes state) { 
        this.state = state;
    }

    // Getter method for state

    /**
     *
     * @return
     */
    public LevelTypes getState() {
        return state;
    }

    // Delegate methods to the current state object

    /**
     *
     * @param customer
     * @param balance
     */
    public void setAccountLevel(Customer customer, double balance) {
    
        if (balance >= 100 && balance < 10000) this.state = new SilverLevel();
        
        if (balance >= 10000 && balance < 20000) this.state = new GoldLevel();
        
        if (balance >= 20000) this.state = new PlatinumLevel();
        
        state.setAccountLevel(customer, balance);
    }

    /**
     *
     * @param customer
     */
    public void getAccountLevel(Customer customer) {
        state.getAccountLevel(customer);
    }
}
