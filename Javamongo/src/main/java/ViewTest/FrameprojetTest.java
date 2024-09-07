package ViewTest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Gestion.GestionProjet;
import Metiers.Project;
import Models.*;
import TestController.ControllerListe;
import TestController.ProjetControl;

public class FrameprojetTest extends JFrame {
    private JList<String> projectList;
    private ProjetControl pc;
    private ControllerListe contr;
    private ListeProjetModel lpm;
    private JButton ajouterButton;
    private JButton rechercher;
    private JTextField searchField;
    private JButton button6;

    public FrameprojetTest() {}

    public FrameprojetTest(ProjetControl pc) {
        this.projectList = new JList<String>();
        this.pc = pc;
        setBackground(Color.black);
        setTitle("Interface Projet");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        this.setLayout(new BorderLayout());
        JButton returnButton = new JButton("Return");
        returnButton.setPreferredSize(new Dimension(100, 30));
        JPanel returnButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnButtonPanel.add(returnButton);
        returnButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30)); 
        JButton rechercher = new JButton("Rechercher");
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        searchPanel.add(searchField);
        searchPanel.add(rechercher);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(returnButtonPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);
        JPanel toolbar = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel p = new JLabel("Encadrement:");
        JButton button1 = new JButton("PFE");
        JButton button2 = new JButton("PFA");
        JButton button3 = new JButton("THESE");
        JLabel p1 = new JLabel("Enseignement:");
        JButton button4 = new JButton("Cours");
        JButton button5 = new JButton("Examen");
        JButton button6 = new JButton("All projects");
        leftPanel.add(p);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button1);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button2);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button3);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(p1);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button4);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button5);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(button6);

        toolbar.add(leftPanel, BorderLayout.WEST);

        Dimension buttonSize = new Dimension(200, 50); 
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);
        button6.setPreferredSize(buttonSize);

        add(toolbar, BorderLayout.WEST);
        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(projectList);
        add(scrollPane, BorderLayout.CENTER);

        toolbar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ajouterButton = new JButton("Ajouter");
        JButton supprimerButton = new JButton("Supprimer");
        bottomPanel.add(ajouterButton);
        bottomPanel.add(supprimerButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.AfficherFormulaire();
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.filterAction("pfe");
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.filterAction("pfa");
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.filterAction("these");
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.filterAction("cours");
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.filterAction("examen");
            }
        });

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.ContorlReadAllProjetcts();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProject = projectList.getSelectedValue();

                if (selectedProject != null) {
                    pc.DeleteProjet(selectedProject);
                    DefaultListModel<String> model = (DefaultListModel<String>) projectList.getModel();
                    model.removeElement(selectedProject);
                } else {
                    JOptionPane.showMessageDialog(FrameprojetTest.this, "Veuillez sélectionner un projet à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        rechercher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<String> listModel = new DefaultListModel<>();
                String keyword = searchField.getText().trim();
                if (!keyword.isEmpty()) {
                    List<Project> resultList = pc.SearchProjectByKeyword(keyword);

                    if (resultList != null && !resultList.isEmpty()) {
                        for (Project prjt : resultList) {
                            listModel.addElement(prjt.getNomProjet());
                        }
                    } else {
                        JOptionPane.showMessageDialog(FrameprojetTest.this, "Aucun résultat trouvé.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(FrameprojetTest.this, "Veuillez saisir un mot-clé de recherche.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
                projectList.setModel(listModel);
            }
        });

        projectList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList<String> list = (JList<String>) e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    String selectedProjectName = list.getModel().getElementAt(index);
                    Project selectedProject = pc.getProjectByName(selectedProjectName);
                    pc.displayProjectDetails(selectedProject);
                    dispose();
                }
            }
        });
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose(); 
            	new AccueilPage(pc, contr);
                // Close the current window to return to the previous screen
            }
        });
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JList<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(JList<String> projectList) {
        this.projectList = projectList;
    }

    public static void main(String[] args) {
        ProjetControl pc = new ProjetControl();
        //new FrameprojetTest(pc);
    }
}
