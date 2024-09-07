package ViewTest;

import javax.swing.*;
import Metiers.Project;
import Metiers.Seance;
import TestController.ProjetControl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SeanceDeProjetView extends JFrame {

    private JLabel titleLabel;
    private JList<String> listSeances;
    private JButton ajouterListeButton, supprimerButton, returnButton;
    private DefaultListModel<String> listModel;
    private ProjetControl control;
    private Project selectedProjec;
    private Seance selectedseance; 

    public SeanceDeProjetView(Project selectedProjec, ProjetControl control) {
        this.control = control;
        this.selectedProjec = selectedProjec;

        setTitle("Liste des séances");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(173, 216, 230)); 
        titleLabel = new JLabel("Liste des séances", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));    
        returnButton = new JButton("Return");
        returnButton.setFont(new Font("Arial", Font.PLAIN, 18));
        returnButton.setBackground(new Color(0, 153, 204));
        returnButton.setForeground(Color.WHITE);
        returnButton.setPreferredSize(new Dimension(100, 40));

        List<Seance> seances = control.afficherSeances(selectedProjec.getNomProjet());
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        for (Seance seance : seances) {
            taskListModel.addElement(String.valueOf(seance.getNumSeance()));
        }

        listSeances = new JList<>(taskListModel);
        JScrollPane taskScrollPane = new JScrollPane(listSeances);
        taskScrollPane.setPreferredSize(new Dimension(800, 500));

        ajouterListeButton = new JButton("Ajouter");
        ajouterListeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        ajouterListeButton.setBackground(new Color(0, 153, 204));
        ajouterListeButton.setForeground(Color.WHITE);
        ajouterListeButton.setPreferredSize(new Dimension(150, 40));

        supprimerButton = new JButton("Supprimer");
        supprimerButton.setFont(new Font("Arial", Font.PLAIN, 18));
        supprimerButton.setBackground(new Color(0, 153, 204));
        supprimerButton.setForeground(Color.WHITE);
        supprimerButton.setPreferredSize(new Dimension(150, 40));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(173, 216, 230));
        topPanel.add(returnButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(173, 216, 230));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(listSeances), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(173, 216, 230));
        buttonPanel.add(ajouterListeButton);
        buttonPanel.add(supprimerButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedSeanceIndex = listSeances.getSelectedIndex();
                if (selectedSeanceIndex != -1) {
                    String selectedSeanceNum = listSeances.getSelectedValue();
                    int seanceNum = Integer.parseInt(selectedSeanceNum);
                    try {
                        control.DeleteSeanceDeProjet(selectedProjec.getNomProjet(), seanceNum);
                        refreshSeanceList(selectedProjec);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(SeanceDeProjetView.this, "Error deleting seance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SeanceDeProjetView.this, "Veuillez sélectionner une séance à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        listSeances.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedSeanceIndex = listSeances.getSelectedIndex();
                    if (selectedSeanceIndex != -1) {
                        String selectedSeanceNum = listSeances.getSelectedValue();
                        int seanceNum = Integer.parseInt(selectedSeanceNum);
                        Seance seance = control.DisplaySeanceDetail(selectedProjec.getNomProjet(), seanceNum);
                        new DetailSeanceView(selectedProjec, seance, control); // Open DetailSeance view
                    }
                }
            }
        });
        
        
        returnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//control.RetourProjetdetail(selectedProjec);
				control.displayProjectDetails(selectedProjec);
				dispose();
			}
		});
        
        
        
}
    
    private void refreshSeanceList(Project selectedProjectName) {
        DefaultListModel<String> seanceListModel = new DefaultListModel<>();
        List<Seance> seances = control.afficherSeances(selectedProjectName.getNomProjet());
        for (Seance seance : seances) {
            seanceListModel.addElement(String.valueOf(seance.getNumSeance()));
        }
        listSeances.setModel(seanceListModel);
    }

    public static void main(String[] args) {
        ProjetControl pc = new ProjetControl();
        
    }
}
