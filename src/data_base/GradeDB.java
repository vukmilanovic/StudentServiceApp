package data_base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model_sistema.Adress;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Semester;
import model_sistema.Status;
import model_sistema.Student;
import model_sistema.Title;

public class GradeDB {

	private static GradeDB instance = null;
	
	public static GradeDB getInstance() {
		if(instance == null) instance = new GradeDB();
		return instance;
	}

	private List<Grade> grades;
	private List<String> cols;
	
	public GradeDB() {
		this.cols = new ArrayList<String>();
		
		initGrade_List();
		
		this.cols.add("Sifra");
		this.cols.add("Naziv predmeta");
		this.cols.add("ESPB bodovi");
		this.cols.add("Ocena");
		this.cols.add("Datum");
	}

	private void initGrade_List() {
		this.grades = new ArrayList<Grade>();
		try {
			grades = ReadDataBaseState();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Grade> getGrades(){
		return grades;
	}
	
	public Set<Grade> getStudentGrades(Student std){
		return std.getPassed();
	}
	
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
	
	public int getColNum() {
		return cols.size();
	}
	
	public String getColName(int idx) {
		return cols.get(idx);
	}
	
	public Grade getRow(int idx) {
		return grades.get(idx);
	}
	
	public Object getValueAt(int row, int col) {
		Grade g = grades.get(row);
		
		switch (col) {
		case 0:
			return g.getCourse().getCode();
		case 1:
			return g.getCourse().getName();
		case 2:
			return g.getCourse().getEspb();
		case 3:
			return g.getGrade();
		case 4:
			return g.getD();
		default:
			return null;
		}
	}
	
	public void addGrade(Student s, CourseClass cs, int grade, Date date) {
		try {
			for(Grade g : grades) {
				if(g.getD().equals(date) && g.getCourse().getCode() == cs.getCode() && g.getStudent().getIdx().equals(s.getIdx())) {
					throw new Exception("Entety already exists");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Grade g = new Grade(s, cs, grade, date);
		grades.add(g);
	}
	
	public void removeGrade(IndexNum index, short code, Date date) {
		for(Grade g : grades) {
			if(g.getStudent().getIdx().equals(index) && g.getCourse().getCode() == code && g.getD().equals(date)) {
				grades.remove(g);
				break;
			}
		}
	}
	
	public void editGrade(Date dateKey, short codeKey, IndexNum idxKey, Student s, CourseClass cs, int grade, Date date) {
		for(Grade g : grades) {
			if(g.getD().equals(dateKey) && g.getCourse().getCode() == codeKey && g.getStudent().getIdx().equals(idxKey)) {
				
				g.setStudent(s);
				g.setCourse(cs);
				g.setGrade(grade);
				g.setD(date);
				break;
			}
		}
	}
	
	public void addGradeToStudent(Grade grade, Student std) {
		try {	
			for(Grade g : std.getPassed()) {
				if(g.getD().equals(grade.getD()) && g.getCourse().getCode() == grade.getCourse().getCode()) {
					throw new Exception("Entity already exists");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		std.addPassed(grade);
	}
	
	public void removeGradeFromStudent(Grade grade, Student std) {
		for(Grade g : std.getPassed()) {
			if(g.getD().equals(grade.getD()) && g.getCourse().getCode() == grade.getCourse().getCode()) {
				std.removePassed(g);
				break;
			}
		}
	}
	
	public void SaveDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Grades.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(grades);
		}
		finally {
			oos.close();
		}
	}
	
	public List<Grade> ReadDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Grades.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		List<Grade> retval;
		
		try {
			retval = (ArrayList<Grade>) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		return retval;
	}
	
}
