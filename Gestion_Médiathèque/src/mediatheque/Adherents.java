package mediatheque;

import java.util.Date;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.HashMap;
public class Adherents implements Runnable {

private Vector<Adherent> adherents = new Vector<Adherent>();

public Adherents() {
	  
}

public void sauvegarder() {
	  
}

public String toJson()
{
	  String str = new String();
	  str = "{ \"Adherents\": {\n";
	  for(int i = 0 ; i < adherents.size(); i++)
	  {
		  str += adherents.get(i).toJSon();
	  }
	  str += "}\n";
	  return str;
}

public Adherent getAdherent(String nom, String prenom, String adresse) {
	  return null;
}

public Vector<Adherent> getAdherents() {
	return adherents;
}

public void addAdherent(Adherent adherent) {
  if (adherents.indexOf(adherent) != -1) {
    adherents.add(adherent);
  }
}

public void run() {
	  for(int i = 0; i < adherents.size();i++) {
		  Hashtable dico = adherents.get(i).getdicExemplaiPret();
		  for(Enumeration e = dico.elements(); e.hasMoreElements();)
		  {
			  Pret pret = (Pret)e.nextElement();
			  Date aujourdhui = new Date();
			  if(aujourdhui.compareTo(pret.getFin()) > 0)
			  {
				  DelaiDepasseEvent event = new DelaiDepasseEvent((Exemplaire)dico.get(pret), adherents.get(i));
				  Bibliothecaire bibliothecaire = new Bibliothecaire();
				  bibliothecaire.exemplaireNonRestitue(event);
			  }
		  }
	  }
	  
}

public void addDelaiRestitutionDepasseListener(DelaiRestitutionDepasseListener l) {
	  
}

/*
public String toString() {
    return "";
}
*/
}
