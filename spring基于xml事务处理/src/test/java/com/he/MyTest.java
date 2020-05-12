package com.he;

import com.he.domain.Account;
import com.he.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *  使用JUnit单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class MyTest {
    @Autowired
    @Qualifier("accountService")
    private AccountService  as;
    @Test
    public void testTransfer(){
        as.tranfer("aaa","bbb",100f);
        testFind();
    }

    @Test
    public void testFind(){
        List<Account> list = as.findAllAccount();
        for (Account account : list) {
            System.out.println(account);
        }
    }

}
