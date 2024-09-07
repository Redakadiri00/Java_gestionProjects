package ViewTest;

import javax.swing.*;

import Metiers.*;
import TestController.ControllerListe;

import java.awt.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class VoirTacheList extends JFrame {

    private JLabel titleLabel;
    private JLabel dateDebutLabel;
    private JLabel dateFinLabel;
    private JLabel descrLabel;
    private JTextArea descriptionTextArea;
    private JButton importButton;
    private DefaultListModel<File> fileListModel;
    private JList<File> fileList;
    ControllerListe c;

    public VoirTacheList( ControllerListe c) {
        setTitle("Voir Tâche");
        setSize(1000, 700);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.c=c;
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Titre de la Tâche");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dateDebutLabel = new JLabel("Date de début : ");
        dateFinLabel = new JLabel("Date de fin : ");
        descrLabel = new JLabel("Description : ");
        descriptionTextArea = new JTextArea(5, 10);
        importButton = new JButton("Importer");
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(titleLabel, BorderLayout.WEST);
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(dateDebutLabel);
        textPanel.add(dateFinLabel);
        p1.add(textPanel, BorderLayout.EAST);
        panelPrincipal.add(p1);

        JPanel desPanel = new JPanel(new GridLayout(2, 1));
        desPanel.add(descrLabel);
        desPanel.add(descriptionTextArea);
        panelPrincipal.add(desPanel);

        JPanel buttonPanel1 = new JPanel(new BorderLayout());
        
       
        buttonPanel1.add(importButton, BorderLayout.NORTH);
        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
   
        
         
        
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
        buttonPanel1.add(new JScrollPane(fileList), BorderLayout.CENTER);
        
        JButton delete = new JButton("Supprimer Document");
        delete.addActionListener(e -> deleteSelectedFiles(fileList));
        buttonPanel1.add(delete, BorderLayout.SOUTH);
        panelPrincipal.add(buttonPanel1);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(bottomPanel);

        descriptionTextArea.setBackground(Color.white);

        add(panelPrincipal);
        setVisible(true);
        
//        importButton.addActionListener(e -> importDocuments());
//        fileList.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) { // Double-click
//                    int[] indices = fileList.getSelectedIndices();
//                    for (int index : indices) {
//                        File selectedFile = fileListModel.getElementAt(index);
//                        if (selectedFile != null) {
//                            openDocument(selectedFile);
//                        }
//                    }
//                }
//            }
//        });
        
//        versDocument.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//  
//              c.openModify();
//     
//            }
//        });
  dispose();
        
    }
    
//    private void importDocuments() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setMultiSelectionEnabled(true);
//        int result = fileChooser.showOpenDialog(this);
//     
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File[] selectedFiles = fileChooser.getSelectedFiles();
//            for (File file : selectedFiles) {
//                fileListModel.addElement(file);
//                /*String nomTache=c.getD().getTaskList().getSelectedValue();
//                System.out.println(nomTache);
//                Fichiers fichier = new Fichiers(file.getName(), "", "", file.getPath());*/
//             String  nomT=this.getTitleLabel().getText();
//             int separatorIndex = nomT.indexOf(":");
//
//          // Si le caractère ":" est trouvé
//          if (separatorIndex != -1) {
//              // Extraire la partie de la chaîne après le ":" (en excluant l'espace)
//              String nomTache = nomT.substring(separatorIndex + 1).trim();
//             System.out.println(nomT);
//             //  System.out.println(nomT);
//                c.storeFileInDatabase(file,nomTache);
//                JOptionPane.showMessageDialog(null, "Document ajouté à la tache ", "Alerte", JOptionPane.WARNING_MESSAGE);
//               
//            }
//        }
//        }
//    }
    
    private void deleteSelectedFiles(JList<File> fileList) {
        int[] indices = fileList.getSelectedIndices();
        for (int i = indices.length - 1; i >= 0; i--) {
            fileListModel.remove(indices[i]);
        }
    }

    public void openDocument(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	ControllerListe c=new ControllerListe();
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public JLabel getDateDebutLabel() {
        return dateDebutLabel;
    }

    public JLabel getDateFinLabel() {
        return dateFinLabel;
    }

    public JLabel getDescrLabel() {
        return descrLabel;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JButton getImportButton() {
        return importButton;
    }


    public DefaultListModel<File> getFileListModel() {
        return fileListModel;
    }

    public JList<File> getFileList() {
        return fileList;
    }
    
    
    
   public void updateTaskDetails(Tache selectedTask) {
	   if (selectedTask != null) {
	   this.titleLabel.setText("Titre de la tache:"+selectedTask.getIntitule());
	   this.dateDebutLabel.setText("Date de début:  "+selectedTask.getDateDebutTache());
	   this.dateFinLabel.setText("Date de fin:    "+ selectedTask.getDateFinTache());
	   this.descriptionTextArea.setText(selectedTask.getDescTache());
	   }
   }
  
}