package interface_graphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NouvelleOeuvre extends JDialog {
	
	public NouvelleOeuvre()
	{
		super();
		build();
	}
	
	private void build()
	{
		setTitle("Nouvel adhérent");
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
		
		JLabel nom = new JLabel("Nom", JLabel.LEFT);
		JLabel auteur = new JLabel("Auteur", JLabel.LEFT);
		
		int text_size = 10;
		JTextField textNom = new JTextField(text_size);
		JTextField textAuteur = new JTextField(text_size);
		
		JRadioButton opera = new JRadioButton("Opéra");
		JRadioButton varietee = new JRadioButton("Variétée");
		ButtonGroup group = new ButtonGroup();
		group.add(opera);
		group.add(varietee);
		Box box = Box.createHorizontalBox();
		box.add(opera);
		box.add(varietee);
		
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(nom, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(auteur, gbc);
		
		gbc = buildConstraints(0, 2);
		gbc.gridwidth = 2;
		panel.add(box, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(textNom, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(textAuteur, gbc);
		
		gbc = buildConstraints(0, 3);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(1, 3);
		panel.add(annuler, gbc);
		
		return panel;
	}
	
	private GridBagConstraints buildConstraints(int x, int y)
	{
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 0, 5, 5);
		if (y != 3)
		{
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
		}
		else
			gbc.anchor = GridBagConstraints.EAST;
		
		gbc.gridx = x;
		gbc.gridy = y;
		
		return gbc;
	}

}
