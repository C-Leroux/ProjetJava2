package interface_graphique;

import javax.swing.SwingUtilities;

import mediatheque.Adherents;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 // À gérer : restitution de l'interface sauvegardée
		 Adherents adherents = new Adherents();
		 
		 SwingUtilities.invokeLater(new Runnable()
		 {
			 public void run()
			 {
				 FenetrePrincipale fenetre = new FenetrePrincipale(adherents);
				 fenetre.setVisible(true);
			 }
		 });
	 }
}
