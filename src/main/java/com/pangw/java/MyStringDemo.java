package com.pangw.java;

/**
 * @Auther: pangw
 * @Date: 2018/8/9 下午3:27
 * @Description:
 */
public class MyStringDemo {
    public static void main(String[] args) {
        String s = new String("string");

        StringBuffer stringBuffer = new StringBuffer("abcdefdhijklmnopqrst");

        stringBuffer.insert(1,3);


    }

    private static void test01() {
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");
        System.out.println(str1 == str2);//false
        System.out.println(str1 == str3);//true
        System.out.println(str2 == str4);//false
    }
}
