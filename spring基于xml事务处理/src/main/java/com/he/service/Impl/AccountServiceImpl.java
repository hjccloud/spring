package com.he.service.Impl;

import com.he.dao.AccountDao;
import com.he.domain.Account;
import com.he.service.AccountService;
import com.he.utils.TransactionManager;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;


public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    public void findAccountById(Integer accountId) {
        accountDao.findAccountById(accountId);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    public void tranfer(String sourceId, String targeName, Float money) {
        System.out.println("transfer");
        //根据名称name查询传出账户
        Account account1 = accountDao.findByName(sourceId);
        //根据名称name查询转入账户
        Account account2 = accountDao.findByName(targeName);
        //装入账户加钱
        account1.setMoney(account1.getMoney()+money);
        //转出账户减钱
        account2.setMoney(account2.getMoney()-money);
        //更新账户
        accountDao.updateAccount(account1);
        accountDao.updateAccount(account2);

    }
}
