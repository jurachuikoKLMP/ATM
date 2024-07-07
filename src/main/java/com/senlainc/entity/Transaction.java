package com.senlainc.entity;

import com.senlainc.repository.impl.CurrencyRepositoryImpl;
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
        StringBuilder transactionData = new StringBuilder();
        transactionData.append(getId()).append(" ")
                .append(fromCard).append(" ")
                .append(toCard).append(" ")
                .append(cash).append(" ")
                .append("%s").append(" ")
                .append(transactionTime.toString()).append(" ");

        if(this.currency == null)
            return String.format(transactionData.toString(), "null");
        else
            return String.format(transactionData.toString(), currency.getId());
    }

    @Override
    public void parseFieldsFromStringValue(String string) {
        String[] values = string.trim().split(" ");

        this.id = Long.valueOf(values[0]);
        this.fromCard = values[1];
        this.toCard = values[2];
        this.cash = new BigDecimal(values[3]);
        this.currency = CurrencyRepositoryImpl.newInstance().findById(Long.valueOf(values[4]));
        this.transactionTime = Date.valueOf(values[5]);
    }
}
