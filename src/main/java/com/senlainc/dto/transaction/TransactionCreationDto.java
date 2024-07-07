package com.senlainc.dto.transaction;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreationDto {
    private String toCard;
    private BigDecimal cash;
}
