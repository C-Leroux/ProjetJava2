package interface_graphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConsulterAdherent extends JDialog {
	
	public ConsulterAdherent()
	{
		super();
		build();
	}
	
	private void build()
	{
		setTitle("Consulter les adhérents");
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
		JLabel coord = new JLabel("Afficher les coordonnées de l'adhérent", JLabel.LEFT);
		JLabel prets = new JLabel("Afficher les prêts en cours", JLabel.LEFT);
		
		JComboBox listeAdh = new JComboBox();
		JCheckBox coordCheck = new JCheckBox();
		JCheckBox pretsCheck = new JCheckBox();
		
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(liste, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(coordCheck, gbc);
		
		gbc = buildConstraints(0, 2);
		panel.add(pretsCheck, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeAdh, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(coord, gbc);
		
		gbc = buildConstraints(1, 2);
		gbc.gridwidth = 2;
		panel.add(prets, gbc);
		
		gbc = buildConstraints(1, 3);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(2, 3);
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