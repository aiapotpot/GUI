package logic;

import database.subjectDAO;

import models.Subject;

public class subjectEditorLogic {
	
	public void createSubject(String name) {
		subjectDAO.insertSubject(name, "Custom");
	}
	
	public void editSubject(Subject subject, String newName) {
		subjectDAO.updateSubject(subject, newName, subject.getTotalWrongs(), null, null);
	}
	
	public void deleteSubject(Subject subject) {
		subjectDAO.deleteSubject(subject);
	}
	
}
