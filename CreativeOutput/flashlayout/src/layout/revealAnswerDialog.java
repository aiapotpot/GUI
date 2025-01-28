package layout;

import javax.swing.*;
import java.awt.*;

public class revealAnswerDialog extends JFrame {
	public revealAnswerDialog() {
		setResizable(false);
		setVisible(true);
		setAlwaysOnTop(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(new Rectangle(0, 0, 300, 125));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{286, 0};
		gridBagLayout.rowHeights = new int[]{41, 41, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel topPanel = new JPanel();
		FlowLayout fl_topPanel = (FlowLayout) topPanel.getLayout();
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.insets = new Insets(0, 0, 5, 0);
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		getContentPane().add(topPanel, gbc_topPanel);
		
		JLabel revealAnswer_message = new JLabel("Reveal Answer? This will not be counted.");
		revealAnswer_message.setHorizontalAlignment(SwingConstants.CENTER);
		revealAnswer_message.setFont(new Font("Tahoma", Font.PLAIN, 14));
		topPanel.add(revealAnswer_message);
		
		JPanel bottomPanel = new JPanel();
		GridBagConstraints gbc_bottomPanel = new GridBagConstraints();
		gbc_bottomPanel.fill = GridBagConstraints.BOTH;
		gbc_bottomPanel.gridx = 0;
		gbc_bottomPanel.gridy = 1;
		getContentPane().add(bottomPanel, gbc_bottomPanel);
		bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton revealAnswer_cancelBtn = new JButton("Cancel");
		revealAnswer_cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bottomPanel.add(revealAnswer_cancelBtn);
		
		JButton revealAnswer_yesBtn = new JButton("Yes");
		revealAnswer_yesBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bottomPanel.add(revealAnswer_yesBtn);
	}

    

    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
        SwingUtilities.invokeLater(() -> new revealAnswerDialog()); // Instantiate the main frame
    }
}
