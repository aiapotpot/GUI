package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.subjectEditorLogic;

import models.Subject;

import java.sql.SQLException;

// TODO: Add keybinds later
// TODO: Refactor anything bad/inefficient/ugly here soon
// TODO: Add error propagation in the program, and display appropriate error messages to the user

public class subjectEditorWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;
	
	private JTextField subjectField;
	private JButton removeBtn;
	private JButton cancelBtn;
	private JButton saveBtn;
	private JLabel subjectLabel;

	private subjectEditorLogic windowLogic;
	
	public void setEditMode(Subject subject) {
		setTitle("Edit Mode");
		removeBtn.setEnabled(true);
		subjectField.setText(subject.getName());
		
		contentPanel.putClientProperty("windowMode", "edit");
		contentPanel.putClientProperty("subject", subject);
	}
	
 	public void setCreateMode() {
		setTitle("Create Mode");
		removeBtn.setEnabled(false);
		
		contentPanel.putClientProperty("windowMode", "create");
	}
 	
	public subjectEditorWindow(subjectEditorLogic logic) {
		this.windowLogic = logic;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only closes WindowB
        setLocationRelativeTo(null);
		
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setBounds(new Rectangle(0, 0, 450, 160));
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{435, 0};
		gbl_contentPanel.rowHeights = new int[]{13, 55, 21, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
		subjectLabel = new JLabel("Subject Name:", SwingConstants.CENTER);
		subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_subjectLabel = new GridBagConstraints();
		gbc_subjectLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_subjectLabel.insets = new Insets(0, 0, 5, 0);
		gbc_subjectLabel.gridx = 0;
		gbc_subjectLabel.gridy = 0;
		contentPanel.add(subjectLabel, gbc_subjectLabel);
		
		subjectField = new JTextField("", 20);
		subjectField.setHorizontalAlignment(SwingConstants.CENTER);
		subjectField.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		
		removeBtn = new JButton("REMOVE");
		removeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Subject subject = (Subject) contentPanel.getClientProperty("subject");
				windowLogic.deleteSubject(subject);
				dispose();
			}
		});
		removeBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(removeBtn);
		
		cancelBtn = new JButton("CANCEL");
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(cancelBtn);
		
		saveBtn = new JButton("SAVE");
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String windowMode = (String) contentPanel.getClientProperty("windowMode");
				Subject subject = (Subject) contentPanel.getClientProperty("subject");
				
				try {
					if (windowMode == "create" && !subjectField.getText().isBlank()) {
						windowLogic.createSubject(subjectField.getText());
					} 
					else if (windowMode == "edit" && !subjectField.getText().isBlank()){
						windowLogic.editSubject(subject, subjectField.getText());
					}
					
					else if (subjectField.getText().isBlank()) {
						throw new IllegalArgumentException();
					}
					
				} 
				catch (IllegalArgumentException i){
					// Temporary fix, I will add proper error messages soon. Should've dealt with the error propagation part earlier ARGHHH!
					// TODO: Implement error propagation and gracefully handle every exception!
					JOptionPane.showMessageDialog(null, "Please input a name for your new subject", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				dispose();
			}
		});
		
		saveBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(saveBtn);
		setCreateMode();
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		subjectEditorLogic windowLogic = new subjectEditorLogic();
		
        SwingUtilities.invokeLater(() -> new subjectEditorWindow(windowLogic)); // Instantiate the main frame
    }

}
