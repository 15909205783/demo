package com.neo.thread.thread;

public class TestThread {
    public static void main(String[] args) {
        PrintNum p1 = new PrintNum("线程1");
        PrintNum p2 = new PrintNum("线程2");
        p1.start();
        p2.start();
    }
}
