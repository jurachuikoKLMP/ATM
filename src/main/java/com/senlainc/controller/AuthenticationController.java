package com.senlainc.controller;

import com.senlainc.entity.Account;
import com.senlainc.entity.enums.CardType;
import com.senlainc.exception.InvalidChoiceError;
import com.senlainc.security.dto.AuthRequest;
import com.senlainc.security.dto.RegistrationRequest;
import com.senlainc.service.AuthenticationService;
import com.senlainc.service.ExitService;
import com.senlainc.service.impl.AuthenticationServiceImpl;
import com.senlainc.service.impl.ExitServiceImpl;
import com.senlainc.utils.StringConstant;
import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class AuthenticationController {
    private static final Scanner scanner;
    private static final AuthenticationService authenticationService;
    private static final ExitService exitService;

    static {
        scanner = new Scanner(System.in);
        authenticationService = new AuthenticationServiceImpl();
        exitService = new ExitServiceImpl();
    }

    public static void run(){
        initialMenu();
    }

    public static void initialMenu(){
        int choice;

        do{
            System.out.println(StringConstant.INITIAL_MENU_UI);

            choice = scanner.nextInt();

            switch (choice){
                case 1 -> {
                    System.out.println(authenticationService.registration(createRegRequest()).toString());
                    AccountController.run();
                }

                case 2 -> {
                    System.out.println(authenticationService.authentication(createAuthRequest()).toString());
                    AccountController.run();
                }

                case 3 -> exitService.exit();

                default -> throw new InvalidChoiceError(choice);
            }
        }while (choice != 3);
    }

    private static AuthRequest createAuthRequest(){
        AuthRequest authRequest = new AuthRequest();

        System.out.print("Input card number: ");
        authRequest.setCardNumber(scanner.next());

        System.out.print("Input card password: ");
        authRequest.setPassword(scanner.nextInt());

        return authRequest;
    }

    private static RegistrationRequest createRegRequest(){
        RegistrationRequest request = new RegistrationRequest();

        int choice;

        do {
            System.out.println(StringConstant.CHOOSE_CARD_TYPE_UI);

            choice = scanner.nextInt();

            switch (choice){
                case 1 -> request.setCardType(CardType.DEFAULT);

                case 2 -> request.setCardType(CardType.GOLD);

                case 3 -> request.setCardType(CardType.PLATINUM);

                default -> throw new InvalidChoiceError(choice);
            }
        } while (choice <= 0 || choice > 3);

        System.out.print("Input name: ");
        request.setName(scanner.next());

        System.out.print("Input surname: ");
        request.setSurname(scanner.next());

        return request;
    }
}
