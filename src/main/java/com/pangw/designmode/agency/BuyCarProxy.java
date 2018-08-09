package com.pangw.designmode.agency;

/**
 * @Auther: pangw
 * @Date: 2018/8/9 17:34
 * @Description:
 */
public class BuyCarProxy implements IBuyCar{
    private Customer customer;

    public BuyCarProxy(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void buyCar() {
        if(customer.getCash()<100){
            System.out.println("钱太少了 买不到");
        }else{
            customer.buyCar();
        }

    }
}
