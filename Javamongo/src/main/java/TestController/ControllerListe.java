package TestController;


import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import Gestion.GestionListe;
import Metiers.Fichiers;
import Metiers.Liste;
import Metiers.Project;
import Metiers.Tache;
import ViewTest.*;

import Models.AffichListModel;

public class ControllerListe {

    private CreatListe form;
    private ListTache list;
    private Liste l;
    private GestionListe gestion;
    private AffichListModel al;
    private ArrayList<Liste> arr;
    private DetailListe d;
    private VoirTacheList voir;
    private  Tache t;
    private AjoutTask ajout;
    private ModifierTacheView modif;
    private ModifyList modifL ; 
    private String selectedList;

ProjetControl proj;
	public ModifierTacheView getModif() {
		return modif;
	}

	public void setModif(ModifierTacheView modif) {
		this.modif = modif;
	}

	public ModifyList getModifL() {
		return modifL;
	}

	public void setModifL(ModifyList modifL) {
		this.modifL = modifL;
	}

    public ControllerListe() {
        super();
        // Initialise la liste avec une référence à ce contrôleur
       this.list=new ListTache(this);
       this.gestion=new GestionListe();
       this.al=new AffichListModel();
       this.voir=new VoirTacheList(this);
   //  this.d=new DetailListe(this,selectedList);
       this.d=new DetailListe(this);
       this.ajout=new AjoutTask(this,selectedList);
      this.proj=proj;
   
    }

    public void AllerVersListTache(ControllerListe contr){
    	new ListTache(this);
    }
    
    public CreatListe getForm() {
        return form;
    }
    
    public Liste getListByName(String ListName) {
        ArrayList<Liste> listes = this.getAl().getListeTaches(); 
        
        if (listes != null) {
            for (Liste list : listes) {
                if (list.getNomListe().equals(ListName)) {
                    return list;
                }
            }
        } else {
            System.out.print("Liste not found: listes is null");
        }
        
        return null; 
    }
    
    public Liste getListByName1(String ListName) {
        return  gestion.listByName(ListName);
    }
    
   public void alleListAccueil() {
	   new ListTache(this).setVisible(true);
   }
		   
    
   
    public void setForm(CreatListe form) {
        this.form = form;
    }

    public ListTache getList() {
        return list;
    }
    
    

    public void setList(ListTache list) {
        this.list = list;
    }

    

    public Liste getL() {
        return l;
    }

    public void setL(Liste l) {
        this.l = l;
    }
    
    
    public void deleteListe(String nom) {
    	
    		gestion.deleteListe(nom);	
    }
    
