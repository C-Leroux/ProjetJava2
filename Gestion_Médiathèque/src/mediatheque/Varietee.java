package mediatheque;

public class Varietee extends Oeuvre {
    
    public Varietee(String titre, String auteur, int nbExemplaire) {
        super(titre, auteur, nbExemplaire);
    }

    
    public String toString() {
    	String str = "";
    	str += "Genre : Variétée\n";
        return str + super.toString();
    }
    
}
