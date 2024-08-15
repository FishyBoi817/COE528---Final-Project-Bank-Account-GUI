package GUI;
/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
/* importing CustomerFile.java, Manger.java, CustomerFile.java & CustomerLevels.java from BankingApplication package */
import BankingApplication.Customer;
import BankingApplication.CustomerFile;
import BankingApplication.CustomerLevels;
import BankingApplication.Manager;

/* FXML and Scene Setup */
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerController implements Initializable {
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField balance;
    
    private File file1;
    private final CustomerFile file = BankAccountMainFXML.file;
    private final Manager manager = LoginPageController.manager;
    public static CustomerLevels customerLevels;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager.login();
    } 
    
    @FXML
    public void logOut(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        BankAccountMainFXML.file = file;
        manager.logout();
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void createCustomer(ActionEvent event) throws IOException {
        try {
            String uname = username.getText();
            String pass = password.getText();
            String bal = balance.getText();

            if (uname.isBlank() || pass.isBlank() || bal.isBlank()) {
                throw new FieldIsBlank("Make sure all fields are filled in!");
            }
            
            double initialBalance = Double.parseDouble(bal);
            
            if (initialBalance < 100) {
                throw new NumberFormatException("A customer must have a minimum balance of $100.00!");
            } 
            else if (file.getCustomerUsernameFromFile(uname)) {
                throw new SameUsernameException("Customer with username: " + uname + "\nAlready exists!");
            }

            file1 = new File ("src/CustomerFiles", uname + ".txt");
            try (FileWriter writer = new FileWriter(file1)) {
                writer.write(uname + "\n" + pass + "\n" + bal);
            }

            Customer customer = new Customer("Customer", uname, pass, initialBalance, "");
            customerLevels = new CustomerLevels();
            customerLevels.setAccountLevel(customer, initialBalance);
            file.retrieveTextFiles();
            
            showAlert2("Customer Added Successfully!","Username: " + uname + "\nPassword: " + pass + "\nBalance: " 
                       + String.format("$ %.2f", initialBalance) + "\nAccount level: " + customer.getLevel());
            
        } catch (FieldIsBlank e1) {
            showAlert1("Customer Fill In Error", "Make sure all fields are filled in");
        } catch (SameUsernameException e2) {
            showAlert1("Customer Fill In Error", "Customer with username: " + username.getText() + " \nAlready exists!");
        } catch (NumberFormatException e3) {
            showAlert1("Customer Balance Error", "A customer must have a minimum balance of $100.00!");
        }
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void deleteCustomer(ActionEvent event) throws IOException {
        String uname = username.getText();
        File file2 = new File("src/CustomerFiles", uname + ".txt");
        
        if (!uname.isBlank() && file.getCustomerUsernameFromFile(uname) && file2.exists()) {
            file2.delete();
            file.deleteCustomerFromFile(uname);
            showAlert2("Customer Delete Confirmation", "A customer with username: \"" + uname + "\" was deleted!");
            
        } else if (!uname.isBlank() && file.getCustomerUsernameFromFile(uname) == false && !file2.exists()) {
            showAlert1("Customer Cannot be Found!", "The username: \"" + uname + "\"\nDoesn't Exist!");
        } else if (uname.isBlank()){
            showAlert1("Username is Blank", "Please fill in Username Text Field!");
        }
    }
    
    private void showAlert1(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        username.clear();
        password.clear();
        balance.clear();
    }
    
    private void showAlert2(String title, String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
        username.clear();
        password.clear();
        balance.clear();    
        
    }

    // Custom Exceptions
    class SameUsernameException extends Exception {
        public SameUsernameException(String message) {
            super(message);
        }
    }

    class FieldIsBlank extends Exception {
        public FieldIsBlank (String message) {
            super(message);
        }
    }
}
