package mediatheque;

import java.util.Vector;

public class Oeuvres {

	private Vector<Oeuvre> oeuvres;
	
	public Oeuvres()
	{
		this.oeuvres = new Vector<Oeuvre>();
	}
	
	public Vector<Oeuvre> getOeuvres()
	{
		return oeuvres;
	}
	
	public void addOeuvre(Oeuvre oeuvre)
	{
		oeuvres.add(oeuvre);
	}
}