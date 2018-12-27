package mediatheque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


  public Exemplaire emprunter(Oeuvre oeuvre) throws ParseException {
	//Calendar today = Calendar.getInstance();
	//Date dateDebut = today.getTime();
    Exemplaire exemplaire = oeuvre.emprunter();

    Calendar dateDebut = Calendar.getInstance();
	Calendar dateFin = Calendar.getInstance(); 
	dateFin.add(Calendar.MONTH, 1);
	
	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    String strdebut = format1.format(dateDebut.getTime());
    String strfin =  format1.format(dateFin.getTime());
  
    Date debutDate = new SimpleDateFormat("dd/MM/yyyy").parse(strdebut);
    Date debutFin = new SimpleDateFormat("dd/MM/yyyy").parse(strfin);
    Pret pret = new Pret(exemplaire.numero, debutDate, debutFin);
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
	  adherentStr = " \"Adherent\": { " + 
			 " \"nom\":\"" + this.nom + "\""+
			 " \"prenom\":\"" + this.prenom + "\"" +
			 " \"adresse\":\"" + this.adresse + "\"" ;
	  adherentStr += toJsonHashtable() + toJsonEvents();
	  adherentStr += "  }";
	  return adherentStr;
  }
  
  public String toJsonHashtable() {
	  String hashStr = new String();
	  hashStr = "\"dicoExemplairePret\": <Exemplaire, Pret> [";
	  Set<Exemplaire> keys = dicoExemplairePret.keySet();
	  for (Exemplaire exemplaire : keys)
	  {
			Pret pret = dicoExemplairePret.get(exemplaire);
			String exStr = exemplaire.toJson();
			String pretStr = pret.toJson(); 
			hashStr += "k : { " + exStr + " }, ";
			hashStr += "v : { " + pretStr + " } }";
	  }
	  hashStr += "      ]";
	  return hashStr; 
	  
  }
  public String toJsonEvents()
  {
	  String evtsStr = new String();
	  evtsStr = "\"Events\": { ";
	  for(int i = 0; i < events.size(); i++) {
		 evtsStr += events.get(i).toJson();
	  }
	  evtsStr += " }";
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
  
  
  public static Adherent restaurer(String line, int index) throws ParseException {
	  int i = line.indexOf("\"nom\":") + 7;
	  String nom = Adherents.getString(line, i);
	  i = line.indexOf("\"prenom\"") + 10;
	  String prenom = Adherents.getString(line, i);
	  i = line.indexOf("\"adresse\"") + 11;
	  String adresse = Adherents.getString(line, i);
	  Adherent adherent = new Adherent(nom, prenom, adresse);

	  if(line.indexOf("\"dicoExemplairePret\"") > 0)
	  {
		    while (line.indexOf("\"Exemplaire\"") > 0 && line.length() > 0) {
		    Exemplaire ex = Exemplaire.restaurer(line, i);
		    Pret pret = Pret.restaurer(line, i);
		    adherent.dicoExemplairePret.put(ex, pret);
		    line = line.substring(line.indexOf("\"fin\":") + 5);
		  }
		  
	  }
	  
	  
	  return adherent;
	  
  }
  
  public String toString() {
      return this.nom + " " + this.prenom;
  }
 
}
