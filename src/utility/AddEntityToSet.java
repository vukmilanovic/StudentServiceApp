package utility;

import data_base.CourseClassDB;
import data_base.StudentDB;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.Professor;
import model_sistema.Student;

public class AddEntityToSet {

	public AddEntityToSet() {
	}
	
	public static void AssignPassedSubjectToStudent(Student student, Grade grade) {
		
		for(Grade g : student.getPassed()) {
			if(g.getCourse().getCode() == grade.getCourse().getCode() && g.getD().equals(grade.getD())) {
				return;
			}
		}
		
		student.getPassed().add(grade);
		
	}
	
	public static void AssignUnpassedSubjectToStudent(Student student, CourseClass courseClass) {
		
		for(CourseClass cc : student.getFailed()) {
			if(cc.getCode() == courseClass.getCode()) {
				return;
			}
		}
		
		student.getFailed().add(courseClass);
		
	}
	
//	public static void AssignAllSubjectsToNewStudent(Student student) {
//		
//		for(CourseClass cc : CourseClassDB.getInstance().getClass_list()) {
//			student.getFailed().add(cc);
//		}
		
//	}
	
//	public static void AssignAllStudentsToNewSubject(CourseClass courseClass) {
//		
//		for(Student student : StudentDB.getInstance().getStudent_list()) {
//			courseClass.getFailed().add(student);
//		}
		
//	}
	
	public static void AddStudentToPassedSubject(CourseClass courseClass, Student student) {
		
		for(Student std : courseClass.getPassed()) {
			if(std.getIdx().equals(student.getIdx())) {
				return;
			}
		}
		
		courseClass.getPassed().add(student);
		
	}
	
	public static void AddStudentToUnpassedSubject(CourseClass courseClass, Student student) {
		
		for(Student std : courseClass.getFailed()) {
			if(std.getIdx().equals(student.getIdx())) {
				return;
			}
		}
		
		courseClass.getFailed().add(student);
		
	}
	
	public static void AssignSubjectToProfessor(Professor professor, CourseClass courseClass) {
		
		for(CourseClass cc : professor.getCourses()) {
			if(cc.getCode() == courseClass.getCode()) {
				return;
			}
		}
		
		professor.getCourses().add(courseClass);
	}
	
	public static void AssignProfessorToSubject(CourseClass courseClass, Professor professor) {
		
		if(courseClass.getProf() == null) {
			courseClass.setProf(professor);
		}
		
	}
	
	public static void RemoveProfessorFromSubject(CourseClass courseClass) {
		
		if(courseClass.getProf() != null) {
			courseClass.setProf(null);
		}
		
	}
	
	public static void MovePassedSubjectToUnpassed(Student student, Grade grade) {
		
		//fali promena prikaza tabele
		
		for(Grade g : student.getPassed()) {
			if(g.getCourse().getCode() == grade.getCourse().getCode() && g.getD().equals(grade.getD())) {
				student.getFailed().add(grade.getCourse());
				student.getPassed().remove(grade);
			}
		}
		
	}
	
	public static void MoveUnpassedSubjectToPassed(Student student, CourseClass courseClass, int grade, Date date) {
	
		//fali promena prikaza tabele
		
		//Ova provera ide u funkciju za dodavanje predmeta nekom studentu da ga sluša
		//if(student.getCurryear() < courseClass.getYear()) {
		//	return;
		//}
		
		for(CourseClass cc : student.getFailed()) {
			if(cc.getCode() == courseClass.getCode()) {
				student.getPassed().add(new Grade(student, courseClass, grade, date));
				student.getFailed().remove(courseClass);
			}
		}
		
	}
}
