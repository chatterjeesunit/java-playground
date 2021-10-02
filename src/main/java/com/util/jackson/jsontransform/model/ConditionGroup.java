package com.util.jackson.jsontransform.model;

import java.util.List;

public class ConditionGroup {

	private List<Condition> conditions;
	private JoinOperator joinOperator;
	/**
	 * 
	 */
	public ConditionGroup() {
	}
	/**
	 * @param conditions
	 * @param joinOperator
	 */
	public ConditionGroup(List<Condition> conditions, JoinOperator joinOperator) {
		super();
		this.conditions = conditions;
		this.joinOperator = joinOperator;
	}
	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions() {
		return conditions;
	}
	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConditionGroup [conditions=" + conditions + ", joinOperator=" + joinOperator + "]";
	}

	
	
}
