package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controlleur.ControlCreateListe;
import Metiers.*;
import presentation.*;

public class ListTache extends JFrame{
 
	 JButton searchButton;
	 JButton dateButton ;
	 JButton categoryButton ;
	 JButton stateButton;
	 JTextField searchField;
	 CreatListe c=new CreatListe();
	ControlCreateListe l=new ControlCreateListe(c, null);
	
	public ListTache() {
	setTitle("Task Manager");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLayout(new BorderLayout());
    this.c=c;
    this.l=l;
    // Panel contenant la ligne de filtrage
    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    filterPanel.setPreferredSize(new Dimension(800, 50));

    // Label "Filtrer par:"
    JLabel filterLabel = new JLabel("Filtrer par:");
    filterPanel.add(filterLabel);

    // Boutons Date, Catégorie, Etat
    
    JButton dateButton = new JButton("Date");
    JButton categoryButton = new JButton("Catégorie");
    JButton stateButton = new JButton("État");
    
    filterPanel.add(dateButton);
    filterPanel.add(categoryButton);
    filterPanel.add(stateButton);

    // Champ de texte de recherche
    JTextField searchField = new JTextField(20);
    filterPanel.add(searchField);

    // Bouton de recherche
    JButton searchButton = new JButton("Rechercher");
    filterPanel.add(searchButton);

    // Ajout de la ligne de filtrage au conteneur principal
    add(filterPanel, BorderLayout.NORTH);

    // Liste pour afficher les noms de listes et tâches associées
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> taskList = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(taskList);
    add(scrollPane, BorderLayout.CENTER);

    // Bouton "Créer" à droite
    JButton createButton = new JButton("Créer");
    add(createButton, BorderLayout.EAST);

    // ActionListener pour le bouton de recherche
    searchButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    });
    createButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 openCreateListe();
		}
		
	});
    
   

    setVisible(true);
}

	 private void openCreateListe() {
	        // Créer une instance de la page de formulaire
	        CreatListe c=new CreatListe();
	        // Rendre la page de formulaire visible
	        c.setVisible(true);
	    }
	    

public static void main(String[] args) {
    new ListTache();
}


public JButton getSearchButton() {
	return searchButton;
}


public void setSearchButton(JButton searchButton) {
	this.searchButton = searchButton;
}


public JButton getDateButton() {
	return dateButton;
}


public void setDateButton(JButton dateButton) {
	this.dateButton = dateButton;
}


public JButton getCategoryButton() {
	return categoryButton;
}


public void setCategoryButton(JButton categoryButton) {
	this.categoryButton = categoryButton;
}


public JButton getStateButton() {
	return stateButton;
}


public void setStateButton(JButton stateButton) {
	this.stateButton = stateButton;
}
	public String getSearchField() {
		return searchField.getText();
	}

	public void setSearchField(JTextField searchField) {
		this.searchField = searchField;
	}
	
}