package models;

import java.util.*;

public class Level {
	Integer id; // has to be set by the database itself
	Integer subject_id;
	
	String name;
	Integer highScore;
	Integer timer;
	boolean timerEnabled;
	
	ArrayList<Flashcard> flashcards;
	
	public Level(Integer id, Integer subject_id, String name, Integer highScore, Integer timer, boolean timerEnabled){
		// initialize a level and its values
		this.id = id;
		this.subject_id = subject_id;
		this.name = name;
		this.highScore = highScore;
		this.timer = timer;
		this.timerEnabled = timerEnabled;
		
		// create empty flashcards array
		this.flashcards =  new ArrayList<Flashcard> ();
	}
	
	// setters
	public void setName(String name) {this.name = name; };
	public void setHighScore(Integer highScore) {this.highScore = highScore;};
	public void setTimer(Integer timer) {this.timer = timer; };
	public void setTimerEnabled(boolean timerEnabled) {this.timerEnabled = timerEnabled;};
	
	// getters
	public int getId() {return this.id; };
	public String getName() {return this.name;};
	public Integer getHighScore() {return this.highScore;};
	public Integer getTimer() {return this.timer;};
	public boolean getTimerEnabled() {return this.timerEnabled;};
	public ArrayList<Flashcard> getFlashcards() {return this.flashcards;}; 
	
	public void addFlashcard(Flashcard flashcard) {
		flashcards.add(flashcard);
	}
	
	public void removeFlashcard(Flashcard flashcard){
		flashcards.remove(flashcard);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
