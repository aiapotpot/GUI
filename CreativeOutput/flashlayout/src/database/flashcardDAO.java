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

	    String query = "INSERT INTO Flashcards (level_id, question, answer) " +
	                   "VALUES (?, ?, ?)";
	    
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(question);
	    parameters.add(answer);

	    dbManager.executeUpdateQuery(query, parameters);
	}
	
	public static void deleteFlashcard(Level level, Flashcard flashcard) {
	    String query = "DELETE FROM Flashcards WHERE level_id = ? AND id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(flashcard.getId());

	    dbManager.executeUpdateQuery(query, parameters);

	}
	
	public static boolean checkFlashcard(Level level, Flashcard flashcard) {
	    String query = "SELECT id FROM Flashcards WHERE level_id = ? AND id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());
	    parameters.add(flashcard.getId());

	    boolean exists = dbManager.executeBooleanQuery(query, parameters);

	    if (exists) {
	        System.out.printf("Success! Flashcard %d was found in level %s!\n", flashcard.getId(), level.getName());
	    } else {
	        System.out.printf("Fail! Flashcard %d was not found in level %s!\n", flashcard.getId(), level.getName());
	    }

	    return exists;
	}

	public static ArrayList<Flashcard> presentFlashcards(Level level) {
	    ArrayList<Flashcard> flashcards = new ArrayList<>();
	    ArrayList<Object> parameters = new ArrayList<Object>();

	    String query = "SELECT * FROM Flashcards WHERE level_id = ?";
	    parameters.add(level.getId());

	    ResultSet rs = dbManager.executeSelectQuery(query, parameters);

	    try {
	        while (rs != null && rs.next()) {
	            int id = rs.getInt("id");
	            int level_id = rs.getInt("level_id");
	            String question = rs.getString("question");
	            String answer = rs.getString("answer");

	            Flashcard f = new Flashcard(id, level_id, question, answer);
	            flashcards.add(f);
	        }

	    } catch (SQLException e) {
	        System.out.println("Error! Could not capture Flashcards! " + e);
	    } finally {
	        try {
	            dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
	        } catch (SQLException e) {
	            System.out.println("Error! Could not close resources " + e);
	        }
	    }

	    return flashcards;
	}
	
	public static void updateFlashcard(Flashcard flashcard, String question, String answer) {
		String query = "UPDATE flashcards SET question = ?, answer = ? WHERE id = ?";
		ArrayList<Object> parameter = new ArrayList<>();
		parameter.add(question);
		parameter.add(answer);
		parameter.add(flashcard.getId());
		dbManager.executeUpdateQuery(query, parameter);
	}
}
