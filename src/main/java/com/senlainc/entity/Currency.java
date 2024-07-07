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
        return String.format("%s %s %s %s", getId(), currencyType.toString(), symbol, coefficient);
    }
}
