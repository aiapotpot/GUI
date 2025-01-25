package flashlayout;

import javax.swing.*;
import java.awt.*;

public class subjectname extends JFrame {

    public subjectname() {
        // Main frame setup
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        getContentPane().setLayout(new BorderLayout());

        JButton openSubjectEditorButton = new JButton("Open Subject Editor");
        openSubjectEditorButton.addActionListener(e -> openSubjectEditor());
        getContentPane().add(openSubjectEditorButton, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSubjectEditor() {
        // Create a pop-up window (JDialog) named 'subjectname' for the Subject Editor
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
        contentPanel.add(subjectTextField, BorderLayout.EAST);

        // Bottom Panel with buttons (Remove, Cancel, Save)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton removeButton = new JButton("REMOVE");
        removeButton.addActionListener(e -> subjectname.dispose()); // Close the pop-up when REMOVE is clicked

        // Adding Remove button to the left of the bottom panel
        buttonPanel.add(removeButton);

        // Cancel button in the center
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(e -> subjectname.dispose()); // Close the pop-up when CANCEL is clicked
        buttonPanel.add(cancelButton);

        // Save button on the right side of the bottom panel
        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> {
            // You can add functionality for saving the data here
            JOptionPane.showMessageDialog(subjectname, "Subject Saved!");
            subjectname.dispose(); // Close the pop-up after saving
        });
        buttonPanel.add(saveButton);

        // Add the button panel to the bottom of the content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add all to the JDialog
        subjectname.getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Make the pop-up window modal and centered relative to the main window
        subjectname.setLocationRelativeTo(this);
        subjectname.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new subjectname()); // Instantiate the main frame
    }
}
