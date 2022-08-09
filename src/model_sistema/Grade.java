package model_sistema;

import java.io.Serializable;

public class Grade implements Serializable{
	
	private static final long serialVersionUID = -163563852566298561L;
	
	private Student student;
	private CourseClass course;
	private int grade;
	private Date d;
	
	public Grade(Student student, CourseClass course, int grade, Date d) {
		this.student= student;
		this.course = course;
		
		if(grade >= 6 && grade <= 10) this.grade = grade;
		else throw new RuntimeException("Invalid grade input");
		
		this.d = d;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseClass getCourse() {
		return course;
	}

	public void setCourse(CourseClass course) {
		this.course = course;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		if(grade >= 6 && grade <= 10) this.grade = grade;
		else throw new RuntimeException("Invalid grade input");
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Student: " + student + "\n" +
			   "Predmet: " + course + "\n" +
			   "Ocena: " + grade + "\n" +
			   "Datum polaganja: " + d;
	}
	
	
}
