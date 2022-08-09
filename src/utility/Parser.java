package utility;

import model_sistema.Adress;
import model_sistema.Date;
import model_sistema.IndexNum;
import model_sistema.Semester;
import model_sistema.Status;
import model_sistema.Title;

public class Parser {

	public Parser() {
	}

	public static Date parseDateFromString(String date) {
		
		int day;
		int month;
		int year;
		
		String[] split = date.split("-", 3);
		
		day = Integer.parseInt(split[0]);
		month = Integer.parseInt(split[1]);
		year = Integer.parseInt(split[2]);
		
		if((day >= 1 && day <= 31) && 
			(month >= 1 && month <= 12) && 
			(year >= 1900 && year <= 2100))
			return new Date(day, month, year);
		else
			return (Date) null;
	}
	
	public static Adress parseAdressFromString(String adr) {
		
		String drzava;
		String grad;
		String ulica;
		String broj;
		
		String[] split = adr.split(", ", 4);
		
		drzava = split[0];
		grad = split[1];
		ulica = split[2];
		broj = split[3];
		
		if(drzava.matches(".*\\d.*") ||
			grad.matches(".*\\d.*") ||
			ulica.matches(".*\\d.*") ||
			!broj.matches(".*\\d.*"))
			return (Adress) null;
		else
			return new Adress(ulica, broj, grad, drzava);
	}
	
	public static IndexNum parseIndexFromString(String index) {
		
		String department;
		int indxnum;
		int year;
		
		String[] split = index.split("-", 3);
		
		department = split[0].toUpperCase();
		indxnum = Integer.parseInt(split[1]);
		year = Integer.parseInt(split[2]);
		
		if(!department.matches(".*\\d.*") &&
			indxnum >= 0 &&
			(year >= 1900 && year <= 2100))
			return new IndexNum(department, indxnum, year);
		else
			return (IndexNum) null;
	}
	
	public static Semester parseSemesterFromString(String sem) {
		
		
		switch(sem) {
		case "zimski":
			return Semester.z;
		case "letnji":
			return Semester.l;
		case "winter":
			return Semester.z;
		case "summer":
			return Semester.l;
		default:
			return (Semester) null;
		}
		
	}
	
	public static Status parseStatusFromString(String stat) {
		
		switch(stat) {
		case "Budet":
			return Status.B;
		case "Samofinansiranje":
			return Status.S;
		case "Co-financing":
			return Status.S;
		case "Scholarship":
			return Status.B;
		default:
			return (Status) null;
		}
		
	}
	
	public static Title parseTitleFromString(String title) {
		
		switch(title) {
		case "Vanredni profesor":
			return Title.vanredni_profesor;
		case "Redovni profesor":
			return Title.redovni_profesor;
		case "Akademik":
			return Title.akademik;
		case "Academic":
			return Title.akademik;
		case "Professor":
			return Title.redovni_profesor;
		case "Associate professor":
			return Title.vanredni_profesor;
		default:
			return (Title) null;
		}
	}
	
	public static String parseEmailFromString(String mail) {
		
		if(mail.contains("@")) return mail;
		else return (String) null;
		
	}
	
}
