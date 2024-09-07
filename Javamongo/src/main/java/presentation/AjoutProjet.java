package presentation;
import Models.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Savepoint;

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
import Persistence.ProjetDao;

public class AjoutProjet extends JFrame {
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
    private FrameProjet frameProjet;
    
    public AjoutProjet() {
    
    }
    
    public AjoutProjet(FrameProjet frameProjet) {
        // Créer une nouvelle fenêtre JFrame pour contenir le formulaire
        frame = new JFrame("Ajouter un Projet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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
        saveButton = new JButton("Ajouter");

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
        this.frameProjet=frameProjet;
        // Ajoutez un ActionListener au bouton "Ajouter"
       /* saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérez les données saisies dans les champs de texte
                String nom = nameField.getText();
                String categorie = categoryField.getText();
                String type = typeField.getText();
                String dateDebut = startDateField.getText();
                String dateFin = endDateField.getText();
                String description = descriptionField.getText();

                // Créez un nouvel objet Project avec ces données
                ProjetAjoutModel nouveauProjet = new ProjetAjoutModel( nom, categorie, type, description, dateDebut, dateFin);

                // Appelez la méthode pour ajouter ce projet à la liste dans FrameProjet
                frameProjet.addProject(nouveauProjet);

                // Affichez un message de succès
                showMessage("Projet ajouté avec succès !");
            }
        });  */
        
        
    }
        
        
        
    
    
  /*  public void action() {
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Récupérer les données saisies dans la vue
            String nom = nameField.getText();
            String categorie = categoryField.getText();
            String type = typeField.getText();
            String dateDebut = startDateField.getText();
            String dateFin = endDateField.getText();
            String description = descriptionField.getText();

            // Mettre à jour le modèle avec les données
            Project p = new Project(null, nom, categorie, type, description, dateDebut, dateFin);
            ProjetDao p1 = new ProjetDao();
            p1.createPrjt(p);

            // Afficher un message de confirmation
            showMessage("Projet ajouté avec succès !");
        }
    });
} */
    
}


