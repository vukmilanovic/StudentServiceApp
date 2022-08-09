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

import model_sistema.CourseChair;
import model_sistema.CourseClass;
import model_sistema.Professor;

public class CourseChairDB {
	
	private static CourseChairDB instance = null;
	
	public static CourseChairDB getInstance() {
		if(instance == null)
			instance = new CourseChairDB();
		
		return instance;
	}
	
	private List<CourseChair> chairs;
	private List<String> cols;
	
	private CourseChairDB() {
		
		initChair_list();
		
		this.cols = new ArrayList<String>(2);
		this.cols.add("Šifra katedre");
		this.cols.add("Naziv katedre");
		
	}

	// prepraviti za serijalizaciju i deserijalizaciju
	private void initChair_list() {
		this.chairs = new ArrayList<CourseChair>();
		try {
			this.chairs = ReadDataBaseState();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<CourseChair> getChairs(){
		return chairs;
	}
	
	public void setChairs(List<CourseChair> chairs) {
		this.chairs = chairs;
	}
	
	public int getColNum() {
		return cols.size();
	}
	
	public String getColName(int idx) {
		return cols.get(idx);
	}
	
	public CourseChair getRow(int idx) {
		return chairs.get(idx);
	}
	
	public Object getValueAt(int row, int col) {
		CourseChair chair = getRow(row);
		
		switch(col) {
		case 0:
			return chair.getCode();
		case 1:
			return chair.getName();
		default:
			return null;
		}
	}
	
	public void addCourseChair(int code, String name, Professor prof) {
		try {
			for(CourseChair c : chairs)
				if(c.getCode() == code)
					throw new Exception("Entity already exsists");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.chairs.add(new CourseChair(code, name, prof, new HashSet<Professor>()));
	}
	
	public void removeCourseChair(int code) {
		for(CourseChair c : chairs)
			if(c.getCode() == code) {
				chairs.remove(c);
				break;
			}
	}
	
	public void editCourseChair(int edit_code, int code, String name, Professor prof) {
		for(CourseChair c : chairs)
			if(c.getCode() == edit_code) {
				c.setCode(code);
				c.setName(name);
				c.setHead(prof);
				break;
			}
	}
	
	public void SaveDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Chairs.txt");
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		
		try {
			oos.writeObject(chairs);
		}
		finally {
			oos.close();
		}
	}
	
	public List<CourseChair> ReadDataBaseState() throws IOException, FileNotFoundException, ClassNotFoundException {
		File f = new File("Database\\Chairs.txt");
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		List<CourseChair> retval;
		
		try {
			retval = (ArrayList<CourseChair>) ois.readObject();
		}
		finally {
			ois.close();
		}
		
		return retval;
	}
	
}
