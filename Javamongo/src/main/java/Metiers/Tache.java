package Metiers;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Tache {



	private String intitule;
	private String categTache;
	private String descTache;
	private String dateDebutTache;
	private String dateFinTache ;
	private List<Fichiers> files = new ArrayList<Fichiers>();
	
	
	public Tache() {
		
	}
	
	public Tache(String intitule, String categTache, String descTache, String dateDebutTache, String dateFinTache
            ) {
        super();
        this.intitule = intitule;
        this.categTache = categTache;
        this.descTache = descTache;
        this.dateDebutTache = dateDebutTache;
        this.dateFinTache = dateFinTache;
        this.files = new ArrayList<Fichiers>();
    }

	public Tache( String intitule, String categTache, String descTache, String dateDebutTache, String dateFinTache,List<Fichiers> files) {
		this.intitule = intitule;
		this.categTache = categTache;
		this.descTache = descTache;
		this.dateDebutTache = dateDebutTache;
		this.dateFinTache = dateFinTache;
		this.files = new ArrayList<>();
	}
	

	public List<Fichiers> getFiles() {
		return files;
	}

	public void setFiles(List<Fichiers> files) {
		this.files = files;
	}
	
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public String getCategTache() {
		return categTache;
	}
	public void setCategTache(String categTache) {
		this.categTache = categTache;
	}
	public String getDescTache() {
		return descTache;
	}
	public void setDescTache(String descTache) {
		this.descTache = descTache;
	}
	public String getDateDebutTache() {
		return dateDebutTache;
	}
	public void setDateDebutTache(String dateDebutTache) {
		this.dateDebutTache = dateDebutTache;
	}
	public String getDateFinTache() {
		return dateFinTache;
	}
	public void setDateFinTache(String dateFinTache) {
		this.dateFinTache = dateFinTache;
	}
	
	@Override
	public String toString() {
		return "Tache [" + "intitule=" + intitule + ", categTache=" + categTache + ", descTache=" + descTache
				+ ", dateDebutTache=" + dateDebutTache + ", dateFinTache=" + dateFinTache + "]";
	}
	
	public void afficher() {
		System.out.println(toString());
	}

	
}
