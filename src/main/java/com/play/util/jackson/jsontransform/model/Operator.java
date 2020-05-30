package com.play.util.jackson.jsontransform.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "operator")
public enum Operator {

	EQ ("="),
	NE ("<>"),
	LIKE ("like"),
	GT (">"),
	GTE (">="),
	LT ("<"),
	LTE ("<="),
	NOT_IN("not in"),
	IN ("in");
	
	private String dbOperator;

	/**
	 * @param operator
	 */
	private Operator(String operator) {
		this.dbOperator = operator;
	}

	/**
	 * @return the dbOperator
	 */
	public String getDbOperator() {
		return dbOperator;
	}

	/**
	 * @param dbOperator the dbOperator to set
	 */
	public void setDbOperator(String dbOperator) {
		this.dbOperator = dbOperator;
	}

	
	
	
}
