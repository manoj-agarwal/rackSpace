package com.rackspace.fileProcessor;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MemoryBasedInputStreamSplitter implements InputStreamSplitter {

    private int chunkSize;
    public MemoryBasedInputStreamSplitter(int chunkSize){
        this.chunkSize = chunkSize;
    }

    @Override
    public List<InputStream> splitStream(InputStream input) {
        List<InputStream> output = new ArrayList<>();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(input, chunkSize);
        int offset = 0;

        int data;
        try {
            byte[] chunk = new byte[chunkSize];
            data = bufferedInputStream.read(chunk, offset, chunkSize);
            while(data != -1){
                output.add(new ByteArrayInputStream(chunk));
                offset += chunkSize;
                chunk = new byte[chunkSize];
                data = bufferedInputStream.read(chunk, offset, chunkSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
