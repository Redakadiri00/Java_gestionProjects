package Controlleur;
import java.io.File;

import javax.swing.JFileChooser;

import Metiers.*;
import Models.*;
import presentation.*;
import Persistence.*;

public class ControlAffichageProjet {
	AffichageProjet affichageprojet;
	ProjetDao projetdao;
	
	public ControlAffichageProjet() {
		this.affichageprojet=new AffichageProjet();
		this.projetdao= new ProjetDao();
	}
	
	
		
		
	
	
	
	private void importDocuments() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setMultiSelectionEnabled(true);
	    int result = fileChooser.showSaveDialog(this);
	    if (result == JFileChooser.APPROVE_OPTION) {
	        File[] selectedFiles = fileChooser.getSelectedFiles();
	        // Display the selected documents
	        for (File file : selectedFiles) {
	        	affichageprojet.getFileListModel().addElement(file);
	            // Call the insertFile method to add the file to the project
	            insertFile(project, file); // Assuming 'project' is the current project
	        }
	    }
	}
	
}

