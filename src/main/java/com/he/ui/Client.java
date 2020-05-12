package com.he.ui;

import com.he.dao.AccountDao;
import com.he.dao.impl.AccountDaoImpl;
import com.he.service.AccountService;
import com.he.service.impl.AccountServiceImpl;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Client {
    /**
     * 获取spring的ioc核心容器，并根据ID获取对象
     *
     * ApplicationContext的三个常用实现类
     *         ClassPathXmlApplicationContext:他可以加载类路径下的配置文件，
     *         FileSystemXmlApplicationContext：加载磁盘任意路径下的配置文件，要有访问权限
     *         AnnotationXmlApplicationContext: 读取注解创建容器的
     *
     * 核心容器的两个接口引发出的问题
     * ApplicationContext：单例对象采用（饿汉）
     *      构建核心容器时，创建对象采取立即加载，读取完配置文件就会创建对象
     * BeanFactory:         多例对象采用（懒汉）
     *      构建核心容器是，创建对象采取延迟加载，调用方法时创建对象
     * @param args
     */
    public static void main(String[] args) {

        //获取核心容器对象
       ApplicationContext ac =new ClassPathXmlApplicationContext("bean.xml");
       // ApplicationContext ac = new FileSystemXmlApplicationContext("这里写绝对路径");
        //根据id获取bean对象
        AccountService as = (AccountService) ac.getBean("accountService");
//        AccountDao ad  = ac.getBean("accountDao", AccountDao.class);


        System.out.println(as);
//        System.out.println(ad);
        as.saveAccount();
    }
}
