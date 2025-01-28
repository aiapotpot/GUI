package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class levelEditorWindow extends JFrame {
	private JTextArea flashcardEditor_questionField;
	private JTextArea flashcardEditor_answerField;
	private JTextField levelEditor_nameField;
	private JTextField levelEditor_subjectField;
	private JTextField levelEditor_timerField;

    public levelEditorWindow() {
    	setVisible(true);
    	setAlwaysOnTop(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(new Rectangle(0, 0, 800, 600));
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{786, 0};
    	gridBagLayout.rowHeights = new int[]{123, 281, 0};
    	gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	getContentPane().setLayout(gridBagLayout);
    	
    	JPanel levelEditor = new JPanel();
    	levelEditor.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Level", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    	GridBagConstraints gbc_levelEditor = new GridBagConstraints();
    	gbc_levelEditor.fill = GridBagConstraints.BOTH;
    	gbc_levelEditor.insets = new Insets(0, 0, 5, 0);
    	gbc_levelEditor.gridx = 0;
    	gbc_levelEditor.gridy = 0;
    	getContentPane().add(levelEditor, gbc_levelEditor);
    	GridBagLayout gbl_levelEditor = new GridBagLayout();
    	gbl_levelEditor.columnWidths = new int[]{352, 0};
    	gbl_levelEditor.rowHeights = new int[]{35, 10, 0};
    	gbl_levelEditor.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gbl_levelEditor.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	levelEditor.setLayout(gbl_levelEditor);
    	
    	JPanel levelEditor_infoPanel = new JPanel();
    	GridBagConstraints gbc_levelEditor_infoPanel = new GridBagConstraints();
    	gbc_levelEditor_infoPanel.insets = new Insets(0, 0, 5, 0);
    	gbc_levelEditor_infoPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_levelEditor_infoPanel.gridx = 0;
    	gbc_levelEditor_infoPanel.gridy = 0;
    	levelEditor.add(levelEditor_infoPanel, gbc_levelEditor_infoPanel);
    	levelEditor_infoPanel.setLayout(new GridLayout(0, 2, 0, 0));
    	
    	JPanel levelEditor_detailsPanel = new JPanel();
    	levelEditor_detailsPanel.setBorder(new TitledBorder(null, "Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	levelEditor_infoPanel.add(levelEditor_detailsPanel);
    	levelEditor_detailsPanel.setLayout(new GridLayout(2, 2, 0, 7));
    	
    	JLabel levelEditor_nameLbl = new JLabel("Name:");
    	levelEditor_nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	levelEditor_detailsPanel.add(levelEditor_nameLbl);
    	
    	levelEditor_nameField = new JTextField();
    	levelEditor_detailsPanel.add(levelEditor_nameField);
    	levelEditor_nameField.setColumns(10);
    	
    	JLabel levelEditor_subjectLbl = new JLabel("Subject:");
    	levelEditor_subjectLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	levelEditor_detailsPanel.add(levelEditor_subjectLbl);
    	
    	levelEditor_subjectField = new JTextField();
    	levelEditor_subjectField.setEditable(false);
    	levelEditor_detailsPanel.add(levelEditor_subjectField);
    	levelEditor_subjectField.setColumns(10);
    	
    	JPanel levelEditor_timerPanel = new JPanel();
    	levelEditor_timerPanel.setBorder(new TitledBorder(null, "Timer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	levelEditor_infoPanel.add(levelEditor_timerPanel);
    	levelEditor_timerPanel.setLayout(new GridLayout(2, 2, 0, 0));
    	
    	JCheckBox levelEditor_enableTimer = new JCheckBox("Enable timer for flashcards");
    	levelEditor_enableTimer.setHorizontalAlignment(SwingConstants.CENTER);
    	levelEditor_timerPanel.add(levelEditor_enableTimer);
    	
    	JPanel levelEditor_timerDetailsPanel = new JPanel();
    	levelEditor_timerPanel.add(levelEditor_timerDetailsPanel);
    	
    	JLabel levelEditor_timerLbl = new JLabel("Time per flashcard:");
    	levelEditor_timerDetailsPanel.add(levelEditor_timerLbl);
    	
    	levelEditor_timerField = new JTextField();
    	levelEditor_timerField.setColumns(10);
    	levelEditor_timerDetailsPanel.add(levelEditor_timerField);
    	
    	JPanel levelEditor_actionPanel = new JPanel();
    	GridBagConstraints gbc_levelEditor_actionPanel = new GridBagConstraints();
    	gbc_levelEditor_actionPanel.fill = GridBagConstraints.VERTICAL;
    	gbc_levelEditor_actionPanel.gridx = 0;
    	gbc_levelEditor_actionPanel.gridy = 1;
    	levelEditor.add(levelEditor_actionPanel, gbc_levelEditor_actionPanel);
    	
    	JButton levelEditor_saveBtn = new JButton("Save");
    	levelEditor_saveBtn.setHorizontalAlignment(SwingConstants.LEFT);
    	levelEditor_actionPanel.add(levelEditor_saveBtn);
    	
    	JPanel flashcardEditor = new JPanel();
    	flashcardEditor.setBorder(new TitledBorder(null, "Flashcards", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagConstraints gbc_flashcardEditor = new GridBagConstraints();
    	gbc_flashcardEditor.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor.gridx = 0;
    	gbc_flashcardEditor.gridy = 1;
    	getContentPane().add(flashcardEditor, gbc_flashcardEditor);
    	GridBagLayout gbl_flashcardEditor = new GridBagLayout();
    	gbl_flashcardEditor.columnWidths = new int[]{275, 0, 0};
    	gbl_flashcardEditor.rowHeights = new int[]{-9, 225};
    	gbl_flashcardEditor.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor.rowWeights = new double[]{1.0, 1.0};
    	flashcardEditor.setLayout(gbl_flashcardEditor);
    	
    	JPanel flashcardEditor_flashcardPreview = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_flashcardPreview = new GridBagConstraints();
    	gbc_flashcardEditor_flashcardPreview.gridheight = 2;
    	gbc_flashcardEditor_flashcardPreview.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_flashcardPreview.insets = new Insets(0, 0, 0, 5);
    	gbc_flashcardEditor_flashcardPreview.gridx = 0;
    	gbc_flashcardEditor_flashcardPreview.gridy = 0;
    	flashcardEditor.add(flashcardEditor_flashcardPreview, gbc_flashcardEditor_flashcardPreview);
    	flashcardEditor_flashcardPreview.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    	GridBagLayout gbl_flashcardEditor_flashcardPreview = new GridBagLayout();
    	gbl_flashcardEditor_flashcardPreview.columnWidths = new int[]{275, 0};
    	gbl_flashcardEditor_flashcardPreview.rowHeights = new int[]{325, 17, 0};
    	gbl_flashcardEditor_flashcardPreview.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor_flashcardPreview.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	flashcardEditor_flashcardPreview.setLayout(gbl_flashcardEditor_flashcardPreview);
    	
    	JButton flashcardPreview_flashcard = new JButton("FLASHCARD");
    	flashcardPreview_flashcard.setFont(new Font("SansSerif", Font.BOLD, 18));
    	flashcardPreview_flashcard.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	GridBagConstraints gbc_flashcardPreview_flashcard = new GridBagConstraints();
    	gbc_flashcardPreview_flashcard.fill = GridBagConstraints.BOTH;
    	gbc_flashcardPreview_flashcard.insets = new Insets(0, 0, 5, 0);
    	gbc_flashcardPreview_flashcard.gridx = 0;
    	gbc_flashcardPreview_flashcard.gridy = 0;
    	flashcardEditor_flashcardPreview.add(flashcardPreview_flashcard, gbc_flashcardPreview_flashcard);
    	
    	JPanel flashcardPreview_actionPanel = new JPanel();
    	GridBagConstraints gbc_flashcardPreview_actionPanel = new GridBagConstraints();
    	gbc_flashcardPreview_actionPanel.anchor = GridBagConstraints.NORTH;
    	gbc_flashcardPreview_actionPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_flashcardPreview_actionPanel.gridx = 0;
    	gbc_flashcardPreview_actionPanel.gridy = 1;
    	flashcardEditor_flashcardPreview.add(flashcardPreview_actionPanel, gbc_flashcardPreview_actionPanel);
    	flashcardPreview_actionPanel.setLayout(new GridLayout(0, 2, 0, 0));
    	
    	JButton flashcardPreview_refreshBtn = new JButton("Refresh");
    	flashcardPreview_actionPanel.add(flashcardPreview_refreshBtn);
    	
    	JButton flashcardPreview_saveBtn = new JButton("Save");
    	flashcardPreview_actionPanel.add(flashcardPreview_saveBtn);
    	
    	JPanel flashcardEditor_infoPanel = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_infoPanel = new GridBagConstraints();
    	gbc_flashcardEditor_infoPanel.gridheight = 2;
    	gbc_flashcardEditor_infoPanel.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_infoPanel.gridx = 1;
    	gbc_flashcardEditor_infoPanel.gridy = 0;
    	flashcardEditor.add(flashcardEditor_infoPanel, gbc_flashcardEditor_infoPanel);
    	flashcardEditor_infoPanel.setBorder(new TitledBorder(null, "Create New", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagLayout gbl_flashcardEditor_infoPanel = new GridBagLayout();
    	gbl_flashcardEditor_infoPanel.columnWidths = new int[]{393, 0};
    	gbl_flashcardEditor_infoPanel.rowHeights = new int[]{140, 0, 125, -40, 0};
    	gbl_flashcardEditor_infoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor_infoPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
    	flashcardEditor_infoPanel.setLayout(gbl_flashcardEditor_infoPanel);
    	
    	JPanel flashcardEditor_detailsPanel = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_detailsPanel = new GridBagConstraints();
    	gbc_flashcardEditor_detailsPanel.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_detailsPanel.insets = new Insets(0, 0, 5, 0);
    	gbc_flashcardEditor_detailsPanel.gridx = 0;
    	gbc_flashcardEditor_detailsPanel.gridy = 0;
    	flashcardEditor_infoPanel.add(flashcardEditor_detailsPanel, gbc_flashcardEditor_detailsPanel);
    	flashcardEditor_detailsPanel.setLayout(new GridLayout(2, 2, 0, 0));
    	
    	JLabel flashcardEditor_questionLbl = new JLabel("Question:");
    	flashcardEditor_questionLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	flashcardEditor_detailsPanel.add(flashcardEditor_questionLbl);
    	
    	flashcardEditor_questionField = new JTextArea();
    	flashcardEditor_detailsPanel.add(flashcardEditor_questionField);
    	flashcardEditor_questionField.setColumns(10);
    	
    	JLabel flashcardEditor_answerLbl = new JLabel("Answer:");
    	flashcardEditor_answerLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	flashcardEditor_detailsPanel.add(flashcardEditor_answerLbl);
    	
    	flashcardEditor_answerField = new JTextArea();
    	flashcardEditor_detailsPanel.add(flashcardEditor_answerField);
    	flashcardEditor_answerField.setColumns(10);
    	
    	Component verticalStrut = Box.createVerticalStrut(20);
    	GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
    	gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
    	gbc_verticalStrut.gridx = 0;
    	gbc_verticalStrut.gridy = 1;
    	flashcardEditor_infoPanel.add(verticalStrut, gbc_verticalStrut);
    	
    	JPanel flashcardEditor_collectionGrid = new JPanel();
    	flashcardEditor_collectionGrid.setBorder(new TitledBorder(null, "Collection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagConstraints gbc_flashcardEditor_collectionGrid = new GridBagConstraints();
    	gbc_flashcardEditor_collectionGrid.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_collectionGrid.insets = new Insets(0, 0, 5, 0);
    	gbc_flashcardEditor_collectionGrid.gridx = 0;
    	gbc_flashcardEditor_collectionGrid.gridy = 2;
    	flashcardEditor_infoPanel.add(flashcardEditor_collectionGrid, gbc_flashcardEditor_collectionGrid);
    	flashcardEditor_collectionGrid.setLayout(new GridLayout(0, 4, 0, 0));
    	
    	JButton btnNewButton_4 = new JButton("");
    	flashcardEditor_collectionGrid.add(btnNewButton_4);
    	
    	JButton btnNewButton_5 = new JButton("");
    	flashcardEditor_collectionGrid.add(btnNewButton_5);
    	
    	JButton btnNewButton_6 = new JButton("");
    	flashcardEditor_collectionGrid.add(btnNewButton_6);
    	
    	JButton btnNewButton_7 = new JButton("+");
    	btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 18));
    	flashcardEditor_collectionGrid.add(btnNewButton_7);
    	
    	JPanel flashcardEditor_actionPanel = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_actionPanel = new GridBagConstraints();
    	gbc_flashcardEditor_actionPanel.anchor = GridBagConstraints.NORTH;
    	gbc_flashcardEditor_actionPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_flashcardEditor_actionPanel.gridx = 0;
    	gbc_flashcardEditor_actionPanel.gridy = 3;
    	flashcardEditor_infoPanel.add(flashcardEditor_actionPanel, gbc_flashcardEditor_actionPanel);
    	flashcardEditor_actionPanel.setLayout(new GridLayout(0, 3, 0, 0));
    	
    	JLabel flashcardEditor_pageNumberLbl = new JLabel("0/0");
    	flashcardEditor_pageNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	flashcardEditor_actionPanel.add(flashcardEditor_pageNumberLbl);
    	
    	JButton flashcardEditor_backBtn = new JButton("<");
    	flashcardEditor_actionPanel.add(flashcardEditor_backBtn);
    	
    	JButton flashcardEditor_nextBtn = new JButton(">");
    	flashcardEditor_actionPanel.add(flashcardEditor_nextBtn);
    }

    private void openLevelEditor() {
        // Sub-window for Level Editor
        JFrame levelEditor = new JFrame("Level Editor");
    }

    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
        SwingUtilities.invokeLater(levelEditorWindow::new); // Fixed the constructor call to match class name
    }
}

