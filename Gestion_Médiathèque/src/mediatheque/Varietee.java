package mediatheque;

import java.util.ArrayList;
import java.util.List;

public class Varietee extends Oeuvre{
	//int nombreExemplaire;
	//List<Exemplaire> listExemplaire = new ArrayList<Exemplaire>();
	
	public Varietee(String titre, String auteur, int nombreExemplaire)
	{
		super(nombreExemplaire);
		this.titre = titre;
		this.auteur = auteur;
		this.nombreExemplaire = nombreExemplaire;
		/*for(int i = 0; i < nombreExemplaire; i++)
		{
			Exemplaire e = new Exemplaire();
			listExemplaire.add(e);
			
		}*/
		
	}
	
}