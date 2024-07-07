package com.senlainc.repository.impl;

import com.senlainc.entity.Card;
import com.senlainc.exception.CardNumberNotFoundException;
import com.senlainc.repository.CardRepository;
import com.senlainc.utils.StringConstant;

import java.util.Map;

public class CardRepositoryImpl extends AbstractRepositoryImpl<Card> implements CardRepository {
    private static CardRepositoryImpl cardRepository;

    private CardRepositoryImpl() {
        super(Card.class, StringConstant.CARDS_FILE_PATH);
    }

    public static CardRepositoryImpl newInstance(){
        if(cardRepository == null)
            cardRepository = new CardRepositoryImpl();

        return cardRepository;
    }

    public Card findByCardNumber(String cardNumber){
        for(Map.Entry<Long, Card> card : storage.entrySet())
            if(card.getValue().getCardNumber().equals(cardNumber))
                return card.getValue();

        throw new CardNumberNotFoundException(cardNumber);
    }

}
