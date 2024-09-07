package Controlleur;
import Models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import Gestion.*;
import Metiers.*;
import Persistence.*;
import presentation.*;
import Models.*;
	
public class ControlAjoutProjet {
    private AjoutProjet view;
    private static AjoutProjetModel model;
    private GestionProjet gestionprojet;
    private FrameProjet frameProjet;
    private ArrayList<AjoutProjetModel> projects;
    
    
    public ControlAjoutProjet() {
    	      
    }
    
    public ControlAjoutProjet(AjoutProjet view, AjoutProjetModel model, GestionProjet gestionprojet) {
    	this.view = new AjoutProjet();
        this.model = model;
        this.gestionprojet = gestionprojet;
        
        // Ajouter un ActionListener pour le bouton d'ajout
        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les données saisies dans la vue
                String nom = view.getNameField().getText();
                String categorie = view.getCategoryField().getText();
                String type = view.getTypeField().getText();
                String dateDebut = view.getStartDateField().getText();
                String dateFin = view.getEndDateField().getText();
                String description = view.getDescriptionField().getText();
                    
                Project p=new Project(nom, categorie, type, description, dateDebut, dateFin);
                view.showMessage("Projet ajouté avec succès !");
                gestionprojet.gestionCreateProjet(p);
                    
            }
        });  
    }
    
    public void showAjoutProjetFrame() {
        view.setVisible(true); // Assuming AjoutProjet extends JFrame and has a setVisible method
    }
    
    public static void main(String[] args) {
    	FrameProjet fr=new FrameProjet();
    	AjoutProjet view = new AjoutProjet(fr);
    	AjoutProjetModel model = new AjoutProjetModel();
    	GestionProjet gestionprojet = new GestionProjet(); // or however you initialize it
    	new ControlAjoutProjet(view, model, gestionprojet);
    }
}