package Metiers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Project {

	private String nomProjet;
	private String categoryPrjt;
	private String typePrjt;
	private String  descPrjt;
	private String dateDebutprjt;
	private String  dateFinprjt;
	private List<Fichiers> files = new ArrayList<Fichiers>();
	private List<Tache> taches = new ArrayList<Tache>();
	private List<Seance> seances = new ArrayList<Seance>();
	
	        
	public Project(String nomProjet, String categoryPrjt, String typePrjt, String descPrjt, String dateDebutprjt,
			String dateFinprjt, List<Tache> taches, List<Seance> seances,List<Fichiers> files) {
		super();
		this.nomProjet = nomProjet;
		this.categoryPrjt = categoryPrjt;
		this.typePrjt = typePrjt;
		this.descPrjt = descPrjt;
		this.dateDebutprjt = dateDebutprjt;
		this.dateFinprjt = dateFinprjt;
		this.files = files;
		this.taches = taches;
		this.seances = seances;
	}
	
	public Project(String nomProjet, String categoryPrjt, String typePrjt, String descPrjt,
			String dateDebutprjt, String dateFinprjt) {
		this.nomProjet = nomProjet;
		this.categoryPrjt = categoryPrjt;
		this.typePrjt = typePrjt;
		this.descPrjt = descPrjt;
		this.dateDebutprjt = dateDebutprjt;
		this.dateFinprjt = dateFinprjt;
		this.taches=taches;
		this.seances=seances;
		this.files=files;
	}
	
	public Project() {
		
		
		
	}
	
	public void addFile(Fichiers fichier) {
        this.files.add(fichier);
    }
	
	public List<Fichiers> getFiles() {
		return files;
	}


	public void setFiles(List<Fichiers> files) {
		this.files = files;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}


	public List<Tache> getTaches() {
		return taches;
	}


	public void setTaches(ArrayList<Tache> taches) {
		this.taches = taches;
	}
	
	public void addTache(Tache tache) {

        this.taches.add(tache);
    }
	
	public void addSeance(Seance seance) {
        this.seances.add(seance);
    }


	public List<Seance> getSeances() {
		return seances;
	}


	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}


	public String getNomProjet() {
		return nomProjet;
	}
	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}
	public String getCategoryPrjt() {
		return categoryPrjt;
	}
	public void setCategoryPrjt(String categoryPrjt) {
		this.categoryPrjt = categoryPrjt;
	}
	public String getTypePrjt() {
		return typePrjt;
	}
	public void setTypePrjt(String typePrjt) {
		this.typePrjt = typePrjt;
	}
	public String getDescPrjt() {
		return descPrjt;
	}
	public void setDescPrjt(String descPrjt) {
		this.descPrjt = descPrjt;
	}
	public String getDateDebutprjt() {
		return dateDebutprjt;
	}
	public void setDateDebutprjt(String dateDebutprjt) {
		this.dateDebutprjt = dateDebutprjt;
	}
	public String getDateFinprjt() {
		return dateFinprjt;
	}
	public void setDateFinprjt(String dateFinprjt) {
		this.dateFinprjt = dateFinprjt;
	}
	
	

	
	
	@Override
	public String toString() {
		return "Project ["  + ", nomProjet=" + nomProjet + ", categoryPrjt=" + categoryPrjt
				+ ", typePrjt=" + typePrjt + ", descPrjt=" + descPrjt + ", dateDebutprjt=" + dateDebutprjt
				+ ", dateFinprjt=" + dateFinprjt + "]";
	}
	
	public void afficher() {
		System.out.println(toString());
	}
	

	
}
