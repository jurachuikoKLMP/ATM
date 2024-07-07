package com.senlainc.entity;


import com.senlainc.entity.enums.CurrencyType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends Entity{
    private CurrencyType currencyType;
    private char symbol;
    private BigDecimal coefficient;

    @Override
    public String toString(){
        StringBuilder currencyData = new StringBuilder();
        currencyData.append(getId()).append(" ")
                .append(currencyType.toString()).append(" ")
                .append(symbol).append(" ")
                .append(coefficient.toString()).append(" ");

        return currencyData.toString();
    }

    @Override
    public void parseFieldsFromStringValue(String string) {
        String[] values = string.trim().split(" ");

        this.id = Long.valueOf(values[0]);

        switch (values[1]){
            case "BYN" -> this.currencyType = CurrencyType.BYN;
            case "USD" -> this.currencyType = CurrencyType.USD;
            case "EUR" -> this.currencyType = CurrencyType.EUR;
            case "RUB" -> this.currencyType = CurrencyType.RUB;
        }

        this.symbol = values[2].charAt(0);
        this.coefficient = new BigDecimal(values[3]);
    }
}
