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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import mediatheque.Adherent;
import mediatheque.Oeuvre;
import mediatheque.Oeuvres;

public class SupprimerOeuvre extends JDialog implements ActionListener {
	
	private Oeuvres oeuvres;
	
	private JComboBox<Oeuvre> listeOeu;
	
	private JButton valider;
	private JButton annuler;
	
	public SupprimerOeuvre(Oeuvres oeuvres)
	{
		super();
		this.oeuvres = oeuvres;
		build();
	}
	
	private void build()
	{
		setTitle("Supprimer une oeuvre");
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
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(liste, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeOeu, gbc);
		
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
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			Oeuvre oeuvre = (Oeuvre) listeOeu.getSelectedItem();
			oeuvres.removeOeuvre(oeuvre);
			revalidate();
			repaint();
		}
		this.dispose();
	}

}
