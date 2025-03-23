package logic;

import database.levelDAO;
import database.subjectDAO;

import java.util.ArrayList;
import java.util.Collections;

import database.flashcardDAO;

import models.Level;
import models.Subject;
import models.Flashcard;

public class levelEditorLogic {
	public void createLevel(Subject subject, String levelName, int time, boolean timerEnabled) {
		levelDAO.insertLevel(subject, levelName, time, timerEnabled);
	}
	
	public void editLevel(Subject subject, Level level, String levelName, Integer highscore, Integer time, boolean timerEnabled) {
		levelDAO.updateLevel(subject, level, levelName, highscore, time, timerEnabled);
	}
	
	public void removeLevel(Subject subject, Level level) {
		levelDAO.deleteLevel(subject, level);
	}
	
	public void createFlashcard(Level level){
		flashcardDAO.insertFlashcard(level, "new flashcard", "no answer");
	}
	
	public void editFlashcard(Flashcard flashcard, String question, String answer) {
		flashcardDAO.updateFlashcard(flashcard, question, answer);
	}
	
	public void removeFlashcard(Level level, Flashcard flashcard) {
		flashcardDAO.deleteFlashcard(level, flashcard);
	}
	
	public Level getLevel(Subject subject, String levelName) {
		Level level = levelDAO.presentLevel(subject, levelName);
		return level;
	}
	
	public ArrayList<Flashcard> getFlashcards(Level level){
		ArrayList<Flashcard> flashcards = flashcardDAO.presentFlashcards(level);
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

	public ArrayList< ArrayList<Flashcard> > getFlashcardsList(Level level) {
		
		ArrayList<Flashcard> flashcards = getFlashcards(level);
		
		ArrayList< ArrayList<Flashcard> > flashcardsList = new ArrayList<>();
		
		int chunkSize = 3;
		for (int i=0; i<flashcards.size(); i+=chunkSize) {
			
			// making sure the chunks don't go out of bounds
			int endIndex = Math.min(i + chunkSize, flashcards.size());
			
			ArrayList<Flashcard> chunk = new ArrayList<>(flashcards.subList(i, endIndex));
			flashcardsList.add(chunk);
		}
		
		return flashcardsList;
	}
	
	public <T> int toggleDataSet(ArrayList<ArrayList<T>> data, char mode, int index) {
		   
		   if (mode == '+' && index < data.size() - 1) {
			   index++;
		   } else if (mode == '-' && index > 0) {
			   index--;
		   }
		   
		   return index;
	}
}
