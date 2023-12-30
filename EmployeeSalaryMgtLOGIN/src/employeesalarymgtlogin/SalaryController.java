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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Kasuni
 */
public class SalaryController implements Initializable {

    @FXML
    private Button btnGross;
    @FXML
    private Button btnnet;
    @FXML
    private MenuItem DetailsMenue;
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
    
    
    private TextField emp_id;

    @FXML
    private TextField gross;
    @FXML
    private TextField net;
    @FXML
    private TextField empid;
    
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    @FXML
    private TextField xbasic;
    @FXML
    private TextField xot;
    @FXML
    private TextField xother;
    @FXML
    private TextField xbonus;
    @FXML
    private TextField xepf;
    @FXML
    private TextField xadv;
    @FXML
    private TextField xother2;
    @FXML
    private TextField xetf;
    @FXML
    private TextField empname;
    @FXML
    private TextField emppos;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnedit;
    @FXML
    private Button btndelete;
    private TextField empmonth;
    @FXML
    private TextField xot1;
    @FXML
    private TextField xot2;
    @FXML
    private TextField eyear;
    @FXML
    private ComboBox<String> emonth;
    
    public String[] monthArray = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    @FXML
    private Button btnclear;
    @FXML
    private TextField empsearch;
    @FXML
    private Button sch;
    @FXML
    private TextField einvoice;
    @FXML
    private Button btnall;
    @FXML
    private Button btnback;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        emonth.getItems().addAll(monthArray);
        emonth.setOnAction(this::getMonth);
    }   
    
    
    
    
    
    
    

    double basic,othours,otrate,ot,epf,etf,SalGross,SalNet;
    
    @FXML
    private void CalGross(ActionEvent event) {
        
        if(xbonus.getText().isEmpty() | xot.getText().isEmpty() | xother.getText().isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter Values for all Feilds");            
            alert.showAndWait();

        }        
        else if((validateBonus() & validateOtHours() & validateOther())){  
        double bonus,othours,ot,other;
        
        basic = Double.parseDouble(xbasic.getText());
        bonus = Double.parseDouble(xbonus.getText());
        othours = Double.parseDouble(xot.getText());
      
       // ot = othours*otrate;
       // xot2.setText(String.valueOf(othours*otrate)); 
        ot = Double.parseDouble(xot2.getText());
        other = Double.parseDouble(xother.getText());
        
        SalGross = (basic+bonus+ot+other);      
        gross.setText(String.valueOf(SalGross));
    }       
    }

    @FXML
    private void CalNet(ActionEvent event) {
        
        if(xadv.getText().isEmpty() | xother2.getText().isEmpty() | gross.getText().isEmpty()){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter Values for all Feilds");            
            alert.showAndWait();       
        }        
        else if(validateAdvance() & validateOtherDeducations()){
        double adv,sepf,setf,otr,salGross,deduc;
        
        sepf = Double.parseDouble(xepf.getText());
        setf = Double.parseDouble(xetf.getText());
        adv = Double.parseDouble(xadv.getText());
        otr = Double.parseDouble(xother2.getText());
        
        deduc = (sepf+setf+adv+otr);
        salGross = Double.parseDouble(gross.getText());
        
        SalNet = (salGross - deduc);
        
        net.setText(String.valueOf(SalNet));
        
    }

    }


    @FXML
    private void Add(ActionEvent event) {
        conn = MySqlConnect.ConnectDb();
        
        if(net.getText().isEmpty()){
        
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Calculate Net Salary");            
            alert.showAndWait(); 
        } 
        else
            
        try {
          pst = conn.prepareStatement("insert into salary(emp_id,month,year,emp_name,position,invoice_num,basic,bonus,ot_hours,ot_rate,ot,other,gross,epf,etf,advance,otherDeducation,net)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  
          
                pst.setString(1,empid.getText()); 
                pst.setString(2,emonth.getValue());                                
                pst.setString(3,eyear.getText());
                pst.setString(4,empname.getText());
                pst.setString(5,emppos.getText()); 
                pst.setString(6,einvoice.getText());
                pst.setString(7,xbasic.getText());
                pst.setString(8,xbonus.getText());
                pst.setString(9,xot.getText());
                pst.setString(10,xot1.getText());
                pst.setString(11,xot2.getText());                
                pst.setString(12,xother.getText());
                pst.setString(13,gross.getText());
                pst.setString(14,xepf.getText());
                pst.setString(15,xetf.getText());
                pst.setString(16,xadv.getText());
                pst.setString(17,xother2.getText());
                pst.setString(18,net.getText());
          

          int status = pst.executeUpdate();
                
                if(status==1){
                    JOptionPane.showMessageDialog(null,"Data is saved successfully");
               
                }
                
                else{
                    JOptionPane.showMessageDialog(null,"Record failed");
                }
            
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Duplicate Value");
                     eyear.setText("");
                     emonth.setValue("");
                     empid.setText("");                    
                     empname.setText("");
                     emppos.setText("");
                     einvoice.setText("");
                     xbasic.setText("");
                     xbonus.setText("");
                     xot.setText("");
                     xot1.setText("");
                     xot2.setText("");
                     xother.setText("");
                     gross.setText("");
                     xepf.setText("");
                     xetf.setText("");
                     xadv.setText("");
                     xother2.setText("");
                     net.setText("");
                     empsearch.setText("");
                     empid.setEditable(true);
          
        }
    
    }



    
    private void EmpData(KeyEvent event) {
     // if(event.getCode().equals(KeyCode.ENTER)){
       
    }

    @FXML
    private void EmployeeData(KeyEvent event) {

               //IncorrectEmpID()
          if(validateYear() && event.getCode().equals(KeyCode.ENTER)){
         // IncorrectEmpID();
          // checkEmpID(empid.getText());
             String sql = "select emp_name,position from empdetails where emp_id='"+empid.getText()+"' ";
             
             try {
              //  IncorrectEmpID();
                 conn = MySqlConnect.ConnectDb();
                 pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                 
                 String i_year =  eyear.getText();
                 String i_month = emonth.getValue();
                 String i_id = empid.getText();
                 
                 String invoice = i_id+i_year+i_month;
                
                      while (rs.next())
                        {
                         String foundType = rs.getString(1);
                         String add1 =rs.getString("emp_name");
                         empname.setText(add1);

                         String add2 =rs.getString("position");
                         emppos.setText(add2);
                         
                         einvoice.setText(invoice);
                     
                                             
                      if(add2.equals("Chief Executive")){
                            basic = 150000.00;
                            xbasic.setText(String.valueOf(basic));
                      }
                      else if(add2.equals("Manager")){
                            basic = 135000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Accountant")){
                            basic = 120000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Clark")){
                            basic = 95000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Billing officer")){
                            basic = 75000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Technician")){
                            basic = 60000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Security Officer")){
                            basic = 35000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Driver")){
                            basic = 30000.00;
                            xbasic.setText(String.valueOf(basic));
                      }else if(add2.equals("Cleaning Officer")){
                            basic = 32000.00;
                            xbasic.setText(String.valueOf(basic));
                      }
                      
                      if(add2.equals("Clark") || add2.equals("Billing officer") || add2.equals("Technician") || add2.equals("Security Officer") || add2.equals("Driver") || add2.equals("Cleaning Officer"))
                      {                          
                          otrate = basic*0.2/100;
                          xot1.setText(String.valueOf(otrate));
                      }else{
                          otrate = 0;
                          xot1.setText(String.valueOf(otrate));
                          xot.setText(String.valueOf(0));
                          xot2.setText(String.valueOf(0));
                      }
                      
                      epf = basic*8/100;
                      xepf.setText(String.valueOf(epf));
                      etf = basic*3/100;
                      xetf.setText(String.valueOf(etf));
                      
                         empid.setEditable(false);
                         empname.setEditable(false);
                         emppos.setEditable(false);
                         einvoice.setEditable(false);
                         xbasic.setEditable(false);
                         xot1.setEditable(false);
                         xepf.setEditable(false);
                         xetf.setEditable(false);
                      
                        }
                           
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
               //  JOptionPane.showMessageDialog(null,"Record failed");
             }         
         }
    }

    @FXML
    private void Update(ActionEvent event) {
        
         try{
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Conform ");
		alert.setHeaderText("Updating");
              
		alert.setContentText("Do you want to really update this record ?");
		
		if(alert.showAndWait().get() == ButtonType.OK){
           
                         
                String value1 = eyear.getText();
                String value2 = emonth.getValue();
                String value3 = empid.getText();
                String value4 = empname.getText();                
                String value5 = xbasic.getText();
                String valuei = einvoice.getText();
                String value6 = xbonus.getText();
                String value7 = xot.getText();               
                String value8 = xot1.getText();
                String value9 = xot2.getText();
                String value10 = xother.getText();
                String value11 = gross.getText();
                String value12 = xepf.getText();
                String value13 = xetf.getText();
                String value14 = xadv.getText();
                String value15 = xother2.getText();
                String value16 = emppos.getText();
                String value17 = net.getText();
                
                String i_year =  eyear.getText();
                String i_month = emonth.getValue();
                String i_id = empid.getText();
                 
                 String vinvoice = i_id+i_year+i_month;
                 einvoice.setText(vinvoice);
                

                String sql= "update salary set year='"+value1+"', "
                        + "month='"+value2+"',emp_id='"+value3+"',emp_name='"+value4+"',basic='"+value5+"', invoice_num='"+vinvoice+"',bonus='"+value6+"',"
                        + "ot_hours='"+value7+"',ot_rate='"+value8+"', ot = '"+value9+"', "
                        + "other = '"+value10+"', gross ='"+value11+"', "
                        + "epf ='"+value12+"', etf ='"+value13+"', advance ='"+value14+"', otherDeducation ='"+value15+"',position='"+value16+"', net ='"+value17+"'   "
                        + " where invoice_num='"+valuei+"' ";

                pst=conn.prepareStatement(sql);
                pst.execute();
              //  JOptionPane.showMessageDialog(null, "Record Updated");
                
                 empid.setEditable(true);
                 empname.setEditable(true);
                 emppos.setEditable(true);
                 einvoice.setEditable(true);
                 xbasic.setEditable(true);
                 xot1.setEditable(true);
                 xepf.setEditable(true);
                 xetf.setEditable(true);
                
                 }

            }catch(Exception e){
               // JOptionPane.showMessageDialog(null, e);
                JOptionPane.showMessageDialog(null,"Duplicate Value. Can't Update");
                     eyear.setText("");
                     emonth.setValue("");
                     empid.setText("");                    
                     empname.setText("");
                     emppos.setText("");
                     einvoice.setText("");
                     xbasic.setText("");
                     xbonus.setText("");
                     xot.setText("");
                     xot1.setText("");
                     xot2.setText("");
                     xother.setText("");
                     gross.setText("");
                     xepf.setText("");
                     xetf.setText("");
                     xadv.setText("");
                     xother2.setText("");
                     net.setText("");
                     empsearch.setText("");
            }finally {

                try{
                    rs.close();
                    pst.close();

                }
                catch(Exception e){

                }
        }
        
        
         
    }

    @FXML
    private void Clear(ActionEvent event) {
                     empid.setText("");                    
                     empname.setText("");
                     emppos.setText("");
                     einvoice.setText("");
                     xbasic.setText("");
                     xbonus.setText("");
                     xot.setText("");
                     xot1.setText("");
                     xot2.setText("");
                     xother.setText("");
                     gross.setText("");
                     xepf.setText("");
                     xetf.setText("");
                     xadv.setText("");
                     xother2.setText("");
                     net.setText("");
                     empsearch.setText("");
                     
                 empid.setEditable(true);
                 empname.setEditable(true);
                 emppos.setEditable(true);
                 einvoice.setEditable(true);
                 xbasic.setEditable(true);
                 xot1.setEditable(true);
                 xepf.setEditable(true);
                 xetf.setEditable(true);
    }

    @FXML
    private void Delete(ActionEvent event) {
        
       
         try{
             
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Conform");
		alert.setHeaderText("  Deleting ");
              
		alert.setContentText("Do you want to really delete this record ?");
		
		if(alert.showAndWait().get() == ButtonType.OK){
            
                String sql ="delete from salary where emp_id=? AND (month=? AND year=? ) ";
                pst=conn.prepareStatement(sql);
                pst.setString(1, empid.getText());
                pst.setString(2, emonth.getValue());
                pst.setString(3, eyear.getText());
                pst.execute();
             
              //  JOptionPane.showMessageDialog(null,"Record Deleted");
                     empsearch.setText("");
                     eyear.setText("");
                     emonth.setValue("");
                     empid.setText("");                    
                     empname.setText("");
                     emppos.setText("");
                     einvoice.setText("");
                     xbasic.setText("");
                     xbonus.setText("");
                     xot.setText("");
                     xot1.setText("");
                     xot2.setText("");
                     xother.setText("");
                     gross.setText("");
                     xepf.setText("");
                     xetf.setText("");
                     xadv.setText("");
                     xother2.setText("");
                     net.setText("");
                     
                 empid.setEditable(true);
                 empname.setEditable(true);
                 emppos.setEditable(true);
                 einvoice.setEditable(true);
                 xbasic.setEditable(true);
                 xot1.setEditable(true);
                 xepf.setEditable(true);
                 xetf.setEditable(true);
                 
                }
                

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);
            }finally {

                try{
                    rs.close();
                    pst.close();

                }
                catch(Exception e){

                }
            }

    }

    @FXML
    private void SearchData(KeyEvent event) {
        
       
        if(event.getCode().equals(KeyCode.ENTER)){
            
             String sql = "select emp_id,emp_name,position,invoice_num,basic,bonus,ot_hours,ot_rate,ot,other,gross,epf,etf,advance,otherDeducation,net FROM salary WHERE emp_id='"+empsearch.getText()+"' AND (month='"+emonth.getValue()+"' AND year='"+eyear.getText()+"') ";
             try {
                 conn = MySqlConnect.ConnectDb();
                 pst=conn.prepareStatement(sql);
                 rs=pst.executeQuery();
                
                      while (rs.next())
                        {
                         String foundType = rs.getString(1);
                         
                         String adn0 =rs.getString("emp_id");
                         empid.setText(adn0);
                         
                         String adn1 =rs.getString("emp_name");
                         empname.setText(adn1);

                         String adn2 =rs.getString("position");
                         emppos.setText(adn2);
                         
                         String adni =rs.getString("invoice_num");
                         einvoice.setText(adni);
                         
                         String adn3 =rs.getString("basic");
                         xbasic.setText(adn3);
                         
                         String adn4 =rs.getString("bonus");
                         xbonus.setText(adn4);
                         
                         String adn5 =rs.getString("ot_hours");
                         xot.setText(adn5);
                         
                         String adn6 =rs.getString("ot_rate");
                         xot1.setText(adn6);
                         
                         String adn7 =rs.getString("ot");
                         xot2.setText(adn7);
                         
                         String adn8 =rs.getString("other");
                         xother.setText(adn8);
                         
                         String adn9 =rs.getString("gross");
                         gross.setText(adn9);
                         
                         String adn10 =rs.getString("epf");
                         xepf.setText(adn10);
                         
                         String adn11 =rs.getString("etf");
                         xetf.setText(adn11);
                         
                         String adn12 =rs.getString("advance");
                         xadv.setText(adn12);
                         
                         String adn13 =rs.getString("otherDeducation");
                         xother2.setText(adn13);
                         
                         String adn14 =rs.getString("net");
                         net.setText(adn14);
                         
                      
                        } 
                      
                 empid.setEditable(false);
                 empname.setEditable(false);
                 emppos.setEditable(false);
                 einvoice.setEditable(false);
                 xbasic.setEditable(false);
                 xot1.setEditable(false);
                 xepf.setEditable(false);
                 xetf.setEditable(false);
                 
                
                           
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
             }         
         }     
    }

    @FXML
    private void SalarySearch(ActionEvent event) {
        //IncorrectSearch()
        if(eyear.getText().isEmpty()){  
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Enter Valid Year and Month");            
            alert.showAndWait();
           
        }
        else{
         String sql = "select emp_id,emp_name,position,invoice_num,basic,bonus,ot_hours,ot_rate,ot,other,gross,epf,etf,advance,otherDeducation,net FROM salary WHERE emp_id='"+empsearch.getText()+"' AND (month='"+emonth.getValue()+"' AND year='"+eyear.getText()+"') ";
             try {
                 conn = MySqlConnect.ConnectDb();
                 pst=conn.prepareStatement(sql);
                 
                 rs=pst.executeQuery();
                
                 
                      while (rs.next())
                        {
                         String foundType = rs.getString(1);
                         
                         String adn0 =rs.getString("emp_id");
                         empid.setText(adn0);
                         
                         String adn1 =rs.getString("emp_name");
                         empname.setText(adn1);

                         String adn2 =rs.getString("position");                        
                         emppos.setText(adn2);
                         
                         String adni =rs.getString("invoice_num");
                         einvoice.setText(adni);
                         
                         String adn3 =rs.getString("basic");
                         xbasic.setText(adn3);
                         
                         String adn4 =rs.getString("bonus");
                         xbonus.setText(adn4);
                         
                         String adn5 =rs.getString("ot_hours");
                         xot.setText(adn5);
                         
                         String adn6 =rs.getString("ot_rate");
                         xot1.setText(adn6);
                         
                         String adn7 =rs.getString("ot");
                         xot2.setText(adn7);
                         
                         String adn8 =rs.getString("other");
                         xother.setText(adn8);
                         
                         String adn9 =rs.getString("gross");
                         gross.setText(adn9);
                         
                         String adn10 =rs.getString("epf");
                         xepf.setText(adn10);
                         
                         String adn11 =rs.getString("etf");
                         xetf.setText(adn11);
                         
                         String adn12 =rs.getString("advance");
                         xadv.setText(adn12);
                         
                         String adn13 =rs.getString("otherDeducation");
                         xother2.setText(adn13);
                         
                         String adn14 =rs.getString("net");
                         net.setText(adn14);
                         
                      
                        }                    
                 
                 //empid.setEditable(false);
                 empname.setEditable(false);
                 emppos.setEditable(false);
                 einvoice.setEditable(false);
                 xbasic.setEditable(false);
                 xot1.setEditable(false);
                 xepf.setEditable(false);
                 xetf.setEditable(false);
                           
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
               // JOptionPane.showMessageDialog(null,"Record failed");
               
               
             }         
        
        }
    }

    private void GoNext(KeyEvent event) {
         
    }

    @FXML
    private void Next(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)){
            xot2.requestFocus();
            double xx = Double.parseDouble(xot.getText());
            double yy = Double.parseDouble(xot1.getText());
            double oo = xx*yy;
            xot2.setText(String.valueOf(oo));
            xot2.setEditable(false);
        }
       
    }

    @FXML
    private void EditOtRate(KeyEvent event) {
       
        
    }

    @FXML
    private void getMonth(ActionEvent event) {
        
        String getmonth = emonth.getValue();
        emonth.setValue(getmonth);
        
    }

    @FXML
    private void yearValidate(KeyEvent evt) {
        
//        String c = evt.getText();
//        if(evt.getCode().equals(KeyCode.ENTER)){
//        emonth.requestFocus();
//        }else if(evt.getCharacter().is){
//        eyear.setEditable(true);
//       // AncestorListener listener = null;
//     //   eyear.addAncestorListener(listener);
//        }else{
//        eyear.setEditable(false);
//        JOptionPane.showMessageDialog(this, "please enter valid charactors!", "Error!",JOptionPane.ERROR_MESSAGE);
//        eyear.setText(null);
//        eyear.grabFocus(); 
//        }
    }

   
