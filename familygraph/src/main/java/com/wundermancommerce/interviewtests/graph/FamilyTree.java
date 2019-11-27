package com.wundermancommerce.interviewtests.graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;
import com.wundermancommerce.interviewtests.graph.service.NetworkingGraphService;

public class FamilyTree {

	public static final Logger LOGGER = LoggerFactory.getLogger(FamilyTree.class);
	List<Person> person = null;
	List<Relationship> relationships = null;
	private NetworkingGraphService service;

	public FamilyTree() {
		super();
	}

	public void loadCSVData(String peopleFilePath, String relationshipFilePath) {
		loadPeopleData(peopleFilePath);
		loadRelationshipData(relationshipFilePath);
	}

	private List<Person> loadPeopleData(String path) {
		path = path.replace("%20", " ");

		try {
			person = new CsvToBeanBuilder<Person>(new FileReader(path)).withType(Person.class)
					.withVerifier(new BeanVerifierImpl<Person>()).build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		return person;
	}

	private List<Relationship> loadRelationshipData(String path) {
		path = path.replace("%20", " ");

		try {
			relationships = new CsvToBeanBuilder<Relationship>(new FileReader(path)).withType(Relationship.class)
					.withVerifier(new BeanVerifierImpl<Relationship>()).build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		return relationships;
	}

	public void createFamily() {
		createFamily(this.getPerson(), this.getRelationships());
	}

	public void createFamily(List<Person> person, List<Relationship> relationships) {
		service = new NetworkingGraphService(person, relationships);
		service.createWholeFamily();
	}

	public Set<Person> analysePersonInFamily(Person person) {
		this.service.resetCurrentPersonFamily();
		return this.service.analysePersonInFamily(person);
	}

	public Set<Person> getLinkedPeople(Person person) {
		Set<Person> linkedPeople = service.getLinkedPeople(person);
		linkedPeople.remove(person); // In normal scenarios, this set also contains this person, but we need only his
										// linked people. So we remove him. If a person has zero linked people, the set
										// would be empty. In that case, this line will have no impact

		return linkedPeople;
	}

	public Person getPerson(String email) {
		return this.service.getPerson(email);
	}

	public List<Person> getPerson() {
		return person;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}
}
