package flashlayout;

import javax.swing.*;
import java.awt.*;

public class popwindow1 extends JFrame { // Fixed class declaration

    public popwindow1() { // Fixed constructor name
        // Main frame setup
        setTitle("Main Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JButton openLevelEditorButton = new JButton("Open Level Editor");
        openLevelEditorButton.addActionListener(e -> openLevelEditor());
        add(openLevelEditorButton, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openLevelEditor() {
        // Sub-window for Level Editor
        JFrame levelEditor = new JFrame("Level Editor");
        levelEditor.setSize(600, 400); // Set size of the sub-window
        levelEditor.setLayout(new BorderLayout());

        // Top section (Level Details)
        JPanel levelDetailsPanel = new JPanel();
        levelDetailsPanel.setBorder(BorderFactory.createTitledBorder("Level Details Section"));
        levelDetailsPanel.setLayout(new GridLayout(3, 2, 5, 5));
        levelDetailsPanel.add(new JLabel("Name:"));
        levelDetailsPanel.add(new JTextField());
        levelDetailsPanel.add(new JLabel("Subject:"));
        levelDetailsPanel.add(new JTextField());
        levelDetailsPanel.add(new JCheckBox("Enable Timer"));
        levelDetailsPanel.add(new JTextField("Time per flashcard:"));

        // Center section (Flashcard Details)
        JPanel flashcardDetailsPanel = new JPanel();
        flashcardDetailsPanel.setBorder(BorderFactory.createTitledBorder("Flashcard Details Section"));
        flashcardDetailsPanel.setLayout(new BorderLayout());

        JPanel questionAnswerPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        questionAnswerPanel.add(new JLabel("Question:"));
        questionAnswerPanel.add(new JTextField());
        questionAnswerPanel.add(new JLabel("Answer:"));
        questionAnswerPanel.add(new JTextField());

        JPanel flashcardActionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flashcardActionPanel.add(new JButton("Q1"));
        flashcardActionPanel.add(new JButton("Q2"));
        flashcardActionPanel.add(new JButton("Q3"));
        flashcardActionPanel.add(new JButton("+"));

        flashcardDetailsPanel.add(questionAnswerPanel, BorderLayout.NORTH);
        flashcardDetailsPanel.add(flashcardActionPanel, BorderLayout.CENTER);

        // Bottom section (Actions)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.add(new JButton("Refresh"));
        bottomPanel.add(new JButton("Save"));

        // Add all sections to the sub-window
        levelEditor.add(levelDetailsPanel, BorderLayout.NORTH);
        levelEditor.add(flashcardDetailsPanel, BorderLayout.CENTER);
        levelEditor.add(bottomPanel, BorderLayout.SOUTH);

        levelEditor.setLocationRelativeTo(this); // Center relative to the main window
        levelEditor.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(popwindow1::new); // Fixed the constructor call to match class name
    }
}

