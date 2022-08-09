package model_sistema;

import java.io.Serializable;
import java.util.Set;

public class CourseChair implements Serializable {
	private static final long serialVersionUID = -6920354143951940856L;
	
	private int code;
	private String name;
	private Professor head = null;
	private Set<Professor> profs;
	
	public CourseChair(int code, String name, Professor head, Set<Professor> profs) {
		if(code >= 0) this.code = code;
		else throw new RuntimeException("Invalid chair code");
		
		if(name.length() >= 0) this.name = name;
		else throw new RuntimeException("invalid name length");
		
		this.head = head;
		this.profs = profs;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		if(code >= 0) this.code = code;
		else throw new RuntimeException("Invalid chair code");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() >= 0) this.name = name;
		else throw new RuntimeException("invalid name length");
	}

	public Professor getHead() {
		return head;
	}

	public void setHead(Professor head) {
		this.head = head;
	}

	public Set<Professor> getProfs() {
		return profs;
	}

	public void addProfs(Professor prof) {
		profs.add(prof);
	}
	
	public void removeProfs(Professor prof) {
		for(Professor p : profs)
			if(p.getIDnum() == prof.getIDnum()) {
				profs.remove(p);
				break;
			}
	}

	@Override
	public String toString() {
		return "Sifra: " + code + "\n" +
			   "Naziv: " + name + "\n" +
			   "Sef: " + head + "\n" +
			   "Profesori: " + profs;
	}
	
	
	
}
