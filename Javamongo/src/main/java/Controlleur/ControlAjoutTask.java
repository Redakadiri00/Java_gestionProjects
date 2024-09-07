package Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gestion.GestionTask;
import Metiers.Tache;
import presentation.AjoutTask;

public class ControlAjoutTask {

    private AjoutTask aj;
    private Tache t;
    private GestionTask gestion;

    public ControlAjoutTask() {
       
    }

    public ControlAjoutTask(AjoutTask aj, Tache t, GestionTask gestion) {
        this.aj = aj;
        this.t = t;
        this.gestion = gestion;
        aj.getSaveButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterTask();
              }
          });
    }

    public void ajouterTask() {
        String intitule = aj.getNameText();
        String category = aj.getCategoryText();
        String dateD = aj.getStartDateText();
        String dateF = aj.getEndDateText();
        String description = aj.getDescriptionText();
        Tache t = new Tache(null, intitule, category, description, dateD, dateF);
        gestion.createTask(t);
        aj.showMessage("Projet ajouté avec succès !");
    }

    public static void main(String[] args) {
    	AjoutTask a=new AjoutTask();
    	GestionTask g=new GestionTask();
    	Tache t=new Tache();
        new ControlAjoutTask(a,t,g);
    }

    public GestionTask getGestion() {
        return gestion;
    }
}