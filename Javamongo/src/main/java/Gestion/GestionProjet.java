package Gestion;
import Persistence.*;

import org.bson.Document;

import Metiers.Fichiers;
import Metiers.Project;
import Metiers.Seance;
import Metiers.Tache;

import java.util.ArrayList;
import java.util.List;

public class GestionProjet {
	
	Project projet;
	ProjetDao projetdao;
	
	public GestionProjet() {
        this.projetdao = new ProjetDao();
        this.projet=projet;  
    }
	
	public void gestionCreateProjet(Project projet) {
		
	    if (projetdao.exists(projet)) {
	        System.out.println("Project already exists in the database.");
	        return;
	    }
	    projetdao.createPrjt(projet);
	    
	}
	
	public List<Project> GestionReadAllProjects() {
        return projetdao.readAll();
    }
	
	public List<Project> FilterByType(String type) {
	    return projetdao.searchByType(type);
	}
	
	public void Cloturer(String projetnom) {
		projetdao.cloturer(projetnom);
	}
	
	public void GestionDeleteProject(String nom) {
		projetdao.deletePrjtName(nom);
	}
	
	public List<Project> GestionSearchProjectBykeywrd(String keyword) {
		return projetdao.filterByKeywordInDescription(keyword);
	}
	
	public void cloneProject(Project projet) {
		projetdao.CloneProject(projet);
	}
	
	public void updateProject(Project projet,String nom, String type ,String categorie, String dateD ,String dateF,String desc) {
		projetdao.updateProjet1( projet, nom,  type , categorie,  dateD , dateF, desc);
	}
	
	public List<Tache> getTacheDeProjet(String projectName) {
		return projetdao.getTasksByProjectName(projectName);	
	}
	
	public void DeleteTacheProjet(String projectName, String taskIntitule) {
		projetdao.DeleteTaskFromProject(projectName, taskIntitule);
	}
	
	public void insertTacheProjet(Project project, Tache task) {
		projetdao.insertTaskToProject(project,task);
	}
	
	public void addFiletoTache(Project projet, Tache task, Fichiers file) {
		projetdao.InsertFile_To_Task_Project( projet,  task,  file);
	}
	
	public void addFileToProject(Project projet , Fichiers file) {
		projetdao.insertFileToProjet(projet,file);
	}
	
	public List<Fichiers> getFileDeProjet(String projet){
		return projetdao.getFileByProjectName(projet);
	}
	
	public void DeleteFileFromProjet(Project projet, String filePath) {
		projetdao.deleteFileFromProjet(projet,filePath);
	}
	
	public List<Fichiers> getFileOfTasks_of_projet(String projectName, String taskIntitule) {
		return projetdao.getFilesOfTaskInProject( projectName,  taskIntitule);
	}
	
	public void AjouterTacheAuPRojet(Project project, Tache task) {
		projetdao.insertTaskToProject(project, task);
	}
	
	public void UpdateTaskOfProject(Project project, Tache task, String newIntitule, String newDescTache, String newDateDebutTache, String newDateFinTache) {
		projetdao.updateTaskInProject(project,task,newIntitule, newDescTache, newDateDebutTache, newDateFinTache);
	}
	
	public void ImportDocToTache(Project projet, Tache task, Fichiers file) {
		projetdao.InsertFile_To_Task_Project( projet,  task,  file);
	}
	
	public List<Seance> readSeances(String Projectname) {
        return (projetdao.getSeancesByProjectName(Projectname));
    }
	
	public void SupprimerSeanceFromProject(String projectName, int Seancenum) {
		projetdao.DeleteSeanceFromProject( projectName,  Seancenum);
	}
	
	public void insertSeance(Project p,Seance sc) {
        projetdao.insertSeanceToProject(p, sc);
    }
	
}