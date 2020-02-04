package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel{
	
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citzenCheck;
	private JTextField taxField;
	private JLabel taxLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width=300;
		setPreferredSize(dim);
		
		nameLabel = new JLabel("Nome: ");
		occupationLabel = new JLabel("Professione: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		ageList = new JList();
		empCombo = new JComboBox();
		citzenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Codice Fiscale");
		maleRadio = new JRadioButton("maschio");
		femaleRadio = new JRadioButton("femmina");
		genderGroup = new ButtonGroup();
		okBtn = new JButton("Ok");
		
		//Set up mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		maleRadio.setActionCommand("maschio");
		femaleRadio.setActionCommand("femmina");
		
		
		//Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		maleRadio.setSelected(true);
		
		//Set up Codice Fiscale
		taxLabel.setEnabled(false);
		taxField.setEditable(false);
		
		citzenCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = citzenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxField.setEditable(isTicked);
				
			}
			
		});
		
		//Set up list box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0,"Sotto 18."));
		ageModel.addElement(new AgeCategory(1,"Dal 18 fino a 65."));
		ageModel.addElement(new AgeCategory(2,"Sopra 65."));	
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(110,76));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(0);
		
		//Set uo combo box
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("Occupato");
		empModel.addElement("Lavoratore autonomo");
		empModel.addElement("Disoccupato");
		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		empCombo.setEditable(true);
		
		
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empCat = (String)empCombo.getSelectedItem();
				String taxId = taxField.getText();
				boolean itCitizen = citzenCheck.isSelected();
				
				String gender = genderGroup.getSelection().getActionCommand();
				
				FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, 
						taxId, itCitizen, gender );
				
				if(formListener != null) {
					formListener.formEventOcurred(ev);
				}
				
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Aggiungi Persona");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
		

	}
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////// First Row - Nome /////////////////
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		///////////// Second Row - Occupazione /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///////////// Next Row - lista di età /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Età: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);
		
		///////////// Next Row - Combo di Occupazione/////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Occupazione: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCombo, gc);
		

		///////////// Next Row - CheckBox do cidadao italiano /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Cittadino italiano: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citzenCheck, gc);		
		
		///////////// Next Row - TextField do Codice Fiscale /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gc);			
		
		
		///////////// Next Row - Radio Button do Genero masculino/////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Genere: "), gc);
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);				
		
		///////////// Next Row - Radio Button do Genero feminino/////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);	
		
		///////////// Next Row - Pulsante OK /////////////////
		gc.gridy++;
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridx = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);
		
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

}

class AgeCategory {
	
	private int id;
	private String text;
	
	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
		
	}
	
	public String toString() {
		return this.text;
	}
	
	public int getId() {
		return this.id;
	}
}
