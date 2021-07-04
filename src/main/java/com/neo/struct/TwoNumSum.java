package com.neo.struct;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Description: 给定一个数组和target，求两个数相加等于target的下标
 * </p>
 *
 * @author yangwuhai
 * @since 2021-07-04
 */
public class TwoNumSum {
    public static void main(String[] args) {
        int[] solution = twoPoint(new int[]{1, 2, 3, 4, 5, 6}, 10);
        System.out.println(Arrays.toString(solution));
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] solution(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 利用map
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] solution1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int low = i, high = nums.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target - nums[i]) {
                    return new int[]{mid, i};
                } else if (nums[mid] > target - nums[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }

        }
        return new int[0];
    }

    /**
     * 双指针
     * 基于有序
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoPoint(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum == target) {
                return new int[]{low, high};
            } else if (sum > target) {
                high--;
            } else {
                low++;
            }
        }

        return new int[0];
    }
}
