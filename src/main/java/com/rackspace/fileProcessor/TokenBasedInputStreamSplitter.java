package com.rackspace.fileProcessor;

import java.io.InputStream;
import java.util.List;

public class TokenBasedInputStreamSplitter implements InputStreamSplitter {

    private List<String> tokens;
    public TokenBasedInputStreamSplitter(List<String> tokens){
        this.tokens = tokens;
    }
    @Override
    public List<InputStream> splitStream(InputStream input) {
        return null;
    }
}
