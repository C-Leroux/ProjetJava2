package mediatheque;

import java.util.Stack;



public class Oeuvre {
    private String titre;
    private String auteur;
    private int nbExemplaire;
    private Stack<Exemplaire> exemplaires = new Stack<Exemplaire>();

    public Oeuvre(String titre, String auteur, int nbExemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaire = nbExemplaire;

        for (int i = 1; i <= nbExemplaire; ++i) {
            exemplaires.add(new Exemplaire(i, this));
        }
    }

    public Exemplaire emprunter() {
        if (!exemplaires.empty()) {
            return exemplaires.pop();
        }
        return null;
    }

    public void ramener(Exemplaire exemplaire) {
        // insert if not already in the stack
        if (exemplaires.search(exemplaire) == -1) {
            exemplaires.push(exemplaire);
        }
    }

    public String toJson()
    {
    	String oeuvreStr = new String();
    	oeuvreStr = "{ \"Adherent\": {\n" + 
   			 " \"titre\": \"" + this.titre + "\"" + 
   			 " \"auteur\": \"" + this.auteur + "\"" +
   			 " \"nbExemplaire\": " + this.nbExemplaire;
    	

    	return oeuvreStr;
    }
    
    public String toJsonExemplaire() {
    	String exStr = new String();
    	//exemplaires.i
    	//exemplaires.iterator().hasNext();
    	return null;
    }
    /*
    public String toString() {
        return "";
    }
    */
}
