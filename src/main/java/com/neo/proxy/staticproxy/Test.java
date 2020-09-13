package com.neo.proxy.staticproxy;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        Student stu = new Student("tom");
        stu.giveMoney(new BigDecimal(20));
        StuProxy stuProxy = new StuProxy(stu);
        stuProxy.giveMoney(new BigDecimal(99));
    }
}
