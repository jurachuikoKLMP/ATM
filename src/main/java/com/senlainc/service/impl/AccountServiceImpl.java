package com.senlainc.service.impl;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.mapper.AccountDtoMapper;
import com.senlainc.dto.mapper.TransactionDtoMapper;
import com.senlainc.dto.payment.PhonePaymentDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Account;
import com.senlainc.entity.Transaction;
import com.senlainc.entity.enums.CurrencyType;
import com.senlainc.exception.BalanceExceededException;
import com.senlainc.exception.NotEnoughMoneyException;
import com.senlainc.exception.PhoneValidationException;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.CardRepository;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
import com.senlainc.security.Session;
import com.senlainc.service.AccountService;
import com.senlainc.service.TransactionService;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void withdrawMoney(BigDecimal cash) {
        Account account = accountRepository.findByCardNumber(Session.cardNumber);

        //todo check sum and add commission
        if(account.getBalance().subtract(cash).compareTo(BigDecimal.ZERO) < 0)
            throw new NotEnoughMoneyException();

        account.setBalance(account.getBalance().subtract(cash));

        accountRepository.save(account);
    }

    @Override
    public void putMoney(BigDecimal cash) {
        Account account = accountRepository.findByCardNumber(Session.cardNumber);

        //todo check cardType
        if (!isMaxBalanceNormal(account, cash))
            throw new BalanceExceededException();


        account.setBalance(account.getBalance().add(cash));

        accountRepository.save(account);
    }

    @Override
    @SneakyThrows
    public void payPhone(PhonePaymentDto paymentDto) {
        if(!isPhoneNumberValid(paymentDto.getPhoneNumber()))
            throw new PhoneValidationException(paymentDto.getPhoneNumber());

        Account account = accountRepository.findByCardNumber(Session.cardNumber);

        //todo commission, check sum
        account.setBalance(account.getBalance().subtract(paymentDto.getCash()));

        accountRepository.save(account);
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        Pattern pattern = Pattern.compile("^\\+\\d{12}$");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    @Override
    public void changeCurrency(long id, CurrencyType type) {
        //todo
    }

    @Override
    public TransactionDto addTransaction(TransactionCreationDto transactionCreationDto) {
        Account fromAccount = accountRepository.findByCardNumber(Session.cardNumber);
        Account toAccount = accountRepository.findByCardNumber(transactionCreationDto.getToCard());

        //todo commission

        if(fromAccount.getBalance().compareTo(transactionCreationDto.getCash()) < 0)
            throw new NotEnoughMoneyException();

        if (!isMaxBalanceNormal(toAccount,transactionCreationDto.getCash()))
            throw new BalanceExceededException();

        fromAccount.setBalance(fromAccount.getBalance().subtract(transactionCreationDto.getCash()));
        toAccount.setBalance(toAccount.getBalance().add(transactionCreationDto.getCash()));

        Transaction transaction = transactionService.createTransaction(transactionCreationDto);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return TransactionDtoMapper.convertEntityToDto(transaction);
    }

    private boolean isMaxBalanceNormal(Account account, BigDecimal cash){
        return account.getBalance().add(cash).compareTo(new BigDecimal(1000000)) <= 0;
    }
}
