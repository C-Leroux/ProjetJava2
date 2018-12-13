package interface_graphique;

import javax.swing.SwingUtilities;

public class LancerInterface {
	 public static void main(String[] args)
	 {
		 SwingUtilities.invokeLater(new Runnable()
		 {
			 public void run()
			 {
				 FenetrePrincipale fenetre = new FenetrePrincipale();
				 fenetre.setVisible(true);
			 }
		 });
	 }
}
