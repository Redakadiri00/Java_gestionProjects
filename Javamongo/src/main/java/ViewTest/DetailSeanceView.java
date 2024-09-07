package ViewTest;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Metiers.Project;
import Metiers.Seance;
import TestController.ProjetControl;

public class DetailSeanceView extends JFrame {
    private JLabel titre;
    private JLabel dateSeance;
    private JLabel heureDebut;
    private JLabel heureFin;
    private JLabel separatorLabel;
    private JLabel descr;
    private JTextArea decrire;
    private JButton add;
    private JLabel note;
    private JTextArea noter;
    private JButton mod;
    private JButton sup;
    private DefaultListModel<String> documentListModel;
    private JList<String> documentList;
    private Project SelectedProjec;
    private Seance seance;
    private ProjetControl pc;

    public DetailSeanceView(Project SelectedProjec, Seance seance, ProjetControl pc) {
    	this.pc=pc;
    	this.SelectedProjec=SelectedProjec;
    	this.seance=seance;
    	
        setTitle("Voir Seance");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        titre = new JLabel("Seance 1");
        dateSeance = new JLabel("date Seance:             ");
        heureDebut = new JLabel("heure de debut:            ");
        heureFin = new JLabel("heure de fin:            ");
        separatorLabel = new JLabel("                                                  ");
        descr = new JLabel("Description :");
        decrire = new JTextArea(5, 10);
        add = new JButton("Ajouter Document");
        note = new JLabel("Note  :");
        noter = new JTextArea(10, 5);
        mod = new JButton("modifier");
        sup = new JButton("Supprimer");

        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(titre, BorderLayout.WEST);
        JPanel textPanel = new JPanel(new GridLayout(3, 1));
        textPanel.add(dateSeance);
        textPanel.add(heureDebut);
        textPanel.add(heureFin);
        p1.add(textPanel, BorderLayout.EAST);
        panelPrincipal.add(p1);
        
        JPanel desPanel = new JPanel(new GridLayout(2, 1));
        desPanel.add(descr);
        desPanel.add(decrire);
        panelPrincipal.add(desPanel);
        
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton importButton = new JButton("Importer");
        buttonPanel.add(importButton, BorderLayout.NORTH);
        documentListModel = new DefaultListModel<>();
        documentList = new JList<>(documentListModel);
        JScrollPane documentScrollPane = new JScrollPane(documentList);
        buttonPanel.add(documentScrollPane, BorderLayout.CENTER);
        JButton delete = new JButton("supprimer document");
        buttonPanel.add(delete, BorderLayout.SOUTH);
        panelPrincipal.add(buttonPanel);

        JPanel notePanel = new JPanel(new BorderLayout());
        notePanel.add(note, BorderLayout.NORTH);
        notePanel.add(noter, BorderLayout.CENTER);
        panelPrincipal.add(notePanel);

        JPanel bottomRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomRightPanel.add(mod);
        bottomRightPanel.add(sup);
        panelPrincipal.add(bottomRightPanel);

        decrire.setBackground(Color.GRAY);
        noter.setBackground(Color.GRAY);
        documentList.setBackground(Color.GRAY);

        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                int result = fileChooser.showOpenDialog(DetailSeanceView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    for (File file : selectedFiles) {
                        documentListModel.addElement(file.getName());
                    }
                }
            }
        });

        

        documentList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = documentList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedFileName = documentListModel.getElementAt(index);
                        if (selectedFileName != null) {
                            openDocument(selectedFileName);
                        }
                    }
                }
            }
        });

        add(panelPrincipal);
        //setVisible(true);
    }

    public JLabel getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(JLabel dateSeance) {
		this.dateSeance = dateSeance;
	}

	public JLabel getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(JLabel heureDebut) {
		this.heureDebut = heureDebut;
	}

	public JLabel getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(JLabel heureFin) {
		this.heureFin = heureFin;
	}

	public JTextArea getDecrire() {
		return decrire;
	}

	public void setDecrire(JTextArea decrire) {
		this.decrire = decrire;
	}

	public JLabel getNote() {
		return note;
	}

	public void setNote(JLabel note) {
		this.note = note;
	}

	private void openDocument(String fileName) {
        File selectedFile = new File(System.getProperty("user.dir") + File.separator + fileName);
        if (selectedFile.exists() && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
    	ProjetControl pc = new ProjetControl();
        //new DetailSeanceView();
    }

	public JLabel getTitre() {
		return titre;
	}

	public void setTitre(JLabel titre) {
		this.titre = titre;
	}
}
