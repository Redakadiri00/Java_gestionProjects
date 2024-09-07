package ViewTest;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import Metiers.Fichiers;
import Metiers.Project;
import Metiers.Tache;
import TestController.ProjetControl;

public class AffichageTaskView extends JFrame {
    private JLabel titre;
    private JLabel dateDebut;
    private JLabel dateFin;
    private JLabel separatorLabel;
    private JLabel descr;
    private JTextArea decrire;
    public JButton importButton;
    private Tache task;
    private ProjetControl pc;
    private Project selectedProjec;
    private JList<File> fileList;
    private JButton returnButton; // Add return button

    public AffichageTaskView() {}

    public AffichageTaskView(Project selectedProjec, Tache task, ProjetControl pc) {
        this.pc = pc;
        this.task = task;
        this.selectedProjec = new Project();

        setTitle("Detail Tache");
        setSize(1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        returnButton = new JButton("Return");
        titre = new JLabel("Projet GI1");
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Panel for the return button
        returnPanel.add(returnButton);
        topPanel.add(returnPanel, BorderLayout.NORTH); // Add return panel at the top

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the title
        titlePanel.add(titre);
        topPanel.add(titlePanel, BorderLayout.CENTER); // Add title panel at the center

        panelPrincipal.add(topPanel);
        dateDebut = new JLabel("date de Debut:             ");
        dateFin = new JLabel("date de fin:            ");
        separatorLabel = new JLabel("                                                  ");
        descr = new JLabel("Description :");
        decrire = new JTextArea(5, 10);
        decrire.setEditable(false);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(dateDebut);
        textPanel.add(dateFin);
        panelPrincipal.add(textPanel);

        JPanel desPanel = new JPanel(new GridLayout(2, 1));
        desPanel.add(descr);
        desPanel.add(decrire);
        panelPrincipal.add(desPanel);

        JPanel buttonPanel1 = new JPanel(new BorderLayout());
        importButton = new JButton("Importer");
        buttonPanel1.add(importButton, BorderLayout.NORTH);

        fileList = new JList<>();
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selection
        fileList.setCellRenderer(new FileListRenderer());
        JScrollPane documentScrollPane = new JScrollPane(fileList);
        buttonPanel1.add(documentScrollPane, BorderLayout.CENTER);

        JButton delete = new JButton("supprimer");
        buttonPanel1.add(delete, BorderLayout.SOUTH);
        panelPrincipal.add(buttonPanel1);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Bottom panel for Voir Tache and Voir Seance buttons
        panelPrincipal.add(bottomPanel);

        decrire.setBackground(Color.white);

        add(panelPrincipal);
        setVisible(true);

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importDocumentsToTask();
            }
        });
        
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	pc.AllerVersTache(selectedProjec, pc);
            	dispose();
            }
        });
        
        
    }

    private void importDocumentsToTask() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for (File file : selectedFiles) {
                storeFileInTask(file);
            }
            JOptionPane.showMessageDialog(this, "Files imported successfully!");
        }
    }

    private void storeFileInTask(File file) {
        String selectedProjectName = pc.getFp().getProjectList().getSelectedValue();
        Project selectedProject = pc.getProjectByName(selectedProjectName);
        Fichiers fichiers = new Fichiers();
        fichiers.setPath(file.getAbsolutePath());
        pc.addDocToFile_Of_Project(selectedProject, task, fichiers);
    }

    public void openDocument(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class FileListRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            File file = (File) value;
            label.setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
            label.setText(file.getName());
            return label;
        }
    }

    public static void main(String[] args) {
        ProjetControl pc = new ProjetControl();
        //new AffichageProjetTest(pc);
    }
    
    public JButton getImportButton() {
        return importButton;
    }

    public JLabel getTitre() {
        return titre;
    }

    public void setTitre(JLabel titre) {
        this.titre = titre;
    }

    public JLabel getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(JLabel dateDebut) {
        this.dateDebut = dateDebut;
    }

    public JLabel getDateFin() {
        return dateFin;
    }

    public void setDateFin(JLabel dateFin) {
        this.dateFin = dateFin;
    }

    public JLabel getSeparatorLabel() {
        return separatorLabel;
    }

    public void setSeparatorLabel(JLabel separatorLabel) {
        this.separatorLabel = separatorLabel;
    }

    public JLabel getDescr() {
        return descr;
    }

    public void setDescr(JLabel descr) {
        this.descr = descr;
    }

    public JTextArea getDecrire() {
        return decrire;
    }

    public void setDecrire(JTextArea decrire) {
        this.decrire = decrire;
    }

    public JList<File> getFileList() {
        return fileList;
    }

    public void setFileList(JList<File> fileList) {
        this.fileList = fileList;
    }

    public void setImportButton(JButton importButton) {
        this.importButton = importButton;
    }

    public void showMessage(String string) {
        JOptionPane.showMessageDialog(this, string);
    }
}
