/* Handles database transactions within the Flashcards table*/

package database; 

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Level;
import models.Flashcard;

public class flashcardDAO {
	
	public static void insertFlashcard(Level level, String question, String answer) {
		if (question.isBlank() || answer.isBlank()) {
			System.out.println("Cannot have empty fields");
			return;
		}
		String query = "INSERT INTO Flashcards ((level_id, question, answer) VALUES (?, ?, ?)";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(question);
	    parameters.add(answer);

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! New Flashcard in level: %s inserted!\n", level.getName());
	}
	
	public static void deleteFlashcard(Level level, Flashcard flashcard) {
		boolean exists = flashcardDAO.checkFlashcard(level, flashcard);
		if (!exists) {
			System.out.println("Data does not exist!");
			return;
		}
		String query = "DELETE FROM Flashcards WHERE level_id = ? AND id = ?";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(flashcard.getId());

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! Flashcard in level: %s deleted!\n", level.getName());
	}
	
	public static boolean checkFlashcard(Level level, Flashcard flashcard) {
		String query = "SELECT id FROM Flashcards WHERE level_id = ? AND id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(flashcard.getId());

	    boolean exists = dbManager.executeBooleanQuery(query, parameters);

	    if (exists) {
	        System.out.printf("Success! Flashcard %s was found in level %s!\n", flashcard.getId(), level.getName());
	    } else {
	        System.out.printf("Fail! Flashcard %s was not found! in level %s\n", flashcard.getId(), level.getName());
	    }

	    return exists;
	}

	public static ArrayList<Flashcard> presentFlashcards(Level level) {
		ArrayList<Object> parameters = new ArrayList<Object> ();
		
		String query = "SELECT * FROM Flashcards WHERE level_id = ?";
		parameters.add(level.getId());
		
		ResultSet rs = dbManager.executeSelectQuery(query, parameters);
		
		try {
			while (rs.next()) {
				int 		  id = rs.getInt("id");
				int 	level_id = rs.getInt("level_id");
				String  question = rs.getString("question");
				String 	  answer = rs.getString("answer");
				
				Flashcard f = new Flashcard(id, level_id, question, answer);
				
				level.addFlashcard(f);
			}
			
			System.out.println("Success! Flashcards captured!");
			
		} catch (SQLException e) {
			System.out.println("Error! Could not capture Flashcards! " + e);
		} finally {
			
			try {
				dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
			} catch (SQLException e){
				System.out.println("Error! Could not close resources " + e);
			}
		}
		
		return level.getFlashcards();
	}
	
	public static void updateFlashcard(Level level, Flashcard flashcard, String newQuestion, String newAnswer) {
		 Map<String, Object> fieldsToUpdate = new HashMap<>();

	    // Dynamically add fields based on non-null parameters
	    if (newQuestion != null) {
	        fieldsToUpdate.put("question", newQuestion);
	    }
	    if (newAnswer != null) {
	        fieldsToUpdate.put("answer", newAnswer);
	    }
	    
	    if (fieldsToUpdate.isEmpty()) {
	        System.out.println("No fields provided to update.");
	        return;
	    }
	    
	    dbManager.executeDynamicUpdate("Levels", "id", level.getId(), fieldsToUpdate);
    }
}
