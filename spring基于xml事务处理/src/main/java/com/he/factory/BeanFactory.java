package com.he.factory;

import com.he.domain.Account;
import com.he.service.AccountService;
import com.he.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {
    private AccountService accountService;
    private TransactionManager txManger;

    public void setTxManger(TransactionManager txManger) {
        this.txManger = txManger;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

   /* *
     * 获取Service代理对象
     * @return
     */
    public AccountService getAccountService() {
         return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try{Object rtValue;
                        //添加事务支持
                        //1.开启事务
                        txManger.beginTransaction();
                        //2.执行操作
                        rtValue = method.invoke(accountService,args);
                        //3.提交事务
                        txManger.commit();
                        //4.返回结果
                        return rtValue;
                        }catch (Exception e){
                            txManger.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //释放连接
                            txManger.release();
                        }

                    }
                });

    }
}
