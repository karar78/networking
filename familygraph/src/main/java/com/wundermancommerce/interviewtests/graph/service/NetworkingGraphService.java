package com.wundermancommerce.interviewtests.graph.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;

public class NetworkingGraphService extends Networking {

	private List<Person> people;
	private List<Relationship> relationships;

	private WeightedGraph<Person, DefaultWeightedEdge> families = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	private Set<Person> membersList = new HashSet<Person>();
	Set<Person> extendedFamily = new HashSet<Person>();

	public NetworkingGraphService(List<Person> people, List<Relationship> relationships) {
		super();
		this.people = people;
		this.relationships = relationships;
	}

	public NetworkingGraphService() {
		super();
	}

	public void createWholeFamily() {
		for (Person person : this.getPeople()) {
			try {
			this.families.addVertex(person);
			mappedPeople.put(person.getEmail(), person);
			}
			catch(Exception e) {}
		}

		for (Relationship relationship : this.getRelationships()) {
			// Below code, based on CSV data, we get two Person objects from mappedPeople
			// Map and create their edge with a weighted value of 10 if it is family or 5 if
			// it is friend
			try {
			this.families.setEdgeWeight(
					this.families.addEdge(mappedPeople.get(relationship.getFirstEmail()),
							mappedPeople.get(relationship.getSecondEmail())),
					relationship.getRelation().equalsIgnoreCase("family") ? Relationship.RelationshipType.FAMILY
							: Relationship.RelationshipType.FRIEND);
			}
			catch(Exception e) {}
		}

	}

	public Set<Person> analysePersonInFamily(Person person) {
		Set<DefaultWeightedEdge> edges=null;
		extendedFamily.add(person);
		if (membersList.contains(person))
			return extendedFamily;
		try {
		edges = families.edgesOf(person);
		}
		
		catch(IllegalArgumentException e) {}
		membersList.add(person);
		for (DefaultWeightedEdge edge : edges) {
			Person edgeSource = families.getEdgeSource(edge);
			Person edgeTarget = families.getEdgeTarget(edge);
			addFamilyMembers(families.getEdgeWeight(edge), edgeSource, edgeTarget);

		}
		return extendedFamily;
	}

	private void addFamilyMembers(double relationshipType, Person edgeSource, Person edgeTarget) {
		if (relationshipType == Relationship.RelationshipType.FAMILY) { // Type of relationship is family, then add in								// the list
			extendedFamily.add(edgeSource);
			extendedFamily.add(edgeTarget);

			analysePersonInFamily(edgeSource);
			analysePersonInFamily(edgeTarget);
		}
	}
	
	public Set<Person> getLinkedPeople(Person person){
		Set<Person> personDirectNetwork =new HashSet<Person>();
		Set<DefaultWeightedEdge> edges =  families.edgesOf(person);
		for (DefaultWeightedEdge edge : edges) {
			personDirectNetwork.add(families.getEdgeSource(edge));
			personDirectNetwork.add(families.getEdgeTarget(edge));
		}
		return personDirectNetwork;
	}
	public void resetCurrentPersonFamily() { //This is important to reset family connection in order to analyse another person's family
		extendedFamily.clear();
	}

	public WeightedGraph<Person, DefaultWeightedEdge> getFamilies() {
		return families;
	}

	public List<Person> getPeople() {
		return people;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

}
