package com.senlainc.service.impl;

import com.senlainc.service.FileService;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

public class FileServiceImpl implements FileService {
    @Override
    @SneakyThrows
    public String readFromFile(String filePath){
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuffer json = new StringBuffer();
        String line;

        while ((line = bufferedReader.readLine()) != null)
            json.append(line);

        return json.toString();
    }

    @Override
    @SneakyThrows
    public void rewriteFile(String filePath, String data){
        FileWriter writer = new FileWriter(filePath);

        writer.write(data);

        writer.close();
    }
}