private boolean validateYear(){

    Pattern p = Pattern.compile("[0-9]{4,4}$");
    Matcher m = p.matcher(eyear.getText());
    if(m.find() && (m.group().equals(eyear.getText()))){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Year");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Year");
        empid.setEditable(true);
        alert.showAndWait();
        
        return false;
    }
} 

   private boolean validateBonus(){

    Pattern p = Pattern.compile("[0-9]*(\\.[0-9]*)?");
    Matcher m = p.matcher(xbonus.getText());
    if(m.find() && m.group().equals(xbonus.getText())){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Value For Bonus ");
      //  xbonus.setText(null);
        alert.showAndWait();
        
        return false;
    }  
}  
   private boolean validateOther(){

    Pattern p = Pattern.compile("[0-9]*(\\.[0-9]*)?");
    Matcher m = p.matcher(xother.getText());
    if(m.find() && m.group().equals(xother.getText())){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Value for other Amount");
      //  xother.setText(null);
        alert.showAndWait();
        
        return false;
    }
}  
   
   private boolean validateOtHours(){

    Pattern p = Pattern.compile("[0-9]+");
    Matcher m = p.matcher(xot.getText());
    if(m.find() && m.group().equals(xot.getText())){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Value for OT Hours");
     //   xot.setText(null);
        alert.showAndWait();
        
        return false;
    }
}  

    private boolean validateAdvance(){

    Pattern p = Pattern.compile("[0-9]*(\\.[0-9]*)?");
    Matcher m = p.matcher(xadv.getText());
    if(m.find() && m.group().equals(xadv.getText())){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Value for Advance");
       // xadv.setText(null);
        alert.showAndWait();
        
        return false;
    }
} 
    
    private boolean validateOtherDeducations(){

    Pattern p = Pattern.compile("[0-9]*(\\.[0-9]*)?");
    Matcher m = p.matcher(xother2.getText());
    if(m.find() && m.group().equals(xother2.getText())){
        return true;
    }else{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error!");
        alert.setHeaderText(null);
        alert.setContentText("Enter Valid Value for Other Deductions");
    //    xother2.setText(null);
        alert.showAndWait();
        
        return false;
    }
} 

    @FXML
    private void MouseClickForOtAmount(MouseEvent event) {
            double x = Double.parseDouble(xot.getText());
            double y = Double.parseDouble(xot1.getText());
            double o = x*y;
            xot2.setText(String.valueOf(o));
            xot2.setEditable(false);
        
    }
  
