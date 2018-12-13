package mediatheque;

import java.util.ArrayList;
import java.util.List;


public class Exemplaire {
	private static int numero = 0;
	protected List<Exemplaire> listExemplaire = new ArrayList<Exemplaire>();
	protected int nombreExemplaire;
	
	public Exemplaire(int nombreExemplaire){
		this.nombreExemplaire = nombreExemplaire;
		this.numero++;
		
	}
	
	public int getNombreExemplaire(){
		return this.nombreExemplaire;
	}
	
	public void setNombreExemplaire(int nombreExemplaire){
		this.nombreExemplaire = nombreExemplaire;
	}
	
	
	public int getNumero()
	{
		return this.numero;
	}
	
	public void ramener() {
		this.nombreExemplaire = nombreExemplaire + 1;
         
		
	}

}
