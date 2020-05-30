package com.play.util.jackson.jsontransform.model;

import java.util.List;

/**
 * @author sunitc
 */
public class SearchCriteria {
	
	private List<ConditionGroup> conditionGroups;
	
	private JoinOperator joinOperator;
	
	private SortOrder sortOrder;
	
	private int pageNumber;
	
	private int pageSize;

	/**
	 * 
	 */
	public SearchCriteria() {
		super();
	}

	/**
	 * @param conditionGroups
	 * @param joinOperator
	 * @param sortOrder
	 * @param pageNumber
	 * @param pageSize
	 */
	public SearchCriteria(List<ConditionGroup> conditionGroups, JoinOperator joinOperator, SortOrder sortOrder,
			int pageNumber, int pageSize) {
		super();
		this.conditionGroups = conditionGroups;
		this.joinOperator = joinOperator;
		this.sortOrder = sortOrder;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	/**
	 * @return the conditionGroups
	 */
	public List<ConditionGroup> getConditionGroups() {
		return conditionGroups;
	}

	/**
	 * @param conditionGroups the conditionGroups to set
	 */
	public void setConditionGroups(List<ConditionGroup> conditionGroups) {
		this.conditionGroups = conditionGroups;
	}

	/**
	 * @return the joinOperator
	 */
	public JoinOperator getJoinOperator() {
		return joinOperator;
	}

	/**
	 * @param joinOperator the joinOperator to set
	 */
	public void setJoinOperator(JoinOperator joinOperator) {
		this.joinOperator = joinOperator;
	}

	/**
	 * @return the sortOrder
	 */
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchCriteria [conditionGroups=" + conditionGroups + ", joinOperator=" + joinOperator + ", sortOrder="
				+ sortOrder + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + "]";
	}

	
	
}