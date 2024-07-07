package com.senlainc.repository.impl;

import com.senlainc.entity.Transaction;
import com.senlainc.repository.TransactionRepository;
import com.senlainc.utils.StringConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryImpl extends AbstractRepositoryImpl<Transaction> implements TransactionRepository {
    private static TransactionRepositoryImpl transactionRepository;

    private TransactionRepositoryImpl() {
        super(Transaction.class, StringConstant.TRANSACTIONS_FILE_PATH);
    }

    public static TransactionRepositoryImpl newInstance(){
        if(transactionRepository == null)
            transactionRepository = new TransactionRepositoryImpl();

        return transactionRepository;
    }

    @Override
    public List<Transaction> findAllByCardNumber(String cardNumber) {
        List<Transaction> transactions = new ArrayList<>();

        for(Map.Entry<Long, Transaction> entry : storage.entrySet())
            if (entry.getValue().getFromCard().equals(cardNumber) || entry.getValue().getToCard().equals(cardNumber))
                transactions.add(entry.getValue());

        return transactions;
    }
}
