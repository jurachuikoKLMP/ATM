package com.senlainc.service;

import java.util.Map;

public interface FileService {
    String readFromFile(String filePath);
    void rewriteFile(String filePath, String data);
}
