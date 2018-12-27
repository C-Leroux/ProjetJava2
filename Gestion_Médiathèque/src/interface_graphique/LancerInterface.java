package interface_graphique;

import javax.swing.SwingUtilities;

import mediatheque.Adherents;
import mediatheque.Oeuvres;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 // À gérer : restitution de l'interface sauvegardée
		 Adherents adherents = new Adherents();
		 Oeuvres oeuvres = new Oeuvres();
		 
		 SwingUtilities.invokeLater(new Runnable()
		 {
			 public void run()
			 {
				 FenetrePrincipale fenetre = new FenetrePrincipale(adherents, oeuvres);
				 fenetre.setVisible(true);
			 }
		 });
	 }
}
