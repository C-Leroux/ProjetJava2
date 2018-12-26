package interface_graphique;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mediatheque.Adherent;
import mediatheque.Adherents;
import mediatheque.Exemplaire;
import mediatheque.Pret;

public class ConsulterAdherent extends JDialog implements ActionListener {
	
	private Adherents adherents;
	private Adherent adherent;
	
	private JComboBox<Adherent> listeAdh;
	private JCheckBox coordCheck;
	private JCheckBox pretsCheck;
	
	private JButton valider;
	private JButton annuler;
	
	private JPanel panel2;
	
	public ConsulterAdherent(Adherents adherents)
	{
		super();
		this.adherents = adherents;
		this.adherent = null;
		build();
	}
	
	private void build()
	{
		setTitle("Consulter les adhérents");
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
		
		coordCheck = new JCheckBox("Afficher les coordonnées de l'adhérent");
		pretsCheck = new JCheckBox("Afficher les prêts en cours");
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(liste, gbc);
		
		gbc = buildConstraints(0, 1);
		gbc.gridwidth = 2;
		panel.add(coordCheck, gbc);
		
		gbc = buildConstraints(0, 2);
		gbc.gridwidth = 2;
		panel.add(pretsCheck, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeAdh, gbc);
		
		gbc = buildConstraints(0, 3);
		panel.add(valider, gbc);
		
		gbc = buildConstraints(1, 3);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			panel2.removeAll();
			
			Adherent adherent = (Adherent) listeAdh.getSelectedItem();
			if (adherent == null)
				return;
			
			Box box = Box.createVerticalBox();
			box.setAlignmentY(TOP_ALIGNMENT);
			box.add(new JLabel("Nom de l'adhérent : " + adherent.getNomPrenom()));
			
			if (coordCheck.isSelected())
			{
				box.add(new JLabel("Adresse : " + adherent.getAdresse()));
			}
			
			if (pretsCheck.isSelected())
			{
				Hashtable<Exemplaire,Pret> prets = adherent.getdicExemplaiPret();
				
				Set<Exemplaire> keys = prets.keySet();
				for (Exemplaire key : keys)
				{
					Box box2 = Box.createHorizontalBox();
					box2.add(new JLabel(key.toString()));
					box2.add(new JLabel(key.getOeuvre().toString()));
					box2.add(new JLabel(prets.get(key).toString()));
					box.add(box2);
				}
			}
			panel2.add(box);
			validate();
			repaint();
		}
		else
			dispose();
		
	}
	
}