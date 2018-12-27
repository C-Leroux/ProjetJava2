package interface_graphique;

import javax.swing.SwingUtilities;

import mediatheque.Adherents;
import mediatheque.Oeuvres;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 // � g�rer : restitution de l'interface sauvegard�e
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
