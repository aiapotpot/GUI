package models;

import java.util.*;

public class Subject {
	int id; // has to be set by the database itself
	
	String name;
	String creationType;
	int totalWrongs;
	int totalRights;
	int totalVisits;

	ArrayList<Level> levels;
	
	public Subject(int id, String name, String creationType, int totalWrongs, int totalRights, int totalVisits){
		this.id = id;
		this.name = name;
		this.creationType = creationType;
		this.totalWrongs = totalWrongs;
		this.totalRights = totalRights;
		this.totalVisits = totalVisits;
		
		this.levels = new ArrayList<Level> ();
	}                                  
	                                   
	// setters
	public void setName(String name) { this.name = name; };
	public void setCreationType(String creationType) {this.creationType = creationType; };
	public void setTotalWrongs(int totalWrongs) {this.totalWrongs = totalWrongs; };
	public void setTotalRights(int totalRights) {this.totalRights = totalRights; };
	public void setTotalVisits(int totalVisits) {this.totalVisits = totalVisits; };
	
	// getters
	public int getId() {return this.id; };
	public String getName() { return this.name; };
	public String getCreationType() {return this.creationType; };
	public int getTotalWrongs() {return this.totalWrongs; };
	public int getTotalRights() {return this.totalRights; };
	public int getTotalVisits() {return this.totalVisits; };
	public ArrayList<Level> getLevels() {return this.levels; }
	
	@Override
	public String toString() {
		return getName();
	}
	
	
	public void addLevel(Level level) {
		this.levels.add(level);
	}
	
	public void removeLevel(Level level) {
		this.levels.remove(level);
	}
}
