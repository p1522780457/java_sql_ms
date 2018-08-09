package com.pangw.designmode.agency;

import sun.applet.Main;

import java.lang.reflect.Proxy;

/**
 * @Auther: pangw
 * @Date: 2018/8/9 17:35
 * @Description:
 */
public class MyProxyTest {
    public static void main(String[] arfs){
//        静态执行
//        Customer c = new Customer();
//        c.setCash(120);
//       BuyCarProxy bc = new BuyCarProxy(c);
//       bc.buyCar();

        //动态

        Customer customer = new Customer();
        customer.setCash(100);

        DynamicProxy dynamicProxy = new DynamicProxy(customer);
        IBuyCar buyCar = (IBuyCar) Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(),customer.getClass().getInterfaces(),dynamicProxy);
        buyCar.buyCar();

    }
}
