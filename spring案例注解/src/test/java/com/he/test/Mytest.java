package com.he.test;

import com.he.domain.Account;
import com.he.service.AccountService;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class Mytest {
    @Autowired
    private AccountService as;
    @Test
    public void testFindAll(){



        List<Account> list = as.findAllAccount();
        for (Account account : list) {
            System.out.println(account);
        }
    }
    @Test
    public void testFindOne(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        as.findAccountById(1);
    }
    @Test
    public void testSave(){

    }
    @Test
    public void testDelete(){

    }
    @Test
    public void testUpdate(){

    }
}
