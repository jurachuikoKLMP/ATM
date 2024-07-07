package com.senlainc.entity;


import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card extends Entity{
    private String cardNumber;
    private int password;
    private int cvv;
    private Date duration;
    private double commision;

    @Override
    public String toString(){
        return String.format("%s %s %s %s %s %s", getId(), cardNumber, password, cvv, duration, commision);
    }
}
