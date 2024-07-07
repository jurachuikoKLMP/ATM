package com.senlainc.service.impl;

import com.senlainc.entity.Account;
import com.senlainc.entity.Card;
import com.senlainc.entity.Currency;
import com.senlainc.entity.Transaction;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.CardRepository;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.repository.TransactionRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
import com.senlainc.repository.impl.TransactionRepositoryImpl;
import com.senlainc.service.ExitService;
import com.senlainc.service.FileService;
import com.senlainc.utils.StringConstant;

import java.util.List;
import java.util.Map;

public class ExitServiceImpl implements ExitService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private CurrencyRepository currencyRepository;
    private TransactionRepository transactionRepository;
    
    private FileService fileService;
    
    public ExitServiceImpl(){
        this.accountRepository = AccountRepositoryImpl.newInstance();
        this.cardRepository = CardRepositoryImpl.newInstance();
        this.currencyRepository = CurrencyRepositoryImpl.newInstance();
        this.transactionRepository = TransactionRepositoryImpl.newInstance();
        
        fileService = new FileServiceImpl();
    }
    @Override
    public void exit() {
        saveAccountsData();
        saveCardsData();
        saveCurrencyData();
        saveTransactionsData();
    }
    
    private void saveAccountsData(){
        Map<Long, Account> accounts = accountRepository.getStorage();
        
        StringBuilder accountData = new StringBuilder();

        accountData.append(StringConstant.ACCOUNT_DATABASE_HEADER);
        
        for(Map.Entry<Long, Account> account : accounts.entrySet())
            accountData.append(account.getValue().toString()).append("\n");
        
        fileService.rewriteFile(StringConstant.ACCOUNTS_FILE_PATH, accountData.toString());
    }

    private void saveCardsData(){
        Map<Long, Card> cards = cardRepository.getStorage();

        StringBuilder cardsData = new StringBuilder();

        cardsData.append(StringConstant.CARD_DATABASE_HEADER);

        for(Map.Entry<Long, Card> card : cards.entrySet())
            cardsData.append(card.getValue().toString()).append("\n");

        fileService.rewriteFile(StringConstant.CARDS_FILE_PATH, cardsData.toString());
    }

    private void saveCurrencyData(){
        Map<Long, Currency> currencies = currencyRepository.getStorage();

        StringBuilder currencyData = new StringBuilder();

        currencyData.append(StringConstant.CURRENCY_DATABASE_HEADER);

        for(Map.Entry<Long, Currency> currency : currencies.entrySet())
            currencyData.append(currency.getValue().toString()).append("\n");

        fileService.rewriteFile(StringConstant.CURRENCY_FILE_PATH, currencyData.toString());
    }

    private void saveTransactionsData(){
        Map<Long, Transaction> transactions = transactionRepository.getStorage();

        StringBuilder transactionData = new StringBuilder();

        transactionData.append(StringConstant.TRANSACTION_DATABASE_HEADER);

        for(Map.Entry<Long, Transaction> transaction : transactions.entrySet())
            transactionData.append(transaction.getValue().toString()).append("\n");

        fileService.rewriteFile(StringConstant.TRANSACTIONS_FILE_PATH, transactionData.toString());
    }
}
