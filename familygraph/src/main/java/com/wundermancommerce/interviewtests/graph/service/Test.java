package com.wundermancommerce.interviewtests.graph.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;

public class Test {

	public static void main(String[] args) throws IllegalStateException, FileNotFoundException {
		 List<Person> person = new CsvToBeanBuilder<Person>( new FileReader("C:\\\\test\\\\people.csv")).withType(Person.class).build().parse();
		 person.forEach(s -> System.out.println(s));
		 
		 List<Relationship> relationship = new CsvToBeanBuilder<Relationship>( new FileReader("C:\\\\test\\\\relationships.csv")).withType(Relationship.class).build().parse();
		 relationship.forEach(s -> System.out.println(s));
	}

}
