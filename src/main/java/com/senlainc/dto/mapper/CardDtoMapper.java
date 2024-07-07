package com.senlainc.dto.mapper;

import com.senlainc.dto.card.CardDto;
import com.senlainc.entity.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardDtoMapper {
    public static CardDto convertEntityToDto(Card source)
    {
        return CardDto.builder()
                .cardNumber(source.getCardNumber())
                .duration(source.getDuration())
                .commision(source.getCommision())
                .build();
    }
}
