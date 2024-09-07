package presentation;

import java.awt.*;
import javax.swing.*;

public class CreateSeance extends JFrame {
    private JLabel titre = new JLabel("Nouvelle séance ");
    private JLabel l1 = new JLabel("Nom Séance : ");
    private JTextField t1 = new JTextField(20);
    private JLabel l2 = new JLabel("Description :");
    private JTextArea t2 = new JTextArea(5, 20);
    private JLabel j3 = new JLabel("Date Debut ");
    private JTextField t3 = new JTextField(20);
    private JLabel j4 = new JLabel("Date Fin ");
    private JTextField t4 = new JTextField(20);
    private JLabel l5 = new JLabel("Note :");
    private JTextArea t5 = new JTextArea(5, 20);
    private JButton add = new JButton("Ajouter séance");

    public CreateSeance() {
        setTitle("Nouvelle Séance");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p1.add(titre);
        
        JPanel p2 = new JPanel(new GridLayout(2, 1, 5, 5));
        p2.add(l1);
        p2.add(t1);
        
        JPanel p3 = new JPanel(new GridLayout(2, 1, 5, 5));
        p3.add(l2);
        p3.add(new JScrollPane(t2)); // Utilisation d'un JScrollPane pour la JTextArea
        
        JPanel p4 = new JPanel(new GridLayout(2, 1, 5, 5));
        p4.add(j3);
        p4.add(t3);
        
        JPanel p5 = new JPanel(new GridLayout(2, 1, 5, 5));
        p5.add(j4);
        p5.add(t4);
        
        JPanel p6 = new JPanel(new GridLayout(2, 1, 5, 5));
        p6.add(l5);
        p6.add(new JScrollPane(t5)); // Utilisation d'un JScrollPane pour la JTextArea
        
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p7.add(add);

        Font font = l1.getFont(); // Récupération de la police actuelle
        font = font.deriveFont(Font.BOLD, 25);
        titre.setFont(font);
        
        mainPanel.add(p1);
        mainPanel.add(p2);
        mainPanel.add(p3);
        mainPanel.add(p4);
        mainPanel.add(p5);
        mainPanel.add(p6);
        mainPanel.add(p7);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateSeance());
    }
}
