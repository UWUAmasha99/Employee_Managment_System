
package employeesalarymgtlogin;

import static employeesalarymgtlogin.MySqlConnect.ConnectDb;
import java.io.IOException;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.scene.control.ToggleGroup;

public class DetailsController implements Initializable {
    
    private String gender,emailPattern;
    
    int c1,c2,c3,c4,c5,d;
    String value1,value2,value3,value4,value5,value6,value7,value8,value9;

    @FXML
    private TextField txt_EmpContact;

    @FXML
    private TextField txt_EmpAddress;

    @FXML
    private ComboBox txt_EmpPosition;

    @FXML
    private TextField txt_EmpEmail;

    @FXML
    private RadioButton EmpRadioBtn1;
    
    @FXML
    private RadioButton EmpRadioBtn2;
    
    @FXML
    private TextField txt_EmpId;

    @FXML
    private TextField txt_EmpName;

    @FXML
    private TextField txt_EmpAge;

    @FXML
    private TextField txt_EmpNic;
    
    @FXML
    private Label requiredError;

    @FXML
    private Label txt_EmpIdError;

    @FXML
    private Label txt_EmpNameError;

    @FXML
    private Label txt_EmpNicError;

    @FXML
    private Label txt_EmpConatctError;
    
    @FXML
    private Label txt_EmpEmailError;
    
    @FXML
    private TextField txt_EmpId1;
    
    @FXML
    private TextField txt_EmpName1;
    
    @FXML
    private TextField txt_EmpAge1;
    
    @FXML
    private TextField txt_EmpNic1;
    
    @FXML
    private TextField txt_EmpGender1;
    
    @FXML
    private TextField txt_EmpContact1;
    
    @FXML
    private TextField  txt_EmpEmail1;
    
    @FXML
    private TextField  txt_EmpAddress1;
    
    @FXML
    private TextField  txt_EmpPosition1;
    
    
    @FXML
    private Button btnback1;

    @FXML
    private Button BtnSave;

    @FXML
    private Button BtnAdd;
    
    @FXML
    private Button BtnEdit;

    
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
    private AnchorPane pane_add;
    
    @FXML
    private AnchorPane pane_edit;
    
    
    @FXML
    private TableView<employees> tableEdit;

    @FXML
    private TableColumn<employees,String> table_EmpId1;

    @FXML
    private TableColumn<employees, String> table_EmpName1;

    @FXML
    private TableColumn<employees, String> table_EmpAge1;

    @FXML
    private TableColumn<employees, String> table_EmpNic1;

    @FXML
    private TableColumn<employees, String> table_EmpGender1;

    @FXML
    private TableColumn<employees, String> table_EmpContact1;

    @FXML
    private TableColumn<employees, String> table_EmpEmail1;

    @FXML
    private TableColumn<employees, String> table_EmpAddress1;

    @FXML
    private TableColumn<employees, String> table_EmpPosition1;
    
    ObservableList<employees> listM;
    
    ObservableList<employees> dataList;
    
    
    String requiredStyle = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 4;");
    String clearError = String.format("-fx-border-color: none;") ;

    int index = -1;
   
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    @FXML
    private ToggleGroup gen;
    @FXML
    private Button BtnClear1;
    
  
    @FXML
    public void addpaneShow(){
        
        pane_add.setVisible(true);
        pane_edit.setVisible(false);
    }
    
    @FXML
    public void editpaneShow(){
        
        pane_add.setVisible(false);
        pane_edit.setVisible(true);
    }
    
