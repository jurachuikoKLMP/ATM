package com.senlainc.dto.transaction;

import com.senlainc.entity.Currency;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String fromCard;
    private String toCard;
    private BigDecimal cash;
    private Currency currency;
    private Date transactionTime;

    @Override
    public String toString(){
        return String.format("""
from: %s
to: %s
cash: %s %s
date: %s
                """, fromCard, toCard, cash, currency.getSymbol(), transactionTime.toString());
    }
}
