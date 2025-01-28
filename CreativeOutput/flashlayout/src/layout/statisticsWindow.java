package layout;

import javax.swing.*;
import java.awt.*;

public class statisticsWindow extends JFrame {
	private JTextField gamesPlayedField;
	private JTextField favoriteSubjectField;
	private JTextField mistakesSubjectField;
	private JTextField correctAnswersSubjectField;
	
	public statisticsWindow() {
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(new Rectangle(0, 0, 450, 230));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{436, 0};
		gridBagLayout.rowHeights = new int[]{32, 142, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel topPanel = new JPanel();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		getContentPane().add(topPanel, gbc_topPanel);
		
		JLabel headerLbl = new JLabel("STATISTICS:", SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		topPanel.add(headerLbl);
		
		JPanel contentPanel = new JPanel();
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 1;
		getContentPane().add(contentPanel, gbc_contentPanel);
		contentPanel.setLayout(new GridLayout(4, 2, 5, 10));
		
		JLabel gamesPlayedLbl = new JLabel("Games Played:");
		gamesPlayedLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
		gamesPlayedLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(gamesPlayedLbl);
		
		gamesPlayedField = new JTextField(10);
		gamesPlayedField.setEditable(false);
		gamesPlayedField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(gamesPlayedField);
		
		JLabel favoriteSubjectLbl = new JLabel("Favorite Subject:");
		favoriteSubjectLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
		favoriteSubjectLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(favoriteSubjectLbl);
		
		favoriteSubjectField = new JTextField(10);
		favoriteSubjectField.setEditable(false);
		favoriteSubjectField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(favoriteSubjectField);
		
		JLabel mistakesSubjectLbl = new JLabel("Subject with most mistakes:");
		mistakesSubjectLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		mistakesSubjectLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPanel.add(mistakesSubjectLbl);
		
		mistakesSubjectField = new JTextField(10);
		mistakesSubjectField.setEditable(false);
		mistakesSubjectField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(mistakesSubjectField);
		
		JLabel correctAnswersSubjectLbl = new JLabel("Subject with most correct answers:");
		correctAnswersSubjectLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		correctAnswersSubjectLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
		contentPanel.add(correctAnswersSubjectLbl);
		
		correctAnswersSubjectField = new JTextField(10);
		correctAnswersSubjectField.setEditable(false);
		correctAnswersSubjectField.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(correctAnswersSubjectField);
	}

    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
        SwingUtilities.invokeLater(() -> new statisticsWindow()); // Instantiate the main frame
    }
}
