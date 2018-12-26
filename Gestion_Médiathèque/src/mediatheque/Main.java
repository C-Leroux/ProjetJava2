package mediatheque;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
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
}