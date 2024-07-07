package com.senlainc.entity;

import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
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

    @Override
    public String toString(){
        StringBuilder accountData = new StringBuilder();
        accountData.append(getId()).append(" ")
                .append(name).append(" ")
                .append(surname).append(" ")
                .append(balance.toString()).append(" ");

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

    @Override
    public void parseFieldsFromStringValue(String string) {
        String[] values = string.trim().split(" ");

        this.id = Long.parseLong(values[0]);
        this.name = values[1];
        this.surname = values[2];
        this.balance = new BigDecimal(values[3]);
        this.currency = CurrencyRepositoryImpl.newInstance().findById(Long.valueOf(values[4]));
        this.card = CardRepositoryImpl.newInstance().findById(Long.valueOf(values[5]));

        //todo transactions
    }
}
