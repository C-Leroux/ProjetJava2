package interface_graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import mediatheque.Adherent;
import mediatheque.Adherents;

public class SupprimerAdherent extends JDialog implements ActionListener {
	
	private Adherents adherents;
	
	private JComboBox<Adherent> listeAdh;
	
	private JButton valider;
	private JButton annuler;
	
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
		
		// On cree la Combo Box contenant la liste des adherents, puis on lui donne un renderer qui va permettre
		// d'afficher le nom complet des adherents de la liste
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
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == valider)
		{
			Adherent adherent = (Adherent) listeAdh.getSelectedItem();
			adherents.removeAdherent(adherent);
		}
		this.dispose();
	}

}
