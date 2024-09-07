package Controlleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Gestion.*;
import Metiers.Liste;
import presentation.CreatListe;

public class ControlCreateListe {
    private CreatListe c;
    private GestionListe gestion;

    public ControlCreateListe(CreatListe c, GestionListe gestion) {
        this.c = c; // Use the passed CreatListe instance instead of creating a new one
        this.gestion = gestion; // Use the passed GestionListe instance instead of creating a new one
        
        // Ajouter un écouteur d'événements au bouton de création de liste
        c.getCreate().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creerListe();
            }
        }); 
    }

    public void creerListe() { 
        String nom = c.getT1().getText();
        String description = c.getT2().getText();
        
        // Create a new list instance
        Liste liste = new Liste(nom, description);
        
        // Call the method in gestion to create the list
        boolean success = gestion.creerListe(liste);
        
        if (success) {
            System.out.println("Liste ajoutée avec succès.");
        } else {
            System.out.println("Erreur lors de l'ajout de la liste.");
        }
    }

    public CreatListe getC() {
        return c;
    }
    
    public GestionListe getGestion() {
        return gestion;
    }

    public static void main(String[] args) {
        CreatListe c = new CreatListe();
        GestionListe gestion = new GestionListe();
        new ControlCreateListe(c, gestion);
    }
}