    public void deleteTaskListe(String nomList,String nomTask) {
    	try {
            if (nomList == null || nomList.isEmpty()) {
                throw new IllegalArgumentException("Le nom de la liste ne peut pas être null ou vide.");
            }
            
            if (nomTask == null || nomTask.isEmpty()) {
                throw new IllegalArgumentException("Le nom de la tâche ne peut pas être null ou vide.");
            }
            
            gestion.supprimerFromListe(nomList, nomTask);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
    public void openCreateListe() {
        CreatListe form = new CreatListe(this);
        form.setVisible(true);
    }
    
    public void openAjouTask(String selected) {
        AjoutTask remplir = new AjoutTask(this,selected);
        remplir.setVisible(true);
    }
    
    
    public void openModifyList() {
		ModifyList up=new ModifyList(this);
		up.setVisible(true);
	}

    public void creerListe(Liste liste) {
    	try {
            
            if (liste.getNomListe() == null || liste.getNomListe().isEmpty()) {
                throw new IllegalArgumentException("Le nom de la liste ne peut pas être null ou vide.");
            }
            
            if (liste.getDescListe() == null || liste.getDescListe().isEmpty()) {
                throw new IllegalArgumentException("La description de la liste ne peut pas être null ou vide.");
            }
          
            GestionListe gestion = new GestionListe();
            this.setGestion(gestion);
            gestion.creerListe(liste);
            JOptionPane.showMessageDialog(null,"Liste ajoutée avec succès.");
        } catch (IllegalArgumentException e) {
          
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void insererTask(Liste l,Tache tache) {
    	 try {
    	       
    	        if (tache.getIntitule() == null || tache.getIntitule().isEmpty()) {
    	            throw new IllegalArgumentException("L'intitulé de la tâche ne peut pas être vide.");
    	        }
    	        
    	        if (tache.getCategTache() == null || tache.getCategTache().isEmpty()) {
    	            throw new IllegalArgumentException("La catégorie de la tâche ne peut pas être vide.");
    	        }
    	        
    	        if (tache.getDescTache() == null || tache.getDescTache().isEmpty()) {
    	            throw new IllegalArgumentException("La description de la tâche ne peut pas être vide.");
    	        }
    	        
    	        if (tache.getDateDebutTache() == null || tache.getDateDebutTache().isEmpty()) {
    	            throw new IllegalArgumentException("La date de début de la tâche ne peut pas être vide.");
    	        }
    	        
    	        if (tache.getDateFinTache() == null || tache.getDateFinTache().isEmpty()) {
    	            throw new IllegalArgumentException("La date de fin de la tâche ne peut pas être vide.");
    	        }
    	        
    	        if (!isValidDateFormat(tache.getDateDebutTache())) {
    	            throw new IllegalArgumentException("Le format de la date de début de la tâche est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
    	        }
    	        
    	        if (!isValidDateFormat(tache.getDateFinTache())) {
    	            throw new IllegalArgumentException("Le format de la date de fin de la tâche est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
    	        }
    	        
    	        GestionListe gestion = new GestionListe();
    	        this.setGestion(gestion);
    	        gestion.addTask(l, tache);
    	        
    	        JOptionPane.showMessageDialog(null, "La tâche a été ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
    	    } catch (IllegalArgumentException e) {
    	        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
    	    }
    }
    
    private boolean isValidDateFormat(String date) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        dateFormat.setLenient(false);
	        dateFormat.parse(date);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
    
    public void insererTask1(String nom ,Tache t) {
   	    GestionListe gestion = new GestionListe();
        this.setGestion(gestion);
        gestion.addTask1(nom, t);
    }
    
    
    public void search1(String keyword) {
        GestionListe gestion = new GestionListe();
        this.setGestion(gestion);
        this.setList(list);
        //String keyword = list.getSearchField().getText().trim();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (!keyword.isEmpty()) {
            List<Liste> resultList = gestion.recherche(keyword);
            if (resultList != null) {
                for (Liste liste : resultList) {
                    listModel.addElement(liste.getNomListe());
                }
                list.getTaskList().setModel(listModel);
            } else {
                showMessage("Aucun résultat trouvé.");
            }
        } else {
            System.out.println("Veuillez saisir un mot-clé pour la recherche.");
        }
    }

    public void search(String keyword) {
        GestionListe gestion = new GestionListe();
        this.setGestion(gestion);
        this.setList(list);
        //String keyword = list.getSearchField().getText().trim();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (!keyword.isEmpty()) {
            List<Liste> resultList = gestion.recherche(keyword);
            if (resultList != null && !resultList.isEmpty()) {
                for (Liste liste : resultList) {
                    listModel.addElement(liste.getNomListe());
                }
                list.getTaskList().setModel(listModel);
            } else {
            	JOptionPane.showMessageDialog(null,"Aucun résultat trouvé pour le mot-clé \"" + keyword + "\".");
                throw new IllegalArgumentException("Aucun résultat trouvé pour le mot-clé \"" + keyword + "\".");
            }
        } else {
        	JOptionPane.showMessageDialog(null,"Veuillez saisir un mot-clé pour la recherche.");
        }
    }
    
    public String namTask() {
        String nomTa = this.getVoir().getTitleLabel().getText();
        String nomTache = null;  // Déclarez nomTache en dehors du bloc if

        int separatorIndex = nomTa.indexOf(":");

        if (separatorIndex != -1) {
            nomTache = nomTa.substring(separatorIndex + 1).trim();
        }
        
        return nomTache;
    }
    
    public String namTask1() { 
    	return this.getD().getSelectedTaskName();
    }
    
    
    
    public void handleMouseClick(int selectedIndex) {
        
        String searchText = list.getSearchField().getText().trim();
        
   
        ArrayList<Liste> allLists = gestion.getAll();
        
    
        ArrayList<Liste> filteredLists = new ArrayList<>();
        
        if (!searchText.isEmpty()) {
            for (Liste liste : allLists) {
                if (liste.getDescListe().contains(searchText)) {
                    filteredLists.add(liste);
                }
            }
        } else {
            filteredLists.addAll(allLists);
        }
        
       
        if (selectedIndex != -1 && !filteredLists.isEmpty()) {
          
            Liste selectedListe = filteredLists.get(selectedIndex);
          
            this.openListe(selectedListe);
        }
    }
    
  

    
   
   
   
    public void handleMouseClickTache(String selectedTaskName) {
        if (selectedTaskName != null && !selectedTaskName.isEmpty()) {
            
            Liste selectedListe = gestion.readListeByTask(selectedTaskName); 

		ArrayList<Tache> tasks = (ArrayList<Tache>) gestion.readTasksListe(selectedListe.getNomListe());

        Tache selectedTask = null;
        for (Tache task : tasks) {
            if (task.getIntitule().equals(selectedTaskName)) {
                selectedTask = task;
                break;
            }
        }

        if (selectedTask != null) {
           
            VoirTacheList affichageTache = new VoirTacheList(this);
            
            affichageTache.updateTaskDetails(selectedTask);
            
            affichageTache.setVisible(true);
        }
        }  
    }

    
   
    

  
    
    public void update(Liste l,String nom,String desc) {
    	gestion.modifierList(l, nom, desc);
    }
    
    

    
    public void afficherAll() {
    	
    	GestionListe gestion=new GestionListe();
    	this.setGestion(gestion);
    	this.setList(list);
        ArrayList<Liste> listes = gestion.getAll();
        
        if (listes != null && !listes.isEmpty()) {
           
            DefaultListModel<String> listeListModel = new DefaultListModel<>();
           
            for (Liste liste : listes) {
                listeListModel.addElement(liste.getNomListe());
            }
            
            
            list.getTaskList().setModel(listeListModel);
        } else {
            
            showMessage("Aucune liste trouvée dans la base de données.");
        }
        
    }

    
    
    
    
    
    
    public void openListe(Liste selectedListe) {
        DetailListe d = new DetailListe(this);
        d.setVisible(true);
        
       
       if (selectedListe != null) {
           d.updateDetails(selectedListe);
       } else {
           System.out.println("Liste sélectionnée invalide.");
       }
   }
    
    public List<Tache> updateTasks(Liste selectedListe) {
    	return  gestion.readTasksListe(selectedListe.getNomListe());
    }
    
    
    public void importer() {
		//gestion.addTask(l, null);
	}
    
    public void ContorlReadAllLists() {
       al.setListeTaches(gestion.getAll());

       DefaultListModel<String> myListModel = new DefaultListModel<>();
       for (Liste liste : al.getListeTaches()) {
    	   myListModel.addElement(liste.getNomListe()); 
       }
       list.getTaskList().setModel(myListModel);
    }
    
    
    
    private void showMessage(String string) {
        
    }

    public GestionListe getGestion() {
        return gestion;
    }

    public void setGestion(GestionListe gestion) {
        this.gestion = gestion;
    }

    public AffichListModel getAl() {
        return al;
    }

    public void setAl(AffichListModel al) {
        this.al = al;
    }
    

    
    public Tache getTacheByNom(String nom) {
		return gestion.getTacheByNom(nom);
	}

   public void updateTaskList(String nomL,String nomT,String nvNom,String nvdes,String nvCat,String nvDaD,String nvDatF) {
	   gestion.modifyTaskList(nomL, nomT, nvNom, nvdes, nvCat, nvDaD, nvDatF);
}
   
    public DetailListe getD() {
		return d;
	}

	public void setD(DetailListe d) {
		this.d = d;
	}
	
	public VoirTacheList getVoir() {
		return voir;
	}

	public void setVoir(VoirTacheList voir) {
		this.voir = voir;
	}

	public static void main(String[] args) {
      ControllerListe c= new ControllerListe();   
    }
	
	public void RetourLisTache() {
        this.getList().pack();
        this.list = new ListTache(this);

    }
	
	public void retourDetaiList() {
		this.d=new DetailListe(this);
	}

	
}