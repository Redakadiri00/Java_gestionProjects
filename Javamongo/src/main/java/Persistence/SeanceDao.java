package Persistence;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import Metiers.*;

public class SeanceDao {

	private MongoCollection<Document> SeanceCollection;
	
	public SeanceDao() {
		MongoDatabase database = Connexion.getDatabase();
		SeanceCollection = database.getCollection("Seance");
    }
	
	public void createSeance(Seance seance) {
	//	Document existingProject = collectionPrjt.find(new Document("idProjet", seance.getIdProjet())).first();
		
		Document doc= new Document("numSeance",seance.getNumSeance()).append("DescriptionSeance", seance.getDescriptionSeance())
		.append("dateDebutSeance", seance.getDateDebutSeance()).append("DatefinSeance", seance.getDatefinSeance())
		.append("note", seance.getNote());
		
		
		// ajout de array tache dans collection projet
	    List<Document> documentesDoc = new ArrayList<>();
	    for (Documente documente : seance.getDocs()) {
	        Document documenteDoc = new Document("titreDoc", documente.getTitreDoc())
	                .append("descDoc", documente.getDescDoc())
	                .append("dateAjout", documente.getDateAjout());
	        documentesDoc.add(documenteDoc);
	    }
	    doc.put("Document", documentesDoc);
	
	    SeanceCollection.insertOne(doc);
	    System.out.println("Seance ajoutee");
		
		
	}
	
	public void updateSeance(int numSeance, String desc_Sc, String datedeb_Sc, String datefin_Sc, String note,ObjectId idprj) {
       
        Document filter = new Document("NumSeance", numSeance);

        Document updateDoc = new Document("$set", new Document("numSeance", numSeance)
                .append("DescriptionSeance", desc_Sc)
                .append("dateDebutSeance", datedeb_Sc)
                .append(" DatefinSeance", datefin_Sc)
                .append("note", note)).append("idProjet", idprj);
        SeanceCollection.updateOne(filter, updateDoc);
    }
	
	public void deleteSeance(Seance seance) {
		Document seanceAsupprimer = new Document();
		SeanceCollection.deleteOne(seanceAsupprimer);;
	}
	
	public List <Seance> getSeance(int numSeance) {
       
        Document filter = new Document("numSeance", numSeance);

        FindIterable<Document> result = SeanceCollection.find(filter);
           ArrayList<Seance> senaces=new ArrayList<Seance>();
       
        for (Document document : result) {
           
            Seance seance = new Seance(document.getObjectId("idSeance"),
                    document.getInteger("numSeance"),
                    document.getString("DescriptionSeance"),
                    document.getString("dateDebSeance"),
                    document.getString("dateFinSeance"),
                    document.getString("note"));
            seance.afficher();
            senaces.add(seance);
        }
        return senaces;
	}
	
	
	public List <Seance> getAllSeance() {
	       
        MongoCursor<Document> cursor=SeanceCollection.find().cursor();
           ArrayList<Seance> seances=new ArrayList<Seance>();
       
        while (cursor.hasNext()) {
        	Document result = cursor.next();
           
            Seance seance = new Seance(result.getObjectId("idSeance"),
            		result.getInteger("numSeance"),
            		result.getString("DescriptionSeance"),
            		result.getString("dateDebSeance"),
            		result.getString("dateFinSeance"),
            		result.getString("note"));
           
            seance.afficher();
            seances.add(seance);
        }
        return seances;
	}
	
	public void insertDocumenteToSeance(Seance seance, Documente documente) {  // regler cette methode apres savoir cmnt manipuler  document 
	    // Create a document representing the task
		TaskDao tachedao=new TaskDao();
		tachedao.createtTache(task);
	    Document taskDoc = new Document("intitule", task.getIntitule())
	                        .append("categTache", task.getCategTache())
	                        .append("descTache", task.getDescTache())
	                        .append("dateDebutTache", task.getDateDebutTache())
	                        .append("dateFinTache", task.getDateFinTache());

	    // Find the project document by its ID
	    Document query = new Document("nomProjet", project.getNomProjet());
	    Document projectDoc = SeanceCollection.find(query).first();
	    
	    // Check if the project document exists
	    if (projectDoc != null) {
	        // Get the array of tasks from the project document
	        List<Document> taches = (List<Document>) projectDoc.get("taches");

	        // Add the new task document to the array
	        taches.add(taskDoc);

	        // Update the project document with the modified array of tasks
	        SeanceCollection.replaceOne(query, projectDoc);
	        System.out.println("Task inserted successfully into the project.");
	    } else {
	        System.out.println("Project document not found for ID: " + project.getNomProjet());
	    }
	}

}