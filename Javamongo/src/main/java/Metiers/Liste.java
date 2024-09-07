package Metiers;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import TestController.ControllerListe;

public class Liste {
	

	String nomListe ; // A revoir //
	String descListe;
	 private List<Tache> taches;
	 
	 
	public Liste( String nomListe, String descListe) {
		
		
		this.nomListe = nomListe;
		this.descListe = descListe;
		this.taches=new ArrayList<Tache>() ;
	}
	
	
	
	public Liste() {
		this.nomListe = nomListe;
		this.descListe = descListe;
		this.taches=taches ;
	}
	



	

	public String getNomListe() {
		return nomListe;
	}
	public void setNomListe(String nomListe) {
		this.nomListe = nomListe;
	}
	public String getDescListe() {
		return descListe;
	}
	public void setDescListe(String descListe) {
		this.descListe = descListe;
	}
	
	public List<Tache> getTaches() {
          
            return taches;
        
    }
	
	@Override
	public String toString() {
		return "Liste [nomListe=" + nomListe + ", descListe=" + descListe + "]";
	}
	
	public void  afficher() {
		System.out.println(toString());
	}



	public void setTaches(List<Tache> tasks) {
		this.taches=tasks;
		
	}



	
}