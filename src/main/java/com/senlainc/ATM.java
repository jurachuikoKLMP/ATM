package com.senlainc;

import com.senlainc.controller.AuthenticationController;
import com.senlainc.repository.CurrencyRepository;
import com.senlainc.repository.impl.AccountRepositoryImpl;
import com.senlainc.repository.impl.CardRepositoryImpl;
import com.senlainc.repository.impl.CurrencyRepositoryImpl;
import com.senlainc.repository.impl.TransactionRepositoryImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ATM {
   public static void run(){
       initDatabase();
       AuthenticationController.run();
   }

   private static void initDatabase(){
       CurrencyRepositoryImpl.newInstance();
       TransactionRepositoryImpl.newInstance();
       CardRepositoryImpl.newInstance();
       AccountRepositoryImpl.newInstance();
   }
}
