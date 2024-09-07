package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controlleur.ControlAjoutProjet;
import Controlleur.ControlFrameProjet;
import Gestion.GestionProjet;
import Metiers.Project;
import Models.*;

public class FrameProjet extends JFrame {
	
	ControlFrameProjet c ;
	private ListeProjetModel lpm;
    private DefaultListModel<String> projectListModel = new DefaultListModel<>();
    public JList<String> projectList;
    private AffichageProjet affichageProjet;
    
    GestionProjet gestionprojet;
    JLabel jl=new JLabel("Encadrement:");
    JButton j11=new JButton("PFA");
    JButton j12=new JButton(" These ");
    JButton j13=new JButton("PFE");
    JLabel j= new JLabel("Enseignement:");
    JButton jl4=new JButton("Cours ");
    JButton jl5=new JButton("Examen");
    JButton jl6=new JButton("All projects");
    JButton ajouterButton ;
    JButton rechercher;
    JTextField searchField;
    JButton button6;
    
    
    
    
    
    
    public JList<String> getProjectList() {
		return projectList;
	}

	public void setProjectList(JList<String> projectList) {
		this.projectList = projectList;
	}
	
	
	
	
	
	
    //AjoutProjetModel selectedProject;
    
    
    
    public FrameProjet() {
    	this.lpm = new ListeProjetModel(); // Initialize the model
        this.c = new ControlFrameProjet(this, lpm, gestionprojet);
        projectList = new JList<>();
        
         this.setBackground(Color.black);
         this.setTitle("Interface Projet ");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1000, 700);
            this.setLayout(new BorderLayout());
            JPanel toolbar = new JPanel(new BorderLayout());
            // Création du panneau pour les boutons à gauche
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Alignement vertical
            JLabel p=new JLabel("Encadrement :");   
            JButton button1 = new JButton("PFE");
            JButton button2 = new JButton("PFA");
            JButton button3 = new JButton("THESE");
            JLabel p1=new JLabel("Enseignement :");
            JButton button4 = new JButton("Cours");
            JButton button5 = new JButton("Examen");
            JButton button6 = new JButton("All projects");
            JTextField searchField = new JTextField();
            searchField.setPreferredSize(new Dimension(200, 30)); 
            JButton rechercher=new JButton("rechercher");
            ImageIcon searchIcon = new ImageIcon("search_icon.png"); // Remplacez "search_icon.png" par le chemin de votre propre icône
            JButton searchButton = new JButton(searchIcon);
            searchButton.setPreferredSize(new Dimension(50, 30)); // Taille de l'icône de recherche
            searchButton.setBorderPainted(false); // Supprime la bordure du bouton
            searchButton.setContentAreaFilled(false); // Supprime le remplissage du bouton
            searchButton.setFocusPainted(false); // Supprime le focus visuel du bouton
            searchButton.setMargin(new Insets(0, 0, 0, 0)); // Ajuste la marge du bouton
            // Création du panneau pour la barre de recherche avec icône
            JPanel searchPanel = new JPanel();
            searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Alignement à droite
            searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajout de marges autour du panneau
            searchPanel.add(searchField);
            searchPanel.add(rechercher);

          

            // Création du JScrollPane pour la liste de projets
            JScrollPane scrollPane = new JScrollPane(projectList);
            leftPanel.add(p);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button1);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button2);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button3);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(p1);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button4);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button5);
            leftPanel.add(Box.createVerticalStrut(10));
            leftPanel.add(button6);
            toolbar.add(leftPanel, BorderLayout.WEST);
            Dimension buttonSize = new Dimension(200, 50); // Largeur x Hauteur
            button1.setPreferredSize(buttonSize);
            button2.setPreferredSize(buttonSize);
            button3.setPreferredSize(buttonSize);
            button4.setPreferredSize(buttonSize);
            button5.setPreferredSize(buttonSize);
            button6.setPreferredSize(buttonSize);
            // Ajout de la barre d'outils à la fenêtre
            add(toolbar,BorderLayout.WEST);
            add(searchPanel,BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
            toolbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            // Create panel for "ajouter" and "supprimer" buttons
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            ajouterButton = new JButton("Ajouter");
            ajouterButton.addActionListener(new AjouterButtonListener());
            JButton supprimerButton = new JButton("Supprimer");
            bottomPanel.add(ajouterButton);
            bottomPanel.add(supprimerButton);
            add(bottomPanel, BorderLayout.SOUTH);
            setVisible(true);
            
            button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filterAction("pfe");
                }
            });
            
            button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filterAction("pfa");
                }
            });
            
            button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filterAction("these");
                }
            });
            
            button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filterAction("cours");
                }
            });
            
            button5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filterAction("examen");
                }
            });
            
            button6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	c.ContorlReadAllProjetcts();
                }
            });
            
            projectList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && !e.isConsumed()) { // Double-click event
                        int selectedIndex = projectList.getSelectedIndex();
                        if (selectedIndex != -1) {
                            Project selectedProject = lpm.getProjects().get(selectedIndex);
                            affichageProjet = new AffichageProjet(selectedProject); // Create AffichageProjet instance with selected project
                            affichageProjet.setVisible(true); // Show AffichageProjet interface
                            e.consume(); // Prevent further actions from the click
                        }
                    }
                }
            });
            
            
        }
    
    
    public JButton get_AllProjects() {
    	return button6;
    }
    
    public void addProject(Project project) {
        lpm.getProjects().add(project);
        projectListModel.addElement(project.getNomProjet());
        projectList.setModel(projectListModel);
    }
  
    
    class AjouterButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		if(e.getSource()== ajouterButton ) {
                c.afficherAjout();
    		}
    	}
    }
    
    
    
    public void filterAction(String type) {
        // Call the FilterByType method in your ControlFrameProjet class
        ArrayList<Project> filteredProjects = c.FilterByType(type);

        // Update the JList with the filtered projects
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        for (Project project : filteredProjects) {
            filteredModel.addElement(project.getNomProjet());
        }
        projectList.setModel(filteredModel);
    }
    
    
    
    
    public JButton getAjouterButton() {
		return ajouterButton;
	}

	public void setAjouterButton(JButton ajouterButton) {
		this.ajouterButton = ajouterButton;
	}

	public static void main(String[] args) {
		new FrameProjet();
	}	
	
}

