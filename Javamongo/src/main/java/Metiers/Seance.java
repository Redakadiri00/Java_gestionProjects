package Metiers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class Seance {
    private int numSeance;
    private String descriptionSeance;
    private String jourDebutSeance; // Nouvel attribut pour le jour de début de la séance
    private String heureDebutSeance;
    private String heureFinSeance;
    private String note;
    private List<Fichiers> docs;
    
    public  Seance() {
    	
    }

    public Seance(int numSeance, String descriptionSeance, String  dateDebutSeance, String heureDebutSeance, String heureFinSeance, String note,List<Fichiers> files) {
        this.numSeance = numSeance;
        this.descriptionSeance = descriptionSeance;
        this.jourDebutSeance = dateDebutSeance;
        this.heureDebutSeance = heureDebutSeance;
        this.heureFinSeance = heureFinSeance;
        this.note = note;
        this.docs = new ArrayList<>();
    }


	public int getNumSeance() {
		return numSeance;
	}

	public void setNumSeance(int numSeance) {
		this.numSeance = numSeance;
	}

	public String getDescriptionSeance() {
		return descriptionSeance;
	}




	public void setDescriptionSeance(String descriptionSeance) {
		this.descriptionSeance = descriptionSeance;
	}




	public String getJourDebutSeance() {
		return jourDebutSeance;
	}




	public void setJourDebutSeance(String jourDebutSeance) {
		this.jourDebutSeance = jourDebutSeance;
	}




	public String getHeureDebutSeance() {
		return heureDebutSeance;
	}




	public void setHeureDebutSeance(String heureDebutSeance) {
		this.heureDebutSeance = heureDebutSeance;
	}




	public String getHeureFinSeance() {
		return heureFinSeance;
	}




	public void setHeureFinSeance(String heureFinSeance) {
		this.heureFinSeance = heureFinSeance;
	}




	public String getNote() {
		return note;
	}




	public void setNote(String note) {
		this.note = note;
	}




	public List<Fichiers> getDocs() {
		return docs;
	}




	public void setDocs(List<Fichiers> docs) {
		this.docs = docs;
	}




	public void afficher() {
        System.out.println(toString());
    }
  
    	
	}