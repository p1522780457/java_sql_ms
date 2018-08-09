package com.pangw.designmode.agency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Auther: pangw
 * @Date: 2018/8/9 17:48
 * @Description:
 */
public class DynamicProxy implements InvocationHandler{
    Object o ;

    public DynamicProxy(Object o) {
        this.o = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前");
        Object result = method.invoke(o,args);
        System.out.println("方法执行后");
        return result;
    }
}
