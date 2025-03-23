package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import layout.mainWindow; 
import layout.levelEditorWindow;
import layout.subjectEditorWindow;

import database.subjectDAO;
import database.levelDAO;
import database.flashcardDAO;

import models.Subject;
import models.Level;
import models.Flashcard;

// TODO: Document code

public class mainWindowLogic {
	
	// consolidate these two into a single function to eliminate redundancy
	public ArrayList< ArrayList<Subject> > getSubjectsList() {
		ArrayList<Subject> subjects = subjectDAO.presentSubjects();
		ArrayList< ArrayList<Subject> > subjectsList = new ArrayList<>();
		
		int chunkSize = 5;
		for (int i=0; i<subjects.size(); i+=chunkSize) {
			
			// making sure the chunks don't go out of bounds
			int endIndex = Math.min(i + chunkSize, subjects.size());
			
			ArrayList<Subject> chunk = new ArrayList<>(subjects.subList(i, endIndex));
			subjectsList.add(chunk);
		}
		
		return subjectsList;
	}
	
	public ArrayList< ArrayList<Level> > getLevelsList(Subject subject) {
		ArrayList<Level> levels = levelDAO.presentLevels(subject);
		ArrayList< ArrayList<Level> > levelsList = new ArrayList<>();
		int chunkSize = 5;
		for (int i=0; i<levels.size(); i+=chunkSize) {
			
			// making sure the chunks don't go out of bounds
			int endIndex = Math.min(i + chunkSize, levels.size());
			
			ArrayList<Level> chunk = new ArrayList<>(levels.subList(i, endIndex));
			levelsList.add(chunk);
		}
		
		return levelsList;
	}
	
	public <T> int toggleDataSet(ArrayList<ArrayList<T>> data, char mode, int index) {
		   
		   if (mode == '+' && index < data.size() - 1) {
			   index++;
		   } else if (mode == '-' && index > 0) {
			   index--;
		   }
		   
		   return index;
	}
	
	public ArrayList<Flashcard> getFlashcards(Level level){
		ArrayList<Flashcard> flashcards = flashcardDAO.presentFlashcards(level);
		Collections.shuffle(flashcards);
		return flashcards;
	}
	
	public Flashcard getFlashcardAt(ArrayList<Flashcard> flashcards, int index) {
		if (flashcards != null && index >= 0 && index < flashcards.size()) {
			return flashcards.get(index);
		}
		
		return null;
	}
	
	public String getFlashcardText(Flashcard flashcard, char viewMode) {
		String text = "";
		
		if (flashcard != null) {
			switch (viewMode) {
			case 'q': 
				text = flashcard.getQuestion();
				break;
			case 'a':
				text = flashcard.getAnswer();
				break;
			}
		}
		
		return text;	
	}

	public boolean checkEmptyFlashcards(ArrayList<Flashcard> flashcards) {
		if (flashcards.size() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean compareAnswer(String a, String b) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		if (a.compareTo(b) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Integer getScore(ArrayList<Flashcard> flashcards, ArrayList<String> userAnswers) {
		Integer score = 0;
		for (int i=0; i<flashcards.size(); i++) {
			
			String flashcardAnswer = flashcards.get(i).getAnswer();
			String userAnswer = userAnswers.get(i);
			
			if (compareAnswer(flashcardAnswer, userAnswer)){
				score++;
			}
		}
		return score;
	}
	
	public Integer getScorePercentage(int score, int flashcardsAmt) {
		double percent = ( (double) score / flashcardsAmt) * 100;
		return (int) percent;
	}
	
	public void saveLevelHighscore(Subject subject, Level level, Integer score) {
		if (score > level.getHighScore()) {
		   levelDAO.updateLevel(subject, level, level.getName(), score, level.getTimer(), level.getTimerEnabled());
		}
	}

}
