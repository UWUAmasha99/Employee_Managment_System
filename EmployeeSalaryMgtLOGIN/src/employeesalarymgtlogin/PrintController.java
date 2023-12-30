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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Kasuni
 */
public class PrintController implements Initializable {
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    private MenuItem DetailsMenue;
    @FXML
    private MenuItem BackMenue;
    @FXML
    private MenuItem ForwardMenue;
    @FXML
    private MenuItem LogOutMenue;
    @FXML
    private MenuItem CloseMenue;
    @FXML
    private Button btnview;
    private Button btnprint;
    @FXML
    private TextField txt_empid;
    private TextField txt_month;
    @FXML
    private TextField txt_year;
    @FXML
    private Button btnback;
    @FXML
    private MenuItem CalMenu;
    
    
    
    
     @FXML
    private TableView<PrintDataTable> TablePrint;
    @FXML
    private TableColumn<PrintDataTable, String> tblempid;
    @FXML
    private TableColumn<PrintDataTable, String> tblempname;
    @FXML
    private TableColumn<PrintDataTable, String> tblcontact;
    @FXML
    private TableColumn<PrintDataTable, String> tbladdress;
    
    @FXML
    private TableColumn<PrintDataTable, String> tblbasicsal;
    @FXML
    private TableColumn<PrintDataTable, String> tblnetsal;
    @FXML
    private TableColumn<PrintDataTable, String> ot;
    @FXML
    private TableColumn<PrintDataTable, String> bonus;
    @FXML
    private TableColumn<PrintDataTable, String> otherearns;
    @FXML
    private TableColumn<PrintDataTable, String> epf;
    @FXML
    private TableColumn<PrintDataTable, String> etf;
    @FXML
    private TableColumn<PrintDataTable, String> advance;
    @FXML
    private TableColumn<PrintDataTable, String> otherdeduction;
    @FXML
    private TableColumn<PrintDataTable, String> year;
    @FXML
    private TableColumn<PrintDataTable, String> month;
   
    
     ObservableList<PrintDataTable> oblist = FXCollections.observableArrayList();
  
    @FXML
    private Button btnslip;
    
    private AnchorPane slippane;
    @FXML
    private Pane SlipPane;
    @FXML
    private Tab tabview;
    @FXML
    private Tab tabslip;
    @FXML
    private Button btnprintslip;
    @FXML
    private TextField tabyr;
    @FXML
    private TextField tabmonth;
    @FXML
    private TextField tabid;
    @FXML
    private TextField tabname;
    @FXML
    private TextField tabcontact;
    @FXML
    private TextField tabaddress;
    @FXML
    private TextField tab_etf;
    @FXML
    private TextField tab_advance;
    @FXML
    private TextField tab_deduction;
    @FXML
    private TextField tab_net;
    @FXML
    private TextField tab_epf;
    @FXML
    private TextField tab_ot;
    @FXML
    private TextField tabb_sal;
    @FXML
    private TextField tab_bonus;
    @FXML
    private TextField tab_earning;
    
    
    int index=-1;
    @FXML
    private Button btnclear;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

