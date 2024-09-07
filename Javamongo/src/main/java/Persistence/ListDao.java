package Persistence;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import Metiers.Fichiers;
import Metiers.Liste;
import Metiers.Tache;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class ListDao {

	ArrayList<Tache> taches;
	private MongoCollection<Document> ListeCollection;
	TaskDao tk;
		
	public ListDao() {
		 	MongoDatabase database = Connexion.getDatabase();
	      ListeCollection = database.getCollection("liste");
	}
	


	
	

	 public List<Tache> getTasksByListName(String nomListe) {
		    // Créer un filtre pour rechercher la liste par son nom
		    BasicDBObject filter = new BasicDBObject("nomListe", nomListe);

		    // Rechercher la liste dans la collection en fonction du filtre
		    Document listeDoc = ListeCollection.find(filter).first();

		    List<Tache> tasks = new ArrayList<>();

		    // Vérifier si la liste existe et si elle contient des tâches
		    if (listeDoc != null && listeDoc.containsKey("taches")) {
		        // Récupérer la liste des tâches associées à cette liste
		        @SuppressWarnings("unchecked")
		        List<Document> tachesDoc = (List<Document>) listeDoc.get("taches");

		        for (Document tacheDoc : tachesDoc) {
		            Tache tache = new Tache(
		                tacheDoc.getString("intitule"),
		                tacheDoc.getString("categTache"),
		                tacheDoc.getString("descTache"),
		                tacheDoc.getString("dateDebutTache"),
		                tacheDoc.getString("dateFinTache")
		            );
		            tasks.add(tache);
		        }
		    } else {
		        System.out.println("La liste de tâches est absente ou vide pour la liste : " + nomListe);
		    }

		    return tasks;
		}

	 
	 
	 
 
	 
	
	public boolean isExistingList(String nomList) {
	    
	    FindIterable<Document> iterable = ListeCollection.find(new Document("nomListe", nomList));
	    return iterable.first() != null; // Retourne true si un document est trouvé, sinon false
	}
	
	public void createListe(Liste l) {
		if(!(this.isExistingList(l.getNomListe()))) {
        Document list=new Document("descListe",l.getDescListe())
                .append("nomListe", l.getNomListe());

        List<Document> tachesDoc = new ArrayList<>();
        taches=(ArrayList<Tache>) l.getTaches();
        for (Tache tache : taches) {
            Document tacheDoc = new Document("intitule", tache.getIntitule())
                    .append("categTache", tache.getCategTache())
                    .append("descTache", tache.getDescTache())
                    .append("dateDebutTache", tache.getDateDebutTache())
                    .append("dateFinTache", tache.getDateFinTache());
            tachesDoc.add(tacheDoc);
        }
        list.put("taches", tachesDoc);
        ListeCollection.insertOne(list);
		}
    }
	
	public Liste getListByTaskName(String taskName) {
        // Recherche de la liste contenant la tâche spécifiée
        Document query = new Document("taches.intitule", taskName);
        Document listDocument = ListeCollection.find(query).first();

        // Vérification si la liste a été trouvée
        if (listDocument != null) {
           
            Liste liste = new Liste(
                listDocument.getString("nomListe"),
                listDocument.getString("descListe")
            );
            return liste;
        }

        return null; // Retourne null si la liste n'est pas trouvée
    }
	
	
	public void deleteList(Liste liste) {
		Document list=new Document("descListe",liste.getDescListe())
				.append("nomListe", liste.getNomListe());
		ListeCollection.deleteOne(list);
	}
	
	
	
public void insertTaskToListt(Liste l, Tache task) {
	
	    
	    Document taskDoc = new Document("intitule", task.getIntitule())
	                        .append("categTache", task.getCategTache())
	                        .append("descTache", task.getDescTache())
	                        .append("dateDebutTache", task.getDateDebutTache())
	                        .append("dateFinTache", task.getDateFinTache());

	    // Find the project document by its ID
	    Document query = new Document("nomListe", l.getNomListe());
	    Document ListDoc = ListeCollection.find(query).first();

	    // Check if the project document exists
	    if (ListDoc != null) {
	        // Get the array of tasks from the project document
	        List<Document> taches = (List<Document>) ListDoc.get("taches");

	        // Add the new task document to the array
	        taches.add(taskDoc); 

	        // Update the project document with the modified array of tasks
	        ListeCollection.replaceOne(query, ListDoc);
	        System.out.println("Task inserted successfully into the project.");
	    } 
	     TaskDao t= new TaskDao();
	     t.createtTache(task);
	}

public void insertTaskToListt1(String nom, Tache task) {
    
    Document taskDoc = new Document("intitule", task.getIntitule())
                        .append("categTache", task.getCategTache())
                        .append("descTache", task.getDescTache())
                        .append("dateDebutTache", task.getDateDebutTache())
                        .append("dateFinTache", task.getDateFinTache());

    // Find the project document by its ID
    Document query = new Document("nomListe", nom);
    Document ListDoc = ListeCollection.find(query).first();

    // Check if the project document exists
    if (ListDoc != null) {
        // Get the array of tasks from the project document
        List<Document> taches = (List<Document>) ListDoc.get("taches");

        // Add the new task document to the array
        taches.add(taskDoc); 

        // Update the project document with the modified array of tasks
        ListeCollection.replaceOne(query, ListDoc);
        System.out.println("Task inserted successfully into the project.");
    } 
     TaskDao t= new TaskDao();
     t.createtTache(task);
}


	public void deleteListName(String nom) {
		
		 Bson filter = Filters.eq("nomListe", nom);
		    
		    // Supprimez le document correspondant au filtre
		    DeleteResult result = ListeCollection.deleteOne(filter);
		    
		    // Vérifiez si le document a été supprimé avec succès
		    if (result.getDeletedCount() > 0) {
		        System.out.println("Liste supprimée avec succès.");
		    } else {
		        System.out.println("La liste avec le nom spécifié n'a pas été trouvée.");
		    }
	}

	 public List<Liste> searchListByKeyword(String keyword) {
	        // Créer un filtre pour rechercher les descriptions contenant le mot-clé
	        Bson filter = Filters.regex("descListe", keyword, "i"); // "i" pour une recherche insensible à la casse
	        
	        // Effectuer la recherche dans la collection
	        FindIterable<Document> iterable = ListeCollection.find(filter);
	        
	        // Liste pour stocker les résultats de la recherche
	        List<Liste> resultList = new ArrayList<>();
	        
	        // Parcourir les résultats et les ajouter à la liste de résultats
	        for (Document document : iterable) {
	            Liste liste = new Liste( document.getString("nomListe"), document.getString("descListe"));
	            resultList.add(liste);
	        }
	        
	        return resultList;
	    }
	 
	 public void updateListe1(Liste liste, String nom, String desc) {
		    if (liste != null && liste.getNomListe() != null) {
		        Bson filter = Filters.eq("nomListe", liste.getNomListe());
		        Document update = new Document("$set", new Document("nomListe", nom)
		                                                    .append("descListe", desc));
		        ListeCollection.updateOne(filter, update);
		        System.out.println("List updated successfully.");
		    } else {
		        System.out.println("The list to update is null, its name is null, or the listeCollection is not initialized.");
		    }
		}
	 
	 public void updateListe(String nomListe,String desc, String nouveauNom, String nouvelleDescription) {
		    if (nomListe != null && !nomListe.isEmpty()) {
		        Bson filter = Filters.eq("nomListe", nomListe);
		        Document update = new Document("$set", new Document("nomListe", nouveauNom)
		                                                    .append("descListe", nouvelleDescription));
		        ListeCollection.updateOne(filter, update);
		        System.out.println("List updated successfully.");
		    } else {
		        System.out.println("Le nom de la liste à mettre à jour est null ou vide.");
		    }
		}



	
	 
	
	
	private Bson eq(String string, String nomListe) {
		
		return null;
	}

	
	
	public List<Liste> getAllListe() {
		MongoCursor<Document> cursor=ListeCollection.find().iterator();
		ArrayList<Liste> listes=new ArrayList<Liste>();
		while (cursor.hasNext()) {
			Document result = cursor.next();
			Liste li=new Liste(result.getString("nomListe"),result.getString("descListe"));
	   listes.add(li);
		}
		for (Liste result : listes) {
	        result.afficher();
	    }
		return listes;
	}
	
	public void deleteTaskFromList1(Liste l, String intituleTache) {
	    if (l != null && intituleTache != null) {
	        // Vérifier que la tâche existe dans la liste donnée
	        boolean taskFound = false;
	        for (Tache tc : l.getTaches()) {
	            if (tc.getIntitule().equals(intituleTache)) {
	                taskFound = true;
	                break;
	            }
	        }
	        if (taskFound) {
	            // Supprimer la tâche de la liste
	            TaskDao tk = new TaskDao(); // Assurez-vous d'avoir une instance de TacheDao
	            for (Tache tc : l.getTaches()) {
	                if (tc.getIntitule().equals(intituleTache)) {
	                    tk.deleteTache(tc); // Supprimer la tâche de la base de données
	                    l.getTaches().remove(tc); // Supprimer la tâche de la liste
	                    break; // Sortir de la boucle après avoir trouvé et supprimé la tâche
	                }
	            }
	            // Mettre à jour la liste dans la base de données
	            deleteListName(l.getNomListe());
	            createListe(l);
	            System.out.println("Tâche '" + intituleTache + "' supprimée avec succès de la liste '" + l.getNomListe() + "'.");
	        } else {
	            // Gérer le cas où la tâche n'est pas trouvée dans la liste
	            System.out.println("La tâche avec le nom spécifié n'a pas été trouvée dans la liste.");
	        }
	    } else {
	        // Gérer le cas où la liste ou le nom de la tâche est null
	        System.out.println("La liste ou le nom de la tâche est null. Veuillez fournir une liste valide et un nom de tâche valide.");
	    }
	}
	
	
	
	
	
	
	
	/*public void deleteTaskFromList1(String nomListe, String intituleTache) {
	    if (nomListe != null && intituleTache != null) {
	        // Trouver la liste dans la base de données
	        Liste liste = getListByName(nomListe);
	        
	        if (liste != null) {
	            // Parcourir les tâches de la liste
	            List<Tache> taches = liste.getTaches();
	            for (Tache tache : taches) {
	                if (tache.getIntitule().equals(intituleTache)) {
	                    // Supprimer la tâche de la liste
	                    taches.remove(tache);
	                    
	                    // Mettre à jour la liste dans la base de données
	                    updateList(liste);
	                    
	                    // Supprimer la tâche de la base de données
	                    TacheDao tacheDao = new TacheDao();
	                    tacheDao.deleteTache(tache);
	                    
	                    System.out.println("Tâche '" + intituleTache + "' supprimée avec succès de la liste '" + nomListe + "'.");
	                    return; // Sortir de la méthode après avoir supprimé la tâche
	                }
	            }
	            
	            // Gérer le cas où la tâche n'a pas été trouvée dans la liste
	            System.out.println("La tâche avec le nom spécifié n'a pas été trouvée dans la liste.");
	        } else {
	            // Gérer le cas où la liste n'a pas été trouvée dans la base de données
	            System.out.println("La liste avec le nom spécifié n'a pas été trouvée.");
	        }
	    } else {
	        // Gérer le cas où les noms de liste ou de tâche sont null
	        System.out.println("Le nom de la liste ou le nom de la tâche est null. Veuillez fournir des noms valides.");
	    }
	}   */

	
	public void updateList(Liste liste) {
	    if (liste != null && liste.getNomListe()!= null) {
	        // Créer un filtre pour trouver la liste par son identifiant unique
	        Bson filter = eq("NomListe", liste.getNomListe());

	        // Créer un document avec les nouvelles informations de la liste
	        Document update = new Document("descListe", liste.getDescListe())
	                            .append("nomListe", liste.getNomListe());

	        // Créer une liste de documents pour les tâches
	        List<Document> tachesDoc = new ArrayList<>();
	        for (Tache tache : liste.getTaches()) {
	            Document tacheDoc = new Document("intitule", tache.getIntitule())
	                                    .append("categTache", tache.getCategTache())
	                                    .append("descTache", tache.getDescTache())
	                                    .append("dateDebutTache", tache.getDateDebutTache())
	                                    .append("dateFinTache", tache.getDateFinTache());
	            tachesDoc.add(tacheDoc);
	        }
	        update.append("taches", tachesDoc);

	        // Mettre à jour la liste dans la base de données
	        ListeCollection.updateOne(filter, new Document("$set", update));
	        System.out.println("Liste mise à jour avec succès.");
	    } else {
	        System.out.println("Impossible de mettre à jour la liste. Liste null ou identifiant non spécifié.");
	    }
	}

	///inserer file à une tache 
//	public void addFileToTask(String listeNom, String taskIntitule, Fichiers fichier) {
//	    // Recherche le document de la liste par son nom
//	    Document query = new Document("nomListe", listeNom);
//	    Document listeDoc = ListeCollection.find(query).first();
//
//	    // Vérifie si le document de la liste existe
//	    if (listeDoc != null) {
//	        // Obtenir le tableau de tâches à partir du document de la liste
//	        List<Document> taches = (List<Document>) listeDoc.get("taches");
//
//	        // Parcourir les tâches pour trouver celle avec l'intitulé correspondant
//	        for (Document taskDoc : taches) {
//	            if (taskDoc.getString("intitule").equals(taskIntitule)) {
//	                // Obtenir le tableau de documents supplémentaires de la tâche
//	                List<Document> autresDocuments = (List<Document>) taskDoc.get("autresDocuments");
//	                
//	                // Vérifie si la liste autresDocuments est null et l'initialise si c'est le cas
//	                if (autresDocuments == null) {
//	                    autresDocuments = new ArrayList<>();
//	                    taskDoc.put("autresDocuments", autresDocuments); // Met à jour le document de la tâche avec la nouvelle liste de documents
//	                }
//	                
//	                // Crée un document pour le fichier
//	                Document fileDoc = new Document("titreDoc", fichier.get)
//	                                    .append("dateAjout", fichier.getDateAjout())
//	                                    .append("descDo", fichier.getDescDo())
//	                                    .append("cheminFichier", fichier.getPath());
//	                
//	                // Ajoute le document de fichier à la liste des documents de la tâche
//	                autresDocuments.add(fileDoc);
//
//	                // Mettre à jour le document de la liste avec le tableau de tâches modifié
//	                ListeCollection.replaceOne(query, listeDoc);
//	                System.out.println("File inserted successfully into the task.");
//	                return; // Sort de la méthode une fois que le fichier est inséré
//	            }
//	        }
//	        System.out.println("Task not found in the list.");
//	    } else {
//	        System.out.println("List not found.");
//	    }
//	}

//	public List<Fichiers> getFilesByTask(String nomListe, Tache task) {
//	    TaskDao tk = new TaskDao();
//	    List<Fichiers> files = new ArrayList<>();
//
//	    // Recherche de la liste dans la base de données
//	    Liste liste = getListByName2(nomListe);
//	    System.out.println("liste");
//	    // Vérification si la liste a été trouvée
//	    if (liste != null) {
//	        // Parcourir les tâches de la liste pour trouver celle avec l'intitulé spécifié
//	        for (Tache tache : liste.getTaches()) {
//	            if (tache.getIntitule().equals(task.getIntitule())) {
//	                System.out.println("Documents associés à la tâche :"+tache.getIntitule());
//	                // Récupérer la liste des documents associés à cette tâche
//	                List<Fichiers> autresDocuments = tk.getFilesByTask(task);
//	                
//	                // Vérifier si la liste de documents est null avant de la traiter
//	                if (autresDocuments != null) {
//	                    for (Fichiers f : autresDocuments) {
//	                        System.out.println(f);
//	                    }
//	                    files.addAll(autresDocuments);
//	                }
//	                break; // Sortir de la boucle après avoir trouvé la tâche et ses documents associés
//	            }
//	        }
//	    } else {
//	        System.out.println("La liste avec le nom spécifié n'a pas été trouvée.");
//	    }
//
//	    return files;
//	}
	
//	public List<Fichiers> getFilesByTask1(String nomListe, Tache task) {
//	    TaskDao tk = new TaskDao();
//	    List<Fichiers> files = new ArrayList<>();
//
//	    // Recherche de la liste dans la base de données
//	    Liste liste = getListByName2(nomListe);
//	    System.out.println("liste");
//	    // Vérification si la liste a été trouvée
//	    if (liste != null) {
//	    	Document taskDocument = tk.getTacheCollection().find(Filters.eq("intitule", task.getIntitule())).first();
//		    
//		    if (taskDocument != null) {
//		        List<Document> fileDocuments = (List<Document>) taskDocument.get("autresDocuments");
//		        
//		        if (fileDocuments != null) {
//		            for (Document fileDocument : fileDocuments) {
//		                Fichiers fichier = new Fichiers(
//		                    fileDocument.getString("titreDoc"),
//		                    fileDocument.getString("dateAjout"),
//		                    fileDocument.getString("descDo"),
//		                    fileDocument.getString("path")
//		                );
//		               
//		                System.out.println(fileDocument);
//		                files.add(fichier);
//		            }
//		        } else {
//		            System.out.println("Aucun fichier associé à la tâche '" + task.getIntitule() + "'.");
//		        }
//		    } else {
//		        System.out.println("La tâche '" + task.getIntitule()+ "' n'a pas été trouvée.");
//		    }
//		    
//		    return files;
//		}
//		return files;
//	}
	
	
//	public List<Fichiers> getFilesByTask2(String nomListe, Tache task) {
//	    TaskDao tk = new TaskDao();
//	    List<Fichiers> files = new ArrayList<>();
//
//	    // Recherche de la liste dans la base de données
//	    Liste liste = getListByName2(nomListe);
//	    System.out.println("liste");
//	    // Vérification si la liste a été trouvée
//	    if (liste != null) {
//	        // Parcourir les tâches de la liste pour trouver celle avec l'intitulé spécifié
//	        for (Tache tache : liste.getTaches()) {
//	            if (tache.getIntitule().equals(task.getIntitule())) {
//	                System.out.println("Documents associés à la tâche : " + tache.getIntitule());
//	                // Utilisation de la méthode getFilesByTask(taskname) de TaskDao pour récupérer les fichiers
//	                List<Fichiers> autresDocuments = tk.getFilesByTask(tache);
//	                
//	                // Vérifier si la liste de documents est null avant de la traiter
//	                if (autresDocuments != null) {
//	                    for (Fichiers f : autresDocuments) {
//	                        System.out.println(f);
//	                    }
//	                    files.addAll(autresDocuments);
//	                }
//	                break; // Sortir de la boucle après avoir trouvé la tâche et ses documents associés
//	            }
//	        }
//	    } else {
//	        System.out.println("La liste avec le nom spécifié n'a pas été trouvée.");
//	    }
//
//	    return files;
//	}
//
	public void DeleteTaskFromProject(String listeNom, String taskIntitule) {
	   
	    Document filter = new Document("nomListe", listeNom);
	    
	    // Create an update operation to remove the task from the project's 'taches' array
	    Document update = new Document("$pull", new Document("taches", new Document("intitule", taskIntitule)));
	    
	    // Execute the update operation
	    ListeCollection.updateOne(filter, update);
	    
	    System.out.println("Task deleted successfully from project: " + listeNom);
	}
	
	public Liste getListByName2(String nomListe) {
	    // Vérification que nomListe n'est pas null
	    if (nomListe == null || nomListe.isEmpty()) {
	        System.err.println("Le nom de la liste est vide ou nul.");
	        return null;
	    }
	    
	    // Création du filtre pour rechercher la liste par son nom
	    Bson filter = Filters.eq("nomListe", nomListe);
	    
	    // Recherche de la liste dans la base de données en utilisant le filtre
	    Document listDocument = ListeCollection.find(filter).first();

	    // Vérification si la liste a été trouvée
	    if (listDocument != null) {
	        // Création d'un objet Liste à partir du document trouvé
	        Liste liste = new Liste(listDocument.getString("nomListe"), listDocument.getString("descListe"));

	        // Récupération des tâches associées à cette liste et ajout à l'objet Liste
	        List<Tache> tasks = getTasksByListName(nomListe);
	        liste.setTaches(tasks);

	        return liste;
	    } else {
	        System.out.println("Liste non trouvée dans la base de données.");
	        return null;
	    }
	}

	public void updateTacheListe(String nomListe, String ancienNomTache, String nouveauNomTache, String nouvelleCategorie, String nouvelleDescription, String nouvelleDateDebut, String nouvelleDateFin) {
	  
	    Liste liste = getListByName2(nomListe);
System.out.println(liste);
	   
	    if (liste != null) {
	        // Parcours des tâches de la liste
	        for (Tache tache : liste.getTaches()) {
	            // Vérification si la tâche correspond au nom ancien
	            if (tache.getIntitule().equals(ancienNomTache)) {
	                // Mise à jour des propriétés de la tâche
	                tache.setIntitule(nouveauNomTache);
	                tache.setCategTache(nouvelleCategorie);
	                tache.setDescTache(nouvelleDescription);
	                tache.setDateDebutTache(nouvelleDateDebut);
	                tache.setDateFinTache(nouvelleDateFin);

	                System.out.println("Tâche mise à jour avec succès dans la liste : " + nomListe);
	                return;
	            }
	        }
	       
	        System.out.println("La tâche avec le nom spécifié n'a pas été trouvée dans la liste : " + nomListe);
	    } else {
	        // Message si la liste n'est pas trouvée
	        System.out.println("La liste avec le nom spécifié n'a pas été trouvée dans la base de données : " + nomListe);
	    }
	}

	
	
	


	
	public static void main(String[] args) {
		ListDao l= new ListDao();
		//l.updateListe("dddd","", "orety", "vreoeu");
	l.updateTacheListe("baba", "ferer", "nololo", "null", "null", "null", "null");
	Tache tr=new Tache("ferer", "null", "null", "null", "null");
	
	//l.insertTaskToListt1("baba", tr);
	
	}

	
}
