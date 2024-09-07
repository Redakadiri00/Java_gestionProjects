package TestController;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Gestion.GestionProjet;
import Gestion.GestionTask;
import Metiers.Fichiers;
import Metiers.Project;
import Metiers.Seance;
import Metiers.Tache;
import Models.ListeProjetModel;
import ViewTest.AffichageProjetTest;
import ViewTest.AffichageTaskView;
import ViewTest.AjoutProjetTest;
import ViewTest.DetailSeanceView;
import ViewTest.FrameprojetTest;
import ViewTest.ModifierProjet;
import ViewTest.SeanceDeProjetView;
import ViewTest.TacheDeProjetView;
public class ProjetControl {
	GestionProjet gestionprojet;
	GestionTask gestiontask;
	FrameprojetTest fp;
	AjoutProjetTest ap;
	AffichageProjetTest affprjt;
	ListeProjetModel lpm;
	ModifierProjet mp;
	TacheDeProjetView tdpv;
	AffichageTaskView atv;
	Project selectedProjec;
	Tache task;
	Seance seance;
	DetailSeanceView dsv;
	SeanceDeProjetView sdpv;
	 
	
	
	
	
	public TacheDeProjetView getTdpv() {
		return tdpv;
	}

	public void setTdpv(TacheDeProjetView tdpv) {
		this.tdpv = tdpv;
	}

	
	
	
	public FrameprojetTest getFp() {
		return fp;
	}

	public void setFp(FrameprojetTest fp) {
		this.fp = fp;
	}
	
	
	public ProjetControl() {
		super();
		this.gestionprojet= new GestionProjet();
		this.lpm=new ListeProjetModel();
		this.fp= new FrameprojetTest(this);
		this.selectedProjec= new Project();
		this.gestiontask=new GestionTask();
		this.atv= new AffichageTaskView();
		this.task = new Tache();
		this.seance=new Seance();
		this.tdpv= new TacheDeProjetView(selectedProjec,this);
		this.dsv= new DetailSeanceView(selectedProjec,seance,this);
	}
	
	public void AfficherFormulaire() {
		this.ap = new AjoutProjetTest(this);
		this.ap.pack();
		this.ap.dispose();
	}
	
	public void RetourFrameProjet() {
		this.fp.pack();
		this.fp = new FrameprojetTest(this);
		
	}
	
	public void AfficherModifierForm() {
		this.mp = new ModifierProjet(this);
		this.mp.pack();
		this.mp.dispose();
	}
	
	public void RetourProjetdetail(Project selectedProjec) {
		this.affprjt.pack();
		//this.affprjt = new AffichageProjetTest(selectedProjec,this);
		this.displayProjectDetails(selectedProjec);
	}
	
	public void AllerVersTache(Project selectedProjec , ProjetControl pc) {
		new TacheDeProjetView(selectedProjec,this);
		this.tdpv.setVisible(true);
	}
	
