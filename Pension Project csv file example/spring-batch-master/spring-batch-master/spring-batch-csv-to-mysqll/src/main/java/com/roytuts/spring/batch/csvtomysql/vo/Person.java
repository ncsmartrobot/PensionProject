package com.roytuts.spring.batch.csvtomysql.vo;

import javax.xml.bind.annotation.XmlAttribute;

public class Person {

	private long id;
	private String firstName;
	private String lastName;

	@XmlAttribute(name = "id")
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
