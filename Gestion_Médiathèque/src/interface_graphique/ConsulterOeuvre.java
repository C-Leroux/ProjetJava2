package interface_graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import mediatheque.Adherent;
import mediatheque.Exemplaire;
import mediatheque.Oeuvre;
import mediatheque.Oeuvres;
import mediatheque.Pret;

public class ConsulterOeuvre extends JDialog implements ActionListener {
	
	private Oeuvres oeuvres;			// Liste des oeuvres enregistrees dans l'application
	private Oeuvre oeuvre;				// Oeuvre actuellement selectionnee
	
	private JComboBox<Oeuvre> listeOeu;
	private JCheckBox exemplaireCheck;
	
	private JButton valider;
	private JButton annuler;
	private JButton ajouter;
	
	private JSpinner nbExemplaires;
	
	private JPanel panel2;
	
	public ConsulterOeuvre(Oeuvres oeuvres)
	{
		super();
		this.oeuvres = oeuvres;
		this.oeuvre = null;
		build();
	}
	
	private void build()
	{
		setTitle("Consulter les oeuvres");
		setSize(600, 200);
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
		panel.setAlignmentY(TOP_ALIGNMENT);
		
		JLabel liste = new JLabel("Liste des oeuvres", JLabel.LEFT);
		
		listeOeu = new JComboBox<Oeuvre>(oeuvres.getOeuvres());
		listeOeu.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Oeuvre){
                    Oeuvre oeuvre = (Oeuvre) value;
                    setText(oeuvre.getTitre());
                }
                return this;
            }
        } );
		
		exemplaireCheck = new JCheckBox("Afficher le nombre d'exemplaires");
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(liste, gbc);
		
		gbc = buildConstraints(0, 1);
		gbc.gridwidth = 2;
		panel.add(exemplaireCheck, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeOeu, gbc);
		
		gbc = buildConstraints(0, 2);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(1, 2);
		panel.add(annuler, gbc);
		
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		panel2.setPreferredSize(new Dimension(300,200));
		panel2.setAlignmentY(TOP_ALIGNMENT);
		
		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(panel);
		global.add(panel2);
		
		return global;
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
	
	private void actualiser()
	{
		panel2.removeAll();
		
		Box box = Box.createVerticalBox();
		box.setAlignmentY(TOP_ALIGNMENT);
		box.add(new JLabel("Oeuvre : " + oeuvre.getTitre()));
		box.add(new JLabel("Auteur : " + oeuvre.getAuteur()));
		box.add(new JLabel("Genre : " + oeuvre.getGenre()));
		
		if (exemplaireCheck.isSelected())
		{
			box.add(new JLabel("Nombre total d'exemplaires : " + oeuvre.getNb()));
			box.add(new JLabel("Nombre d'exemplaires disponibles : " + oeuvre.getDispo()));
			
			SpinnerModel model = new SpinnerNumberModel();
			nbExemplaires = new JSpinner(model);
			Component editor = nbExemplaires.getEditor();
			JFormattedTextField field = ((JSpinner.DefaultEditor) editor).getTextField();
			field.setColumns(4);
			
			ajouter = new JButton("Ajouter exemplaires");
			
			JPanel box2 = new JPanel();
			box2.setLayout(new FlowLayout());
			box2.setAlignmentX(LEFT_ALIGNMENT);
			box2.add(nbExemplaires);
			box2.add(ajouter);
			
			panel2.add(box);
			panel2.add(box2);
			
			ajouter.addActionListener(this);
		}
		else
			panel2.add(box);
		
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			oeuvre = (Oeuvre) listeOeu.getSelectedItem();
			if (oeuvre == null)
				return;
			
			actualiser();
		}
		else if (source == ajouter)
		{
			oeuvre.ajouterExemplaire((Integer) nbExemplaires.getValue()); 
			nbExemplaires.setValue(0);
			
			actualiser();
		}
		else
			this.dispose();
	}

}
