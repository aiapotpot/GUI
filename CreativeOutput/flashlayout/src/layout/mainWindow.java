package layout;

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
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SpringLayout;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class mainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField gameArea_answerField;
    private JTextField answersPreview_yourAnswerPane;
    private JTextField answersPreview_correctAnswerPane;
    private JTextField scoreGrid_subjectField;
    private JTextField scoreGrid_yourScoreField;
    private JTextField scoreGrid_levelField;
    private JTextField scoreGrid_percentField;

    
    public static void main(String[] args) {
    	try {
    		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainWindow frame = new mainWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public mainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));
        
        JPanel welcomeScreen = new JPanel();
        contentPane.add(welcomeScreen, "name_282256796268700");
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
        welcomeScreen_bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JButton welcomeScreen_quitBtn = new JButton("QUIT");
        welcomeScreen_quitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        welcomeScreen_quitBtn.setForeground(Color.RED);
        welcomeScreen_bottomPanel.add(welcomeScreen_quitBtn);
        
        JButton welcomeScreen_playBtn = new JButton("PLAY");
        welcomeScreen_playBtn.setForeground(new Color(0, 128, 0));
        welcomeScreen_bottomPanel.add(welcomeScreen_playBtn);
        
        JPanel subjectSelection = new JPanel();
        contentPane.add(subjectSelection, "name_282648083337500");
        subjectSelection.setLayout(new BorderLayout(0, 5));
        
        JPanel subjectSelection_topPanel = new JPanel();
        subjectSelection.add(subjectSelection_topPanel, BorderLayout.NORTH);
        subjectSelection_topPanel.setLayout(new BorderLayout(0, 0));
        
        JButton subjectSelection_backBtn = new JButton("BACK");
        subjectSelection_backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        subjectSelection_topPanel.add(subjectSelection_backBtn, BorderLayout.WEST);
        
        JButton subjectSelection_statsBtn = new JButton("STATS");
        subjectSelection_topPanel.add(subjectSelection_statsBtn, BorderLayout.EAST);
        
        JLabel subjectSelection_titleLbl = new JLabel("SELECT SUBJECT:");
        subjectSelection_titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
        subjectSelection_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        subjectSelection_topPanel.add(subjectSelection_titleLbl, BorderLayout.CENTER);
        
        JPanel subjectSelection_subjectGrid = new JPanel();
        subjectSelection.add(subjectSelection_subjectGrid, BorderLayout.CENTER);
        subjectSelection_subjectGrid.setLayout(new GridLayout(2, 3, 10, 10));
        
        JButton subjectGrid_subject_1 = new JButton("");
        subjectSelection_subjectGrid.add(subjectGrid_subject_1);
        
        JButton subjectGrid_subject_2 = new JButton("");
        subjectSelection_subjectGrid.add(subjectGrid_subject_2);
        
        JButton subjectGrid_subject_3 = new JButton("");
        subjectSelection_subjectGrid.add(subjectGrid_subject_3);
        
        JButton subjectGrid_subject_4 = new JButton("");
        subjectSelection_subjectGrid.add(subjectGrid_subject_4);
        
        JButton subjectGrid_subject_5 = new JButton("");
        subjectSelection_subjectGrid.add(subjectGrid_subject_5);
        
        JButton subjectGrid_addBtn = new JButton("+");
        subjectGrid_addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        subjectGrid_addBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        subjectSelection_subjectGrid.add(subjectGrid_addBtn);
        
        JPanel subjectSelection_bottomPanel = new JPanel();
        subjectSelection.add(subjectSelection_bottomPanel, BorderLayout.SOUTH);
        
        JLabel subjectSelection_pageNumberLbl = new JLabel("0/0");
        subjectSelection_bottomPanel.add(subjectSelection_pageNumberLbl);
        
        JButton subjectSelection_pageBackBtn = new JButton("<");
        subjectSelection_pageBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        subjectSelection_bottomPanel.add(subjectSelection_pageBackBtn);
        
        JButton subjectSelection_pageFwdBtn = new JButton(">");
        subjectSelection_pageFwdBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        subjectSelection_bottomPanel.add(subjectSelection_pageFwdBtn);
        
        JPanel levelSelection = new JPanel();
        contentPane.add(levelSelection, "name_283686756723700");
        levelSelection.setLayout(new BorderLayout(0, 0));
        
        JPanel levelSelection_topPanel = new JPanel();
        levelSelection.add(levelSelection_topPanel, BorderLayout.NORTH);
        levelSelection_topPanel.setLayout(new BorderLayout(0, 0));
        
        JButton levelSelection_backBtn = new JButton("BACK");
        levelSelection_topPanel.add(levelSelection_backBtn, BorderLayout.WEST);
        
        JLabel levelSelection_titleLbl = new JLabel("SELECT LEVEL:");
        levelSelection_titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        levelSelection_titleLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
        levelSelection_topPanel.add(levelSelection_titleLbl, BorderLayout.CENTER);
        
        JPanel levelSelection_levelGrid = new JPanel();
        levelSelection.add(levelSelection_levelGrid, BorderLayout.CENTER);
        levelSelection_levelGrid.setLayout(new GridLayout(0, 2, 10, 10));
        
        JButton btnNewButton_14 = new JButton("");
        levelSelection_levelGrid.add(btnNewButton_14);
        
        JButton btnNewButton_15 = new JButton("");
        levelSelection_levelGrid.add(btnNewButton_15);
        
        JButton btnNewButton_16 = new JButton("");
        levelSelection_levelGrid.add(btnNewButton_16);
        
        JButton btnNewButton_17 = new JButton("");
        levelSelection_levelGrid.add(btnNewButton_17);
        
        JButton btnNewButton_18 = new JButton("");
        levelSelection_levelGrid.add(btnNewButton_18);
        
        JButton levelGrid_addBtn = new JButton("+");
        levelGrid_addBtn.setFont(new Font("SansSerif", Font.BOLD, 18));
        levelSelection_levelGrid.add(levelGrid_addBtn);
        
        JPanel levelSelection_bottomPanel = new JPanel();
        levelSelection.add(levelSelection_bottomPanel, BorderLayout.SOUTH);
        
        JLabel levelSelection_pageNumberLbl = new JLabel("0/0");
        levelSelection_bottomPanel.add(levelSelection_pageNumberLbl);
        
        JButton levelSelection_pageBackBtn = new JButton("<");
        levelSelection_pageBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        levelSelection_bottomPanel.add(levelSelection_pageBackBtn);
        
        JButton levelSelection_pageFwdBtn = new JButton(">");
        levelSelection_pageFwdBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        levelSelection_bottomPanel.add(levelSelection_pageFwdBtn);
        
        JPanel gameArea = new JPanel();
        contentPane.add(gameArea, "name_285212948032700");
        gameArea.setLayout(new BorderLayout(0, 10));
        
        JPanel gameArea_topPanel = new JPanel();
        gameArea.add(gameArea_topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_gameArea_topPanel = new GridBagLayout();
        gbl_gameArea_topPanel.columnWidths = new int[]{85, 586, 93, 0};
        gbl_gameArea_topPanel.rowHeights = new int[]{50, 0};
        gbl_gameArea_topPanel.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_gameArea_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        gameArea_topPanel.setLayout(gbl_gameArea_topPanel);
        
        JButton gameArea_exitBtn = new JButton("EXIT");
        GridBagConstraints gbc_gameArea_exitBtn = new GridBagConstraints();
        gbc_gameArea_exitBtn.fill = GridBagConstraints.BOTH;
        gbc_gameArea_exitBtn.anchor = GridBagConstraints.NORTHWEST;
        gbc_gameArea_exitBtn.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_exitBtn.gridx = 0;
        gbc_gameArea_exitBtn.gridy = 0;
        gameArea_topPanel.add(gameArea_exitBtn, gbc_gameArea_exitBtn);
        
        JPanel gameArea_flashcardsView = new JPanel();
        GridBagConstraints gbc_gameArea_flashcardsView = new GridBagConstraints();
        gbc_gameArea_flashcardsView.anchor = GridBagConstraints.NORTH;
        gbc_gameArea_flashcardsView.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_flashcardsView.gridx = 1;
        gbc_gameArea_flashcardsView.gridy = 0;
        gameArea_topPanel.add(gameArea_flashcardsView, gbc_gameArea_flashcardsView);
        GridBagLayout gbl_gameArea_flashcardsView = new GridBagLayout();
        gbl_gameArea_flashcardsView.columnWidths = new int[]{109, 109, 109, 109, 109, 0};
        gbl_gameArea_flashcardsView.rowHeights = new int[]{75, 0};
        gbl_gameArea_flashcardsView.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_gameArea_flashcardsView.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        gameArea_flashcardsView.setLayout(gbl_gameArea_flashcardsView);
        
        JButton gameArea_flashcard_1 = new JButton("");
        gameArea_flashcard_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gameArea_flashcard_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        GridBagConstraints gbc_gameArea_flashcard_1 = new GridBagConstraints();
        gbc_gameArea_flashcard_1.fill = GridBagConstraints.BOTH;
        gbc_gameArea_flashcard_1.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_flashcard_1.gridx = 0;
        gbc_gameArea_flashcard_1.gridy = 0;
        gameArea_flashcardsView.add(gameArea_flashcard_1, gbc_gameArea_flashcard_1);
        
        JButton gameArea_flashcard_2 = new JButton("");
        gameArea_flashcard_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
        GridBagConstraints gbc_gameArea_flashcard_2 = new GridBagConstraints();
        gbc_gameArea_flashcard_2.fill = GridBagConstraints.BOTH;
        gbc_gameArea_flashcard_2.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_flashcard_2.gridx = 1;
        gbc_gameArea_flashcard_2.gridy = 0;
        gameArea_flashcardsView.add(gameArea_flashcard_2, gbc_gameArea_flashcard_2);
        
        JButton gameArea_flashcard_3 = new JButton("");
        gameArea_flashcard_3.setFont(new Font("SansSerif", Font.PLAIN, 18));
        GridBagConstraints gbc_gameArea_flashcard_3 = new GridBagConstraints();
        gbc_gameArea_flashcard_3.fill = GridBagConstraints.BOTH;
        gbc_gameArea_flashcard_3.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_flashcard_3.gridx = 2;
        gbc_gameArea_flashcard_3.gridy = 0;
        gameArea_flashcardsView.add(gameArea_flashcard_3, gbc_gameArea_flashcard_3);
        
        JButton gameArea_flashcard_4 = new JButton("");
        gameArea_flashcard_4.setFont(new Font("SansSerif", Font.PLAIN, 18));
        GridBagConstraints gbc_gameArea_flashcard_4 = new GridBagConstraints();
        gbc_gameArea_flashcard_4.fill = GridBagConstraints.BOTH;
        gbc_gameArea_flashcard_4.insets = new Insets(0, 0, 0, 5);
        gbc_gameArea_flashcard_4.gridx = 3;
        gbc_gameArea_flashcard_4.gridy = 0;
        gameArea_flashcardsView.add(gameArea_flashcard_4, gbc_gameArea_flashcard_4);
        
        JButton gameArea_flashcard_5 = new JButton("");
        gameArea_flashcard_5.setFont(new Font("SansSerif", Font.PLAIN, 18));
        GridBagConstraints gbc_gameArea_flashcard_5 = new GridBagConstraints();
        gbc_gameArea_flashcard_5.fill = GridBagConstraints.BOTH;
        gbc_gameArea_flashcard_5.gridx = 4;
        gbc_gameArea_flashcard_5.gridy = 0;
        gameArea_flashcardsView.add(gameArea_flashcard_5, gbc_gameArea_flashcard_5);
        
        JTextField gameArea_timerPane = new JTextField();
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
        
        JButton gameArea_currentFlashcardView = new JButton("Q");
        gameArea_currentFlashcardView.setFont(new Font("SansSerif", Font.PLAIN, 18));
        gameArea_currentFlashcardView.setBackground(Color.WHITE);
        gameArea_currentFlashcardView.setBorder(new LineBorder(new Color(0, 0, 0)));
        gameArea_centerPanel.add(gameArea_currentFlashcardView);
        
        JPanel gameArea_actionPanel = new JPanel();
        gameArea_centerPanel.add(gameArea_actionPanel, BorderLayout.SOUTH);
        gameArea_actionPanel.setLayout(new BorderLayout(0, 0));
        
        JButton gameArea_nextBtn = new JButton(">");
        gameArea_actionPanel.add(gameArea_nextBtn, BorderLayout.EAST);
        
        gameArea_answerField = new JTextField();
        gameArea_answerField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        gameArea_answerField.setColumns(10);
        gameArea_actionPanel.add(gameArea_answerField, BorderLayout.CENTER);
        
        JButton gameArea_backBtn = new JButton("<");
        gameArea_actionPanel.add(gameArea_backBtn, BorderLayout.WEST);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        gameArea_centerPanel.add(horizontalStrut, BorderLayout.WEST);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        gameArea_centerPanel.add(horizontalStrut_1, BorderLayout.EAST);
        
        Component verticalStrut = Box.createVerticalStrut(5);
        gameArea.add(verticalStrut, BorderLayout.SOUTH);
        
        JPanel resultsArea = new JPanel();
        contentPane.add(resultsArea, "name_287920268942300");
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
        
        JButton flashcardPreview_flashcard = new JButton("FLASHCARD");
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
        GridBagConstraints gbc_answersPreview_prevBtn = new GridBagConstraints();
        gbc_answersPreview_prevBtn.fill = GridBagConstraints.VERTICAL;
        gbc_answersPreview_prevBtn.insets = new Insets(0, 0, 0, 5);
        gbc_answersPreview_prevBtn.gridx = 0;
        gbc_answersPreview_prevBtn.gridy = 0;
        answersPreview_actionPanel.add(answersPreview_prevBtn, gbc_answersPreview_prevBtn);
        
        JLabel answersPreview_pageNumberLbl = new JLabel("0/0");
        answersPreview_pageNumberLbl.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_answersPreview_pageNumberLbl = new GridBagConstraints();
        gbc_answersPreview_pageNumberLbl.fill = GridBagConstraints.VERTICAL;
        gbc_answersPreview_pageNumberLbl.insets = new Insets(0, 0, 0, 5);
        gbc_answersPreview_pageNumberLbl.gridx = 1;
        gbc_answersPreview_pageNumberLbl.gridy = 0;
        answersPreview_actionPanel.add(answersPreview_pageNumberLbl, gbc_answersPreview_pageNumberLbl);
        
        JButton answersPreview_nextBtn = new JButton("Next");
        GridBagConstraints gbc_answersPreview_nextBtn = new GridBagConstraints();
        gbc_answersPreview_nextBtn.fill = GridBagConstraints.VERTICAL;
        gbc_answersPreview_nextBtn.gridx = 2;
        gbc_answersPreview_nextBtn.gridy = 0;
        answersPreview_actionPanel.add(answersPreview_nextBtn, gbc_answersPreview_nextBtn);
        
        JPanel resultsArea_bottomPanel = new JPanel();
        resultsArea.add(resultsArea_bottomPanel, BorderLayout.SOUTH);
        resultsArea_bottomPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JButton resultsArea_returnBtn = new JButton("RETURN TO...");
        resultsArea_returnBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
        resultsArea_bottomPanel.add(resultsArea_returnBtn);
        
        JButton resultsArea_restartBtn = new JButton("RESTART");
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
        scoreGrid_subjectField.setColumns(10);
        resultsArea_scoreGrid.add(scoreGrid_subjectField);
        
        JLabel scoreGrid_yourScoreLbl = new JLabel("Score:");
        scoreGrid_yourScoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
        resultsArea_scoreGrid.add(scoreGrid_yourScoreLbl);
        
        scoreGrid_yourScoreField = new JTextField();
        scoreGrid_yourScoreField.setColumns(10);
        resultsArea_scoreGrid.add(scoreGrid_yourScoreField);
        
        JLabel scoreGrid_levelLbl = new JLabel("Level:");
        scoreGrid_levelLbl.setHorizontalAlignment(SwingConstants.CENTER);
        resultsArea_scoreGrid.add(scoreGrid_levelLbl);
        
        scoreGrid_levelField = new JTextField();
        scoreGrid_levelField.setColumns(10);
        resultsArea_scoreGrid.add(scoreGrid_levelField);
        
        JLabel scoreGrid_percentLbl = new JLabel("Percent:");
        scoreGrid_percentLbl.setHorizontalAlignment(SwingConstants.CENTER);
        resultsArea_scoreGrid.add(scoreGrid_percentLbl);
        
        scoreGrid_percentField = new JTextField();
        scoreGrid_percentField.setColumns(10);
        resultsArea_scoreGrid.add(scoreGrid_percentField);
    }
}
