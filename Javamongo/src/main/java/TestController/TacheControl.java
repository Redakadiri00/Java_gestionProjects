//package TestController;
//
//import java.util.List;
//
//import javax.swing.DefaultListModel;
//
//import Gestion.GestionTask;
//import Metiers.Project;
//import Metiers.Tache;
//import ViewTest.ModifierTacheView;
//import ViewTest.TacheDeProjetView;
//
//public class TacheControl {
//	
//	private TacheDeProjetView tp;
//	private ModifierTacheView mtv;
//	private GestionTask gestiontask;
//	private Project selectedProject;
//	private ProjetControl pc;
//	
//	public ProjetControl getPc() {
//		return pc;
//	}
//
//	public void setPc(ProjetControl pc) {
//		this.pc = pc;
//	}
//
//	public TacheControl() {
//		super();
//		this.tp = new TacheDeProjetView(selectedProject,this.getPc());
//		//this.mtv=new ModifierTacheView(pc);
//		this.pc=new ProjetControl();
//
//
// }
//	
//	public void AfficherTacheDeProjet() {
//		//this.tp = new TacheDeProjetView(this);
//		this.tp.pack();
//		this.tp.setVisible(true);
//	}
//	
////	public void ContorlReadAlltasks(Project projet) {
////	   	 // Call the method to retrieve all projects
////		vtm.setProjects(gestiontask.getTacheDeProjet(projet));
////	       
////	       // Update the JList with the retrieved projects
////	       DefaultListModel<String> projectListModel = new DefaultListModel<>();
////	       for (Project project : lpm.getProjects()) {
////	           projectListModel.addElement(project.getNomProjet()); // Assuming 'getName()' method returns project name
////	       }
////	       fp.getProjectList().setModel(projectListModel);
////	   }
//	
//	
//	private void displayTasksForProject(String projet) {
//        // Get selected project from AffichageProjet view
//        Project selectedProject = pc.getProjectByName(projet);
//        
//        // Check if a project is selected
//        if (selectedProject != null) {
//            // Get tasks associated with the selected project
//            List<Tache> tasks = selectedProject.getTaches();
//            
//            // Update VoirTacheDeProjet view to display tasks
//            //voirTacheDeProjet.displayTasks(tasks);
//        }
//    }
//	
////	public void ModifyTache(String intitule, String categorie, String desc, String dateD, String dateF) {
////		gestiontask.modifyTask( intitule,  categorie,  desc,  dateD,  dateF);
////	}
//	
//	
//	
//	
//	
//	
//}
