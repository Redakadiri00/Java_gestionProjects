package Persistence;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;


import Metiers.*;

public class ProjetDao{
	

	public ProjetDao() {
		MongoDatabase database = Connexion.getDatabase();
		collectionPrjt = database.getCollection("projet");
		tacheCollection=database.getCollection("Tache");
		
    }


private final MongoCollection<Document> collectionPrjt;
private  MongoCollection<Document> tacheCollection;

//private MongoDatabase db;

public void createPrjt(Project projet) {
    Document doc = new Document("nomProjet", projet.getNomProjet())
            .append("descPrjt", projet.getDescPrjt())
            .append("dateDebutprjt", projet.getDateDebutprjt())
            .append("dateFinprjt", projet.getDateFinprjt())
            .append("categoryPrjt",projet.getCategoryPrjt())
            .append("typePrjt", projet.getTypePrjt());
  
    List<Document> tachesDoc = new ArrayList<>();
    for (Tache tache : projet.getTaches()) {
        Document tacheDoc = new Document("intitule", tache.getIntitule())
                .append("categTache", tache.getCategTache())
                .append("descTache", tache.getDescTache())
                .append("dateDebutTache", tache.getDateDebutTache())
                .append("dateFinTache", tache.getDateFinTache());
        tachesDoc.add(tacheDoc);
    }
    doc.put("taches", tachesDoc);
    List<Document> seancesDoc = new ArrayList<>();
    for (Seance seance : projet.getSeances()) {
        Document seanceDoc = new Document("numSeance", seance.getNumSeance())
                
                .append("DescriptionSeance", seance.getDescriptionSeance())
                .append("jourDebutSeance", seance.getJourDebutSeance())
                .append("heureDebutSeance", seance.getHeureDebutSeance())
                .append("heureFinSeance", seance.getHeureFinSeance())
                .append("note", seance.getNote());
        seancesDoc.add(seanceDoc);
    }
    doc.put("seances", seancesDoc);
    
    List<Document> fichiersDoc = new ArrayList<>();
    for (Fichiers fichier : projet.getFiles()) {
        Document fichierDoc = new Document("file", fichier.getPath());
        fichiersDoc.add(fichierDoc);
    }
    doc.put("fichiers", fichiersDoc);

    collectionPrjt.insertOne(doc);
    System.out.println("projet ajouté ");
}

//not used

public boolean exists(Project projet) {
    Document filter = new Document("nomProjet", projet.getNomProjet());
    List<Document> existingProjects = collectionPrjt.find(filter).into(new ArrayList<>());
    return !existingProjects.isEmpty();
}

public void cloturer(String projectName) {
    Bson filter = eq("nomProjet", projectName);
        Document projectDoc = collectionPrjt.find(filter).first();

    if (projectDoc != null) {
        String updatedProjectName = projectDoc.getString("nomProjet") + " (Cloturé)";
        Bson update = Updates.set("nomProjet", updatedProjectName);
        collectionPrjt.updateOne(filter, update);
        System.out.println("Project '" + projectName + "' has been closed successfully. New name: " + updatedProjectName);
    } else {
        System.out.println("Project '" + projectName + "' not found.");
    }
}

public void updateTaskInProject(Project project, Tache task, String newIntitule, String newDescTache, String newDateDebutTache, String newDateFinTache) {
    if (project == null) {
        System.out.println("Project is null.");
        return;
    }
    if (task == null) {
        System.out.println("Task is null.");
        return;
    }

    Document query = new Document("nomProjet", project.getNomProjet());
    Document projectDoc = collectionPrjt.find(query).first();

    if (projectDoc != null) {
        List<Document> taches = (List<Document>) projectDoc.get("taches");

        if (taches != null) {
            for (Document taskDoc : taches) {
                if (taskDoc.getString("intitule").equals(task.getIntitule())) {
                    if (newIntitule != null) {
                        taskDoc.put("intitule", newIntitule);
                    }
                    if (newDescTache != null) {
                        taskDoc.put("descTache", newDescTache);
                    }
                    if (newDateDebutTache != null) {
                        taskDoc.put("dateDebutTache", newDateDebutTache);
                    }
                    if (newDateFinTache != null) {
                        taskDoc.put("dateFinTache", newDateFinTache);
                    }
                    break; // Task found and updated, exit the loop
                }
            }
        }

        collectionPrjt.replaceOne(query, projectDoc);
        System.out.println("Task updated successfully in the project.");
    } else {
        System.out.println("Project document not found for name: " + project.getNomProjet());
    }
}



public void CloneProject(Project originalProject) {
    if (originalProject != null) {
        Project clonedProject = new Project(
            originalProject.getNomProjet() + " (1)", // Append (1) to the name
            originalProject.getCategoryPrjt(),
            originalProject.getTypePrjt(),
            originalProject.getDescPrjt(),
            originalProject.getDateDebutprjt(),
            originalProject.getDateFinprjt(),
            originalProject.getTaches(),
            originalProject.getSeances(),
            originalProject.getFiles()
        );
        int count = 1;
        String newProjectName = clonedProject.getNomProjet();
        
        while (exists(clonedProject)) {
            count++;
            newProjectName = originalProject.getNomProjet() + " (" + count + ")";
            clonedProject.setNomProjet(newProjectName);
        }

        createPrjt(clonedProject);
        System.out.println("Project cloned successfully. New project name: " + newProjectName);
    } else {
        System.out.println("Original project is null.");
    }
}

public List<Tache> getTasksByProjectName(String projectName) {
    List<Tache> tasks = new ArrayList<>();
    List<Fichiers> files = new ArrayList<>();
    Document projectDocument = collectionPrjt.find(Filters.eq("nomProjet", projectName)).first();

    if (projectDocument != null) {
        List<Document> tasksDocuments = (List<Document>) projectDocument.get("taches");

        // Convert task documents into Task objects
        if (tasksDocuments != null) {
            for (Document taskDocument : tasksDocuments) {
                Tache task = new Tache(
                    taskDocument.getString("intitule"),
                    taskDocument.getString("categTache"),
                    taskDocument.getString("descTache"),
                    taskDocument.getString("dateDebutTache"),
                    taskDocument.getString("dateFinTache"),
                    files
                );
                tasks.add(task);
            }
        }
    }
    System.out.println(tasks.toString());
    return tasks;
}



public List<Seance> getSeancesByProjectName(String projectName) {
    List<Seance> seances = new ArrayList<>();
    List<Fichiers> files = new ArrayList<>();
    
    Document projectDocument = collectionPrjt.find(Filters.eq("nomProjet", projectName)).first();
    
    if (projectDocument != null) {
        List<Document> seancesDocuments = (List<Document>) projectDocument.get("seances");
        if (seancesDocuments != null) {
            for (Document seanceDocument : seancesDocuments) {
                Seance seance = new Seance(
                    seanceDocument.getInteger("numSeance"),
                    seanceDocument.getString("descriptionSeance"),
                    seanceDocument.getString("dateDebutSeance"),
                    seanceDocument.getString("heureDebutSeance"),
                    seanceDocument.getString("heureFinSeance"),
                    seanceDocument.getString("note"),
                    files
                );
                seances.add(seance);
            }
        }
    }
    System.out.println(seances.toString());
    return seances;
}

public void DeleteSeanceFromProject(String projectName, int Seancenum) {
    // Create a filter to find the project document by its name
    Document filter = new Document("nomProjet", projectName);
    
    // Create an update operation to remove the task from the project's 'taches' array
    Document update = new Document("$pull", new Document("seances", new Document("numSeance", Seancenum)));
    
    // Execute the update operation
    collectionPrjt.updateOne(filter, update);
    
    System.out.println("seance deleted successfully from project: " + projectName);
}



