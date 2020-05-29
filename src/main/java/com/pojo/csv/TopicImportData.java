package com.pojo.csv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicImportData {

	private String topicNumber;
	private String topicName;
	private String parentTopicNumber;
}
