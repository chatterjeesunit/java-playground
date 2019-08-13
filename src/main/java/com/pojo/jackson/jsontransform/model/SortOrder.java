package com.pojo.jackson.jsontransform.model;

public class SortOrder {

	private String fieldName;
	private SortBy sortBy;
	/**
	 * 
	 */
	public SortOrder() {
		super();
	}

	public SortOrder(String fieldName, SortBy sortBy) {
		this.fieldName = fieldName;
		this.sortBy = sortBy;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the sortBy
	 */
	public SortBy getSortBy() {
		return sortBy;
	}
	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SortOrder [fieldName=" + fieldName + ", sortBy=" + sortBy + "]";
	}
	
	
	
	
}
