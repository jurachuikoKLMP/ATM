package com.senlainc.dto.mapper;

import com.senlainc.dto.account.AccountFullDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Account;
import com.senlainc.entity.Transaction;
import lombok.experimental.UtilityClass;

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
                .transactions(source.getTransactions() == null ? new ArrayList<>() : source.getTransactions().stream()
                        .map(TransactionDtoMapper::convertEntityToDto).toList())
                .build();
    }
}
