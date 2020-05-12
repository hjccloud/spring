package com.he.service;

import com.he.domain.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface AccountService {
    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccount();
    /**
     * 查询一个
     */
    void findAccountById(Integer accountId);

    void updateAccount(Account account);
    void deleteAccount(Integer accountId);
}
