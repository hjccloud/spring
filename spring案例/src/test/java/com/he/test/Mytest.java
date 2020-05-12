package com.he.test;

import com.he.domain.Account;
import com.he.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 * Spring整合Junit的配置
 *        1.导入spring整合的junit的jar
 *        2.使用Junit提供的一个注解吧原有的main方法，替换成Spring提供的
 *        @Runwith
 *        3.告知Spring的运行器，spring和ioc创建是基于xml还是注解，并说明其位置
 *        @ContextConfiguration
 *                  locations:指定xml文件的位置，加上classPath关键字，表示在其类路径下
 *                  classes:指定注解类所在的位置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:bean.xml")
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
