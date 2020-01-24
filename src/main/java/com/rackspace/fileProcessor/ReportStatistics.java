package com.rackspace.fileProcessor;

import java.util.HashMap;
import java.util.Map;

class ReportStatistics {

    private long wordCount;
    private Map<String, Long> wordOccuranceCountMap = new HashMap<>();

    Map<String, Long> getWordCountMap() {
        return wordOccuranceCountMap;
    }

    void setWordCountMap(Map<String, Long> wordCountMap) {
        this.wordOccuranceCountMap = wordCountMap;
    }

    long getWordCount() {
        return wordCount;
    }

    void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }
}
