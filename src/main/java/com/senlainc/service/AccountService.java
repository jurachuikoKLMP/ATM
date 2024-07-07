package com.senlainc.service;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.payment.PhonePaymentDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.entity.enums.CurrencyType;

import java.math.BigDecimal;

public interface AccountService {
    AccountFullDto getInfoById(long id);
    void withdrawMoney(long id, BigDecimal cash);
    void putMoney(long id, BigDecimal cash);
    void payPhone(long id, PhonePaymentDto paymentDto);
    //Оплатить учлуги
    void changeCurrency(long id, CurrencyType type);
    void addTransaction(long id, TransactionCreationDto transactionCreationDto);
}
