package flashlayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class results extends JFrame {

    public results() {
        
        setTitle("Results");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        getContentPane().setLayout(new BorderLayout());

        
        JPanel topSection = new JPanel(new GridLayout(2, 4, 10, 10));
        topSection.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        topSection.add(new JLabel("Subject: ", JLabel.RIGHT));
        topSection.add(new JButton("Math"));
        topSection.add(new JLabel("Your Score: ", JLabel.RIGHT));
        JButton button = new JButton("2/2");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        topSection.add(button);

        topSection.add(new JLabel("Level: ", JLabel.RIGHT));
        topSection.add(new JButton("Integers"));
        topSection.add(new JLabel("Percent: ", JLabel.RIGHT));
        topSection.add(new JButton("100%"));

        getContentPane().add(topSection, BorderLayout.NORTH);

   
        JPanel centerSection = new JPanel(new GridLayout(1, 2, 10, 10));
        centerSection.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
        JLabel questionLabel = new JLabel("Q1: LOREM IPSUM?", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        centerSection.add(questionLabel);

        
        JPanel answerDetailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        answerDetailsPanel.add(new JLabel("Your Answer: Right Answer/Wrong Answer", JLabel.CENTER));
        answerDetailsPanel.add(new JLabel("Correct Answer:", JLabel.CENTER));
        answerDetailsPanel.add(new JTextField()); 
        JPanel navigationPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        navigationPanel.add(new JButton("<")); 
        navigationPanel.add(new JLabel("1/2", JLabel.CENTER)); 
        navigationPanel.add(new JButton(">")); 
        answerDetailsPanel.add(navigationPanel);

        centerSection.add(answerDetailsPanel);
        getContentPane().add(centerSection, BorderLayout.CENTER);

       
        JPanel bottomSection = new JPanel(new GridLayout(1, 2, 10, 10));
        bottomSection.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        bottomSection.add(new JButton("RETURN TO..."));
        bottomSection.add(new JButton("RESTART"));

        getContentPane().add(bottomSection, BorderLayout.SOUTH);

       
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new results());
    }
}
