package com.senlainc.repository;

import com.senlainc.service.FileService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AbstractRepository <T, ID>{
    List<T> findAll();
    T findById(ID id);
    T save(T entity);
    void deleteById(ID id);
    Map<Long, T> getStorage();
}