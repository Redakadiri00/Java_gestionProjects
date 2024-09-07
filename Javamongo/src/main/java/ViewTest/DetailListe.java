package ViewTest;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Metiers.Liste;
import Metiers.Tache;
import TestController.ControllerListe;

public class DetailListe extends JFrame {

    private JLabel titleLabel;
    private JTextArea descriptionTextArea;
    private JList<String> taskList;
    private JButton importerButton;
    private JButton supprimerButton;
    private JButton returnButton; // Add return button
    private JLabel décrire;
    private JLabel titListe;
    private String selectedList;
    private JButton modifierButton;
    private JPanel buttonPanel;
    private java.util.List<Tache> tasks;
    private ControllerListe c;

    public DetailListe(ControllerListe c) {
        this.c = c;
        this.selectedList = selectedList;
        setTitle("Détails de la Liste");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Fond de la page
        getContentPane().setBackground(new Color(173, 216, 230)); // Bleu ciel

        modifierButton = new JButton("Modifier");
        modifierButton.setPreferredSize(new Dimension(120, 5));
        modifierButton.setBackground(new Color(173, 216, 230));
        modifierButton.setForeground(Color.WHITE);

        // Initialisation des composants
        titleLabel = new JLabel("Titre de la Liste: ");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        descriptionTextArea = new JTextArea(10, 50);
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setBackground(Color.WHITE);

        taskList = new JList<>();
        taskList.setBackground(Color.WHITE);
        taskList.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        titListe = new JLabel("Liste des taches de la liste: ");
        titListe.setHorizontalAlignment(SwingConstants.CENTER);

        décrire = new JLabel("Description: ");
        décrire.setHorizontalAlignment(SwingConstants.CENTER);

        supprimerButton = new JButton("Supprimer");
        supprimerButton.setBackground(new Color(173, 216, 230));
        supprimerButton.setForeground(Color.WHITE);

        importerButton = new JButton("Importer Tache");
        importerButton.setBackground(new Color(173, 216, 230));
        importerButton.setForeground(Color.WHITE);

        returnButton = new JButton("Return");
        returnButton.setBackground(new Color(173, 216, 230));
        returnButton.setForeground(Color.WHITE);

        // Set up the main panel
        JPanel mainPanel = new JPanel(new GridLayout(8, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.setBackground(new Color(173, 216, 230));

        buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(modifierButton, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(décrire);
        mainPanel.add(new JScrollPane(descriptionTextArea));
        mainPanel.add(titListe);
        mainPanel.add(new JScrollPane(taskList));

        JPanel bottomButtonPanel = new JPanel(new FlowLayout());
        bottomButtonPanel.setBackground(new Color(173, 216, 230));
        bottomButtonPanel.add(importerButton);
        bottomButtonPanel.add(supprimerButton);
        bottomButtonPanel.add(returnButton); // Add return button to the panel
        mainPanel.add(bottomButtonPanel);

        add(mainPanel);
        // setVisible(true);

        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList<String> list = (JList<String>) e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    String selectedTaskName = list.getModel().getElementAt(index);
                    c.handleMouseClickTache(selectedTaskName);
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedTaskName = taskList.getModel().getElementAt(selectedIndex);
                    String selectedList = c.getList().getTaskList().getSelectedValue();
                    c.deleteTaskListe(selectedList, selectedTaskName);
                    afficherMessage("Tâche supprimée de la liste");
                    DefaultListModel<String> model = (DefaultListModel<String>) taskList.getModel();
                    model.remove(selectedIndex);
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.openModifyList();
            }
        });

        importerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.openAjouTask(selectedList);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window to return
            }
        });
    }

    public String getSelectedTaskName() {
        return taskList.getSelectedValue();
    }

    public void updateDetails(Liste liste) {
        if (liste != null) { // Vérifie si la liste n'est pas null
            titleLabel.setText("Titre de la liste : " + liste.getNomListe());
            descriptionTextArea.setText(liste.getDescListe());
            List<Tache> tasks = c.updateTasks(liste);
            DefaultListModel<String> taskListModel = new DefaultListModel<>();
            if (tasks != null) { // Vérifie si la liste de tâches n'est pas null
                for (Tache task : tasks) {
                    taskListModel.addElement(task.getIntitule());
                }
            } else {
                taskListModel.addElement("Aucune tâche trouvée");
            }
            taskList.setModel(taskListModel);
        } else {
            titleLabel.setText("Titre de la liste : Liste non disponible");
            descriptionTextArea.setText("Description non disponible");
            DefaultListModel<String> taskListModel = new DefaultListModel<>();
            taskListModel.addElement("Aucune tâche trouvée");
            taskList.setModel(taskListModel);
        }
    }

    public void updateDetails1(Liste liste) {
        titleLabel.setText(liste.getNomListe());
        descriptionTextArea.setText(liste.getDescListe());
        ArrayList<Tache> tasks = (ArrayList<Tache>) liste.getTaches();
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        if (tasks != null) {
            for (Tache task : tasks) {
                taskListModel.addElement(task.getIntitule());
            }
        } else {
            taskListModel.addElement("Aucune tâche trouvée");
        }
        taskList.setModel(taskListModel);
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public JList<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(JList<String> taskList) {
        this.taskList = taskList;
    }

    public JButton getImporterButton() {
        return importerButton;
    }

    public void setImporterButton(JButton importerButton) {
        this.importerButton = importerButton;
    }

    public JButton getSupprimerButton() {
        return supprimerButton;
    }

    public void setSupprimerButton(JButton supprimerButton) {
        this.supprimerButton = supprimerButton;
    }

    public JLabel getDécrire() {
        return décrire;
    }

    public void setDécrire(JLabel décrire) {
        this.décrire = décrire;
    }

    public void setTasks(ArrayList<Tache> tasks) {
        //this.tasks = tasks;
    }

    public ControllerListe getC() {
        return c;
    }

    public void setC(ControllerListe c) {
        this.c = c;
    }

    public void displayTasks1(Liste selectedListe, DetailListe detailListe) {
        ArrayList<Tache> tasks = (ArrayList<Tache>) selectedListe.getTaches();
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        if (tasks != null) {
            for (Tache task : tasks) {
                taskListModel.addElement(task.getIntitule());
            }
            taskList.setModel(taskListModel);
            taskList.setVisibleRowCount(taskListModel.getSize());
        }
        System.out.println("empty");
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        ControllerListe c = new ControllerListe();
    }
}
