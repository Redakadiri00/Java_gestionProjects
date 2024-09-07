package ViewTest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import TestController.ControllerListe;
import TestController.ProjetControl;

public class AccueilPage extends JFrame {
		private ProjetControl pc;
		private ControllerListe contr;
		
	    public AccueilPage(ProjetControl pc, ControllerListe contr) {
	        super("Espace enseignant");
	        this.pc= pc;
	        this.contr=contr;
	        
	        this.setBackground(Color.black);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(1000, 500);
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	        JLabel label = new JLabel("Espace Enseignant ", SwingConstants.CENTER);
	        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        textPanel.add(label);
	        textPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, textPanel.getPreferredSize().height));
	        mainPanel.add(textPanel);
	        JPanel buttonPanel1 = new JPanel();
	        JPanel buttonPanel2 = new JPanel();
	        JPanel buttonPanel3 = new JPanel();
	        JButton button1 = new JButton("Projet");
	        JButton button2 = new JButton("Liste des taches ");
	        buttonPanel1.add(button1);
	        buttonPanel2.add(button2);
	        buttonPanel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel1.getPreferredSize().height));
	        buttonPanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, buttonPanel2.getPreferredSize().height)); 
	        mainPanel.add(buttonPanel1);
	        mainPanel.add(buttonPanel2);
	        add(mainPanel);

	        setVisible(true);
	        //dispose();
	        
	        button1.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					pc.AllerVersFrameProjet();
										
				}
			});
	        
	        button2.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					contr.AllerVersListTache(contr);
										
				}
			});
	        
	        
	    }

	    public static void main(String[] args) {
	    	ProjetControl pc = new ProjetControl();
	    }
	    
	    
	}
