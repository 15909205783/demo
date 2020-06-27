package com.neo.thread.thread;

public class Test extends Thread {
    @Override
    public void run() {
        writeSomething();
    }

    //非静态方法，如果创建的是两个对象，则这种锁无效，非静态的sychronized方法相当于拿到的是对象this的锁
    //因此当多个线程调用这个同步的方法时，并不能起到并发控制的作用
    public synchronized void writeSomething() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println(" ");
    }
}
