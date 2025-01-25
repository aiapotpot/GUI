package flashlayout;

import javax.swing.*;
import java.awt.*;

 class reveal extends JFrame {

    public reveal() {
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

        JButton openRevealButton = new JButton("Open Reveal Answer");
        openRevealButton.addActionListener(e -> openRevealAnswerDialog());
        getContentPane().add(openRevealButton, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openSubjectEditor() {
        // Code for the Subject Editor (as before)
    }

    private void openStatisticsWindow() {
        // Code for the Statistics Window (as before)
    }

    private void openRevealAnswerDialog() {
        // Create a JDialog for "Reveal Answer"
        JDialog revealDialog = new JDialog(this, "Reveal Answer", true);
        revealDialog.setSize(350, 200); // Set the size to a rectangle shape
        revealDialog.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Reveal Answer", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font for the header
        headerPanel.add(headerLabel);
        revealDialog.add(headerPanel, BorderLayout.NORTH);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // Center text
        JLabel contentLabel = new JLabel("Reveal Answer? This will not be counted in the correct answers?", JLabel.CENTER);
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Bottom Panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // YES button on the left
        JButton yesButton = new JButton("YES");
        yesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(revealDialog, "You chose YES!");
            revealDialog.dispose(); // Close the dialog after clicking YES
        });
        buttonPanel.add(yesButton);

        // NO button on the right
        JButton noButton = new JButton("NO");
        noButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(revealDialog, "You chose NO!");
            revealDialog.dispose(); // Close the dialog after clicking NO
        });
        buttonPanel.add(noButton);

        // Add the button panel to the content panel
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the content panel to the dialog
        revealDialog.add(contentPanel, BorderLayout.CENTER);

        // Set the dialog to be centered relative to the main window
        revealDialog.setLocationRelativeTo(this);
        revealDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new reveal()); // Instantiate the main frame
    }
}
