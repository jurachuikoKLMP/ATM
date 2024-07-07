package com.senlainc.service.impl;

import com.senlainc.service.FileService;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {
    @Override
    @SneakyThrows
    public List<String> readFromFile(String filePath){
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<String> lines = new ArrayList<>();
        String line;

        int k = 0;

        while((line = br.readLine()) != null) {
            if(k == 0){
                ++k;
                continue;
            }

            lines.add(line);
        }

        return lines;
    }

    @Override
    @SneakyThrows
    public void rewriteFile(String filePath, String data){
        FileWriter writer = new FileWriter(filePath);

        writer.write(data);

        writer.close();
    }
}
