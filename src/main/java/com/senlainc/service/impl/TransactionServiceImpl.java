package com.senlainc.service.impl;

import com.senlainc.dto.mapper.TransactionDtoMapper;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Account;
import com.senlainc.entity.Transaction;
import com.senlainc.exception.CardNumberNotFoundException;
import com.senlainc.exception.NotEnoughMoneyException;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.TransactionRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.TransactionRepositoryImpl;
import com.senlainc.security.Session;
import com.senlainc.service.TransactionService;
import lombok.SneakyThrows;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(){
        this.accountRepository = AccountRepositoryImpl.newInstance();
        this.transactionRepository = TransactionRepositoryImpl.newInstance();
    }

    @Override
    @SneakyThrows
    public Transaction createTransaction(TransactionCreationDto transactionCreationDto) {
        Account from = accountRepository.findByCardNumber(Session.cardNumber);

        if(from.getBalance().compareTo(transactionCreationDto.getCash())<0)
            throw new NotEnoughMoneyException();

        from.setBalance(from.getBalance().subtract(transactionCreationDto.getCash()));

        Account to = Optional.ofNullable(accountRepository.findByCardNumber(transactionCreationDto.getToCard()))
                .orElseThrow(() -> new CardNumberNotFoundException(transactionCreationDto.getToCard()));

        to.setBalance(to.getBalance().add(transactionCreationDto.getCash()));

        Transaction transaction = Transaction.builder()
                .fromCard(Session.cardNumber)
                .toCard(transactionCreationDto.getToCard())
                .cash(transactionCreationDto.getCash())
                .transactionTime(new Date(System.currentTimeMillis()))
                .build();

        transactionRepository.save(transaction);

        return transaction;
    }

    @Override
    public List<TransactionDto> getAllTransactionsByCardNumber(String cardNumber) {
        return transactionRepository.findAllByCardNumber(cardNumber).stream()
                .map(TransactionDtoMapper::convertEntityToDto)
                .toList();
    }
}
