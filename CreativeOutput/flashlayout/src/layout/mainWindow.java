package layout;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.TitledBorder;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import java.util.*;
import models.Subject;
import models.Level;
import models.Flashcard;

import logic.mainWindowLogic;
import logic.subjectEditorLogic;
import logic.levelEditorLogic;
// TODO: Document code soon
// TODO: Add keybinds to program if ever
// TODO: Bug, reveal answer even if game has not formally started yet
// TODO: Bugfix, gracefully handle editing empty flashcard

public class mainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel subjectSelection;
	private JPanel levelSelection;
	private JPanel gameArea;
	private JPanel resultsArea;
	
	private JTextField gameArea_answerField; 
	private JTextField answersPreview_yourAnswerPane;
	private JTextField answersPreview_correctAnswerPane;
	private JTextField scoreGrid_subjectField;
	private JTextField scoreGrid_yourScoreField;
	private JTextField scoreGrid_levelField;
	private JTextField scoreGrid_percentField;
	private JTextField gameArea_timerPane;
	
	private JLabel gameArea_remainingFlashcardsLbl;
	private JLabel subjectSelection_pageNumberLbl;
	private JLabel levelSelection_pageNumberLbl;
	private JLabel answersPreview_pageNumberLbl;
	
	private JButton subjectGrid_subject_1;
	private JButton subjectGrid_subject_2;
	private JButton subjectGrid_subject_3;
	private JButton subjectGrid_subject_4;
	private JButton subjectGrid_subject_5;
	private ArrayList<JButton> subjectBtns = new ArrayList<JButton>();
	
	private JButton levelGrid_level_1;
	private JButton levelGrid_level_2;
	private JButton levelGrid_level_3;
	private JButton levelGrid_level_4;
	private JButton levelGrid_level_5;
	private ArrayList<JButton> levelBtns = new ArrayList<JButton>();
	
	private JButton gameArea_nextBtn;
	private JButton gameArea_currentFlashcardView;
	private JButton flashcardPreview_flashcard;
	
	private CardLayout cardLayout;
	
	private mainWindowLogic windowLogic;
    
    
   public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	mainWindowLogic windowLogic = new mainWindowLogic();
                	
                    mainWindow frame = new mainWindow(windowLogic);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   /////////////////////////////////////////////////////////////////////////////
   // utility methods
   private <T> T getClientPropertySafe(JComponent component, String key, Class<T> type) {
	   Object value = component.getClientProperty(key);
	   if (type.isInstance(value)) {
		   return type.cast(value);
	   } else {
		   return null;
	   }
   }
   private void updatePageNumber(JLabel pageNumberLbl, int left, int right) {
	   String page = "%d/%d";
	   page = String.format(page, left, right);
	   pageNumberLbl.setText(page);
   }
   private void showFlashcardData(JButton flashcardWidget) {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(flashcardWidget, "flashcards", ArrayList.class);
	   Integer index = getClientPropertySafe(flashcardWidget, "index", Integer.class);
	   Character viewMode = getClientPropertySafe(flashcardWidget, "viewMode", Character.class);
	   
	   if (flashcards == null || index == null || viewMode == null || index < 0 || index >= flashcards.size()) {
	        return; // Prevents crashes due to invalid data
	   }
	   
	   Flashcard flashcard = windowLogic.getFlashcardAt(flashcards, index);
	   
	   if (flashcard == null) return;
    		flashcardWidget.putClientProperty("question", flashcard.getQuestion());
    		flashcardWidget.putClientProperty("answer", flashcard.getAnswer());
    		flashcardWidget.setText(windowLogic.getFlashcardText(flashcard, viewMode));
   }
   private boolean traverseFlashcards(JButton flashcardWidget, JLabel pageNumberLbl, int direction) {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(flashcardWidget, "flashcards", ArrayList.class);
	   Integer currentIndex = getClientPropertySafe(flashcardWidget, "index", Integer.class);
	   
	   if (flashcards == null || currentIndex == null) {
	        return true; // Prevents crashes if data is missing
	   }

	   Integer newIndex = currentIndex + direction;
	   
	   if (newIndex >= 0 && newIndex < flashcards.size()) {
		   flashcardWidget.putClientProperty("index", newIndex);
		   showFlashcardData(flashcardWidget);
		   updatePageNumber(pageNumberLbl, newIndex+1, flashcards.size());
		   return false;
	   }
	   return true;
   }      
   private void toggleFlashcardMode(JButton flashcardWidget) {
	   
	   Character viewMode = getClientPropertySafe(flashcardWidget, "viewMode", Character.class);
	   
	   switch (viewMode){
	   case 'a':
		   flashcardWidget.putClientProperty("viewMode", 'q');
		   break;
	   case 'q':
		   flashcardWidget.putClientProperty("viewMode", 'a');
		   break;
	   }
	   
   }
   /////////////////////////////////////////////////////////////////////////////

   
   /////////////////////////////////////////////////////////////////////////////
   // Methods to initialize the selection grids
   private void initGridBtns() {
	   // subjects
	   subjectBtns.add(subjectGrid_subject_1);
	   subjectBtns.add(subjectGrid_subject_2);
	   subjectBtns.add(subjectGrid_subject_3);
	   subjectBtns.add(subjectGrid_subject_4);
	   subjectBtns.add(subjectGrid_subject_5);
	   
	   // levels
	   levelBtns.add(levelGrid_level_1);
	   levelBtns.add(levelGrid_level_2);
	   levelBtns.add(levelGrid_level_3);
	   levelBtns.add(levelGrid_level_4);
	   levelBtns.add(levelGrid_level_5);
   }
   private <T> void populateGrid(
		   JPanel selectionPane, 
		   ArrayList<JButton> gridBtns, 
		   JLabel pageNumberLbl,
		   ArrayList <ArrayList<T>> data, 
		   int index, 
		   String propertyName) {
	   
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
   private void initSubjectSelection() {
	   Integer initialIndex = 0;
	   subjectSelection.putClientProperty("index", initialIndex);
	   populateSubjects(initialIndex);
	   
	   cardLayout.show(contentPane, "subjectSelection");
   }
   private void initLevelSelection(JButton selectedSubject) {
	   Subject subject = getClientPropertySafe(selectedSubject, "subject", Subject.class);
	   
	   if (subject == null) {
		   JOptionPane.showMessageDialog(null, "This grid section is empty! Create a new one using the Subject Editor!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   Integer initialIndex = 0;
	   levelSelection.putClientProperty("subject", subject);
	   levelSelection.putClientProperty("index", initialIndex);
	   populateLevels(subject, initialIndex);
	   
	   cardLayout.show(contentPane, "levelSelection");
   }
   private void populateSubjects(int index) {
	   // clear old subjects list then repopulate with new subjects
	   emptyGrid(subjectBtns, "subject");
	   ArrayList< ArrayList<Subject> > subjects = windowLogic.getSubjectsList();
	   populateGrid(subjectSelection, subjectBtns, subjectSelection_pageNumberLbl, subjects, index, "subject");   
   } 
   private void populateLevels(Subject subject, int index) {
	   emptyGrid(levelBtns, "level");
	   ArrayList<ArrayList<Level>> levels = windowLogic.getLevelsList(subject);
	   populateGrid(levelSelection, levelBtns, levelSelection_pageNumberLbl, levels, index, "level");
   } 
   private void emptyGrid(ArrayList<JButton> gridBtns, String propertyName) {
	   for (JButton button: gridBtns) {
		   button.setText("");
		   button.putClientProperty(propertyName, null);
	   }
   }  
   private void toggleLevels(char direction) {
	   	Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	   	Integer oldIndex = getClientPropertySafe(levelSelection, "index", Integer.class);
	   	ArrayList< ArrayList<Level> > levels = getClientPropertySafe(levelSelection, "levels", ArrayList.class);
	   	
	   	int newIndex = windowLogic.toggleDataSet(levels, direction, oldIndex);
	   	levelSelection.putClientProperty("index", newIndex);
		
		populateLevels(subject, newIndex);
   }
   private void toggleSubjects(char direction) {
	   	Integer oldIndex = getClientPropertySafe(subjectSelection, "index", Integer.class);
	   	
	   	ArrayList< ArrayList<Subject> > subjects = getClientPropertySafe(subjectSelection, "subjects", ArrayList.class);
	   	
	   	int newIndex = windowLogic.toggleDataSet(subjects, direction, oldIndex);
	   	subjectSelection.putClientProperty("index", newIndex);
	   	
		populateSubjects(newIndex);
   }
   /////////////////////////////////////////////////////////////////////////////
   
   
   /////////////////////////////////////////////////////////////////////////////
   // timer methods	
   private void setTimer() {
	   Level level = (Level) getClientPropertySafe(gameArea, "level", Level.class);
	  
	   gameArea_timerPane.putClientProperty("remainingTime", level.getTimer());
	   gameArea_timerPane.putClientProperty("isTimerEnabled", level.getTimerEnabled());
	   
	   Boolean isEnabled = getClientPropertySafe(gameArea_timerPane, "isTimerEnabled", Boolean.class);
	   
	   if (isEnabled) {
		   gameArea_timerPane.setEnabled(true);
		   startTimer();
	   }
	   
	   else {
		   gameArea_timerPane.setEnabled(false);
	   }
   }
   private void startTimer() {
	   ActionListener runTimer = new ActionListener() {
		   public void actionPerformed(ActionEvent evt) {
			   Integer time = getClientPropertySafe(gameArea_timerPane, "remainingTime", Integer.class);
			   gameArea_timerPane.setText(time.toString());
			   
			   if (time > 0) {
				   gameArea_timerPane.putClientProperty("remainingTime", --time);
			   }
			   
			   else if (time <= 0) {
				   endGame();
				   
			   }
		   }
	   };
	   
	   Timer timer = new Timer(1000, runTimer);
	   timer.start();
	   
	   gameArea_timerPane.putClientProperty("timerMechanism", timer);
   }
   private void stopTimer() {
	   Timer timer = getClientPropertySafe(gameArea_timerPane, "timerMechanism", Timer.class);
	   if (timer != null) {
		   timer.stop();
	   }
	   gameArea_timerPane.putClientProperty("timerMechanism", null);
   }
   /////////////////////////////////////////////////////////////////////////////

   
   /////////////////////////////////////////////////////////////////////////////
   // game initialization
   private void initGameArea(JButton selectedLevel) {
	   Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	   Level level = getClientPropertySafe(selectedLevel, "level", Level.class);
	   
	   // check one
	   if (level == null) {
		   JOptionPane.showMessageDialog(null, "This grid section is empty! Create a new one using the Level Editor!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   ArrayList<Flashcard> flashcards = windowLogic.getFlashcards(level);
	   
	   // check two
	   if (windowLogic.checkEmptyFlashcards(flashcards)) {
		   JOptionPane.showMessageDialog(null, "This level is empty! Add flashcards using the Level Editor (Right click)!", "Error", JOptionPane.ERROR_MESSAGE);
		   return;
	   }
	   
	   setGameData(subject, level, flashcards);
	   clearLastGameData();
	   setupStartBtn();
	   
	   cardLayout.show(contentPane, "gameArea");
   }   
   private void setGameData(Subject subject, Level level, ArrayList<Flashcard> flashcards) {
	   gameArea.putClientProperty("subject", subject);
	   gameArea.putClientProperty("level", level);

	   gameArea_currentFlashcardView.putClientProperty("flashcards", flashcards);
	   // always start at beginning of flashcards
	   gameArea_currentFlashcardView.putClientProperty("index", 0); 
	   // always view in question mode
	   gameArea_currentFlashcardView.putClientProperty("viewMode", 'q'); 
	   // an empty placeholder for user answers
	   gameArea_currentFlashcardView.putClientProperty("userAnswers", new ArrayList<String>());
	   gameArea_currentFlashcardView.putClientProperty("revealAnswer", false);
   }
   private void clearLastGameData() {
	   // clear last game data
	   gameArea.putClientProperty("flashcards", null);
	   gameArea.putClientProperty("userAnswers", null);
	   gameArea.putClientProperty("currentFlashcardIndex", null);
   }
   private void setupStartBtn() {
	   // create a clickable start button before game formally starts
	   gameArea_currentFlashcardView.setText("START!");
	   gameArea_answerField.setEnabled(false);
   }
   /////////////////////////////////////////////////////////////////////////////
   
   
   /////////////////////////////////////////////////////////////////////////////
   // game session control
   private void startGame() {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(gameArea_currentFlashcardView, "flashcards", ArrayList.class);
	   Integer index = (Integer) getClientPropertySafe(gameArea_currentFlashcardView, "index", Integer.class);
	   
	   gameArea_answerField.setText("");
	   gameArea_answerField.setEnabled(true);
	   showFlashcardData(gameArea_currentFlashcardView); // show 1st flashcard at the start of the game
	   updatePageNumber(gameArea_remainingFlashcardsLbl, index+1, flashcards.size());
	   setTimer();  
   }  
   private void addUserAnswer() {
	   ArrayList<String> userAnswers = getClientPropertySafe(gameArea_currentFlashcardView, "userAnswers", ArrayList.class);
	   String userAnswer = gameArea_answerField.getText();
	   userAnswers.add(userAnswer);
	   gameArea_currentFlashcardView.putClientProperty("userAnswers", userAnswers);
	   gameArea_answerField.setText("");
   }  
   private void handleNextFlashcard() {
	   boolean fullyTraversed = traverseFlashcards(gameArea_currentFlashcardView, gameArea_remainingFlashcardsLbl, 1);
	
	   gameArea_currentFlashcardView.putClientProperty("revealAnswer", false);
	   gameArea_currentFlashcardView.setEnabled(true);
	   gameArea_answerField.setEnabled(true);
	
	   if (fullyTraversed) {
	       endGame();
	   }
   }
   private void markUnansweredFlashcards() {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(gameArea_currentFlashcardView, "flashcards", ArrayList.class);
	   ArrayList<String> userAnswers = getClientPropertySafe(gameArea_currentFlashcardView, "userAnswers", ArrayList.class);
	   
	   // TODO: Fix flashcard pages only updating after game has started
	   Integer index = getClientPropertySafe(gameArea_currentFlashcardView, "index", Integer.class);
	   
	   System.out.println(userAnswers.size());
	   
	   if (userAnswers.size() >= 1) {
	   
		   for (int i=index; i<flashcards.size(); i++) {
			   	if (userAnswers.get(i).isBlank()) {
				   userAnswers.add(i, "");
			   	}
		   }
		  
	   }
   }
   private void endGame() {
	   markUnansweredFlashcards();
	   stopTimer();
	   initResultsArea();
   }
   
   private void abortGame() {
	   markUnansweredFlashcards();
	   stopTimer();
   }
   /////////////////////////////////////////////////////////////////////////////
   
   
   /////////////////////////////////////////////////////////////////////////////
   // results initialization
   private void initResultsArea() {
	   if (!validateGameData()) return;
	   
	   setFlashcardPreview();
	   displayScore();
	   cardLayout.show(contentPane, "resultsArea");
   }
   private boolean validateGameData() {
	    ArrayList<Flashcard> flashcards = getClientPropertySafe(gameArea_currentFlashcardView, "flashcards", ArrayList.class);
	    ArrayList<String> userAnswers = getClientPropertySafe(gameArea_currentFlashcardView, "userAnswers", ArrayList.class);
	   
	    if (flashcards == null || flashcards.isEmpty() || userAnswers == null) {
	        JOptionPane.showMessageDialog(null, "Error: No flashcards or answers available.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } else {
	    	return true;
	    }
  }
   private void setFlashcardPreview() {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(gameArea_currentFlashcardView, "flashcards", ArrayList.class);
	   Integer defaultIndex = 0;
	  
	   flashcardPreview_flashcard.putClientProperty("index", defaultIndex);
	   flashcardPreview_flashcard.putClientProperty("viewMode", 'q');
	   flashcardPreview_flashcard.putClientProperty("flashcards", flashcards);
	   showFlashcardData(flashcardPreview_flashcard);
	   updatePageNumber(answersPreview_pageNumberLbl, defaultIndex+1, flashcards.size());
   } 
   private void showAnswerComparison() {
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(flashcardPreview_flashcard, "flashcards", ArrayList.class);;
	   ArrayList<String> userAnswers = getClientPropertySafe(gameArea_currentFlashcardView, "userAnswers", ArrayList.class);
	   Integer index = (Integer) getClientPropertySafe(flashcardPreview_flashcard, "index", Integer.class);
	   
	   Flashcard currentFlashcard = windowLogic.getFlashcardAt(flashcards, index);
	   String userAnswer = userAnswers.get(index); // internal data from the app itself
	   String correctAnswer = windowLogic.getFlashcardText(currentFlashcard, 'a');
	   
	   answersPreview_yourAnswerPane.setText(userAnswer);
	   answersPreview_correctAnswerPane.setText(correctAnswer);
   } 
   private void displayScore() {
	   Subject subject = getClientPropertySafe(gameArea, "subject", Subject.class);
	   Level level =  getClientPropertySafe(gameArea, "level", Level.class);
	   ArrayList<Flashcard> flashcards = getClientPropertySafe(gameArea_currentFlashcardView, "flashcards", ArrayList.class);
	   ArrayList<String> userAnswers = getClientPropertySafe(gameArea_currentFlashcardView, "userAnswers", ArrayList.class);

	   Integer score = windowLogic.getScore(flashcards, userAnswers);
	   String scoreStr = String.format("%d / %d", score, flashcards.size());
	   Integer percent = windowLogic.getScorePercentage(score, flashcards.size());
	   
	   scoreGrid_subjectField.setText(subject.getName());
	   scoreGrid_levelField.setText(level.getName());
	   scoreGrid_yourScoreField.setText(scoreStr);
	   scoreGrid_percentField.setText(percent.toString());
	   
	   showAnswerComparison();
	   windowLogic.saveLevelHighscore(subject, level, score);
   } 
   /////////////////////////////////////////////////////////////////////////////
   
   /////////////////////////////////////////////////////////////////////////////
   // opening other windows
   private void openSubjectEditor(Subject subject) {
	   subjectEditorLogic windowLogic = new subjectEditorLogic();
	   subjectEditorWindow subjectEditor = new subjectEditorWindow(windowLogic);
	   
	   if (subject != null) {
		   subjectEditor.setEditMode(subject);
	   } else {
		   subjectEditor.setCreateMode();
	   }
	   
	   subjectEditor.setVisible(true);
	   
	   subjectEditor.addWindowListener(new WindowAdapter() {
		   @Override
		   public void windowClosed(WindowEvent e) {
			   Integer index = getClientPropertySafe(subjectSelection, "index", Integer.class);
			   populateSubjects(index);
		   }
	   });
	   
   }
   
   private void openLevelEditor(Subject subject, Level level) {
	   levelEditorLogic windowLogic = new levelEditorLogic();
	   levelEditorWindow levelEditor = new levelEditorWindow(windowLogic);
	   
	   if (level != null) {
		   levelEditor.setEditMode(subject, level);
	   } else {
		   levelEditor.setCreateMode(subject);
	   }
	   
	   levelEditor.setVisible(true);
	   
	   levelEditor.addWindowListener(new WindowAdapter() {
		   @Override
		   public void windowClosed(WindowEvent e) {
			   // refresh implementation
			   Integer index = getClientPropertySafe(levelSelection, "index", Integer.class);
			   populateLevels(subject, index);
		   }
	   });
	   
   }
   /////////////////////////////////////////////////////////////////////////////
   
   // main method
   public mainWindow(mainWindowLogic logic) {
    	this.windowLogic = logic;
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        
        cardLayout = (CardLayout) contentPane.getLayout(); // ADDED BY PROGRAMMER
    
	    JPanel welcomeScreen = new JPanel();
	    contentPane.add(welcomeScreen, "welcomeScreen");
	    GridBagLayout gbl_welcomeScreen = new GridBagLayout();
	    gbl_welcomeScreen.columnWidths = new int[]{775, 0};
	    gbl_welcomeScreen.rowHeights = new int[]{300, 0, 0};
	    gbl_welcomeScreen.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_welcomeScreen.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
	    welcomeScreen.setLayout(gbl_welcomeScreen);
	    
	    JLabel welcomeScreen_appLbl = new JLabel("FLASHCARD QUIZ");
	    welcomeScreen_appLbl.setFont(new Font("Dialog", Font.BOLD, 24));
	    GridBagConstraints gbc_welcomeScreen_appLbl = new GridBagConstraints();
	    gbc_welcomeScreen_appLbl.anchor = GridBagConstraints.SOUTH;
	    gbc_welcomeScreen_appLbl.insets = new Insets(0, 0, 5, 0);
	    gbc_welcomeScreen_appLbl.gridx = 0;
	    gbc_welcomeScreen_appLbl.gridy = 0;
	    welcomeScreen.add(welcomeScreen_appLbl, gbc_welcomeScreen_appLbl);
	    
	    JPanel welcomeScreen_bottomPanel = new JPanel();
	    GridBagConstraints gbc_welcomeScreen_bottomPanel = new GridBagConstraints();
	    gbc_welcomeScreen_bottomPanel.fill = GridBagConstraints.HORIZONTAL;
	    gbc_welcomeScreen_bottomPanel.gridx = 0;
	    gbc_welcomeScreen_bottomPanel.gridy = 1;
	    welcomeScreen.add(welcomeScreen_bottomPanel, gbc_welcomeScreen_bottomPanel);
	    
	    JButton welcomeScreen_playBtn = new JButton("PLAY");
	    welcomeScreen_playBtn.setFont(new Font("SansSerif", Font.PLAIN, 24));
	    welcomeScreen_playBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		initSubjectSelection();
	    	}
	    });
	    JButton welcomeScreen_quitBtn = new JButton("QUIT");
	    welcomeScreen_quitBtn.setFont(new Font("SansSerif", Font.PLAIN, 24));
	    welcomeScreen_quitBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		System.exit(0);
	    	}
	    });
	    welcomeScreen_bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
	    welcomeScreen_quitBtn.setForeground(Color.RED);
	    welcomeScreen_bottomPanel.add(welcomeScreen_quitBtn);
	    welcomeScreen_playBtn.setForeground(new Color(0, 128, 0));
	    welcomeScreen_bottomPanel.add(welcomeScreen_playBtn);
	    
	    subjectSelection = new JPanel();
	    contentPane.add(subjectSelection, "subjectSelection");
	    subjectSelection.setLayout(new BorderLayout(0, 5));
	    
	    JPanel subjectSelection_topPanel = new JPanel();
	    subjectSelection.add(subjectSelection_topPanel, BorderLayout.NORTH);
	    subjectSelection_topPanel.setLayout(new BorderLayout(0, 0));
	    
	    JButton subjectSelection_backBtn = new JButton("BACK");
	    subjectSelection_backBtn.addMouseListener(new MouseAdapter() {	
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		cardLayout.show(contentPane, "welcomeScreen");
	    	}
	    });
	    subjectSelection_topPanel.add(subjectSelection_backBtn, BorderLayout.WEST);
	    
	    JButton subjectSelection_statsBtn = new JButton("STATS");
	    subjectSelection_statsBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		statisticsWindow stats = new statisticsWindow();
	    		stats.setVisible(true);
	    	}
	    });
	    subjectSelection_topPanel.add(subjectSelection_statsBtn, BorderLayout.EAST);
	    
	    JLabel subjectSelection_titleLbl = new JLabel("SELECT SUBJECT:");
	    subjectSelection_titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
	    subjectSelection_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    subjectSelection_topPanel.add(subjectSelection_titleLbl, BorderLayout.CENTER);
	    
	    JPanel subjectSelection_subjectGrid = new JPanel();
	    subjectSelection.add(subjectSelection_subjectGrid, BorderLayout.CENTER);
	    subjectSelection_subjectGrid.setLayout(new GridLayout(2, 3, 10, 10));
	    
	    subjectGrid_subject_1 = new JButton("");
	    subjectGrid_subject_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	        		initLevelSelection(subjectGrid_subject_1);
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && subjectGrid_subject_1.getText() != "") {
	    			Subject subject = getClientPropertySafe(subjectGrid_subject_1, "subject", Subject.class);
	    			openSubjectEditor(subject);
	    		}
	    	}
	    });
	    subjectSelection_subjectGrid.add(subjectGrid_subject_1);
	    
	    subjectGrid_subject_2 = new JButton("");
	    subjectGrid_subject_2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	        		initLevelSelection(subjectGrid_subject_2);
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && subjectGrid_subject_2.getText() != "") {
	    			Subject subject = getClientPropertySafe(subjectGrid_subject_2, "subject", Subject.class);
	    			openSubjectEditor(subject);
	    		}
	    	}
	    });
	    subjectSelection_subjectGrid.add(subjectGrid_subject_2);
	    
	    subjectGrid_subject_3 = new JButton("");
	    subjectGrid_subject_3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	        		initLevelSelection(subjectGrid_subject_3);
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && subjectGrid_subject_3.getText() != "") {
	    			Subject subject = getClientPropertySafe(subjectGrid_subject_3, "subject", Subject.class);
	    			openSubjectEditor(subject);
	    		}
	    	}
	    });
	    subjectSelection_subjectGrid.add(subjectGrid_subject_3);
	    
	    subjectGrid_subject_4 = new JButton("");
	    subjectGrid_subject_4.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	        		initLevelSelection(subjectGrid_subject_4);
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && subjectGrid_subject_4.getText() != "") {
	    			Subject subject = getClientPropertySafe(subjectGrid_subject_4, "subject", Subject.class);
	    			openSubjectEditor(subject);
	    		}
	    	}
	    });
	    subjectSelection_subjectGrid.add(subjectGrid_subject_4);
	    
	    subjectGrid_subject_5 = new JButton("");
	    subjectGrid_subject_5.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	        		initLevelSelection(subjectGrid_subject_5);
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && subjectGrid_subject_5.getText() != "") {
	    			Subject subject = getClientPropertySafe(subjectGrid_subject_5, "subject", Subject.class);
	    			openSubjectEditor(subject);
	    		}
	    	}
	    });
	    subjectSelection_subjectGrid.add(subjectGrid_subject_5);
	    
	    JButton subjectGrid_addBtn = new JButton("+");
	    subjectGrid_addBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		openSubjectEditor(null);
	    	}
	    });
	    subjectGrid_addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
	    subjectSelection_subjectGrid.add(subjectGrid_addBtn);
	    
	    JPanel subjectSelection_bottomPanel = new JPanel();
	    subjectSelection.add(subjectSelection_bottomPanel, BorderLayout.SOUTH);
	    
	    subjectSelection_pageNumberLbl = new JLabel("0/0");
	    subjectSelection_bottomPanel.add(subjectSelection_pageNumberLbl);
	    
	    JButton subjectSelection_pageBackBtn = new JButton("<");
	    subjectSelection_pageBackBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
    			toggleSubjects('-');
	    	}
	    });
	    subjectSelection_pageBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    subjectSelection_bottomPanel.add(subjectSelection_pageBackBtn);
	    
	    JButton subjectSelection_pageFwdBtn = new JButton(">");
	    subjectSelection_pageFwdBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		toggleSubjects('+');
	    	}
	    });
	    subjectSelection_pageFwdBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    subjectSelection_bottomPanel.add(subjectSelection_pageFwdBtn);
	    
	    levelSelection = new JPanel();
	    contentPane.add(levelSelection, "levelSelection");
	    levelSelection.setLayout(new BorderLayout(0, 0));
	    
	    JPanel levelSelection_topPanel = new JPanel();
	    levelSelection.add(levelSelection_topPanel, BorderLayout.NORTH);
	    levelSelection_topPanel.setLayout(new BorderLayout(0, 0));
	    
	    JButton levelSelection_backBtn = new JButton("BACK");
	    levelSelection_backBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					cardLayout.previous(contentPane);
	    			emptyGrid(levelBtns, "level");
	    		}
	    	}
	    });
	    levelSelection_topPanel.add(levelSelection_backBtn, BorderLayout.WEST);
	    
	    JLabel levelSelection_titleLbl = new JLabel("SELECT LEVEL:");
	    levelSelection_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    levelSelection_titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
	    levelSelection_topPanel.add(levelSelection_titleLbl, BorderLayout.CENTER);
	    
	    JPanel levelSelection_levelGrid = new JPanel();
	    levelSelection.add(levelSelection_levelGrid, BorderLayout.CENTER);
	    levelSelection_levelGrid.setLayout(new GridLayout(0, 2, 10, 10));
	    
	    levelGrid_level_1 = new JButton("");
	    levelGrid_level_1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		// correct implementation, just needs data from the level into this editor.
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	    			initGameArea(levelGrid_level_1);
	    			
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && levelGrid_level_1.getText() != "") {
	    			Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	    			Level level = getClientPropertySafe(levelGrid_level_1, "level", Level.class);
	    			openLevelEditor(subject, level);
	    		}
	    		
	    	}
	    });
	    levelSelection_levelGrid.add(levelGrid_level_1);
	    
	    levelGrid_level_2 = new JButton("");
	    levelGrid_level_2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	    			initGameArea(levelGrid_level_2);
	    			
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && levelGrid_level_2.getText() != "") {
	    			Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	    			Level level = getClientPropertySafe(levelGrid_level_2, "level", Level.class);
	    			openLevelEditor(subject, level);
	    		}
	    	}
	    });
	    levelSelection_levelGrid.add(levelGrid_level_2);
	    
	    levelGrid_level_3 = new JButton("");
	    levelGrid_level_3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	    			initGameArea(levelGrid_level_3);
	    			
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && levelGrid_level_3.getText() != "") {
	    			Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	    			Level level = getClientPropertySafe(levelGrid_level_3, "level", Level.class);
	    			openLevelEditor(subject, level);
	    		}
	    	}
	    });
	    levelSelection_levelGrid.add(levelGrid_level_3);
	    
	    levelGrid_level_4 = new JButton("");
	    levelGrid_level_4.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	    			initGameArea(levelGrid_level_4);
	    			
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && levelGrid_level_4.getText() != "") {
	    			Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	    			Level level = getClientPropertySafe(levelGrid_level_4, "level", Level.class);
	    			openLevelEditor(subject, level);
	    		}
	    	}
	    });
	    levelSelection_levelGrid.add(levelGrid_level_4);
	    
	    levelGrid_level_5 = new JButton("");
	    levelGrid_level_5.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		if (e.getButton() == MouseEvent.BUTTON1) {
	    			initGameArea(levelGrid_level_5);
	    			
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && levelGrid_level_5.getText() != "") {
	    			Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
	    			Level level = getClientPropertySafe(levelGrid_level_5, "level", Level.class);
	    			openLevelEditor(subject, level);
	    		}

	    	}
	    });
	    levelSelection_levelGrid.add(levelGrid_level_5);
	    
	    JButton levelGrid_addBtn = new JButton("+");
	    levelGrid_addBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		Subject subject = getClientPropertySafe(levelSelection, "subject", Subject.class);
    			openLevelEditor(subject, null);
	    	}
	    });
	    levelGrid_addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
	    levelSelection_levelGrid.add(levelGrid_addBtn);
	    
	    JPanel levelSelection_bottomPanel = new JPanel();
	    levelSelection.add(levelSelection_bottomPanel, BorderLayout.SOUTH);
	    
	    levelSelection_pageNumberLbl = new JLabel("0/0");
	    levelSelection_bottomPanel.add(levelSelection_pageNumberLbl);
	    
	    JButton levelSelection_pageBackBtn = new JButton("<");
	    levelSelection_pageBackBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		toggleLevels('-');
	    	}
	    });
	    levelSelection_pageBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    levelSelection_bottomPanel.add(levelSelection_pageBackBtn);
	    
	    JButton levelSelection_pageFwdBtn = new JButton(">");
	    levelSelection_pageFwdBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		toggleLevels('+');
	    	}
	    });
	    levelSelection_pageFwdBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    levelSelection_bottomPanel.add(levelSelection_pageFwdBtn);
	    
	    gameArea = new JPanel();
	    contentPane.add(gameArea, "gameArea");
	    gameArea.setLayout(new BorderLayout(0, 10));
	    
	    JPanel gameArea_topPanel = new JPanel();
	    gameArea.add(gameArea_topPanel, BorderLayout.NORTH);
	    GridBagLayout gbl_gameArea_topPanel = new GridBagLayout();
	    gbl_gameArea_topPanel.columnWidths = new int[]{85, 586, 93, 0};
	    gbl_gameArea_topPanel.rowHeights = new int[]{50, 0};
	    gbl_gameArea_topPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	    gbl_gameArea_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	    gameArea_topPanel.setLayout(gbl_gameArea_topPanel);
	    
	    JButton gameArea_exitBtn = new JButton("EXIT");
	    gameArea_exitBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		abortGame();
	    		cardLayout.show(contentPane, "levelSelection");
	    	}
	    });
	    GridBagConstraints gbc_gameArea_exitBtn = new GridBagConstraints();
	    gbc_gameArea_exitBtn.fill = GridBagConstraints.BOTH;
	    gbc_gameArea_exitBtn.anchor = GridBagConstraints.NORTHWEST;
	    gbc_gameArea_exitBtn.insets = new Insets(0, 0, 0, 5);
	    gbc_gameArea_exitBtn.gridx = 0;
	    gbc_gameArea_exitBtn.gridy = 0;
	    gameArea_topPanel.add(gameArea_exitBtn, gbc_gameArea_exitBtn);
	    
	    gameArea_remainingFlashcardsLbl = new JLabel("0/0");
	    gameArea_remainingFlashcardsLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    gameArea_remainingFlashcardsLbl.setFont(new Font("SansSerif", Font.PLAIN, 24));
	    GridBagConstraints gbc_gameArea_remainingFlashcardsLbl = new GridBagConstraints();
	    gbc_gameArea_remainingFlashcardsLbl.insets = new Insets(0, 0, 0, 5);
	    gbc_gameArea_remainingFlashcardsLbl.gridx = 1;
	    gbc_gameArea_remainingFlashcardsLbl.gridy = 0;
	    gameArea_topPanel.add(gameArea_remainingFlashcardsLbl, gbc_gameArea_remainingFlashcardsLbl);
	    
	    gameArea_timerPane = new JTextField();
	    gameArea_timerPane.setFont(new Font("SansSerif", Font.BOLD, 24));
	    gameArea_timerPane.setHorizontalAlignment(SwingConstants.CENTER);
	    gameArea_timerPane.setEditable(false);
	    gameArea_timerPane.setText("0");
	    gameArea_timerPane.setBorder(new LineBorder(new Color(0, 0, 0)));
	    GridBagConstraints gbc_gameArea_timerPane = new GridBagConstraints();
	    gbc_gameArea_timerPane.fill = GridBagConstraints.BOTH;
	    gbc_gameArea_timerPane.gridx = 2;
	    gbc_gameArea_timerPane.gridy = 0;
	    gameArea_topPanel.add(gameArea_timerPane, gbc_gameArea_timerPane);
	    
	    JPanel gameArea_centerPanel = new JPanel();
	    gameArea.add(gameArea_centerPanel, BorderLayout.CENTER);
	    gameArea_centerPanel.setLayout(new BorderLayout(10, 10));
	    
	    gameArea_currentFlashcardView = new JButton("START!");
	    gameArea_currentFlashcardView.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
	    		Boolean revealAnswer = getClientPropertySafe(gameArea_currentFlashcardView, "revealAnswer", Boolean.class);
	    		
	    		if (e.getButton() == MouseEvent.BUTTON1 && gameArea_currentFlashcardView.getText() == "START!") {
	    			startGame();
	    		} else if (e.getButton() == MouseEvent.BUTTON3 && !revealAnswer) {
	    			int choice = JOptionPane.showConfirmDialog(null, "Reveal Answer? This would not be counted as correct", "Confirm", JOptionPane.YES_NO_OPTION);
	    			if (choice == JOptionPane.YES_OPTION) {
	    				toggleFlashcardMode(gameArea_currentFlashcardView);
	    				showFlashcardData(gameArea_currentFlashcardView);
	    				gameArea_answerField.setEnabled(false);
	    				gameArea_currentFlashcardView.setEnabled(false);
	    			}
	    		}
	    	}
	    });
	    gameArea_currentFlashcardView.setFont(new Font("SansSerif", Font.PLAIN, 18));
	    gameArea_currentFlashcardView.setBackground(Color.WHITE);
	    gameArea_currentFlashcardView.setBorder(null);
	    gameArea_centerPanel.add(gameArea_currentFlashcardView);
	    
	    JPanel gameArea_actionPanel = new JPanel();
	    gameArea_centerPanel.add(gameArea_actionPanel, BorderLayout.SOUTH);
	    GridBagLayout gbl_gameArea_actionPanel = new GridBagLayout();
	    gbl_gameArea_actionPanel.columnWidths = new int[]{712, 0, 0};
	    gbl_gameArea_actionPanel.rowHeights = new int[]{29, 0};
	    gbl_gameArea_actionPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
	    gbl_gameArea_actionPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    gameArea_actionPanel.setLayout(gbl_gameArea_actionPanel);
	    
	    gameArea_answerField = new JTextField();
	    gameArea_answerField.setHorizontalAlignment(SwingConstants.CENTER);
	    gameArea_answerField.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	    			addUserAnswer();
	    			handleNextFlashcard();
	    		}
	    	}
	    });
	    gameArea_answerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    gameArea_answerField.setColumns(10);
	    GridBagConstraints gbc_gameArea_answerField = new GridBagConstraints();
	    gbc_gameArea_answerField.insets = new Insets(0, 0, 0, 5);
	    gbc_gameArea_answerField.anchor = GridBagConstraints.NORTH;
	    gbc_gameArea_answerField.fill = GridBagConstraints.HORIZONTAL;
	    gbc_gameArea_answerField.gridx = 0;
	    gbc_gameArea_answerField.gridy = 0;
	    gameArea_actionPanel.add(gameArea_answerField, gbc_gameArea_answerField);
	    
	    gameArea_nextBtn = new JButton(">");
	    gameArea_nextBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		addUserAnswer();
	    		handleNextFlashcard();
	    	}
	    });
	    GridBagConstraints gbc_gameArea_nextBtn = new GridBagConstraints();
	    gbc_gameArea_nextBtn.fill = GridBagConstraints.HORIZONTAL;
	    gbc_gameArea_nextBtn.gridx = 1;
	    gbc_gameArea_nextBtn.gridy = 0;
	    gameArea_actionPanel.add(gameArea_nextBtn, gbc_gameArea_nextBtn);
	    
	    Component verticalStrut = Box.createVerticalStrut(5);
	    gameArea.add(verticalStrut, BorderLayout.SOUTH);
	    
	    resultsArea = new JPanel();
	    contentPane.add(resultsArea, "resultsArea");
	    resultsArea.setLayout(new BorderLayout(0, 10));
	    
	    JPanel resultsArea_reviewPanel = new JPanel();
	    resultsArea_reviewPanel.setBorder(new TitledBorder(null, "Review", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    resultsArea.add(resultsArea_reviewPanel, BorderLayout.CENTER);
	    GridBagLayout gbl_resultsArea_reviewPanel = new GridBagLayout();
	    gbl_resultsArea_reviewPanel.columnWidths = new int[]{300, 473, 0};
	    gbl_resultsArea_reviewPanel.rowHeights = new int[]{424, 0};
	    gbl_resultsArea_reviewPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
	    gbl_resultsArea_reviewPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	    resultsArea_reviewPanel.setLayout(gbl_resultsArea_reviewPanel);
	    
	    JPanel resultsArea_flashcardPreview = new JPanel();
	    resultsArea_flashcardPreview.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    GridBagConstraints gbc_resultsArea_flashcardPreview = new GridBagConstraints();
	    gbc_resultsArea_flashcardPreview.fill = GridBagConstraints.BOTH;
	    gbc_resultsArea_flashcardPreview.insets = new Insets(0, 0, 0, 5);
	    gbc_resultsArea_flashcardPreview.gridx = 0;
	    gbc_resultsArea_flashcardPreview.gridy = 0;
	    resultsArea_reviewPanel.add(resultsArea_flashcardPreview, gbc_resultsArea_flashcardPreview);
	    resultsArea_flashcardPreview.setLayout(new GridLayout(0, 1, 0, 0));
	    
	    flashcardPreview_flashcard = new JButton("FLASHCARD");
	    flashcardPreview_flashcard.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		toggleFlashcardMode(flashcardPreview_flashcard);
	    		showFlashcardData(flashcardPreview_flashcard);
	    	}
	    });
	    flashcardPreview_flashcard.setFont(new Font("SansSerif", Font.BOLD, 22));
	    resultsArea_flashcardPreview.add(flashcardPreview_flashcard);
	    
	    JPanel resultsArea_answersPreview = new JPanel();
	    GridBagConstraints gbc_resultsArea_answersPreview = new GridBagConstraints();
	    gbc_resultsArea_answersPreview.fill = GridBagConstraints.HORIZONTAL;
	    gbc_resultsArea_answersPreview.gridx = 1;
	    gbc_resultsArea_answersPreview.gridy = 0;
	    resultsArea_reviewPanel.add(resultsArea_answersPreview, gbc_resultsArea_answersPreview);
	    GridBagLayout gbl_resultsArea_answersPreview = new GridBagLayout();
	    gbl_resultsArea_answersPreview.columnWidths = new int[]{445, 0};
	    gbl_resultsArea_answersPreview.rowHeights = new int[]{40, 65, 40, 70, 70, 0};
	    gbl_resultsArea_answersPreview.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_resultsArea_answersPreview.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	    resultsArea_answersPreview.setLayout(gbl_resultsArea_answersPreview);
	    
	    JLabel answersPreview_yourAnswerLbl = new JLabel("Your Answer:");
	    answersPreview_yourAnswerLbl.setVerticalAlignment(SwingConstants.BOTTOM);
	    answersPreview_yourAnswerLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_answersPreview_yourAnswerLbl = new GridBagConstraints();
	    gbc_answersPreview_yourAnswerLbl.anchor = GridBagConstraints.WEST;
	    gbc_answersPreview_yourAnswerLbl.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_yourAnswerLbl.insets = new Insets(0, 0, 5, 0);
	    gbc_answersPreview_yourAnswerLbl.gridx = 0;
	    gbc_answersPreview_yourAnswerLbl.gridy = 0;
	    resultsArea_answersPreview.add(answersPreview_yourAnswerLbl, gbc_answersPreview_yourAnswerLbl);
	    
	    answersPreview_yourAnswerPane = new JTextField();
	    answersPreview_yourAnswerPane.setEditable(false);
	    GridBagConstraints gbc_answersPreview_yourAnswerPane = new GridBagConstraints();
	    gbc_answersPreview_yourAnswerPane.fill = GridBagConstraints.BOTH;
	    gbc_answersPreview_yourAnswerPane.insets = new Insets(0, 0, 5, 0);
	    gbc_answersPreview_yourAnswerPane.gridx = 0;
	    gbc_answersPreview_yourAnswerPane.gridy = 1;
	    resultsArea_answersPreview.add(answersPreview_yourAnswerPane, gbc_answersPreview_yourAnswerPane);
	    answersPreview_yourAnswerPane.setColumns(10);
	    
	    JLabel answersPreview_correctAnswerLbl = new JLabel("Correct Answer:");
	    answersPreview_correctAnswerLbl.setVerticalAlignment(SwingConstants.BOTTOM);
	    answersPreview_correctAnswerLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_answersPreview_correctAnswerLbl = new GridBagConstraints();
	    gbc_answersPreview_correctAnswerLbl.anchor = GridBagConstraints.WEST;
	    gbc_answersPreview_correctAnswerLbl.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_correctAnswerLbl.insets = new Insets(0, 0, 5, 0);
	    gbc_answersPreview_correctAnswerLbl.gridx = 0;
	    gbc_answersPreview_correctAnswerLbl.gridy = 2;
	    resultsArea_answersPreview.add(answersPreview_correctAnswerLbl, gbc_answersPreview_correctAnswerLbl);
	    
	    answersPreview_correctAnswerPane = new JTextField();
	    answersPreview_correctAnswerPane.setEditable(false);
	    answersPreview_correctAnswerPane.setColumns(10);
	    GridBagConstraints gbc_answersPreview_correctAnswerPane = new GridBagConstraints();
	    gbc_answersPreview_correctAnswerPane.insets = new Insets(0, 0, 5, 0);
	    gbc_answersPreview_correctAnswerPane.fill = GridBagConstraints.BOTH;
	    gbc_answersPreview_correctAnswerPane.gridx = 0;
	    gbc_answersPreview_correctAnswerPane.gridy = 3;
	    resultsArea_answersPreview.add(answersPreview_correctAnswerPane, gbc_answersPreview_correctAnswerPane);
	    
	    JPanel answersPreview_actionPanel = new JPanel();
	    GridBagConstraints gbc_answersPreview_actionPanel = new GridBagConstraints();
	    gbc_answersPreview_actionPanel.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_actionPanel.gridx = 0;
	    gbc_answersPreview_actionPanel.gridy = 4;
	    resultsArea_answersPreview.add(answersPreview_actionPanel, gbc_answersPreview_actionPanel);
	    GridBagLayout gbl_answersPreview_actionPanel = new GridBagLayout();
	    gbl_answersPreview_actionPanel.columnWidths = new int[]{85, 45, 85, 0};
	    gbl_answersPreview_actionPanel.rowHeights = new int[]{70, 0};
	    gbl_answersPreview_actionPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gbl_answersPreview_actionPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	    answersPreview_actionPanel.setLayout(gbl_answersPreview_actionPanel);
	    
	    JButton answersPreview_prevBtn = new JButton("Prev");
	    answersPreview_prevBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		traverseFlashcards(flashcardPreview_flashcard, answersPreview_pageNumberLbl, -1);
	    		showAnswerComparison();
	    	}
	    });
	    GridBagConstraints gbc_answersPreview_prevBtn = new GridBagConstraints();
	    gbc_answersPreview_prevBtn.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_prevBtn.insets = new Insets(0, 0, 0, 5);
	    gbc_answersPreview_prevBtn.gridx = 0;
	    gbc_answersPreview_prevBtn.gridy = 0;
	    answersPreview_actionPanel.add(answersPreview_prevBtn, gbc_answersPreview_prevBtn);
	    
	    answersPreview_pageNumberLbl = new JLabel("0/0");
	    answersPreview_pageNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    GridBagConstraints gbc_answersPreview_pageNumberLbl = new GridBagConstraints();
	    gbc_answersPreview_pageNumberLbl.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_pageNumberLbl.insets = new Insets(0, 0, 0, 5);
	    gbc_answersPreview_pageNumberLbl.gridx = 1;
	    gbc_answersPreview_pageNumberLbl.gridy = 0;
	    answersPreview_actionPanel.add(answersPreview_pageNumberLbl, gbc_answersPreview_pageNumberLbl);
	    
	    JButton answersPreview_nextBtn = new JButton("Next");
	    answersPreview_nextBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		traverseFlashcards(flashcardPreview_flashcard, answersPreview_pageNumberLbl, 1);
	    		showAnswerComparison();
	
	        }
	    });
	    GridBagConstraints gbc_answersPreview_nextBtn = new GridBagConstraints();
	    gbc_answersPreview_nextBtn.fill = GridBagConstraints.VERTICAL;
	    gbc_answersPreview_nextBtn.gridx = 2;
	    gbc_answersPreview_nextBtn.gridy = 0;
	    answersPreview_actionPanel.add(answersPreview_nextBtn, gbc_answersPreview_nextBtn);
	    
	    JPanel resultsArea_bottomPanel = new JPanel();
	    resultsArea.add(resultsArea_bottomPanel, BorderLayout.SOUTH);
	    resultsArea_bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));
	    
	    JButton resultsArea_returnBtn = new JButton("SELECT SUBJECT");
	    resultsArea_returnBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		cardLayout.show(contentPane, "subjectSelection");
	    	}
	    });
	    resultsArea_returnBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    resultsArea_bottomPanel.add(resultsArea_returnBtn);
	    
	    JButton resultsArea_restartBtn = new JButton("SELECT LEVEL");
	    resultsArea_restartBtn.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		cardLayout.show(contentPane, "levelSelection");
	    	}
	    });
	    resultsArea_bottomPanel.add(resultsArea_restartBtn);
	    
	    JPanel resultsArea_topPanel = new JPanel();
	    resultsArea.add(resultsArea_topPanel, BorderLayout.NORTH);
	    resultsArea_topPanel.setLayout(new GridLayout(2, 1, 0, 0));
	    
	    JLabel resultsArea_titleLbl = new JLabel("RESULTS:");
	    resultsArea_topPanel.add(resultsArea_titleLbl);
	    resultsArea_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    resultsArea_titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
	    
	    JPanel resultsArea_scoreGrid = new JPanel();
	    resultsArea_topPanel.add(resultsArea_scoreGrid);
	    resultsArea_scoreGrid.setLayout(new GridLayout(2, 4, 5, 5));
	    
	    JLabel scoreGrid_subjectLbl = new JLabel("Subject:");
	    scoreGrid_subjectLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    resultsArea_scoreGrid.add(scoreGrid_subjectLbl);
	    
	    scoreGrid_subjectField = new JTextField();
	    scoreGrid_subjectField.setEditable(false);
	    scoreGrid_subjectField.setColumns(10);
	    resultsArea_scoreGrid.add(scoreGrid_subjectField);
	    
	    JLabel scoreGrid_yourScoreLbl = new JLabel("Score:");
	    scoreGrid_yourScoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    resultsArea_scoreGrid.add(scoreGrid_yourScoreLbl);
	    
	    scoreGrid_yourScoreField = new JTextField();
	    scoreGrid_yourScoreField.setEditable(false);
	    scoreGrid_yourScoreField.setColumns(10);
	    resultsArea_scoreGrid.add(scoreGrid_yourScoreField);
	    
	    JLabel scoreGrid_levelLbl = new JLabel("Level:");
	    scoreGrid_levelLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    resultsArea_scoreGrid.add(scoreGrid_levelLbl);
	    
	    scoreGrid_levelField = new JTextField();
	    scoreGrid_levelField.setEditable(false);
	    scoreGrid_levelField.setColumns(10);
	    resultsArea_scoreGrid.add(scoreGrid_levelField);
	    
	    JLabel scoreGrid_percentLbl = new JLabel("Percent:");
	    scoreGrid_percentLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    resultsArea_scoreGrid.add(scoreGrid_percentLbl);
	    
	    scoreGrid_percentField = new JTextField();
	    scoreGrid_percentField.setEditable(false);
	    scoreGrid_percentField.setColumns(10);
	    resultsArea_scoreGrid.add(scoreGrid_percentField);
	    
	    // ADDED BY PROGRAMMER! DO NOT REMOVE!
	    initGridBtns();
    }
}
