package model_sistema;

import java.io.Serializable;
import java.util.Set;

public class Professor extends Person implements Serializable{
	private static final long serialVersionUID = 2047007458456241972L;
	
	private String office;
	private int IDnum;
	private Title t;
	private int serviceyrs;
	private Set<CourseClass> courses;
	
	public Professor(String name, String surname, Date dob, Adress adr, String tel, String mail, String office, int iDnum,
			Title t, int serviceyrs, Set<CourseClass> courses) {
		
		super(name, surname, dob, adr, tel, mail);
		
		if(office.length() >= 0 && office.length() <= 25) this.office = office;
		else throw new RuntimeException("Invalid office name");
		
		if(IDnum >= 0 && IDnum <= 15) IDnum = iDnum;
		else throw new RuntimeException("Invalid IDnum");
		
		this.t = t;
		
		if(serviceyrs >= 0) this.serviceyrs = serviceyrs;
		else throw new RuntimeException("Invalide number of service years");
		
		this.courses = courses;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		if(office.length() >= 0 && office.length() <= 25) this.office = office;
		else throw new RuntimeException("Invalid office name");
	}

	public int getIDnum() {
		return IDnum;
	}

	public void setIDnum(int iDnum) {
		if(IDnum >= 0 && IDnum <= 1000000000) IDnum = iDnum;
		else throw new RuntimeException("Invalid IDnum");
	}

	public Title getT() {
		return t;
	}

	public void setT(Title t) {
		this.t = t;
	}

	public int getServiceyrs() {
		return serviceyrs;
	}

	public void setServiceyrs(int serviceyrs) {
		if(serviceyrs >= 0) this.serviceyrs = serviceyrs;
		else throw new RuntimeException("Invalide number of service years");
	}

	public Set<CourseClass> getCourses() {
		return courses;
	}

	public void addCourses(CourseClass course) {
		courses.add(course);
	}
	
	public void removeCourse(CourseClass course) {
		System.out.println("Model");
		System.out.println(courses.size());
		for(CourseClass cs : courses) {
			if(cs.getCode() == course.getCode()) {
				courses.remove(cs);
				break;
			}
		}
		
		System.out.println(courses.size());
		System.out.println("Kraj Model");
	}

	@Override
	public String toString() {
		return "Profesor: " + super.toString() + "\n" +
			   "Kancelarija: " + office + "\n" +
			   "Broj licne karte: " + IDnum + "\n" +
			   "Zvanje: " + t + "\n" +
			   "Godine staza: " + serviceyrs + "\n" +
			   "Spisak predmeta: " + courses;
	}
	
	@Override
	public boolean equals(Object prof) {

		if(prof == this) {
			return true;
		}
		
		if(!(prof instanceof Professor)) {
			return false;
		}
		
		Professor pr = (Professor) prof;
		
		return (pr.IDnum == IDnum);
	}
	
}
