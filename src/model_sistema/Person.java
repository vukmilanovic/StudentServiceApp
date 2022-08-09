package model_sistema;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = -6793437291105644731L;
	
	private String name;
	private String surname;
	private Date dob;
	private Adress adr;
	private String tel;
	private String mail;
	
	public Person(String name, String surname, Date dob, Adress adr, String tel, String mail) {
		if(name.length() >= 0 && name.length() <= 25) {
			this.name = name;
		}
		else throw new RuntimeException("Invalid name length");
		
		if(surname.length() >= 0 && surname.length() <= 25) {
			this.surname = surname;
		}
		else throw new RuntimeException("Invalid surname length");
		
		this.dob = dob; // just use new Date()
		
		this.adr = adr; // same as Date
		
		if(tel.length() >= 0 && tel.length() <= 20) {
			this.tel = tel;
		}
		else throw new RuntimeException("Invalid telephone number");
		
		if(mail.length() >= 0 && mail.length() <= 50) {
			this.mail= mail;
		}
		else throw new RuntimeException("Invalid email length");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() >= 0 && name.length() <= 25) {
			this.name = name;
		}
		else throw new RuntimeException("Invalid name length");
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if(surname.length() >= 0 && surname.length() <= 25) {
			this.surname = surname;
		}
		else throw new RuntimeException("Invalid surname length");
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Adress getAdr() {
		return adr;
	}

	public void setAdr(Adress adr) {
		this.adr = adr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		if(tel.length() >= 0 && tel.length() <= 20) {
			this.tel = tel;
		}
		else throw new RuntimeException("Invalid telephone number");
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		if(mail.length() >= 0 && mail.length() <= 50) {
			this.mail= mail;
		}
		else throw new RuntimeException("Invalid email length");
	}

	@Override
	public String toString() {
		return "Ime: " + name + "\n" +
			   "Prezime: " + surname + "\n" +
			   dob + "\n" +
			   adr + "\n" +
			   "Telefon: " + tel + "\n" +
			   "E-Mail: " + mail;
				
	}
	
	
	
}
