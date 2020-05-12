package com.he.service.impl;

import com.he.dao.AccountDao;
import com.he.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDao accountDao;
    public void saveAccount() {

       accountDao.saveAccount();
    }
}
