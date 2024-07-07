package com.senlainc.repository;

import com.senlainc.entity.Account;

import java.util.List;
import java.util.Map;

public interface AccountRepository {
    List<Account> findAll();
    Account findById(Long id);
    Account findByCardNumber(String cardNamber);
    Account save(Account entity);
    void deleteById(Long id);
    Map<Long, Account> getStorage();
}
