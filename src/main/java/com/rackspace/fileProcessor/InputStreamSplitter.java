package com.rackspace.fileProcessor;

import java.io.InputStream;
import java.util.List;

public interface InputStreamSplitter {
    List<InputStream> splitStream(InputStream input);
}