	public List<Fichiers> getFileByProjectName(String projectName) {
    List<Fichiers> files = new ArrayList<>();
    Document projectDocument = collectionPrjt.find(Filters.eq("nomProjet", projectName)).first();
    if (projectDocument != null) {
    	
        List<Document> FileDocuments = (List<Document>) projectDocument.get("fichiers");
        // file document a File objet
        if (FileDocuments != null) {
            for (Document fileDocument : FileDocuments) {
                Fichiers fichier = new Fichiers(fileDocument.getString("filePath"));
                files.add(fichier);
            }
        }
    }
    System.out.println(files.toString());
    return files;
}


	public List<Fichiers> getFilesOfTaskInProject(String projectName, String taskIntitule) {
    List<Fichiers> files = new ArrayList<>();

    // Get the project document from the database
    Document projectQuery = new Document("nomProjet", projectName);
    Document projectDoc = collectionPrjt.find(projectQuery).first();

    if (projectDoc != null) {
        // Get the tasks array from the project document
        List<Document> tasks = (List<Document>) projectDoc.get("taches");

        if (tasks != null) {
            // Find the task with the given intitule
            for (Document taskDoc : tasks) {
                if (taskDoc.getString("intitule").equals(taskIntitule)) {
                    // Get the files array from the task document
                    List<Document> fichiers = (List<Document>) taskDoc.get("fichiers");

                    if (fichiers != null) {
                        // Convert file documents into Fichiers objects
                        for (Document fileDoc : fichiers) {
                            Fichiers fichier = new Fichiers(fileDoc.getString("file"));
                            files.add(fichier);
                        }
                    }
                    break; // Task found, no need to continue searching
                }
            }
        }
    } else {
        System.out.println("Project document get not found for name: " + projectName);
    }

    System.out.println(files.toString());
    return files;
	}


public void DeleteTaskFromProject(String projectName, String taskIntitule) {
    // Create a filter to find the project document by its name
    Document filter = new Document("nomProjet", projectName);
    
    // Create an update operation to remove the task from the project's 'taches' array
    Document update = new Document("$pull", new Document("taches", new Document("intitule", taskIntitule)));
    
    // Execute the update operation
    collectionPrjt.updateOne(filter, update);
    
    System.out.println("Task deleted successfully from project: " + projectName);
}

public void insertTaskToProject(Project project, Tache task) {
	TaskDao tachedao=new TaskDao();
    Document taskDoc = new Document("intitule", task.getIntitule())
                        .append("categTache", task.getCategTache())
                        .append("descTache", task.getDescTache())
                        .append("dateDebutTache", task.getDateDebutTache())
                        .append("dateFinTache", task.getDateFinTache())
                        .append("fichiers", task.getFiles());

    
    if (collectionPrjt.find(taskDoc).first()==null) {
    	tachedao.createtTache(task);
    }
    Document query = new Document("nomProjet", project.getNomProjet());
    Document projectDoc = collectionPrjt.find(query).first();
    
    if (projectDoc != null) {
        List<Document> taches = (List<Document>) projectDoc.get("taches");
        taches.add(taskDoc);
        collectionPrjt.replaceOne(query, projectDoc);
        System.out.println("Task inserted successfully into the project.");
    } else {
        System.out.println("Project document not found for ID: " + project.getNomProjet());
    }
}

public void InsertFile_To_Task_Project(Project projet, Tache task, Fichiers file) {
    if (projet == null || projet.getNomProjet() == null) {
        System.out.println("Project or project name is null");
        return;
    }

    if (task == null || task.getIntitule() == null) {
        System.out.println("Task or task intitule is null");
        return;
    }

    // Get the project document from the database
    Document query = new Document("nomProjet", projet.getNomProjet());
    Document projectDoc = collectionPrjt.find(query).first();

    if (projectDoc != null) {
        // Find the task within the project document
        List<Document> taches = (List<Document>) projectDoc.get("taches");
        if (taches != null) {
            for (Document tacheDoc : taches) {
                if (tacheDoc.getString("intitule").equals(task.getIntitule())) {
                    // Add the file to the 'fichiers' array of the task document
                    List<Document> fichiers = (List<Document>) tacheDoc.get("fichiers");
                    if (fichiers == null) {
                        fichiers = new ArrayList<>();
                    }
                    fichiers.add(new Document("file", file.getPath()));
                    tacheDoc.put("fichiers", fichiers);
                    break; // Stop searching for the task once found
                }
            }
        }

        // Replace the project document with the updated one in the collection
        collectionPrjt.replaceOne(query, projectDoc);
        System.out.println("File inserted successfully into the task of the project.");
    } else {
        System.out.println("Project document not found for name: " + projet.getNomProjet());
    }
}


public void insertSeanceToProject(Project project, Seance seance) {
    Document seanceDoc = new Document("numSeance", seance.getNumSeance())
        .append("DescriptionSeance", seance.getDescriptionSeance())
        .append("jourDebutSeance", seance.getJourDebutSeance())
        .append("dateDebutSeance", seance.getHeureDebutSeance())
        .append("dateFinSeance", seance.getHeureFinSeance())
        .append("note", seance.getNote())
        .append ("fichiers", seance.getDocs());

    Document query = new Document("nomProjet", project.getNomProjet());
    Document projetDoc = collectionPrjt.find(query).first();

    if (projetDoc != null) {
        List<Document> seances = (List<Document>) projetDoc.get("seances");

        if (seances == null) {
            seances = new ArrayList<>();
        }

        seances.add(seanceDoc);

        projetDoc.put("seances", seances);
        collectionPrjt.replaceOne(query, projetDoc);
        System.out.println("Séance ajoutée avec succès au projet.");
    } else {
        System.out.println("Document du projet introuvable pour le nom: " + project.getNomProjet());
    }
}

public void insertFileToProjet(Project projet, Fichiers file) {
    // Create a document for the file
    Document fileDoc = new Document("filePath", file.getPath());

    // Get the task document from the database
    Document query = new Document("nomProjet", projet.getNomProjet());
    Document projetDoc = collectionPrjt.find(query).first();

    if (projetDoc != null) {

    	List<Document> fichiers = (List<Document>) projetDoc.get("fichiers");
        fichiers.add(fileDoc);
        collectionPrjt.replaceOne(query, projetDoc);
        System.out.println("File inserted successfully into the Project.");
    } else {
        System.out.println("Project document not found for nom: " + projet.getNomProjet());
    }
}


public void deleteFileFromProjet(Project projet, String filePath) {
    Document query = new Document("nomProjet", projet.getNomProjet());
    Document projetDoc = collectionPrjt.find(query).first();

    if (projetDoc != null) {
        List<Document> fichiers = (List<Document>) projetDoc.get("fichiers");
        for (Iterator<Document> iterator = fichiers.iterator(); iterator.hasNext();) {
            Document fileDoc = iterator.next();
            if (fileDoc.getString("filePath").equals(filePath)) {
                iterator.remove();
                System.out.println("File deleted successfully from the Project.");
                break; 
            }
        }

        collectionPrjt.replaceOne(query, projetDoc);
    } else {
        System.out.println("Project document not found for nom: " + projet.getNomProjet());
    }
}


public void deletePrjtName(String nom) {
    Bson filter = eq("nomProjet", nom);
    this.collectionPrjt.deleteOne(filter);
    System.out.println("Projet supprimé avec succès.");
}


	
	public void updateProjet1(Project project, String nom, String type, String categorie, String dateD, String dateF, String desc) {
	    Bson filter = eq("nomProjet", project.getNomProjet());

	    Document update = new Document("nomProjet", nom)
	            .append("descPrjt", desc)
	            .append("typePrjt", type)
	            .append("categoryPrjt", categorie)
	            .append("dateFinprjt", dateF)
	            .append("dateDebutprjt", dateD);
	    this.collectionPrjt.updateOne(filter, new Document("$set", update));
	    System.out.println("Projet modifié avec succès.");
	}
	
	
	public ArrayList<Project> searchByType(String type) {
		
	    Bson filter = eq("typePrjt", type);
	    FindIterable<Document> results = collectionPrjt.find(filter);
	    
	    ArrayList<Project> projects = new ArrayList<>();
	    
	    try (MongoCursor<Document> cursor = results.iterator()) {
	        while (cursor.hasNext()) {
	            Document result = cursor.next();
	            Project p = new Project(
	                    result.getString("nomProjet"),
	                    result.getString("categoryPrjt"),
	                    result.getString("typePrjt"),
	                    result.getString("descPrjt"),
	                    result.getString("dateDebutprjt"),
	                    result.getString("dateFinprjt"));
	            projects.add(p);
	            p.afficher(); 
	        }
	    }

	    return projects;
	}
	

