package com.senlainc.service;

import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionCreationDto transactionCreationDto);
    List<TransactionDto> getAllTransactionsByCardNumber(String cardNumber);
}
