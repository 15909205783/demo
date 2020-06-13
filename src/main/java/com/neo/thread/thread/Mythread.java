package com.neo.thread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mythread extends Thread {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();

//        ExecutorService pool = Executors.newFixedThreadPool(5);
//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 20; i++) {
            pool.submit(runnable);
        }
        pool.shutdown();
    }
}