//   private boolean IncorrectSearch(){
//        conn = MySqlConnect.ConnectDb();
//        String sql = "SELECT emp_id,month,year FROM salary where emp_id != ? AND (month != ? AND year != ?) ";
//       
//       try {
//            conn = MySqlConnect.ConnectDb();
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, empsearch.getText());
//            pst.setString(2, emonth.getValue());
//            pst.setString(3, eyear.getText());
//            rs=pst.executeQuery();
//            
//            while (rs.next()){
//            JOptionPane.showMessageDialog(null, " Enter Required Values ");
//            
//        return false;
//    }
//           
//        } catch (Exception e) {
//        }
//       
//       
//        return false;
//    }
//   


    @FXML
    private void ViewAll(ActionEvent event) {
        
            try {
                
                btnall.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("ViewSalary.fxml"));
                
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
            
        } catch (Exception e) {
        }
    }
    
    
//         private void IncorrectEmpID(){
//        conn = MySqlConnect.ConnectDb();
//        String sql = "SELECT emp_id FROM empdetails where emp_id != ? ";
//       
//       try {
//            conn = MySqlConnect.ConnectDb();
//            pst = conn.prepareStatement(sql);
//            pst.setString(1, empid.getText());            
//            rs=pst.executeQuery();
//            
//       // JOptionPane.showMessageDialog(null, " Enter Valide Employee ID ");  
//            if(rs.next()){
//              JOptionPane.showMessageDialog(null, " Enter Valide Employee ID ");  
//              
//            }           
//        } catch (Exception e) {
//        }
//       
//       
//        
//    }
    
    
    private boolean IncorrectEmpID() {
        
    // String sql = "SELECT emp_id FROM empdetails where emp_id !='"+empid.getText()+"' ";
       try {
                   conn = MySqlConnect.ConnectDb();
        String sql = "SELECT emp_id FROM empdetails where emp_id = ? ";
            conn = MySqlConnect.ConnectDb();
            pst = conn.prepareStatement(sql);
            pst.setString(1, empid.getText());            
            rs=pst.executeQuery();  
            if(rs.next()){
            JOptionPane.showMessageDialog(null, " Enter Valide Employee ID ");
            return false;
            }
        } catch (Exception e) {
         
            
            //  JOptionPane.showMessageDialog(null, " Enter Valide Employee ID ");  
              
            
        } 
       
    return false;
    }
         
   
