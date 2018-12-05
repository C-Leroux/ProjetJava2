package interface_graphique;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetrePrincipale extends JFrame{
	
	public FenetrePrincipale()
	{
		super();
		build();
	}
	
	private void build()
	{
		setTitle("Médiathèque");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setJMenuBar(buildMenuBar());
	}
	
	private JPanel buildContentPane()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		
		return panel;
	}
	
	private JMenuBar buildMenuBar()
	{
		JMenuBar menu = new JMenuBar();
		JMenu adherents = new JMenu("Adhérents");
		JMenu oeuvres = new JMenu("Oeuvres");
		
		JMenuItem nouveauA = new JMenuItem("Nouveau");
		JMenuItem consulterA = new JMenuItem("Consulter");
		JMenuItem supprimerA = new JMenuItem("Supprimer");
		
		JMenuItem nouveauB = new JMenuItem("Nouveau");
		JMenuItem consulterB = new JMenuItem("Consulter");
		JMenuItem supprimerB = new JMenuItem("Supprimer");
		
		adherents.add(nouveauA);
		adherents.add(consulterA);
		adherents.add(supprimerA);
		
		oeuvres.add(nouveauB);
		oeuvres.add(consulterB);
		oeuvres.add(supprimerB);
		
		menu.add(oeuvres);
		menu.add(adherents);
		
		return menu;
	}
}
