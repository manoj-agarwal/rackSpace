package com.rackspace.fileProcessor;

import java.io.InputStream;


public interface InputStreamSplitter {

    void splitStream(InputStream input);

    void splitFile(String file);
}
