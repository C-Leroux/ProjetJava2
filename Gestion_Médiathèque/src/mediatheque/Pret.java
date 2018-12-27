package mediatheque;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
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
    
    public Date getFin() {
    	return this.fin;
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
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	strPret = "{ \"Pret\": {" + 
   			 " \"numeroP\": \"" + this.numero + "\"" + 
   			 " \"debut\": \"" + format.format(debut) + "\"" +
   			 " \"fin\": \"" + format.format(fin) + "\"" +
   			 " } " + " } ";
    	return strPret;
    }
    
    public static Pret restaurer(String line, int index) throws ParseException {
    	int i = line.indexOf("\"numeroP\":") + 12;
  	    String numero = Adherents.getString(line, i);
  	    i = line.indexOf("\"debut\":") + 10;
  	    String debut = Adherents.getString(line, i);
  	    i = line.indexOf("\"fin\":") + 8;
  	    String fin = Adherents.getString(line, i);
  	    Date date1= new SimpleDateFormat("dd/MM/yyyy").parse(debut); 
  	    Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fin); 
  	    Pret pret = new Pret(Integer.parseInt(numero), date1, date2);
    	return pret;
    }
}
