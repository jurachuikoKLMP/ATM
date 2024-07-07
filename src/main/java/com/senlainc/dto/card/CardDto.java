package com.senlainc.dto.card;

import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    protected Date duration;
    protected double commision;
    protected String cardNumber;
}
