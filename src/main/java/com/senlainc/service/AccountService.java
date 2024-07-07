package com.senlainc.service;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.payment.PhonePaymentDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.enums.CurrencyType;

import java.math.BigDecimal;

public interface AccountService {
    AccountFullDto getInfoById(long id);
    void withdrawMoney(BigDecimal cash);
    void putMoney(BigDecimal cash);
    void payPhone(PhonePaymentDto paymentDto);
    //Оплатить учлуги
    void changeCurrency(long id, CurrencyType type);
    TransactionDto addTransaction(TransactionCreationDto transactionCreationDto);
}
