package com.rackspace.fileProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemoryBasedInputStreamSplitter implements InputStreamSplitter {

    private int chunkSize;
    private File file;
    private int FILE_SIZE;
    
    MemoryBasedInputStreamSplitter(int chunkSize){
        this.chunkSize = chunkSize;
    }

    @Override
    public void splitStream(InputStream input) {
        List<String>[] output = new ArrayList[5];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input, chunkSize);
        byte[] temporary;
         int totalBytesRead = 0;
         int count =0;
         
         try {
          
          while ( totalBytesRead < FILE_SIZE )
          {
           int bytesRemaining = FILE_SIZE-totalBytesRead;
           if ( bytesRemaining < chunkSize ) // Remaining Data Part is Smaller Than CHUNK_SIZE
                      // CHUNK_SIZE is assigned to remain volume
           {
        	   chunkSize = bytesRemaining;
            System.out.println("CHUNK_SIZE: "+chunkSize);
           }
           output[count] = new ArrayList<>();
           temporary = new byte[chunkSize]; //Temporary Byte Array
           int bytesRead = input.read(temporary, 0, chunkSize);
           output[count].add(new String(temporary, 0, bytesRead));
        	AppDataCenter.addElementInDataQueue(output[count]);
        	count++;

           if ( bytesRead > 0) // If bytes read is not empty
           {
            totalBytesRead += bytesRead;
           }
           
          }
        
         }catch (IOException e) {
             e.printStackTrace();
         }
        
/*        try {
            byte[] chunk = new byte[chunkSize];
            data = bufferedInputStream.read(chunk, 0, chunkSize);
            while(data != -1){
            	output.add(new String(chunk, 0, data));
            	AppDataCenter.addElementInDataQueue(output);
                output.clear();
               // output.add(new ByteArrayInputStream(chunk));
                offset += chunkSize;
                chunk = new byte[chunkSize];
                data = bufferedInputStream.read(chunk, offset, chunkSize);
            } */
/*        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }
    

    
    
    public  void splitFile(String file){
       	
    	List<String> fileChunk = new ArrayList<String>();

    	File f = new File(file);
    	this.file =f;
    	
    	this.FILE_SIZE = (int)f.length();
    	
	    try {
			FileInputStream fis = new FileInputStream( file );
			splitStream(fis);

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
