package com.senlainc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Entity{
    private String fromCard;
    private String toCard;
    private BigDecimal cash;
    private Currency currency;
    private Date transactionTime;

    @Override
    public String toString(){
        StringBuilder transactionData = new StringBuilder(String.format("%s %s %s %s %s ", getId(), fromCard, toCard, cash, transactionTime));

        if(currency == null)
            transactionData.append("null").append(" ");
        else
            transactionData.append(currency.getId()).append(" ");

        return transactionData.toString();
    }
}
