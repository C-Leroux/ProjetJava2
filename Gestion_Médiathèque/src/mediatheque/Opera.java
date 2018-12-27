package mediatheque;

public class Opera extends Oeuvre {

    public Opera(String titre, String auteur, int nbExemplaire) {
        super(titre, auteur, nbExemplaire);
    }
    
    public String toString() {
    	String str = "";
    	str += " Genre : Opera\n";
        return str + super.toString();
    }
    
    public String toJson() {
    	String str = "";
    	str += " Genre : Opera\n";
        return str;
    }

    /*
    public String toString() {
        return "";
    }
    */
}
