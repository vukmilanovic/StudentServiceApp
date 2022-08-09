package model_sistema;

import java.io.Serializable;

public class IndexNum implements Serializable{
	private static final long serialVersionUID = 5009596743401101177L;
	
	private String department;
	private int idxnum;
	private int year;
	
	public IndexNum(String department, int idxnum, int year) {
		if(department.length() <= 25 && department.length() >= 0) {
			this.department = department;
		}
		else {
			throw new RuntimeException("Invalid department");
		}
		
		if(idxnum > 0 && idxnum < 1000 ) {
			this.idxnum = idxnum;
		}
		else {
			throw new RuntimeException("Invalid index number");
		}
		
		if(year > 2000 && year < 2100) {
			this.year = year;
		}
		else {
			throw new RuntimeException("Invalid index year");
		}
		
	}

	public String getdepartment() {
		return department;
	}

	public void setdepartment(String department) {
		if(department.length() <= 25 && department.length() >= 0) {
			this.department = department;
		}
		else {
			throw new RuntimeException("Invalid department");
		}
	}

	public int getIdxnum() {
		return idxnum;
	}

	public void setIdxnum(int idxnum) {
		if(idxnum > 0 && idxnum < 1000 ) {
			this.idxnum = idxnum;
		}
		else {
			throw new RuntimeException("Invalid index number");
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year > 2000 && year < 2100) {
			this.year = year;
		}
		else {
			throw new RuntimeException("Invalid index year");
		}
	}

	@Override
	public String toString() {
		return department + "-" + idxnum + "-" + year;
	}
	
	@Override
	public boolean equals(Object indxNum) {
		
		if(indxNum == this) {
			return true;
		}
		
		if(!(indxNum instanceof IndexNum)) {
			return false;
		}
		
		IndexNum in = (IndexNum) indxNum;
		
		return (department.equals(in.department)) && (in.idxnum == idxnum) && (in.year == year);
		
	}

}
