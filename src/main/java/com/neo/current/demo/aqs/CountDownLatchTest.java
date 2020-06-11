package com.neo.current.demo.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        CountDownLatchTest(countDownLatch);
    }
    private static void CountDownLatchTest(final CountDownLatch countDownLatch) {
        //threadA尝试执行，计数器2被阻塞
        Thread threadA = new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + " await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.setName("threadA");
        //thread尝试执行，计数器为2被阻塞
        Thread threadB = new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + " await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        threadB.setName("threadB");
        //threadC在1秒后将计数器数量减1
        Thread threadC=new Thread(() -> {
            try {
                Thread.sleep(1000);
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " countDown");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadC.setName("threadC");
        //threadD在5秒后将计数器数量减1
        Thread threadD = new Thread(() -> {
            try {
                Thread.sleep(5000);
                countDownLatch.countDown();

                System.out.println(Thread.currentThread().getName() + " countDown");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadD.setName("threadD");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
