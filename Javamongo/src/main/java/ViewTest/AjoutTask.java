package ViewTest;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Metiers.Liste;
import Metiers.Tache;
import TestController.ControllerListe;

public class AjoutTask extends JFrame {

    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel descriptionLabel;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionField;
    private ControllerListe c;
    private JButton saveButton;
	private String selectedList;
	
    public AjoutTask(ControllerListe c,String selectedList) {
    	this.c=c;
    	
        setTitle("Ajout de Tâche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        nameLabel = new JLabel("Nom:");
        categoryLabel = new JLabel("Catégorie:");
        startDateLabel = new JLabel("Date Début:");
        endDateLabel = new JLabel("Date Fin:");
        descriptionLabel = new JLabel("Description:");
        nameField = new JTextField(20);
        categoryField = new JTextField(20);
        startDateField = new JTextField(20);
        endDateField = new JTextField(20);
        descriptionField = new JTextArea(5, 20);
        saveButton = new JButton("Enregistrer");
        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.WHITE;
        Color textFieldBackgroundColor = new Color(230, 230, 230);
        Color buttonBackgroundColor = new Color(0, 153, 204);
        Color buttonTextColor = Color.WHITE;
        Color panelBackgroundColor = new Color(45, 45, 45);

        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        categoryLabel.setFont(labelFont);
        categoryLabel.setForeground(labelColor);
        startDateLabel.setFont(labelFont);
        startDateLabel.setForeground(labelColor);
        endDateLabel.setFont(labelFont);
        endDateLabel.setForeground(labelColor);
        descriptionLabel.setFont(labelFont);
        descriptionLabel.setForeground(labelColor);
        nameField.setBackground(textFieldBackgroundColor);
        categoryField.setBackground(textFieldBackgroundColor);
        startDateField.setBackground(textFieldBackgroundColor);
        endDateField.setBackground(textFieldBackgroundColor);
        descriptionField.setBackground(textFieldBackgroundColor);
        saveButton.setBackground(buttonBackgroundColor);
        saveButton.setForeground(buttonTextColor);
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBackground(panelBackgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionField));
        panel.add(saveButton);
        add(panel);
        //setVisible(true);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String intitule = nameField.getText();
                 String category = categoryField.getText();
                 String dateD = startDateField.getText();
                 String dateF = endDateField.getText();
                 String description = descriptionField.getText();
                 Tache t = new Tache(intitule, category, description, dateD, dateF);
                 String nom=c.getList().getTaskList().getSelectedValue();
                 
               // Liste lala=c.getListByName(nom);
              //  System.out.println(lala);
               // c.insererTask(lala, t);
                c.insererTask1(nom, t);
                DefaultListModel<String> model = (DefaultListModel<String>) c.getD().getTaskList().getModel();
                model.addElement(intitule); 
               
             //    String lnom=c.recupérerListe();
               
                
                // afficherMessage("tache ajouté avec succès !");

            }
        }); 
     
        

       
    }

   
    
    

    // Getters and setters
    public String getNameText() {
        return nameField.getText();
    }

    public void setNameText(String nameText) {
        nameField.setText(nameText);
    }

    public String getCategoryText() {
        return categoryField.getText();
    }

    public void setCategoryText(String categoryText) {
        categoryField.setText(categoryText);
    }

    public String getStartDateText() {
        return startDateField.getText();
    }

    public void setStartDateText(String startDateText) {
        startDateField.setText(startDateText);
    }

    public String getEndDateText() {
        return endDateField.getText();
    }

    public void setEndDateText(String endDateText) {
        endDateField.setText(endDateText);
    }

    public String getDescriptionText() {
        return descriptionField.getText();
    }

    public void setDescriptionText(String descriptionText) {
        descriptionField.setText(descriptionText);
    }
   
    public void showMessage(String string) {
        // TODO Auto-generated method stub
    }

	public JButton getSaveButton() {
		return saveButton;
	}

	public void afficherMessage(String message) {
	    JOptionPane.showMessageDialog(this, message);
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
	
	public static void main(String[] args) {
    	ControllerListe c=new ControllerListe();
	}
    
}