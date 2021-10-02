package com.util.jackson.jsontransform.model;

import java.util.List;

public class Condition {
	private String fieldName;
	private Operator operator;
	private String value;
	private List<String> multiValue;
	/**
	 * 
	 */
	public Condition() {
		super();
	}


	public Condition(String fieldName, Operator operator, String value) {
		super();
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
	}




	public Condition(String fieldName, Operator operator, String value, List<String> multipleValue) {
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
		this.multiValue = multipleValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getMultiValue() {
		return multiValue;
	}

	public void setMultiValue(List<String> multiValue) {
		this.multiValue = multiValue;
	}

	@Override
	public String toString() {
		return "Condition [fieldName=" + fieldName + ", operator=" + operator + ", value=" + value + ", multiValue="
				+ multiValue + "]";
	}

	
}
