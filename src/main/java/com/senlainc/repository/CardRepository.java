package com.senlainc.repository;

import com.senlainc.entity.Card;
import com.senlainc.service.FileService;

import java.util.List;
import java.util.Map;

public interface CardRepository {
    List<Card> findAll();
    Card findById(Long id);
    Card save(Card entity);
    void deleteById(Long id);
    Map<Long, Card> getStorage();
}
