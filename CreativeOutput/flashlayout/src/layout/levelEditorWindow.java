package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import logic.levelEditorLogic;

import models.Subject;
import models.Level;
import models.Flashcard;

// TODO: Refactor crappy code because I was too tired when I wrote it last night
// TODO: Level Editor is almost good, After that time to deal with the flashcard editor itself
// TODO: Write the flashcard editor methods
// NOTE TO FUTURE DEVELOPERS: Never code tired, you'll refactor the garbage that you wrote tomorrow anyway!

public class levelEditorWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel levelEditor;
	private JPanel flashcardEditor;
	
	private JButton levelEditor_removeBtn;
	private JButton levelEditor_saveBtn;
	
	private JTextField levelEditor_nameField;
	private JTextField levelEditor_subjectField;
	private JTextField levelEditor_timerField;
	
	private JCheckBox levelEditor_enableTimer;
	
	
	private JButton flashcardPreview_saveBtn;
	private JButton flashcardPreview_refreshBtn;
	private JButton flashcardPreview_flashcard;
	private JButton flashcardEditor_addBtn;
	private JButton flashcardEditor_flashcard_1;
	private JButton flashcardEditor_flashcard_2;
	private JButton flashcardEditor_flashcard_3;
	private JButton flashcardEditor_nextBtn;
	private JButton flashcardEditor_backBtn;
	
	private JTextArea flashcardEditor_questionField;
	private JTextArea flashcardEditor_answerField;
	private JLabel flashcardEditor_pageNumberLbl;
	private ArrayList<JButton> flashcardBtns = new ArrayList<JButton>();
	private ArrayList<JComponent> flashcardEditorElements = new ArrayList<JComponent>();
	
	private JTextPane statusBar;

	private String windowMode; // temporary, maybe it's a good idea to add it to a JComponent as a property soon
	
	private levelEditorLogic windowLogic;

	public void toggleFlashcardEditor(boolean mode) {
		for (JComponent element: flashcardEditorElements) {
			element.setEnabled(mode);
		}
	}
	
	public void addFlashcardEditorElements() {

		flashcardEditorElements.add(flashcardPreview_saveBtn);
		flashcardEditorElements.add(flashcardPreview_flashcard);
		flashcardEditorElements.add(flashcardPreview_refreshBtn);
		flashcardEditorElements.add(flashcardEditor_addBtn);
		flashcardEditorElements.add(flashcardEditor_flashcard_1);
		flashcardEditorElements.add(flashcardEditor_flashcard_2);
		flashcardEditorElements.add(flashcardEditor_flashcard_3);
		flashcardEditorElements.add(flashcardEditor_questionField);
		flashcardEditorElements.add(flashcardEditor_answerField);
		flashcardEditorElements.add(flashcardEditor_nextBtn);
		flashcardEditorElements.add(flashcardEditor_backBtn);
	}
	
 	public void setEditMode(Subject subject, Level level) {
 		addFlashcardEditorElements();
 		toggleFlashcardEditor(true);
 		
 		levelEditor_removeBtn.setEnabled(true);
 		
		flashcardPreview_flashcard.putClientProperty("viewMode", 'q'); // default view mode
		
		setTitle("Edit mode");
		
		levelEditor.putClientProperty("subject", subject);
		levelEditor.putClientProperty("level", level);
		
		levelEditor_nameField.setText(level.getName());
		levelEditor_subjectField.setText(subject.getName());
		levelEditor_timerField.setText(level.getTimer().toString());
		
		Boolean timerEnabled = level.getTimerEnabled();
		levelEditor_enableTimer.setSelected(timerEnabled);
		
		windowMode = "edit";
		
		ArrayList< ArrayList<Flashcard> > flashcardsList = windowLogic.getFlashcardsList(level);
		
		populateGrid(flashcardEditor, flashcardBtns, flashcardEditor_pageNumberLbl, flashcardsList, 0, "Flashcard");
	}
	
	public void setCreateMode(Subject subject) {
		addFlashcardEditorElements();
		toggleFlashcardEditor(false);
		
		flashcardPreview_flashcard.putClientProperty("viewMode", 'q'); // default view mode

		setTitle("Create mode");
		
		levelEditor.putClientProperty("subject", subject);
		levelEditor_subjectField.setText(subject.getName());
		levelEditor_removeBtn.setEnabled(false);
		
		windowMode = "create";
	}
	
	private void initGridBtns() {
		flashcardBtns.add(flashcardEditor_flashcard_1);
		flashcardBtns.add(flashcardEditor_flashcard_2);
		flashcardBtns.add(flashcardEditor_flashcard_3);
		
		flashcardEditor.putClientProperty("index", 0);
	}

	private <T> void populateGrid(
			   JPanel selectionPane, 
			   ArrayList<JButton> gridBtns, 
			   JLabel pageNumberLbl,
			   ArrayList <ArrayList<T>> data, 
			   int index, 
			   String propertyName) {
		   
		   emptyGrid(gridBtns, propertyName);
		
		   if (data.isEmpty() || index >= data.size()) return;
		   
		   ArrayList<T> dataSet = data.get(index);
		   
		   for (int i=0; i<dataSet.size(); i++) {
			   JButton button = gridBtns.get(i);
			   T item = dataSet.get(i);
			   
			   button.putClientProperty(propertyName, item);
			   button.setText(item.toString());
		   }
		   
		   selectionPane.putClientProperty(propertyName + "s", data);
		   selectionPane.putClientProperty("current" + propertyName + "Set", dataSet);
		   
		   updatePageNumber(pageNumberLbl, index+1, data.size());
	   }
	
	private void setCurrentFlashcard(JButton flashcardBtn) {
		flashcardPreview_flashcard.putClientProperty("viewMode", 'q'); // default view mode
		
		Flashcard flashcard = (Flashcard) flashcardBtn.getClientProperty("Flashcard");
	
		if (flashcard != null) {
		
			flashcardEditor.putClientProperty("currentFlashcard", flashcard);
			
			flashcardEditor_questionField.setText(flashcard.getQuestion());
			
			flashcardEditor_answerField.setText(flashcard.getAnswer());
			
			showFlashcardPreview();
		}
		
	}
	
	private void updatePageNumber(JLabel pageNumberLbl, int left, int right) {
		   String page = "%d/%d";
		   page = String.format(page, left, right);
		   pageNumberLbl.setText(page);
	   }
	
	private void showFlashcardPreview() {
		Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
		
		flashcardPreview_flashcard.setText(flashcard.getQuestion());
	}
	
	private void toggleFlashcardMode() {
		   
		   Character viewMode = (Character) flashcardPreview_flashcard.getClientProperty("viewMode");
		   
		   switch (viewMode){
		   case 'a':
			   flashcardPreview_flashcard.putClientProperty("viewMode", 'q');
			   break;
		   case 'q':
			   flashcardPreview_flashcard.putClientProperty("viewMode", 'a');
			   break;
		   }
		   
	   }
	
	private void toggleFlashcards(char direction) {
		Level level = (Level) levelEditor.getClientProperty("level");
		
		ArrayList< ArrayList<Flashcard> > flashcardsList = windowLogic.getFlashcardsList(level);
		
		int oldIndex = (int) flashcardEditor.getClientProperty("index");
		int newIndex = windowLogic.toggleDataSet(flashcardsList, direction, oldIndex);
		
		flashcardEditor.putClientProperty("index", newIndex);
		
		populateGrid(flashcardEditor, flashcardBtns, flashcardEditor_pageNumberLbl, flashcardsList, newIndex, "Flashcard");
	}
	
	private void emptyGrid(ArrayList<JButton> gridBtns, String propertyName) {
		   for (JButton button: gridBtns) {
			   button.setText("");
			   button.putClientProperty(propertyName, null);
		   }
	   }  
	
	private void refreshGrid() {
		Level level = (Level) levelEditor.getClientProperty("level");
		Integer index = (Integer) flashcardEditor.getClientProperty("index");
		ArrayList<ArrayList<Flashcard>> flashcardsList = windowLogic.getFlashcardsList(level);
		
		populateGrid(flashcardEditor, flashcardBtns, flashcardEditor_pageNumberLbl, flashcardsList, index, "Flashcard");
	}
	
    public levelEditorWindow(levelEditorLogic logic) {
    	this.windowLogic = logic;
    	
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setVisible(true);
    	setAlwaysOnTop(false);
    	setBounds(new Rectangle(0, 0, 800, 600));
    	
    	GridBagLayout gridBagLayout = new GridBagLayout();
    	gridBagLayout.columnWidths = new int[]{786, 0};
    	gridBagLayout.rowHeights = new int[]{123, 365, 20, 0};
    	gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
    	getContentPane().setLayout(gridBagLayout);
    	
    	levelEditor = new JPanel();
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
    	
    	levelEditor_enableTimer = new JCheckBox("Enable timer for flashcards");
    	levelEditor_enableTimer.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
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
    	GridBagLayout gbl_levelEditor_actionPanel = new GridBagLayout();
    	gbl_levelEditor_actionPanel.columnWidths = new int[]{10, 80, 0};
    	gbl_levelEditor_actionPanel.rowHeights = new int[]{28, 0};
    	gbl_levelEditor_actionPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    	gbl_levelEditor_actionPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
    	levelEditor_actionPanel.setLayout(gbl_levelEditor_actionPanel);
    	
    	levelEditor_saveBtn = new JButton("SAVE");
    	levelEditor_saveBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			
    			Subject subject = (Subject) levelEditor.getClientProperty("subject");
    			String levelName = levelEditor_nameField.getText();
    			String timeStr = levelEditor_timerField.getText();
    			Boolean timerEnabled = levelEditor_enableTimer.isSelected();
    			Integer time = Integer.parseInt(timeStr);
    			
    			if (windowMode == "create") {
	    			windowLogic.createLevel(subject, levelName, time, timerEnabled);
	    			
	    			Level level = windowLogic.getLevel(subject, levelName);
	    			
	    			if (level != null) {
	    				setEditMode(subject, level);
	    			}
	    			
	    			
    			} else if (windowMode == "edit") {
    				Level level = (Level) levelEditor.getClientProperty("level");
    				Integer highscore = level.getHighScore();
    				
    				windowLogic.editLevel(subject, level, levelName, highscore, time, timerEnabled);
    			}
    			
    		}
    	});
    	
    	levelEditor_removeBtn = new JButton("REMOVE");
    	levelEditor_removeBtn.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
    			Subject subject = (Subject) levelEditor.getClientProperty("subject");
    			Level level = (Level) levelEditor.getClientProperty("level");
    			
    			int choice = JOptionPane.showConfirmDialog(null, "Delete entire level with the flashcards? Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					windowLogic.removeLevel(subject, level);	
					dispose();
				}
    		}
    	});
    	GridBagConstraints gbc_levelEditor_removeBtn = new GridBagConstraints();
    	gbc_levelEditor_removeBtn.fill = GridBagConstraints.BOTH;
    	gbc_levelEditor_removeBtn.insets = new Insets(0, 0, 0, 5);
    	gbc_levelEditor_removeBtn.gridx = 0;
    	gbc_levelEditor_removeBtn.gridy = 0;
    	levelEditor_actionPanel.add(levelEditor_removeBtn, gbc_levelEditor_removeBtn);
    	GridBagConstraints gbc_levelEditor_saveBtn = new GridBagConstraints();
    	gbc_levelEditor_saveBtn.fill = GridBagConstraints.BOTH;
    	gbc_levelEditor_saveBtn.gridx = 1;
    	gbc_levelEditor_saveBtn.gridy = 0;
    	levelEditor_actionPanel.add(levelEditor_saveBtn, gbc_levelEditor_saveBtn);
    	
    	flashcardEditor = new JPanel();
    	flashcardEditor.setBorder(new TitledBorder(null, "Flashcards", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagConstraints gbc_flashcardEditor = new GridBagConstraints();
    	gbc_flashcardEditor.insets = new Insets(0, 0, 5, 0);
    	gbc_flashcardEditor.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor.gridx = 0;
    	gbc_flashcardEditor.gridy = 1;
    	getContentPane().add(flashcardEditor, gbc_flashcardEditor);
    	GridBagLayout gbl_flashcardEditor = new GridBagLayout();
    	gbl_flashcardEditor.columnWidths = new int[]{275, 0, 0};
    	gbl_flashcardEditor.rowHeights = new int[]{310};
    	gbl_flashcardEditor.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor.rowWeights = new double[]{1.0};
    	flashcardEditor.setLayout(gbl_flashcardEditor);
    	
    	JPanel flashcardEditor_flashcardPreview = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_flashcardPreview = new GridBagConstraints();
    	gbc_flashcardEditor_flashcardPreview.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_flashcardPreview.insets = new Insets(0, 0, 0, 5);
    	gbc_flashcardEditor_flashcardPreview.gridx = 0;
    	gbc_flashcardEditor_flashcardPreview.gridy = 0;
    	flashcardEditor.add(flashcardEditor_flashcardPreview, gbc_flashcardEditor_flashcardPreview);
    	flashcardEditor_flashcardPreview.setBorder(null);
    	GridBagLayout gbl_flashcardEditor_flashcardPreview = new GridBagLayout();
    	gbl_flashcardEditor_flashcardPreview.columnWidths = new int[]{309, 0};
    	gbl_flashcardEditor_flashcardPreview.rowHeights = new int[]{295, 17, 0};
    	gbl_flashcardEditor_flashcardPreview.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor_flashcardPreview.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    	flashcardEditor_flashcardPreview.setLayout(gbl_flashcardEditor_flashcardPreview);
    	
    	flashcardPreview_flashcard = new JButton("FLASHCARD");
    	flashcardPreview_flashcard.setFont(new Font("SansSerif", Font.BOLD, 18));
    	flashcardPreview_flashcard.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			toggleFlashcardMode();
    			
    			Character viewMode = (Character) flashcardPreview_flashcard.getClientProperty("viewMode");
    			Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
    			String text = windowLogic.getFlashcardText(flashcard, viewMode);
    			
    			flashcardPreview_flashcard.setText(text);
    			
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
    	
    	flashcardPreview_refreshBtn = new JButton("REFRESH");
    	flashcardPreview_refreshBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    		}
    	});
    	flashcardPreview_actionPanel.add(flashcardPreview_refreshBtn);
    	
    	flashcardPreview_saveBtn = new JButton("SAVE");
    	flashcardPreview_saveBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
    			
    			String newQuestion = flashcardEditor_questionField.getText();
    			String newAnswer = flashcardEditor_answerField.getText();
    			
    			windowLogic.editFlashcard(flashcard, newQuestion, newAnswer);
    			
    			refreshGrid();
    		}
    	});
    	flashcardPreview_actionPanel.add(flashcardPreview_saveBtn);
    	
    	JPanel flashcardEditor_infoPanel = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_infoPanel = new GridBagConstraints();
    	gbc_flashcardEditor_infoPanel.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_infoPanel.gridx = 1;
    	gbc_flashcardEditor_infoPanel.gridy = 0;
    	flashcardEditor.add(flashcardEditor_infoPanel, gbc_flashcardEditor_infoPanel);
    	flashcardEditor_infoPanel.setBorder(new TitledBorder(null, "Create New", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagLayout gbl_flashcardEditor_infoPanel = new GridBagLayout();
    	gbl_flashcardEditor_infoPanel.columnWidths = new int[]{393, 0};
    	gbl_flashcardEditor_infoPanel.rowHeights = new int[]{105, 125, -40, 0};
    	gbl_flashcardEditor_infoPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    	gbl_flashcardEditor_infoPanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
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
    	
    	JPanel flashcardEditor_collectionGrid = new JPanel();
    	flashcardEditor_collectionGrid.setBorder(new TitledBorder(null, "Collection", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	GridBagConstraints gbc_flashcardEditor_collectionGrid = new GridBagConstraints();
    	gbc_flashcardEditor_collectionGrid.fill = GridBagConstraints.BOTH;
    	gbc_flashcardEditor_collectionGrid.insets = new Insets(0, 0, 5, 0);
    	gbc_flashcardEditor_collectionGrid.gridx = 0;
    	gbc_flashcardEditor_collectionGrid.gridy = 1;
    	flashcardEditor_infoPanel.add(flashcardEditor_collectionGrid, gbc_flashcardEditor_collectionGrid);
    	flashcardEditor_collectionGrid.setLayout(new GridLayout(0, 4, 0, 0));
    	
    	flashcardEditor_flashcard_1 = new JButton("");
    	flashcardEditor_flashcard_1.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			Character viewMode = (Character) flashcardPreview_flashcard.getClientProperty("viewMode");
    			Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
    			if (e.getButton() == MouseEvent.BUTTON1) {
	    			setCurrentFlashcard(flashcardEditor_flashcard_1);
	    			
	    			windowLogic.getFlashcardText(flashcard, viewMode);
    			} else if (e.getButton() == MouseEvent.BUTTON3 && !flashcardEditor_flashcard_1.getText().isBlank()) {
    				int choice = JOptionPane.showConfirmDialog(null, "Delete flashcard? Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
    				if (choice == JOptionPane.YES_OPTION) {
    					Level level = (Level) levelEditor.getClientProperty("level");
    					
    					windowLogic.removeFlashcard(level, flashcard);
    					refreshGrid();
    					
    				}
    			}
    			
    		}
    	});
    	flashcardEditor_collectionGrid.add(flashcardEditor_flashcard_1);
    	
    	flashcardEditor_flashcard_2 = new JButton("");
    	flashcardEditor_flashcard_2.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			Character viewMode = (Character) flashcardPreview_flashcard.getClientProperty("viewMode");
    			Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
    			if (e.getButton() == MouseEvent.BUTTON1) {
	    			setCurrentFlashcard(flashcardEditor_flashcard_2);
	    			
	    			windowLogic.getFlashcardText(flashcard, viewMode);
    			} else if (e.getButton() == MouseEvent.BUTTON3 && !flashcardEditor_flashcard_2.getText().isBlank()) {
    				int choice = JOptionPane.showConfirmDialog(null, "Delete flashcard? Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
    				if (choice == JOptionPane.YES_OPTION) {
    					Level level = (Level) levelEditor.getClientProperty("level");
    					
    					windowLogic.removeFlashcard(level, flashcard);
    					refreshGrid();
    					
    				}
    			}
    		}
    	});
    	flashcardEditor_collectionGrid.add(flashcardEditor_flashcard_2);
    	
    	flashcardEditor_flashcard_3 = new JButton("");
    	flashcardEditor_flashcard_3.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			Character viewMode = (Character) flashcardPreview_flashcard.getClientProperty("viewMode");
    			Flashcard flashcard = (Flashcard) flashcardEditor.getClientProperty("currentFlashcard");
    			if (e.getButton() == MouseEvent.BUTTON1) {
	    			setCurrentFlashcard(flashcardEditor_flashcard_3);
	    			
	    			windowLogic.getFlashcardText(flashcard, viewMode);
    			} else if (e.getButton() == MouseEvent.BUTTON3 && !flashcardEditor_flashcard_3.getText().isBlank()) {
    				int choice = JOptionPane.showConfirmDialog(null, "Delete flashcard? Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
    				if (choice == JOptionPane.YES_OPTION) {
    					Level level = (Level) levelEditor.getClientProperty("level");
    					
    					windowLogic.removeFlashcard(level, flashcard);
    					refreshGrid();
    					
    				}
    			}
    		}
    	});
    	flashcardEditor_collectionGrid.add(flashcardEditor_flashcard_3);
    	
    	flashcardEditor_addBtn = new JButton("+");
    	flashcardEditor_addBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			
    			Level level = (Level) levelEditor.getClientProperty("level");
    			windowLogic.createFlashcard(level);
    			
    			refreshGrid();
    		}
    	});
    	flashcardEditor_addBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
    	flashcardEditor_collectionGrid.add(flashcardEditor_addBtn);
    	
    	JPanel flashcardEditor_actionPanel = new JPanel();
    	GridBagConstraints gbc_flashcardEditor_actionPanel = new GridBagConstraints();
    	gbc_flashcardEditor_actionPanel.anchor = GridBagConstraints.NORTH;
    	gbc_flashcardEditor_actionPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_flashcardEditor_actionPanel.gridx = 0;
    	gbc_flashcardEditor_actionPanel.gridy = 2;
    	flashcardEditor_infoPanel.add(flashcardEditor_actionPanel, gbc_flashcardEditor_actionPanel);
    	flashcardEditor_actionPanel.setLayout(new GridLayout(0, 3, 0, 0));
    	
    	flashcardEditor_pageNumberLbl = new JLabel("0/0");
    	flashcardEditor_pageNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
    	flashcardEditor_actionPanel.add(flashcardEditor_pageNumberLbl);
    	
    	flashcardEditor_backBtn = new JButton("<");
    	flashcardEditor_backBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			toggleFlashcards('-');
    		}
    	});
    	flashcardEditor_actionPanel.add(flashcardEditor_backBtn);
    	
    	flashcardEditor_nextBtn = new JButton(">");
    	flashcardEditor_nextBtn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			toggleFlashcards('+');
    		}
    	});
    	flashcardEditor_actionPanel.add(flashcardEditor_nextBtn);
    	
    	JPanel statusPanel = new JPanel();
    	GridBagConstraints gbc_statusPanel = new GridBagConstraints();
    	gbc_statusPanel.anchor = GridBagConstraints.SOUTH;
    	gbc_statusPanel.fill = GridBagConstraints.HORIZONTAL;
    	gbc_statusPanel.gridx = 0;
    	gbc_statusPanel.gridy = 2;
    	getContentPane().add(statusPanel, gbc_statusPanel);
    	statusPanel.setLayout(new GridLayout(0, 1, 0, 0));
    	
    	statusBar = new JTextPane();
    	statusBar.setEditable(false);
    	statusPanel.add(statusBar);
    	
    	initGridBtns();
    }

    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
    	
    	levelEditorLogic windowLogic = new levelEditorLogic();
    	
    	SwingUtilities.invokeLater(() -> new levelEditorWindow(windowLogic));
    }
}