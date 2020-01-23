package com.rackspace.fileProcessor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

 class FileDataProcessor {


	public FileDataProcessor(){
		
	}
	
	public void fileDataProcessor() {
		
		BlockingQueue queue =  AppDataCenter.getDataQueue();
		long wordCount =0;
		Map<String,Long> wordOccuranceCountMap = new HashMap<String,Long>();

		
		while(!queue.isEmpty()) {
		List<String> data = new ArrayList<String>();
		try {
			data = (List<String>) queue.take();

			wordCount = streamWordCound(data);
			wordOccuranceCountMap = streamWordOccurance(data);
			
			ReportStatistics reportStatistics = new ReportStatistics();
			reportStatistics.setWordCount(wordCount);
			reportStatistics.setWordCountMap(wordOccuranceCountMap);
			
			AppDataCenter.addReportStatistic(reportStatistics);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}
	
	public Long streamWordCound(List<String> data){
		
		long wordCount=0;
	//	try{
        wordCount = data.stream().flatMap(line -> Arrays.stream(line.split(" "))).count();
	//	}catch(Exception e){};
        return wordCount;

		
	}
	
	 public Map<String,Long> streamWordOccurance(List<String> data){
		 
	 Map<String,Long> wordOccuranceCountMap = new HashMap<String,Long>();

		wordOccuranceCountMap = data.stream()
				.flatMap(i -> Arrays.stream(i.split(" ")))
				.map(i -> i.toLowerCase().toString())
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		
		 return wordOccuranceCountMap;
		 
	 }

    
     public void printSummay()
     {
    	 
    	 List<ReportStatistics> reports = AppDataCenter.getReport();
    	 int size = reports.size();
    	 long totalWordCount =0;
    	 Map<String,Long> totalWordOccuranceCountMap = new HashMap<String,Long>();

    	 
    	 for(int i=0;i <size;i++){
    		 
    		 ReportStatistics statstic = reports.get(i);
    		 long wordCount = statstic.getWordCount();
    		 List<Entry<String, Long>> list = new ArrayList();
    		 list = findGreatest(statstic.getWordCountMap(),5);
             
    		 System.out.println("======Print Summary wrt parts====");
    		 System.out.println("Word Count in Part"+ "[" +i +"] =" +  wordCount);
    		 System.out.println("List of words with occurance in Part"+ "[" +i +"] =" +  statstic.getWordCountMap());
    		 System.out.println(" Top 5 words having maximum occurance in Part"+ "[" +i +"] = " );
    		 list.forEach((Entry<String,Long> value) -> System.out.print(" Word : " + value.getKey() + " & Occuance : " + value.getValue() + "\n" ));

    		 System.out.println("\n");
    		 System.out.println("=====================================================");
    		 totalWordCount = totalWordCount+ wordCount;
    		 
    		 totalWordOccuranceCountMap= MergeTwoMap(totalWordOccuranceCountMap,statstic.getWordCountMap());

    	 }
    	 
    	 System.out.println("======Print consolidated Summary for whole input file====");
    	 System.out.println("Total Word Count = " +  totalWordCount);
		 System.out.println("Total List of words with occurance = " +  totalWordOccuranceCountMap);

		 List<Entry<String, Long>> list = new ArrayList();
		 list = findGreatest(totalWordOccuranceCountMap,5);
		 System.out.println(" Top 5 words having maximum occurance are = " );
		 list.forEach((Entry<String,Long> value) -> System.out.print(" Word : " + value.getKey() + " & Occuance : " + value.getValue()));

		 System.out.println("\n");
		 System.out.println("=====================================================");
		 
		 
    	 
     }
     
     private HashMap<String,Long> MergeTwoMap(Map<String, Long> totalWordOccuranceCountMap, Map<String, Long> map){
    	 
    	 Set<String> keySet=map.keySet();

    	 for(String str: keySet){
    	     long value=(long) map.get(str);
    	     if(totalWordOccuranceCountMap.containsKey(str)){
    	    	 totalWordOccuranceCountMap.put(str, (long)totalWordOccuranceCountMap.get(str)+value);
    	     }
    	     else{
    	    	 totalWordOccuranceCountMap.put(str, value);
    	     }
    	 }
    	 return (HashMap<String, Long>) totalWordOccuranceCountMap;
     }
     
     
     
     private static <K, V extends Comparable<? super V>> List<Entry<K, V>> 
     findGreatest(Map<K, V> map, int n)
 {
     Comparator<? super Entry<K, V>> comparator = 
         new Comparator<Entry<K, V>>()
     {
         @Override
         public int compare(Entry<K, V> e0, Entry<K, V> e1)
         {
             V v0 = e0.getValue();
             V v1 = e1.getValue();
             return v0.compareTo(v1);
         }
     };
     PriorityQueue<Entry<K, V>> highest = 
         new PriorityQueue<Entry<K,V>>(n, comparator);
     for (Entry<K, V> entry : map.entrySet())
     {
         highest.offer(entry);
         while (highest.size() > n)
         {
             highest.poll();
         }
     }

     List<Entry<K, V>> result = new ArrayList<Map.Entry<K,V>>();
     while (highest.size() > 0)
     {
         result.add(highest.poll());
     }
     return result;
 }
     
	
}


