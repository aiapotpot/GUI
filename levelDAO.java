/* Handles database transactions within the Levels table*/

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Level;
import models.Subject;

public class levelDAO {	
	public static void insertLevel(Subject subject, String name, int timer) {
		if (name.isBlank()) {
			System.out.println("Cannot have empty fields");
			return;
		}
		String query = "INSERT INTO Levels (subject_id, name, timer) VALUES (?, ?, ?)";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());
	    parameters.add(name);
	    parameters.add(timer);

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! Level: %s, Subject: %s inserted!\n", name, subject.getName());
	}
		
	
	public static void deleteLevel(Subject subject, Level level) {
		boolean exists = levelDAO.checkLevel(subject, level);
		if (!exists) {
			System.out.println("Data does not exist!");
			return;
		}
		String query = "DELETE FROM Levels WHERE subject_id = ? AND id = ?";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());
	    parameters.add(level.getId());

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! Level: %s, Subject: %s deleted!\n", level.getName(), subject.getCreationType());
	}
	
	public static boolean checkLevel(Subject subject, Level level) {
		if (subject == null || subject.getId() <= 0) {
			return false;
		}
		
		String query = "SELECT id FROM Levels WHERE id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(level.getId());

	    boolean exists = dbManager.executeBooleanQuery(query, parameters);

	    if (exists) {
	        System.out.printf("Success! %s was found in %s!\n", level.getName(), subject.getName());
	    } else {
	        System.out.printf("Fail! %s was not found in %s!\n", level.getName(), subject.getName());
	    }

	    return exists;
	}
	
	public static ArrayList<Level> presentLevels(Subject subject){
		ArrayList<Object> parameters = new ArrayList<Object> ();
		
		String query = "SELECT * FROM Levels WHERE subject_id = ?";
		parameters.add(subject.getId());
		
		ResultSet rs = dbManager.executeSelectQuery(query, parameters);
		
		try {
			while (rs != null && rs.next()) {
				int 			id = rs.getInt("id");
				int 	subject_id = rs.getInt("subject_id");
				String  	  name = rs.getString("name");
				int 	 highScore = rs.getInt("highScore");
				int 		 timer = rs.getInt("timer");
				
				Level l = new Level(id, subject_id, name, highScore, timer);
				
				subject.addLevel(l);
			}
			
			System.out.println("Success! Subjects captured!");
			
		} catch (SQLException e) {
			System.out.println("Error! Could not capture Levels! " + e);
		} finally {
			
			try {
				dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
			} catch (SQLException e){
				System.out.println("Error! Could not close resources " + e);
			}
			
		}
		
		return subject.getLevels();
		
	}
	
	public static void updateLevel(Subject subject, Level level, String newName, 
								   Integer newHighScore, Integer newTimer) {
		// Base query
	    Map<String, Object> fieldsToUpdate = new HashMap<>();

	    // Dynamically add fields based on non-null parameters
	    if (newName != null) {
	        fieldsToUpdate.put("newName", newName);
	    }
	    if (newHighScore != null) {
	        fieldsToUpdate.put("newHighScore", newHighScore);
	    }
	    
	    if (newTimer != null) {
	        fieldsToUpdate.put("newTimer", newTimer);
	    }
	    // Remove trailing comma and add WHERE condition
	    if (fieldsToUpdate.isEmpty()) {
	        System.out.println("No fields provided to update.");
	        return;
	    }
	    
	    dbManager.executeDynamicUpdate("Levels", "id", level.getId(), fieldsToUpdate);
	    
    }
}
