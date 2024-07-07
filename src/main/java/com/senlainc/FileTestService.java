package com.senlainc;

import com.senlainc.entity.Account;
import com.senlainc.entity.Card;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.service.ExitService;
import com.senlainc.service.impl.ExitServiceImpl;

import java.math.BigDecimal;

public class FileTestService {
    public static void main(String[] args) {
        AccountRepository accountRepository = AccountRepositoryImpl.newInstance();
        ExitService exitService = new ExitServiceImpl();

        accountRepository.save(Account.builder()
                        .name("asdasd")
                        .surname("asdasd")
                        .balance(new BigDecimal(141))
                        .card(Card.builder()
                                .cvv(123)
                                .cardNumber("asdasdasd")
                                .build())
                .build());

        exitService.exit();
    }
}
