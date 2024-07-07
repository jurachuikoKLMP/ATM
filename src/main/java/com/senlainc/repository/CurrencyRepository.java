package com.senlainc.repository;

import com.senlainc.entity.Card;
import com.senlainc.entity.Currency;
import com.senlainc.service.FileService;

import java.util.List;
import java.util.Map;

public interface CurrencyRepository {
    List<Currency> findAll();
    Currency save(Currency entity);
    Currency findById(Long id);
    void deleteById(Long id);
    Map<Long, Currency> getStorage();
}
