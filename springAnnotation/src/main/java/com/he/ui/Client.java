package com.he.ui;

import com.he.dao.AccountDao;
import com.he.dao.impl.AccountDaoImpl;
import com.he.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {


       ApplicationContext ac =new ClassPathXmlApplicationContext("bean.xml");


        AccountService as = (AccountService) ac.getBean("accountServiceImpl");
        AccountDao ad = ac.getBean("accountDaoImpl", AccountDaoImpl.class);


        System.out.println(as);

        as.saveAccount();
    }
}
