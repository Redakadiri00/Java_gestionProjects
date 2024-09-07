package Gestion;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import Metiers.Fichiers;
import Metiers.Project;
import Metiers.Tache;
import Persistence.ProjetDao;
import Persistence.TaskDao;

public class GestionTask {

	TaskDao tachedao;
	ProjetDao projetdao;
	
	
	
	
	public GestionTask() {
		this.tachedao=new TaskDao();	
	}

	public void createTask(Tache t)  {
        String nomTache= t.getIntitule();

       if (tachedao.isExistingTache(nomTache)) {
           showMessage("Un projet avec le nom '" + nomTache + "' existe déjà.");
            return; }
       tachedao.createtTache(t);
         showMessage("tache ajouté");
    }
	
	private void showMessage(String string) {
	
		
	}
	
	
	public void ajouterTache(Tache task, Fichiers file) {
		tachedao.insertFileToTask(task, file);
	}
	
	
	
	
	
}