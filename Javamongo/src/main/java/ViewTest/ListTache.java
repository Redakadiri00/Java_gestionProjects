package ViewTest;

import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Metiers.Liste;
import TestController.ControllerListe;
import TestController.ProjetControl;

public class ListTache extends JFrame {

    private JButton searchButton , backButton;
    private JButton categoryButton;
    private JButton createButton;
    private JButton deleteButton;
    private JTextField searchField;
    private JList<String> taskList;
    private JPanel buttonPanel;
    private ProjetControl pc;
    private ControllerListe control;

    public ListTache(ControllerListe control) {
        this.control = control; // Initialiser la référence au contrôleur

        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.setPreferredSize(new Dimension(800, 50));       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel filterLabel = new JLabel("Filtrer par:");
        filterPanel.add(filterLabel);
        categoryButton = new JButton("Afficher");
        filterPanel.add(categoryButton);
        searchField = new JTextField(20);
        filterPanel.add(searchField);        
        backButton=new JButton("return");
        buttonPanel.add(backButton);      
        searchButton = new JButton("Rechercher");
        filterPanel.add(searchButton);
        add(filterPanel, BorderLayout.NORTH);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);
        // Create a new panel for the buttons and add it to the bottom center
        //JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createButton = new JButton("Créer");
        deleteButton = new JButton("Supprimer");
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = searchField.getText().trim();
                control.search(key);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.openCreateListe();
                
            }
        });

        categoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.afficherAll();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedList = taskList.getSelectedValue();
                if (selectedList != null) {
                    control.deleteListe(selectedList);
                    JOptionPane.showMessageDialog(null, "Liste supprimée", "Alerte", JOptionPane.WARNING_MESSAGE);
                    DefaultListModel<String> model = (DefaultListModel<String>) taskList.getModel();
                    model.removeElement(selectedList);
                } else {
                    System.out.println("Veuillez sélectionner un projet à supprimer.");
                }
            }
        });

        taskList.setCellRenderer(new ProjectListCellRenderer());

        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JList<String> list = (JList<String>) e.getSource();
                    int index = list.locationToIndex(e.getPoint());
                    String selectedListName = list.getModel().getElementAt(index);
                    Liste selectedList = control.getListByName(selectedListName);
                    control.handleMouseClick(index);
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new AccueilPage(pc, control).setVisible(true);
              dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Vous devrez passer une référence à un objet ControllerListe ici
        ControllerListe controllerListe = new ControllerListe();
        // new ListTache(controllerListe);
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JButton getCategoryButton() {
        return categoryButton;
    }

    public void setCategoryButton(JButton categoryButton) {
        this.categoryButton = categoryButton;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JList<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(JList<String> taskList) {
        this.taskList = taskList;
    }

    public ControllerListe getControl() {
        return control;
    }

    public void setControl(ControllerListe control) {
        this.control = control;
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

class ProjectListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 18)); // Taille de police 18
        label.setBorder(new EmptyBorder(5, 10, 5, 10)); // Ajout de marges
        return label;
    }
}
