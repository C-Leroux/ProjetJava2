package mediatheque;


public class Exemplaire {
    public int numero;
    public Oeuvre oeuvre;

    public Exemplaire(int numero, Oeuvre oeuvre) {
        this.numero = numero;
        this.oeuvre = oeuvre;
    }

    public String toJson()
    {
    	String exStr = new String(); 
  	    exStr = "{ \"Exemplaire\": {\n" + 
  			 " \"nom\": \"" + this.numero + "\"\n" + 
  			 " \"prenom\": \"" + this.oeuvre + "\"\n" + 
  			 "}\n";
  	    return exStr;
    }
    /*
    public String toString() {
        return "";
    }
    */
}