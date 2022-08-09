package model_sistema;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date implements Serializable{

	private static final long serialVersionUID = 8517745772843494234L;
	
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		if(day >= 1 && day <= 31) {
			this.day = day;
		}
		else {
			throw new RuntimeException("Invalid day");
		}
		
		if(month >= 1 && month <= 12) {
			this.month = month;
		}
		else {
			throw new RuntimeException("Invalid month");
		}
		
		if(year >= 1900 && year <= 2100) {
			this.year = year;
		}
		else {
			throw new RuntimeException("Invalid year");
		}
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if(day >= 1 && day <= 31) {
			this.day = day;
		}
		else {
			throw new RuntimeException("Invalid day");
		}
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if(month >= 1 && month <= 12) {
			this.month = month;
		}
		else {
			throw new RuntimeException("Invalid month");
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if(year >= 1900 && year <= 2100) {
			this.year = year;
		}
		else {
			throw new RuntimeException("Invalid year");
		}
	}

	public static String getCurrentDate() {
	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
	
		LocalDateTime date_and_time = LocalDateTime.now();
		
		return dtf.format(date_and_time);
	}
	
	@Override
	public String toString() {
		return day + "-" + month + "-" + year;
	}
	
	@Override
	public boolean equals(Object date) {
		
		if(date == this) {
			return true;
		}
		
		if(!(date instanceof Date)) {
			return false;
		}
		
		Date dt = (Date) date;
	
		return (dt.day == day) && (dt.month == month) && (dt.year == year);
		
	}

}
