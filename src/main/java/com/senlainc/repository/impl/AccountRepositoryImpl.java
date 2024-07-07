package com.senlainc.repository.impl;

import com.senlainc.entity.Account;
import com.senlainc.exception.CardNumberNotFoundException;
import com.senlainc.repository.AccountRepository;
import com.senlainc.utils.StringConstant;

public class AccountRepositoryImpl extends AbstractRepositoryImpl<Account> implements AccountRepository {
    private static AccountRepositoryImpl accountRepository;

    private AccountRepositoryImpl() {
        super(Account.class, StringConstant.ACCOUNTS_FILE_PATH);
    }

    public static AccountRepositoryImpl newInstance(){
        if(accountRepository == null)
            accountRepository = new AccountRepositoryImpl();

        return accountRepository;
    }

    @Override
    public Account findByCardNumber(String cardNumber){
        for(Account account : storage.values()){
            if(account.getCard().getCardNumber().equals(cardNumber))
                return account;
        }

        throw new CardNumberNotFoundException(cardNumber);
    }
}
