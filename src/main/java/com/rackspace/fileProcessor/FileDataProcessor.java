package com.rackspace.fileProcessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

 class FileDataProcessor extends AbstractFileOperation{


	public FileDataProcessor(){
		
	}
	

	public void readWordCountForChunk(){
		
		AbstractFileOperation.FileOperation.getDataQueue().poll().stream().forEach(s -> System.out.println(s) );

       
	
	}
	
	public void saveWordsWithOccurance(){
		
	}
	
	public void printSummaryForChunk()
	{
		
	}
	
}


