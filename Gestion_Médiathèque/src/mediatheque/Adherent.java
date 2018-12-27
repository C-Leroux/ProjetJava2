package mediatheque;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JLabel;

public class Adherent {

  private String nom;
  private String prenom;
  private String adresse;
  private Hashtable<Exemplaire,Pret> dicoExemplairePret = new Hashtable<Exemplaire,Pret>();
  private ArrayList<DelaiRestitutionDepasseListener> events = new ArrayList<DelaiRestitutionDepasseListener>();

  public Adherent(String nom, String prenom, String adresse) {
    this.nom = nom;
    this.prenom = prenom;
    this.adresse = adresse;
  }
  
  public String getNom() {
	  return this.nom;
  }
  
  public String getPrenom() {
	  return this.prenom;
  }
  
  public String getNomPrenom()
  {
	  return nom + " " + prenom;
  }
  
  public String getAdresse()
  {
	  return adresse;
  }

  public Exemplaire emprunter(Oeuvre oeuvre) {
    Exemplaire exemplaire = oeuvre.emprunter();
    Pret pret = new Pret(exemplaire.numero, null, null);
    if (exemplaire != null) {
      dicoExemplairePret.put(exemplaire, pret);
      return exemplaire;
    }
    return null;
  }

  public void ramener(Exemplaire exemplaire) {
      dicoExemplairePret.remove(exemplaire);
      exemplaire.oeuvre.ramener(exemplaire);
  }

  public void addDelaiRestitutionDepasseListener(DelaiRestitutionDepasseListener l) {
	  if(events.indexOf(l) > -1)
		  return ;
	  events.add(l);
	  
  }
  
  public Hashtable<Exemplaire,Pret> getdicExemplaiPret() {
	  return this.dicoExemplairePret;
  }
  
  
  
  public String toJSon()
  {
	  String adherentStr = new String(); 
	  adherentStr = " \"Adherent\": {\n" + 
			 "    \"nom\": \"" + this.nom + "\n" + 
			 "    \"prenom\": \"" + this.prenom + "\"\n" +
			 "    \"adresse\": \"" + this.adresse + "\"\n" ;
	  adherentStr += toJsonHashtable() + toJsonEvents();
	  adherentStr += "  }\n";
	  return adherentStr;
  }
  
  public String toJsonHashtable() {
	  String hashStr = new String();
	  hashStr = "    \"dicoExemplairePret\": <Exemplaire, Pret> \n      [";
	  Set<Exemplaire> keys = dicoExemplairePret.keySet();
	  for (Exemplaire exemplaire : keys)
	  {
			Pret pret = dicoExemplairePret.get(exemplaire);
			String exStr = exemplaire.toJson();
			String pretStr = pret.toJson(); 
			hashStr += "{\n      k : { " + exStr + "           },\n";
			hashStr += "        v : { " + pretStr + "            }\n           }\n";
	  }
	  hashStr += "      ]\n";
	  return hashStr; 
	  
  }
  public String toJsonEvents()
  {
	  String evtsStr = new String();
	  evtsStr = "    \"Events\": {\n";
	  for(int i = 0; i < events.size(); i++) {
		 evtsStr += events.get(i).toJson() + "\n";
	  }
	  evtsStr += "    }\n";
	  return evtsStr;
  }
  public Vector<Exemplaire> getExemplairesEmpruntes(){
	  Vector<Exemplaire> exemplaires = new Vector<Exemplaire>();
	  Set<Exemplaire> keys = dicoExemplairePret.keySet();
	  for (Exemplaire exemplaire : keys)
	  {
		  exemplaires.add(exemplaire);
	  }
	  
	  return exemplaires;
  }
  
  
  public String toString() {
      return this.nom + " " + this.prenom;
  }
  
}
