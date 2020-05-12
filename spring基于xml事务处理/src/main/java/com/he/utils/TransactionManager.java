package com.he.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 和事务管理相关的工具类，它包含了，开启事务，提交事务，回滚事务和释放连接
 */

//声明这是一个切面类
@Aspect


public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Pointcut("execution(* com.he.service.Impl.*.*(..))")
    public void pt() {

    }

    /**
     * 开启事务
     * 前置通知
     */
    @Before("pt()")
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     * 后置通知
     */
    @AfterReturning("pt()")
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     * 异常通知
     */
    @AfterThrowing("pt()")
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 释放连接
     * 最后通知
     */
    @After(("pt()"))
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();//还回连接池中
            connectionUtils.removeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 环绕通知
     * 可以动态的配置通知
     *
     */
    @Around(("pt()"))
    public Object arround(ProceedingJoinPoint pjp){
        Object rtValue = null;


        try {
            //得到执行方法所需的参数
            Object[] args = pjp.getArgs();

            beginTransaction();//

            //执行业务方法（切入点方法）
            rtValue = pjp.proceed(args);

            commit();
        } catch (Throwable throwable) {
            rollback();
            throwable.printStackTrace();
        }finally {
            release();
        }
        return rtValue;
    }
}

