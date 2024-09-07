package Models;

import java.util.ArrayList;

import Metiers.Liste;

public class AffichListModel {
	Liste list;
	ArrayList<Liste> listeTaches;
	String descript;
  
	public AffichListModel(Liste list, ArrayList<Liste> listeTaches, String descript) {
	super();
	this.list = new Liste();
	this.listeTaches = listeTaches;
	this.descript = descript;
}

	public AffichListModel() {
		
	}



	
	public ArrayList<Liste> getListeTaches() {
		return listeTaches;
	}
	public void setListeTaches(ArrayList<Liste> listeTaches) {
		this.listeTaches = listeTaches;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	 public void ajouterListe(Liste liste) {
	        listeTaches.add(liste);
	    }
	
	
	
}