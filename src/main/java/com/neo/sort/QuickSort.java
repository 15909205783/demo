package com.neo.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int a[] = new int[]{12, 20, 5, 16, 15, 1, 30, 45};
        System.out.println("before sort:" + Arrays.toString(a));
        quickSort(a, 0, a.length - 1);
        System.out.println("after sort:" + Arrays.toString(a));
    }

    public static void quickSort(int[] a, int low, int hight) {
        if (low < hight) {
            // 找寻基准数据的正确索引
            int index = getIndex(a, low, hight);
            quickSort(a, low, index - 1);
            quickSort(a, index + 1, hight);
        }

    }

    /**
     * 算法思想是交换高位和低位的数据，直至高位和低位的index相等的时候找到真正的基准值的位置
     */
    private static int getIndex(int[] a, int low, int hight) {
        int povlot = a[low];
        while (low < hight) {
            //从后往前比较
            //当队尾的元素大于等于基准数据时,向前挪动high指针
            while (hight > low && a[hight] >= povlot) {
                hight--;
            }
            a[low] = a[hight];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < hight && a[low] <= povlot) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            a[hight] = a[low];
        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        a[low] = povlot;
        return low;
    }
}
