/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package GUI;

import BankingApplication.CustomerFile;
import BankingApplication.Manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class BankAccountMainFXML extends Application {
    
    public static CustomerFile file;
    public static Manager manager;
    @Override
    public void start(Stage stage) throws Exception {
        
        manager = new Manager("Manager", "admin", "admin");
        file = new CustomerFile();
        
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        /*dispay on console at start */
        file.retrieveTextFiles();        
        
        System.out.println("GUI has opened (Before): \n" + file.toString());
        System.out.println("Manager.java toString was called: " + manager.toString() + "\n");
        System.out.println(manager.repOK() + "\n");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        /*display on console at end (when gui is closed) */
        System.out.println("\n*************************************************************************************");
        System.out.println("GUI has closed (After): \n" + file.toString());
    }
    
}
