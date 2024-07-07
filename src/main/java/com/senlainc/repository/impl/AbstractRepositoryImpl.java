package com.senlainc.repository.impl;

import com.senlainc.entity.Entity;
import com.senlainc.exception.EntityNotFoundException;
import com.senlainc.repository.AbstractRepository;
import com.senlainc.service.FileService;
import com.senlainc.service.impl.FileServiceImpl;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.*;

public abstract class AbstractRepositoryImpl<T extends Entity> implements AbstractRepository<T, Long> {
    @Getter
    protected Map<Long, T> storage;
    protected String filePath;
    protected FileService fileService;

    public AbstractRepositoryImpl(String filePath){
        this.filePath = filePath;
        this.fileService = new FileServiceImpl();
        this.storage = new HashMap<>();//todo deserialize
    }

    @Override
    public List<T> findAll() {
        if(storage == null)
            return null;

        return new ArrayList<T>(storage.values());
    }

    @Override
    @SneakyThrows
    public T findById(Long id) {
        return Optional.ofNullable(storage.get(id))
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public T save(T entity) {
        if(!storage.containsValue(entity)) {
            Long id = generateId();
            entity.setId(id);
        }
        storage.put(entity.getId(), entity);

        return entity;
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        if(!storage.containsKey(id))
            throw new EntityNotFoundException(id);

        storage.remove(id);
    }

    @SneakyThrows
    protected Long generateId() {
        Long maxId = storage.keySet().stream().max(Long::compare).orElse(0L);
        
        return maxId+1;
    }

}
