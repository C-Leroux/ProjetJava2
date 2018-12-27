package interface_graphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JButton preter;
	private JButton rendre;
	private JButton sauver;
	
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
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		
		preter = new JButton("Prêter un exemplaire");
		rendre = new JButton("Rendre un exemplaire");
		sauver = new JButton("Sauvegarder la médiathèque");
		
		preter.addActionListener(this);
		rendre.addActionListener(this);
		sauver.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(preter, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(rendre, gbc);
		
		gbc = buildConstraints(0, 2);
		gbc.gridwidth = 2;
		panel.add(sauver, gbc);
		
		return panel;
	}
	
	private GridBagConstraints buildConstraints(int x, int y)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 0, 5, 5);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = x;
		gbc.gridy = y;
		
		return gbc;
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
			ConsulterOeuvre dialog = new ConsulterOeuvre(oeuvres);
			dialog.setVisible(true);
		}
		if (source == supprimerO) {
			SupprimerOeuvre dialog = new SupprimerOeuvre(oeuvres);
			dialog.setVisible(true);
		}
		if (source == preter)
		{
			Preter dialog = new Preter(adherents, oeuvres);
			dialog.setVisible(true);
		}
		if (source == rendre)
		{
			Rendre dialog = new Rendre(adherents, oeuvres);
			dialog.setVisible(true);
		}
		if (source == sauver)
		{
			// À compléter
		}
	}
}
