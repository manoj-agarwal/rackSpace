package com.rackspace.fileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppDataCenter {

	static BlockingQueue<List> dataQueue = new ArrayBlockingQueue<List>(2);
	static List<ReportStatistics> report = new ArrayList<ReportStatistics>(5);

	public static BlockingQueue<List> getDataQueue() {
		return dataQueue;
	}

	public static void setDataQueue(BlockingQueue<List> dataQueue) {
		dataQueue = dataQueue;
	}

	public static void addElementInDataQueue(List<String> item) {

		try {
			dataQueue.put(item);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<ReportStatistics> getReport() {
		return report;
	}

	public static void setReport(List<ReportStatistics> report) {
		report = report;
	}

	public static void addReportStatistic(ReportStatistics reportStatistic) {

		report.add(reportStatistic);
	}

}
