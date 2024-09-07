package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Gestion.*;
import Metiers.*;
import presentation.*;

public class CreatListe extends JFrame {

	JLabel titre= new JLabel("Nouvelle Liste ");
	JLabel l1=new JLabel("Nom Liste :");
	JLabel l2=new JLabel("Description :");
	JTextField t1=new JTextField(15);
	JTextArea t2=new JTextArea(5,10);
	JButton create= new JButton("créer ");
	//ControlCreateListe cl;
	public CreatListe() {
		
		 setTitle("Créer Liste");
	        setSize(1000, 700);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
//this.cl=new ControlCreateListe(null, null);
	        JPanel panelPrincipal = new JPanel();
	        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
           JPanel p1=new JPanel(new FlowLayout());
           titre.setHorizontalAlignment((int) CENTER_ALIGNMENT);
          
           p1.add(titre);
          JPanel p2=new JPanel(new GridLayout(2,15));
           p2.add(l1);
           p2.add(t1);
           JPanel p3=new JPanel(new GridLayout(5,15));
           p3.add(l2);
           p3.add(t2);
           
           JPanel p4=new JPanel(new FlowLayout(FlowLayout.RIGHT));
           p4.add(create);  
           panelPrincipal.add(p1);
           panelPrincipal.add(p2);
           panelPrincipal.add(p3);
           panelPrincipal.add(p4);  
           Font font = titre.getFont(); // Récupération de la police actuelle
           font = font.deriveFont(Font.BOLD, 20);// Modification de la taille de police à 20
           Font font1 = l1.getFont(); // Récupération de la police actuelle
           font1 = font.deriveFont(Font.BOLD, 15);
           titre.setFont(font);
           l1.setFont(font1);
           l2.setFont(font1);
           create.setSize(new Dimension(200,50));
           panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
           this.add(panelPrincipal);
		this.setVisible(true);
	    pack();
	/*    create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creerListe();
            }
        }); */
		
	}
/*	public void creerListe() { 
        String nom = t1.getText();
        String description = t2.getText();
        Liste liste = new Liste(null, nom, description);
        
		cl.getGestion().creerListe(liste);
        
        System.out.println("Licste ajoutée avec succès.");
    }   */
	public static void main(String[] args) {
		new CreatListe();
	}
	public JButton getCreate() {
		return create;
	}
	public void setCreate(JButton create) {
		this.create = create;
	}
	public JLabel getTitre() {
		return titre;
	}
	public void setTitre(JLabel titre) {
		this.titre = titre;
	}
	public JLabel getL1() {
		return l1;
	}
	public void setL1(JLabel l1) {
		this.l1 = l1;
	}
	public JLabel getL2() {
		return l2;
	}
	public void setL2(JLabel l2) {
		this.l2 = l2;
	}
	public JTextField getT1() {
		return t1;
	}
	public void setT1(JTextField t1) {
		this.t1 = t1;
	}
	public JTextArea getT2() {
		return t2;
	}
	public void setT2(JTextArea t2) {
		this.t2 = t2;
	}
	
}