	public void AllerVersFrameProjet() {
		new FrameprojetTest(this);
		this.fp.setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void SaveProject(Project p) {
	    try {
	        if (p.getNomProjet() == null || p.getNomProjet().isEmpty()) {
	            throw new IllegalArgumentException("Le nom du projet ne peut pas être vide.");
	        }
	        if (p.getDateDebutprjt() == null || p.getDateDebutprjt().isEmpty()) {
	            throw new IllegalArgumentException("La date de début du projet ne peut pas être vide.");
	        }
	        if (p.getDateFinprjt() == null || p.getDateFinprjt().isEmpty()) {
	            throw new IllegalArgumentException("La date de fin du projet ne peut pas être vide.");
	        }
	    
	        if (!isValidDateFormat(p.getDateDebutprjt())) {
	            throw new IllegalArgumentException("Le format de la date de début du projet est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
	        }
	      
	        if (!isValidDateFormat(p.getDateFinprjt())) {
	            throw new IllegalArgumentException("Le format de la date de fin du projet est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
	        }
	        
	        gestionprojet.gestionCreateProjet(p);
	        
	        JOptionPane.showMessageDialog(null, "Projet Bien Ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
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

	
	
	public void filterAction(String type) {
		
		
    	lpm.setProjects(gestionprojet.FilterByType(type));
        // Filter the projects based on the selected category
        DefaultListModel<String> filteredModel = new DefaultListModel<>();
        for (Project project : lpm.getProjects()) {
            if (project.getTypePrjt().equals(type)) {
                filteredModel.addElement(project.getNomProjet());
            }
        }
        fp.getProjectList().setModel(filteredModel); // Update the JList with filtered model
    }
	
	public void ContorlReadAllProjetcts() {
   	
       lpm.setProjects(gestionprojet.GestionReadAllProjects());
       
       DefaultListModel<String> projectListModel = new DefaultListModel<>();
       for (Project project : lpm.getProjects()) {
           projectListModel.addElement(project.getNomProjet()); // Assuming 'getName()' method returns project name
       }
       fp.getProjectList().setModel(projectListModel);
   }
	
	
	
	public void DeleteProjet(String nom) {
		gestionprojet.GestionDeleteProject(nom);
	}
	
	public List<Project> SearchProjectByKeyword(String keyword) {
	    List<Project> projects = gestionprojet.GestionSearchProjectBykeywrd(keyword);

	    if (projects.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Aucun projet trouvé pour le mot-clé : " + keyword, "Résultat de la recherche", JOptionPane.INFORMATION_MESSAGE);
	    }

	    return projects;
	}
	
	
	
	public void rechercherP(String keyword) {
	    DefaultListModel<String> listModel = new DefaultListModel<>();
	    
	    if (!keyword.isEmpty()) {
	        // Call the DAO method to search for projects by keyword
	        List<Project> resultList = gestionprojet.GestionSearchProjectBykeywrd(keyword);
	        
	        if (resultList != null && !resultList.isEmpty()) {
	            for (Project prjt : resultList) {
	                listModel.addElement(prjt.getNomProjet());
	            }
	        } else {
	            System.out.println("Aucun résultat trouvé.");
	        }
	    } else {
	        System.out.println("Veuillez saisir un mot-clé de recherche.");
	    }
	    
	 
	    fp.getProjectList().setModel(listModel);
	}
	
	
	public Project getProjectByName(String projectName) {
		
	    List<Project> ListeDeProjets = this.lpm.getProjects(); 
	    
	    for (Project project : ListeDeProjets) {
	        if (project.getNomProjet().equals(projectName)) {
	            return project;
	        }
	    }
	    System.out.print("Project  not found");
	    return null; 
	}
	
	public Tache getTaskByName(String taskName) {
		
	    List<Tache> ListeDesTache = this.selectedProjec.getTaches();   // erreur null //
	     
	    for (Tache tache : ListeDesTache) {
	        if (tache.getIntitule().equals(taskName)) {
	            return tache;
	        }
	    }
	    System.out.print("task  not found");
	    return null; 
	}
	
	public void displayProjectDetails(Project project) {
		this.affprjt=new AffichageProjetTest(project,this);
        this.affprjt.setVisible(true);
        affprjt.getTitre().setText(project.getNomProjet());
        affprjt.getDateDebut().setText("Date de début: " + project.getDateDebutprjt());
        affprjt.getDateFin().setText("Date de fin: " + project.getDateFinprjt());
        affprjt.getDecrire().setText(project.getDescPrjt());

     
    }
	
	public void displayTacheDetails(Tache SelectedTask) {
		this.atv=new AffichageTaskView(selectedProjec,SelectedTask,this);
		
        // Assuming you have getters in your Project class for retrieving project details
		atv.getTitre().setText(SelectedTask.getIntitule());
		atv.getDateDebut().setText("Date de début: " + SelectedTask.getDateDebutTache());
		atv.getDateFin().setText("Date de fin: " + SelectedTask.getDateFinTache());
		atv.getDecrire().setText(SelectedTask.getDescTache());
	}
	
	public void displaySeanceDetails(Seance selectedseance) {
		this.dsv=new DetailSeanceView(selectedProjec,selectedseance,this);
		
        // Assuming you have getters in your Project class for retrieving project details
		dsv.getTitre().setText("seance: " + String.valueOf(selectedseance.getNumSeance()));
		dsv.getHeureDebut().setText("heure de début: " + selectedseance.getHeureDebutSeance());
		dsv.getHeureFin().setText("heure de fin: " + selectedseance.getHeureFinSeance());
		dsv.getDecrire().setText(selectedseance.getDescriptionSeance());
	}
	
	
//	public void Cloner() {
//		// Gather data of the current project being displayed
//	    String projectName = titre.getText();
//	    String startDate = dateDebut.getText();
//	    String endDate = dateFin.getText();
//	    String description = decrire.getText();
//	    
//	    // Create a Project object with the gathered data
//	    Project projectToClone = new Project(projectName, startDate, endDate, description);
//	    
//	    // Call the cloner method from your ProjetControl class
//	    pc.cloner(projectToClone);
//	}
	
	
	public void ModifierProjet(Project project, String nom, String type, String categorie, String dateD, String dateF, String desc) {
        String selectedProjectName = fp.getProjectList().getSelectedValue();
        
        if (selectedProjectName != null) {
            Project selectedProject = this.getProjectByName(selectedProjectName);
            
            gestionprojet.updateProject(selectedProject, nom, type, categorie, dateD, dateF, desc);
        } 
    }
	
	public void modifyProject(Project project, String nom, String type, String categorie, String dateD, String dateF, String desc) {
	    gestionprojet.updateProject(project, nom, type, categorie, dateD, dateF, desc);

	  
	    project.setNomProjet(nom);
	    project.setTypePrjt(type);
	    project.setCategoryPrjt(categorie);
	    project.setDateDebutprjt(dateD);
	    project.setDateFinprjt(dateF);
	    project.setDescPrjt(desc);
  
	}
	
	public void modifytask(Project project,Tache task, String nom, String dateD, String dateF, String desc) {
	
	    gestionprojet.UpdateTaskOfProject(project,task, nom, dateD, dateF, desc);

	    task.setIntitule(nom);
	    task.setDateDebutTache(dateD);
	    task.setDateFinTache(dateF);
	    task.setDescTache(desc);

	    // Optionally, refresh the view to display the updated project information
	    // For example:
	    //mp.refreshProjectDetails(project);
	}
	
	public List<Tache> DisplayTask_By_ProjetName(String projet) {
		return gestionprojet.getTacheDeProjet(projet);
	}
	
	public void DeleteTacheDeProjet(String projectName, String taskIntitule) {
		gestionprojet.DeleteTacheProjet(projectName,taskIntitule);
	}
	
	public void DeleteSeanceDeProjet(String projectName, int Seancenum) {
		gestionprojet.SupprimerSeanceFromProject( projectName, Seancenum);
	}
	
	public ListeProjetModel getLpm() {
		return lpm;
	}
	
	public void setLpm(ListeProjetModel lpm) {
		this.lpm = lpm;
	}
	
	public void cloturerProject(String projectName) {
        gestionprojet.Cloturer(projectName); // Call the method to mark the project as closed
        affprjt.getTitre().setText(projectName + " (cloture)"); // Update the project name with "(cloture)"
    }
	
	
	public void storeFileInProject(File file) {
	    // Get the selected project
	    String selectedProjectName = this.getFp().getProjectList().getSelectedValue();
	    if (selectedProjectName != null) {
	        Project selectedProject = this.getProjectByName(selectedProjectName);
	        
	        Fichiers fichiers = new Fichiers();
	        //fichiers.setNomFichier(file.getName()); 
	        fichiers.setPath(file.getAbsolutePath()); 
	        
	        gestionprojet.addFileToProject(selectedProject, fichiers);
	    } else {
	        // Handle case where no project is selected
	        // For example, display a message to the user
	        //JOptionPane.showMessageDialog(this, "Veuillez sélectionner un projet.", "Avertissement", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	public void importDocumentsToProject() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this.getAffprjt());
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            // Iterate through each selected file
            for (File file : selectedFiles) {
                // Store the file in MongoDB along with its path
                this.storeFileInProject(file);
            }
//            List<Fichiers> updatedFiles = this.DisplayFile_Of_Projet(selectedProjec.getNomProjet());
//            // Clear the existing list model
//            DefaultListModel<File> fileListModel = (DefaultListModel<File>) affprjt.getFileList().getModel();
//            fileListModel.clear();
//            // Add the newly imported files to the list model
//            for (Fichiers fichier : updatedFiles) {
//                fileListModel.addElement(new File(fichier.getPath()));
//            }
        }
    }
	
	public void addDocToFile_Of_Project(Project projet, Tache task, Fichiers file) {
		gestionprojet.ImportDocToTache(projet,task,  file);
	}
	
	public List<Fichiers> DisplayFile_Of_Projet(String projet){
		return gestionprojet.getFileDeProjet(projet);
	}
	
	public List<Fichiers> DiplayFile_Of_tasksInProjet(String projectName, String taskIntitule) {
		return gestionprojet.getFileOfTasks_of_projet( projectName, taskIntitule);
	}
	
	public void SupprimerFileFromProjet(Project projet, String filePath) {
		gestionprojet.DeleteFileFromProjet(projet,filePath);
	}
	
	public void AjouterTacheProjet(Project projet, Tache task) {
	    try {
	    
	        if (projet == null) {
	            throw new IllegalArgumentException("Le projet ne peut pas être null.");
	        }

	        if (task == null) {
	            throw new IllegalArgumentException("La tâche ne peut pas être null.");
	        }

	        if (task.getIntitule() == null || task.getIntitule().isEmpty()) {
	            throw new IllegalArgumentException("Le nom de la tâche ne peut pas être vide.");
	        }

	        if (task.getDateDebutTache() == null || task.getDateDebutTache().isEmpty()) {
	            throw new IllegalArgumentException("La date de début de la tâche ne peut pas être vide.");
	        }

	        if (!isValidDateFormat(task.getDateDebutTache())) {
	            throw new IllegalArgumentException("Le format de la date de début de la tâche est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
	        }

	        if (task.getDateFinTache() == null || task.getDateFinTache().isEmpty()) {
	            throw new IllegalArgumentException("La date de fin de la tâche ne peut pas être vide.");
	        }

	        if (!isValidDateFormat(task.getDateFinTache())) {
	            throw new IllegalArgumentException("Le format de la date de fin de la tâche est incorrect. Veuillez utiliser le format jj/mm/yyyy.");
	        }

	        if (task.getDescTache() == null || task.getDescTache().isEmpty()) {
	            throw new IllegalArgumentException("La description de la tâche ne peut pas être vide.");
	        }

	        gestionprojet.AjouterTacheAuPRojet(projet, task);

	        JOptionPane.showMessageDialog(null, "La tâche a été ajoutée au projet avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IllegalArgumentException e) {
	        
	        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}

	

	
	
	

	public Tache getTaskDetailsByName(String projectName, String taskName) {
        
        List<Tache> tasks = DisplayTask_By_ProjetName(projectName);
        
        for (Tache task : tasks) {
            if (task.getIntitule().equals(taskName)) {
                return task; // Return the task if found
            }
    }
        
        return null;
    }
	
	public Seance DisplaySeanceDetail(String projectName, int seanceNum) {
        // Assuming you have a method to retrieve tasks by project name
        List<Seance> seances = afficherSeances(projectName);
        
        // Loop through the tasks to find the one with the matching name
        for (Seance seance: seances) {
            if (seance.getNumSeance()==seanceNum) {
                return seance;
            }
    }
        
      
        return null;
    }
	
	public List<Seance> afficherSeances(String  Projectname) {
        return gestionprojet.readSeances(Projectname);
    }
	
	
	
//	public void openVoirSéances(Project selectProjet) {
//		SeanceDeProjetView l=new SeanceDeProjetView(this);
//
//		        l.setVisible(true);
//
//		    }
	
	
	
	
	
	
	public AffichageProjetTest getAffprjt() {
		return affprjt;
	}
	
	public void setAffprjt(AffichageProjetTest affprjt) {
		this.affprjt = affprjt;
	}
	
	public static void main(String[] args) {
	//ProjetControl pc =new ProjetControl();
	//pcdessinFormulaire();
	}
	
	public void insérerSeance(Project p, Seance sc) {
	    try {
	        
	        if (p == null) {
	            throw new IllegalArgumentException("Le projet ne peut pas être null.");
	        }
	        if (sc == null) {
	            throw new IllegalArgumentException("La séance ne peut pas être null.");
	        }

	        if (sc.getNumSeance() <= 0) {
	            throw new IllegalArgumentException("Le numéro de séance doit être supérieur à zéro.");
	        }
	        if (sc.getDescriptionSeance() == null || sc.getDescriptionSeance().isEmpty()) {
	            throw new IllegalArgumentException("La description de la séance ne peut pas être vide.");
	        }
	        if (sc.getJourDebutSeance() == null || sc.getJourDebutSeance().isEmpty()) {
	            throw new IllegalArgumentException("Le jour de début de la séance ne peut pas être vide.");
	        }
	        if (!isValidTimeFormat(sc.getHeureDebutSeance())) {
	            throw new IllegalArgumentException("Le format de l'heure de début de la séance est incorrect. Veuillez utiliser le format hh:mm.");
	        }
	        if (!isValidTimeFormat(sc.getHeureFinSeance())) {
	            throw new IllegalArgumentException("Le format de l'heure de fin de la séance est incorrect. Veuillez utiliser le format hh:mm.");
	        }
	        if (sc.getHeureDebutSeance() == null || sc.getHeureDebutSeance().isEmpty()) {
	            throw new IllegalArgumentException("L'heure de début de la séance ne peut pas être vide.");
	        }
	        if (sc.getHeureFinSeance() == null || sc.getHeureFinSeance().isEmpty()) {
	            throw new IllegalArgumentException("L'heure de fin de la séance ne peut pas être vide.");
	        }
	        if (sc.getNote() == null || sc.getNote().isEmpty()) {
	            throw new IllegalArgumentException("La note de la séance ne peut pas être vide.");
	        }

	        gestionprojet.insertSeance(p, sc);

	        JOptionPane.showMessageDialog(null, "La séance a été ajoutée au projet avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IllegalArgumentException e) {
	    
	        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private boolean isValidTimeFormat(String time) {
	    // Regular expression for hh:mm format
	    String timeRegex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
	    return time.matches(timeRegex);
	}
}