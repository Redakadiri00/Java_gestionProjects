package ViewTest;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import java.util.List;
import Metiers.Fichiers;
import Metiers.Project;
import Metiers.Tache;
import Models.*;
import TestController.ProjetControl;

public class AffichageProjetTest extends JFrame {
    private JLabel titre;
    private JButton button1 , button3, returnButton;
    private JLabel dateDebut;
    private JLabel dateFin;
    private JLabel separatorLabel;
    private JLabel descr;
    private JTextArea decrire;
    public JButton importButton;
    private JButton VersTache;
    private JButton VersSeance;
    private ProjetControl pc;
    private Project selectedProjec;
    private JList<File> fileList;
    //private ListeProjetModel selectedProject;
    
    

    public AffichageProjetTest() {
    	
    }
    
    public AffichageProjetTest(Project selectedProjec, ProjetControl pc) {
    	this.pc=pc;
    	this.selectedProjec=selectedProjec;
    	
    	
        setTitle("Voir Projet");
        setSize(1000, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        titre = new JLabel("Projet GI1");
        titre.setFont(new Font("Arial", Font.BOLD, 24));      
        button1 = new JButton("Modifier");
        button3 = new JButton("Cloturer");
        returnButton = new JButton("Return"); 
        dateDebut = new JLabel("date de Debut:             ");
        dateFin = new JLabel("date de fin:            ");
        separatorLabel = new JLabel("                                                  ");
        descr = new JLabel("Description :");
        decrire = new JTextArea(5, 10);
        decrire.setEditable(false);
        decrire.setEditable(false);
        //add = new JButton("Ajouter Document");
        VersTache = new JButton("Voir Tache");
        VersSeance = new JButton("Voir seance");
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(titre, BorderLayout.WEST);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton);// Panel for new buttons
        buttonPanel.add(button1);
        buttonPanel.add(button3);
        p1.add(buttonPanel, BorderLayout.NORTH); 
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
        
        JButton importButton = new JButton("Importer"); 
        buttonPanel1.add(importButton, BorderLayout.NORTH);
     
        List<Fichiers> files = pc.DisplayFile_Of_Projet(selectedProjec.getNomProjet());
        DefaultListModel<File> fileListModel = new DefaultListModel<>();
        for (Fichiers fichier : files) {
            fileListModel.addElement(new File(fichier.getPath())); 
        }
        fileList=new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
        fileList.setCellRenderer(new FileListRenderer()); 
        JScrollPane documentScrollPane = new JScrollPane(fileList);
        buttonPanel1.add(documentScrollPane, BorderLayout.CENTER);    
        
        JButton delete = new JButton("supprimer");
        buttonPanel1.add(delete, BorderLayout.SOUTH);
        panelPrincipal.add(buttonPanel1);   
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
        bottomPanel.add(VersTache);
        bottomPanel.add(VersSeance);
        panelPrincipal.add(bottomPanel);
        decrire.setBackground(Color.white);
        
        add(panelPrincipal);
        setVisible(true);
        
        
        
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File selectedFile = fileList.getSelectedValue();
                
                if (selectedFile != null) {
                    String selectedFilePath = selectedFile.getAbsolutePath();
                    
                    pc.SupprimerFileFromProjet(selectedProjec, selectedFilePath);
                    
                    DefaultListModel<File> model = (DefaultListModel<File>) fileList.getModel();
                    model.removeElement(selectedFile);
                } else {
                    JOptionPane.showMessageDialog(AffichageProjetTest.this, "Veuillez sélectionner un fichier à supprimer.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pc.importDocumentsToProject();  
            	}
            });
        
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

        
        
        //modifier button
        button1.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                pc.AfficherModifierForm();
            }
        });
        
        VersTache.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                String selectedProjectName = pc.getFp().getProjectList().getSelectedValue();
                if (selectedProjectName != null) {
                     Project selectedProjec = pc.getProjectByName(selectedProjectName);
                     new TacheDeProjetView(selectedProjec,pc).setVisible(true);
                    //voirTacheDeProjet.setVisible(true);
                } else {
                   // JOptionPane.showMessageDialog(FrameprojetTest.this, "Veuillez sélectionner un projet.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
                dispose();
            }
        });
        
        VersSeance.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
                String selectedProjectName = pc.getFp().getProjectList().getSelectedValue();
                if (selectedProjectName != null) {
                    Project selectedProjec = pc.getProjectByName(selectedProjectName);
                     new SeanceDeProjetView(selectedProjec,pc);
                    //voirTacheDeProjet.setVisible(true);
                } else {
                   // JOptionPane.showMessageDialog(FrameprojetTest.this, "Veuillez sélectionner un projet.", "Avertissement", JOptionPane.WARNING_MESSAGE);
                }
                dispose();
            }
		});
        
        
        
        
        
        
        
        
        
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String SelectedProjectName = selectedProjec.getNomProjet();
                pc.cloturerProject(SelectedProjectName);
            }
        });
        
        returnButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				 dispose(); 
				 pc.RetourFrameProjet();
				
			}
		});
        
        
        
   }
    
    private void cloturerProject() {
        String currentTitle = titre.getText();
        if (!currentTitle.contains("(cloturé)")) {
            titre.setText(currentTitle + " (cloturé)");
        }
    }
    


    public void openDocument(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    public class FileListRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            File file = (File) value;
            label.setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
            label.setText(file.getName());
            return label;
        }
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


	public JButton getButton3() {
		return button3;
	}

	public void setButton3(JButton button3) {
		this.button3 = button3;
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
		
	}
	
	public static void main(String[] args) {
    	ProjetControl pc = new ProjetControl();
        //new AffichageProjetTest(pc);
    	
    }
	
}
