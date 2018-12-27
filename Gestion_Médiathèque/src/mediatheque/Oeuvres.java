package mediatheque;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Vector;

public class Oeuvres {

	private Vector<Oeuvre> oeuvres;
	
	public Oeuvres()
	{
		this.oeuvres = new Vector<Oeuvre>();
	}
	
	public Vector<Oeuvre> getOeuvres()
	{
		return oeuvres;
	}
	
	public void addOeuvre(Oeuvre oeuvre)
	{
		oeuvres.add(oeuvre);
	}
	
	public void removeOeuvre(Oeuvre oeuvre)
	{
		oeuvres.remove(oeuvre);
	}
	
	public String toJson() {
		String str = "";
		for(int i = 0; i < oeuvres.size();i++) {
			str += oeuvres.get(i);
		}
		return str;
	}
	
	public void sauvegarder() {
		String str = new String();
		  // 
		  try {
			PrintWriter fichier = new PrintWriter("sauvegardeOeuvres.json", "UTF-8");
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
	
	public Vector<Oeuvre> restaurer() throws IOException, ParseException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("sauvegardeOeuvres.json"));
			String line;
			while ((line = br.readLine()) != null) {
			   if(line.indexOf("\"Oeuvre\"") > 0)
			   {
				   if(line.indexOf("\"Varietee\":") > 0)
		    		{
		    			Varietee varietee1 = Varietee.restaurer(line, line.indexOf("\"Varietee\":"));
		    			oeuvres.add(varietee1);
		    		}
		    		else 
		    		{
		    			 Opera opera = Opera.restaurer(line, line.indexOf("\"Opera\":"));
		    			 oeuvres.add(opera);
		    		}
			   }
			}
			br.close();
			return oeuvres;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oeuvres;	
	}
}