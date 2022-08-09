package controller;

import javax.swing.JOptionPane;

import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.ProfessorDB;
import data_base.StudentDB;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Semester;
import model_sistema.Student;
import view.edit.Edit_Professor_Tabs;
import view.edit.Edit_Student_Tabs;
import view.main_frame.Main_Window_Tabs;
import view.main_frame.Main_window;

public class CourseClassController {

	private static CourseClassController instance = null;
	
	public static CourseClassController getInstance() {
		if (instance == null) {
			instance = new CourseClassController();
		}
		return instance;
	}
	
	private CourseClassController() {}
	
	public void AddCourseClass(short sifra, String name, int year, Semester s, int espb) {
		CourseClassDB.getInstance().addCourseClass(sifra, name, year, s, espb);
		Main_Window_Tabs.getInstance().updateSubjectView("CourseClass Added", -1);
	}
	
	public void RemoveCourseClass(int row) {
		
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		CourseClassDB.getInstance().removeCourseClass(course.getCode());
		CourseClassDB.getInstance().removeCourseFromAllStudents(course);
		CourseClassDB.getInstance().removeCourseFromAllProfessors(course);
		CourseClassDB.getInstance().removeCourseFromAllGrades(course);
		Main_Window_Tabs.getInstance().updateSubjectView("CourseClass Removed", course.getCode());
	}
	
	public void EditCourseClass(int row, short sifra, String name, int year, Semester s, int espb) {
		
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		CourseClassDB.getInstance().editCourseClass(course.getCode(), sifra, name, year, s, espb);
		Main_Window_Tabs.getInstance().updateSubjectView("CourseClass Edited", course.getCode());
	}
	
	// dodaj predmet
	public void AddCourseToStudent(int row, IndexNum index) {
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		Student std = StudentDB.getInstance().getStudentByIdx(index);
		
		CourseClassDB.getInstance().addCourseToStudent(course, std);
		StudentDB.getInstance().addStudentToCourseFailed(std, course);
		Edit_Student_Tabs.updateUnpassedSubjectsView("Added course to failed", index.getIdxnum());
	}
	
	// obrisi predmet
	public void RemoveCourseFromStudent(int row, IndexNum index) {
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		Student std = StudentDB.getInstance().getStudentByIdx(index);
		
		CourseClassDB.getInstance().removeCourseFromStudent(course, std);
		StudentDB.getInstance().removeStudentFromCourseFailed(std, course);
		Edit_Student_Tabs.updateUnpassedSubjectsView("Removed course from failed", index.getIdxnum());
	}
		
	// polozi predmet
	public void PassCourse(int row, IndexNum index, int grade, Date date) {
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		Student std = StudentDB.getInstance().getStudentByIdx(index);
		Grade g = new Grade(std, course, grade, date);
		
		GradeDB.getInstance().addGrade(std, course, grade, date);
		CourseClassDB.getInstance().removeCourseFromStudent(course, std);
		StudentDB.getInstance().removeStudentFromCourseFailed(std, course);
		StudentDB.getInstance().addStudentToCoursePassed(std, course);
		GradeDB.getInstance().addGradeToStudent(g, std);
		Edit_Student_Tabs.updateUnpassedSubjectsView("Removed course from failed", index.getIdxnum());
		Edit_Student_Tabs.updatePassedSubjectsView("Add course to grades", index.getIdxnum());
	}
	
	// obrisi predmet profesoru
	public void RemoveCourseFromProfessor(int row, int ID) {
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		Professor prof = ProfessorDB.getInstance().getProfByID(ID);
		
		CourseClassDB.getInstance().removeCourseFromProfessor(course, prof);
		ProfessorDB.getInstance().removeProfessorFromSubject(course);
		
		if(Edit_Professor_Tabs.getTableOfProfessorSubjects() != null)
			Edit_Professor_Tabs.updateProfessorSubjectsView("Remove course from prof", prof.getIDnum());
		
		if(Main_Window_Tabs.getInstance().getTableOfSubjects() != null)
			Main_Window_Tabs.getInstance().updateSubjectView("Add course to professor", ID);
	}
	
	// dodaj predmet profesoru
	
	public void AddCourseToProfessor(int row, int id_prof) {
		CourseClass course = CourseClassDB.getInstance().getRow(row);
		Professor professor = ProfessorDB.getInstance().getProfByID(id_prof);
		
		CourseClassDB.getInstance().addCourseToProfessor(course, professor);
		ProfessorDB.getInstance().addProfessorToSubject(course, professor);
		
		if(Edit_Professor_Tabs.getTableOfProfessorSubjects() != null)
			Edit_Professor_Tabs.updateProfessorSubjectsView("Add course to professor", id_prof);
		
		if(Main_Window_Tabs.getInstance().getTableOfSubjects() != null)
			Main_Window_Tabs.getInstance().updateSubjectView("Add course to professor", id_prof);
	}
	
}
