package interface_graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mediatheque.Adherent;
import mediatheque.Adherents;
import mediatheque.Exemplaire;
import mediatheque.Oeuvre;
import mediatheque.Oeuvres;

public class Rendre extends JDialog implements ActionListener {
	
	private Adherents adherents;
	private Oeuvres oeuvres;
	private Adherent adherent; // Adherent actuellement selectionne
	
	private JPanel panel;
	
	private JComboBox<Adherent> listeAdh;
	private JComboBox<Exemplaire> listeExe;
	
	JLabel exe = null;
	
	private JButton valider;
	private JButton annuler;
	
	public Rendre(Adherents adherents, Oeuvres oeuvres)
	{
		super();
		this.adherents = adherents;
		this.oeuvres = oeuvres;
		build();
	}
	
	private void build()
	{
		setTitle("Rendre un exemplaire");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane()
	{
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.white);
		
		JLabel adh = new JLabel("Adherent", JLabel.LEFT);
		
		listeAdh = new JComboBox<Adherent>(adherents.getAdherents());
		listeAdh.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Adherent){
                    Adherent adherent = (Adherent) value;
                    setText(adherent.getNomPrenom());
                }
                return this;
            }
        } );
		
		listeAdh.addActionListener(this);
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.setEnabled(false);
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		adherent = (Adherent) listeAdh.getSelectedItem();
		
		if (adherent != null)
			setComboBox();
		else
			valider.setEnabled(false);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(adh, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeAdh, gbc);
		
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		gbc.gridx = x;
		gbc.gridy = y;
		
		return gbc;
	}
	
	private void setComboBox()
	{
		if (exe == null)
			exe = new JLabel("Exemplaire à rendre", JLabel.LEFT);
		else
		{
			panel.remove(exe);
			panel.remove(listeExe);
		}
		
		Vector<Exemplaire> exemplaires = new Vector<Exemplaire>(adherent.getdicExemplaiPret().keySet());
		listeExe = new JComboBox<Exemplaire>(exemplaires);
		listeExe.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Exemplaire){
                    Exemplaire exemplaire = (Exemplaire) value;
                    setText(exemplaire.getOeuvre().getTitre() + ", n°" + Integer.toString(exemplaire.getNumero())); // TODO : Améliorer l'affichage de l'exemplaire
                }
                return this;
            }
        } );
		
		GridBagConstraints gbc;	
		
		gbc = buildConstraints(0, 1);
		panel.add(exe, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(listeExe, gbc);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			Exemplaire exemplaire = (Exemplaire) listeExe.getSelectedItem();
			adherent.ramener(exemplaire);
			this.dispose();
		}
		else if (source == listeAdh)
		{
			adherent = (Adherent) listeAdh.getSelectedItem();
			if (adherent != null)
			{
				setComboBox();
				if (listeExe.getSelectedItem() == null)
					valider.setEnabled(false);
				else
					valider.setEnabled(true);
			}
			else
				valider.setEnabled(false);
			revalidate();
			repaint();
		}
		else
			this.dispose();
	}

}
