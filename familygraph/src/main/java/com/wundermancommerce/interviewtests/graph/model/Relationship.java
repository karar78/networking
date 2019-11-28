package com.wundermancommerce.interviewtests.graph.model;

import com.opencsv.bean.CsvBindByPosition;

public class Relationship {

	@CsvBindByPosition(position = 0)
	private String firstEmail;

	@CsvBindByPosition(position = 1)
	private String relation;

	@CsvBindByPosition(position = 2)
	private String secondEmail;

	public Relationship() {
		super();
	}

	public Relationship(String firstEmail, String secondEmail, String relation) {
		super();
		this.firstEmail = firstEmail;
		this.secondEmail = secondEmail;
		this.relation = relation;
	}

	public String getFirstEmail() {
		return firstEmail;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public String getRelation() {
		return relation;
	}

	public final class RelationshipType {
		public static final double FAMILY = 10;
		public static final double FRIEND = 5;
	}

	@Override
	public String toString() {
		return "Relationship [firstEmail=" + firstEmail + ", relation=" + relation + ", secondEmail=" + secondEmail
				+ "]";
	}

	public void setFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

}
