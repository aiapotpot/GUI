package flashlayout; 

import javax.swing.*;
import java.awt.*;

public class SUBJECTSELECTION extends JFrame {
    public SUBJECTSELECTION() {
        
        setTitle("Subject Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        
        setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new BorderLayout()); 
        JButton backButton = new JButton("BACK");
        JButton statsButton = new JButton("STATISTICS");

        
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(statsButton, BorderLayout.EAST);

       
        JPanel subjectGrid = new JPanel(new GridLayout(2, 3, 10, 10));
        subjectGrid.add(new JButton("MATH"));
        subjectGrid.add(new JButton("ENGLISH"));
        subjectGrid.add(new JButton("SCIENCE"));
        subjectGrid.add(new JButton("HISTORY"));
        subjectGrid.add(new JButton("PHILOSOPHY"));
        subjectGrid.add(new JButton("+"));

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(new JButton("<"));
        bottomPanel.add(new JButton(">"));

       
        add(topPanel, BorderLayout.NORTH);
        add(subjectGrid, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

      
        setVisible(true);
    }

    public static void main(String[] args) {
        new SUBJECTSELECTION(); 
    }
}
