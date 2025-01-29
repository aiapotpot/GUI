/*
 * Handles core database operations like connections and transactions
 */

package database;

import java.sql.*;
import java.util.*;

public class dbManager {
	public static void createDB() {
		// TODO insert SQL statements pertaining to DB initialization here
	}

	public static Connection connectDB(){
		var url = "jdbc:sqlite:CreativeOutput/flashlayout/src/database/appdata.db";
		try{
			var conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute("PRAGMA foreign_keys = ON;");
			
			System.out.println("Success! Connection established!");
			return conn;
			
		} catch (SQLException e) {
			System.out.println("Error! Could not connect to the database! " + e);
			return null;
		}
	}
	
	public static void closeResources(Connection conn, Statement stmt, ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Success! Database is closed!");
			}
		}
		catch (SQLException e) {
			System.out.println("Error! Could not close resources! " + e);
		}
	}
	
	public static void beginTransaction(Connection conn) throws SQLException {
		if (conn != null) {
			conn.setAutoCommit(false);
		} else {
            throw new SQLException("Connection is null.");
        }
	}
	
	public static void commitTransaction(Connection conn) throws SQLException {
		if (conn != null) {
			conn.commit();
		} else {
            throw new SQLException("Connection is null.");
        }
	}
	
	public static void rollbackTransaction(Connection conn) throws SQLException {
		if (conn != null) {
			conn.rollback();
		} else {
            throw new SQLException("Connection is null.");
        }
	}
	
	public static void endTransaction(Connection conn) throws SQLException {
		if (conn != null) {
			conn.setAutoCommit(true);
		} else {
            throw new SQLException("Connection is null.");
        }
	}

	public static void executeUpdateQuery(String query, ArrayList<Object> parameters) {
		Connection conn = dbManager.connectDB();
		PreparedStatement stmt = null;
		
		try {
			dbManager.beginTransaction(conn);
			stmt = conn.prepareStatement(query);
			
			for (int i=0; i < parameters.size(); i++) {
				stmt.setObject(i+1, parameters.get(i));
			}
			
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Success! Query Executed: " + query);
			} else {
				System.out.println("Warning! No rows affected.");
			}
			
			dbManager.commitTransaction(conn);
		} catch (SQLException e) {
			System.out.println("Error! Could not execute query: " + e);
			
			try {
				dbManager.rollbackTransaction(conn);
			} catch (SQLException ex) {
				System.out.println("Rollback failed: " + ex);
			}
		} finally {
			dbManager.closeResources(conn, stmt, null);
		}	
	}
	
	public static boolean executeBooleanQuery(String query, ArrayList<Object> parameters) {
		Connection conn = dbManager.connectDB();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean exists = false;
	    
	    try {
	        stmt = conn.prepareStatement(query);
	        for (int i = 0; i < parameters.size(); i++) {
	            stmt.setObject(i + 1, parameters.get(i));
	        }

	        rs = stmt.executeQuery();
	        exists = rs.next(); // If there's at least one result, return true

	    } catch (SQLException e) {
	        System.out.println("Error! Could not execute query: " + e);
	    } finally {
	        dbManager.closeResources(conn, stmt, rs);
	    }

	    return exists;
	}

	public static ResultSet executeSelectQuery(String query, ArrayList<Object> parameters) {
		Connection conn = dbManager.connectDB();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(query);
			
			for (int i=0; i<parameters.size(); i++) {
				stmt.setObject(i + 1, parameters.get(i));
			}
			
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error! Could not execute query: " + e);
		}
		
		return rs;
	}

	public static void executeDynamicUpdate(String tableName, String conditionField, Object conditionValue,
											Map<String, Object> fieldsToUpdate) 
	{
		StringBuilder dynamicQuery = new StringBuilder("UPDATE " + tableName + " SET ");
		ArrayList<Object> parameters = new ArrayList<Object> ();
		
		// Dynamically add fields based on non-null parameters
		for (Map.Entry<String, Object> entry : fieldsToUpdate.entrySet()) {
			dynamicQuery.append(entry.getKey()).append(" = ?, ");
			parameters.add(entry.getValue());
		}
		
		// remove trailing comma and add WHERE condition
		if (parameters.isEmpty()) {
			System.out.println("No fields provided to update.");
			return;
		}
	
		dynamicQuery.setLength(dynamicQuery.length() - 2);
		dynamicQuery.append(" WHERE " + conditionField + " = ? ");
		parameters.add(conditionValue);
		
		String finalQuery = dynamicQuery.toString();
		
		Connection conn = dbManager.connectDB();
		PreparedStatement stmt = null;
		
		try {
			dbManager.beginTransaction(conn);
			
			stmt = conn.prepareStatement(finalQuery);
			for (int i=0; i<parameters.size(); i++) {
				stmt.setObject(i + 1, parameters.get(i));
			}
			
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.printf("Success! %s updated.\n", tableName);
	        } else {
	            System.out.println("Error! No records updated.");
	        }

	        dbManager.commitTransaction(conn);
		} catch (SQLException e) {
			System.out.printf("Error! Could not update %s: %s\n", tableName, e);
		} finally {
			dbManager.closeResources(conn, stmt, null);
		}
		
		
	}
}

	