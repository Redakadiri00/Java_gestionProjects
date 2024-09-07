package presentation;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ProjeTache extends JFrame  {

	

	    JPanel PanelPrincipale;
	    JPanel TitrePanel;
	    JPanel buttonPanel;
	    JPanel descriptionPanel;
	    JPanel PdfPanel;
	    JPanel DatePanel;
	    JButton cloturerButton;
	    JButton modifierButton;
	    JButton deleteButton; // Add delete button
	    JLabel Tache;
	    JLabel Description;
	    JLabel PDF;
	    JLabel Date;
	    JTextArea descriptionArea;

	    private JButton importButton;
	    private DefaultListModel<File> fileListModel;

	    public ProjeTache() {
	    	 this.setBackground(Color.black);
			 this.setTitle("Interface Projet ");
		       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setSize(1000, 700);
	        Tache = new JLabel("tache 1");
	        Tache.setFont(new Font("Arial", Font.BOLD, 24));
	        TitrePanel = new JPanel();
	        TitrePanel.add(Tache);

	        Date = new JLabel("date debut/date fin");
	        Date.setFont(new Font("Arial", Font.BOLD, 18));
	        DatePanel = new JPanel();
	        DatePanel.add(Date);

	        Description = new JLabel("Description");
	        descriptionArea = new JTextArea(5, 20);

	        descriptionPanel = new JPanel(new BorderLayout());
	        descriptionPanel.add(Description, BorderLayout.NORTH);
	        descriptionPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

	        PdfPanel = new JPanel(new BorderLayout());
	        PDF = new JLabel("PDF Document");
	        importButton = new JButton("Import PDF"); // Create import PDF button
	        importButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                importDocuments();
	            }
	        });
	        PdfPanel.add(PDF, BorderLayout.WEST); // Place label to the left
	        PdfPanel.add(importButton, BorderLayout.EAST); // Place button to the right

	        fileListModel = new DefaultListModel<>();
	        JList<File> fileList = new JList<>(fileListModel);
	        fileList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); // Allow multiple selection
	        fileList.setCellRenderer(new FileListRenderer());
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
	        JScrollPane scrollPane = new JScrollPane(fileList);
	        PdfPanel.add(scrollPane);

	        // Add delete button
	        deleteButton = new JButton("Delete");
	        deleteButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                deleteSelectedFiles(fileList);
	            }
	        });
	        PdfPanel.add(deleteButton, BorderLayout.SOUTH); // Add delete button to the bottom

	        JPanel combinedPanel = new JPanel(new GridLayout(3, 1)); // Nested panel to hold DatePanel, descriptionPanel, and PdfPanel
	        combinedPanel.add(DatePanel);
	        combinedPanel.add(descriptionPanel);
	        combinedPanel.add(PdfPanel);

	        // Create a panel to hold TitrePanel and buttonPanel
	        JPanel topPanel = new JPanel(new BorderLayout());
	        topPanel.add(TitrePanel, BorderLayout.CENTER);

	        // Add buttonPanel to the right side of topPanel
	        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        cloturerButton = new JButton("Cloturer");
	        modifierButton = new JButton("Modifier");
	        buttonPanel.add(cloturerButton);
	        buttonPanel.add(modifierButton);
	        topPanel.add(buttonPanel, BorderLayout.EAST);

	        PanelPrincipale = new JPanel(new BorderLayout());
	        PanelPrincipale.add(topPanel, BorderLayout.NORTH);
	        PanelPrincipale.add(combinedPanel, BorderLayout.CENTER); // Add combinedPanel instead of descriptionPanel

	        getContentPane().add(PanelPrincipale);
	        setVisible(true);
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

	    private void openDocument(File file) {
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

	    private void deleteSelectedFiles(JList<File> fileList) {
	        int[] indices = fileList.getSelectedIndices();
	        for (int i = indices.length - 1; i >= 0; i--) {
	            fileListModel.remove(indices[i]);
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new ProjeTache();
	            }
	        });
	    }
	}


