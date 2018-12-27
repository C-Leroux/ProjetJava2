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
    	str += "Numéro de l'exemplaire : " + numero + "\n";
        return str;
    }
    
}