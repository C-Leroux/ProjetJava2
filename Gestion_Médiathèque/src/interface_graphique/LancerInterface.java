package interface_graphique;

import javax.swing.SwingUtilities;

import mediatheque.Adherents;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 // � g�rer : restitution de l'interface sauvegard�e
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
