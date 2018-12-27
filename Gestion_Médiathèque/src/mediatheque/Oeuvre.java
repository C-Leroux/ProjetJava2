package mediatheque;

import java.util.Iterator;
import java.util.Stack;

public abstract class Oeuvre {
    private String titre;
    private String auteur;
    private int nbExemplaire;
    private Stack<Exemplaire> exemplaires = new Stack<Exemplaire>();

    public Oeuvre(String titre, String auteur, int nbExemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.nbExemplaire = nbExemplaire;

        for (int i = 1; i <= nbExemplaire; ++i) {
            exemplaires.push(new Exemplaire(i, this));
        }
    }

    public String getTitre() {
    	return this.titre;
    }
    
    public String getAuteur() {
    	return this.auteur;
    }
    
    public abstract String getGenre();
    
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
    
    public void ajouterExemplaire(int nb)
    {
    	for (int i = nbExemplaire + 1 ; i <= nbExemplaire + nb ; ++i)
    	{
    		exemplaires.push(new Exemplaire(i, this));
    	}
    	nbExemplaire += nb;
    }
    
    public int getDispo()
    {
    	return exemplaires.size();
    }
    
    public int getNb()
    {
    	return nbExemplaire;
    }

    public String toJsonOeuvre()
    {
    	String oeuvreStr = new String();
    	oeuvreStr = "\"Oeuvre\": {" + 
   			 " \"titre\":\"" + this.titre + "\"" + 
   			 " \"auteur\":\"" + this.auteur + "\"" +
   			 " \"nbExemplaireO\":\"" + this.nbExemplaire + "\"";
    	//oeuvreStr = oeuvreStr + toJsonExemplaire() + " } ";
    	String s = this instanceof Opera? "Opera" : this instanceof Varietee ? "Varietee" : "";
    	oeuvreStr += "\"type\":";
    	oeuvreStr += this instanceof Opera? "\"Opera\"" : this instanceof Varietee ? "\"Varietee\"" : "";
    	oeuvreStr +=   " } ";
    	return oeuvreStr;
    }
    
    private String toJsonExemplaire() {
    	String exStr = new String();
    	exStr = "{ \"exemplaires\": {";
    	
    	Iterator<Exemplaire> itr = exemplaires.iterator();
    	while(itr.hasNext()) {
    		exStr += "{";
    		Exemplaire exemplaire = (Exemplaire)itr.next();
    		exStr += exemplaire.toJson();
    		exStr += "}";
    	}
    	exStr += "}";
    	return exStr;
    }
    
    public String toString() {
    	String str = "";
    	str += "\n Titre : " + titre + "\n";
    	str += " Auteur : " + auteur + "\n";
        return str;
    }
    

    
    
    
}
