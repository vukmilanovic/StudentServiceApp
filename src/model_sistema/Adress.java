package model_sistema;

import java.io.Serializable;

public class Adress implements Serializable {
	
	private static final long serialVersionUID = -8402877873611971890L;
	
	private String street;
	private String num; // to support street numbers like 72A, 35C/2 or apt numbers 13/3
	private String city;
	private String country;
	
	public Adress(String street, String num, String city, String country) {
		if(street.length() <= 100 && street.length() >= 0) {
			this.street = street;
		}
		else {
			throw new RuntimeException("Invalid street name length");
		}
		
		if(num.length() >= 0 && num.length() <= 1000) {
			this.num = num;
		}
		else {
			throw new RuntimeException("Invalid street number length");
		}
		
		if(city.length() >= 0 && city.length() <= 100) {
			this.city = city;
		}
		else {
			throw new RuntimeException("Invalid city name length");
		}
		
		if(country.length() >= 0 && country.length() <= 100) {
			this.country= country;
		}
		else {
			throw new RuntimeException("Invalid country name length");
		}
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if(street.length() <= 100 && street.length() >= 0) {
			this.street = street;
		}
		else {
			throw new RuntimeException("Invalid street name length");
		}
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		if(num.length() >= 0 && num.length() <= 1000) {
			this.num = num;
		}
		else {
			throw new RuntimeException("Invalid street number length");
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if(city.length() >= 0 && city.length() <= 100) {
			this.city = city;
		}
		else {
			throw new RuntimeException("Invalid city name length");
		}
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		if(country.length() >= 0 && country.length() <= 100) {
			this.country= country;
		}
		else {
			throw new RuntimeException("Invalid country name length");
		}
	}

	@Override
	public String toString() {
		return country + ", " + city + ", " + street + ", " + num;
	}
	
	
	
}
