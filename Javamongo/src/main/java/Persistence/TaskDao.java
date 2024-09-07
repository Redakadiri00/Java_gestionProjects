package Persistence;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Metiers.*;
public class TaskDao {
	private MongoCollection<Document> tacheCollection;
	
	public TaskDao() {
        MongoDatabase database = Connexion.getDatabase();
        tacheCollection = database.getCollection("Tache");
       }
	
	
	 public void createtTache(Tache tache){	
		 Document document = new Document("intitule", tache.getIntitule())
                 .append("categTache", tache.getCategTache())
                 .append("descTache", tache.getDescTache())
                 .append("dateDebutTache", tache.getDateDebutTache())
                 .append("dateFinTache", tache.getDateFinTache());
		 
		 List<Document> fichiersDoc = new ArrayList<>();
		    for (Fichiers fichier : tache.getFiles()) {
		    	Document fichierDoc = new Document("filePath", fichier.getPath());
		        fichiersDoc.add(fichierDoc);
		    }
		    document.put("fichiers", fichiersDoc);
		 
		 tacheCollection.insertOne(document);


		 System.out.println("Tache ajoutée avec succès.");
		}
	 
	 	public void deleteTache(Tache task) {
			Bson filter = eq("intitule", task.getIntitule());
		    this.tacheCollection.deleteOne(filter);
		    System.out.println("tache supprimé avec succès.");
		}
	 
	 public boolean isExistingTache(String nomTask) {

         FindIterable<Document> iterable = tacheCollection.find(new Document("intitule", nomTask));
         return iterable.first() != null; // Retourne true si un document est trouvé, sinon false
     }
	 
	 public void updateTask(Tache task, String newIntitule, String newCategorie, String newDateDebut, String newDateFin, String NewDescription) {
	        Bson filter = eq("intitule", task.getIntitule());
	        
	        Document update = new Document()
	                .append("intitule", newIntitule)
	                .append("categTache", newCategorie)
	                .append("dateDebutTache", newDateDebut)
	                .append("dateFinTache", newDateFin)
	                .append("descTache", NewDescription);

	        tacheCollection.updateOne(filter, new Document("$set", update));
	        System.out.println("Tache mise à jour avec succès.");
	   }
	 
	 public void updateByName(String nom,String newIntit) {
		 Bson filter= eq("intitule",nom);
		 Document update=new Document("$set",new Document("Intitule", newIntit));
		 this.tacheCollection.updateOne(filter, update);
	 }
	 
	 public Tache searchBynom(String intituleTache) {
	        
	        Document filter = new Document("intitule", intituleTache);
	        FindIterable<Document> results = tacheCollection.find(filter);
		    
		    //List<Tache> projects = new ArrayList<>();
		    
		    try (MongoCursor<Document> cursor = results.iterator()) {
		        while (cursor.hasNext()) {
	        Document result = cursor.next();
	            Tache t= new Tache(
	                result.getString("intitule"),
	                result.getString("categTache"),
	                result.getString("descTache"),
	                result.getString("dateDebutTache"),
	                result.getString("dateFinTache"),
	                null
	            	);
	            	t.afficher();    
	            	return t;
		        }
	        return null;
		    }
	 }
	 
	 // Search par mot cle
	 public List<Tache> searchByKeyword(String keyword) {
	        List<Tache> matchingTasks = new ArrayList<>();
	        
	        // Create a regular expression pattern for the keyword
	        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
	        
	        // Define the filter to search for the keyword in the description field
	        Document filter = new Document("descTache", pattern);

	        // Iterate over the documents matching the filter
	        for (Document result : tacheCollection.find(filter)) {

	            Tache t = new Tache(
	                result.getString("intitule"),
	                result.getString("categTache"),
	                result.getString("descTache"),
	                result.getString("dateDebutTache"),
	                result.getString("dateFinTache"),
	                null            
	            );
	            t.afficher();
	            matchingTasks.add(t);
	        }

	        return matchingTasks;
	    }
	  
	 public List<Tache> searchByCat(String categorie) {
	        List<Tache> taches = new ArrayList<>();
	        Document filter = new Document("categTache", categorie);
	        for (Document result : tacheCollection.find(filter)) {
	            
	            Tache t = new Tache(
	                result.getString("intitule"),
	                result.getString("categTache"),
	                result.getString("descTache"),
	                result.getString("dateDebutTache"),
	                result.getString("dateFinTache"),
	                null
	            );
	            t.afficher();
	            taches.add(t);
	        }
	        return taches;
	    }
	 
	/// not working // 
	 public List<Tache> selectTachesOfProject(Project project) {
	        List<Tache> taches = new ArrayList<>();

	        // Construct the filter to find tasks related to the given project
	        Bson filter = Filters.eq("nomProjet", project.getNomProjet());

	        // Execute the query with the filter
	        MongoCursor<Document> cursor = tacheCollection.find(filter).iterator();

	        try {
	            // Iterate over the query results
	            while (cursor.hasNext()) {
	                Document result = cursor.next();
	                Tache tache = new Tache(
	                    result.getString("intitule"),
	                    result.getString("categTache"),
	                    result.getString("descTache"),
	                    result.getString("dateDebutTache"),
	                    result.getString("dateFinTache"),
		                null
	                );
	                taches.add(tache);
	            }
	        } finally {
	            cursor.close();
	        }

	        return taches;
	    }
		 
