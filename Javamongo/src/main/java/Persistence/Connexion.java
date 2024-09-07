package Persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connexion {


     private static final String DATABASE_NAME = "Gestion_De_Projet";
        private static final String CONNECTION_STRING = "mongodb://localhost:27017/";

        private static MongoClient mongoClient;
        private static MongoDatabase database;

        private Connexion() {}

        public static synchronized MongoDatabase getDatabase() {
            if (database == null) {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                database = mongoClient.getDatabase(DATABASE_NAME);
                System.out.println("Connexion réussie à la base de données : " + DATABASE_NAME);
            }
            return database;
        }

        public static synchronized void close() {
            if (mongoClient != null) {
                mongoClient.close();
                database = null;
            }
        }

        public static void main(String[] args) {
            MongoDatabase database = Connexion.getDatabase();

            if (database != null) {
                System.out.println("Connexion réussie à la base de données : " + Connexion.DATABASE_NAME);

            } else {
                System.out.println("La connexion à la base de données a échoué.");
            }
        }
}