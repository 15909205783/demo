package com.neo.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int a[] = new int[]{12, 20, 5, 16, 15, 1, 30, 45};
        System.out.println("before sort:" + Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println("after sort:" + Arrays.toString(a));

    }

    public static void quickSort(int[] a, int start, int end) {
        //基准位置
        int povlot = a[start];
        //循环初始位置
        int low = start;
        //循环的最大值
        int hight = end;
        while (low < hight) {
            //从后往前比较
            while (hight > low && a[hight] >= povlot) {
                hight--;
            }
            a[low] = a[hight];
            while (low < hight && a[low] <= povlot) {
                low++;
            }
            a[hight] = a[low];
        }
        a[low] = povlot;
        if (low > start) quickSort(a, low, start - 1);
        if (hight < end) quickSort(a, hight + 1, end);
    }
}
