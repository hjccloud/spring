package com.he.dao;

import com.he.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface AccountDao {
    List<Account> findAllAccount();

    void findAccountById(Integer accountId);

    void updateAccount(Account account);
    void deleteAccount(Integer accountId);
    Account findByName(String name);
}
