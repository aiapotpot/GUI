package models;

import java.util.*;

public class Flashcard {
	int id; // has to be set by the database itself
	int level_id;
	String question;
	String answer;
	
	ArrayList<Level> levels;
	
	public Flashcard(int id, int level_id, String question, String answer){
		this.id = id;
		this.level_id = level_id;
		this.question = question;
		this.answer = answer;
	}
	
	// setters
	public void setQuestion(String question) {this.question = question; };
	public void setAnswer(String answer) {this.answer = answer; };
	
	// getters
	public String getQuestion() {return this.question;};
	public String getAnswer() {return this.answer;}

	public Integer getId() {return this.id;};
	
	@Override
	public String toString() {
		return this.question;
	}
}
