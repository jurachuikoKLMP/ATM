package com.senlainc.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringConstant {
    //PATHS
    public static final String ACCOUNTS_FILE_PATH = "src/main/resources/accounts.txt";
    public static final String CARDS_FILE_PATH = "src/main/resources/cards.txt";
    public static final String CURRENCY_FILE_PATH = "src/main/resources/currency.txt";
    public static final String TRANSACTIONS_FILE_PATH = "src/main/resources/transactions.txt";

    //CARD NUMBER GENERATION SETTINGS
    public static final String DEFAULT_CARD_NUMBER_STARTING_SYMBOLS = "4100";
    public static final String GOLD_CARD_NUMBER_STARTING_SYMBOLS = "4285";
    public static final String PLATINUM_CARD_NUMBER_STARTING_SYMBOLS = "9112";

    //DATABASE HEADERS
    public static final String ACCOUNT_DATABASE_HEADER = "id name surname balance currency_id card_id\n";
    public static final String CARD_DATABASE_HEADER = "id card_number password cvv duration commission card_type\n";
    public static final String CURRENCY_DATABASE_HEADER = "id currency_type symbol coefficient\n";
    public static final String TRANSACTION_DATABASE_HEADER = "id from_card to_card cash currency_id transaction_time\n";
    public static final String ACCOUNT_TRANSACTION_DATABASE_HEADER = "account_id transaction_id\n";

    //UI
    public static final String INITIAL_MENU_UI = """
            1. REGISTRATION
            2. LOGIN
            3. EXIT
            """;
    public static final String MAIN_MENU_UI = """
            1. GET INFO
            2. CREATE TRANSACTION
            3. PUT MONEY TO CARD
            4. GET MONEY FROM CARD
            5. SERVICES PAYMENT
            6. GET TRANSACTIONS HISTORY
            7. BACK
            """;
    public static final String CHOOSE_CARD_TYPE_UI = """
            Choose card type:
            1. DEFAULT
            2. GOLD
            3. PLATINUM
            """;
}
