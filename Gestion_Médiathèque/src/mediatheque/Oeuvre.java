package mediatheque;

import java.util.ArrayList;
import java.util.List;


public class Oeuvre extends Exemplaire{

	protected String titre;
	protected String auteur;
	//int nombreExemplaire;
	//List<Exemplaire> listExemplaire = new ArrayList<Exemplaire>();
	
	public Oeuvre(int nombreExemplaire)
	{
		super(nombreExemplaire);
		for(int i = 0; i < nombreExemplaire; i++)
		{
			Exemplaire e = new Exemplaire(nombreExemplaire);
			listExemplaire.add(e);
			
		}
	}
	
	
	public Exemplaire getListExElmIndex(int i){
		return listExemplaire.get(i);
	}


}