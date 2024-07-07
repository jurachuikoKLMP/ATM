package com.senlainc.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends Entity{
    private String name;
    private String surname;
    private BigDecimal balance;
    private Currency currency;
    private Card card;
    private List<Transaction> transactions;

    @Override
    public String toString(){
        StringBuilder accountData = new StringBuilder();
        accountData.append(getId()).append(" ")
                .append(name).append(" ")
                .append(surname).append(" ")
                .append(balance).append(" ");

        if (currency == null)
            accountData.append("null").append(" ");
        else
            accountData.append(currency.getId()).append(" ");

        if (card == null)
            accountData.append("null").append(" ");
        else
            accountData.append(card.getId()).append(" ");

        return accountData.toString();
    }
}
