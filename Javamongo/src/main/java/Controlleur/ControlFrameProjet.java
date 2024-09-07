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


public class ControlFrameProjet {
	ProjetDao projetdao;
	private FrameProjet view;
    private GestionProjet gestionprojet;
    private FrameProjet frameProjet;
    private ListeProjetModel lpm;
    
    
    public ListeProjetModel getLpm() {
		return lpm;
	}

	public void setLpm(ListeProjetModel lpm) {
		this.lpm = lpm;
	}


	private AjoutProjet ap;
    private ControlAjoutProjet cap;
    
	 
    
    public ControlFrameProjet() {
    	
    }
    public void afficherAjout() {
    	cap=new ControlAjoutProjet();
    	cap.showAjoutProjetFrame();
    }
    
    public ControlFrameProjet(FrameProjet view, ListeProjetModel model, GestionProjet gestionprojet) {
    	this.view = view;
        this.lpm = new ListeProjetModel();
        this.gestionprojet = new GestionProjet();
        this.projetdao =new ProjetDao();
        this.ap= new AjoutProjet();
        this.cap= new ControlAjoutProjet();
        
        
        
        
        
//        view.get_AllProjects().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Récupérer les données saisies dans la vue
//                String nom = view.getNameField().getText();
//                
//
//                // Mettre à jour le modèle avec les données
//           /*   model.setNomProjet(nom);
//                model.setCategoryPrjt(categorie);
//                model.setTypePrjt(type);
//                model.setDateDebutprjt(dateDebut);
//                model.setDateFinprjt(dateFin);
//                model.setDescPrjt(description); */
//                    
//                Project p=new Project(null, nom, categorie, type, description, dateDebut, dateFin);
//                view.showMessage("Projet ajouté avec succès !");
//                gestionprojet.gestionCreateProjet(p);
//               /* ProjetDao p1=new ProjetDao();
//                p1.createPrjt(p); */
//                    
//               // frameProjet.addProject(p);        
//            }
//        }); 
    }
    
    public void AllezVersProjet() {
    	
            
        }
    
    	
    
    
    
    public void ContorlReadAllProjetcts() {
    	 // Call the method to retrieve all projects
        lpm.setProjects(gestionprojet.GestionReadAllProjects());
        
        // Update the JList with the retrieved projects
        DefaultListModel<String> projectListModel = new DefaultListModel<>();
        for (Project project : lpm.getProjects()) {
            projectListModel.addElement(project.getNomProjet()); // Assuming 'getName()' method returns project name
        }
        view.projectList.setModel(projectListModel);
    }
    
    
    
    public ArrayList<Project> FilterByType(String type) {
        // Call the searchByType method from your DAO class
        return projetdao.searchByType(type);
    }
    
    public void filterAction(String type) {
    	lpm.setProjects(gestionprojet.FilterByType(type));
        // Filter the projects based on the selected category
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        for (Project project : lpm.getProjects()) {
            if (project.getTypePrjt().equals(type)) {
                filteredModel.addElement(project.getNomProjet());
            }
        }
        view.projectList.setModel(filteredModel); // Update the JList with filtered model
    }
    
    
    public static void main(String[] args) {
    	FrameProjet view = new FrameProjet();
        ListeProjetModel model = new ListeProjetModel();
        GestionProjet gestionprojet = new GestionProjet();
        // Instantiate the controller and pass necessary objects
        new ControlFrameProjet(view, model, gestionprojet);
    }
    

    
   

}
