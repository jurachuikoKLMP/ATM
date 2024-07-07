package com.senlainc.controller;

import com.senlainc.dto.payment.PhonePaymentDto;
import com.senlainc.dto.transaction.TransactionCreationDto;
import com.senlainc.dto.transaction.TransactionDto;
import com.senlainc.entity.Transaction;
import com.senlainc.exception.InvalidChoiceError;
import com.senlainc.security.Session;
import com.senlainc.service.AccountService;
import com.senlainc.service.TransactionService;
import com.senlainc.service.impl.AccountServiceImpl;
import com.senlainc.service.impl.TransactionServiceImpl;
import com.senlainc.utils.CleanConsoleUtil;
import com.senlainc.utils.StringConstant;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Scanner;

@UtilityClass
public class AccountController {
    private static final Scanner scanner;
    private static final AccountService accountService;
    private static final TransactionService transactionService;

    static {
        scanner = new Scanner(System.in);
        accountService = new AccountServiceImpl();
        transactionService = new TransactionServiceImpl();
    }

    public static void run(){
        mainMenu();
    }

    private void mainMenu(){
        int choice;

        do{
            System.out.println(StringConstant.MAIN_MENU_UI);

            choice = scanner.nextInt();

            switch (choice){
                case 1 -> {
                    CleanConsoleUtil.CleanConsole();
                    System.out.println(accountService.getInfoById(Session.accountId).toString());
                }

                case 2 -> {
                    CleanConsoleUtil.CleanConsole();
                    System.out.println(accountService.addTransaction(createTransactionDto()).toString());
                }

                case 3 -> {
                    CleanConsoleUtil.CleanConsole();
                    accountService.putMoney(takeCash());
                }

                case 4 -> {
                    CleanConsoleUtil.CleanConsole();
                    accountService.withdrawMoney(takeCash());
                }

                case 5 -> {
                    CleanConsoleUtil.CleanConsole();
                    accountService.payPhone(createPhonePaymentDto());
                }

                case 6 -> {
                    CleanConsoleUtil.CleanConsole();
                    for(TransactionDto dto : transactionService.getAllTransactionsByCardNumber(Session.cardNumber))
                        System.out.println(dto.toString());
                }

                case 7 -> AuthenticationController.run();

                default -> throw new InvalidChoiceError(choice);
            }
        }while (choice != 7);
    }

    private static TransactionCreationDto createTransactionDto(){
        CleanConsoleUtil.CleanConsole();

        TransactionCreationDto transactionCreationDto = new TransactionCreationDto();

        System.out.print("Input cardNumber of receiver: ");
        transactionCreationDto.setToCard(scanner.next());

        System.out.print("Input amount: ");
        transactionCreationDto.setCash(new BigDecimal(scanner.next()));

        return transactionCreationDto;
    }

    private static BigDecimal takeCash(){
        CleanConsoleUtil.CleanConsole();

        System.out.print("Input amount: ");

        return new BigDecimal(scanner.next());
    }

    private static PhonePaymentDto createPhonePaymentDto(){
        CleanConsoleUtil.CleanConsole();

        PhonePaymentDto paymentDto = new PhonePaymentDto();

        System.out.print("Input phone number: ");
        paymentDto.setPhoneNumber(scanner.next());

        System.out.print("Input cash: ");
        paymentDto.setCash(new BigDecimal(scanner.next()));

        return paymentDto;
    }
}
