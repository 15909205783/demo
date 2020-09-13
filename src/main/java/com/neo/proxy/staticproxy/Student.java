package com.neo.proxy.staticproxy;

import com.neo.proxy.Person;

import java.math.BigDecimal;

public class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void giveMoney(BigDecimal amount) {
        System.out.println(name + "上交" + amount + "班费");
    }
}
