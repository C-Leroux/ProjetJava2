package interface_graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mediatheque.Adherent;
import mediatheque.Oeuvre;
import mediatheque.Oeuvres;
import mediatheque.Opera;
import mediatheque.Varietee;

public class NouvelleOeuvre extends JDialog implements ActionListener {
	
	private Oeuvres oeuvres;
	
	private JTextField textNom;
	private JTextField textAuteur;
	private JSpinner nbExemplaires;
	
	private JRadioButton opera;
	private JRadioButton varietee;
	
	private JButton valider;
	private JButton annuler;
	
	public NouvelleOeuvre(Oeuvres oeuvres)
	{
		super();
		this.oeuvres = oeuvres;
		build();
	}
	
	private void build()
	{
		setTitle("Nouvelle oeuvre");
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
		JLabel nb = new JLabel("Nombre d'exemplaires", JLabel.LEFT);
		
		int text_size = 10;
		textNom = new JTextField(text_size);
		textAuteur = new JTextField(text_size);
		
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
				if (textNom.getText().equals("") || textAuteur.getText().equals("")){
					valider.setEnabled(false);
				}
				else {
					valider.setEnabled(true);
				}
		
			}
		};
		
		textNom.getDocument().addDocumentListener(dl);
		textAuteur.getDocument().addDocumentListener(dl);
		
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
		nbExemplaires = new JSpinner(model);
		
		opera = new JRadioButton("Op�ra");
		varietee = new JRadioButton("Vari�t�e");
		ButtonGroup group = new ButtonGroup();
		group.add(opera);
		group.add(varietee);
		opera.doClick();
		Box box = Box.createHorizontalBox();
		box.add(opera);
		box.add(varietee);
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.setEnabled(false);
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(nom, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(auteur, gbc);
		
		gbc = buildConstraints(0, 2);
		gbc.gridwidth = 2;
		panel.add(box, gbc);
		
		gbc = buildConstraints(0, 3);
		panel.add(nb, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(textNom, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(textAuteur, gbc);
		
		gbc = buildConstraints(1, 3);
		panel.add(nbExemplaires, gbc);
		
		gbc = buildConstraints(0, 4);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(1, 4);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			String nom = textNom.getText();
			String auteur = textAuteur.getText();
			int nb = (Integer) nbExemplaires.getValue();
			if (opera.isSelected())
				oeuvres.addOeuvre(new Opera(nom, auteur, nb));
			else
				oeuvres.addOeuvre(new Varietee(nom, auteur, nb));
		}
		this.dispose();
	}

}
