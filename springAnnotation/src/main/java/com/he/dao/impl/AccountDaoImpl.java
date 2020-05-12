package com.he.dao.impl;

import com.he.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao{

    public void saveAccount() {
        System.out.println("保存账户");
    }
}
