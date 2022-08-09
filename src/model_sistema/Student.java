package model_sistema;

import java.io.Serializable;
import java.util.Set;

public class Student extends Person implements Serializable{
	private static final long serialVersionUID = -6499096053589349475L;
	
	private Status s;
	private IndexNum idx;
	private int enrollyear;
	private int curryear; // year of studies
	private double avg;
	private Set<Grade> passed; // all passed courses are in Grade by definition since min grade is 6
	private Set<CourseClass> failed;
	private int pointsESPB;
	
	public Student(String name, String surname, Date dob, Adress adr, String tel, String mail, IndexNum idx,
			int enrollyear, int curryear, Status s, Set<Grade> passed, Set<CourseClass> failed) {
		
		super(name, surname, dob, adr, tel, mail);
		this.idx = idx;
		
		if(enrollyear >= 1900 && enrollyear <= 2100) this.enrollyear = enrollyear;
		else throw new RuntimeException("Invalid enroll year");
		
		if(curryear >= 0 && curryear <= 10) this.curryear = curryear;
		else throw new RuntimeException("Invalid current year");
		
		this.s = s;
		
		this.passed = passed;
		
		this.failed = failed;
		
		int sum = 0;
		for(Grade g : passed) {
			sum += g.getGrade();
		}
		
		if(passed.isEmpty()) this.avg = 0;
		else this.avg = sum / passed.size();
		
		sum = 0;
		for(Grade g : passed) {
			sum += g.getCourse().getEspb();
		}
		
		if(passed.isEmpty()) this.pointsESPB = 0;
		else this.pointsESPB = sum;
	}

	public Status getS() {
		return s;
	}

	public void setS(Status s) {
		this.s = s;
	}

	public IndexNum getIdx() {
		return idx;
	}

	public void setIdx(IndexNum idx) {
		this.idx = idx;
	}

	public int getEnrollyear() {
		return enrollyear;
	}

	public void setEnrollyear(int enrollyear) {
		if(enrollyear >= 1900 && enrollyear <= 2100) this.enrollyear = enrollyear;
		else throw new RuntimeException("Invalid enroll year");
	}

	public int getCurryear() {
		return curryear;
	}

	public void setCurryear(int curryear) {
		if(curryear >= 0 && curryear <= 10) this.curryear = curryear;
		else throw new RuntimeException("Invalid current year");
	}

	public Set<Grade> getPassed() {
		return passed;
	}

	public void addPassed(Grade g) {
		passed.add(g);
	}
	
	public void removePassed(Grade g) {
		for(Grade gr : passed) {
			if(gr.getCourse().getCode() == g.getCourse().getCode() &&
			   gr.getStudent().getIdx().equals(g.getStudent().getIdx()) &&
			   gr.getD().equals(g.getD())) {
				
				passed.remove(gr);
				break;
			}
		}
	}

	public Set<CourseClass> getFailed() {
		return failed;
	}

	public void addFailed(CourseClass c) {
		failed.add(c);
	}
	
	public void removeFailed(CourseClass c) {
		for(CourseClass cs : failed) {
			if(cs.getCode() == c.getCode()) {
				failed.remove(cs);
				break;
			}
		}
	}
	
	public double getAvg() {
		return avg;
	}

	public void setAvg() {
		float sum = 0;
		for(Grade g : passed) {
			sum += g.getGrade();
		}
		
		if(passed.isEmpty()) this.avg = 0;
		else {
			this.avg = Math.round(sum / passed.size() * 100.00) / 100.00;
		}
	}
	
	public int getSumESPB() {
		return pointsESPB;
	}
	
	public void setSumESPB() {
		int sum = 0;
		for(Grade g : passed) {
			sum += g.getCourse().getEspb();
		}
		
		if(passed.isEmpty()) this.pointsESPB = 0;
		else this.pointsESPB = sum;
	}
	
	@Override
	public String toString() {
		return "Student: " + super.toString() + "\n" +
			   "Indeks: " + idx + "\n" + 
			   "Godina upisa: " + enrollyear + "\n" +
			   "Trenutna godina: " + curryear + "\n" +
			   "Prosecna ocena: " + avg + "\n" +
			   "Polozeni ispiti: " + passed + "\n" +
			   "Nepolozeni ispiti: " + failed;
	}
	
}
