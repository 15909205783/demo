package com.neo.thread.thread;

public class SynchronizedTest {
    static Test t1 = new Test();
    public static void main(String[] args) {
//https://www.cnblogs.com/qdhxhz/p/10267932.html
        new Thread(new Runnable() {
            @Override
            public void run() {
                t1.writeSomething();
            }
        }).start();

        t1.start();
//        Test t2 = new Test();
//        t2.start();
    }


}