    public static ObservableList<employees> getDataemployees(){
        
        Connection conn = ConnectDb();
        ObservableList<employees> list = FXCollections.observableArrayList();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement("select * from empdetails");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            
            list.add(new employees(rs.getString("emp_id"),rs.getString("emp_name"),rs.getString("age"),rs.getString("nic"),rs.getString("gender"),rs.getString("contact_no"),rs.getString("email"),rs.getString("address"),rs.getString("position") ));
            }
   
        }catch (Exception e ){
        }
        
        return list;
    
    }  
    
    @FXML
    void getSelected(MouseEvent event){
        
        index = tableEdit.getSelectionModel().getSelectedIndex();
        
        if(index <= -1){
            return;
        }
        
        txt_EmpId1.setText(table_EmpId1.getCellData(index).toString());
        txt_EmpName1.setText(table_EmpName1.getCellData(index).toString());
        txt_EmpAge1.setText(table_EmpAge1.getCellData(index).toString());
        txt_EmpNic1.setText(table_EmpNic1.getCellData(index).toString());
        txt_EmpGender1.setText(table_EmpGender1.getCellData(index).toString());
        txt_EmpContact1.setText(table_EmpContact1.getCellData(index).toString());
        txt_EmpEmail1.setText(table_EmpEmail1.getCellData(index).toString());
        txt_EmpAddress1.setText(table_EmpAddress1.getCellData(index).toString());
        txt_EmpPosition1.setText(table_EmpPosition1.getCellData(index).toString());
       
        txt_EmpId1.setEditable(false);
        
    }
    
    public void updateTable(){
    
        table_EmpId1.setCellValueFactory(new PropertyValueFactory<employees,String>("id"));
        table_EmpName1.setCellValueFactory(new PropertyValueFactory<employees,String>("name"));
        table_EmpAge1.setCellValueFactory(new PropertyValueFactory<employees,String>("age"));
        table_EmpNic1.setCellValueFactory(new PropertyValueFactory<employees,String>("nic"));
        table_EmpGender1.setCellValueFactory(new PropertyValueFactory<employees,String>("gender"));
        table_EmpContact1.setCellValueFactory(new PropertyValueFactory<employees,String>("contact"));
        table_EmpEmail1.setCellValueFactory(new PropertyValueFactory<employees,String>("email"));
        table_EmpAddress1.setCellValueFactory(new PropertyValueFactory<employees,String>("address"));
        table_EmpPosition1.setCellValueFactory(new PropertyValueFactory<employees,String>("position"));
        
        listM = getDataemployees();
        tableEdit.setItems(listM);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txt_EmpPosition.getItems().addAll("Chief Executive","Manager","Accountant","Clark","Billing officer","Technician",
                "Security Officer","Driver","Cleaning Officer");
        updateTable();
    }  
    
  
    @FXML
    public void selectGender(ActionEvent event){
        
        if(EmpRadioBtn1.isSelected()){
            gender = EmpRadioBtn1.getText();
        }else if(EmpRadioBtn2.isSelected()){
            gender = EmpRadioBtn2.getText();
        }
    }
    
    @FXML
    public void autofill(KeyEvent k) {
        if (k.getCode().equals(KeyCode.ENTER)) {
            autofillFunc();
            txt_EmpId1.setEditable(false);
        }
    }
    
    @FXML
    public void clearNameError(MouseEvent event) {
        if(txt_EmpName1.getText().equals("Only letters,space & dots are allowed!")){
            txt_EmpName1.setText("");
         }
    }
    
    @FXML
    public void clearNicError(MouseEvent event) {
        if(txt_EmpNic1.getText().equals("Should be in new/old NIC format")){
            txt_EmpNic1.setText("");
         }
    }
    
    @FXML
    public void clearContactError(MouseEvent event) {
        if(txt_EmpContact1.getText().equals("0xxxxxxxxx/+94xxxxxxxxx format!")){
            txt_EmpContact1.setText("");
         }
    }
    
    @FXML
    public void clearEmailError(MouseEvent event) {
        if(txt_EmpEmail1.getText().equals("Should be in Correct email format")){
            txt_EmpEmail1.setText("");
         }
    }
    
    public void autofillFunc(){
        
        conn = MySqlConnect.ConnectDb();
        String sql = "select emp_id,emp_name,age,nic,gender,contact_no,email,address,position from empdetails where emp_id =?";
       
        
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_EmpId1.getText());
            ResultSet res = pst.executeQuery();
          
            if (res.next()) {
             
                    txt_EmpName1.setText(res.getString("emp_name"));
                    txt_EmpAge1.setText(res.getString("age"));
                    txt_EmpNic1.setText(res.getString("nic"));
                    txt_EmpGender1.setText(res.getString("gender"));
                    txt_EmpContact1.setText(res.getString("contact_no"));
                    txt_EmpEmail1.setText(res.getString("email"));
                    txt_EmpAddress1.setText(res.getString("address"));
                    txt_EmpPosition1.setText(res.getString("position"));
            }
    
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e);
        
        }
         
    }

    @FXML
    private void save(ActionEvent event) throws Exception {
        
        conn = MySqlConnect.ConnectDb();
        c1=0;c2=0;c3=0;c4=0;c5=0;
        
        try {
                
                pst = conn.prepareStatement("insert into empdetails(emp_id,emp_name,age,nic,gender,contact_no,email,address,position)"
                        + "values (?,?,?,?,?,?,?,?,?)");
                
                if (txt_EmpId.getText().isEmpty() || txt_EmpName.getText().isEmpty() || txt_EmpNic.getText().isEmpty() 
                        || ( !(EmpRadioBtn1.isSelected()||EmpRadioBtn2.isSelected()) )|| txt_EmpContact.getText().isEmpty()) {
                    requiredError.setStyle(requiredStyle);
                    requiredError.setVisible(true);
                    requiredError.setText("The *fields are required!");
                    c5=1;
                    
                }else{
                    requiredError.setVisible(false);
                    c5=0;
                }
                
                if(!txt_EmpName.getText().trim().isEmpty()){
                if (!txt_EmpName.getText().matches("^\\pL+[\\pL\\pZ\\pP]{0,}")){
                    txt_EmpNameError.setStyle(errorStyle);
                    txt_EmpNameError.setVisible(true);
                    txt_EmpNameError.setText("Only letters,space &  dots are allowed!");
                    c1=1;
                }else{
                    txt_EmpNameError.setVisible(false);
                    c1=0;
                }}
                
                if(!txt_EmpNic.getText().trim().isEmpty()){
                if (!txt_EmpNic.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
                    txt_EmpNicError.setStyle(errorStyle);
                    txt_EmpNicError.setVisible(true);
                    txt_EmpNicError.setText("Should be in new/old NIC format");                  
                    c2=1;
                 }else{
                    txt_EmpNicError.setVisible(false);
                    c2=0;
                }}
                
                if(!txt_EmpContact.getText().trim().isEmpty()){
                if (!txt_EmpContact.getText().matches("^(?:0|(?:\\+94))[0-9]{9,10}$")){
                    txt_EmpConatctError.setStyle(errorStyle);
                    txt_EmpConatctError.setVisible(true);
                    txt_EmpConatctError.setText("0xxxxxxxxx/+94xxxxxxxxx format!");
                    c3=1;
                }else{
                    txt_EmpConatctError.setVisible(false);
                    c3=0;
                }}
                
                if(!txt_EmpEmail.getText().trim().isEmpty()){
                if (!txt_EmpEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                    txt_EmpEmailError.setStyle(errorStyle);
                    txt_EmpEmailError.setVisible(true);
                    txt_EmpEmailError.setText("Should be in Correct email format");
                    c4=1;
                }else{
                     txt_EmpEmailError.setVisible(false);
                    c4=0;
                }}
                   
                if (c1==0 && c2==0 && c3==0 && c4==0 && c5==0 ){
                    
                    pst.setString(1,txt_EmpId.getText());
                    pst.setString(2,txt_EmpName.getText());

                    if (txt_EmpAge.getText().isEmpty()){
                        pst.setNull(3,Types.NULL);
                    }
                    else {pst.setString(3,txt_EmpAge.getText());}

                    pst.setString(4,txt_EmpNic.getText());

                    pst.setString(5,gender);
                    gender="";

                    pst.setString(6,txt_EmpContact.getText());

                    if (txt_EmpEmail.getText().isEmpty()){
                        pst.setNull(7,Types.NULL);
                    }   
                    else {pst.setString(7,txt_EmpEmail.getText());}

                    if (txt_EmpAddress.getText().isEmpty()){
                        pst.setNull(8,Types.NULL);
                    }
                    else {pst.setString(8,txt_EmpAddress.getText());}

                    if (txt_EmpPosition.getSelectionModel().isEmpty()){
                       pst.setNull(9,Types.NULL);   
                    }
                    else {pst.setString(9,txt_EmpPosition.getValue().toString());}

                    int status=pst.executeUpdate();
                    updateTable();
                    
                    if(status==1){
                                JOptionPane.showMessageDialog(null,"Data is saved successfully");

                    }

                    else{
                                JOptionPane.showMessageDialog(null,"Record failed");
                    }
                }
                txt_EmpId1.setEditable(true);
        }
        
        catch (Exception e)
        {
                JOptionPane.showMessageDialog(null,e);
        }
    }         
    
    @FXML
    public void delete(){
        
        conn = MySqlConnect.ConnectDb();
        String sql = "delete from empdetails where emp_id =?";
        
        try{
            
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_EmpId1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Record Deleted");
            updateTable();
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e);
        
        }
        txt_EmpId1.setEditable(true);
       
    }
    
    @FXML
    private void edit(ActionEvent event) throws Exception {
        
        conn = MySqlConnect.ConnectDb();
        c1=0;c2=0;c3=0;c4=0;c5=0;
        
        try {
            
                if (txt_EmpId1.getText().isEmpty() || txt_EmpName1.getText().isEmpty() || txt_EmpNic1.getText().isEmpty() 
                        || txt_EmpContact1.getText().isEmpty() ||txt_EmpGender1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null,"The *fields are required!");
                    c5=1;
                    
                }else{
                    requiredError.setVisible(false);
                    c5=0;
                }
                
                if(!txt_EmpName1.getText().trim().isEmpty()){
                if (!txt_EmpName1.getText().matches("^\\pL+[\\pL\\pZ\\pP]{0,}")){
                    txt_EmpName1.setStyle(errorStyle);
                    txt_EmpName1.setText("Only letters,space & dots are allowed!");
                    c1=1;
                }else{
                    txt_EmpName1.setStyle(clearError);
                    c1=0;
                }}
                
                if(!txt_EmpNic1.getText().trim().isEmpty()){
                if (!txt_EmpNic1.getText().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$")){
                    txt_EmpNic1.setStyle(errorStyle);
                    txt_EmpNic1.setText("Should be in new/old NIC format");                  
                    c2=1;
                 }else{
                    txt_EmpNic1.setStyle(clearError);
                    c2=0;
                }}
                
                if(!txt_EmpContact1.getText().trim().isEmpty()){
                if (!txt_EmpContact1.getText().matches("^(?:0|(?:\\+94))[0-9]{9,10}$")){
                    txt_EmpContact1.setStyle(errorStyle);
                    txt_EmpContact1.setText("0xxxxxxxxx/+94xxxxxxxxx format!");
                    c3=1;
                }else{
                    txt_EmpContact1.setStyle(clearError);
                    c3=0;
                }}
                
                if(!txt_EmpEmail1.getText().trim().isEmpty()){
                if (!txt_EmpEmail1.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
                    txt_EmpEmail1.setStyle(errorStyle);
                    txt_EmpEmail1.setText("Should be in Correct email format");
                    c4=1;
                }else{
                    txt_EmpEmail1.setStyle(clearError);
                    c4=0;
                }}
                 
                
                if (c1==0 && c2==0 && c3==0 && c4==0 && c5==0){
                    
                    value1 = txt_EmpId1.getText();
                    value2 = txt_EmpName1.getText();

                    if (txt_EmpAge1.getText().isEmpty()){
                        value3 = "";
                    }
                    else {
                        value3 = txt_EmpAge1.getText();
                    }

                    value4 = txt_EmpNic1.getText();
                    
                    value5 = txt_EmpGender1.getText();

                    value6 = txt_EmpContact1.getText();

                     if (txt_EmpEmail1.getText().isEmpty()){
                        value7 = "";
                    }
                    else {
                        value7 = txt_EmpEmail1.getText();
                    }

                    if (txt_EmpAddress1.getText().isEmpty()){
                        value8 = "";
                    }
                    else {
                        value8 = txt_EmpAddress1.getText();
                    }

                    if (txt_EmpPosition1.getText().isEmpty()){
                        value9 = "";   
                    }
                    else {
                        value9 = txt_EmpPosition1.getText();
                    }

                     String sql = "update empdetails set emp_id= '"+value1+"',emp_name = '"+value2+"',age = '"+value3+"',nic = '"+value4+"',"
                        + "gender = '"+value5+"',contact_no ='"+value6+"',email = '"+value7+"',address = '"+value8+"',position = '"+value9+"' "
                        + "where emp_id = '"+value1+"' ";
                
                    pst= conn.prepareStatement(sql);
                    int status=pst.executeUpdate();
                    updateTable();
                   
                    
                    if(status==1){
                                JOptionPane.showMessageDialog(null,"Record Updated");

                    }

                    else{
                                JOptionPane.showMessageDialog(null,"Record failed");
                    }
                }
            }
        
            catch (Exception e)
            {
                    JOptionPane.showMessageDialog(null,e);
            }
        
    }               
     
    @FXML
    private void clearAdd(ActionEvent event) throws Exception {
         
        try {
            
            txt_EmpId.setText("");
            txt_EmpName.setText("");
            txt_EmpAge.setText("");
            txt_EmpNic.setText("");
            EmpRadioBtn1.setSelected(false);
            EmpRadioBtn2.setSelected(false);
            txt_EmpContact.setText("");
            txt_EmpEmail.setText("");
            txt_EmpAddress.setText("");
            txt_EmpPosition.valueProperty().set(null);
        }
            
        catch (Exception e)
        {
                JOptionPane.showMessageDialog(null,e);
        }
    
    }
    
    @FXML
    private void clearEdit(ActionEvent event) throws Exception {
    
        try {
            
            txt_EmpId1.setEditable(true);
            txt_EmpId1.setText("");
            txt_EmpName1.setText("");
            txt_EmpAge1.setText("");
            txt_EmpNic1.setText("");
            txt_EmpGender1.setText("");
            txt_EmpContact1.setText("");
            txt_EmpEmail1.setText("");
            txt_EmpAddress1.setText("");
            txt_EmpPosition1.setText("");
           
        }
            
        catch (Exception e)
        {
                JOptionPane.showMessageDialog(null,e);
        }
    
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) btnback1.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
           primaryStage.setTitle("Add Employee Details");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }
            
    //menu bar
    @FXML
    private void printdetails(ActionEvent event) throws IOException {
           Stage stage = (Stage) btnback1.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Print.fxml"));
           primaryStage.setTitle("Add Employee Details");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show(); 
    }

    @FXML
    private void menuback(ActionEvent event) throws IOException {
        
           Stage stage = (Stage) btnback1.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
           primaryStage.setTitle("Add Employee Details");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
        
    }

    @FXML
    private void forward(ActionEvent event) throws IOException {
           Stage stage = (Stage) btnback1.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("Add Employee Details");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }
    
    
       @FXML
     private void menuclose(ActionEvent event) {
          Stage stage = (Stage) btnback1.getScene().getWindow();
         stage.close();
        
        
    }
     
    @FXML
    private void calsal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback1.getScene().getWindow();
            stage.close();
        Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Salary.fxml"));
           primaryStage.setTitle("Add Employee Details");
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
		stage = (Stage) btnback1.getScene().getWindow();
		stage.close();

                //move to the logout page
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                primaryStage.setTitle("Select Options");
                primaryStage.setScene(new Scene(root,1103,685));
                primaryStage.show();
                
                }

    }    
    
    
}