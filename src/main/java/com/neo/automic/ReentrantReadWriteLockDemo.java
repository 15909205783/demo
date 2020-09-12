package com.neo.automic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private volatile ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        try {
            rwl.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t正在写入" + key);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
//            rwl.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t正在读取");
            try {
                TimeUnit.SECONDS.sleep(5);
                Object result = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t读取完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }
}

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}
