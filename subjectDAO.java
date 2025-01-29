/* Handles database transactions within the Subjects table*/

package database;

import java.sql.*;
import java.util.*;

import models.Subject;

public class subjectDAO {
	/* 	TODO: Things to improve in the code
	 * 	Add logging system
	 */
	public static void insertSubject(String name, String creationType) {
		if (name.isBlank() || creationType.isBlank()) {
			System.out.println("Cannot have empty fields");
			return;
		}
		
		String query = "INSERT INTO Subjects (name, creationType) VALUES (?, ?)";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(name);
	    parameters.add(creationType);

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! Subject: %s, creationType: %s inserted!\n", name, creationType);
	}
	
	public static void deleteSubject(Subject subject) {
		boolean exists = subjectDAO.checkSubject(subject);
		if (!exists) {
			System.out.println("Data does not exist!");
			return;
		}
		
		String query = "DELETE FROM Subjects WHERE id = ?";
		ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());

	    dbManager.executeUpdateQuery(query, parameters);

	    System.out.printf("Success! Subject: %s, creationType: %s deleted!\n", subject.getName(), subject.getCreationType());
		
	}
	
	public static boolean checkSubject(Subject subject) {
		if (subject == null || subject.getId() <= 0) {
			return false;
		}
		
		String query = "SELECT id FROM Subjects WHERE id = ?";
	    ArrayList<Object> parameters = new ArrayList<>();
	    parameters.add(subject.getId());

	    boolean exists = dbManager.executeBooleanQuery(query, parameters);

	    if (exists) {
	        System.out.printf("Success! %s was found!\n", subject.getName());
	    } else {
	        System.out.printf("Fail! %s was not found!\n", subject.getName());
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
		
	public static void updateSubject(Subject subject, String newName, Integer newTotalWrongs, 
									 Integer newTotalRights, Integer newTotalVisits) {
	    // Base query
	    boolean exists = subjectDAO.checkSubject(subject);
	    if (!exists) {
	    	return;
	    }
	    
	    Map<String, Object> fieldsToUpdate = new HashMap<>();

	    // Dynamically add fields based on non-null parameters
	    if (newName != null) {
	        fieldsToUpdate.put("name", newName);
	    }
	    if (newTotalWrongs != null) {
	       fieldsToUpdate.put("newTotalWrongs", newTotalWrongs);
	    }
	    if (newTotalRights != null) {
	        fieldsToUpdate.put("newTotalRights", newTotalRights);
	    }
	    if (newTotalVisits != null) {
	    	fieldsToUpdate.put("newTotalVisits", newTotalVisits);
	    }

	    // Remove trailing comma and add WHERE condition
	    if (fieldsToUpdate.isEmpty()) {
	        System.out.println("No fields provided to update.");
	        return;
	    }

	    dbManager.executeDynamicUpdate("Subjects", "id", subject.getId(), fieldsToUpdate);
	}		
}
