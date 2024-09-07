package ViewTest;


import TestController.ProjetControl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Metiers.Project;


public class AjoutProjetTest extends JFrame {
    private JFrame frame;
    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel typeLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel descriptionLabel;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField typeField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionField;
    private JButton saveButton;
    private ProjetControl controlleur;
    
    
    
    public AjoutProjetTest(ProjetControl controlleur) {
    	this.controlleur=controlleur;
    	
    	
    	
        // Créer une nouvelle fenêtre JFrame pour contenir le formulaire
        frame = new JFrame("Ajouter un Projet");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        // Initialiser les composants du formulaire
        nameLabel = new JLabel("Nom:");
        categoryLabel = new JLabel("Catégorie:");
        typeLabel = new JLabel("Type :");
        startDateLabel = new JLabel("Date de Début:");
        endDateLabel = new JLabel("Date de Fin:");
        descriptionLabel = new JLabel("Description:");
        nameField = new JTextField(20);
        categoryField = new JTextField(20);
        typeField = new JTextField(20);
        startDateField = new JTextField(20);
        endDateField = new JTextField(20);
        descriptionField = new JTextArea(5, 20);
        saveButton = new JButton("Save");

        // Appliquer un style moderne aux composants
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color textFieldBackgroundColor = new Color(230, 230, 230); // Fond gris clair
        Color buttonBackgroundColor = new Color(0, 153, 204); // Fond bleu pour le bouton
        Color buttonTextColor = Color.WHITE; // Texte blanc pour le bouton

        nameLabel.setFont(labelFont);
        categoryLabel.setFont(labelFont);
        startDateLabel.setFont(labelFont);
        endDateLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);
        typeLabel.setFont(labelFont);

        nameField.setBackground(textFieldBackgroundColor);
        categoryField.setBackground(textFieldBackgroundColor);
        startDateField.setBackground(textFieldBackgroundColor);
        endDateField.setBackground(textFieldBackgroundColor);
        descriptionField.setBackground(textFieldBackgroundColor);
        typeField.setBackground(textFieldBackgroundColor);
        saveButton.setBackground(buttonBackgroundColor);
        saveButton.setForeground(buttonTextColor);
        saveButton.setPreferredSize(new Dimension(100, 30)); // Taille préférée pour le bouton

        Font textFieldFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        nameField.setFont(textFieldFont);
        categoryField.setFont(textFieldFont);
        typeField.setFont(textFieldFont);
        startDateField.setFont(textFieldFont);
        endDateField.setFont(textFieldFont);
        descriptionField.setFont(textFieldFont);

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5)); // 0 lignes, 1 colonne
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(typeLabel);
        panel.add(typeField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionField));
        panel.add(saveButton);
        frame.add(panel);
        frame.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        saveButton.addActionListener(new  ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = nameField.getText();
	            String category = categoryField.getText();
	            String type = typeField.getText();
	            String startDate = startDateField.getText();
	            String endDate = endDateField.getText();
	            String description = descriptionField.getText();
	            
	            Project newProject = new Project(name, category, type, description, startDate, endDate);
	            controlleur.SaveProject(newProject);
                JOptionPane.showMessageDialog(AjoutProjetTest.this, "Projet Bien Ajoutee.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                
				
			}		
		});
    }



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}



	public JLabel getNameLabel() {
		return nameLabel;
	}



	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}



	public JLabel getCategoryLabel() {
		return categoryLabel;
	}



	public void setCategoryLabel(JLabel categoryLabel) {
		this.categoryLabel = categoryLabel;
	}



	public JLabel getTypeLabel() {
		return typeLabel;
	}



	public void setTypeLabel(JLabel typeLabel) {
		this.typeLabel = typeLabel;
	}



	public JLabel getStartDateLabel() {
		return startDateLabel;
	}



	public void setStartDateLabel(JLabel startDateLabel) {
		this.startDateLabel = startDateLabel;
	}



	public JLabel getEndDateLabel() {
		return endDateLabel;
	}



	public void setEndDateLabel(JLabel endDateLabel) {
		this.endDateLabel = endDateLabel;
	}



	public JLabel getDescriptionLabel() {
		return descriptionLabel;
	}



	public void setDescriptionLabel(JLabel descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}



	public JTextField getNameField() {
		return nameField;
	}



	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}



	public JTextField getCategoryField() {
		return categoryField;
	}



	public void setCategoryField(JTextField categoryField) {
		this.categoryField = categoryField;
	}



	public JTextField getTypeField() {
		return typeField;
	}



	public void setTypeField(JTextField typeField) {
		this.typeField = typeField;
	}



	public JTextField getStartDateField() {
		return startDateField;
	}



	public void setStartDateField(JTextField startDateField) {
		this.startDateField = startDateField;
	}



	public JTextField getEndDateField() {
		return endDateField;
	}



	public void setEndDateField(JTextField endDateField) {
		this.endDateField = endDateField;
	}



	public JTextArea getDescriptionField() {
		return descriptionField;
	}



	public void setDescriptionField(JTextArea descriptionField) {
		this.descriptionField = descriptionField;
	}



	public JButton getSaveButton() {
		return saveButton;
	}



	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}



	public ProjetControl getControlleur() {
		return controlleur;
	}



	public void setControlleur(ProjetControl controlleur) {
		this.controlleur = controlleur;
	}
    
    
    public static void main(String[] args) {
		//ProjetControl pc = new ProjetControl();

	}
    
    
    	
    	
    
    
    
    
    
    
  
}


