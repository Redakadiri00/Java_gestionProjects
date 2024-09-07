package ViewTest;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import java.util.List;
import Metiers.Project;
import Metiers.Tache;
import TestController.ProjetControl;

public class TacheDeProjetView extends JFrame {

	private JButton supprimerButton,  ajouterButton, RechercheButton, returnButton;
	private JTextField TextField;
	private JLabel rechercheLabel;
	private JLabel MesTaches, FilterLabel;
	private JPanel Paneltop, Panelmiddle, Panelbottom, PanelPrincipale;
	private JPanel TopButtonPanel, RecherchePanel;
	private JList<String> taskList;
	private ProjetControl pc;
	private Project selectedProjec;
	
	public JList<String> getTaskList() {
		return taskList;
	}
	
	public void setTaskList(JList<String> taskList) {
		this.taskList = taskList;
	}







	
	
//	public TacheDeProjetView (Project project) {
//		this.project=project;
//		
//	}
	
	public Project getSelectedProjec() {
		return selectedProjec;
	}

	public void setSelectedProjec(Project selectedProjec) {
		this.selectedProjec = selectedProjec;
	}

	public TacheDeProjetView() {
		
	}

	public TacheDeProjetView(Project selectedProjec,ProjetControl pc) {
	   this.pc=pc;
	   this.selectedProjec = selectedProjec;
	   this.taskList= taskList;
	   this.Dessiner(); 
	   
	   
       
       
       supprimerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // Get the selected task from the JList
               String selectedTask = taskList.getSelectedValue();
               if (selectedTask != null) {
                   // Call the DeleteTacheDeProjet method in ProjetControl
                   try {
                       pc.DeleteTacheDeProjet(selectedProjec.getNomProjet(), selectedTask);
                       refreshTaskList(selectedProjec);
                   } catch (Exception ex) {
                       ex.printStackTrace();
                       // Handle any exceptions that occur during deletion
                       JOptionPane.showMessageDialog(TacheDeProjetView.this, "Error deleting task: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   }
               } else {
                   JOptionPane.showMessageDialog(TacheDeProjetView.this, "Veuillez sélectionner une tâche à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
               }	
           }
       });
       
       ajouterButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {	
			new AjouterTacheView(selectedProjec,pc);
			
		}
       });
        
       
       
       taskList.addMouseListener(new MouseAdapter() {
    	    @Override
    	    public void mouseClicked(MouseEvent e) {
    	        if (e.getClickCount() == 2) { 
    	        	
    	            String selectedTask = taskList.getSelectedValue();
    	            if (selectedTask != null) {
    	                Tache task = pc.getTaskDetailsByName(selectedProjec.getNomProjet(), selectedTask);
    	                pc.displayTacheDetails(task);
    	            }
    	            dispose();
    	        }
    	        
    	    }
    	});
       	
       	returnButton.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			pc.displayProjectDetails(selectedProjec);
			dispose();
			
			
		}
       	});
       	
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
   }

	public void Dessiner() {
	setTitle("Voir Tache");
    setSize(1000, 700);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    // Components
    MesTaches = new JLabel("mes Taches");
    MesTaches.setFont(new Font("Arial", Font.BOLD, 24));
    // Create TopButtonPanel to hold buttons
    TopButtonPanel = new JPanel();
    returnButton= new JButton("return");
    supprimerButton = new JButton("Supprimer");
    ajouterButton = new JButton("Ajouter");
    TopButtonPanel.add(ajouterButton);
    TopButtonPanel.add(supprimerButton);
    TopButtonPanel.add(returnButton);
    Paneltop = new JPanel(new BorderLayout()); // Use BorderLayout for Paneltop
    Paneltop.add(MesTaches, BorderLayout.LINE_START); // Add MesTaches label to the left
    Paneltop.add(TopButtonPanel, BorderLayout.EAST); // Add TopButtonPanel to the right
    RecherchePanel = new JPanel();
    FilterLabel = new JLabel("Filtrer Par:");
    rechercheLabel = new JLabel("Recherche");
    TextField = new JTextField(15);
    RechercheButton = new JButton("Rechercher");
    RecherchePanel.add(rechercheLabel);
    RecherchePanel.add(TextField);
    Panelmiddle = new JPanel();
    Panelmiddle.add(FilterLabel);
    Panelmiddle.add(RecherchePanel, BorderLayout.EAST);
    Panelmiddle.add(RechercheButton);
    List<Tache> tasks = pc.DisplayTask_By_ProjetName(selectedProjec.getNomProjet()); // Assuming you have a method to get tasks by project name
    DefaultListModel<String> taskListModel = new DefaultListModel<>();
    for (Tache task : tasks) {
        taskListModel.addElement(task.getIntitule()); // Or task.toString() depending on your implementation
    }
    taskList = new JList<>(taskListModel);
    
    JScrollPane taskScrollPane = new JScrollPane(taskList);
    add(taskScrollPane, BorderLayout.CENTER);
    taskScrollPane.setPreferredSize(new Dimension(800, 500)); // Set preferred size for the scroll pane
    
    Panelbottom = new JPanel(new BorderLayout()); // Use BorderLayout for Panelbottom
    Panelbottom.add(new JLabel("List of Tasks:"), BorderLayout.NORTH);
    Panelbottom.add(taskScrollPane, BorderLayout.CENTER); // Add taskScrollPane to take up most of the space
    
    PanelPrincipale = new JPanel(new BorderLayout());
    PanelPrincipale.add(Paneltop, BorderLayout.NORTH);
    PanelPrincipale.add(Panelmiddle, BorderLayout.WEST); // Add Panelmiddle to the center
    PanelPrincipale.add(Panelbottom, BorderLayout.SOUTH); // Add Panelmiddle to the center
    getContentPane().add(PanelPrincipale);
    //setVisible(true);
	   
}
   
   
   
	private void refreshTaskList(Project selectedProjectName) {
    DefaultListModel<String> taskListModel = new DefaultListModel<>();
    List<Tache> tasks = pc.DisplayTask_By_ProjetName(selectedProjectName.getNomProjet());
    for (Tache task : tasks) {
        taskListModel.addElement(task.getIntitule());
    }
    taskList.setModel(taskListModel);
	}
   
   
	
   
   
   
	
   public static void main(String[] args) {
	   ProjetControl pc = new ProjetControl();	     
   }
   
	}
