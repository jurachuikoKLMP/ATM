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
}
