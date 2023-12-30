/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesalarymgtlogin;

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kasuni
 */
public class ChangepasswordController implements Initializable {

    
    

    @FXML
    private Button btnchange;
    @FXML
    private Label label;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private PasswordField txtconfirmpassword;
    
    /**
     * Initializes the controller class.
     */
    
    
    
     Connection conn = null;
      ResultSet rs = null;
    PreparedStatement pst = null;
  
    @Override
 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
   
    

    @FXML
    private void ChangePassword(ActionEvent event) throws IOException, SQLException {
       
        if(validatepassword()){
                conn = MySqlConnect.ConnectDb();
                pst = conn.prepareStatement("insert into login(username,password)values (?,?)");
  
                pst.setString(1,txtusername.getText());
                pst.setString(2,txtconfirmpassword.getText());
               
            
                int status = pst.executeUpdate();
                
                    try {
                      if (txtpassword.getText().equals(txtconfirmpassword.getText())){
      
                
                   JOptionPane.showMessageDialog(null,"Data Changed successfully");
                    btnchange.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();
        }
                      
                
                    
                else{
                    JOptionPane.showMessageDialog(null,"Password do not match");
                    
                    
                }
                   
                    
                    }catch (Exception e)
        {
                JOptionPane.showMessageDialog(null,e);
        }

//      }  
//        
//        
//        
//             
//            else{
//             JOptionPane.showMessageDialog(null,"Password do not match");
//          }
        
}
    }

//    
//}
private boolean validatepassword(){
Pattern p =Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,10})");
Matcher m =p.matcher(txtpassword.getText());
//if (m.matches()){
if(m.find() && m.group().equals(txtpassword.getText())){
    return true;

}else{}
Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Paaword Validation");
		alert.setHeaderText(null);
              
		alert.setContentText("Pasword must contain at least one(Digit, lowercase,uppercase and special character)and length must be between 6-10");
  alert.showAndWait();
return false;

}
}
