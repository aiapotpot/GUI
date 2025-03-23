/* Handles database transactions within the Levels table*/

package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Level;
import models.Subject;

public class levelDAO {	
	public static void insertLevel(Subject subject, String levelName, int timer, boolean timerEnabled) {
	    if (subject.getName().isBlank() || levelName.isBlank()) {
	        System.out.println("Cannot have empty fields");
	        return;
	    }

	    String query = "INSERT INTO Levels (subject_id, name, timer, timerEnabled) VALUES (?, ?, ?, ?)";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());
	    parameters.add(levelName);
	    parameters.add(timer);

	    dbManager.executeUpdateQuery(query, parameters);
	}
		
	public static void deleteLevel(Subject subject, Level level) {
	    String query = "DELETE FROM Levels WHERE subject_id = ? AND id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());
	    parameters.add(level.getId());

	    dbManager.executeUpdateQuery(query, parameters);
	}
	
	public static Level presentLevel(Subject subject, String levelName) {
		// TODO: Recheck if this one works or makes sense
	    String query = "SELECT * FROM Levels WHERE subject_id = ? AND name = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());
	    parameters.add(levelName);

	    ResultSet rs = dbManager.executeSelectQuery(query, parameters);
	    Level level = null;
	    
	    try {
	    	while (rs != null && rs.next()) {
	    		
	    		int id = rs.getInt("id");
	    		int subjectId = rs.getInt("subject_id");
	    		String name = rs.getString("name");
	    		int highScore = rs.getInt("highScore");
	    		int timer = rs.getInt("timer");
	    		boolean timerEnabled = rs.getBoolean("timerEnabled");
	    		
	    		level = new Level(id, subjectId, name, highScore, timer, timerEnabled);
	    	}
	    } catch (SQLException e) {
	        System.out.println("Error! Could not capture Level! " + e);
	    } finally {
	        try {
	            dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
	        } catch (SQLException e) {
	            System.out.println("Error! Could not close resources " + e);
	        }
	    }
	    return level;
	}
	
	public static ArrayList<Level> presentLevels(Subject subject) {
	    ArrayList<Level> levels = new ArrayList<>();
	    ArrayList<Object> parameters = new ArrayList<>();

	    String query = "SELECT * FROM Levels WHERE subject_id = ?";
	    parameters.add(subject.getId());

	    ResultSet rs = dbManager.executeSelectQuery(query, parameters);

	    try {
	        while (rs != null && rs.next()) {
	            int id = rs.getInt("id");
	            int subjectId = rs.getInt("subject_id");
	            String name = rs.getString("name");
	            int highScore = rs.getInt("highScore");
	            int timer = rs.getInt("timer");
	            boolean timerEnabled = rs.getBoolean("timerEnabled");

	            Level level = new Level(id, subjectId, name, highScore, timer, timerEnabled);
	            levels.add(level);
	        }

	        System.out.printf("Success! Levels for subject '%s' captured!\n", subject.getName());

	    } catch (SQLException e) {
	        System.out.println("Error! Could not capture Levels! " + e);
	    } finally {
	        try {
	            dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
	        } catch (SQLException e) {
	            System.out.println("Error! Could not close resources " + e);
	        }
	    }

	    return levels;
	}
	
	public static void updateLevel(Subject subject, Level level, String newName, Integer newHighScore,
								   Integer newTimer, boolean timerEnabled) {
		
		String query = "UPDATE levels SET name = ?, highScore = ?, timer = ?, timerEnabled = ? WHERE subject_id = ? AND id = ?";
		ArrayList<Object> parameters = new ArrayList<>();
		parameters.add(newName);
		parameters.add(newHighScore);
		parameters.add(newTimer);
		parameters.add(timerEnabled);
		parameters.add(subject.getId());
		parameters.add(level.getId());
		
		dbManager.executeUpdateQuery(query, parameters);
	}

}
