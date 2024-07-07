package com.senlainc.repository.impl;

import com.senlainc.entity.Card;
import com.senlainc.repository.CardRepository;
import com.senlainc.utils.StringConstant;

public class CardRepositoryImpl extends AbstractRepositoryImpl<Card> implements CardRepository {
    private static CardRepositoryImpl cardRepository;

    private CardRepositoryImpl() {
        super(StringConstant.CARDS_FILE_PATH);
    }

    public static CardRepositoryImpl newInstance(){
        if(cardRepository == null)
            cardRepository = new CardRepositoryImpl();

        return cardRepository;
    }
}
