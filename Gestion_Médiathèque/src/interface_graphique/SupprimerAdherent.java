package interface_graphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mediatheque.Adherents;

public class SupprimerAdherent extends JDialog {
	
	private Adherents adherents;
	
	public SupprimerAdherent(Adherents adherents)
	{
		super();
		this.adherents = adherents;
		build();
	}
	
	private void build()
	{
		setTitle("Supprimer un adhérent");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		
		JLabel liste = new JLabel("Liste des adhérents", JLabel.LEFT);
		
		JComboBox listeAdh = new JComboBox();
		
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(liste, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeAdh, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(annuler, gbc);
		
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

}
