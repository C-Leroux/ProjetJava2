package mediatheque;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Adherent {
	Hashtable dicoExemplairePret = new Hashtable();
	private String nom;
	private String prenom;
	private String adresse;
	
	public Adherent(String nom, String prenom, String adresse)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	
	public String getNom(){
		return this.nom;
	}
	
	public String getPrenom(){
		return this.prenom;
	}
	
	public String getAdresse(){
		return this.adresse;
	}
	
	
	public Exemplaire emprunter(Oeuvre oeuvre){
		int nbExemplaire = oeuvre.getNombreExemplaire();
		if( nbExemplaire > 1)
		{
			// recupere l'exemplaire
			Exemplaire exemplaire = oeuvre.getListExElmIndex(nbExemplaire - 1);

			// date de début et date de fin (date de fin 1 mois apres l'empreint)

			Calendar dateDebut = Calendar.getInstance();
			Calendar dateFin = Calendar.getInstance(); 
			dateFin.add(Calendar.MONTH, 1);
			
			// faire le pret
			Pret pret = new Pret(exemplaire.getNumero(),dateDebut, dateFin);
			
			// ajoute dans la table de hash le livre
			dicoExemplairePret.put(exemplaire, pret);
			// decremente le nombre de livre dans la mediatheque
			oeuvre.setNombreExemplaire(nbExemplaire - 1);
			return exemplaire;
			
		}
		// sinon pas possible pas d'exemplaire disponible
		// exception "il n'y a plus d'exemplaire disponible"
		
		return null;
	}
	public void ramener(Exemplaire exemplaire){
		// retourner une erreur
		if(exemplaire == null)
			return;
		Exemplaire e = (Exemplaire) dicoExemplairePret.remove(exemplaire);
		if (e != null)
		{
			// appel la fonction ramener de l'exemplaire qui incremente 
			exemplaire.ramener();
		}
		 // erreur l'element retourner n'est pas dans la liste des empreints
			
		
	}
	
	public String toString(){
		return this.nom + " " + this.prenom;
	}

}
