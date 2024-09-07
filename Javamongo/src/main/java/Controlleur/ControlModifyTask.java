package Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gestion.*;
import presentation.*;

public class ControlModifyTask {
    private GestionTask gestion;
    private ModifyTask view;

    // Constructor
    public ControlModifyTask(GestionTask gestion, ModifyTask view) {
        this.gestion = gestion;
        this.view = view;

        // Add ActionListener to the "Modifier" button
        view.getSaveButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Gather information from UI components
                String intitule = view.getNameText();
                String categorie = view.getCategoryText();
                String desc = view.getDescriptionText();
                
                String dateD = view.getStartDateText();
                String dateF = view.getEndDateText();

                // Call the method in Gestion class to modify the task
                gestion.modifyTask(intitule, categorie, desc, dateD, dateF);
        	}
        });  
    // ActionListener for the "Modifier" button
    }
}
