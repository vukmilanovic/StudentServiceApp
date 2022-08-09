package controller;

import data_base.StudentDB;
import view.main_frame.Main_Window_Tabs;
import model_sistema.Adress;
import model_sistema.Date;
import model_sistema.IndexNum;
import model_sistema.Status;
import model_sistema.Student;

public class StudentController {

	private static StudentController instance = null;
		
	public static StudentController getInstance() {
		if (instance == null) {
			instance = new StudentController();
		}
		return instance;
	}
	
	private StudentController() {}
	
	public void AddStudent(String name, String surname, Date dob, Adress adr, String tel, String mail, IndexNum idx, int enroll_year, int current_year, Status status) {
		StudentDB.getInstance().addStudent(name, surname, dob, adr, tel, mail, idx, enroll_year, current_year, status);
		Main_Window_Tabs.getInstance().updateStudentView("Student Added", -1);
	}
	
	public void RemoveStudent(int row) {
		
		Student student = StudentDB.getInstance().getRow(row);
		StudentDB.getInstance().removeStudent(student.getIdx());
		StudentDB.getInstance().removeStudentFromAllCoursesFailed(student);
		StudentDB.getInstance().removeStudentFromAllCoursesPassed(student);
		StudentDB.getInstance().removeAllStudentGrades(student);
		Main_Window_Tabs.getInstance().updateStudentView("Student Removed", student.getIdx().getIdxnum());
	}
	
	public void EditStudent(int row, String name, String surname, Date dob, Adress adr, String tel, String mail, IndexNum idx, int enroll_year, int current_year, Status status) {
		
		Student student = StudentDB.getInstance().getRow(row);
		StudentDB.getInstance().editStudent(student.getIdx(), name, surname, dob, adr, tel, mail, idx, enroll_year, current_year, status);
		Main_Window_Tabs.getInstance().updateStudentView("Student Edited", student.getIdx().getIdxnum());
	}
}
