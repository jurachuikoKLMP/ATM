package com.senlainc.dto.mapper;

import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Transaction;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.sql.Timestamp;

@UtilityClass
public class TransactionDtoMapper {
    public static TransactionDto convertEntityToDto(Transaction source) {
        return TransactionDto.builder()
                .cash(source.getCash())
                .transactionTime(source.getTransactionTime())
                .toCard(source.getToCard())
                .fromCard(source.getFromCard())
                .currency(source.getCurrency())
                .build();
    }

    public static Transaction convertDtoToEntity(TransactionCreationDto source) {
        return Transaction.builder()
                .cash(source.getCash())
                .toCard(source.getToCard())
                .transactionTime(new Date(System.currentTimeMillis()))
                .build();
    }
}
