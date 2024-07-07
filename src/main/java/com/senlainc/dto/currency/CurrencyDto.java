package com.senlainc.dto.currency;

import com.senlainc.entity.enums.CurrencyType;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {
    private CurrencyType currencyType;
    private char symbol;
    private BigDecimal coefficient;
}
