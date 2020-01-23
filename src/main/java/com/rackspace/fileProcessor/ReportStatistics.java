package com.rackspace.fileProcessor;

import java.util.HashMap;
import java.util.Map;

public class ReportStatistics {
	
	private long wordCount;
	private  Map<String,Long> wordOccuranceCountMap = new HashMap<String,Long>();
	
	
	
	public  Map<String, Long> getWordCountMap() {
		return wordOccuranceCountMap;
	}
	
	public  void setWordCountMap(Map<String, Long> wordCountMap) {
		this.wordOccuranceCountMap = wordCountMap;
	}

	public long getWordCount() {
		return wordCount;
	}

	public void setWordCount(long wordCount) {
		this.wordCount = wordCount;
	}
	
	



}
