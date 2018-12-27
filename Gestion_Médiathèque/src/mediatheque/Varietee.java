package mediatheque;

public class Varietee extends Oeuvre {
    
    public Varietee(String titre, String auteur, int nbExemplaire) {
        super(titre, auteur, nbExemplaire);
    }

    public String getGenre()
    {
    	return "Variétée";
    }
    
    public String toString() {
    	String str = "";
    	str += " Genre : Varietee\n";
        return str + super.toString();
    }
    
    public String toJson() {
    	String str = "";
    	str += " Genre : Varietee";
        return str;
    }
    
   /* public String toString() {
    	String str = "";
    	str += " Genre : Varietee\n";
        return str + super.toString();
    }*/
    
    public static Varietee restaurer(String line, int index) {
    	int i = line.indexOf("\"titre\":") + 9;
  	    String titre = Adherents.getString(line, i);
  	    i = line.indexOf("\"auteur\":") + 10;
  	    String auteur = Adherents.getString(line, i);
  	    i = line.indexOf("\"nbExemplaireO\":") + 17;
  	    String nbExemplaire = Adherents.getString(line, i);
  	    Varietee varietee = new Varietee(titre, auteur, Integer.parseInt(nbExemplaire)); 
    	return varietee;
    }
    
}
