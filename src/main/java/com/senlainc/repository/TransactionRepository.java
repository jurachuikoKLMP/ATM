package com.senlainc.repository;

import com.senlainc.entity.Card;
import com.senlainc.entity.Transaction;
import com.senlainc.service.FileService;

import java.util.List;
import java.util.Map;

public interface TransactionRepository {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Transaction save(Transaction entity);
    void deleteById(Long id);
    Map<Long, Transaction> getStorage();
}
