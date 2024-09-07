package Persistence;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Metiers.*;
public class main {

	public static void main(String[] args) {
		String connectionString = "mongodb://localhost:27017";
		MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
	      MongoClient mongoClient = MongoClients.create(settings);
	      MongoDatabase database = mongoClient.getDatabase("Gestion_De_Projet");
		
	      //MongoCollection<Document> collection = database.getCollection("Projet");
	      
	      
	      
	      
	     // insertion 
//		Seance seance = new Seance(19,new ObjectId("6609dcf379d8f937e61fcc19"),"newww ","2034/12/12","2037/12/12","this is note 2 ") ;		
//	    SeanceDao seanceDao= new SeanceDao(database);
	    
	    
	    Tache tache1 = new Tache("tache 1 ", "Enseignement" , "this is description", "2034/12/12","2035/12/12",new ArrayList());
	    Tache tache2 = new Tache("tache 4", "Encadrement" , "modelisation de traffic routier", "2034/10/9","203/12/12",new ArrayList());
	    Tache tache3 = new Tache("New task", "Enseignement" , "modelisation de blabla", "2034/12/1","2034/5/19",new ArrayList());
	    Tache tache4 = new Tache("Task task ", "encadrement" , "modelisation de blabla", "2034/12/12","2035/12/12",new ArrayList());
	    TaskDao tachedao = new TaskDao();
	    
	    
	    
	    Liste liste = new Liste( "Tache de soutient", "this is description");
	    ListDao listdao = new ListDao();
	    
	    Project projet= new Project("jdiid ", "sdsd", "pfe","this is desc","2034/12/12","2035/12/12");
	    Project projet2= new Project("projet 2 ", "encad", "pfa","this is new desc","2034/12/12","2035/12/12");
	    Project projet3= new Project("nhar kbir ", "encad", "pfa","this is new desc","2034/12/12","2035/12/12");
	    ProjetDao projetdao =new  ProjetDao();
	    
	    
	    Seance seance = new Seance(1, "desc de la sc", "2034/12/12","08:30","10:30","this is note",new ArrayList());
	    SeanceDao seancedao = new SeanceDao();
	    
	    
	    Fichiers fichier = new Fichiers("C:\\Users\\redaa\\OneDrive\\Desktop\\TP projet\\Tp.csv");
	    FichierDao fichierdao = new FichierDao();
	    
	    //tachedao.createtTache(tache1);
	    //tachedao.update("faire cours", "Encadrement", "new description","New task", "2034/12/12", "2044/12/12")	    
	    //tachedao.searchByKeyword("r");
	    //tachedao.filterByDate();	
	    //tachedao.deleteTache(tache2);
	    //projetdao.createPrjt(projet);
	    //projetdao.insertTaskToProject(projet,tache3);
	    //projetdao.getAllTasksWithInfoForProject(projet);	    
	    //projetdao.showTaskForProject(projet);	    
	    //seancedao.createSeance(seance);
	    //listdao.creerListe(liste);
	    //listdao.insertTaskToList(liste, tache1);	    
	    //projetdao.readAll();
	    //projetdao.read(projet3);
	    //projetdao.deletePrjt(projet3);
	    //projetdao.deletePrjtName("ggg");
	    //projetdao.filterByKeywordInDescription("hh");
	    //projetdao.cloneProject(projet);
	    //projetdao.updateProjet1(projet, "nom", "type"  , "category" ,  "dateD" , "dateF" , "desc");
	    //projetdao.selectTachesOfProject(projet2);
	    //projetdao.getTasksByProjectName("jdiid ");
	    //projetdao.DeleteTaskFromProject("projet 2 ", "tache 1 ");
	    //tachedao.insertFileToTask(tache1,fichier);
	    //projetdao.InsertFile_To_Task_Project(projet3,tache1,fichier);
	    //tachedao.getFilePathsForTask(tache1);
	    //projetdao.deleteFileFromProjet(projet2, "C:\\Users\\redaa\\OneDrive\\Desktop\\TP projet\\Tp.csv") ;
	    //projetdao.CloneProject(projet3);
	    //projetdao.insertFileToProjet(projet3, fichier);
	    //tachedao.updateTask(tache1,  "newIntitule",  "newCategorie", "newDateDebut", "newDateFin", "newDescription ");
	    //projetdao.InsertFile_To_Task_Project(projet, tache3, fichier);
	    //projetdao.getFilesOfTaskInProject( "neww ","tache 1 ");
	    //projetdao.cloturer("neww ");
	    //projetdao.SearchInTaskOfProject(projet,"this");
	    //projetdao.updateTaskInProject( projet, tache3, " newIntitule", "newDescTache", " newDateDebutTache", " newDateFinTache");
	    projetdao.insertSeanceToProject(projet,  seance);
	    //projetdao.getSeancesByProjectName("jdiid ");
	    //projetdao.DeleteSeanceFromProject("jdiid ", 1);
	}
	
}
