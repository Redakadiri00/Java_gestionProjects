package Javamongo;


import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;


public class Connexion {
    public static void main(String[] args) {
        // MongoDB connection string
        String connectionString = "mongodb://localhost:27017"; // Change this to your MongoDB URI

        // Create MongoClientSettings
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        // Create MongoClient
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            // Connect to the MongoDB database
            MongoDatabase database = mongoClient.getDatabase("Gestion_De_Projet"); // Change this to your database name
            System.out.println("Connected to MongoDB database successfully.");
            
            String collectionName = "test"; // Change this to your collection name

            // Create the collection
            database.createCollection(collectionName);
            System.out.println("Collection created successfully: " + collectionName);
            
            database.getCollection(collectionName).drop();
            System.out.println("Collection droped successfully: " + collectionName);
            
            
            database.createCollection("customers");
            
            MongoCollection<Document> collection = database.getCollection("customers");
            Document document = new Document();
            document.put("name", "Shubham");
            document.put("company", "Baeldung");
            document.put("age", 2);
            collection.insertOne(document);

        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB database: " + e.getMessage());
            System.err.println("Error creating collection: " + e.getMessage());
        }
        
        
        
       
        
        
        
        
    }
}
