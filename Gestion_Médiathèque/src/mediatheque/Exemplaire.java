package mediatheque;


public class Exemplaire {
    public int numero;
    public Oeuvre oeuvre;

    public Exemplaire(int numero, Oeuvre oeuvre) {
        this.numero = numero;
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
  	    exStr = "{ \"Exemplaire\": {\n" + 
  	    	 " \"id\": \"" + this.oeuvre.getTitre() + ":" + this.oeuvre.getAuteur() + "\"\n" +
  			 " \"nom\": \"" + this.numero + "\"\n" + 
  			 " \"prenom\": \"" + this.oeuvre + "\"\n" + 
  			 "}\n";
  	    return exStr;
    }
    
    public String toString() {
    	String str = "";
    	str += "Num�ro de l'exemplaire : " + numero + "\n";
        return str;
    }
    
}