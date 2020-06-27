package com.neo.thread.thread;

public class TicketThread implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (this){
                if (this.ticket>0){
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+"卖票---->"+(this.ticket--));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TicketThread t1 = new TicketThread();
        new Thread(t1,"线程1").start();
         new Thread(t1,"线程2").start();

    }
}
