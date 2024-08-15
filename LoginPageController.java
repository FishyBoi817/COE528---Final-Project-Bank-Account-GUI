/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package GUI;
/**
 *
 * @author Jonathan Ly | Mar 23, 2024
 */
/* importing CustomerFile.java, Manger.java & CustomerFile.java from BankingApplication package */
import BankingApplication.Customer;
import BankingApplication.CustomerFile;
import BankingApplication.Manager;

/* FXML and Scene Setup */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class LoginPageController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private ComboBox <String> comboBox; 
    
    public static Manager manager; 
    private  CustomerFile file;
    public static Customer c; 
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        
        file = BankAccountMainFXML.file;
        manager = BankAccountMainFXML.manager;
        if (comboBox != null) {
            comboBox.setItems(FXCollections.observableArrayList("Manager", "Customer"));
        } else {
            System.err.println("ComboBox is null. Check your FXML file.");
        }
        username.clear();
        password.clear();

    }
    
    @FXML
    private void FieldVerifier(ActionEvent event) throws IOException{
        
        String name = username.getText(); 
        String pass = password.getText();
        try{
            /* Logging in options as Manager */
            if (comboBox.getValue().equals(manager.getRole()) && name.equals(manager.getUsername()) && pass.equals(manager.getPassword())){

                label.setText("Manager was invoked");

                /* Transition to Manager Scene */
                Parent root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }
            /* Logging in options as Customer */
            else if ( file.getCustomerRoleFromFile(comboBox.getValue())&& file.getCustomerUsernameFromFile(name) && file.getCustomerPasswordFromFile(pass)){

                label.setText("Customer was invoked");
                c = file.getCustomer(name);
                
                /*Transtion to Customer Scene */
                Parent root = FXMLLoader.load(getClass().getResource("Customer.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }
            
            else {
                label.setText("Wrong Login Info");
                showAlert1("Failure to Sign in", "Check Username Or Password!");
            } 
        } catch (Exception e){
            label.setText("Empty Field Error");
            showAlert1("Empty Field Error", "Please Fill In All Fields!");  
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
    }
    
}
