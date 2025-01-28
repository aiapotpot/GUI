package layout;

import javax.swing.*;
import java.awt.*;

public class subjectEditorWindow extends JFrame {
	private JTextField subjectField;
	public subjectEditorWindow() {
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(new Rectangle(0, 0, 450, 160));
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{435, 0};
		gbl_contentPanel.rowHeights = new int[]{13, 55, 21, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		JLabel subjectLabel = new JLabel("Subject Name:", SwingConstants.CENTER);
		subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_subjectLabel = new GridBagConstraints();
		gbc_subjectLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_subjectLabel.insets = new Insets(0, 0, 5, 0);
		gbc_subjectLabel.gridx = 0;
		gbc_subjectLabel.gridy = 0;
		contentPanel.add(subjectLabel, gbc_subjectLabel);
		
		subjectField = new JTextField("", 20);
		subjectField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_subjectField = new GridBagConstraints();
		gbc_subjectField.fill = GridBagConstraints.BOTH;
		gbc_subjectField.insets = new Insets(0, 0, 5, 0);
		gbc_subjectField.gridx = 0;
		gbc_subjectField.gridy = 1;
		contentPanel.add(subjectField, gbc_subjectField);
		
		JPanel buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPanel.anchor = GridBagConstraints.NORTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 2;
		contentPanel.add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton removeButton = new JButton("REMOVE");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(removeButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(cancelButton);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(saveButton);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
        SwingUtilities.invokeLater(() -> new subjectEditorWindow()); // Instantiate the main frame
    }

}
