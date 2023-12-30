/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesalarymgtlogin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chathumi
 */
public class ViewSalaryController implements Initializable {

    @FXML
    private ComboBox<String> month;
    public String[] montharray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    @FXML
    private TextField year;
    @FXML
    private Button view;
    @FXML
    private TableView<ViewSal> table;
    @FXML
    private TableColumn<ViewSal, String> tid;
    @FXML
    private TableColumn<ViewSal, String> tinvoice;
    @FXML
    private TableColumn<ViewSal, String> tname;
    @FXML
    private TableColumn<ViewSal, String> tbasic;
    @FXML
    private TableColumn<ViewSal, String> tbonus;
    @FXML
    private TableColumn<ViewSal, String> thours;
    @FXML
    private TableColumn<ViewSal, String> trate;
    @FXML
    private TableColumn<ViewSal, String> tamount;
    @FXML
    private TableColumn<ViewSal, String> tadd;
    @FXML
    private TableColumn<ViewSal, String> tgross;
    @FXML
    private TableColumn<ViewSal, String> tepf;
    @FXML
    private TableColumn<ViewSal, String> tetf;
    @FXML
    private TableColumn<ViewSal, String> tadvance;
    @FXML
    private TableColumn<ViewSal, String> tsub;
    @FXML
    private TableColumn<ViewSal, String> tnet;
     
    ObservableList<ViewSal> oblist = FXCollections.observableArrayList();
    
    @FXML
    private Button btnc;
    @FXML
    private Button btnback;
    @FXML
    private Button btnforward;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        month.getItems().addAll(montharray);
        month.setOnAction(this::getMonth);
         
    }    

    @FXML
    private void ViewSalary(ActionEvent event) {
        
        try {
            Connection conn = MySqlConnect.ConnectDb();
            String sql = "select emp_id,invoice_num,emp_name,basic,bonus,ot_hours,ot_rate,ot,other,gross,epf,etf,advance,otherDeducation,net FROM salary WHERE month='"+month.getValue()+"' AND year='"+year.getText()+"' ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            
            while(rs.next()){
                oblist.add(new ViewSal(rs.getString("emp_id"),rs.getString("invoice_num"),rs.getString("emp_name"),rs.getString("basic"), rs.getString("bonus"), rs.getString("ot_hours"),rs.getString("ot_rate"), rs.getString("ot"),rs.getString("other"),rs.getString("gross"),rs.getString("epf"),rs.getString("etf"),rs.getString("advance"),rs.getString("otherDeducation"),rs.getString("net")));
            
            }
            
        } catch (Exception e) {
        }
        
       
        
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tinvoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbasic.setCellValueFactory(new PropertyValueFactory<>("basic"));
        tbonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        thours.setCellValueFactory(new PropertyValueFactory<>("hours"));
        trate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        tamount.setCellValueFactory(new PropertyValueFactory<>("ot"));
        tadd.setCellValueFactory(new PropertyValueFactory<>("add"));
        tgross.setCellValueFactory(new PropertyValueFactory<>("gross"));
        tepf.setCellValueFactory(new PropertyValueFactory<>("epf"));
        tetf.setCellValueFactory(new PropertyValueFactory<>("etf"));
        tadvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        tsub.setCellValueFactory(new PropertyValueFactory<>("sub"));
        tnet.setCellValueFactory(new PropertyValueFactory<>("net"));
        
        table.setItems(oblist);
       
        
    }

    @FXML
    private void getMonth(ActionEvent event) {
        
    }

    @FXML
    private void ClearAll(ActionEvent event) {
        
        table.getItems().clear();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnforward.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show(); 
    }

    @FXML
    private void forward(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnforward.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Print.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1400,725));
           primaryStage.show(); 
    }
    
}
