package mediatheque;
import java.text.SimpleDateFormat;
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
    
    public Date getDebut() {
    	return debut;
    }
    
    public Date getFin() {
    	return fin;
    }
    
    public String getFormatDebut(SimpleDateFormat format) {
    	return format.format(debut);
    }
    
    public String getFormatFin(SimpleDateFormat format) {
    	return format.format(fin);
    }

    
    public String toString() {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String str = "";
    	str += "Date du pret : " + format.format(debut) + "\n";
    	str += "Date butoire : " + format.format(fin) + "\n";
        return str;
    }
    
    
    public String toJson() {
    	String strPret = new String();
    	strPret = "{ \"Pret\": {\n" + 
   			 "                      \"numero\": \"" + this.numero + "\"\n" + 
   			 "                      \"debut\": \"" + this.debut + "\"\n" +
   			 "                      \"fin\": \"" + this.fin + "\"\n" +
   			 "                        }\n" + "               }\n";
    	return strPret;
    }
}
