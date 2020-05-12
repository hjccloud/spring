package com.he.service.impl;

import com.he.dao.AccountDao;
import com.he.dao.impl.AccountDaoImpl;
import com.he.service.AccountService;

public class AccountServiceImpl implements AccountService{

        private AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
