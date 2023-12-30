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
public class ViewSal {
    
    
    String id,name,invoice,hours,basic,bonus,rate,ot,add,gross,epf,etf,advance,sub,net;

    public ViewSal(String id, String name, String invoice, String basic, String bonus,String hours, String rate, String ot, String add, String gross, String epf, String etf, String advance, String sub, String net) {
        this.id = id;
        this.name = name;
        this.invoice = invoice;        
        this.basic = basic;
        this.bonus = bonus;
        this.hours = hours;
        this.rate = rate;
        this.ot = ot;
        this.add = add;
        this.gross = gross;
        this.epf = epf;
        this.etf = etf;
        this.advance = advance;
        this.sub = sub;
        this.net = net;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInvoice() {
        return invoice;
    }
   

    public String getBasic() {
        return basic;
    }

    public String getBonus() {
        return bonus;
    }
    
    public String getHours() {
        return hours;
    }

    public String getRate() {
        return rate;
    }

    public String getOt() {
        return ot;
    }

    public String getAdd() {
        return add;
    }

    public String getGross() {
        return gross;
    }

    public String getEpf() {
        return epf;
    }

    public String getEtf() {
        return etf;
    }

    public String getAdvance() {
        return advance;
    }

    public String getSub() {
        return sub;
    }

    public String getNet() {
        return net;
    }

    
    
    


    
}
