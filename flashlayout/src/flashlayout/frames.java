package flashlayout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class frames extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frames frame = new frames();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public frames() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout()); 

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0; 
        gbcLabel.gridy = 0; 
        gbcLabel.anchor = GridBagConstraints.CENTER; 
        JLabel lblNewLabel = new JLabel("FlashCard Quiz");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
        contentPane.add(lblNewLabel, gbcLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  
        GridBagConstraints gbcButtonPanel = new GridBagConstraints();
        gbcButtonPanel.gridx = 0; 
        gbcButtonPanel.gridy = 1; 
        gbcButtonPanel.anchor = GridBagConstraints.CENTER; 
        contentPane.add(buttonPanel, gbcButtonPanel);

        
        JButton btnNewButton_1 = new JButton("PLAY");
        btnNewButton_1.setForeground(new Color(0, 128, 0));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        buttonPanel.add(btnNewButton_1);

        JButton btnNewButton = new JButton("QUIT");
        btnNewButton.setForeground(Color.RED);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        buttonPanel.add(btnNewButton);
    }
}
