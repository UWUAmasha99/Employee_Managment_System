/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesalarymgtlogin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Kasuni
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField TxtUserName;
    @FXML
    private PasswordField TxtPassword;
    @FXML
    private Button BtnLogin;
    @FXML
    private Button BtnLogout;
    
  @FXML
    private Button btnforgot;
   
       
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void LogIn(ActionEvent event) throws Exception {
        
        
        
        
        
        
        
        
        //call the database connection
        conn = MySqlConnect.ConnectDb();
        String sql = "SELECT * FROM login where username = ? and password = ?";
        
        //check username and password
        
        try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, TxtUserName.getText());
        pst.setString(2, TxtPassword.getText());
        rs = pst.executeQuery();
        
        if (rs.next()){
            JOptionPane.showMessageDialog(null, " User Name and Password is Correct ");
            
            //if user name and password is correct, move to the next interface
            BtnLogin.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            
        }
        
        else{
        JOptionPane.showMessageDialog(null, " Invalid User Name or Password  ");
        }
        
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);       
        
        }
        
    }
	
	//Log Out
Stage stage;	
    @FXML
	public void LogOut(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("About  Logout or Not!");
              
		alert.setContentText("Do you want Log Out the System ?: ");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			stage = (Stage) BtnLogout.getScene().getWindow();
			System.out.println("You successfully logged out!");
			stage.close();
        
        
        
    }
        }

    @FXML
    private void forgotbutton(ActionEvent event) throws IOException {
             btnforgot.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Changepassword.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        
    }
}
