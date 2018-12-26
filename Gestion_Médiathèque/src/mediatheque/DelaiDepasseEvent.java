package mediatheque;


public class DelaiDepasseEvent {
	private Exemplaire exemplaire;
	private Adherent adherent;
	
	public DelaiDepasseEvent(Exemplaire exemplaire, Adherent adherent) {
		this.exemplaire = exemplaire;
		this.adherent = adherent;
	}
	

}