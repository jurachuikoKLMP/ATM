package com.senlainc.repository.impl;

import com.senlainc.entity.Currency;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.utils.StringConstant;

public class CurrencyRepositoryImpl extends AbstractRepositoryImpl<Currency> implements CurrencyRepository {
    private static CurrencyRepositoryImpl currencyRepository;

    private CurrencyRepositoryImpl() {
        super(StringConstant.CURRENCY_FILE_PATH);
    }

    public static CurrencyRepositoryImpl newInstance(){
        if(currencyRepository == null)
             currencyRepository = new CurrencyRepositoryImpl();

        return currencyRepository;
    }
}
