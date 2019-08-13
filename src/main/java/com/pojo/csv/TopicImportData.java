package com.pojo.csv;

public class TopicImportData {

	private String topicNumber;
	private String topicName;
	private String parentTopicNumber;
	/**
	 * 
	 */
	public TopicImportData() {
		super();
	}
	/**
	 * @param topicNumber
	 * @param topicName
	 * @param parentTopicNumber
	 */
	public TopicImportData(String topicNumber, String topicName, String parentTopicNumber) {
		super();
		this.topicNumber = topicNumber;
		this.topicName = topicName;
		this.parentTopicNumber = parentTopicNumber;
	}
	/**
	 * @return the topicNumber
	 */
	public String getTopicNumber() {
		return topicNumber;
	}
	/**
	 * @param topicNumber the topicNumber to set
	 */
	public void setTopicNumber(String topicNumber) {
		this.topicNumber = topicNumber;
	}
	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * @return the parentTopicNumber
	 */
	public String getParentTopicNumber() {
		return parentTopicNumber;
	}
	/**
	 * @param parentTopicNumber the parentTopicNumber to set
	 */
	public void setParentTopicNumber(String parentTopicNumber) {
		this.parentTopicNumber = parentTopicNumber;
	}

	@Override
	public String toString() {
		return "TopicImportData{" +
				"topicNumber='" + topicNumber + '\'' +
				", topicName='" + topicName + '\'' +
				", parentTopicNumber='" + parentTopicNumber + '\'' +
				'}';
	}
}
