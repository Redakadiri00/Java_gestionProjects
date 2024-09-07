package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import javax.swing.*;



public class User extends JFrame {
    JLabel jl = new JLabel("Bienvenue",SwingConstants.CENTER);
    JButton jb = new JButton("Connexion");

  public User() {
    	        super("Connexion à Gmail");
    	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        setSize(400, 300);

    	        // Création d'un panneau principal avec un gestionnaire de disposition BorderLayout
    	        JPanel mainPanel = new JPanel(new BorderLayout());

    	        // Création du texte de bienvenue au centre du nord
    	        JLabel welcomeLabel = new JLabel("Bienvenue sur Gmail", SwingConstants.CENTER);
    	        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

    	        // Création d'un panneau pour le bouton et le logo
    	        JPanel buttonPanel = new JPanel(new BorderLayout());

    	        // Création du bouton de connexion Gmail
    	        JButton gmailButton = new JButton("Connexion Gmail");
    	        buttonPanel.add(gmailButton, BorderLayout.CENTER);

    	        // Chargement de l'image du logo Gmail depuis une URL (vous pouvez utiliser une image locale également)
    	        try {
    	            URL logoUrl = new URL("https://www.google.com/gmail/about/static/images/logo-gmail.png");
    	            ImageIcon gmailIcon = new ImageIcon(logoUrl);
    	            JLabel gmailLogo = new JLabel(gmailIcon);
    	            buttonPanel.add(gmailLogo, BorderLayout.WEST);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }

    	        // Ajout du panneau de bouton et logo au centre du panneau principal
    	        mainPanel.add(buttonPanel, BorderLayout.CENTER);

    	        // Ajout du panneau principal à la fenêtre
    	        add(mainPanel);

    	        setVisible(true);
    	    }

    	    public static void main(String[] args) {
    	        SwingUtilities.invokeLater(() -> new User());
    	    }
    	}


