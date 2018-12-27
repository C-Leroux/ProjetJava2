package mediatheque;

import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

public class Main {

    /**
     * @param args the command line arguments
     * @throws IOException 
     * @throws ParseException 
     */
    public static void main(String[] args) throws IOException, ParseException {
        // testPart1();
    	testPart2();
    }

    public static void testPart1() {
        try {
            Adherent adherent1 = new Adherent("Albert", "Durant", "2 allee tataouine");
            Adherent adherent2 = new Adherent("Joseph", "Beauve", "14 rue de la grange");

            String titre = "BestOfLouisMariano";
            String auteur = "Louis Mariano";
            int nombreExemplaire = 3;
            Varietee varietee1 = new Varietee(titre, auteur, nombreExemplaire);

            System.out.println("Adherent 1 avant emprunt =" + adherent1);
            Exemplaire exemplaire = adherent1.emprunter(varietee1);
            System.out.println("Adherent 1 apres emprunt=" + adherent1);

            adherent1.ramener(exemplaire);
            System.out.println("Adherent 1 apres restitution =" + adherent1);
            
            
            //////
            Bibliothecaire bibliothecaire = new Bibliothecaire();
            Adherents adherents = new Adherents();
            
            adherents.addDelaiRestitutionDepasseListener(bibliothecaire);
            
        } catch( Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void testPart2() throws IOException, ParseException {
    	// ...
    	//Adherents adherents = new Adherents(2000) ;
    	Adherents adherents = new Adherents();
    	Adherent adherent1 = new Adherent( "Albert", "Durant", "2 allee tataouine" );
    	adherents.addAdherent(adherent1);
    	String titre = "BestOfLouisMariano";
    	String auteur = "Louis Mariano";
    	int nombreExemplaire = 3;
    	Varietee varietee1 = new Varietee(titre, auteur, nombreExemplaire);
    	Exemplaire exemplaire = adherent1.emprunter(varietee1);
    	
    	
    	Opera opera1 = new Opera(titre, auteur, nombreExemplaire);
    	Exemplaire exemplaire2 = adherent1.emprunter(opera1);
    	adherents.sauvegarder(); 
    	adherents.restaurer();
    	adherents.addAdherent(adherent1);
    	Adherent adherentReconstruit = adherents.getAdherent( "Albert", "Durant", "2 allee tataouine" ); 
        System.out.print(adherents.getAdherents().get(0).getNomPrenom());
 
    	Vector<Exemplaire> exemplairesEmpruntes = adherentReconstruit.getExemplairesEmpruntes();
    	for( int i=0; i<exemplairesEmpruntes.size(); i++ ){
    		exemplaire = (Exemplaire)exemplairesEmpruntes.elementAt(i); 
    	    adherentReconstruit.ramener(exemplaire);
    	}


    }
}