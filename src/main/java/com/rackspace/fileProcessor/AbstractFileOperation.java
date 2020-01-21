package com.rackspace.fileProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

  public class AbstractFileOperation {
	
	
	public AbstractFileOperation(){

	}
	
	   static class FileOperation {
			
			
			private static BlockingQueue<List> dataQueue = new ArrayBlockingQueue<List>(4);

			public static BlockingQueue<List> getDataQueue() {
				return FileOperation.dataQueue;
			}

			public static void setDataQueue(BlockingQueue<List> dataQueue) {
				FileOperation.dataQueue = dataQueue;
			}

			public static void addElementInDataQueue(List<String> item) {
				
				FileOperation.dataQueue.add(item);
			}
	   }
	   
  }
  
