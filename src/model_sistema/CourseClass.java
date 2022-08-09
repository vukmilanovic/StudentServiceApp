package model_sistema;

import java.io.Serializable;
import java.util.Set;

public class CourseClass implements Serializable {

	private static final long serialVersionUID = -4409019136940995955L;
	
	private short code;
	private String name;
	private Semester sem;
	private int year;
	private Professor prof = null;
	private int espb;
	private Set<Student> passed;
	private Set<Student> failed;
	
	public CourseClass() {
		
	}
	
	public CourseClass(short code, String name, Semester sem, int year, Professor prof, int espb, Set<Student> passed, Set<Student> failed) {
		if(code >= 0) {
			this.code = code;
		}
		else throw new RuntimeException("Invalid course code");
		
		if(name.length() >= 0) {
			this.name = name;
		}
		else throw new RuntimeException("Invalid course name");
		
		this.sem = sem;
		
		if(year >= 1 && year <= 4) {
			this.year = year;
		}
		else throw new RuntimeException("Invalid course year");
		
		this.prof = prof;
		
		if(espb >= 0) {
			this.espb = espb;
		}
		else throw new RuntimeException("Invalid course espb number");
		
		this.passed = passed;
		
		this.failed = failed;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		if(code >= 0) {
			this.code = code;
		}
		else throw new RuntimeException("Invalid course code");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() >= 0 && name.length() <= 25) {
			this.name = name;
		}
		else throw new RuntimeException("Invalid course name");
	}

	public Semester getSem() {
		return sem;
	}

	public void setSem(Semester sem) {
		this.sem = sem;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year >= 1 && year <= 4) {
			this.year = year;
		}
		else throw new RuntimeException("Invalid course year");
	}
	
	public Professor getProf() {
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}
	
	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		if(espb >= 0) {
			this.espb = espb;
		}
		else throw new RuntimeException("Invalid course espb number");
	}

	public Set<Student> getPassed() {
		return passed;
	}

	public void addPassed(Student s) {
		passed.add(s);
	}
	
	public void removePassed(Student s) {
		for(Student std : passed) {
			if(std.getIdx().equals(s.getIdx())) {
				passed.remove(std);
				break;
			}
		}
	}

	public Set<Student> getFailed() {
		return failed;
	}

	public void addFailed(Student s) {
		failed.add(s);
	}
	
	public void removeFailed(Student s) {
		for(Student std : failed) {
			if(std.getIdx().equals(s.getIdx())) {
				passed.remove(std);
				break;
			}
		}
	}

	@Override
	public String toString() {
		return "Sifra: " + code + "\n" +
			   "Naziv: " + name + "\n" +
			   "Semestar: " + sem + "\n" +
			   "Profesor: " + prof + "\n" +
			   "ESPB: " + espb + "\n" +
			   "Polozili: " + passed + "\n" +
			   "Pali: " + failed + "\n";
	}
	
	
}
