package mediatheque;
import java.util.Calendar;

import java.util.Date;

public class Pret {
    private int numero;
    private Date debut;
    private Date fin;

    public Pret(int numero, Date debut, Date fin) {
        this.numero = numero;
        this.debut = debut;
        this.fin = fin;
    }
    
    public Date getFin() {
    	return this.fin;
    }

    /*
    public String toString() {
        return "";
    }
    */
    
    public String toJson() {
    	String strPret = new String();
    	strPret = "{ \"Pret\": {\n" + 
   			 " \"numero\": \"" + this.numero + "\"\n" + 
   			 " \"debut\": \"" + this.debut + "\"\n" +
   			 " \"fin\": \"" + this.fin + "\"\n" +
   			 "}\n";
    	return strPret;
    }
}
