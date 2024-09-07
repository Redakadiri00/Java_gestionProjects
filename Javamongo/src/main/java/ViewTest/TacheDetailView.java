package ViewTest;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import TestController.ProjetControl;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TacheDetailView extends JFrame {

    private JPanel PanelPrincipale;
    private JPanel TitrePanel;
    private JPanel buttonPanel;
    private JPanel descriptionPanel;
    private JPanel PdfPanel;
    private JPanel DatePanel;
    private JButton cloturerButton;
    private JButton modifierButton;
    private JButton deleteButton; // Add delete button
    private JLabel Tache;
    private JLabel Description;
    private JLabel PDF;
    private JLabel Date;
    private JTextArea descriptionArea;
    private JButton importButton;
    private JButton returnButton; // Add return button
    private DefaultListModel<File> fileListModel;
    private ProjetControl pc;

    public TacheDetailView(ProjetControl pc) {
        this.pc = pc;

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
        PdfPanel.add(PDF, BorderLayout.WEST); // Place label to the left
        PdfPanel.add(importButton, BorderLayout.EAST); // Place button to the right

        deleteButton = new JButton("Delete"); // Initialize delete button
        PdfPanel.add(deleteButton, BorderLayout.SOUTH); // Add delete button to the bottom

        JPanel combinedPanel = new JPanel(new GridLayout(3, 1)); // Nested panel to hold DatePanel, descriptionPanel, and PdfPanel
        combinedPanel.add(DatePanel);
        combinedPanel.add(descriptionPanel);
        combinedPanel.add(PdfPanel);

        // Create a panel to hold TitrePanel and buttonPanel
        JPanel topPanel = new JPanel(new BorderLayout());

        // Create the return button
        returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the previous view
                // For example, call a method in ProjetControl to return to the project details view
                // pc.returnToPreviousView();
            }
        });
        topPanel.add(returnButton, BorderLayout.WEST); // Add the return button to the left side

        // Add TitrePanel to the center of topPanel
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

        fileListModel = new DefaultListModel<>();
        JList<File> fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);
        PdfPanel.add(scrollPane);
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

        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                importDocuments();
            }
        });

        // Add delete button functionality
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = fileList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    fileListModel.remove(selectedIndices[i]);
                }
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

    public static void main(String[] args) {
        ProjetControl pc = new ProjetControl();
        new TacheDetailView(pc);
    }
}
