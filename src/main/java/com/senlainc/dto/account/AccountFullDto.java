package com.senlainc.dto.account;

import com.senlainc.dto.card.CardDto;
import com.senlainc.dto.currency.CurrencyDto;
import com.senlainc.dto.transaction.TransactionDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountFullDto {
    private long uuid;
    private String name;
    private String surname;
    private BigDecimal balance;
    private CurrencyDto currency;
    private CardDto card;
    private List<TransactionDto> transactions;

    @Override
    public String toString(){
        return String.format("""
            uuid: %s
            name: %s
            surname: %s
            balance: %s
            currency: %s
            card number: %s
            """, uuid, name, surname, balance.toString(), currency.getSymbol(), card.getCardNumber()
        );
    }
}
