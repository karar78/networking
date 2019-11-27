package com.wundermancommerce.interviewtests.graph.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wundermancommerce.interviewtests.graph.model.Person;

public abstract class Networking {

	Map<String, Person> mappedPeople;
	
	public Networking() {
		super();
		mappedPeople = new HashMap<String, Person>();
	}

	public void addPerson(Person person) {
		mappedPeople.put(person.getEmail(), person);
	}

	public Person getPerson(String email) {
		return mappedPeople.get(email);
	}

	public abstract void createWholeFamily();

	public List<Person> getPeople() {
		return new ArrayList<Person>(mappedPeople.values());
	}

}
