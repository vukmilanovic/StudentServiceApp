package controller;

import javax.swing.JOptionPane;

import data_base.CourseChairDB;
import data_base.ProfessorDB;
import model_sistema.Adress;
import model_sistema.CourseChair;
import model_sistema.Date;
import model_sistema.Professor;
import model_sistema.Title;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class ProfessorController {
	
	private static ProfessorController instance = null;
	
	public static ProfessorController getInstance() {
		if (instance == null) {
			instance = new ProfessorController();
		}
		return instance;
	}
	
	private ProfessorController() {}
	
	public void AddProfessor(String name, String surname, Date dob, Adress adr, String tel, String mail, String kanc, int IDnum, Title t, int god) {
		ProfessorDB.getInstance().addProfessor(name, surname, dob, adr, tel, mail, kanc, IDnum, t, god);
		Main_Window_Tabs.getInstance().updateProfessorView("Professor Added", -1);
	}
	
	public void RemoveProfessor(int row) {
		
		Professor prof = ProfessorDB.getInstance().getRow(row);
		ProfessorDB.getInstance().removeProfessor(prof.getIDnum());
		ProfessorDB.getInstance().removeProfessorFromAllSubjects(prof);
		ProfessorDB.getInstance().removeProfessorFromAllChairHeads(prof);
		ProfessorDB.getInstance().removeProfessorFromAllChairProfs(prof);
		Main_Window_Tabs.getInstance().updateProfessorView("Professor Removed", prof.getIDnum());
	}
	
	public void EditProfessor(int row, String name, String surname, Date dob, Adress adr, String tel, String mail, String kanc, int IDnum, Title t, int god) {
		
		Professor prof = ProfessorDB.getInstance().getRow(row);
		ProfessorDB.getInstance().editProfessor(prof.getIDnum(), name, surname, dob, adr, tel, mail, kanc, IDnum, t, god);
		Main_Window_Tabs.getInstance().updateProfessorView("Professor Edited", prof.getIDnum());
	}

	public void AddProfessorToCourseChairHead(int row, int ID) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		Professor professor = ProfessorDB.getInstance().getProfByID(ID);
		ProfessorDB.getInstance().addProfessorToChairHead(chair, professor);
		ProfessorDB.getInstance().addProfessorToChairProfs(chair, professor);
	}
	
	public void AddProfessorToCourseChairProfs(int row, int ID) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		Professor professor = ProfessorDB.getInstance().getProfByID(ID);
		ProfessorDB.getInstance().addProfessorToChairProfs(chair, professor);
	}
	
	public void RemoveProfessorFromCourseChairHead(int row) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		ProfessorDB.getInstance().removeProfessorFromChairHead(chair);
	}
	
	public void RemoveProfessorFromCourseChairProfs(int row, int ID) {
		CourseChair chair = CourseChairDB.getInstance().getRow(row);
		Professor professor = ProfessorDB.getInstance().getProfByID(ID);
		ProfessorDB.getInstance().removeProfessorFromChairProfs(chair, professor);
	}
	
}
