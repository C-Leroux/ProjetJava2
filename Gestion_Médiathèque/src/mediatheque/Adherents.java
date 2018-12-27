package mediatheque;

import java.util.Date;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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
	String str = new String();
	  // 
	  try {
		PrintWriter fichier = new PrintWriter("sauvegarde.json", "UTF-8");
		str = toJson();
		fichier.print(str);
		fichier.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void restaurer() throws IOException, ParseException {
	String str = new String();
	BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("sauvegarde.json"));
		String line;
		while ((line = br.readLine()) != null) {
		   //System.out.println(line);
		   if(line.indexOf("\"Adherent\"") > 0)
		   {
			   Adherent ad1 = Adherent.restaurer(line,line.indexOf("Adherent" + 1));
			   adherents.add(ad1);
		   }
		}
		br.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public String toJson()
{
	  String str = new String();
	  str = "{ \"Adherents\": {\n";
	  for(int i = 0 ; i < adherents.size(); i++)
	  {
		  str += adherents.get(i).toJSon();
	  }
	  str += "\n}\n";
	  return str;
}

public Adherent getAdherent(String nom, String prenom, String adresse) {
	
	for(int i = 0; i < adherents.size() ;i++)
	{
		if(adherents.get(i).getNom().compareTo(nom) == 0 && adherents.get(i).getPrenom().compareTo(prenom) == 0 && adherents.get(i).getAdresse().compareTo(adresse) == 0) {
			return adherents.get(i);
		}
	}
	return null;
}

public Vector<Adherent> getAdherents() {
	return adherents;
}

public void removeAdherent(Adherent adherent)
{
	adherents.remove(adherent);
}

public void addAdherent(Adherent adherent) {
    adherents.add(adherent);
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

public static String getString(String line, int i) {
	  String str = "";
	  for(; i < line.length(); i++ ) {
		      if(line.charAt(i) == '"') {
			  return str;
		  }
		  else {
			  str += line.charAt(i);
		  }
	  }
	  return null;
	  
}

/*
public String toString() {
    return "";
}
*/
}
