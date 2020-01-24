package com.rackspace.fileProcessor;

import java.io.InputStream;
import java.util.List;

public class TokenBasedInputStreamSplitter implements InputStreamSplitter {

    private List<String> tokens;
    public TokenBasedInputStreamSplitter(List<String> tokens){
        this.tokens = tokens;
    }
    @Override
    public void splitStream(InputStream input) {

    }
	@Override
	public void splitFile(String file) {
		// TODO Auto-generated method stub
		
	}
}
