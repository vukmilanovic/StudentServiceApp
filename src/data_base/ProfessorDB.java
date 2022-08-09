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
import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.Date;
import model_sistema.Professor;
import model_sistema.Student;
import model_sistema.Title;

public class ProfessorDB {
	
	private static ProfessorDB instance = null;
	
	public static ProfessorDB getInstance() {
		if(instance == null) {
			instance = new ProfessorDB();
		}
		
		return instance;
	}
	
	private List<Professor> prof_list;
	private List<String>	cols;
	
	private ProfessorDB() {
		
		initProfessor_List();
		
		this.cols = new ArrayList<String>();
		this.cols.add("Ime");
		this.cols.add("Prezime");
		this.cols.add("Titula");
		this.cols.add("E-Mail");
	}

	private void initProfessor_List() {
		this.prof_list = new ArrayList<Professor>();
		try {
			prof_list = ReadDataBaseState();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Professor> getProf_list() {
		return prof_list;
	}
	
	public Professor getProfByID(int ID) {
		Professor prof = null;
		for(Professor p : prof_list) {
			if(p.getIDnum() == ID) {
				prof = p;
				break;
			}
		}
		
		return prof;
	}

	public void setProf_list(List<Professor> prof_list) {
		this.prof_list = prof_list;
	}
	
	public int getColNum() {
		return cols.size();
	}
	
	public String getColName(int idx) {
		return this.cols.get(idx);
	}
	
	public Professor getRow(int idx) {
		return this.prof_list.get(idx);
	}
	
	public Object getValueAt(int row, int col) {
		Professor prof = this.prof_list.get(row);
		
		switch(col) {
		case 0:
			return prof.getName();
		case 1:
			return prof.getSurname();
		case 2:
			return prof.getT();
		case 3:
			return prof.getMail();
		default:
			return null;
		}
	}
	
	public void addProfessor(String name, String surname, Date dob, Adress adr, String tel, String mail, String kanc, int IDnum, Title t, int god) {
		
		try {
			for(Professor prof : prof_list) {
				if(prof.getIDnum() == IDnum) {
					throw new Exception("Entity already exists");
				} 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		prof_list.add(new Professor(name, surname, dob, adr, tel, mail, kanc, IDnum, t , god, new HashSet<CourseClass>()));
		
	}
	
	public void removeProfessor(int IDnum) {
		for(Professor prof : prof_list) {
			if(prof.getIDnum() == IDnum) {
				prof_list.remove(prof);
				break;
			}
		}
	}
	
	public void editProfessor(int IDnum, String name, String surname, Date dob, Adress adr, String tel, String mail, String kanc, int newIDnum, Title t, int god) {
		
		for(Professor prof : prof_list) {
			if(prof.getIDnum() == IDnum) {
				
				prof.setName(name);
				prof.setSurname(surname);
				prof.setDob(dob);
				prof.setAdr(adr);
				prof.setTel(tel);
				prof.setMail(mail);
				prof.setOffice(kanc);
				prof.setIDnum(newIDnum);
				prof.setT(t);
				prof.setServiceyrs(god);
				break;
			}
		}
	}
	
	public void addProfessorToSubject(CourseClass course, Professor professor) {	
			if(course.getProf() == null) {
				course.setProf(professor);
			}
	}
	
	public void removeProfessorFromSubject(CourseClass course) {
		if(course.getProf() != null) {
			course.setProf(null);
		}
	}
	
	public void removeProfessorFromAllSubjects(Professor prof) {
		for(CourseClass cs : CourseClassDB.getInstance().getClasses()) {
			if(cs.getProf() != null) {
				if(cs.getProf().getIDnum() == prof.getIDnum()) {
					cs.setProf(null);
				}
			}
		}
	}
	
	public void addProfessorToChairHead(CourseChair chair, Professor head) {
		if(chair.getHead() == null)
			chair.setHead(head);
	}
	
	public void removeProfessorFromChairHead(CourseChair chair) {
		if(chair.getHead() != null) {
			chair.setHead(null);
		}
	}
	
	public void addProfessorToChairProfs(CourseChair chair, Professor prof) {
		try {
			for(Professor p : chair.getProfs())
				if(p.getIDnum() == prof.getIDnum())
					throw new Exception("Entity already exists");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		chair.addProfs(prof);
	}
	
	public void removeProfessorFromChairProfs(CourseChair chair, Professor prof) {
		for(Professor p : chair.getProfs())
			if(p.getIDnum() == prof.getIDnum()) {
				chair.removeProfs(p);
				break;
			}
	}
	
	public void removeProfessorFromAllChairHeads(Professor prof) {
		for(CourseChair chair : CourseChairDB.getInstance().getChairs())
			if(chair.getHead() != null)
				if(prof.getIDnum() == chair.getHead().getIDnum())
					chair.setHead(null);
	}
	
	public void removeProfessorFromAllChairProfs(Professor prof) {
		for(CourseChair chair : CourseChairDB.getInstance().getChairs()) {
			Iterator<Professor> it = chair.getProfs().iterator();
			while(it.hasNext()) {
				if(it.next().getIDnum() == prof.getIDnum()) {
					it.remove();
					break;
				}
			}
		}
	}
	
	public void SaveDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Professors.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(prof_list);
		}
		finally {
			oos.close();
		}
	}
	
	public List<Professor> ReadDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Professors.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		List<Professor> retval;
		
		try {
			retval = (ArrayList<Professor>) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		return retval;
	}
}
