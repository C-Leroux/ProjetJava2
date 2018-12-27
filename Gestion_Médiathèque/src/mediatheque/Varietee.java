package mediatheque;

public class Varietee extends Oeuvre {
    
    public Varietee(String titre, String auteur, int nbExemplaire) {
        super(titre, auteur, nbExemplaire);
    }

    
    public String toString() {
    	String str = "";
    	str += " Genre : Varietee\n";
        return str + super.toString();
    }
    
    public String toJson() {
    	String str = "";
    	str += " Genre : Varietee\n";
        return str;
    }
    
   /* public String toString() {
    	String str = "";
    	str += " Genre : Varietee\n";
        return str + super.toString();
    }*/
    
}
