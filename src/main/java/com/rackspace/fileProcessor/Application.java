package com.rackspace.fileProcessor;

public class Application {
    public static void main(String[] args) {

        InputStreamSplitter splitter = new MemoryBasedInputStreamSplitter(1048576);
        FileDataProcessor dataprocess = new FileDataProcessor();

/*		// Single threaded Manner

		splitter.splitFile("src//main//java//resources//2600-0.txt");
      
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
     	dataprocess.fileDataProcessor();   
     	
     	// End Single Threaded Manner
*/
        //Multi Threaded Manner
        Thread tSplitter = new Thread(() -> splitter.splitFile("src//main//java//resources//2600-0.txt"), "Splitter");

        Thread tProcessor = new Thread(dataprocess::fileDataProcessor, "processor");

        tSplitter.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tProcessor.start();
        try {
            tProcessor.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //End of Multithreaded Manner
        dataprocess.printSummary();
    }
}