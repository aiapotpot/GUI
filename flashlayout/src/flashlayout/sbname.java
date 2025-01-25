package flashlayout;

import javax.swing.*;
import java.awt.*;

public class sbname extends JFrame {

    public sbname() {
        // Main frame setup
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setLayout(new BorderLayout());

        JButton openSubjectEditorButton = new JButton("Open Subject Editor");
        openSubjectEditorButton.addActionListener(e -> openSubjectEditor());
        getContentPane().add(openSubjectEditorButton, BorderLayout.CENTER);

        JButton openStatisticsButton = new JButton("Open Statistics");
        openStatisticsButton.addActionListener(e -> openStatisticsWindow());
        getContentPane().add(openStatisticsButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSubjectEditor() {
        // Create a new frame (JFrame) for the Subject Editor
        JFrame subjectname = new JFrame("Subject Editor (Subwindow)");
        subjectname.setSize(350, 200); // Small rectangle shape
        subjectname.getContentPane().setLayout(new BorderLayout());

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Subject Name at the top
        JLabel subjectLabel = new JLabel("Subject Name:", SwingConstants.CENTER);
        contentPanel.add(subjectLabel, BorderLayout.NORTH);

        // Text area with "HELLO WORLD" in the center
        JTextField subjectTextField = new JTextField("HELLO WORLD", 20);
        contentPanel.add(subjectTextField, BorderLayout.CENTER);  // Change from EAST to CENTER to center it

        // Bottom Panel with buttons (Remove, Cancel, Save)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));  // Center buttons

        JButton removeButton = new JButton("REMOVE");
        removeButton.addActionListener(e -> subjectname.dispose()); // Close the window when REMOVE is clicked

        // Adding Remove button to the bottom panel
        buttonPanel.add(removeButton);

        // Cancel button in the center
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(e -> subjectname.dispose()); // Close the window when CANCEL is clicked
        buttonPanel.add(cancelButton);

        // Save button on the right side of the bottom panel
        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> {
            // You can add functionality for saving the data here
            JOptionPane.showMessageDialog(subjectname, "Subject Saved!");
            subjectname.dispose(); // Close the window after saving
        });
        buttonPanel.add(saveButton);

        // Add the button panel to the bottom of the content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add all to the frame
        subjectname.getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Center the frame relative to the main window
        subjectname.setLocationRelativeTo(this);
        subjectname.setVisible(true);
    }

    private void openStatisticsWindow() {
        // Create a new square frame for the Statistics window
        JFrame statisticsFrame = new JFrame("Statistics");
        statisticsFrame.setSize(300, 300); // Square size
        statisticsFrame.getContentPane().setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("STATISTICS:", SwingConstants.CENTER);
        headerPanel.add(headerLabel);
        statisticsFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(4, 2, 10, 10)); // Grid layout for labels and text fields

        // Label and Text Field for "Games Played"
        JLabel gamesPlayedLabel = new JLabel("Games Played:");
        JTextField gamesPlayedField = new JTextField(10);
        contentPanel.add(gamesPlayedLabel);
        contentPanel.add(gamesPlayedField);

        // Label and Text Field for "Favorite Subject"
        JLabel favoriteSubjectLabel = new JLabel("Favorite Subject:");
        JTextField favoriteSubjectField = new JTextField(10);
        contentPanel.add(favoriteSubjectLabel);
        contentPanel.add(favoriteSubjectField);

        // Label and Text Field for "Subject with most mistakes"
        JLabel mistakesSubjectLabel = new JLabel("Subject with most mistakes:");
        mistakesSubjectLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        JTextField mistakesSubjectField = new JTextField(10);
        contentPanel.add(mistakesSubjectLabel);
        contentPanel.add(mistakesSubjectField);

        // Label and Text Field for "Subject with most correct answers"
        JLabel correctAnswersSubjectLabel = new JLabel("Subject with most correct answers:");
        correctAnswersSubjectLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        JTextField correctAnswersSubjectField = new JTextField(10);
        contentPanel.add(correctAnswersSubjectLabel);
        contentPanel.add(correctAnswersSubjectField);

        // Add content panel to the frame
        statisticsFrame.getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Make the statistics window visible
        statisticsFrame.setLocationRelativeTo(this);
        statisticsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new sbname()); // Instantiate the main frame
    }
}
