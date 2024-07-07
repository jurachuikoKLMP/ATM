package com.senlainc.repository.impl;

import com.senlainc.entity.Transaction;
import com.senlainc.repository.TransactionRepository;
import com.senlainc.utils.StringConstant;

import java.util.HashMap;
import java.util.Map;

public class TransactionRepositoryImpl extends AbstractRepositoryImpl<Transaction> implements TransactionRepository {
    private static TransactionRepositoryImpl transactionRepository;
    public Map<Long, Long> transactionAdminManyToManyPool = new HashMap<>();

    private TransactionRepositoryImpl() {
        super(StringConstant.TRANSACTIONS_FILE_PATH);
    }

    public static TransactionRepositoryImpl newInstance(){
        if(transactionRepository == null)
            transactionRepository = new TransactionRepositoryImpl();

        return transactionRepository;
    }
}