	public List<Project> filterByKeywordInDescription(String keyword) {
	    collectionPrjt.createIndex(new Document("descPrjt", "text"));
	    
	    Bson filter = new Document("$text", new Document("$search", keyword));
	    FindIterable<Document> results = collectionPrjt.find(filter);
	    
	    List<Project> projects = new ArrayList<>();
	    
	    try (MongoCursor<Document> cursor = results.iterator()) {
	        while (cursor.hasNext()) {
	            Document result = cursor.next();
	            Project p = new Project(
	                    result.getString("nomProjet"),
	                    result.getString("categoryPrjt"),
	                    result.getString("typePrjt"),
	                    result.getString("descPrjt"),
	                    result.getString("dateDebutprjt"),
	                    result.getString("dateFinprjt"));
	            projects.add(p);
	            p.afficher(); 
	        }
	    }

	    return projects;
	}

		
	public ArrayList<Project> readAll() {
	    MongoCursor<Document> cursor = collectionPrjt.find().iterator();
	    ArrayList<Project> projets = new ArrayList<>();

	    try {
	        while (cursor.hasNext()) {
	            Document result = cursor.next();
	            Project p = new Project(
	                    result.getString("nomProjet"),
	                    result.getString("categoryPrjt"),
	                    result.getString("typePrjt"),
	                    result.getString("descPrjt"),
	                    result.getString("dateDebutprjt"),
	                    result.getString("dateFinprjt"));
	            projets.add(p);
	        }
	    } finally {
	        cursor.close(); 
	    }

	    for (Project result : projets) {
	        result.afficher();
	    }

	    return projets;
	}		
}
