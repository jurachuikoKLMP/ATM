package com.senlainc.service.impl;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.mapper.AccountDtoMapper;
import com.senlainc.dto.payment.PhonePaymentDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.entity.Account;
import com.senlainc.entity.Transaction;
import com.senlainc.entity.enums.CurrencyType;
import com.senlainc.exception.NotEnoughMoneyException;
import com.senlainc.exception.PhoneValidationException;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.CardRepository;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
import com.senlainc.service.AccountService;
import com.senlainc.service.TransactionService;
import lombok.SneakyThrows;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private CurrencyRepository currencyRepository;
    private TransactionService transactionService;

    public AccountServiceImpl() {
        this.accountRepository = AccountRepositoryImpl.newInstance();
        this.cardRepository = CardRepositoryImpl.newInstance();
        this.currencyRepository = CurrencyRepositoryImpl.newInstance();
        this.transactionService = new TransactionServiceImpl();
    }

    @Override
    public AccountFullDto getInfoById(long id) {
        return AccountDtoMapper.convertEntityToDto(accountRepository.findById(id));
    }

    @Override
    public void withdrawMoney(long id, BigDecimal cash) {
        Account account = accountRepository.findById(id);

        //todo check sum and add commission
        account.setBalance(account.getBalance().subtract(cash));

        accountRepository.save(account);
    }

    @Override
    public void putMoney(long id, BigDecimal cash) {
        Account account = accountRepository.findById(id);

        //todo check cardType
        account.setBalance(account.getBalance().add(cash));

        accountRepository.save(account);
    }

    @Override
    @SneakyThrows
    public void payPhone(long id, PhonePaymentDto paymentDto) {
        if(!isPhoneNumberValid(paymentDto.getPhoneNumber()))
            throw new PhoneValidationException(paymentDto.getPhoneNumber());

        Account account = accountRepository.findById(id);

        //todo commission, check sum
        account.setBalance(account.getBalance().subtract(paymentDto.getCash()));

        accountRepository.save(account);
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        return true; //todo add logic
    }

    @Override
    public void changeCurrency(long id, CurrencyType type) {
        //todo
    }

    @Override
    public void addTransaction(long id, TransactionCreationDto transactionCreationDto) {
        Account fromAccount = accountRepository.findById(id);
        Account toAccount = accountRepository.findByCardNumber(transactionCreationDto.getToCard());

        //todo commission

        if(fromAccount.getBalance().compareTo(transactionCreationDto.getCash()) < 0)
            throw new NotEnoughMoneyException();

        fromAccount.setBalance(fromAccount.getBalance().subtract(transactionCreationDto.getCash()));
        toAccount.setBalance(toAccount.getBalance().add(transactionCreationDto.getCash()));

        Transaction transaction = transactionService.createTransaction(transactionCreationDto);

        toAccount.getTransactions().add(transaction);
        fromAccount.getTransactions().add(transaction);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
