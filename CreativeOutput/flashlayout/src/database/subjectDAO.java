/* Handles database transactions within the Subjects table*/

package database;

import java.sql.*;
import java.util.*;

import models.Subject;

public class subjectDAO {
	/* 	TODO: Things to improve in the code
	 * 	Add logging system
	 */
	public static void insertSubject(String name, String creationType){
		if (name.isBlank() || creationType.isBlank()) {
			System.out.println("Cannot have empty fields");
			return;
		}
		
		String query = "INSERT INTO Subjects (name, creationType) VALUES (?, ?)";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(name);
	    parameters.add(creationType);

	    dbManager.executeUpdateQuery(query, parameters);
	}
	
	public static void deleteSubject(Subject subject) {
	    String query = "DELETE FROM Subjects WHERE id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());

	    dbManager.executeUpdateQuery(query, parameters);

	}
	
	// Haven't found a use for this yet, better to keep it for now
	public static boolean checkSubjectByName(String name) {
	    if (name == null || name.isBlank()) {
	        return false;
	    }
	    
	    String query = "SELECT id FROM Subjects WHERE name = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(name);

	    boolean exists = dbManager.executeBooleanQuery(query, parameters);

	    if (exists) {
	        System.out.printf("Success! Subject with name: %s was found!\n", name);
	    } else {
	        System.out.printf("Fail! Subject with name: %s was not found!\n", name);
	    }

	    return exists;
	}
	
	public static ArrayList<Subject> presentSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject> ();
		ArrayList<Object> parameters = new ArrayList<Object> ();
		
		String query = "SELECT * FROM Subjects";
		
		ResultSet rs = dbManager.executeSelectQuery(query, parameters);
		
		try {
			while (rs != null && rs.next()) {
				int 			  id = rs.getInt("id");
				String 			name = rs.getString("name");
				String 	creationType = rs.getString("creationType");
				int 	 totalWrongs = rs.getInt("totalWrongs");
				int 	 totalRights = rs.getInt("totalRights");
				int 	 totalVisits = rs.getInt("totalVisits");
				
				Subject s = new Subject(id, name, creationType, totalWrongs, totalRights, totalVisits);
				
				subjects.add(s);
			}
			
			System.out.println("Success! Subjects captured!");
			
		} catch (SQLException e) {
			System.out.println("Error! Could not capture Subjects! " + e);
		} finally {
			
			try {
				dbManager.closeResources(rs.getStatement().getConnection(), rs.getStatement(), rs);
			} catch (SQLException e){
				System.out.println("Error! Could not close resources " + e);
			}
		}
		return subjects;
	}
		
	public static void updateSubject(Subject subject, String newName, Integer totalWrong, Integer totalRights, Integer totalVisits) {
		String query = "UPDATE subjects SET name = ?, totalWrongs = ?, totalRights = ?, totalVisits = ? WHERE name = ?";
		ArrayList<Object> parameters = new ArrayList<>();
		parameters.add(newName);
		parameters.add(totalWrong);
		parameters.add(totalRights);
		parameters.add(totalVisits);
		parameters.add(subject.getName());
		dbManager.executeUpdateQuery(query, parameters);
	}
}
