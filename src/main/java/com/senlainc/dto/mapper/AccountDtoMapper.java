package com.senlainc.dto.mapper;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Account;
import com.senlainc.entity.Transaction;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
import com.senlainc.security.dto.RegistrationRequest;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;

@UtilityClass
public class AccountDtoMapper {
    public static AccountFullDto convertEntityToDto(Account source){
        return AccountFullDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .surname(source.getSurname())
                .balance(source.getBalance())
                .currency(CurrencyDtoMapper.convertEntityToDto(source.getCurrency()))
                .card(CardDtoMapper.convertEntityToDto(source.getCard()))
                .build();
    }

    public static Account convertDtoToEntity(RegistrationRequest source){
        return Account.builder()
                .name(source.getName())
                .surname(source.getSurname())
                .balance(BigDecimal.ZERO)
                .currency(CurrencyRepositoryImpl.newInstance().findById(1L))
                .build();
    }
}
