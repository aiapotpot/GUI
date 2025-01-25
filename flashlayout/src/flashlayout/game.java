package flashlayout;

import javax.swing.*;
import java.awt.*;

public class game extends JFrame {

    public game() {
        
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

       
        JPanel topSection = new JPanel(new BorderLayout());
        JButton backButton = new JButton("BACK");
        topSection.add(backButton, BorderLayout.WEST);

        JPanel cardPanel = new JPanel(new GridLayout(1, 5, 10, 0)); 
        for (int i = 0; i < 5; i++) {
            cardPanel.add(new JButton(""));
        }
        topSection.add(cardPanel, BorderLayout.CENTER);

        JButton scoreButton = new JButton("50");
        topSection.add(scoreButton, BorderLayout.EAST);

        add(topSection, BorderLayout.NORTH);

        
        JPanel contentSection = new JPanel();
        contentSection.setLayout(new BorderLayout());
        contentSection.setBackground(Color.WHITE); 

        JLabel questionLabel = new JLabel("Q1: LOREM IPSUM?", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contentSection.add(questionLabel, BorderLayout.CENTER);

        add(contentSection, BorderLayout.CENTER);

       
        JPanel bottomSection = new JPanel(new BorderLayout());
        JButton previousButton = new JButton("<");
        bottomSection.add(previousButton, BorderLayout.WEST);

        JButton nextButton = new JButton(">");
        bottomSection.add(nextButton, BorderLayout.EAST);

        JButton actionButton = new JButton("DOLOR SIT AMET");
        bottomSection.add(actionButton, BorderLayout.CENTER);

        add(bottomSection, BorderLayout.SOUTH);

      
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(game::new);
    }
}

