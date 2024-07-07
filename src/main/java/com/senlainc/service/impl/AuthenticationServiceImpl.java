package com.senlainc.service.impl;

import com.senlainc.dto.mapper.AccountDtoMapper;
import com.senlainc.entity.Account;
import com.senlainc.entity.Card;
import com.senlainc.exception.BadCredentialsException;
import com.senlainc.exception.CardNumberNotFoundException;
import com.senlainc.exception.IncorrectCardTypeException;
import com.senlainc.repository.AccountRepository;
import com.senlainc.repository.CardRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.security.Session;
import com.senlainc.security.dto.AuthRequest;
import com.senlainc.security.dto.AuthResponse;
import com.senlainc.security.dto.RegistrationRequest;
import com.senlainc.security.dto.RegistrationResponse;
import com.senlainc.service.AuthenticationService;
import com.senlainc.utils.Generator;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    public AuthenticationServiceImpl(){
        this.accountRepository = AccountRepositoryImpl.newInstance();
        this.cardRepository = CardRepositoryImpl.newInstance();
    }

    @Override
    public AuthResponse authentication(AuthRequest request) {
        Card card = Optional.ofNullable(cardRepository.findByCardNumber(request.getCardNumber()))
                .orElseThrow(() -> new CardNumberNotFoundException(request.getCardNumber()));

        if(card.getPassword() == request.getPassword()){
            Session.accountId = accountRepository.findByCardNumber(card.getCardNumber()).getId();
            Session.cardId = cardRepository.findByCardNumber(card.getCardNumber()).getId();
            Session.cardNumber = card.getCardNumber();
            Session.password = card.getPassword();

            return AuthResponse.builder()
                    .status(200)
                    .message("OK")
                    .build();
        } else {
            throw new BadCredentialsException();
        }
    }

    @Override
    public RegistrationResponse registration(RegistrationRequest request) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2); //+2 года

        Account account = AccountDtoMapper.convertDtoToEntity(request);

        Card card = Card.builder()
                .cardNumber(Generator.generateCardNumberByCardType(request.getCardType()))
                .cardType(request.getCardType())
                .password(Generator.generatePinCode())
                .cvv(Generator.generateCVV())
                .duration(new Date(calendar.getTimeInMillis()))
                .build();

        switch (request.getCardType()){
            case DEFAULT -> card.setCommission(0.1);

            case GOLD -> card.setCommission(0.05);

            case PLATINUM -> card.setCommission(0.02);

            default -> throw new IncorrectCardTypeException(request.getCardType());
        }

        cardRepository.save(card);

        account.setCard(card);

        accountRepository.save(account);

        Session.accountId = accountRepository.findByCardNumber(card.getCardNumber()).getId();
        Session.cardId = cardRepository.findByCardNumber(card.getCardNumber()).getId();
        Session.cardNumber = card.getCardNumber();
        Session.password = card.getPassword();

        return RegistrationResponse.builder()
                .status(200)
                .cardNumber(card.getCardNumber())
                .password(card.getPassword())
                .cvv(card.getCvv())
                .duration(card.getDuration())
                .commission(card.getCommission())
                .build();
    }
}
