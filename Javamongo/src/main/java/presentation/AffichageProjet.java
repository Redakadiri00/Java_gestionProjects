package presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import Metiers.Project;
import Models.*;

public class AffichageProjet extends JFrame {
    private JLabel titre;
    private JButton button1 , button2, button3, button4; // New button 1
    private JLabel dateDebut;
    private JLabel dateFin;
    private JLabel separatorLabel;
    private JLabel descr;
    private JTextArea decrire;
    public JButton importButton;
    private JButton VersTache;
    private JButton VersSeance;
    private DefaultListModel<File> fileListModel;
    private JList<File> fileList;
    private ListeProjetModel selectedProject;
    private Project projet;
    
    
    
    

    public AffichageProjet(Project projet) {
    	this.projet=projet;
        setTitle("Voir Projet");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        titre = new JLabel("Projet GI1");
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Creating new buttons
        button1 = new JButton("Modifier");
        button2 = new JButton("Supprimer");
        button3 = new JButton("Cloturer");
        button4 = new JButton("Cloner");

        dateDebut = new JLabel("date de Debut:             ");
        dateFin = new JLabel("date de fin:            ");
        separatorLabel = new JLabel("                                                  ");
        descr = new JLabel("Description :");
        decrire = new JTextArea(5, 10);
        decrire.setEditable(false);
        //add = new JButton("Ajouter Document");
        VersTache = new JButton("Voir Tache");
        VersSeance = new JButton("Voir seance");

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(titre, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel for new buttons
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        p1.add(buttonPanel, BorderLayout.NORTH); // Add new buttons to the top
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.add(dateDebut);
        textPanel.add(dateFin);
        p1.add(textPanel, BorderLayout.EAST);
        panelPrincipal.add(p1);
        
        JPanel desPanel = new JPanel(new GridLayout(2, 1));
        desPanel.add(descr);
        desPanel.add(decrire);
        panelPrincipal.add(desPanel);
        
        JPanel buttonPanel1 = new JPanel(new BorderLayout());
        
        // buttone importer
        JButton importButton = new JButton("Importer"); 
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importDocuments();
            }
        });
        
        buttonPanel1.add(importButton, BorderLayout.NORTH);
        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selection
        fileList.setCellRenderer(new FileListRenderer());
        
        JScrollPane documentScrollPane = new JScrollPane(fileList);
        buttonPanel1.add(documentScrollPane, BorderLayout.CENTER);
        
        
        JButton delete = new JButton("supprimer document");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedFiles(fileList);
            }
        });
        buttonPanel1.add(delete, BorderLayout.SOUTH);
        panelPrincipal.add(buttonPanel1);
        
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Bottom panel for Voir Tache and Voir Seance buttons
        bottomPanel.add(VersTache);
        bottomPanel.add(VersSeance);
        panelPrincipal.add(bottomPanel);

        decrire.setBackground(Color.white);
        

        fileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click
                    int[] indices = fileList.getSelectedIndices();
                    for (int index : indices) {
                        File selectedFile = fileListModel.getElementAt(index);
                        if (selectedFile != null) {
                            openDocument(selectedFile);
                        }
                    }
                }
            }
        });

        add(panelPrincipal);
        setVisible(true);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cloneProject();
            }
        });
    }
    
    private void importDocuments() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            // Display the selected documents
            for (File file : selectedFiles) {
                fileListModel.addElement(file);
            }
        }
    }
    
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
    
    public void displayProjectDetails(Project project) {
        // Assuming you have getters in your Project class for retrieving project details
        titre.setText(project.getNomProjet());
        dateDebut.setText("Date de début: " + project.getDateDebutprjt());
        dateFin.setText("Date de fin: " + project.getDateFinprjt());
        decrire.setText(project.getDescPrjt());

        // Additional logic to handle displaying other project details if needed
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
    
    private void cloneProject() {
        // Obtenez les informations du projet actuel
        String titreProjet = titre.getText();
        String dateDebutProjet = dateDebut.getText();
        String dateFinProjet = dateFin.getText();
        String descriptionProjet = decrire.getText();
        
        // Créez une nouvelle instance de votre classe de modèle de projet
        AjoutProjetModel nouveauProjet = new AjoutProjetModel();
        
        // Copiez les informations du projet actuel dans la nouvelle instance
        nouveauProjet.setNomProjet(titreProjet);
        nouveauProjet.setDate_debutProjet(dateDebutProjet);
        nouveauProjet.setDate_finProjet(dateFinProjet);
        nouveauProjet.setDescriptionProjet(descriptionProjet);
        
        // Affichez le nouveau projet ou ajoutez-le à votre système
        // Par exemple, vous pouvez afficher une nouvelle fenêtre avec les détails du nouveau projet
        JOptionPane.showMessageDialog(this, "Projet cloné avec succès !");
    }


    public static void main(String[] args) {
        //new AffichageProjet();
    }

	public JButton getImportButton() {
		
		return importButton ;
	}

	public JLabel getTitre() {
		return titre;
	}

	public void setTitre(JLabel titre) {
		this.titre = titre;
	}

	public JButton getButton1() {
		return button1;
	}

	public void setButton1(JButton button1) {
		this.button1 = button1;
	}

	public JButton getButton2() {
		return button2;
	}

	public void setButton2(JButton button2) {
		this.button2 = button2;
	}

	public JButton getButton3() {
		return button3;
	}

	public void setButton3(JButton button3) {
		this.button3 = button3;
	}

	public JButton getButton4() {
		return button4;
	}

	public void setButton4(JButton button4) {
		this.button4 = button4;
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

	public JButton getVersTache() {
		return VersTache;
	}

	public void setVersTache(JButton versTache) {
		VersTache = versTache;
	}

	public JButton getVersSeance() {
		return VersSeance;
	}

	public void setVersSeance(JButton versSeance) {
		VersSeance = versSeance;
	}
	
	public DefaultListModel<File> getFileListModel() {
		return fileListModel;
	}

	public void setFileListModel(DefaultListModel<File> fileListModel) {
		this.fileListModel = fileListModel;
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
		// TODO Auto-generated method stub
		
	}
}
