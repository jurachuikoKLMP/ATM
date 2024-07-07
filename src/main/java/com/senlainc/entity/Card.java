package com.senlainc.entity;


import com.senlainc.entity.enums.CardType;
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
    private double commission;
    private CardType cardType;

    @Override
    public String toString(){
        StringBuilder cardData = new StringBuilder();
        cardData.append(getId()).append(" ")
                .append(cardNumber).append(" ")
                .append(password).append(" ")
                .append(cvv).append(" ")
                .append(duration.toString()).append(" ")
                .append(commission).append(" ")
                .append(cardType.toString()).append(" ");

        return cardData.toString();
    }

    @Override
    public void parseFieldsFromStringValue(String string) {
        String[] values = string.trim().split(" ");

        this.id = Long.valueOf(values[0]);
        this.cardNumber = values[1];
        this.password = Integer.valueOf(values[2]);
        this.cvv = Integer.valueOf(values[3]);
        this.duration = Date.valueOf(values[4]);
        this.commission = Double.valueOf(values[5]);

        switch (values[6]){
            case "GOLD" -> this.cardType = CardType.GOLD;
            case "PLATINUM" -> this.cardType = CardType.PLATINUM;
            case "DEFAULT" -> this.cardType = CardType.DEFAULT;
        }
    }
}
