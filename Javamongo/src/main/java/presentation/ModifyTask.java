package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Persistence.TaskDao;


public class ModifyTask extends JFrame {

    private JLabel nameLabel;
    private JLabel categoryLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JLabel descriptionLabel;

    private JTextField nameField;
    private JTextField categoryField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea descriptionField;

    private JButton saveButton;

    public ModifyTask() {
        initUI();
    }

    private void initUI() {
        setTitle("Modifier Tâche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        nameLabel = new JLabel("Nom:");
        categoryLabel = new JLabel("Catégorie:");
        startDateLabel = new JLabel("Date Début:");
        endDateLabel = new JLabel("Date Fin:");
        descriptionLabel = new JLabel("Description:");

        nameField = new JTextField(20);
        categoryField = new JTextField(20);
        startDateField = new JTextField(20);
        endDateField = new JTextField(20);
        descriptionField = new JTextArea(5, 20);

        saveButton = new JButton("Modifier");

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.WHITE;
        Color textFieldBackgroundColor = new Color(230, 230, 230);
        Color buttonBackgroundColor = new Color(0, 153, 204);
        Color buttonTextColor = Color.WHITE;
        Color panelBackgroundColor = new Color(45, 45, 45);

        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        categoryLabel.setFont(labelFont);
        categoryLabel.setForeground(labelColor);
        startDateLabel.setFont(labelFont);
        startDateLabel.setForeground(labelColor);
        endDateLabel.setFont(labelFont);
        endDateLabel.setForeground(labelColor);
        descriptionLabel.setFont(labelFont);
        descriptionLabel.setForeground(labelColor);

        nameField.setBackground(textFieldBackgroundColor);
        categoryField.setBackground(textFieldBackgroundColor);
        startDateField.setBackground(textFieldBackgroundColor);
        endDateField.setBackground(textFieldBackgroundColor);
        descriptionField.setBackground(textFieldBackgroundColor);

        saveButton.setBackground(buttonBackgroundColor);
        saveButton.setForeground(buttonTextColor);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBackground(panelBackgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionField));
        panel.add(saveButton);

        add(panel);

        addActionListeners();

        setVisible(true);
        
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve the updated task information from the form fields
                String name = getNameText();
                String category = getCategoryText();
                String startDate = getStartDateText();
                String endDate = getEndDateText();
                String description = getDescriptionText();
                
                // Assuming you have an instance of your TaskDao class called 'taskDao'
                TaskDao taskDao = new TaskDao(); // Create an instance of TaskDao
                // Call the update method with the retrieved task information
                //taskDao.update(name, category, description, startDate, endDate);
                
                // Show a message indicating that the task has been updated successfully
                showMessage("Task updated successfully!");
            }
        });
    }

    private void addActionListeners() {
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    // Getters and setters
    public String getNameText() {
        return nameField.getText();
    }

    public void setNameText(String nameText) {
        nameField.setText(nameText);
    }

    public String getCategoryText() {
        return categoryField.getText();
    }

    public void setCategoryText(String categoryText) {
        categoryField.setText(categoryText);
    }

    public String getStartDateText() {
        return startDateField.getText();
    }
    
    public void setStartDateText(String startDateText) {
        startDateField.setText(startDateText);
    }
    
    public String getEndDateText() {
        return endDateField.getText();
    }
    
    public void setEndDateText(String endDateText) {
        endDateField.setText(endDateText);
    }
    
    public String getDescriptionText() {
        return descriptionField.getText();
    }
    
    public void setDescriptionText(String descriptionText) {
        descriptionField.setText(descriptionText);
    }
    
    public static void main(String[] args) {
        new ModifyTask();
    }
    
    public void showMessage(String string) {
        // TODO Auto-generated method stub

    }

	public JButton getSaveButton() {
		return saveButton;
	}
	
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
}