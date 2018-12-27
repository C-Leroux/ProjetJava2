package mediatheque;

public class Opera extends Oeuvre {

    public Opera(String titre, String auteur, int nbExemplaire) {
        super(titre, auteur, nbExemplaire);
    }
    
    public String getGenre()
    {
    	return "Opéra";
    }
    
    public String toString() {
    	String str = "";
    	str += " Genre : Opera ";
        return str + super.toString();
    }
    
    public String toJson() {
    	String str = "";
    	str += " Genre : Opera ";
        return str;
    }
    
    public static Opera restaurer(String line, int index) {
    	int i = line.indexOf("\"titre\":") + 9;
  	    String titre = Adherents.getString(line, i);
  	    i = line.indexOf("\"auteur\":") + 10;
  	    String auteur = Adherents.getString(line, i);
  	    i = line.indexOf("\"nbExemplaireO\":") + 17;
  	    String nbExemplaire = Adherents.getString(line, i);
  	    Opera opera = new Opera(titre, auteur, Integer.parseInt(nbExemplaire)); 
    	return opera;
    }

    /*
    public String toString() {
        return "";
    }
    */
}
