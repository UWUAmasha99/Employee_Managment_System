/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesalarymgtlogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kasuni
 */
public class SelectOptionController implements Initializable {

    @FXML
    private Button btnEmp;
    @FXML
    private Button btnSal;
    @FXML
    private Button btnprint;
    @FXML
    private MenuItem DetailsMenue;
    @FXML
    private MenuItem CalMenue;
    @FXML
    private MenuItem PrintMenue;
    @FXML
    private MenuItem BackMenue;
    @FXML
    private MenuItem ForwardMenue;
    @FXML
    private MenuItem LogOutMenue;
    @FXML
    private MenuItem CloseMenue;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //navigate to another interface
    @FXML
    private void EmpDetails(ActionEvent event) throws IOException {
        
           btnEmp.getScene().getWindow().hide();
           Stage stage = (Stage) btnEmp.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
     
        
        
    }

    @FXML
    private void Salary(ActionEvent event) throws IOException {
        
           btnSal.getScene().getWindow().hide();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }

    @FXML
    private void Print(ActionEvent event)throws IOException {
        
           btnprint.getScene().getWindow().hide();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Print.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1400,725));
           primaryStage.show();
    }

    //back button
    @FXML
    private void back(ActionEvent event) throws IOException {
        
           btnback.getScene().getWindow().hide();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
           
        
        
    }
    
    // Menu Bar

    @FXML
    private void calsal(ActionEvent event) throws IOException {
       
           Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
           
        
        
    }

    @FXML
    private void printdetails(ActionEvent event) throws IOException {
         
       Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Print.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1400,725));
           primaryStage.show(); 
    }

    @FXML
    private void menuback(ActionEvent event) throws IOException {
        
      Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
          primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
        
    }

    @FXML
    private void forward(ActionEvent event) throws IOException {
           Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
             Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }
    
    
       @FXML
     private void menuclose(ActionEvent event) {
        Stage stage = (Stage) btnback.getScene().getWindow();
         stage.close();
        
    }
    
Stage stage;
    @FXML
    private void menulogout(ActionEvent event) throws IOException {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("About  Logout or Not!");
              
		alert.setContentText("Do you want Log Out the System ?: ");
		
		if(alert.showAndWait().get() == ButtonType.OK){
                    
                    //close current pages
			stage = (Stage) btnback.getScene().getWindow();
			stage.close();

                        //move to the logout page
            Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }

 
    
    }
    
    //menu bar employee details

    @FXML
    private void empdetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
          Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
        
    }
}
