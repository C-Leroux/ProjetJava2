package interface_graphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import mediatheque.Adherents;
import mediatheque.Oeuvres;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FenetrePrincipale extends JFrame implements ActionListener {
	
	private JMenuItem nouveauA;
	private JMenuItem consulterA;
	private JMenuItem supprimerA;
	
	private JMenuItem nouveauO;
	private JMenuItem consulterO;
	private JMenuItem supprimerO;
	
	private Adherents adherents;
	private Oeuvres oeuvres;
	
	public FenetrePrincipale(Adherents adherents, Oeuvres oeuvres)
	{
		super();
		this.adherents = adherents;
		this.oeuvres = oeuvres;
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
		
		nouveauA = new JMenuItem("Nouveau");
		consulterA = new JMenuItem("Consulter");
		supprimerA = new JMenuItem("Supprimer");
		
		nouveauO = new JMenuItem("Nouveau");
		consulterO = new JMenuItem("Consulter");
		supprimerO = new JMenuItem("Supprimer");
		
		nouveauA.addActionListener(this);
		consulterA.addActionListener(this);
		supprimerA.addActionListener(this);
		
		nouveauO.addActionListener(this);
		consulterO.addActionListener(this);
		supprimerO.addActionListener(this);
		
		adherents.add(nouveauA);
		adherents.add(consulterA);
		adherents.add(supprimerA);
		
		oeuvres.add(nouveauO);
		oeuvres.add(consulterO);
		oeuvres.add(supprimerO);
		
		menu.add(oeuvres);
		menu.add(adherents);
		
		return menu;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == nouveauA) {
			NouvelAdherent dialog = new NouvelAdherent(adherents);
			dialog.setVisible(true);
		}
		if (source == consulterA) {
			ConsulterAdherent dialog = new ConsulterAdherent(adherents);
			dialog.setVisible(true);
		}
		if (source == supprimerA) {
			SupprimerAdherent dialog = new SupprimerAdherent(adherents);
			dialog.setVisible(true);
		}
		if (source == nouveauO) {
			NouvelleOeuvre dialog = new NouvelleOeuvre(oeuvres);
			dialog.setVisible(true);
		}
		if (source == consulterO) {
			ConsulterOeuvre dialog = new ConsulterOeuvre();
			dialog.setVisible(true);
		}
		if (source == supprimerO) {
			SupprimerOeuvre dialog = new SupprimerOeuvre();
			dialog.setVisible(true);
		}
	}
}
