package com.rackspace.fileProcessor;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;


public class FileSplitter extends AbstractFileOperation {
	
	

	private boolean isMemoryBased = false;
	private boolean isTokenBased = false;
	private String file;
	private List<Stream> chunks = new ArrayList<Stream>();

	
	
	public FileSplitter(String file, boolean isMemoryBased, boolean isTokenBased){
		this.isMemoryBased=isMemoryBased;
		this.isTokenBased=isTokenBased;
		this.file=file;

	}



	public void split(){
		
		if(isMemoryBased && !isTokenBased)
			splitByBufferSize();
		else if(isTokenBased && !isMemoryBased)
			splitUsingToken();
		else
			System.out.println("Select any one kind of splitting approach");
		
	}
	
	
	private void splitByBufferSize()
	{
		  try {
			  System.out.println("calling buffersize based split");
			  File sourceFile = new File ( file );
			   InputStream inStream = null;
			   int totalBytesRead = 0;
			   int FILE_SIZE = (int)sourceFile.length();
			   int CHUNK_SIZE =1048576;
			   byte[] temporary = new byte[CHUNK_SIZE]; //Temporary Byte Array
			   
			try {
			    inStream = new BufferedInputStream ( new FileInputStream( sourceFile ));
			    FileInputStream fis = new FileInputStream( sourceFile );
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    List<String> datachunk = new ArrayList<String>();
			    BlockingQueue dataQueue = new ArrayBlockingQueue<List>(4);
			    
	            int readCount = 0;
	            while((readCount = fis.read(temporary)) > 0){
				     datachunk.add(new String(temporary, 0, readCount-1));
				     
				     AbstractFileOperation.FileOperation.addElementInDataQueue(datachunk);

	            	
	            }

			   }
			   finally {
			    inStream.close();
			   }
			  }
			  catch (FileNotFoundException ex)
			  {
			   ex.printStackTrace();
			  }
			  catch (IOException ex)
			  {
			   ex.printStackTrace();
			  }
			 }
		

	
	private void splitUsingToken() 
	
	{
		
	}
	
	public static void main(String[] args){
		
		System.out.println("calling main method");
		

		FileSplitter splitter = new FileSplitter("src//main//java//resources//2600-0.txt",true,false);
		System.out.println("calling split method");
		splitter.split();
		
		FileDataProcessor dataprocess = new FileDataProcessor();
		dataprocess.readWordCountForChunk();
	}
	
	
}
