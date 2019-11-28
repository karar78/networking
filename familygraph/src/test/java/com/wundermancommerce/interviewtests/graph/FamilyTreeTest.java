package com.wundermancommerce.interviewtests.graph;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;

import com.wundermancommerce.interviewtests.graph.model.Person;

public class FamilyTreeTest {

	static FamilyTree familyTree;

	@BeforeClass
	public static void loadData() {

		ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
		File personFile = new File(classLoader.getResource("people.csv").getFile());
		File relationshipFile = new File(classLoader.getResource("relationships.csv").getFile());

		familyTree = new FamilyTree();
		familyTree.loadCSVData(personFile.getAbsolutePath(), relationshipFile.getAbsolutePath());

		familyTree.createFamily();
	}

	@org.junit.Test
	public void testDataLoadedFromCSV() { // Exercise 2
		assertEquals(12, familyTree.getPerson().size());
		assertEquals(16, familyTree.getRelationships().size());
	}

	@org.junit.Test
	public void testRelationshipAmongPeople() { // Exercise 3
		Map<String, Integer> people = new HashMap<String, Integer>();

		String bob = "bob@bob.com";
		String jenny = "jenny@toys.com";
		String nigel = "nigel@marketing.com";
		String alan = "alan@lonely.org";

		people.put(bob, 4);
		people.put(jenny, 3);
		people.put(nigel, 2);
		people.put(alan, 0);

		String toTest = null;

		toTest = bob;
		assertEquals(people.get(toTest).intValue(), familyTree.getLinkedPeople(familyTree.getPerson(toTest)).size());

		toTest = jenny;
		assertEquals(people.get(toTest).intValue(), familyTree.getLinkedPeople(familyTree.getPerson(toTest)).size());

		toTest = nigel;
		assertEquals(people.get(toTest).intValue(), familyTree.getLinkedPeople(familyTree.getPerson(toTest)).size());

		toTest = alan;
		assertEquals(people.get(toTest).intValue(), familyTree.getLinkedPeople(familyTree.getPerson(toTest)).size());
	}

	@org.junit.Test
	public void testPersonFamilyData() { // Exercise 4

		Person jenny = familyTree.getPerson("jenny@toys.com");
		Set<Person> familyMembers = familyTree.analysePersonInFamily(jenny);
		assertEquals(4, familyMembers.size());

		Person bob = familyTree.getPerson("bob@bob.com");
		familyMembers = familyTree.analysePersonInFamily(bob);
		assertEquals(4, familyMembers.size());

	}

}
