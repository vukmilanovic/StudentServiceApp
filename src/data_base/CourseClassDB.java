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
import java.util.Set;

import model_sistema.Adress;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Grade;
import model_sistema.Semester;
import model_sistema.Student;
import model_sistema.Title;
import model_sistema.Professor;

public class CourseClassDB {
	
	private static CourseClassDB instance = null;
	
	public static CourseClassDB getInstance() {
		if(instance == null) {
			instance = new CourseClassDB();
		}
		
		return instance;
	}

	private int					id_num;
	private List<CourseClass>	classes;
	private List<String>		MW_cols;		// for main window view
	private List<String>		failed_cols;	// for edit student view
	private List<String>		profsub_cols; 	// for edit professor view 
	
	private CourseClassDB() {
		id_num = 0;
		
		initCourse_List();
		
		this.MW_cols = new ArrayList<String>();
		this.MW_cols.add("Šifra predmeta");
		this.MW_cols.add("Naziv predmeta");
		this.MW_cols.add("ESPB bodovi");
		this.MW_cols.add("Godina studija");
		this.MW_cols.add("Semestar");
		
		this.failed_cols = new ArrayList<String>();
		this.failed_cols.add("Šifra predmeta");
		this.failed_cols.add("Naziv predmeta");
		this.failed_cols.add("ESPB bodovi");
		this.failed_cols.add("Godina studija");
		this.failed_cols.add("Semestar");
		
		this.profsub_cols = new ArrayList<String>();
		this.profsub_cols.add("Naziv predmeta");
		this.profsub_cols.add("Šifra predmeta");
		this.profsub_cols.add("Godina studija");
		this.profsub_cols.add("Semestar");
		
	}
	
	private void initCourse_List() {
		this.classes = new ArrayList<CourseClass>();
		try {
			this.classes = ReadDataBaseState();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private short generateID() {
		return (short) ++id_num;
	}
	
	public List<CourseClass> getClasses(){
		return classes;
	}
	
	public Set<CourseClass> getStudentClasses(Student std){
		return std.getFailed();
	}
	
	public void setClasses(List<CourseClass> classes) {
		this.classes = classes;
	}
	
	public int getMWColNum() {
		return MW_cols.size();
	}
	
	public int getFailedColNum() {
		return failed_cols.size();
	}
	
	public int getProfSubColNum() {
		return profsub_cols.size();
	}
	
	public String getMWColName(int idx) {
		return MW_cols.get(idx);
	}
	
	public String getFailedColName(int idx) {
		return failed_cols.get(idx);
	}
	
	public String getProfSubColName(int idx) {
		return profsub_cols.get(idx);
	}
	
	public CourseClass getRow(int idx) {
		return classes.get(idx);
	}
	
	public Object getValueAtMW(int row, int col) {
		CourseClass course = getRow(row);
		
		switch(col) {
		case 0:
			return course.getCode();
		case 1:
			return course.getName();
		case 2:
			return course.getEspb();
		case 3:
			return course.getYear();
		case 4:
			return course.getSem();
		default:
			return null;
		}
	}
	
	public Object getValueAtFailed(int row, int col) {
		CourseClass course = getRow(row);
		
		switch(col) {
		case 0:
			return course.getCode();
		case 1:
			return course.getName();
		case 2:
			return course.getEspb();
		case 3:
			return course.getYear();
		case 4:
			return course.getSem();
		default:
			return null;
		}
	}
	
	public Object getValueAtProfSub(int row, int col) {
		CourseClass course = getRow(row);
		
		switch(col) {
		case 0:
			return course.getName();
		case 1:
			return course.getCode();
		case 2:
			return course.getYear();
		case 3:
			return course.getSem();
		default:
			return null;
		}
	}
	
	public void addCourseClass(short sifra, String name, int year, Semester s, int espb) {		
		try {
			for(CourseClass course : classes) {
				if(course.getCode() == sifra) {
					throw new Exception("Entity already exists");
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.classes.add(new CourseClass(sifra, name, s, year, (Professor) null, espb, new HashSet<Student>(), new HashSet<Student>()));
	}
	
	public void removeCourseClass(short code) {
		for(CourseClass course : classes) {
			if(course.getCode() == code) {
				classes.remove(course);
				break;
			}
		}
	}
	
	public void editCourseClass(short code, short sifra, String name, int year, Semester s, int espb) {
		for(CourseClass course : classes) {
			if(course.getCode() == code) {
				course.setCode(sifra);
				course.setName(name);
				course.setYear(year);
				course.setSem(s);
				course.setEspb(espb);
				break;
			}
		}
	}
	
	public void addCourseToStudent(CourseClass course, Student std) {
		try {
			for(CourseClass cs : std.getFailed()) {
				if(cs.getCode() == course.getCode()) {
					throw new Exception("Entity already exists");
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		std.addFailed(course);
	}
	
	public void removeCourseFromStudent(CourseClass course, Student std) {
		for(CourseClass cs : std.getFailed()) {
			if(cs.getCode() == course.getCode()) {
				std.removeFailed(cs);
				break;
			}
		}
	}
	
	public void addCourseToProfessor(CourseClass course, Professor professor) {
		
		try {
			for(CourseClass cc : professor.getCourses()) {
				if(cc.getCode() == course.getCode()) {
					throw new Exception("Entity already exists");
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		professor.getCourses().add(course);
	}
	
	public void removeCourseFromProfessor(CourseClass course, Professor professor) {
		System.out.println("DB");
		System.out.println(professor.getCourses().size());
		for(CourseClass cc : professor.getCourses()) {
			if(cc.getCode() == course.getCode()) {
				professor.removeCourse(course);
				break;
			}
		}
		System.out.println(professor.getCourses().size());
		System.out.println("Kraj DB");
	}
	
	public void removeCourseFromAllStudents(CourseClass course) {
		for(Student std : StudentDB.getInstance().getStudent_list()) {
			Iterator<CourseClass> it = std.getFailed().iterator();
			while(it.hasNext()) {
				if(it.next().getCode() == course.getCode()) {
					it.remove();
					break;
				}
			}
		}
	}
	
	public void removeCourseFromAllProfessors(CourseClass course) {
		for(Professor prof : ProfessorDB.getInstance().getProf_list()) {
			Iterator<CourseClass> it = prof.getCourses().iterator();
			while(it.hasNext()) {
				if(it.next().getCode() == course.getCode()) {
					it.remove();
					break;
				}
			}
		}
	}
	
	public void removeCourseFromAllGrades(CourseClass course) {
		
		// izbrisi sve ocene sa ovim predmetom od studenata
		for(Student std : StudentDB.getInstance().getStudent_list()) {
			Iterator<Grade> grade_it = std.getPassed().iterator();
			while(grade_it.hasNext()) {
				if(grade_it.next().getCourse().getCode() == course.getCode())
					grade_it.remove();
			}
		}
		
		// izbrisi sve ocene ovog predmeta
		Iterator<Grade> grade_it = GradeDB.getInstance().getGrades().iterator();
		while(grade_it.hasNext()) {
			if(grade_it.next().getCourse().getCode() == course.getCode())
				grade_it.remove();
		}
		
	}
	
	public void SaveDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Courses.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(classes);
		}
		finally {
			oos.close();
		}
	}
	
	public List<CourseClass> ReadDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Courses.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		List<CourseClass> retval;
		
		try {
			retval = (ArrayList<CourseClass>) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		return retval;
	}
	
}
