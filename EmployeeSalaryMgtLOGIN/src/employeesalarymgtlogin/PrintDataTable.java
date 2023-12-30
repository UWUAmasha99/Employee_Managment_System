/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeesalarymgtlogin;

/**
 *
 * @author Kasuni
 */
public class PrintDataTable {
    String emp_id, emp_name, address,contact_no,month, year, basicsal,netsal,ot,bonus,otherearnings,epf,etf,advance,otherdeduction;

    public PrintDataTable(String year, String month, String emp_id, String emp_name, String contact_no, String address, String basicsal, String ot, String bonus, String otherearnings , String epf, String etf, String advance, String otherdeduction, String netsal) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.address = address;
        this.contact_no = contact_no;
        this.month = month;
        this.year = year;
        this.basicsal = basicsal;
        this.netsal = netsal;
        this.ot = ot;
        this.bonus = bonus;
        this.otherearnings = otherearnings;
        this.epf = epf;
        this.etf = etf;
        this.advance = advance;
        this.otherdeduction = otherdeduction;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBasicsal() {
        return basicsal;
    }

    public void setBasicsal(String basicsal) {
        this.basicsal = basicsal;
    }

    public String getNetsal() {
        return netsal;
    }

    public void setNetsal(String netsal) {
        this.netsal = netsal;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getOtherearnings() {
        return otherearnings;
    }

    public void setOtherearnings(String otherearnings) {
        this.otherearnings = otherearnings;
    }

    public String getEpf() {
        return epf;
    }

    public void setEpf(String epf) {
        this.epf = epf;
    }

    public String getEtf() {
        return etf;
    }

    public void setEtf(String etf) {
        this.etf = etf;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getOtherdeduction() {
        return otherdeduction;
    }

    public void setOtherdeduction(String otherdeduction) {
        this.otherdeduction = otherdeduction;
    }
    

    
}
