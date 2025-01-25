package flashlayout; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Selectlevel extends JFrame {
    public Selectlevel() {
        
        setTitle("Select Level");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        
        getContentPane().setLayout(new BorderLayout());

        
        JPanel topPanel = new JPanel(new BorderLayout()); 
        JButton backButton = new JButton("BACK");
        JButton homeButton = new JButton("HOME");

        
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(homeButton, BorderLayout.EAST);

        
        JPanel levelGrid = new JPanel(new GridLayout(3, 2, 10, 10)); 
        JButton button_1 = new JButton("INTEGERS");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        levelGrid.add(button_1);

        JButton button_2 = new JButton("ADDITION");
        levelGrid.add(button_2);

        JButton button_3 = new JButton("ADDITION 2");
        levelGrid.add(button_3);

        JButton button_4 = new JButton("SUBTRACTION");
        levelGrid.add(button_4);

        JButton button_5 = new JButton("SUBTRACTION 2");
        levelGrid.add(button_5);

        JButton button_6 = new JButton("+");
        levelGrid.add(button_6);

        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(new JButton("<"));
        bottomPanel.add(new JButton(">"));

      
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(levelGrid, BorderLayout.CENTER);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

       
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Selectlevel::new); 
    }
}

