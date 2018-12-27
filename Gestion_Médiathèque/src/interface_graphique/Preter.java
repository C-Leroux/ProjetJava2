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

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import mediatheque.Adherent;
import mediatheque.Adherents;
import mediatheque.Oeuvre;
import mediatheque.Oeuvres;
import mediatheque.Opera;
import mediatheque.Varietee;

public class Preter extends JDialog implements ActionListener {
	
	private Adherents adherents;
	private Oeuvres oeuvres;
	
	private JComboBox<Adherent> listeAdh;
	private JComboBox<Oeuvre> listeOeu;
	private JTextField textDate;
	
	private JButton valider;
	private JButton annuler;
	
	public Preter(Adherents adherents, Oeuvres oeuvres)
	{
		super();
		this.adherents = adherents;
		this.oeuvres = oeuvres;
		build();
	}
	
	private void build()
	{
		setTitle("Prêter un exemplaire");
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
		
		JLabel adh = new JLabel("Adherent", JLabel.LEFT);
		JLabel oeu = new JLabel("Oeuvre empruntée", JLabel.LEFT);
		JLabel date = new JLabel("Date butoire", JLabel.LEFT);
		
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
		
		textDate = new JTextField("jj/mm/aaaa");
		
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		
		valider.addActionListener(this);
		annuler.addActionListener(this);
		
		GridBagConstraints gbc;
		
		gbc = buildConstraints(0, 0);
		panel.add(adh, gbc);
		
		gbc = buildConstraints(0, 1);
		panel.add(oeu, gbc);
		
		gbc = buildConstraints(0, 2);
		panel.add(date, gbc);
		
		gbc = buildConstraints(1, 0);
		panel.add(listeAdh, gbc);
		
		gbc = buildConstraints(1, 1);
		panel.add(listeOeu, gbc);
		
		gbc = buildConstraints(1, 2);
		panel.add(textDate, gbc);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == valider)
		{
			Adherent adherent = (Adherent) listeAdh.getSelectedItem();
			Oeuvre oeuvre = (Oeuvre) listeOeu.getSelectedItem();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date = format.parse(textDate.getText());
				adherent.emprunter(oeuvre, date);
				this.dispose();
			} catch (ParseException e1) {
				// Label error
			}
		}
		else
			this.dispose();
	}

}