		 // =========== Difference entre readAll and SelectAllTaches ???? 
		 
	public List<Tache> readAll() {
			    MongoCursor<Document> cursor =tacheCollection.find().iterator();
			    ArrayList<Tache> taches = new ArrayList<>();

			    try {
			        while (cursor.hasNext()) {
			            Document result = cursor.next();
			            Tache p = new Tache(		            		
			                    result.getString("intitule"),
			                    result.getString("categTache"),
			                    result.getString("descTache"),
			                    result.getString("dateDebutTache"),
			                    result.getString("dateFinTache"),
				                null
			                    );
			            taches.add(p);
			        }
			    } finally {
			        cursor.close(); 
			    }

			    for (Tache result : taches) {
			        result.afficher();
			    }

			    return taches;
			}
		 
	public List<Tache> readAll(Project project) {
			    MongoCursor<Document> cursor = null;
			    ArrayList<Tache> taches = new ArrayList<>();

			    try {
			        // Construct the filter to find tasks related to the given project
			        Bson filter = Filters.eq("nomProjet", project.getNomProjet());

			        // Execute the query with the filter
			        cursor = tacheCollection.find(filter).iterator();

			        while (cursor.hasNext()) {
			            Document result = cursor.next();
			            Tache p = new Tache(                         
			                    result.getString("intitule"),
			                    result.getString("categTache"),
			                    result.getString("descTache"),
			                    result.getString("dateDebutTache"),
			                    result.getString("dateFinTache"),
				                null
			                    );
			            taches.add(p);
			        }
			    } finally {
			        if (cursor != null) {
			            cursor.close(); 
			        }
			    }

			    for (Tache result : taches) {
			        result.afficher();
			    }

			    return taches;
			}
		 
	public void insertFileToTask(Tache task, Fichiers file) {
	    // Create a document for the file
	    Document fileDoc = new Document("filePath", file.getPath());

	    // Get the task document from the database
	    Document query = new Document("intitule", task.getIntitule());
	    Document taskDoc = tacheCollection.find(query).first();

	    if (taskDoc != null) {
	        // Get the files array from the task document
	        List<Document> fichiers = (List<Document>) taskDoc.get("fichiers");

	        // Add the new file document to the array
	        fichiers.add(fileDoc);

	        // Update the task document with the modified array of files
	        tacheCollection.replaceOne(query, taskDoc);
	        System.out.println("File inserted successfully into the task.");
	    } else {
	        System.out.println("Task document not found for intitule: " + task.getIntitule());
	    }
	}
	
	public List<String> getFilePathsForTask(Tache task) {
	    List<String> filePaths = new ArrayList<>();

	    // Rechercher la tâche dans la base de données en utilisant son intitulé
	    Document query = new Document("intitule", task.getIntitule());
	    Document taskDocument = tacheCollection.find(query).first();

	    if (taskDocument != null) {
	        // Récupérer la liste des documents de fichiers de la tâche
	        List<Document> filesDocuments = (List<Document>) taskDocument.get("fichiers");

	        // Parcourir la liste de documents de fichiers et extraire les chemins de fichier
	        for (Document fileDocument : filesDocuments) {
	            String filePath = fileDocument.getString("file");
	            filePaths.add(filePath);
	        }
	    } else {
	        System.out.println("Task document not found for intitule: " + task.getIntitule());
	    }
	    System.out.println(filePaths.toString());
	    return filePaths;
	}
		 	
		 	
	public List<Tache> filterByDate() {
		        List<Tache> tasks = new ArrayList<>();

		        // Définir le tri pour la date de début (ou la date de fin si vous préférez)
		        // Le tri se fait du plus récent au plus ancien
		        Document sort = new Document("dateFinTache", 1); // Utiliser 1 pour trier par ordre croissant

		        // Effectuer la requête pour récupérer les tâches triées par date
		        for (Document result : tacheCollection.find().sort(sort)) {

		            Tache t = new Tache(
		                result.getString("intitule"),
		                result.getString("categTache"),
		                result.getString("descTache"),
		                result.getString("dateDebutTache"),
		                result.getString("dateFinTache"),
		                null
		            );
		            t.afficher();
		            tasks.add(t);
		        }
		        return tasks;
		    }
	
	
public Tache getTacheByName(String nomTache) {
        try {
        	
            if (nomTache == null || nomTache.isEmpty()) {
                System.out.println("Le nom de la tâche ne peut pas être null ou vide.");
                return null;
            }

            Document filter = new Document("intitule", nomTache);
            Document result = tacheCollection.find(filter).first();

            if (result != null) {
                Tache tache = new Tache(
                    result.getString("intitule"),
                    result.getString("categTache"),
                    result.getString("descTache"),
                    result.getString("dateDebutTache"),
                    result.getString("dateFinTache")
                );
                return tache;
            } else {
                System.out.println("La tâche avec le nom spécifié n'a pas été trouvée.");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite lors de la récupération de la tâche : " + e.getMessage());
            return null;
        }
    }

	
	
}






