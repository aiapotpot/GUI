package flashlayout;

import javax.swing.*;
import java.awt.*;

public class returnn extends JFrame {

    public returnn() {
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

        JButton openReturnToButton = new JButton("Open Return To Dialog");
        openReturnToButton.addActionListener(e -> openReturnToDialog());
        getContentPane().add(openReturnToButton, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSubjectEditor() {
        // Code for the Subject Editor (as before)
    }

    private void openStatisticsWindow() {
        // Code for the Statistics Window (as before)
    }

    private void openReturnToDialog() {
        // Create a JDialog for "Return To"
        JDialog returnDialog = new JDialog(this, "Return To", true);
        returnDialog.setSize(350, 200); // Set the size to a rectangle shape
        returnDialog.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Return To", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font for the header
        headerPanel.add(headerLabel);
        returnDialog.add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Center text
        JLabel contentLabel = new JLabel("Return To:", JLabel.CENTER);
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Bottom Panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // MENU button on the left
        JButton menuButton = new JButton("MENU");
        menuButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(returnDialog, "You chose MENU!");
            returnDialog.dispose(); // Close the dialog after clicking MENU
        });
        buttonPanel.add(menuButton);

        // SUBJECT button in the center
        JButton subjectButton = new JButton("SUBJECT");
        subjectButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(returnDialog, "You chose SUBJECT!");
            returnDialog.dispose(); // Close the dialog after clicking SUBJECT
        });
        buttonPanel.add(subjectButton);

        // LEVEL button on the right
        JButton levelButton = new JButton("LEVEL");
        levelButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(returnDialog, "You chose LEVEL!");
            returnDialog.dispose(); // Close the dialog after clicking LEVEL
        });
        buttonPanel.add(levelButton);

        // Add the button panel to the content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the content panel to the dialog
        returnDialog.add(contentPanel, BorderLayout.CENTER);

        // Set the dialog to be centered relative to the main window
        returnDialog.setLocationRelativeTo(this);
        returnDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new returnn()); // Instantiate the main frame
    }
}

