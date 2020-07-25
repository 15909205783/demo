package com.neo.mq.demo;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        //  System.out.println("3");
        int[] a = new int[]{8, 5, 6, 7, 4, 9};
        sort(a, 0, a.length - 1);
        System.out.println("sorted:");
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    public static void sort(int[] a, int low, int heigh) {
        //递归终止条件
        if (low>heigh) return;
        int start = low;
        int end = heigh;
        int key = a[low];
        while (start < end) {
            while (start < end && key<=a[end]) {
                end--;
            }
            if (start < end) {
                //交换index的值而不是基准值
                a[start] = a[end];
            }

            while (start < end && key >= a[start]) {
                start++;
            }
            if (start < end) {
                a[end] = a[start];
            }

        }
//        a[low] = a[start];
        //基准值的最终位置
        a[start] = key;
        sort(a, low, start - 1);
        sort(a, start + 1, heigh);
    }
}
