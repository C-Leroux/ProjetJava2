package mediatheque;
import java.util.Calendar;

public class Pret {
	private int numero;
	private Calendar debut;
	private Calendar fin; 
	
    public Pret(int numero, Calendar debut,Calendar fin){
    	this.numero = numero;
    	this.debut = debut;
    	this.fin = fin;
    }
}
