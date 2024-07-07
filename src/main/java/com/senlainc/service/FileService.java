package com.senlainc.service;

import java.util.List;

public interface FileService {
    List<String> readFromFile(String filePath);
    void rewriteFile(String filePath, String data);
}
