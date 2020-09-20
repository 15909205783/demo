package com.neo.proxy.staticproxy;

import com.neo.proxy.Person;

import java.math.BigDecimal;

public class StuProxy implements Person {
    private Student stu;

    public StuProxy(Student stu) {
        this.stu = stu;
    }

    @Override
    public void giveMoney(BigDecimal amount) {
        stu.giveMoney(amount);
    }

    @Override
    public void increatMoney(BigDecimal prc) {
        stu.increatMoney(prc);
    }
}
