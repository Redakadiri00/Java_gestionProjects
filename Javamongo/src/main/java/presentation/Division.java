package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;


	public class Division extends JFrame {
	    public Division() {
	        super("Espace enseignant");
	        this.setBackground(Color.black);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(400, 400);

	        // Création d'un panneau principal avec un BoxLayout orienté verticalement
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        

	        // Création du texte centré au centre du nord dans son propre bloc
	        JLabel label = new JLabel("Espace Enseignant ", SwingConstants.CENTER);
	        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        textPanel.add(label);
	        textPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textPanel.getPreferredSize().height)); // Ajout de rigidité verticale
	        mainPanel.add(textPanel);

	        // Création d'un panneau pour chaque bouton dans son propre bloc
	        JPanel buttonPanel1 = new JPanel();
	        JPanel buttonPanel2 = new JPanel();
	        JPanel buttonPanel3 = new JPanel();
	        
	        // Création et ajout des trois boutons dans leurs panneaux respectifs
	        JButton button1 = new JButton("Projet");
	        JButton button2 = new JButton("Liste des taches ");
	        JButton button3 = new JButton("Voir statistiques ");
	        buttonPanel1.add(button1);
	        buttonPanel2.add(button2);
	        buttonPanel3.add(button3);

	        // Ajout des panneaux de boutons au panneau principal
	        buttonPanel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel1.getPreferredSize().height)); // Ajout de rigidité verticale
	        buttonPanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel2.getPreferredSize().height)); // Ajout de rigidité verticale
	        buttonPanel3.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel3.getPreferredSize().height)); // Ajout de rigidité verticale
	        mainPanel.add(buttonPanel1);
	        mainPanel.add(buttonPanel2);
	        mainPanel.add(buttonPanel3);

	        // Ajout du panneau principal à la fenêtre
	        add(mainPanel);

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new Division ());
	    }
	}


