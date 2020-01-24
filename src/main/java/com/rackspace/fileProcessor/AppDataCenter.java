package com.rackspace.fileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class AppDataCenter {

    private static BlockingQueue<List> dataQueue = new ArrayBlockingQueue<>(2);
    private static List<ReportStatistics> report = new ArrayList<>(5);

    static BlockingQueue<List> getDataQueue() {
        return dataQueue;
    }

    static void setDataQueue(BlockingQueue<List> thisDataQueue) {
        dataQueue = thisDataQueue;
    }

    static void addElementInDataQueue(List<String> item) {

        try {
            dataQueue.put(item);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static List<ReportStatistics> getReport() {
        return report;
    }

    static void setReport(List<ReportStatistics> thisReport) {
        report = thisReport;
    }

    static void addReportStatistic(ReportStatistics reportStatistic) {

        report.add(reportStatistic);
    }

}
