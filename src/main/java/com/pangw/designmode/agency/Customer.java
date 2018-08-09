package com.pangw.designmode.agency;

/**
 * @Auther: pangw
 * @Date: 2018/8/9 17:32
 * @Description:
 */
public class Customer implements IBuyCar{
    private int cash;

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    @Override
    public void buyCar() {
        System.out.println("买车花费了"+cash+"元");
    }
}
