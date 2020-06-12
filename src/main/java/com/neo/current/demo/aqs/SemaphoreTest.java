package com.neo.current.demo.aqs;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        SemphoreTest(semaphore);

    }
    private static void SemphoreTest(final Semaphore semaphore) throws InterruptedException {
        //线程A初始获取4个信号量，然后分三次释放掉了这4个信号量
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(4);
                    System.out.println(Thread.currentThread().getName()+" get 4 semaphore");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " release 1 semaphore");
                    semaphore.release(1);
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " release 1 semaphore");
                    semaphore.release(1);
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " release 2 semaphore");
                    semaphore.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadA.setName("threadA");
        //线程B初始获取了5个信号量，然后分2次释放了这5个信号量
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(5);
                    System.out.println(Thread.currentThread().getName() + " get 5 semaphore");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " release 2 semaphore");
                    semaphore.release(2);
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " release 3 semaphore");
                    semaphore.release(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadB.setName("threadB");
        //线程c初始获取了4个信号量，然后分1次释放了这4个信号量
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(4);
                    System.out.println(Thread.currentThread().getName() + " get 4 semaphore");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " release 4 semaphore");
                    semaphore.release(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadC.setName("threadC");
        //线程D初始获取了10个信号量，然后分1次释放了这10个信号量
        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire(10);
                    System.out.println(Thread.currentThread().getName() + " get 10 semaphore");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " release 10 semaphore");
                    semaphore.release(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadD.setName("threadD");
        //线程A和线程B首先分别获取了4个和5个信号量，总信号量变为了1个
        threadA.start();
        threadB.start();
        Thread.sleep(1);
        //线程C尝试获取4个发现不够则等待
        threadC.start();
        Thread.sleep(1);
        //线程D尝试获取10个发现不够则等待
        threadD.start();
    }
}
