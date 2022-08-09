package controller;

import data_base.CourseClassDB;
import data_base.GradeDB;
import data_base.StudentDB;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Student;
import view.edit.Edit_Student_Tabs;

public class GradeController {

	private static GradeController instance = null;
	
	public static GradeController getInstance() {
		if(instance == null) instance = new GradeController();
		return instance;
	}
	
	private GradeController() {}
	
	public void AddGrade(Student s, CourseClass cs, int grade, Date date) {
		GradeDB.getInstance().addGrade(s, cs, grade, date);
		Edit_Student_Tabs.updatePassedSubjectsView("Grade added", -1);
	}
	
	public void RemoveGrade(int row) {
		Grade grade = GradeDB.getInstance().getRow(row);
		GradeDB.getInstance().removeGrade(grade.getStudent().getIdx(), grade.getCourse().getCode(), grade.getD());
		Edit_Student_Tabs.updatePassedSubjectsView("Grade removed", grade.getGrade());
	}
	
	public void EditGrade(int row, Student s, CourseClass cs, int grade, Date date) {
		Grade g = GradeDB.getInstance().getRow(row);
		GradeDB.getInstance().editGrade(g.getD(), g.getCourse().getCode(), g.getStudent().getIdx(), s, cs, grade, date);
		Edit_Student_Tabs.updatePassedSubjectsView("Grade edited", grade);
	}
	
	// ponisti ocenu
	public void RemoveGradeFromStudent(int row, IndexNum index) {
		Grade grade = GradeDB.getInstance().getRow(row);
		CourseClass course = grade.getCourse();
		Student std = StudentDB.getInstance().getStudentByIdx(index);
		
		GradeDB.getInstance().removeGradeFromStudent(grade, std);
		StudentDB.getInstance().removeStudentFromCoursePassed(std, course);
		StudentDB.getInstance().addStudentToCourseFailed(std, course);
		CourseClassDB.getInstance().addCourseToStudent(course, std);
		Edit_Student_Tabs.updateUnpassedSubjectsView("Add course to failed", index.getIdxnum());
		Edit_Student_Tabs.updatePassedSubjectsView("Remove course from grades", index.getIdxnum());
	}
}
