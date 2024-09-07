package Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Gestion.*;
import Metiers.Liste;
import presentation.*;

public class ControlListe {
GestionListe gestion ;
ListTache l;

public ControlListe() {
	
	this.l=new ListTache();
	this.gestion=new GestionListe();
	
	l.getSearchButton().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            search();
        }
    });
	
}

public ArrayList<Liste> search() {
	String key=l.getSearchField();
	return gestion.recherche(key);
}

public GestionListe getGestion() {
	return gestion;
}

public void setGestion(GestionListe gestion) {
	this.gestion = gestion;
}

public ListTache getL() {
	return l;
}

public void setL(ListTache l) {
	this.l = l;
}
	public static void main(String[] args) {
		 new ControlListe();
	}
	
}