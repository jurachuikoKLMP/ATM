package com.senlainc.utils;

import com.senlainc.entity.enums.CardType;
import com.senlainc.exception.IncorrectCardTypeException;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Generator {
    private static final Random random;

    static {
        random = new Random();
    }

    public static String generateCardNumberByCardType(CardType cardType){ //todo cardNumber is unique
        StringBuilder generatedCardNumber = new StringBuilder();

        switch (cardType){
            case DEFAULT -> {
                generatedCardNumber.append(StringConstant.DEFAULT_CARD_NUMBER_STARTING_SYMBOLS);
            }
            case GOLD -> {
                generatedCardNumber.append(StringConstant.GOLD_CARD_NUMBER_STARTING_SYMBOLS);
            }
            case PLATINUM -> {
                generatedCardNumber.append(StringConstant.PLATINUM_CARD_NUMBER_STARTING_SYMBOLS);
            }
            default -> {
                throw new IncorrectCardTypeException(cardType);
            }
        }

        int nextNumber;
        for(int i = 0; i < 3; ++i){
            nextNumber = random.nextInt(1000, 9999);

            generatedCardNumber.append(nextNumber);
        }

        return generatedCardNumber.toString();
    }

    public static int generatePinCode(){
        return random.nextInt(1000, 9999);
    }

    public static int generateCVV() {
        return random.nextInt(100, 999);
    }
}
