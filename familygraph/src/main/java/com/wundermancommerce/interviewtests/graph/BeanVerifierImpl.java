package com.wundermancommerce.interviewtests.graph;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.wundermancommerce.interviewtests.graph.model.Person;
import com.wundermancommerce.interviewtests.graph.model.Relationship;

public class BeanVerifierImpl<T> implements BeanVerifier<T> {

	@Override
	public boolean verifyBean(T bean) throws CsvConstraintViolationException {
		
		if(bean instanceof Relationship) {
			Relationship relationshipBean = (Relationship)bean;
			if(relationshipBean.getFirstEmail().trim().equals(""))
				return false;
		}
		else if(bean instanceof Person) {
			Person person = (Person)bean;
			if(person.getName().trim().equals(""))
				return false;
		}
		return true;
	}

	

}
