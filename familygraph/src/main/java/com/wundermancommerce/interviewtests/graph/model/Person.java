package com.wundermancommerce.interviewtests.graph.model;

import java.util.Objects;

import com.opencsv.bean.CsvBindByPosition;

public class Person {
	public Person() {
		super();
		
	}
	@CsvBindByPosition(position = 0)
	private String name;
	
	@CsvBindByPosition(position = 1)
	private String email;
	
	@CsvBindByPosition(position = 2)
	private int age;
	
	
	
	public Person(String name, String email, int age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", email=" + email + ", age=" + age + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
