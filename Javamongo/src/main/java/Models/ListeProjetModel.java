package Models;
import Metiers.*;
import java.util.ArrayList;
import java.util.List;

public class ListeProjetModel {
	private Project projet;
	private List<Project> projects;
    private  String recherche;
    private Fichiers files;
    
    public ListeProjetModel() {
    	
    }
    
	public ListeProjetModel(List<Project> projects, String recherche, Project projet,Fichiers files) {
		super();
		this.projet= new Project();
		this.projects = projects;
		this.recherche = recherche;
		this.files=new Fichiers();
	}
	
	public Project getProjet() {
		return projet;
	}
	
	public Fichiers getFiles() {
		return files;
	}

	public void setFiles(Fichiers files) {
		this.files = files;
	}

	public void setProjet(Project projet) {
		this.projet = projet;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public String getRecherche() {
		return recherche;
	}
	
	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	
	
}
