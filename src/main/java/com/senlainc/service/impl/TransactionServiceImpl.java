package com.senlainc.service.impl;

import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Transaction;
import com.senlainc.service.TransactionService;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public Transaction createTransaction(TransactionCreationDto transactionCreationDto) {
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactionsByCardNumber(String cardNumber) {
        return List.of();
    }
}