       @FXML
    private void menuclose(ActionEvent event) {
         Stage stage = (Stage) btnback.getScene().getWindow();
         stage.close();
    }

    
    //button view,printall,print
    @FXML
    private void view(ActionEvent event)  {
        
       
        Connection conn =MySqlConnect.ConnectDb();
        try {
            
         ResultSet rs = conn.createStatement().executeQuery("SELECT sal.emp_id, emp.emp_name,emp.contact_no,emp.address,sal.month,sal.year,sal.basic,sal.bonus,sal.ot,sal.other, sal.epf,sal.etf,sal.advance,sal.otherDeducation , sal.net FROM empdetails emp JOIN salary sal ON emp.emp_id=sal.emp_id ORDER BY emp.emp_id ASC");
                 

         
         while(rs.next()){
        oblist.add(new PrintDataTable(rs.getString("year"),rs.getString("month") , rs.getString("emp_id"), rs.getString("emp_name"), rs.getString("contact_no"), rs.getString("address"),rs.getString("basic"),rs.getString("ot"),rs.getString("bonus"),rs.getString("other"),rs.getString("epf"),rs.getString("etf"),rs.getString("advance"),rs.getString("otherDeducation"),rs.getString("net")));
                
//                ,rs.getString("contact_no"),rs.getString("contact_no"),rs.getString("contact_no")));
        
      } 
        
        
        } catch (SQLException ex) {
            Logger.getLogger(PrintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        tblempid.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tblempname.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        tblcontact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblbasicsal.setCellValueFactory(new PropertyValueFactory<>("basicsal"));
        ot.setCellValueFactory(new PropertyValueFactory<>("ot"));
        bonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        otherearns.setCellValueFactory(new PropertyValueFactory<>("otherearnings"));
        epf.setCellValueFactory(new PropertyValueFactory<>("epf"));
        etf.setCellValueFactory(new PropertyValueFactory<>("etf"));
        advance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        otherdeduction.setCellValueFactory(new PropertyValueFactory<>("otherdeduction"));
        tblnetsal.setCellValueFactory(new PropertyValueFactory<>("netsal"));
        
        
        
  TablePrint.setItems(oblist);
  
  //search option
        
//     Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<PrintDataTable> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
                
                                
                
		txt_empid.textProperty().addListener((observable, oldValue, newValue) ->  {
			filteredData.setPredicate(PrintDataTable -> {
                        
                        
                    
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                    
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
//				
				if (PrintDataTable.getEmp_id().toLowerCase().indexOf(lowerCaseFilter) != -1)  {
                                    
					return true; // Filter matches first name.
				}
//                                
//                                
//                                
//                                else if (PrintDataTable.getEmp_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//				else if (String.valueOf(PrintDataTable.getAddress()).indexOf(lowerCaseFilter)!=-1)
//				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
                
//                txt_month.textProperty().addListener((observable, oldValue, newValue) ->  {
//			filteredData.setPredicate(PrintDataTable -> {
//                        
//                        
//                    
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//                                    
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (PrintDataTable.getMonth().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
//                                    
//					return true; // Filter matches first name.
//				}
////                                
////                                
////                                
////                                else if (PrintDataTable.getEmp_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
////					return true; // Filter matches last name.
////				}
////				else if (String.valueOf(PrintDataTable.getAddress()).indexOf(lowerCaseFilter)!=-1)
////				     return true;
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
                
                
                
                 txt_year.textProperty().addListener((observable, oldValue, newValue) ->  {
			filteredData.setPredicate(PrintDataTable -> {
                        
                        
                    
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
                                    
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (PrintDataTable.getYear().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                                    
					return true; // Filter matches first name.
				}
//                                
//                                
//                                
//                                else if (PrintDataTable.getEmp_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//				else if (String.valueOf(PrintDataTable.getAddress()).indexOf(lowerCaseFilter)!=-1)
//				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
                
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<PrintDataTable> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TablePrint.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TablePrint.setItems(sortedData);
//               
//                
        
        
        
    }

    private void printonly(ActionEvent event) {
        
        
      //print
              Printer printer = Printer.getDefaultPrinter();
              Stage dialogStage = new Stage(StageStyle.DECORATED);
              PrinterJob job = PrinterJob.createPrinterJob(printer);
              if (job != null) {
                 boolean showDialog =job.showPrintDialog(btnprint.getScene().getWindow());
                //  boolean showDialog = job.showPageSetupDialog(dialogStage);
                  if (showDialog) {
                      SlipPane.setScaleX(1);
                        SlipPane.setScaleY(1.5);
                        SlipPane.setTranslateX(-5);
                        SlipPane.setTranslateY(-6);
                      boolean success = job.printPage(  SlipPane);
                      if (success) {
                          job.endJob();
                      }
                       SlipPane.setTranslateX(0);
                       SlipPane.setTranslateY(0);
                       SlipPane.setScaleX(1.0);
                       SlipPane.setScaleY(1.0);
                  }

              }    
        
        
    }

    
    //back label

    @FXML
    private void back(ActionEvent event) throws IOException {
       Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
         Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
        
           
    }
       
    
    //menue bar
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

    @FXML
    private void calsal(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
          Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1300,685));
           primaryStage.show();
    }

    @FXML
    private void menuback(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
         Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }

    @FXML
    private void forward(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
         Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
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

   

    @FXML
    private void createslip(ActionEvent event)   {
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Create a Salary Slip");
		//alert.setHeaderText("About  Logout or Not!");
              
		alert.setContentText("Do you want to create Slip ? ");
		
		if(alert.showAndWait().get() == ButtonType.OK){
        
        
        
        index = TablePrint.getSelectionModel().getSelectedIndex();
        
        if(index <= -1){
            return;
        }
        
        
        tabyr.setText(year.getCellData(index).toString());
        tabmonth.setText(month.getCellData(index).toString());
        
        tabid.setText(tblempid.getCellData(index).toString());
        tabname.setText(tblempname.getCellData(index).toString());
        tabcontact.setText(tblcontact.getCellData(index).toString());
        tabaddress.setText(tbladdress.getCellData(index).toString());
        
        tabb_sal.setText(tblbasicsal.getCellData(index).toString());
        tab_ot.setText(ot.getCellData(index).toString());
        tab_bonus.setText(bonus.getCellData(index).toString());
        tab_earning.setText(otherearns.getCellData(index).toString());      
        tab_epf.setText(epf.getCellData(index).toString());
        tab_etf.setText(etf.getCellData(index).toString());
        tab_advance.setText(advance.getCellData(index).toString());
        tab_deduction.setText(otherdeduction.getCellData(index).toString());
        tab_net.setText(tblnetsal.getCellData(index).toString());
        
        
                }
                
        
        
    }

    @FXML
    private void printslip(ActionEvent event) {
        
        
           Printer printer = Printer.getDefaultPrinter();
              Stage dialogStage = new Stage(StageStyle.DECORATED);
              PrinterJob job = PrinterJob.createPrinterJob(printer);
              if (job != null) {
                 boolean showDialog =job.showPrintDialog(btnprintslip.getScene().getWindow());
                //  boolean showDialog = job.showPageSetupDialog(dialogStage);
                  if (showDialog) {
                      SlipPane.setScaleX(1);
                       SlipPane.setScaleY(1.5);
                       SlipPane.setTranslateX(-5);
                       SlipPane.setTranslateY(-6);
                      boolean success = job.printPage(SlipPane);
                      if (success) {
                          job.endJob();
                      }
                       SlipPane.setTranslateX(0);
                       SlipPane.setTranslateY(0);
                       SlipPane.setScaleX(1.0);
                       SlipPane.setScaleY(1.0);
                  }

              }
        
    }
        

    @FXML
    private void clear(ActionEvent event){
         
             //oblist.clear();
            // txt_month.setText("");
             txt_empid.setText("");
             txt_year.setText("");
           
        }    
}