//    String sql = "select emp_name,position from empdetails where emp_id='"+empid.getText()+"' ";
//             try {
//                 conn = MySqlConnect.ConnectDb();
//                 pst=conn.prepareStatement(sql);
//                 rs=pst.executeQuery();
    

// 
//    public boolean displayModalPopup(String message, String yesmessage, String nomessage) {
//	Alert alert = new Alert(AlertType.CONFIRMATION);
//	alert.setTitle("Duplicate data warning");
//	alert.setContentText("Do you want to Delete data ?");
//
//	Optional<ButtonType> result = alert.showAndWait();
//	if (result.get() == ButtonType.OK){
//		return true;
//        }else 
//	return false;
//}

    @FXML
    private void EmpDetails(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
            stage.close();
        Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Details.fxml"));
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
           Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
    }

    @FXML
    private void forward(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("Print.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1376,725));
           primaryStage.show();
    }

    @FXML
    private void menulogout(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
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
    private void menuclose(ActionEvent event) {
        Stage stage = (Stage) btnback.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnback.getScene().getWindow();
           stage.close();
           Stage primaryStage = new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("SelectOption.fxml"));
           primaryStage.setTitle("ABC Company (PVT) Ltd - Salary Management System ");
           primaryStage.setScene(new Scene(root,1103,685));
           primaryStage.show();
        
    }
  
}
