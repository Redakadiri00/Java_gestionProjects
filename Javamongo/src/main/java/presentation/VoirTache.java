package presentation;

import java.awt.*;

import javax.swing.*;
import javax.swing.SwingUtilities;

public class VoirTache extends JFrame {

	JButton supprimerButton, modifierButton, ajouterButton;
    JButton DateButton, EtatButton, CategorieButton;
    JTextField TextField;
    JLabel rechercheLabel;
    JLabel MesTaches, FilterLabel;
    JPanel Paneltop, Panelmiddle, Panelbottom, PanelPrincipale;
    JPanel TopButtonPanel, RecherchePanel;
    JList<String> taskList; // JList to display tasks

   public VoirTache() {
       // Frame settings
       setTitle("Voir Tache");
       setSize(1000, 700);
       setDefaultCloseOperation(EXIT_ON_CLOSE);

       // Components
       MesTaches = new JLabel("mes Taches");
       MesTaches.setFont(new Font("Arial", Font.BOLD, 24));
       // Create TopButtonPanel to hold buttons
       TopButtonPanel = new JPanel();
       supprimerButton = new JButton("Supprimer");
       modifierButton = new JButton("Modifier");
       ajouterButton = new JButton("Ajouter");
       TopButtonPanel.add(ajouterButton);
       TopButtonPanel.add(modifierButton);
       TopButtonPanel.add(supprimerButton);

       Paneltop = new JPanel(new BorderLayout()); // Use BorderLayout for Paneltop
       Paneltop.add(MesTaches, BorderLayout.LINE_START); // Add MesTaches label to the left
       Paneltop.add(TopButtonPanel, BorderLayout.EAST); // Add TopButtonPanel to the right

       RecherchePanel = new JPanel();
       FilterLabel = new JLabel("Filtrer Par:");
       EtatButton = new JButton("Etat");
       DateButton = new JButton("Date");
       CategorieButton = new JButton("Categorie");
       rechercheLabel = new JLabel("Recherche");
       TextField = new JTextField(15);
       RecherchePanel.add(rechercheLabel);
       RecherchePanel.add(TextField);

       Panelmiddle = new JPanel();
       Panelmiddle.add(FilterLabel);
       Panelmiddle.add(DateButton);
       Panelmiddle.add(EtatButton);
       Panelmiddle.add(CategorieButton);
       Panelmiddle.add(RecherchePanel, BorderLayout.EAST);

       // Create a list of tasks for Panelbottom
       String[] tasks = {"Task 1", "Task 2", "Task 3","bo3oo"}; // Example tasks
       taskList = new JList<>(tasks);
       JScrollPane taskScrollPane = new JScrollPane(taskList);
       taskScrollPane.setPreferredSize(new Dimension(800, 500)); // Set preferred size for the scroll pane

       Panelbottom = new JPanel(new BorderLayout()); // Use BorderLayout for Panelbottom
       Panelbottom.add(new JLabel("List of Tasks:"), BorderLayout.NORTH);
       Panelbottom.add(taskScrollPane, BorderLayout.CENTER); // Add taskScrollPane to take up most of the space

       PanelPrincipale = new JPanel(new BorderLayout());
       PanelPrincipale.add(Paneltop, BorderLayout.NORTH);
       PanelPrincipale.add(Panelmiddle, BorderLayout.WEST); // Add Panelmiddle to the center
       PanelPrincipale.add(Panelbottom, BorderLayout.SOUTH); // Add Panelmiddle to the center

       getContentPane().add(PanelPrincipale);
       setVisible(true);
   }

   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               new VoirTache();
           }
       });
   }
}
