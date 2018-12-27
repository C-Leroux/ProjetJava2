package interface_graphique;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.SwingUtilities;

import mediatheque.Adherents;
import mediatheque.Oeuvres;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 Adherents adherents = new Adherents();
		 try {
			adherents.restaurer();
		 }
		 catch (IOException e) {}
		 catch (ParseException e) {}
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
