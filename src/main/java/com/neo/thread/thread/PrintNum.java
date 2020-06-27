package com.neo.thread.thread;

public class PrintNum extends Thread {
    private String name;

    public PrintNum( String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2==0){
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
