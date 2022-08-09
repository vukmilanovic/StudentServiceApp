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
import java.util.Iterator;
import java.util.List;

import model_sistema.Adress;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.IndexNum;
import model_sistema.Professor;
import model_sistema.Semester;
import model_sistema.Student;
import model_sistema.Title;
import view.main_frame.Main_window;
import model_sistema.Status;

public class StudentDB {

	private static StudentDB instance = null;
	
	public static StudentDB getInstance() {
		if(instance == null) {
			instance = new StudentDB();
		}
		
		return instance;
	}
	
	private int 			id_num;
	private List<Student>	student_list;
	private List<String>	cols;
	
	private StudentDB() {
		id_num = 0;
		
		// ova funkcija ce citati iz fajla za bazu
		initStudent_List();
		
		// init cols
		this.cols = new ArrayList<String>();
		this.cols.add("Indeks");
		this.cols.add("Ime");
		this.cols.add("Prezime");
		this.cols.add("Godina studija");
		this.cols.add("Status");
		this.cols.add("Prosek");
	}

	private void initStudent_List() {
		this.student_list = new ArrayList<Student>();
		try {
			this.student_list = ReadDataBaseState();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudent_list() {
		return student_list;
	}

	public void setStudent_list(List<Student> student_list) {
		this.student_list = student_list;
	}
	
	public Student getStudentByIdx(IndexNum idx) {
		for(Student s : student_list) {
			if(s.getIdx().equals(idx)) {
				return s;
			}
		}
		
		return null;
	}
	
	private IndexNum generateIndex() {
		// TODO Auto-generated method stub
		return new IndexNum("RA", ++id_num, 2019);
	}
	
	public int getColNum() {
		return cols.size();
	}
	
	public String getColName(int idx) {
		return this.cols.get(idx);
	}
	
	public Student getRow(int idx) {
		return this.student_list.get(idx);
	}
	
	// vracanje object ce mozda praviti problem
	public Object getValueAt(int row, int col) {
		Student student = this.student_list.get(row);
		switch(col) {
		case 0:
			return student.getIdx();
		case 1:
			return student.getName();
		case 2:
			return student.getSurname();
		case 3:
			return student.getCurryear();
		case 4:
			return student.getS();
		case 5:
			student.setAvg();
			return student.getAvg();
		default:
			return null;
		}
	}
	
	public void addStudent(String name, String surname, Date dob, Adress adr, String tel, String mail, IndexNum idx, int enroll_year, int current_year, Status status) {
		
		try {
			for(Student std : student_list) {
				if(std.getIdx().equals(idx)) {
					throw new Exception("Entity already exists");
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		this.student_list.add(new Student(name, surname, dob, adr, tel, mail, idx, enroll_year, current_year,
				status, new HashSet<Grade>(), new HashSet<CourseClass>()));
	}
	
	public void removeStudent(IndexNum index) {
		for(Student std : student_list) {
			if(std.getIdx() == index) {
				
				student_list.remove(std);
				break;
			}
		}
	}
	
	public void editStudent(IndexNum index, String name, String surname, Date dob, Adress adr, String tel,
			String mail, IndexNum idx, int enroll_year, int current_year, Status status) {
		
		for(Student std : student_list) {
			if(std.getIdx() == index) {
				
				std.setName(name);
				std.setSurname(surname);
				std.setDob(dob);
				std.setAdr(adr);
				std.setTel(tel);
				std.setMail(mail);
				std.setIdx(idx);
				std.setEnrollyear(enroll_year);
				std.setCurryear(current_year);
				std.setS(status);
				break;
			}
		}
	}
	
	public void addStudentToCourseFailed(Student std, CourseClass course) {
		try {	
			for(Student s : course.getFailed()) {
				if(std.getIdx().equals(s.getIdx())) throw new Exception("Entity already exists");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		course.addFailed(std);
	}
	
	public void removeStudentFromCourseFailed(Student std, CourseClass course) {
		for(Student s : course.getFailed()) {
			if(std.getIdx().equals(s.getIdx())) {
				course.removeFailed(s);
				break;
			}
		}
	}
	
	public void addStudentToCoursePassed(Student std, CourseClass course) {
		try {	
			for(Student s : course.getPassed()) {
				if(std.getIdx().equals(s.getIdx())) throw new Exception("Entity already exists");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		course.addPassed(std);
	}
	
	public void removeStudentFromCoursePassed(Student std, CourseClass course) {
		for(Student s : course.getPassed()) {
			if(std.getIdx().equals(s.getIdx())) {
				course.removePassed(s);
				break;
			}
		}
	}
	
	public void removeStudentFromAllCoursesPassed(Student std) {
		for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
			Iterator<Student> it = cs.getPassed().iterator();
			while(it.hasNext()) {
				if(it.next().getIdx().equals(std.getIdx())) {
					it.remove();
					break;
				}
			}
		}
	}
	
	public void removeStudentFromAllCoursesFailed(Student std) {
		for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
			Iterator<Student> it = cs.getFailed().iterator();
			while(it.hasNext()) {
				if(it.next().getIdx().equals(std.getIdx())) {
					it.remove();
					break;
				}
			}
		}
	}
	
	public void removeAllStudentGrades(Student std) {
		Iterator<Grade> it = GradeDB.getInstance().getGrades().iterator();
		while(it.hasNext()) {
			if(it.next().getStudent().getIdx().equals(std.getIdx()))
				it.remove();
		}
	}
	
	public void SaveDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Students.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(student_list);
		}
		finally {
			oos.close();
		}
	}
	
	public List<Student> ReadDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Students.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		List<Student> retval;
		
		try {
			retval = (ArrayList<Student>) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		return retval;
	}
	
}
