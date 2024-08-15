/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
/* importing CustomerFile.java, Customer.java & CustomerLevels from BankingApplication package */
import BankingApplication.Customer;
import BankingApplication.CustomerFile;
import BankingApplication.CustomerLevels; 
import java.io.File;
import java.io.FileWriter;

/* FXML and Scene Setup */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CustomerController implements Initializable {

    private CustomerLevels level;
    private Customer customer; 
    
    @FXML
    private Label customerLabel; 
    
    @FXML
    private Label balanceLabel; 
    
    @FXML
    private Label levelLabel;
    
    @FXML
    private TextField depositAmount;
    
    @FXML
    private TextField withdrawAmount;
    
    @FXML
    private TextField purchaseAmount;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer = LoginPageController.c;
        level = CustomerFile.customerLevels; //wrong
        customerLabel.setText("Logged in as: " + customer.getUsername());
        balanceLabel.setText(String.format("Current Balance: $ %.2f", customer.getBalance()));

        if (level != null) {
            levelLabel.setText("Current Account Level: " + customer.getLevel());
            level.setAccountLevel(customer, customer.getBalance());
            customer.login();
        } else {
            // Handle the case when level is null
            // For example, show an error message or log it
            System.err.println("Customer level is not initialized!");
        }
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void logOut(ActionEvent event) throws IOException{
        
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show(); 
            customer.logout();
               
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void depositMoneyButton (ActionEvent event) throws IOException{
        
        try{
            double addMoney = Double.parseDouble(depositAmount.getText());

            if (addMoney <= 0){     
                customer.depositMoney(addMoney);
                showAlert2("Customer Deposit Amount Error","A customer cannot deposit less than 0.");
            }

            else{
                customer.depositMoney(addMoney);
                this.getBalanceButton(event);
                this.getCustomerLevelButton(event);
                this.overwriteFile();
            }
        }catch (IOException | NumberFormatException e){ 
            showAlert1("Customer Fill In Error","Make sure Deposit Amount field is filled in.");  
        }
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void WithdrawMoneyButton (ActionEvent event) throws IOException{
       
        try{
            double takeMoney = Double.parseDouble(withdrawAmount.getText());

            if (takeMoney > customer.getBalance() || takeMoney <= 0){
                customer.withdrawMoney(takeMoney);
                showAlert2("Customer Withdrawing Amount Error","A customer cannot withdraw more than their balance\nor withdraw less than 0.");
            }
            
            else{
                customer.withdrawMoney(takeMoney);
                this.getBalanceButton(event);
                this.getCustomerLevelButton(event);
                this.overwriteFile();
            }
        }catch (IOException | NumberFormatException e){
            showAlert1("Customer Fill In Error", "Make sure Withdraw Amount field is filled in.");
        }
        
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void OnlinePurchasesButton (ActionEvent event) throws IOException{

        try{
            double buy = Double.parseDouble(purchaseAmount.getText());

            if (buy < 50 || buy > customer.getBalance()){
                customer.makeOnlinePurchase(buy);
                showAlert2("Customer Purchase Amount Error","A customer must make minimum purchase of $50\nor cannot purchase more than balance.");
            }
            else {
                customer.makeOnlinePurchase(buy);
                this.getBalanceButton(event);
                this.getCustomerLevelButton(event);
                this.overwriteFile();
            }
        } catch (IOException | NumberFormatException e){          
            showAlert1("Customer Fill In Error", "Make sure Purchase Amount field is filled in."); 
        }

    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void getCustomerLevelButton (ActionEvent event) throws IOException{
        
        level.getAccountLevel(customer);
        levelLabel.setText("Current Account Level: " + customer.getLevel());
        depositAmount.clear();
        withdrawAmount.clear();
        purchaseAmount.clear();
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void getBalanceButton(ActionEvent event) throws IOException {
        
        balanceLabel.setText(String.format("Current Balance: $ %.2f", customer.getBalance())); 
        level.setAccountLevel(customer, customer.getBalance());
        level.getAccountLevel(customer);
        depositAmount.clear();
        withdrawAmount.clear();
        purchaseAmount.clear();
    }   
    
    /**
     *
     * @throws IOException
     */
    public void overwriteFile() throws IOException{
        
        File file2, file1;
        file2 = new File("src/CustomerFiles", customer.getUsername() + ".txt");
        
        if (file2.exists()){
        file1 = new File ("src/CustomerFiles", customer.getUsername() + ".txt");
            try (FileWriter writer = new FileWriter(file1)) {
                writer.write(customer.getUsername() + "\n" + customer.getPassword()+ "\n" + customer.getBalance());
            }
        }
        
    }
    
    private void showAlert1(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        depositAmount.clear();
        withdrawAmount.clear();
        purchaseAmount.clear();
    }
    
    private void showAlert2(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        depositAmount.clear();
        withdrawAmount.clear();
        purchaseAmount.clear();
    }    
    
}
