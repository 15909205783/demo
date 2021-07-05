package com.neo.hashmaptest;

import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author yangwuhai
 * @since 2021-06-29
 */

public class A03Bean {
    protected int number;

    public A03Bean(int number) {
        this.number = number;
    }

    /**
     * 重写hashCode()方法，只要是4的倍数，最后算出的哈希值都会是0.
     */
    @Override
    public int hashCode() {
        return number % 4;
    }

    /**
     * 也必须重写equals()方法。当发生哈希冲突的时候，需要调用equals()方法比较两个对象的实际内容是否相同。
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        A03Bean other = (A03Bean) obj;
        if (number != other.number)
            return false;
        return true;
    }
}

class A03Method_TreeifyBin2 {
    public static void main(String[] args) {
        HashMap<A03Bean, Integer> hashMap = new HashMap<>();
        hashMap.put(new A03Bean(4), 0);
        hashMap.put(new A03Bean(8), 1);
        hashMap.put(new A03Bean(12), 2);
        hashMap.put(new A03Bean(16), 3);
        hashMap.put(new A03Bean(20), 4);
        hashMap.put(new A03Bean(24), 5);
        hashMap.put(new A03Bean(28), 6);
        hashMap.put(new A03Bean(32), 7);
        hashMap.put(new A03Bean(36), 8);
        hashMap.put(new A03Bean(40), 9);
        hashMap.put(new A03Bean(44), 10);

        System.out.println("hashMap.size = " + hashMap.size());

        //查看是否所有对象都放到HashMap中了：
        for (A03Bean key : hashMap.keySet()) {
            System.out.println(key.number);
        }
    }
}