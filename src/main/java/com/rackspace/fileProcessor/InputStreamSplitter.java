package com.rackspace.fileProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface InputStreamSplitter {
	
    void splitStream(InputStream input);
    void splitFile(String file);

    

}
