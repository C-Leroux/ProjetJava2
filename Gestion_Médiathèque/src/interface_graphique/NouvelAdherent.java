package interface_graphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mediatheque.Adherent;
import mediatheque.Adherents;

public class NouvelAdherent extends JDialog implements ActionListener {
	
	private Adherents adherents;
	
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textAdresse;
	
	private JButton valider;
	private JButton annuler;
	
	public NouvelAdherent(Adherents adherents)
	{
		super();
		this.adherents = adherents;
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
		JLabel prenom = new JLabel("Prénom", JLabel.LEFT);
		JLabel adresse = new JLabel("Adresse", JLabel.LEFT);
		
		int text_size = 10;
		textNom = new JTextField(text_size);
		textPrenom = new JTextField(text_size);
		textAdresse = new JTextField(text_size);
		
		DocumentListener dl = new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}
			public void removeUpdate(DocumentEvent e) {
				changed();
			}
			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {
				if (textNom.getText().equals("") || textPrenom.getText().equals("") || textAdresse.getText().equals("")){
					valider.setEnabled(false);
				}
				else {
					valider.setEnabled(true);
				}
		
			}
		};
		
		textNom.getDocument().addDocumentListener(dl);
		textPrenom.getDocument().addDocumentListener(dl);
		textAdresse.getDocument().addDocumentListener(dl);
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.setEnabled(false);
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(nom, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(prenom, gbc);
		
		gbc = buildConstraints(0, 2);
		panel.add(adresse, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(textNom, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(textPrenom, gbc);
		
		gbc = buildConstraints(1, 2);
		gbc.gridwidth = 2;
		panel.add(textAdresse, gbc);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			String adresse = textAdresse.getText();
			adherents.addAdherent(new Adherent(nom, prenom, adresse));
		}
		this.dispose();
	}
	
}
