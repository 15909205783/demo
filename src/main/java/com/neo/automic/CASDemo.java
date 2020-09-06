package com.neo.automic;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2019));
        System.out.println(atomicInteger.compareAndSet(5,2018));
        atomicInteger.getAndIncrement();
    }
}
