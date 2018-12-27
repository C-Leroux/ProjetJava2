package mediatheque;


public class Exemplaire {
	public static int nbExemplaire = 0;
    public int numero;
    public Oeuvre oeuvre;

    public Exemplaire(int numero, Oeuvre oeuvre) {
    	++Exemplaire.nbExemplaire;
        this.numero = Exemplaire.nbExemplaire;
        this.oeuvre = oeuvre;
    }
    
    public Exemplaire(int nbExemplaire, int numero, Oeuvre oeuvre) {
    	this.nbExemplaire = nbExemplaire;
        this.numero = Exemplaire.nbExemplaire;
        this.oeuvre = oeuvre;
    }
    
    public int getNumero()
    {
    	return numero;
    }
    
    public Oeuvre getOeuvre()
    {
    	return oeuvre;
    }

    public String toJson()
    {
    	String exStr = new String(); 
  	    exStr = " \"Exemplaire\": { " + 
  	    	 " \"id\":\"" + this.oeuvre.getTitre() + ":" + this.oeuvre.getAuteur() + "\"" +
  			 " \"numero\":\"" + this.numero + "\"" + 
  			 " \"nbExemplaire\":\"" + this.nbExemplaire + "\"" + 
  			 " \"oeuvre\": {" + this.oeuvre.toJsonOeuvre() + " } " + 
  			 " }";
  	    return exStr;
    }
    
    public String toString() {
    	String str = "";
    	str += "Numero de l'exemplaire : " + numero + "\n";
        return str;
    }
    
    public static Exemplaire restaurer(String line, int index) {
    	int i = line.indexOf("\"nbExemplaire\":") + 16;
  	    String nbExemplaire = Adherents.getString(line, i);
  	    i = line.indexOf("\"numero\":") + 10;
  	    String numero = Adherents.getString(line, i);
    	if(line.indexOf("\"oeuvre\":") > 0)
    	{ 
    		if(line.indexOf("\"Varietee\":") > 0)
    		{
    			Varietee varietee1 = Varietee.restaurer(line, index);
    			Exemplaire ex = new Exemplaire(Integer.parseInt(nbExemplaire), Integer.parseInt(numero),varietee1);
    			return ex;
    		}
    		else 
    		{
    			 Opera opera = Opera.restaurer(line, index);
    			 Exemplaire ex = new Exemplaire(Integer.parseInt(nbExemplaire), Integer.parseInt(numero), opera);
    			 return ex;
    		}
    	}
    	
  	    return null;
    }
    
}